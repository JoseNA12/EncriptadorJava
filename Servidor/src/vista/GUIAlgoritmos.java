package vista;

import controlador.ControladorAdministrador;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.List;

public class GUIAlgoritmos {

    @FXML Button btn_atras, btn_eliminar, btn_agregar;

    @FXML ListView<String> lview_algoritmos;

    private ControladorAdministrador miControlador;

    @FXML
    public void initialize(){
        this.miControlador = GUI.miInstancia.miControlador;

        lview_algoritmos.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                // recuperarAlfabeto();
            }
        });
        cargarListaAlfabetos();
    }

    private void cargarListaAlfabetos(){
        lview_algoritmos.getItems().clear();
        List<String> algoritmos = this.miControlador.CargarAlgoritmos();

        if(algoritmos != null){
            lview_algoritmos.setItems(
                    FXCollections.observableArrayList(algoritmos)
            );
        }
    }

    @FXML
    public void irPantallaInicial() throws IOException {
        FXRouter.goTo("GUI");
    }
}
