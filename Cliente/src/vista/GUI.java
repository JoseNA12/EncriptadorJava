package vista;

import accionesCliente.TipoAcciones;
import controlador.Controlador;
import datosDTO.AlgoritmosDTO;
import datosDTO.CargarDatosDTO;
import datosDTO.DatosDTO;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.util.ArrayList;
import java.util.List;

public class GUI extends Application {

    @FXML private ListView lv_algoritmos, lv_algoritmos_deseados;
    @FXML private ComboBox<String> cb_alfabetos, cb_formatosEscritura;
    @FXML private TextArea ta_textoEntrada, ta_textoProcesado;
    @FXML private CheckBox cb_codificar, cb_generarTexto;
    @FXML private Button bt_procesar;

    private Controlador miControlador;

    public static final ObservableList ol_algoritmos =
            FXCollections.observableArrayList();

    public static final ObservableList ol_algoritmos_deseados =
            FXCollections.observableArrayList();

    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("GUI.fxml"));
        primaryStage.setTitle("Procesamiento textual");

        Scene miScene = new Scene(root);
        miScene.getStylesheets().add("/vista/CSS/Estilo.css");

        primaryStage.setScene(miScene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    @FXML
    void initialize(){
        miControlador = new Controlador();
        CargarDatosDTO miDTO = miControlador.SolicitarDatosVisuales(new DatosDTO(null, TipoAcciones.CARGAR_ALGORIT_ALFAB));
        initComponenteAlfabeto(miDTO.getNombresAlfabetos());
        initComponenteAlgoritmos(miDTO.getNombresAlgoritmos());
        initComponenteEscritores(miDTO.getFormatosEscritura());
    }

    private void initComponenteAlfabeto(List<String> pAlfabetos) // ComboBox
    {
        cb_alfabetos.getItems().addAll(pAlfabetos);
        Callback<ListView<String>, ListCell<String>> factory = lv -> new ListCell<String>() {

            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? "" : item);
            }
        };

        cb_alfabetos.setCellFactory(factory);
        cb_alfabetos.setButtonCell(factory.call(null));
    }

    private void initComponenteAlgoritmos(List<String> pAlgoritmos) // ListView
    {
        for(int i = 0; i < pAlgoritmos.size(); i++) {
            ol_algoritmos.add(pAlgoritmos.get(i));
        }

        lv_algoritmos.setItems(ol_algoritmos);
    }

    private void initComponenteEscritores(List<String> pEscritores){
        cb_formatosEscritura.getItems().addAll(pEscritores);
        Callback<ListView<String>, ListCell<String>> factory = lv -> new ListCell<String>() {

            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? "" : item);
            }
        };

        cb_formatosEscritura.setCellFactory(factory);
        cb_formatosEscritura.setButtonCell(factory.call(null));
    }

    public void cb_action_generarTexto(){
        ta_textoEntrada.setVisible(!cb_generarTexto.isSelected());
    }

    public void bt_action_procesar()
    {
        if (!ol_algoritmos_deseados.isEmpty())
        {
            if (!ta_textoEntrada.getText().equals(""))
            {
                if (!cb_alfabetos.getSelectionModel().isEmpty())
                {
                    AlgoritmosDTO respuesta = miControlador.ProcesarTexto(new AlgoritmosDTO(
                            ObtenerAlgorimosMarcados(),
                            TipoAcciones.PROCESAR_TEXTO,
                            ta_textoEntrada.getText(),
                            "",
                            cb_alfabetos.getSelectionModel().getSelectedItem(), // nombre
                            cb_codificar.isSelected(),
                            cb_formatosEscritura.getSelectionModel().getSelectedItem())); // true -> Codificar

                    ta_textoProcesado.setText(respuesta.getMiResultado());
                    //Algun codigo para desplegarlo en pantalla
                }
                else
                {
                    MostrarMensajeAlerta( "Debe seleccionar un alfabeto!");
                }
            }
            else
            {
                MostrarMensajeAlerta( "Debe insertar una frase para procesar!");
            }
        }
        else
        {
            MostrarMensajeAlerta("Debe seleccionar al menos un metodo de procesamiento!");
        }
    }

    // agregar a la lista de metodos deseados
    public void bt_action_agregar()
    {
        final int selectedIdx = lv_algoritmos.getSelectionModel().getSelectedIndex();

        if (selectedIdx != -1)
        {
            // obtengo el elemento de la lista de los algoritmos
            Object itemToRemove = lv_algoritmos.getSelectionModel().getSelectedItem();

            // añado ese elemento a la lista de los deseados
            ol_algoritmos_deseados.add(itemToRemove);
            lv_algoritmos_deseados.setItems(ol_algoritmos_deseados);

            // elimino el elemento de la lista de los algoritmos (original digamos)
            lv_algoritmos.getItems().remove(selectedIdx);
        }
    }

    // quitar de la lista los metodos deseados
    public void bt_action_quitar()
    {
        final int selectedIdx = lv_algoritmos_deseados.getSelectionModel().getSelectedIndex();

        if (selectedIdx != -1)
        {
            // obtengo el elemento de la lista de los algoritmos
            Object itemToRemove = lv_algoritmos_deseados.getSelectionModel().getSelectedItem();

            // añado ese elemento a la lista de los deseados
            ol_algoritmos.add(itemToRemove);
            lv_algoritmos.setItems(ol_algoritmos);

            // elimino el elemento de la lista de los algoritmos (original digamos)
            lv_algoritmos_deseados.getItems().remove(selectedIdx);
        }
    }

    private List<String> ObtenerAlgorimosMarcados()
    {
        List<String> miLista = new ArrayList<String>();

        for (int i = 0; i < lv_algoritmos_deseados.getItems().size(); i++)
        {
            miLista.add(lv_algoritmos_deseados.getItems().get(i).toString());
        }
        return miLista;
    }

    private void MostrarMensajeAlerta(String pContenido)
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Atención");
        // alert.setHeaderText("Look, an Information Dialog");
        alert.setContentText(pContenido);
        alert.showAndWait().ifPresent(rs -> {
            if (rs == ButtonType.OK) {
                System.out.println("Aceptar");
            }
        });
    }


}
