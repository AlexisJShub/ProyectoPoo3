package org.joaquinsanchez.system;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage escenarioPrincipal) throws Exception {
       
            Parent raiz = FXMLLoader.load(getClass().getResource("/org/joaquinsanchez/view/PaqueteView.fxml"));

            Scene escena = new Scene(raiz, 480, 640);
            escenarioPrincipal.setTitle("Sistema de Gestión de Paquetes");
            escenarioPrincipal.setScene(escena);
            escenarioPrincipal.show();
        }
    }
