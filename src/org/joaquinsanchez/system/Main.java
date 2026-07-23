package org.joaquinsanchez.system;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.joaquinsanchez.model.Paquete;
import org.joaquinsanchez.view.PaqueteView;

public class Main extends Application {




    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage escenarioPrincipal) throws Exception {
        PaqueteView vista = new PaqueteView(); 
        TabPane raiz = vista.getPanelPestana();
        Scene escena = new Scene(raiz, 450, 600);
        escenarioPrincipal.setScene(escena);
        escenarioPrincipal.show(); 
        
       
    }

}