package org.joaquinsanchez.view;

import javafx.application.Application;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;


public class PaqueteView{

     private TabPane panelPestana = new TabPane(); 
    
    public PaqueteView(){
        iniciarVista(); 
    }
    
    private void iniciarVista(){
        //formulario Resgistro
        Tab tablaRegistro = new Tab("Registro");
        
        
        //formulario Lista Resumen
        Tab tablaResumen = new Tab("Lista Resumen");
        
        panelPestana.getTabs().addAll(tablaRegistro, tablaResumen);
    }
    
    public TabPane getPanelPestana(){
        return panelPestana; 
    }
    
    public void setPanelPestana(TabPane panelPestana){
        this.panelPestana = panelPestana; 
    }
}