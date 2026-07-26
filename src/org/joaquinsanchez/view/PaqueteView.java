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

    //los nodos
    private TextField txtId = new TextField();
    private ComboBox<String> cbTipoPaquete = new ComboBox<>();
    private VBox panelCambiante = new VBox(12);

    //elementos y atributos de cada paquete
    private TextField txtAncho = new TextField();
    private TextField txtLargo = new TextField();
    private TextField txtAlto = new TextField();
    //Sobre
    private TextField txtPeso = new TextField();
    //Tubo de Envio
    private TextField txtLongitud = new TextField();
    private TextField txtDiametro = new TextField();

    //nodos interactivos: boton, mensaje de estado y tabla resumen
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

        //Pestaña de Lista 
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

    private void construirColumnasTabla() {
        TableColumn<Paquete, String> colId = new TableColumn<>("ID");
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colId.setPrefWidth(90);

        TableColumn<Paquete, String> colTipo = new TableColumn<>("Tipo");
        colTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        colTipo.setPrefWidth(120);

        TableColumn<Paquete, String> colDetalle = new TableColumn<>("Detalle");
        colDetalle.setCellValueFactory(new PropertyValueFactory<>("detalle"));
        colDetalle.setPrefWidth(170);

        TableColumn<Paquete, Number> colTotal = new TableColumn<>("Total");
        colTotal.setCellValueFactory(datos ->
                new javafx.beans.property.SimpleDoubleProperty(datos.getValue().calculoTotal()));
        colTotal.setPrefWidth(100);

        tablaResumen.getColumns().addAll(colId, colTipo, colDetalle, colTotal);
    }

    //los paneles cambiantes

    public void mostrarCaja() {
        panelCambiante.getChildren().clear();
        txtAncho.setPromptText("Ej: 30.0");
        txtLargo.setPromptText("Ej: 40.0");
        txtAlto.setPromptText("Ej: 20.0");
        panelCambiante.getChildren().addAll(
                new Label("Ancho (cm):"), txtAncho,
                new Label("Largo (cm):"), txtLargo,
                new Label("Alto (cm):"), txtAlto);
    }

    public void mostrarSobre() {
        panelCambiante.getChildren().clear();
        txtPeso.setPromptText("Ej: 0.5");
        panelCambiante.getChildren().addAll(new Label("Peso (kg):"), txtPeso);
    }

    public void mostrarTubo() {
        panelCambiante.getChildren().clear();
        txtLongitud.setPromptText("Ej: 60.0");
        txtDiametro.setPromptText("Ej: 10.0");
        panelCambiante.getChildren().addAll(
                new Label("Longitud (cm):"), txtLongitud,
                new Label("Diámetro (cm):"), txtDiametro);
    }

    public void limpiarPanelCambiante() {
        panelCambiante.getChildren().clear();
    }

    public void limpiarCampos() {
        txtId.clear();
        txtAncho.clear();
        txtLargo.clear();
        txtAlto.clear();
        txtPeso.clear();
        txtLongitud.clear();
        txtDiametro.clear();
    }

    public void mostrarMensajeExito(String mensaje) {
        lblEstado.setText(mensaje);
        lblEstado.getStyleClass().removeAll("etiqueta-error");
        if (!lblEstado.getStyleClass().contains("etiqueta-exito")) {
            lblEstado.getStyleClass().add("etiqueta-exito");
        }
    }

    public void mostrarMensajeError(String mensaje) {
        lblEstado.setText(mensaje);
        lblEstado.getStyleClass().removeAll("etiqueta-exito");
        if (!lblEstado.getStyleClass().contains("etiqueta-error")) {
            lblEstado.getStyleClass().add("etiqueta-error");
        }
    }

    //getters y setters 
    public TabPane getPanelPestana() {
        return panelPestana;
    }

    public void setPanelPestana(TabPane panelPestana) {
        this.panelPestana = panelPestana;
    }

    public TextField getTxtId() {
        return txtId;
    }

    public void setTxtId(TextField txtId) {
        this.txtId = txtId;
    }

    public ComboBox<String> getCbTipoPaquete() {
        return cbTipoPaquete;
    }

    public void setCbTipoPaquete(ComboBox<String> cbTipoPaquete) {
        this.cbTipoPaquete = cbTipoPaquete;
    }

    public VBox getPanelCambiante() {
        return panelCambiante;
    }

    public void setPanelCambiante(VBox panelCambiante) {
        this.panelCambiante = panelCambiante;
    }

    public TextField getTxtAncho() {
        return txtAncho;
    }

    public void setTxtAncho(TextField txtAncho) {
        this.txtAncho = txtAncho;
    }

    public TextField getTxtLargo() {
        return txtLargo;
    }

    public void setTxtLargo(TextField txtLargo) {
        this.txtLargo = txtLargo;
    }

    public TextField getTxtAlto() {
        return txtAlto;
    }

    public void setTxtAlto(TextField txtAlto) {
        this.txtAlto = txtAlto;
    }

    public TextField getTxtPeso() {
        return txtPeso;
    }

    public void setTxtPeso(TextField txtPeso) {
        this.txtPeso = txtPeso;
    }

    public TextField getTxtLongitud() {
        return txtLongitud;
    }

    public void setTxtLongitud(TextField txtLongitud) {
        this.txtLongitud = txtLongitud;
    }

    public TextField getTxtDiametro() {
        return txtDiametro;
    }

    public void setTxtDiametro(TextField txtDiametro) {
        this.txtDiametro = txtDiametro;
    }

    public Button getBtnGuardar() {
        return btnGuardar;
    }

    public void setBtnGuardar(Button btnGuardar) {
        this.btnGuardar = btnGuardar;
    }

    public Label getLblEstado() {
        return lblEstado;
    }

    public void setLblEstado(Label lblEstado) {
        this.lblEstado = lblEstado;
    }

    public TableView<Paquete> getTablaResumen() {
        return tablaResumen;
    }

    public void setTablaResumen(TableView<Paquete> tablaResumen) {
        this.tablaResumen = tablaResumen;
    }

}
