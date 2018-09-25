package vista;

import Controlador.Controlador;
import Controlador.AlgoritmosDTO;
import Modelo.Alfabeto;
import Modelo.TipoAlgoritmo;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.ComboBoxListCell;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GUI extends Application {

    @FXML private ListView lv_algoritmos, lv_algoritmos_deseados;
    @FXML private ComboBox <Alfabeto> cb_alfabetos;
    @FXML private TextArea ta_textoEntrada, ta_textoProcesado;
    @FXML private CheckBox cb_codificar;
    @FXML private Button bt_procesar, btn_agregar_alfabeto;

    private Controlador miControlador = new Controlador();

    public static final ObservableList ol_algoritmos =
            FXCollections.observableArrayList();

    public static final ObservableList ol_algoritmos_deseados =
            FXCollections.observableArrayList();

    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("GUI.fxml"));
        primaryStage.setTitle("Procesamiento textual");

        primaryStage.setScene(new Scene(root, 680, 427));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    @FXML
    void initialize(){
        initComponenteAlfabeto();
        initComponenteAlgorimos();
    }

    private void initComponenteAlfabeto() // ComboBox
    {
        cb_alfabetos.getItems().addAll(miControlador.CargarAlfabetos());
        Callback<ListView<Alfabeto>, ListCell<Alfabeto>> factory = lv -> new ListCell<Alfabeto>() {

            @Override
            protected void updateItem(Alfabeto item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? "" : item.getSimbolos());
            }
        };

        cb_alfabetos.setCellFactory(factory);
        cb_alfabetos.setButtonCell(factory.call(null));
    }

    private void initComponenteAlgorimos() // ListView
    {
        for(TipoAlgoritmo m : TipoAlgoritmo.values()) {
            ol_algoritmos.add(m.getNombre());
        }

        lv_algoritmos.setItems(ol_algoritmos);
    }

    public void bt_action_procesar()
    {
        if (!ol_algoritmos_deseados.isEmpty())
        {
            if (!ta_textoEntrada.getText().equals(""))
            {
                if (!cb_alfabetos.getSelectionModel().isEmpty())
                {
                    AlgoritmosDTO dto = new AlgoritmosDTO(
                            ta_textoEntrada.getText(),
                            null,
                            cb_alfabetos.getSelectionModel().getSelectedItem(),
                            ObtenerAlgorimosMarcados(),
                            cb_codificar.isSelected());
                    miControlador.ProcesarTexto(dto); //Sets resultado
                    miControlador.EscribirArch(dto);
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

    public void bt_action_agregarNuevoAlfabeto()
    {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Agregar nuevo alfabeto");
        dialog.setHeaderText("Ingrese los simbolos del alfabeto deseado");
        dialog.setContentText("Símbolos:");

        Optional<String> result = dialog.showAndWait();

        if (result.isPresent())
        {
            if (miControlador.AgregarAlfabeto(new AlgoritmosDTO(new Alfabeto(0, result.get()))))
            {
                // cargar los alfabetos con el nuevo registrado

                MostrarMensajeAlerta("Se ha registrado el alfabeto");
            }
        }
    }

    private List<TipoAlgoritmo> ObtenerAlgorimosMarcados()
    {
        List<TipoAlgoritmo> miLista = new ArrayList<TipoAlgoritmo>();

        for (int i = 0; i < lv_algoritmos_deseados.getItems().size(); i++)
        {
            for(TipoAlgoritmo m : TipoAlgoritmo.values())
            {
                if (m.getNombre().equals(lv_algoritmos_deseados.getItems().get(i)))
                {
                    miLista.add(m);
                }
            }
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
