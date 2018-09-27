package controlador;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
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
            List<Object> miPedido = new ArrayList<>();
            miPedido.add(OpcionesCliente.CARGAR_ALGORIT_ALFAB);

            dos.writeObject(miPedido);
            dos.flush();
            dos.reset();

            miDTO = (CargarDatosDTO) dis.readObject();

            // String received = dis.readUTF();
            // System.out.println(received);
        }
        catch (Exception e) {}

        return miDTO;
    }

    public AlgoritmosDTO ProcesarTexto(AlgoritmosDTO pDTO){
        AlgoritmosDTO miDTO = null;
        try{
            List<Object> miPedido = new ArrayList<>();
            miPedido.add(OpcionesCliente.PROCESAR_TEXTO);
            miPedido.add(pDTO);

            dos.writeObject(miPedido);
            dos.flush();
            dos.reset();

            //miDTO = (AlgoritmosDTO) dis.readObject();
        }
        catch (Exception e) {}

        return miDTO;
    }
}
