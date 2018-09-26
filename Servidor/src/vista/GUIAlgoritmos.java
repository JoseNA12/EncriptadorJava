package vista;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class GUIAlgoritmos {

    @FXML Button btn_atras, btn_eliminar, btn_agregar;

    @FXML
    public void irPantallaInicial() throws IOException {
        FXRouter.goTo("GUI");
    }
}
