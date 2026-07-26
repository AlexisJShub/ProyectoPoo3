package org.joaquinsanchez.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import org.joaquinsanchez.model.CajaEstandar;
import org.joaquinsanchez.model.Paquete;
import org.joaquinsanchez.model.Sobre;
import org.joaquinsanchez.model.TuboEnvio;

public class PaqueteController implements Initializable {

    //nodos del fxml
    @FXML private TextField txtId;
    @FXML private ComboBox<String> cbTipoPaquete;
    @FXML private VBox panelCambiante;
    @FXML private Button btnGuardar;
    @FXML private Label lblEstado;

    @FXML private TableView<Paquete> tablaResumen;
    @FXML private TableColumn<Paquete, String> colId;
    @FXML private TableColumn<Paquete, String> colTipo;
    @FXML private TableColumn<Paquete, String> colDetalle;
    @FXML private TableColumn<Paquete, Number> colTotal;

    //campos dinamicos segun el tipo de paquete seleccionado (no vienen del fxml,
    //se crean en tiempo de ejecucion y se insertan en panelCambiante)
    private final TextField txtAncho = new TextField();
    private final TextField txtLargo = new TextField();
    private final TextField txtAlto = new TextField();
    private final TextField txtPeso = new TextField();
    private final TextField txtLongitud = new TextField();
    private final TextField txtDiametro = new TextField();

    private final ObservableList<Paquete> listaPaquetes = FXCollections.observableArrayList();

    //se ejecuta automaticamente despues de que el FXMLLoader inyecta todos los @FXML
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cbTipoPaquete.getItems().addAll("Caja Estándar", "Sobre", "Tubo de Envío");

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        colDetalle.setCellValueFactory(new PropertyValueFactory<>("detalle"));
        colTotal.setCellValueFactory(datos -> new SimpleDoubleProperty(datos.getValue().calculoTotal()));

        //la tabla queda conectada directamente a la lista cualquier paquete que
        //se agregue aparece agregado en Lista Resumen
        tablaResumen.setItems(listaPaquetes);
        tablaResumen.setPlaceholder(new Label("Aún no hay paquetes registrados."));
    }

    @FXML
    private void manejarTipoPaquete() {
        String opcion = cbTipoPaquete.getValue();
        if (opcion == null) {
            return;
        }

        switch (opcion) {
            case "Caja Estándar":
                mostrarCaja();
                break;
            case "Sobre":
                mostrarSobre();
                break;
            case "Tubo de Envío":
                mostrarTubo();
                break;
        }
    }

    private void mostrarCaja() {
        panelCambiante.getChildren().clear();
        txtAncho.clear();
        txtAncho.setPromptText("Ej: 30.0");
        txtLargo.clear();
        txtLargo.setPromptText("Ej: 40.0");
        txtAlto.clear();
        txtAlto.setPromptText("Ej: 20.0");
        panelCambiante.getChildren().addAll(
                new Label("Ancho (cm):"), txtAncho,
                new Label("Largo (cm):"), txtLargo,
                new Label("Alto (cm):"), txtAlto);
    }

    private void mostrarSobre() {
        panelCambiante.getChildren().clear();
        txtPeso.clear();
        txtPeso.setPromptText("Ej: 0.5");
        panelCambiante.getChildren().addAll(new Label("Peso (kg):"), txtPeso);
    }

    private void mostrarTubo() {
        panelCambiante.getChildren().clear();
        txtLongitud.clear();
        txtLongitud.setPromptText("Ej: 60.0");
        txtDiametro.clear();
        txtDiametro.setPromptText("Ej: 10.0");
        panelCambiante.getChildren().addAll(
                new Label("Longitud (cm):"), txtLongitud,
                new Label("Diámetro (cm):"), txtDiametro);
    }

}