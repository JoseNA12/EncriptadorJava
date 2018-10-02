package vista;

import accionesCliente.TipoAcciones;
import accionesCliente.TiposGenerarFrase;
import controlador.Controlador;
import datosDTO.AlgoritmosDTO;
import datosDTO.CargarDatosDTO;
import datosDTO.DatosDTO;
import datosDTO.GenerarFraseDTO;
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
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.util.ArrayList;
import java.util.List;

public class GUI extends Application {

    @FXML private ListView lv_algoritmos, lv_algoritmos_deseados;
    @FXML private ComboBox<String> cb_alfabetos, cb_formatosEscritura;
    @FXML private TextArea ta_textoEntrada, ta_textoProcesado;
    @FXML private CheckBox cb_codificar, cb_generarTexto, cb_noConsecuNoDuplica, cb_consecuNoDuplica, cb_consecuDuplica;
    @FXML private Button bt_procesar;
    @FXML private Pane pane_generarTexto;
    @FXML private TextField tf_longitudTexto;

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

        try{
            initComponenteAlfabeto(miDTO.getNombresAlfabetos());
            initComponenteAlgoritmos(miDTO.getNombresAlgoritmos());
            initComponenteEscritores(miDTO.getFormatosEscritura());
            cb_action_checkBoxsGenerarTexto();
        }
        catch(NullPointerException e){
            MostrarMensajeAlerta("Al parecer el servidor no se encuentra en linea. " +
                    "Por favor, intente más tarde!.");
        }

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

    public void cb_action_checkBoxsGenerarTexto(){
        cb_generarTexto.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                pane_generarTexto.setVisible(newValue);
                ta_textoEntrada.setVisible(!newValue);
            }
        });
    }

    public void cb_action_noConsecuNoDuplica(){
        cb_consecuNoDuplica.setSelected(false);
        cb_consecuDuplica.setSelected(false);
    }

    public void cb_action_consecuNoDuplica(){
        cb_noConsecuNoDuplica.setSelected(false);
        cb_consecuDuplica.setSelected(false);
    }

    public void cb_action_consecuDuplica(){
        cb_noConsecuNoDuplica.setSelected(false);
        cb_consecuNoDuplica.setSelected(false);
    }

    public void bt_action_procesar()
    {
        if (!ol_algoritmos_deseados.isEmpty())
        {
            if (!cb_alfabetos.getSelectionModel().isEmpty())
            {
                if (!cb_formatosEscritura.getSelectionModel().isEmpty())
                {
                    if (cb_generarTexto.isSelected())
                    {
                        TiposGenerarFrase tiposGenerarFrase = null;

                        if (cb_noConsecuNoDuplica.isSelected())
                        {
                            tiposGenerarFrase = TiposGenerarFrase.NO_CONSECUTIVOS_Y_NO_DUPLICADOS;
                        }
                        else if (cb_consecuNoDuplica.isSelected())
                        {
                            tiposGenerarFrase = TiposGenerarFrase.CONSECUTIVOS_Y_NO_DUPLICADOS;
                        }
                        else if (cb_consecuDuplica.isSelected())
                        {
                            tiposGenerarFrase = TiposGenerarFrase.CONSECUTIVOS_Y_DUPLICADOS;
                        }
                        else
                        {
                            MostrarMensajeAlerta( "Debe seleccionar un tipo de generación de texto!");
                            tiposGenerarFrase = null;
                        }

                        if (tiposGenerarFrase != null)
                        {
                            if (!tf_longitudTexto.getText().trim().equals(""))
                            {
                                try
                                {
                                    int longitudFrase = Integer.valueOf(tf_longitudTexto.getText());

                                    AlgoritmosDTO respuesta = miControlador.ProcesarTexto(new GenerarFraseDTO(
                                            longitudFrase,
                                            tiposGenerarFrase,
                                            ObtenerAlgorimosMarcados(),
                                            TipoAcciones.PROCESAR_TEXTO_GENERAR_FRASE,
                                            ta_textoEntrada.getText(),
                                            "",
                                            cb_alfabetos.getSelectionModel().getSelectedItem(), // nombre
                                            cb_codificar.isSelected(),
                                            cb_formatosEscritura.getSelectionModel().getSelectedItem())); // true -> Codificar

                                    ta_textoProcesado.setText(respuesta.getMiResultado());
                                }
                                catch(NumberFormatException e)
                                {
                                    MostrarMensajeAlerta( "Debe ingresar valores enteros para la longitud de la frase!");
                                }
                            }
                            else
                            {
                                MostrarMensajeAlerta( "Debe ingresar un largo para la frase!");
                            }
                        }
                        else {/* pos naahh */ }
                    }
                    else{
                        if (!ta_textoEntrada.getText().trim().equals(""))
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
                        }
                        else
                        {
                            MostrarMensajeAlerta( "Debe insertar una frase para procesar!");
                        }
                    }
                }
                else
                {
                    MostrarMensajeAlerta( "Debe seleccionar un tipo de formato de escritura!");
                }
            }
            else
            {
                MostrarMensajeAlerta( "Debe seleccionar un alfabeto!");
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
        alert.setHeaderText("Atención!");
        alert.setContentText(pContenido);
        alert.showAndWait().ifPresent(rs -> {
            if (rs == ButtonType.OK) {
                // System.out.println("Aceptar");
            }
        });
    }


}
