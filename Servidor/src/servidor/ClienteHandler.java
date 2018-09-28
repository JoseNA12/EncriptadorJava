package servidor;

import controlador.AlgoritmosDTO;
import controlador.CargarDatosDTO;
import controlador.Controlador;
import controlador.OpcionesCliente;

import java.io.*;
import java.net.*;
import java.util.List;

public class ClienteHandler extends Thread {

    final ObjectInputStream dis;
    final ObjectOutputStream dos;
    final Socket s;

    private Controlador miControlador;

    // Constructor
    public ClienteHandler(Socket s, ObjectInputStream dis, ObjectOutputStream dos)
    {
        this.s = s;
        this.dos = dos;
        this.dis = dis;
        this.miControlador = new Controlador();
    }

    @Override
    public void run()
    {
        Object received; //String received;
        String toreturn;
        while (true) {
            try {
                // receive the answer from client
                received = (List<Object>) dis.readObject();
                // 0 -> tipo de consulta
                // 1 -> contenido enviado por el cliente

                // write on output stream based on the answer from the client
                switch (OpcionesCliente.valueOf(((List) received).get(0).toString())) {

                    case CARGAR_ALGORIT_ALFAB:
                        dos.writeObject(new CargarDatosDTO(miControlador.CargarAlfabetos(), miControlador.CargarAlgoritmos()));
                        dos.flush();
                        break;

                    case PROCESAR_TEXTO:
                        //System.out.println(((List) received).get(1));
                        AlgoritmosDTO prueba = new AlgoritmosDTO("", "el servidor envió esto", "", null, true);
                        dos.writeObject(prueba);
                        dos.flush();
                        break;

                    case CERRAR_CONEXION:
                        break;

                    default:
                        dos.writeUTF("Invalid input");
                        dos.flush();
                        break;
                }

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            catch (SocketException e){
                // Connection reset, el cliente cierra directamente el programa
                break;
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        cerrarRecursos();
    }

    private void cerrarRecursos(){
        try
        {
            System.out.println("- Cliente desconectado: " + s.getLocalAddress() + ", " + s.getPort());
            this.s.close();
            this.dis.close();
            this.dos.close();

        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
