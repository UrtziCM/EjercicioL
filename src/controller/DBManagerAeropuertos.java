package controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.*;

public class DBManagerAeropuertos {
	private ConnectionDB conexion;

	public ObservableList<Aeropuerto> cargarAeropuertosPublicos() {

		ObservableList<Aeropuerto> aeropuertos = FXCollections.observableArrayList();
		try {
			conexion = new ConnectionDB();
			String consulta = "SELECT * FROM aeropuertos";
			PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String nombre = rs.getString("nombre");
				int ano = rs.getInt("anio_inauguracion");
				int capacidad = rs.getInt("capacidad");
				PreparedStatement datosPublicos = conexion.getConexion()
						.prepareStatement("SELECT * FROM aeropuertos_publicos WHERE id_aeropuerto=" + id);
				ResultSet resultadoDatosPublicos = datosPublicos.executeQuery();
				double financiacion = 0;
				int trabajadores = 0;
				if (resultadoDatosPublicos.next()) {
					financiacion = resultadoDatosPublicos.getFloat("financiacion");
					trabajadores = resultadoDatosPublicos.getInt("num_trabajadores");
				}
				PreparedStatement direccionStatement = conexion.getConexion()
						.prepareStatement("SELECT * FROM direcciones WHERE id =" + rs.getInt("id_direccion"));
				ResultSet resultadoDireccion = direccionStatement.executeQuery();
				Direccion direccion;
				if (resultadoDireccion.next()) {
					direccion = new Direccion(resultadoDireccion.getString("pais"),
							resultadoDireccion.getString("ciudad"), resultadoDireccion.getString("calle"),
							resultadoDireccion.getInt("numero"));
				} else
					direccion = null;
				if (financiacion != 0 && direccion != null) {

					Aeropuerto a = new Aeropuerto(id, nombre, ano, capacidad, id, rs.getString("imagen"), direccion,
							trabajadores, financiacion);
					aeropuertos.add(a);
				}
			}
			rs.close();
			conexion.closeConexion();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return aeropuertos;
	}

	public ObservableList<Aeropuerto> cargarAeropuertosPrivados() {
		ObservableList<Aeropuerto> aeropuertos = FXCollections.observableArrayList();
		try {
			conexion = new ConnectionDB();
			String consulta = "SELECT a.*, ap.numero_socios AS socios FROM aeropuertos a,aeropuertos_privados ap WHERE id IN (SELECT id_aeropuerto FROM aeropuertos_privados) AND ap.numero_socios = (SELECT numero_socios FROM aeropuertos_privados WHERE id_aeropuerto = id)";
			PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String nombre = rs.getString("nombre");
				int ano = rs.getInt("anio_inauguracion");
				int capacidad = rs.getInt("capacidad");
				int socios = rs.getInt("socios");
				PreparedStatement direccionStatement = conexion.getConexion().prepareStatement("SELECT * FROM direcciones WHERE id =" + rs.getInt("id_direccion"));
				ResultSet resultadoDireccion = direccionStatement.executeQuery();
				Direccion direccion;
				if (resultadoDireccion.next()) {
					direccion = new Direccion(resultadoDireccion.getString("pais"),resultadoDireccion.getString("ciudad"), resultadoDireccion.getString("calle"),							resultadoDireccion.getInt("numero"));
				} else
					direccion = null;
				Aeropuerto a = new Aeropuerto(id, nombre, ano, capacidad, id, rs.getString("imagen"), direccion, socios);
				a.setSocios(socios);
				aeropuertos.add(a);
			}
			rs.close();
			conexion.closeConexion();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return aeropuertos;
	}

