package org.example;
import java . net .*;
import java . io .*;

public class GameServer
{
    public static final int PORT = 8100;

    public GameServer() throws IOException
    {
        ServerSocket serverSocket = null ;
        try
        {
            serverSocket = new ServerSocket ( PORT ) ;
            while (true)
            {
                System . out . println ( " Asteptam un client ... ") ;
                Socket socket = serverSocket.accept ();
                ClientThread t = new ClientThread ( socket) ;
                t.start () ;
            }
        } catch ( IOException e )
        {
            System . err . println ( " Eroare IO \n " + e ) ;
        } finally
        {
            assert serverSocket != null;
            serverSocket.close () ;
        }
    }
    public static void main ( String [] args ) throws IOException
    {
        GameServer server = new GameServer () ;
    }
}
