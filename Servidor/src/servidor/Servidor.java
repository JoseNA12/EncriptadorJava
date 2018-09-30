package servidor;


import java.io.*;
import java.net.*;

public class Servidor {

    private ServerSocket ss;

    public Servidor(int pPuerto) {
        try {
            ss = new ServerSocket(pPuerto);
            System.out.println("Servidor listo, esperando peticiones...");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void iniciar(){
        // running infinite loop for getting
        // client request
        while (true) {
            Socket s = null;

            try {
                // socket object to receive incoming client requests
                s = ss.accept();

                System.out.println("- Un cliente se ha conectado: " + s.getLocalAddress() + ", " + s.getPort());

                // obtaining input and out streams
                ObjectOutputStream dos = new ObjectOutputStream(s.getOutputStream());
                ObjectInputStream dis = new ObjectInputStream(s.getInputStream());

                System.out.println("Creando un nuevo hilo para el cliente... ");

                // create a new thread object
                Thread t = new ClienteHandler(s, dis, dos);

                // Invoking the start() method
                t.start();
            }
            catch (Exception e){
                try {
                    assert s != null;
                    s.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                e.printStackTrace();
            }
        }
    }
}
