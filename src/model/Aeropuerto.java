package model;

import java.util.Objects;

public class Aeropuerto {
	private int id;
	private String nombre;
	private int anio, capacidad, id_dir;
	private String imagen;
	private String calle, pais, ciudad;
	private int numero;
	private int trabajadores, socios;
	
	double financiacion;

	public Aeropuerto(int id, String nombre, int anio, int capacidad, int id_dir, String imagen, Direccion direccion,int socios) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.anio = anio;
		this.capacidad = capacidad;
		this.id_dir = id_dir;
		this.imagen = imagen;
		this.calle = direccion.getCalle();
		this.pais = direccion.getPais();
		this.ciudad = direccion.getCiudad();
		this.numero = direccion.getNumero();
	}
	public Aeropuerto(int id, String nombre, int anio, int capacidad, int id_dir, String imagen, Direccion direccion, int trabajadores, double financiacion) {
		this.id = id;
		this.nombre = nombre;
		this.anio = anio;
		this.capacidad = capacidad;
		this.id_dir = id_dir;
		this.imagen = imagen;
		this.calle = direccion.getCalle();
		this.pais = direccion.getPais();
		this.ciudad = direccion.getCiudad();
		this.numero = direccion.getNumero();
		this.trabajadores = trabajadores;
		this.financiacion = financiacion;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getAnio() {
		return anio;
	}

	public void setAnio(int anio) {
		this.anio = anio;
	}

	public int getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}

	public int getId_dir() {
		return id_dir;
	}

	public void setId_dir(int id_dir) {
		this.id_dir = id_dir;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}
	public int getTrabajadores() {
		return trabajadores;
	}

	public void setTrabajadores(int trabajadores) {
		this.trabajadores = trabajadores;
	}

	public int getSocios() {
		return socios;
	}

	public void setSocios(int socios) {
		this.socios = socios;
	}

	public double getFinanciacion() {
		return financiacion;
	}

	public void setFinanciacion(double financiacion) {
		this.financiacion = financiacion;
	}
	
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Aeropuerto other = (Aeropuerto) obj;
		return id == other.id || nombre.equals(other.nombre);
	}
	


}
