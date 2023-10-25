package model;

public class Aeropuerto {
	private int id;
	private String nombre;
	private int anio,capacidad,id_dir;
	private String imagen;
	private Direccion direccion;
	public Aeropuerto(int id, String nombre, int anio, int capacidad, int id_dir, String imagen, Direccion direccion) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.anio = anio;
		this.capacidad = capacidad;
		this.id_dir = id_dir;
		this.imagen = imagen;
		this.setDireccion(direccion);
	}
	public Aeropuerto(String nombre, int anio, int capacidad, int id_dir, String imagen, Direccion direccion) {
		super();
		this.id = -1;
		this.nombre = nombre;
		this.anio = anio;
		this.capacidad = capacidad;
		this.id_dir = id_dir;
		this.imagen = imagen;
		this.setDireccion(direccion);
	}
	public Aeropuerto(Aeropuerto aeropuerto) {
		super();
		this.id = aeropuerto.id;
		this.nombre = aeropuerto.nombre;
		this.anio = aeropuerto.anio;
		this.capacidad = aeropuerto.capacidad;
		this.id_dir = aeropuerto.id_dir;
		this.imagen = aeropuerto.imagen;
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
	public Direccion getDireccion() {
		return direccion;
	}
	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}
	
}
