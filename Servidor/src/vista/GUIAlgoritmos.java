package vista;

import controlador.ControladorAdministrador;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

import java.io.*;
import java.nio.file.*;
import java.util.List;

public class GUIAlgoritmos {

    @FXML Button btn_atras, btn_eliminar, btn_agregar;

    @FXML ListView<String> lview_algoritmos;

    private FileChooser fileChooser = new FileChooser();

    private ControladorAdministrador miControlador;

    @FXML
    public void initialize(){
        this.miControlador = GUI.miInstancia.miControlador;

        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Archivos .java", "*.java")
        );

        lview_algoritmos.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                // recuperarAlfabeto();
            }
        });

        btn_agregar.setOnAction(e -> {

            File miArchivo = fileChooser.showOpenDialog(GUI.miPrimaryStage);
            File fileDestino = new File((Paths.get(System.getProperty("user.dir") + "/src/controlador/algoritmos/" + miArchivo.getName())).toString());

            try {
                Files.copy(miArchivo.toPath(), fileDestino.toPath());
                cargarListaAlfabetos();

                GUI.miInstancia.MostrarMensajeAlerta("Se ha agregado correctamente el método al servidor!\n" +
                        "Reinicie el servidor para aplicar los cambios.");

            }
            catch(IOException ee){

            }
            // Files.delete(origPath);
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
