package vista;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import modelo.Alfabeto;

import java.io.IOException;

public class GUIAlfabetos {

    @FXML
    Button btn_atras, btn_eliminar, btn_agregar;

    @FXML
    ListView<Alfabeto> lview_alfabetos;

    @FXML
    public void irPantallaInicial() throws IOException {
        FXRouter.goTo("GUI");
    }
}
