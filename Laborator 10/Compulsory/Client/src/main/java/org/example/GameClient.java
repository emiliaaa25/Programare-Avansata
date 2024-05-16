package org.example;
import java . net .*;
import java . io .*;
import java.util.Objects;
import java.util.Scanner;

public class GameClient {
    public static void main ( String [] args ) throws IOException
    {
        String adresaServer = "127.0.0.1" ;
        int PORT = 8100;
        Socket socket = null ;
        PrintWriter out = null ;
        BufferedReader in = null ;
        String cerere , raspuns ;
        try {
            socket = new Socket ( adresaServer , PORT ) ;
            out = new PrintWriter ( socket . getOutputStream () , true ) ;
            in = new BufferedReader ( new InputStreamReader (
                    socket . getInputStream () ) ) ;
            Scanner scan = new Scanner(System.in);

            while(true)
            {
                System.out.println("Introduceti o cerere: ");
                //For string
                cerere = scan.nextLine();

                out.println(cerere);
                raspuns = in.readLine();
                System.out.println(raspuns);
                if(Objects.equals(cerere, "exit") || Objects.equals(cerere, "stop"))
                {
                    break;
                }
            }
        } catch ( UnknownHostException e ) {
            System . err . println ( " Serverul nu poate fi gasit \n " + e )
            ;
            System . exit (1) ;
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
