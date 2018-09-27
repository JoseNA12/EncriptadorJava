package controlador;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Cliente {

    Scanner scn;
    InetAddress ip;
    Socket s;
    ObjectInputStream dis;
    ObjectOutputStream dos;

    public Cliente() {
        try {
            this.scn = new Scanner(System.in);
            this.ip = InetAddress.getByName("localhost");
            this.s = new Socket(ip, 5056);
            this.dos = new ObjectOutputStream(s.getOutputStream());
            this.dis = new ObjectInputStream(s.getInputStream());
        }
        catch (Exception e) {}
    }

    public void cerrarConexion(){
        try{
            s.close();
            scn.close();
            dis.close();
            dos.close();
        }
        catch (Exception e) {}
    }

    public CargarDatosDTO solicitarDatosVisuales(){
        CargarDatosDTO miDTO = null;
        try{
            dos.writeObject(Funciones.CARGAR_ALGORIT_ALFAB); // writeUTF("Time");
            dos.flush();
            dos.reset();

            miDTO = (CargarDatosDTO) dis.readObject();

            // String received = dis.readUTF();
            // System.out.println(received);
        }
        catch (Exception e) {}

        return miDTO;
    }

    /*public static void main(String[] args) throws IOException
    {
        try
        {
            Scanner scn = new Scanner(System.in);

            // getting localhost ip
            InetAddress ip = InetAddress.getByName("localhost");

            // establish the connection with server port 5056
            Socket s = new Socket(ip, 5056);

            // obtaining input and out streams
            DataInputStream dis = new DataInputStream(s.getInputStream());
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());

            // the following loop performs the exchange of
            // information between client and client handler
            while (true)
            {
                System.out.println(dis.readUTF());
                String tosend = scn.nextLine();
                dos.writeUTF(tosend);

                // If client sends exit,close this connection
                // and then break from the while loop
                if(tosend.equals("Exit"))
                {
                    System.out.println("Closing this connection : " + s);
                    s.close();
                    System.out.println("Connection closed");
                    break;
                }

                // printing date or time as requested by client
                String received = dis.readUTF();
                System.out.println(received);
            }

            // closing resources
            scn.close();
            dis.close();
            dos.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }*/

}
