package controlador;

import java.util.List;

public class IOServidorDTO {
    private String identificadorAlfabeto;
    private String simbolosAlfabeto;

    //TODO: agregar atributos conforme se necesiten


    public IOServidorDTO(String identificadorAlfabeto, String simbolosAlfabeto) {
        this.identificadorAlfabeto = identificadorAlfabeto;
        this.simbolosAlfabeto = simbolosAlfabeto;
    }

    public String getIdentificadorAlfabeto() {
        return identificadorAlfabeto;
    }

    public void setIdentificadorAlfabeto(String identificadorAlfabeto) {
        this.identificadorAlfabeto = identificadorAlfabeto;
    }

    public String getSimbolosAlfabeto() {
        return simbolosAlfabeto;
    }

    public void setSimbolosAlfabeto(String simbolosAlfabeto) {
        this.simbolosAlfabeto = simbolosAlfabeto;
    }
}
