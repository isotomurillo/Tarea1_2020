package Tec.tareas.tarea1.grafica;

import Tec.tareas.tarea1.server.Client;
import Tec.tareas.tarea1.server.ClientHandler;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;

public class JavaFx extends Application {
    Stage window;
    Scene scene;
    Button button;

    public static void main(String[] args){
        launch(args);

        String dis = null;
        String dos = null;
        Client c = new Client(dis, dos);

        c.run();

    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        window = primaryStage;
        window.setTitle("Mensajería");



        TextField txt = new TextField();

        button = new Button("Enviar");

        button.setOnAction(e -> {
            String dos=txt.getText();
            System.out.println(dos);
        });

        VBox layout = new VBox(10);
        layout.setPadding(new Insets( 20,20,20,20));
        layout.getChildren().addAll(txt, button);

        scene = new Scene(layout,600,500);
        window.setScene(scene);
        window.show();
    }
}
