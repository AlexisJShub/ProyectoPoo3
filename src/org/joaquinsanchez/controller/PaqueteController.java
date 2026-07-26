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

    //nodos 
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

       //campos dinamicos segun el tipo de paquete seleccionado (no vienen del fxml,
    //se crean en tiempo de ejecucion y se insertan en panelCambiante)
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

    //enlazado por onAction="#guardarPaquete" en el boton del fxml
    @FXML
    private void guardarPaquete() {
        String id = txtId.getText().trim();
        String tipo = cbTipoPaquete.getValue();

        if (id.isEmpty()) {
            mostrarMensajeError("Ingresa el ID del paquete.");
            return;
        }
        if (tipo == null) {
            mostrarMensajeError("Selecciona un tipo de paquete.");
            return;
        }

        Paquete nuevoPaquete = null;

        try {
            switch (tipo) {
                case "Caja Estándar":
                    float ancho = Float.parseFloat(txtAncho.getText().trim());
                    float largo = Float.parseFloat(txtLargo.getText().trim());
                    float alto = Float.parseFloat(txtAlto.getText().trim());
                    if (ancho <= 0 || largo <= 0 || alto <= 0) {
                        throw new IllegalArgumentException("Las medidas de la caja deben ser mayores a 0.");
                    }
                    nuevoPaquete = new CajaEstandar(id, ancho, largo, alto);
                    break;

                case "Sobre":
                    float peso = Float.parseFloat(txtPeso.getText().trim());
                    if (peso <= 0) {
                        throw new IllegalArgumentException("El peso del sobre debe ser mayor a 0.");
                    }
                    nuevoPaquete = new Sobre(id, peso);
                    break;

                case "Tubo de Envío":
                    float longitud = Float.parseFloat(txtLongitud.getText().trim());
                    float diametro = Float.parseFloat(txtDiametro.getText().trim());
                    if (longitud <= 0 || diametro <= 0) {
                        throw new IllegalArgumentException("Las medidas del tubo deben ser mayores a 0.");
                    }
                    nuevoPaquete = new TuboEnvio(id, longitud, diametro);
                    break;
            }
        } catch (NumberFormatException ex) {
            mostrarMensajeError("Ingresa valores numéricos válidos en todos los campos.");
            return;
        } catch (IllegalArgumentException ex) {
            mostrarMensajeError(ex.getMessage());
            return;
        }

        listaPaquetes.add(nuevoPaquete);

        mostrarMensajeExito("Paquete " + nuevoPaquete.getId() + " registrado. Total: "
                + String.format("%.2f", nuevoPaquete.calculoTotal()));

        txtId.clear();
        panelCambiante.getChildren().clear();
        cbTipoPaquete.getSelectionModel().clearSelection();
    }

    private void mostrarMensajeExito(String mensaje) {
        lblEstado.setText(mensaje);
        lblEstado.setStyle("-fx-text-fill: #1a7f37; -fx-font-weight: bold;");
    }

    private void mostrarMensajeError(String mensaje) {
        lblEstado.setText(mensaje);
        lblEstado.setStyle("-fx-text-fill: #c0392b; -fx-font-weight: bold;");
    }
}
