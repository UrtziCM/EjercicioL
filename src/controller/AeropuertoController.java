package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Aeropuerto;

public class AeropuertoController {

    @FXML
    private TableColumn<Aeropuerto, Integer> anoCol;

    @FXML
    private TableColumn<Aeropuerto, String> calleCol;

    @FXML
    private TableColumn<Aeropuerto, Integer> capCol;

    @FXML
    private TableColumn<Aeropuerto, String> ciudadCol;

    @FXML
    private TableColumn<Aeropuerto, Float> finanSociosCol;

    @FXML
    private TableColumn<Aeropuerto, Integer> idCol;

    @FXML
    private TableColumn<Aeropuerto, Integer> nTrabajadoresCol;

    @FXML
    private TableColumn<Aeropuerto, String> nomCol;

    @FXML
    private TableColumn<Aeropuerto, Integer> numCol;

    @FXML
    private TableColumn<Aeropuerto, String> paisCol;
    
    @FXML
    private TableView<Aeropuerto> tablaAeropuertos;

    @FXML
    private ToggleGroup tipoToggleGroup;
    private boolean leyendoPublicos;
    private DBManagerAeropuertos gestordb;
    
    public void initialize() {
    	leyendoPublicos = true;
		anoCol.setCellValueFactory(new PropertyValueFactory<>("anio"));
		calleCol.setCellValueFactory(new PropertyValueFactory<>("calle"));
		capCol.setCellValueFactory(new PropertyValueFactory<>("capacidad"));
		ciudadCol.setCellValueFactory(new PropertyValueFactory<>("ciudad"));
		finanSociosCol.setCellValueFactory(new PropertyValueFactory<>("financiacion"));
		idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
		nTrabajadoresCol.setCellValueFactory(new PropertyValueFactory<>("trabajadores"));
		nomCol.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		paisCol.setCellValueFactory(new PropertyValueFactory<>("pais"));
	    numCol.setCellValueFactory(new PropertyValueFactory<>("numero"));;

		
		gestordb = new DBManagerAeropuertos();
		tablaAeropuertos.setItems(gestordb.cargarAeropuertosPublicos());
		
    }
    

    @FXML
    void anadirAeropuertoPublico(ActionEvent event) {
    	Stage ventana = createGridWindowFromNodes("Aviones - Añadir aeropuerto".toUpperCase(),new Label("Nombre"),new TextField(),
    			new Label("Pais"),new TextField(),
    			new Label("Ciudad"),new TextField(),
    			new Label("Calle"),new TextField(),
    			new Label("Numero"),new TextField(),
    			new Label("Año de inauguracion"),new TextField(),
    			new Label("Capacidad"),new TextField(),
    			new Label("Financiacion"),new TextField(),
    			new Label("Numero de trabajadores"),new TextField(),
    			new Button("Guardar"), new Button("Cancelar"));
    	ventana.show();
    }
    @FXML
    void anadirAeropuertoPrivado(ActionEvent event) {
    	
    }


    @FXML
    void borrarAeropuerto(ActionEvent event) {

    }

    @FXML
    void borrarAvion(ActionEvent event) {

    }

    @FXML
    void editarAeropuertoPublico(ActionEvent event) {

    }
    @FXML
    void editarAeropuertoPrivado(ActionEvent event) {

    }

    @FXML
    void infoAeropuerto(ActionEvent event) {

    }
    
    @FXML
    void activarAvion(ActionEvent event) {
    	
    }
    @FXML
    void anadirAvion(ActionEvent event) {
    	
    }

    @FXML
    void updateTable(ActionEvent event) {
    	finanSociosCol.setVisible(!leyendoPublicos);
    	if (leyendoPublicos) {
    		nTrabajadoresCol.setCellValueFactory(new PropertyValueFactory<Aeropuerto, Integer>("socios"));
    		nTrabajadoresCol.setText("Socios");
    		tablaAeropuertos.setItems(gestordb.cargarAeropuertosPrivados());
    		
    	} else {
    		nTrabajadoresCol.setCellValueFactory(new PropertyValueFactory<Aeropuerto, Integer>("trabajadores"));
    		nTrabajadoresCol.setText("Trabajadores");
    		tablaAeropuertos.setItems(gestordb.cargarAeropuertosPublicos());
    	}
    	leyendoPublicos = !leyendoPublicos;
    }
    private Stage createGridWindowFromNodes(String title,Node... nodes) {
    	GridPane customGridPane = new GridPane();
    	int i=0,j = 0;
    	for (Node n : nodes) {
    		customGridPane.add(n, i, j);
    		i++;
    		if (i == 2) {
    			i = 0;
    			j++;
    		}
    	}

    	Scene scn = new Scene(customGridPane);
    	Stage customStage = new Stage();
    	customStage.setScene(scn);
    	customStage.setTitle(title);
    	customStage.setResizable(false);
    	return customStage;
    }

}
