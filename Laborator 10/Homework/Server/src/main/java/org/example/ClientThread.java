package org.example;

import java . net .*;
import java . io .*;
import java.util.Objects;

import static org.example.NeedAlive.findBoardPair;

public class ClientThread extends Thread {
    Socket socket = null ;
    Board yourBoard;
    Board enemyBoard;
    public ClientThread ( Socket socket)
    {
        this.socket = socket ;
    }
    public void run ()
    {
        String cerere;
        StringBuilder raspuns ;
        try
        {
            BufferedReader in = new BufferedReader ( new
                    InputStreamReader (
                    socket . getInputStream () ) ) ;
            PrintWriter out = new PrintWriter (
                    socket . getOutputStream () ) ;


            while(true)
            {
                cerere = in.readLine();
                System.out.println("Cerere de la client: " + cerere);
                if(cerere == null)
                {
                    return;
                }

                raspuns = new StringBuilder("Server received the request ... ");

                if (Objects.equals(cerere, "stop"))
                {
                    raspuns = new StringBuilder("Server stopped ...");
                    out.println(raspuns);
                    out.flush();
                    System.exit(0);
                    // Inchidem socketul deschis pentru clientul curent
                    try
                    {
                        socket . close () ;
                    } catch ( IOException e )
                    {
                        System . err . println ( " Socketul nu poate fi inchis \n " +
                                e ) ;
                    }
                    return;
                }
                else if(Objects.equals(cerere, "exit")) {
                    out.println(raspuns);
                    out.flush();
                    try
                    {
                        socket . close () ;
                    } catch ( IOException e )
                    {
                        System . err . println ( " Socketul nu poate fi inchis \n " +  e ) ;
                    }
                    return;
                }
                else if(Objects.equals(cerere, "create game"))
                {
                    yourBoard = new Board();
                    yourBoard.isTaken = true;
                    enemyBoard = new Board();
                    System.out.println("Game created on board " + yourBoard.boardId);
                    NeedAlive.gamesStarted.add(new BoardPair(yourBoard, enemyBoard));
                    while (!enemyBoard.isTaken) {
                        sleep(10);
                    }
                    raspuns = new StringBuilder();
                    for(int i = 1; i <= 10; ++ i) {
                        for (int j = 1; j <= 10; ++j)
                            if (yourBoard.board[i][j] > 0) {
                                raspuns.append(yourBoard.board[i][j]).append(" ").append(i).append(" ").append(j).append(",");
                            }
                    }
                    raspuns.append("|");
                    for(int i = 1; i <= 10; ++ i)
                    {
                        for (int j = 1; j <= 10; ++j)
                            if (enemyBoard.board[i][j] > 0 ) {
                                raspuns.append(enemyBoard.board[i][j]).append(" ").append(i).append(" ").append(j).append(",");
                            }
                    }
                    raspuns.append("|").append(yourBoard.turn);
                }
                else if(Objects.equals(cerere, "join game"))
                {
                    yourBoard = NeedAlive.findEmptyBoard();
                    yourBoard.isTaken = true;
                    raspuns = new StringBuilder("Game joined ...Boar0 id: " + yourBoard.boardId);
                    enemyBoard = NeedAlive.ThePair(yourBoard);
                    while(NeedAlive.ThePair(yourBoard) == null || !NeedAlive.ThePair(yourBoard).isTaken)
                    {
                        sleep(10);
                    }

                    raspuns = new StringBuilder();
                    for(int i = 1; i <= 10; ++ i) {
                        for (int j = 1; j <= 10; ++j)
                            if (yourBoard.board[i][j] > 0) {
                                raspuns.append(yourBoard.board[i][j]).append(" ").append(i).append(" ").append(j).append(",");
                            }
                    }
                    raspuns.append("|");
                    for(int i = 1; i <= 10; ++ i)
                    {
                        for (int j = 1; j <= 10; ++j)
                            if (enemyBoard.board[i][j] > 0) {
                                raspuns.append(enemyBoard.board[i][j]).append(" ").append(i).append(" ").append(j).append(",");
                            }
                    }
                    raspuns.append("|").append(yourBoard.turn);

                }
                else if(cerere.contains("nimic"))
                {
                    if (!yourBoard.isEnemyAlive)
                    {
                        raspuns = new StringBuilder("reset");
                        yourBoard.isTaken = false;
                        enemyBoard.isTaken = false;
                        findBoardPair(yourBoard);
                    }
                    else if(yourBoard.turn == -1 || enemyBoard.turn == -1)
                    {
                        raspuns = new StringBuilder("Ai castigat! Timpul inamicului a expirat!");
                    }
                    else {
                        raspuns = new StringBuilder();
                        for (int i = 1; i <= 10; ++i) {
                            for (int j = 1; j <= 10; ++j)
                                if (yourBoard.board[i][j] > 0) {
                                    raspuns.append(yourBoard.board[i][j]).append(" ").append(i).append(" ").append(j).append(",");
                                }
                        }
                        raspuns.append("|");
                        for (int i = 1; i <= 10; ++i) {
                            for (int j = 1; j <= 10; ++j)
                                if (enemyBoard.board[i][j] > 0 ) {
                                    raspuns.append(enemyBoard.board[i][j]).append(" ").append(i).append(" ").append(j).append(",");
                                }
                        }
                        raspuns.append("|").append(yourBoard.turn);
                    }
                }
                else if(cerere.contains("atac"))
                {
                    cerere = cerere.substring(5);
                    if(cerere.equals("exit"))
                    {
                        raspuns = new StringBuilder("exit");
                        yourBoard.isEnemyAlive = false;
                        enemyBoard.isEnemyAlive = false;
                        yourBoard.isTaken = false;
                        enemyBoard.isTaken = false;
                        findBoardPair(yourBoard);
                        out.println(raspuns);
                        out.flush();
                        try
                        {
                            socket . close () ;
                        } catch ( IOException e )
                        {
                            System . err . println ( " Socketul nu poate fi inchis \n " +  e ) ;
                        }
                        return;
                    }
                    System.out.println("Atac pe pozitia: " + cerere);
                    int i = 0, j;
                    while(cerere.charAt(0) != '\0' && cerere.charAt(0) >= '0' && cerere.charAt(0) <= '9')
                    {
                        i = i * 10 + cerere.charAt(0) - '0';
                        cerere = cerere.substring(1);
                    }
                    j = cerere.charAt(0) - 'A' + 1;
                    System.out.println("Atac pe pozitia: " + i + " " + j);

                    if(enemyBoard.board[i][j] == 1)
                    {
                        enemyBoard.board[i][j] = 2;
                        yourBoard.turn = 1;
                        if (isItOver())
                        {
                            raspuns = new StringBuilder("Ai castigat! Felicitari!");
                            yourBoard.isTaken = false;
                            enemyBoard.turn = -1;
                            enemyBoard.isTaken = false;
                            enemyBoard.isEnemyAlive = false;
                            yourBoard.getRandomBoard();
                            enemyBoard.getRandomBoard();
                            out.println(raspuns);
                            out.flush();
                            try
                            {
                                socket . close () ;
                            } catch ( IOException e )
                            {
                                System . err . println ( " Socketul nu poate fi inchis \n " +  e ) ;
                            }
                        }
                        //fillTheBoarders(enemyBoard, i, j);

                    }
                    else
                    {
                        enemyBoard.board[i][j] = 3;
                        yourBoard.turn = 0;
                        enemyBoard.turn = 1;
                    }

                    raspuns = new StringBuilder();
                    for(i = 1; i <= 10; ++ i) {
                        for (j = 1; j <= 10; ++j)
                            if (yourBoard.board[i][j] > 0) {
                                raspuns.append(yourBoard.board[i][j]).append(" ").append(i).append(" ").append(j).append(",");
                            }
                    }
                    raspuns.append("|");
                    for(i = 1; i <= 10; ++ i)
                    {
                        for (j = 1; j <= 10; ++j)
                            if (enemyBoard.board[i][j] > 0) {
                                raspuns.append(enemyBoard.board[i][j]).append(" ").append(i).append(" ").append(j).append(",");
                            }
                    }
                    raspuns.append("|").append(yourBoard.turn);

                }
                else if(cerere.equals("timpExpirat"))
                {
                    raspuns = new StringBuilder("Timpul a expirat ... Ai pierdut ...");
                    yourBoard.isTaken = false;
                    enemyBoard.turn = -1;
                    enemyBoard.isTaken = false;
                    yourBoard.getRandomBoard();
                    enemyBoard.getRandomBoard();
                    //findBoardPair(yourBoard);
                }
                out.println(raspuns);
                out.flush();
            }
        } catch ( IOException e )
        {
            yourBoard.isTaken = false;
            enemyBoard.isTaken = false;
            yourBoard.isEnemyAlive = false;
            enemyBoard.isEnemyAlive = false;
            enemyBoard.turn = -1;
           // findBoardPair(yourBoard);
            System.out.println("Aqui!!!!!!!!!");
            System . err . println ( " Eroare IO \n " + e ) ;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally
        {
            try
            {
                socket . close () ;
            } catch ( IOException e )
            {
                System . err . println ( " Socketul nu poate fi inchis \n " + e ) ;
            }
        }
    }

    public void fillTheBoarders(Board board, int i, int j)
    {
        if(i > 1)
        {
            if( board.board[i - 1][j] == 0)
                board.board[i - 1][j] = 3;
            if( board.board[i - 1][j] == 1)
                board.board[i - 1][j] = 3;
        }
        if(i < 10 && board.board[i + 1][j] == 0)
        {
            board.board[i + 1][j] = 3;
        }
        if(j > 1 && board.board[i][j - 1] == 0)
        {
            board.board[i][j - 1] = 3;
        }
        if(j < 10 && board.board[i][j + 1] == 0)
        {
            board.board[i][j + 1] = 3;
        }
    }

    public boolean isItOver()
    {
        for(int i = 1; i <= 10; ++ i)
        {
            for(int j = 1; j <= 10; ++ j)
            {
                if(enemyBoard.board[i][j] == 1)
                {
                    return false;
                }
            }
        }
        return true;
    }

}