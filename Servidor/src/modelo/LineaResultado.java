package modelo;

public class LineaResultado {
    private String nombreAlgoritmo;
    private String textoProcesado;
    private String modoCodificacion;

    public LineaResultado(String nombreAlgoritmo, String textoProcesado, boolean modoCodificacion) {
        this.nombreAlgoritmo = nombreAlgoritmo;
        this.textoProcesado = textoProcesado;
        setModoCodificacion(modoCodificacion);
    }

    public String getTipoAlgoritmo() {
        return nombreAlgoritmo;
    }

    public void setTipoAlgoritmo(String nombreAlgoritmo) {
        this.nombreAlgoritmo = nombreAlgoritmo;
    }

    public String getTextoProcesado() {
        return textoProcesado;
    }

    public void setTextoProcesado(String textoProcesado) {
        this.textoProcesado = textoProcesado;
    }

    public String getModoCodificacion() {
        return modoCodificacion;
    }

    public void setModoCodificacion(boolean modoCodificacion) {
        if (modoCodificacion) { this.modoCodificacion = "Codificación"; }
        else { this.modoCodificacion = "Descodificación"; }
    }

    public String toString(){
        String str = "";
        str += "\r\n\tTipo de algoritmo: " + this.nombreAlgoritmo + "\r\n";

        str += "\tModo: " + this.modoCodificacion + "\r\n";

        str += "\tSalida: " + this.textoProcesado + "\r\n";

        return str;
    }
}
