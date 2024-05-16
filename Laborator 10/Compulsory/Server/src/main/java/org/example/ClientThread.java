package org.example;

import java . net .*;
import java . io .*;
import java.util.Objects;

public class ClientThread extends Thread {
    Socket socket = null ;
    public ClientThread ( Socket socket)
    {
        this.socket = socket ;
    }
    public void run ()
    {
        String cerere , raspuns ;
        try
        {
            BufferedReader in = new BufferedReader ( new
                    InputStreamReader (
                    socket . getInputStream () ) ) ;
            PrintWriter out = new PrintWriter (
                    socket . getOutputStream () ) ;

            while(IsServerAlive.isAlive == 1)
            {
                cerere = in.readLine();
                System.out.println("Cerere de la client: " + cerere);

                raspuns = "Server received the request ... ";

                if (Objects.equals(cerere, "stop"))
                {
                    IsServerAlive.isAlive = 0;
                    raspuns = "Server stopped ...";
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
                if(Objects.equals(cerere, "exit")) {
                    out.println(raspuns);
                    out.flush();
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
                out.println(raspuns);
                out.flush();
            }
        } catch ( IOException e )
        {
            System . err . println ( " Eroare IO \n " + e ) ;
        } finally
        {
            try
            {
                socket . close () ;
            } catch ( IOException e )
            {
                System . err . println ( " Socketul nu poate fi inchis \n " +
                        e ) ;
            }
        }
    }

}