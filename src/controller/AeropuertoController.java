package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
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
    void anadirAeropuerto(ActionEvent event) {
    	
    }


    @FXML
    void borrarAeropuerto(ActionEvent event) {

    }

    @FXML
    void borrarAvion(ActionEvent event) {

    }

    @FXML
    void editarAeropuerto(ActionEvent event) {

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

}
