package controlador;

import accionesCliente.TipoAcciones;
import datosDTO.AlgoritmosDTO;
import datosDTO.CargarDatosDTO;
import datosDTO.DatosDTO;

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

    public CargarDatosDTO solicitarDatosVisuales(DatosDTO pDTO){
        CargarDatosDTO miDTO = null;
        try{
            dos.writeObject(pDTO);
            dos.flush();
            dos.reset();

            // respuesta del servidor
            miDTO = (CargarDatosDTO) dis.readObject();
        }
        catch (Exception e) {}

        return miDTO;
    }

    public AlgoritmosDTO ProcesarTexto(AlgoritmosDTO pDTO){
        AlgoritmosDTO miDTO = null;
        try{
            dos.writeObject(pDTO);
            dos.flush();
            dos.reset();

            // respuesta del servidor
            miDTO = (AlgoritmosDTO) dis.readObject();
        }
        catch (Exception e) {}

        return miDTO;
    }
}
