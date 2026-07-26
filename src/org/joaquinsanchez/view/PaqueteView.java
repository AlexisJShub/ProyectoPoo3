package org.joaquinsanchez.view;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import org.joaquinsanchez.model.Paquete;

public class PaqueteView {

    private TabPane panelPestana = new TabPane();

    //nodos del formulario
    private TextField txtId = new TextField();
    private ComboBox<String> cbTipoPaquete = new ComboBox<>();
    private VBox panelCambiante = new VBox(12);

    //elementos y atributos de cada paquete
    //Caja Estandar
    private TextField txtAncho = new TextField();
    private TextField txtLargo = new TextField();
    private TextField txtAlto = new TextField();
    //Sobre
    private TextField txtPeso = new TextField();
    //Tubo de Envio
    private TextField txtLongitud = new TextField();
    private TextField txtDiametro = new TextField();

    //nodos interactivos
    private Button btnGuardar = new Button("Registrar Paquete");
    private Label lblEstado = new Label();
    private TableView<Paquete> tablaResumen = new TableView<>();

    public PaqueteView() {
        iniciarVista();
    }

    private void iniciarVista() {
        panelPestana.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        panelPestana.getStyleClass().add("tab-pane-principal");

        //Formulario 
        Tab tabRegistro = new Tab("Registro");
        tabRegistro.setClosable(false);

        StackPane stackRegistro = new StackPane();
        stackRegistro.setPadding(new Insets(20));

        VBox tarjetaRegistro = new VBox(12);
        tarjetaRegistro.getStyleClass().add("panel-formulario");

        txtId.setPromptText("Ej: PAQ-001");

        cbTipoPaquete.getItems().addAll("Caja Estándar", "Sobre", "Tubo de Envío");
        cbTipoPaquete.setPromptText("Seleccione un tipo de paquete");
        cbTipoPaquete.setMaxWidth(Double.MAX_VALUE);

        btnGuardar.setMaxWidth(Double.MAX_VALUE);
        btnGuardar.getStyleClass().add("boton-primario");

        lblEstado.getStyleClass().add("etiqueta-estado");
        lblEstado.setWrapText(true);

        tarjetaRegistro.getChildren().addAll(
                new Label("ID del Paquete:"), txtId,
                new Label("Tipo de Paquete:"), cbTipoPaquete,
                panelCambiante, btnGuardar, lblEstado);

        stackRegistro.getChildren().add(tarjetaRegistro);
        tabRegistro.setContent(stackRegistro);

        //pestaña lista
        Tab tabResumen = new Tab("Lista Resumen");
        tabResumen.setClosable(false);

        StackPane stackResumen = new StackPane();
        stackResumen.setPadding(new Insets(20));

        VBox cardResumen = new VBox(15);
        cardResumen.getStyleClass().add("panel-formulario");

        construirColumnasTabla();
        tablaResumen.setPlaceholder(new Label("Aún no hay paquetes registrados."));
        tablaResumen.getStyleClass().add("tabla-resumen");

        cardResumen.getChildren().addAll(new Label("Paquetes Registrados"), tablaResumen);
        stackResumen.getChildren().add(cardResumen);
        tabResumen.setContent(stackResumen);

        panelPestana.getTabs().addAll(tabRegistro, tabResumen);
    }

}