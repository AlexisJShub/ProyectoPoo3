package org.joaquinsanchez.system;

import javafx.application.Application;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;
import org.joaquinsanchez.controller.PaqueteController;
import org.joaquinsanchez.view.PaqueteView;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage escenarioPrincipal) throws Exception {
        PaqueteView vista = new PaqueteView();
        TabPane raiz = vista.getPanelPestana();
        PaqueteController controlador = new PaqueteController(vista);

        Scene escena = new Scene(raiz, 480, 640);
        escenarioPrincipal.setTitle("Sistema de Gestión de Paquetes");
        escenarioPrincipal.setScene(escena);
        escenarioPrincipal.show();
    }

}
