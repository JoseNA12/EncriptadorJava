package modelo;

import javax.sound.sampled.Line;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Resultado {

    private List<LineaResultado> lineasResultados;
    private Date fecha;
    private String fraseOriginal;

    public Resultado(String fraseOriginal) {
        this.fraseOriginal = fraseOriginal;
        lineasResultados = new ArrayList<>();
        this.fecha = Calendar.getInstance().getTime();
    }

    public void agregarLineaResultado(LineaResultado linea){
        lineasResultados.add(linea);
    }

    public List<LineaResultado> getLineasResultados() {
        return lineasResultados;
    }

    public void setLineasResultados(List<LineaResultado> lineasResultados) {
        this.lineasResultados = lineasResultados;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getFraseOriginal() {
        return fraseOriginal;
    }

    public void setFraseOriginal(String fraseOriginal) {
        this.fraseOriginal = fraseOriginal;
    }

    @Override
    public String toString() {
        String str = "Frase original: " + this.fraseOriginal + "\n";
        str += "Fecha: " + this.fecha.toString() + "\n";
        for(LineaResultado linea : this.lineasResultados){
            str += linea.toString();
        }

        return str;
    }
}
