package controller;

import java.sql.SQLException;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.*;

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
	private TableColumn<Aeropuerto, Double> finanSociosCol;

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
		for (int i = 0; i <= 1; i++) {
			updateTable(null);
		}
	}

	@FXML
	void anadirAeropuertoPublico(ActionEvent event) {
		String[] datos = new String[9];
		Button botonGuardar = new Button("Guardar");
		Button botonCancelar = new Button("Cancelar");
		Stage ventana = createGridWindowFromNodes("Aviones - Añadir aeropuerto".toUpperCase(), new Label("Nombre"),
				new TextField(), new Label("Año de inauguracion"), new TextField(), new Label("Capacidad"),
				new TextField(), new Label("País"), new TextField(), new Label("Ciudad"), new TextField(),
				new Label("Calle"), new TextField(), new Label("Numero"), new TextField(), new Label("Financiacion"),
				new TextField(), new Label("Numero de trabajadores"), new TextField(), botonGuardar, botonCancelar);
		ventana.show();
		botonCancelar.setOnAction(e -> {
			ventana.close();
		});
		botonGuardar.setOnAction(e -> {
			int i = 0;
			for (Node n : getAllNodes(ventana.getScene().getRoot())) {
				if (n.getClass() == TextField.class) {
					datos[i] = ((TextField) n).getText();
					i++;
				}
			}
			for (String dato : datos) {
				if (dato.equals("")) {
					mostrarVentanaEmergente("Campos vacíos", "Algunos campos no se han rellenado", AlertType.ERROR);
					return;
				}

			}
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
			tablaAeropuertos.setItems(gestordb.cargarAeropuertosPublicos());
			ventana.close();
		});
	}

	@FXML
	void anadirAeropuertoPrivado(ActionEvent event) {
		String[] datos = new String[9];
		Button botonGuardar = new Button("Guardar");
		Button botonCancelar = new Button("Cancelar");
		Stage ventana = createGridWindowFromNodes("Aviones - Añadir aeropuerto".toUpperCase(), new Label("Nombre"),
				new TextField(), new Label("Año de inauguracion"), new TextField(), new Label("Capacidad"),
				new TextField(), new Label("País"), new TextField(), new Label("Ciudad"), new TextField(),
				new Label("Calle"), new TextField(), new Label("Numero"), new TextField(), new Label("Financiacion"),
				new TextField(), new Label("Numero de trabajadores"), new TextField(), botonGuardar, botonCancelar);
		ventana.show();
		botonCancelar.setOnAction(e -> {
			ventana.close();
		});
		botonGuardar.setOnAction(e -> {
			int i = 0;
			for (Node n : getAllNodes(ventana.getScene().getRoot())) {
				if (n.getClass() == TextField.class) {
					datos[i] = ((TextField) n).getText();
					i++;
				}
			}
			for (String dato : datos) {
				if (dato.equals("")) {
					mostrarVentanaEmergente("Campos vacíos", "Algunos campos no se han rellenado", AlertType.ERROR);
					return;
				}

			}
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
			tablaAeropuertos.setItems(gestordb.cargarAeropuertosPrivados());
			ventana.close();
		});
	}

	@FXML
	void borrarAeropuerto(ActionEvent event) {
		Aeropuerto selectedAirport = tablaAeropuertos.getSelectionModel().getSelectedItem();
		if (selectedAirport == null)
			return;
		try {
			gestordb.borrarAeropuerto(selectedAirport);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (leyendoPublicos) {
				tablaAeropuertos.setItems(gestordb.cargarAeropuertosPublicos());
			} else {
				tablaAeropuertos.setItems(gestordb.cargarAeropuertosPrivados());
			}
		}
	}

	@FXML
	void editarAeropuerto(ActionEvent event) {
		String[] datos = new String[9];
		Aeropuerto selectedAirport = tablaAeropuertos.getSelectionModel().getSelectedItem();
		if (selectedAirport == null) {
			mostrarVentanaEmergente("Falta de seleccion", "Ningun aeropuerto ha sido seleccionado", AlertType.ERROR);
			return;
		}
		if (leyendoPublicos) {
			Button botonGuardar = new Button("Guardar");
			Button botonCancelar = new Button("Cancelar");
			Stage ventana = createGridWindowFromNodes("Aviones - Editar aeropuerto".toUpperCase(), new Label("Nombre"),
					new TextField(selectedAirport.getNombre()), new Label("Año de inauguracion"),
					new TextField(selectedAirport.getAnio() + ""), new Label("Capacidad"),
					new TextField(selectedAirport.getCapacidad() + ""), new Label("País"),
					new TextField(selectedAirport.getPais()), new Label("Ciudad"),
					new TextField(selectedAirport.getCiudad()), new Label("Calle"),
					new TextField(selectedAirport.getCalle()), new Label("Numero"),
					new TextField(selectedAirport.getNumero() + ""), new Label("Financiacion"),
					new TextField(selectedAirport.getFinanciacion() + ""), new Label("Numero de trabajadores"),
					new TextField(selectedAirport.getTrabajadores() + ""), botonGuardar, botonCancelar);
			ventana.show();
			botonCancelar.setOnAction(e -> {
				ventana.close();
			});
			botonGuardar.setOnAction(e -> {
				int i = 0;
				for (Node n : getAllNodes(ventana.getScene().getRoot())) {
					if (n.getClass() == TextField.class) {
						datos[i] = ((TextField) n).getText();
						i++;
					}
				}
				for (String dato : datos) {
					if (dato.equals("")) {
						mostrarVentanaEmergente("Campos vacíos", "Algunos campos no se han rellenado", AlertType.ERROR);
						return;
					}

				}
				try {
					Aeropuerto modifiedAeropuerto = new Aeropuerto(-1, datos[0], Integer.parseInt(datos[1]),
							Integer.parseInt(datos[2]), -1, "none",
							new Direccion(datos[3], datos[4], datos[5], Integer.parseInt(datos[6])),
							Integer.parseInt(datos[8]), Double.parseDouble(datos[7]));
					gestordb.modificarAeropuerto(selectedAirport, modifiedAeropuerto);
				} catch (NumberFormatException e1) {
					e1.printStackTrace();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				tablaAeropuertos.setItems(gestordb.cargarAeropuertosPublicos());
				ventana.close();
			});
		} else {
			Button botonGuardar = new Button("Guardar");
			Button botonCancelar = new Button("Cancelar");
			Stage ventana = createGridWindowFromNodes("Aviones - Editar aeropuerto".toUpperCase(), new Label("Nombre"),
					new TextField(selectedAirport.getNombre()), new Label("Año de inauguracion"),
					new TextField(selectedAirport.getAnio() + ""), new Label("Capacidad"),
					new TextField(selectedAirport.getCapacidad() + ""), new Label("País"),
					new TextField(selectedAirport.getPais()), new Label("Ciudad"),
					new TextField(selectedAirport.getCiudad()), new Label("Calle"),
					new TextField(selectedAirport.getCalle()), new Label("Numero"),
					new TextField(selectedAirport.getNumero() + ""), new Label("Numero de socios"),
					new TextField(selectedAirport.getSocios() + ""), botonGuardar, botonCancelar);
			ventana.show();
			botonCancelar.setOnAction(e -> {
				ventana.close();
			});
			botonGuardar.setOnAction(e -> {
				int i = 0;
				for (Node n : getAllNodes(ventana.getScene().getRoot())) {
					if (n.getClass() == TextField.class) {
						datos[i] = ((TextField) n).getText();
						i++;
					}
				}
				for (String dato : datos) {
					if (dato.equals("")) {
						mostrarVentanaEmergente("Campos vacíos", "Algunos campos no se han rellenado", AlertType.ERROR);
						return;
					}
				}
				try {
					Aeropuerto modifiedAeropuerto = new Aeropuerto(-1, datos[0], Integer.parseInt(datos[1]),
							Integer.parseInt(datos[2]), -1, "none",
							new Direccion(datos[3], datos[4], datos[5], Integer.parseInt(datos[6])),
							Integer.parseInt(datos[7]));
					gestordb.modificarAeropuerto(selectedAirport, modifiedAeropuerto);
				} catch (NumberFormatException e1) {
					mostrarVentanaEmergente("Numerico mal formado", "Un campo numérico se ha rellenado con texto",
							AlertType.ERROR);
					return;
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				tablaAeropuertos.setItems(gestordb.cargarAeropuertosPrivados());
				ventana.close();
			});
		}
	}

	@FXML
	void infoAeropuerto(ActionEvent event) {
		Aeropuerto selectedAirport = tablaAeropuertos.getSelectionModel().getSelectedItem();
		if (selectedAirport == null) {
			mostrarVentanaEmergente("Falta de seleccion", "Ningun aeropuerto ha sido seleccionado", AlertType.ERROR);
			return;
		}
		mostrarVentanaEmergente("INFORMACION AEROPUERTO",
				selectedAirport.toString().replace("%AVIONES", avionesEnAeropuertoFormateado(selectedAirport)),
				AlertType.INFORMATION);
	}

	@FXML
	void borrarAvion(ActionEvent event) {
		ComboBox<String> comboNombreAeropuertos = new ComboBox<String>(gestordb.cargarNombresAeropuertos());
		Button botonGuardar = new Button("Borrar");
		Button botonCancelar = new Button("Cancelar");
		ComboBox<Avion> comboAviones = new ComboBox<>();
		comboAviones.setDisable(true);
		comboNombreAeropuertos.setOnAction(e -> {
			comboAviones.setItems(gestordb
					.listadoAvionesAeropuerto(gestordb.cargarAeropuertoPorNombre(comboNombreAeropuertos.getValue())));
			comboAviones.setDisable(false);
		});
		Stage ventana = createGridWindowFromNodes("Aviones - Borrar avion".toUpperCase(), new Label("Aeropuerto"),
				comboNombreAeropuertos, new Label("Avion"), comboAviones, botonGuardar, botonCancelar);
		ventana.show();
		botonCancelar.setOnAction(e -> {
			ventana.close();
		});
		botonGuardar.setOnAction(e -> {
			try {
				gestordb.deleteAvion(comboAviones.getValue());
			} catch (NumberFormatException numberFromatEx) {
				numberFromatEx.printStackTrace();
			} catch (SQLException sqlEx) {
				sqlEx.printStackTrace();
			}
			ventana.close();
		});
	}

	@FXML
	void activarAvion(ActionEvent event) {
		ComboBox<String> comboNombreAeropuertos = new ComboBox<String>(gestordb.cargarNombresAeropuertos());
		Button botonGuardar = new Button("Actualizar");
		Button botonCancelar = new Button("Cancelar");
		ComboBox<Avion> comboAviones = new ComboBox<>();
		comboAviones.setDisable(true);
		comboNombreAeropuertos.setOnAction(e -> {
			comboAviones.setItems(gestordb
					.listadoAvionesAeropuerto(gestordb.cargarAeropuertoPorNombre(comboNombreAeropuertos.getValue())));
			comboAviones.setDisable(false);
		});

		RadioButton[] activo = { new RadioButton("Activado"), new RadioButton("Desactivado") };
		activo[0].setSelected(leyendoPublicos);
		activo[1].setSelected(!leyendoPublicos);
		ToggleGroup activoGroup = new ToggleGroup();
		for (RadioButton r : activo) {
			r.setToggleGroup(activoGroup);
		}
		Stage ventana = createGridWindowFromNodes("Aviones - Activar avion".toUpperCase(), new Label("Aeropuerto"),
				comboNombreAeropuertos, new Label("Avion"), comboAviones, activo[0], activo[1], botonGuardar,
				botonCancelar);
		ventana.show();
		botonCancelar.setOnAction(e -> {
			ventana.close();
		});
		botonGuardar.setOnAction(e -> {
			try {
				Avion a = comboAviones.getValue();
				a.setActivado(activo[0].isSelected());
				gestordb.updateAvion(a);
			} catch (NumberFormatException numberFromatEx) {
				numberFromatEx.printStackTrace();
			} catch (SQLException sqlEx) {
				sqlEx.printStackTrace();
			}
			ventana.close();
		});
	}

	@FXML
	void anadirAvion(ActionEvent event) {
		String[] datos = new String[3];
		RadioButton[] activo = { new RadioButton("Activado"), new RadioButton("Desactivado") };
		activo[0].setSelected(leyendoPublicos);
		activo[1].setSelected(!leyendoPublicos);
		ToggleGroup activoGroup = new ToggleGroup();
		for (RadioButton r : activo) {
			r.setToggleGroup(activoGroup);
		}
		ComboBox<String> comboNombreAeropuertos = new ComboBox<String>(gestordb.cargarNombresAeropuertos());
		Button botonGuardar = new Button("Guardar");
		Button botonCancelar = new Button("Cancelar");
		Stage ventana = createGridWindowFromNodes("Aviones - Añadir avion".toUpperCase(), new Label("Modelo"),
				new TextField(), new Label("Asientos"), new TextField(), new Label("Vel. Max."), new TextField(),
				activo[0], activo[1], new Label("Aeropuerto:"), comboNombreAeropuertos, botonGuardar, botonCancelar);
		ventana.show();
		botonCancelar.setOnAction(e -> {
			ventana.close();
		});
		botonGuardar.setOnAction(e -> {
			int i = 0;
			for (Node n : getAllNodes(ventana.getScene().getRoot())) {
				if (n.getClass() == TextField.class) {
					datos[i] = ((TextField) n).getText();
					i++;
				}
			}
			for (String dato : datos) {
				if (dato.equals("")) {
					mostrarVentanaEmergente("Campos vacíos", "Algunos campos no se han rellenado", AlertType.ERROR);
					return;
				}

			}
			try {
				int idAero = gestordb.cargarAeropuertoPorNombre(comboNombreAeropuertos.getValue()).getId();
				gestordb.addAvion(idAero, new Avion(Integer.parseInt(datos[1]), idAero, Float.parseFloat(datos[2]),
						datos[0], activo[0].isSelected()));
			} catch (NumberFormatException numberFromatEx) {
				numberFromatEx.printStackTrace();
			} catch (SQLException sqlEx) {
				sqlEx.printStackTrace();
			}
			ventana.close();
		});
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

	private static void mostrarVentanaEmergente(String titulo, String content, AlertType tipo) {
		Alert anadidoAnimal = new Alert(tipo);
		anadidoAnimal.setTitle(titulo);
		anadidoAnimal.setHeaderText(null);
		anadidoAnimal.setContentText(content);
		anadidoAnimal.showAndWait();
	}

	private String avionesEnAeropuertoFormateado(Aeropuerto aeropuerto) {
		ObservableList<Avion> aviones = gestordb.listadoAvionesAeropuerto(aeropuerto);
		String textoAviones = "Aviones:\n";
		for (Avion a : aviones) {
			textoAviones += a.toString();
		}
		return textoAviones;
	}
}
