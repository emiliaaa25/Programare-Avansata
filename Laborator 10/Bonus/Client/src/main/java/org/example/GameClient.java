package org.example;
import java . net .*;
import java . io .*;
import java.time.Duration;
import java.util.Objects;
import java.util.Scanner;
import java.time.Duration;
import java.time.Instant;


import static java.lang.Thread.sleep;

public class GameClient {
    public static void main ( String [] args ) throws IOException
    {
        int [][] yourBoard = new int [11][11];
        int [][] enemyBoard = new int [11][11];
        int MAXTIME = 1000000; // 100 sec
        boolean gameStarted = false;
        int turn = 0;
        int time = 0;

        String adresaServer = "127.0.0.1" ;
        int PORT = 8100;
        Socket socket = null ;
        PrintWriter out = null ;
        BufferedReader in = null ;
        String cerere = null, raspuns ;
        try {
            socket = new Socket ( adresaServer , PORT ) ;
            out = new PrintWriter ( socket . getOutputStream () , true ) ;
            in = new BufferedReader ( new InputStreamReader (
                    socket . getInputStream () ) ) ;
            Scanner scan = new Scanner(System.in);

            while(true)
            {
                if(gameStarted)
                {
                    if(turn == 1)
                    {
                        Instant start = Instant.now();
                        System.out.println("Ce piesa doriti sa atacati? (exemplu: 1A)");

                        cerere = scan.nextLine();
                        cerere = "atac:" + cerere;
                        Instant end = Instant.now();
                        Duration timeElapsed = Duration.between(start, end);
                        time += (int) timeElapsed.toMillis();
                        if(time > MAXTIME)
                        {
                            cerere = "timpExpirat";
                            turn = 0;
                            gameStarted = false;
                            time = 0;
                            out.println(cerere);
                            raspuns = in.readLine();
                            System.out.println(raspuns);
                            continue;
                        }
                    }
                    else
                    {
                        System.out.println("Este randul inamicului sa atace!");
                        sleep(10000);
                        cerere = "nimic";
                    }
                }
                else
                {
                    yourBoard = new int[11][11];
                    enemyBoard = new int[11][11];
                    System.out.println("Introduceti o cerere: ");
                    cerere = scan.nextLine();
                }

                out.println(cerere);
                raspuns = in.readLine();
                System.out.println(raspuns);
                if(Objects.equals(cerere, "exit") || Objects.equals(cerere, "stop"))
                {
                    break;
                }
                else if(Objects.equals(raspuns, "reset") || Objects.equals(raspuns, "Ai castigat! Felicitari!") || Objects.equals(raspuns, "Ai castigat! Timpul inamicului a expirat!"))
                {
                    turn = 0;
                    gameStarted = false;
                    time = 0;
                }
                else if(Objects.equals(cerere, "join game") || Objects.equals(cerere, "create game") || Objects.equals(cerere, "nimic") || turn == 1)
                {
                    gameStarted = true;
                    System.out.println("Boardurile tale: ");
                    // Preia boardurile
                    int n = 0, i, j, val;
                    while(raspuns.charAt(n) != '|' )
                    {
                        i = j = val = 0;

                        val = raspuns.charAt(n) - '0';
                        n += 2;
                        while(raspuns.charAt(n) >= '0' && raspuns.charAt(n) <= '9')
                        {
                            i = i * 10 + raspuns.charAt(n) - '0';
                            n ++;
                        }
                        // sarim peste spatiu
                        n++;
                        while(raspuns.charAt(n) >= '0' && raspuns.charAt(n) <= '9')
                        {
                            j = j * 10 + raspuns.charAt(n) - '0';
                            n ++;
                        }
                        yourBoard[i][j] = val;
                        if(raspuns.charAt(n) == ',')
                            n ++; // sarim peste virgula
                    }
                    n ++;
                    // boardul inamic
                    while(raspuns.charAt(n) != '|' && raspuns.charAt(n) != '\0')
                    {
                        i = j = val = 0;
                        val = raspuns.charAt(n) - '0';
                        n += 2;
                        while(raspuns.charAt(n) >= '0' && raspuns.charAt(n) <= '9')
                        {
                            i = i * 10 + raspuns.charAt(n) - '0';
                            n ++;
                        }
                        // sarim peste spatiu
                        n++;
                        while(raspuns.charAt(n) >= '0' && raspuns.charAt(n) <= '9')
                        {
                            j = j * 10 + raspuns.charAt(n) - '0';
                            n ++;
                        }
                        enemyBoard[i][j] = val;
                        if(raspuns.charAt(n) == ',') n ++; // sarim peste virgula
                    }
                    turn = raspuns.charAt(++ n) - '0';
                    System.out.println("Boardul tau: ");
                    for(i = 1; i <= 10; ++ i)
                    {
                        for(j = 1; j <= 10; ++ j)
                        {
                            System.out.print(yourBoard[i][j] + " ");
                        }
                        System.out.println();
                    }
                    System.out.println("Boardul inamic: ");
                    System.out.println("   A B C D E F G H I J");
                    for(i = 1; i <= 10; ++ i)
                    {
                        System.out.print(i + "  ");
                        for(j = 1; j <= 10; ++ j)
                         if(enemyBoard[i][j] > 1)
                         {
                            System.out.print(enemyBoard[i][j] + " ");
                         }
                         else
                            System.out.print(0 + " ");
                        System.out.println();
                    }


                }
            }
        } catch ( UnknownHostException e ) {
            System . err . println ( " Serverul nu poate fi gasit \n " + e )
            ;
            System . exit (1) ;
        } catch (InterruptedException e) {
            System.out.println("Eroare");
            throw new RuntimeException(e);
        } finally {
            if ( out != null )
                out . close () ;
            if ( in != null )
                in . close () ;
            if ( socket != null )
                socket . close () ;
        }
    }
}