	public void addAeropuerto(Aeropuerto newAeropuerto) throws SQLException {
		
		conexion = new ConnectionDB();
		String consulta = "SELECT MAX(id) as maxID FROM aeropuertos";
		PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
		ResultSet rs = pstmt.executeQuery();
		if (rs.next())
			newAeropuerto.setId(rs.getInt("maxID")+1);
			newAeropuerto.setId_dir(newAeropuerto.getId());
		rs.close();
		pstmt.close();
		Statement direcStmt = conexion.getConexion().createStatement();
		String sql = "INSERT INTO `direcciones` (`id`,`pais`, `ciudad`, `calle`, `numero`) "
				+ "VALUES ("+ newAeropuerto.getId_dir() +",'" + newAeropuerto.getPais() + "', '" + newAeropuerto.getCiudad() + "', '"
				+ newAeropuerto.getPais() + "','" + newAeropuerto.getNumero() + "')";
		System.out.println(LocalDate.now());
		direcStmt.executeUpdate(sql);			

		Statement stmt = conexion.getConexion().createStatement();
		sql = "INSERT INTO `aeropuertos` (`id`,`nombre`, `anio_inauguracion`, `capacidad`, `id_direccion`) "
				+ "VALUES (" + newAeropuerto.getId() +",'" + newAeropuerto.getNombre() + "'," + " " + newAeropuerto.getAnio() + "," + " "
				+ newAeropuerto.getCapacidad() + "," + " " + newAeropuerto.getId_dir() + ")";
		stmt.executeUpdate(sql);
		
		if (newAeropuerto.getFinanciacion() <= 0) {
			stmt.close();
			stmt = conexion.getConexion().createStatement();
			sql = "INSERT INTO `aeropuertos_privados` (`id_aeropuerto`, `numero_socios`)"
					+ "VALUES (" + newAeropuerto.getId() + ", " + newAeropuerto.getSocios() + ")";
			stmt.executeUpdate(sql);
			stmt.close();
		} else {
			stmt.close();
			stmt = conexion.getConexion().createStatement();
			sql = "INSERT INTO `aeropuertos_publicos` (`id_aeropuerto`, `financiacion`, `num_trabajadores`)"
					+ "VALUES (" + newAeropuerto.getId() + ", " + newAeropuerto.getFinanciacion() + ", "+ newAeropuerto.getTrabajadores() + ")";
			stmt.executeUpdate(sql);
			stmt.close();
		}
		conexion.closeConexion();
	}

	public void borrarAeropuerto(Aeropuerto aeropuerto) throws SQLException {
		conexion = new ConnectionDB();
		Statement stmt = conexion.getConexion().createStatement();
		String sql = "DELETE FROM aeropuertos WHERE id=" + aeropuerto.getId();
		stmt.executeUpdate(sql);
		sql = "DELETE FROM aeropuertos_privados WHERE id_aeropuerto=" + aeropuerto.getId();
		stmt.executeUpdate(sql);
		sql = "DELETE FROM direcciones WHERE id=" + aeropuerto.getId();
		stmt.executeUpdate(sql);
		conexion.closeConexion();
	}

	public void modificarAnimal(Aeropuerto oldAeropuerto, Aeropuerto newAeropuerto) throws SQLException {
		conexion = new ConnectionDB();
		Statement stmt = conexion.getConexion().createStatement();
		String sql = "UPDATE aeropuertos " + "SET nombre='" + newAeropuerto.getNombre() + "'," + "anio_inauguracion='"
				+ newAeropuerto.getAnio() + "'," + "capacidad='" + newAeropuerto.getCapacidad() + " WHERE id=" + oldAeropuerto.getId();
		stmt.executeUpdate(sql);
		if (oldAeropuerto.getSocios() > 0) {
			sql = "UPDATE aeropuertos_privados " + "SET numero_socios='" + newAeropuerto.getSocios() + " WHERE id_aeropuerto=" + oldAeropuerto.getId();
		} else {
			sql = "UPDATE aeropuertos_privados " + "SET financiacion='" + newAeropuerto.getFinanciacion() + "'," + "num_trabajadores='"
					+ newAeropuerto.getTrabajadores() +" WHERE id_aeropuerto=" + oldAeropuerto.getId();
		}
		stmt.executeUpdate(sql);
		conexion.closeConexion();
	}
}