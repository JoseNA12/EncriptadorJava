package vista;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import servidor.Servidor;

import java.io.IOException;

public class GUI extends Application {

    @FXML Button btn_algoritmos, btn_mecanismos, btn_bitacora;
    private static Servidor mServidor;

    @Override
    public void start(Stage primaryStage) throws Exception{
        /*Parent root = FXMLLoader.load(getClass().getResource("GUI.fxml"));
        primaryStage.setTitle("Administrador");

        Scene miScene = new Scene(root);
        miScene.getStylesheets().add(getClass().getResource("/CSS/Estilo.css").toExternalForm());

        primaryStage.setScene(miScene);
        primaryStage.show();*/

        FXRouter.bind(this, primaryStage, "Administrador");
        FXRouter.when("GUI", "GUI.fxml");
        FXRouter.when("GUIAlfabetos", "GUIAlfabetos.fxml");
        FXRouter.when("GUIAlgoritmos", "GUIAlgoritmos.fxml");

        FXRouter.goTo("GUI"); // pantalla inicial
    }

    public static void main(String[] args) {
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
}
