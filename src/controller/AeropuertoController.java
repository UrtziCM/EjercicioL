package controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
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
import javafx.stage.WindowEvent;
import model.Aeropuerto;
import model.Direccion;

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
		numCol.setCellValueFactory(new PropertyValueFactory<>("numero"));

		gestordb = new DBManagerAeropuertos();
		tablaAeropuertos.setItems(gestordb.cargarAeropuertosPublicos());

	}

	@FXML
	void anadirAeropuertoPublico(ActionEvent event) {
		String[] datos = new String[9];
		Button botonGuardar = new Button("Guardar");
		Stage ventana = createGridWindowFromNodes("Aviones - Añadir aeropuerto".toUpperCase(), new Label("Nombre"),
				new TextField(), new Label("Año de inauguracion"), new TextField(), new Label("Capacidad"),
				new TextField(), new Label("País"), new TextField(), new Label("Ciudad"), new TextField(),
				new Label("Calle"), new TextField(), new Label("Numero"), new TextField(), new Label("Financiacion"),
				new TextField(), new Label("Numero de trabajadores"), new TextField(), botonGuardar,
				new Button("Cancelar"));
		ventana.show();
		botonGuardar.setOnAction(e -> {
			ventana.fireEvent(new WindowEvent(ventana, WindowEvent.WINDOW_CLOSE_REQUEST));
		});
		ventana.setOnCloseRequest(e -> {
			e.consume();
			int i = 0;
			for (Node n : getAllNodes(ventana.getScene().getRoot())) {
				if (n.getClass() == TextField.class) {
					datos[i] = ((TextField) n).getText();
					i++;
				}
				System.out.println(Arrays.toString(datos));
				try {
					gestordb.addAeropuerto(
							new Aeropuerto(-1, datos[0], Integer.parseInt(datos[1]), Integer.parseInt(datos[2]), -1, "none",
									new Direccion(datos[3], datos[4], datos[5], Integer.parseInt(datos[6])),
									Integer.parseInt(datos[7]), Integer.parseInt(datos[8])));
				} catch (NumberFormatException e1) {
					e1.printStackTrace();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			gestordb.cargarAeropuertosPublicos();
			ventana.close();
		});
	}

	@FXML
	void anadirAeropuertoPrivado(ActionEvent event) {

	}

	@FXML
	void borrarAeropuerto(ActionEvent event) {

	}

	@FXML
	void borrarAvion(ActionEvent event) {
		int i = 0;
		for (Node n : getAllNodes(ventana.getScene().getRoot())) {
			System.out.println(n);
			if (n.getClass() == TextField.class) {
				datos[i] = ((TextField) n).getText();
				i++;
			}
		}
		System.out.println(Arrays.toString(datos));
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

	private Stage createGridWindowFromNodes(String title, Node... nodes) {
		GridPane customGridPane = new GridPane();
		int i = 0, j = 0;
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

	public static ArrayList<Node> getAllNodes(Parent root) {
		ArrayList<Node> nodes = new ArrayList<Node>();
		addAllDescendents(root, nodes);
		return nodes;
	}

	private static void addAllDescendents(Parent parent, ArrayList<Node> nodes) {
		for (Node node : parent.getChildrenUnmodifiable()) {
			nodes.add(node);
			if (node instanceof Parent)
				addAllDescendents((Parent) node, nodes);
		}
	}

}
