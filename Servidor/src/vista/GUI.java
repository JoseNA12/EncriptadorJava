package vista;

import controlador.ControladorAdministrador;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import servidor.Servidor;

import java.io.IOException;

public class GUI extends Application {

    @FXML Button btn_algoritmos, btn_mecanismos, btn_bitacora;

    private static Servidor mServidor;

    protected static GUI miInstancia = null;
    protected static Stage miPrimaryStage = null;
    protected ControladorAdministrador miControlador = new ControladorAdministrador();

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        FXRouter.bind(this, primaryStage, "ControladorAdministrador");
        FXRouter.when("GUI", "GUI.fxml");
        FXRouter.when("GUIAlfabetos", "GUIAlfabetos.fxml");
        FXRouter.when("GUIAlgoritmos", "GUIAlgoritmos.fxml");

        FXRouter.goTo("GUI"); // pantalla inicial

        miInstancia = GUI.this;
        miPrimaryStage = primaryStage;
    }

    public static void main(String[] args)
    {
        new Thread(()->{iniciarServidor(5056);}).start();
        launch(args);
    }

    private static void iniciarServidor(int pPuerto){
        mServidor = new Servidor(pPuerto);
        mServidor.iniciar();
    }

    @FXML
    public void irPantallaAlfabetos() throws IOException {
        FXRouter.goTo("GUIAlfabetos");
    }

    @FXML
    public void irPantallaAlgoritmos() throws IOException {
        FXRouter.goTo("GUIAlgoritmos");
    }

    @FXML
    public void irPantallaBitacoras() throws IOException {
        miControlador.AbrirDirectorioBitacoras();
    }

    public void MostrarMensajeAlerta(String pContenido)
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
