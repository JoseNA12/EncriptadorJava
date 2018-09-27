package vista;

import controlador.IOServidorDTO;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;

public class GUIDetalleAlfabeto {
    @FXML
    Button btn_guardar, btn_cancelar;

    @FXML
    TextField tf_identificador, tf_simbolos;

    @FXML
    public void guardar() throws IOException {
        String identificador = tf_identificador.getText();
        String simbolos = tf_simbolos.getText();

        GUI.miInstancia.miControlador.AgregarAlfabeto(new IOServidorDTO(identificador, simbolos));

        FXRouter.goTo("GUIAlfabetos");
    }

    @FXML
    public void cancelar() throws IOException {
        FXRouter.goTo("GUIAlfabetos");
    }
}
