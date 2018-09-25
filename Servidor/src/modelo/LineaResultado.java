package modelo;

public class LineaResultado {
    private TipoAlgoritmo tipoAlgoritmo;
    private String textoProcesado;
    private boolean modoCodificacion;

    public LineaResultado(TipoAlgoritmo tipoAlgoritmo, String textoProcesado, boolean modoCodificacion) {
        this.tipoAlgoritmo = tipoAlgoritmo;
        this.textoProcesado = textoProcesado;
        this.modoCodificacion = modoCodificacion;
    }

    public TipoAlgoritmo getTipoAlgoritmo() {
        return tipoAlgoritmo;
    }

    public void setTipoAlgoritmo(TipoAlgoritmo tipoAlgoritmo) {
        this.tipoAlgoritmo = tipoAlgoritmo;
    }

    public String getTextoProcesado() {
        return textoProcesado;
    }

    public void setTextoProcesado(String textoProcesado) {
        this.textoProcesado = textoProcesado;
    }

    public boolean isModoCodificacion() {
        return modoCodificacion;
    }

    public void setModoCodificacion(boolean modoCodificacion) {
        this.modoCodificacion = modoCodificacion;
    }

    public String toString(){
        String str = "";
        str += "Tipo de algoritmo: " + this.tipoAlgoritmo.toString() + "\t";
        if(this.modoCodificacion){
            str += "Modo: Codificacion\t";
        } else {
            str += "Modo: Descodificacion\t";
        }
        str += "Salida: " + this.textoProcesado + "\n";

        return str;
    }
}
