package vista;

import controlador.ControladorAdministrador;
import controlador.IOServidorDTO;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import modelo.Alfabeto;

import java.io.IOException;
import java.util.ArrayList;

public class GUIAlfabetos {

    @FXML Button btn_atras, btn_eliminar, btn_modificar, btn_agregar;

    @FXML TextField tf_identificador, tf_simbolos;

    @FXML ListView<Alfabeto> lview_alfabetos;

    private ControladorAdministrador miControlador;

    @FXML
    public void initialize(){
        this.miControlador = GUI.miInstancia.miControlador;
        setListCellFactory();
        lview_alfabetos.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                recuperarAlfabeto();
            }
        });
        cargarListaAlfabetos();
    }

    @FXML
    public void irPantallaInicial() throws IOException {
        FXRouter.goTo("GUI");
    }

    private void cargarListaAlfabetos(){
        lview_alfabetos.getItems().clear();
        ArrayList<Alfabeto> alfabetos = this.miControlador.CargarAlfabetos().getAlfabetos();
        if(alfabetos != null){
            lview_alfabetos.setItems(
                    FXCollections.observableArrayList(alfabetos)
            );
        }
    }

    @FXML
    public void agregarAlfabeto(){
        String identificador = tf_identificador.getText();
        String simbolos = tf_simbolos.getText();

        if(!this.miControlador.AgregarAlfabeto(new IOServidorDTO(identificador, simbolos))){
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error de alfabeto");
            alerta.setHeaderText("ERROR DE ALFABETO");
            alerta.setContentText("Ha ocurrido un error al agregar el alfabeto ingresado.\n\n" +
                    "Esto puede ocurrir debido al ingreso de un identificador duplicado o un conjunto de simbolos " +
                    "invalidos (i.e. duplicados). Por favor intente de nuevo.");
            alerta.showAndWait();
        }
        cargarListaAlfabetos();
    }

    private void recuperarAlfabeto(){
        Alfabeto alfabetoActual = lview_alfabetos.getSelectionModel().getSelectedItem();
        if(alfabetoActual != null){
            tf_identificador.setText(alfabetoActual.getIdentificador());
            tf_simbolos.setText(alfabetoActual.getSimbolos());
        }
    }

    @FXML
    public void modificarAlfabeto(){
        String identificador = tf_identificador.getText();
        String simbolos = tf_simbolos.getText();

        this.miControlador.ModificarAlfabeto(new IOServidorDTO(identificador, simbolos));
        cargarListaAlfabetos();
    }

    @FXML
    public void eliminarAlfabeto(){
        if (lview_alfabetos.getSelectionModel().getSelectedItem() != null){
            String identificador = lview_alfabetos.getSelectionModel().getSelectedItem().getIdentificador();

            this.miControlador.EliminarAlfabeto(new IOServidorDTO(identificador));

            setListCellFactory();
            cargarListaAlfabetos();
        }
        else{
            GUI.miInstancia.MostrarMensajeAlerta("Por favor, seleccione un alfabeto!");
        }

    }

    private void setListCellFactory(){
        lview_alfabetos.setCellFactory(new Callback<ListView<Alfabeto>, ListCell<Alfabeto>>() {
            @Override
            public ListCell<Alfabeto> call(ListView<Alfabeto> param) {
                return new AlfabetoCell();
            }
        });
    }

    static class AlfabetoCell extends ListCell<Alfabeto>{
        @Override
        public void updateItem(Alfabeto item, boolean empty){
            super.updateItem(item, empty);
            HBox contenedor = new HBox();

            if(!empty){
                contenedor.getChildren().addAll(
                        new Label(item.getIdentificador()),
                        new Label(":    "),
                        new Label(item.getSimbolos())
                );
                setGraphic(contenedor);
            }
        }
    }
}
