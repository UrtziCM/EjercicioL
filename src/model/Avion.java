package model;

public class Avion {
	private int id,asientos,id_aeropuerto;
	private float velocidad;
	private String modelo;
	private boolean activado;
	public Avion(int id, int asientos, int id_aeropuerto, float velocidad, String modelo, boolean activado) {
		super();
		this.id = id;
		this.asientos = asientos;
		this.id_aeropuerto = id_aeropuerto;
		this.velocidad = velocidad;
		this.modelo = modelo;
		this.activado = activado;
	}
	public Avion(int asientos, int id_aeropuerto, float velocidad, String modelo, boolean activado) {
		super();
		this.asientos = asientos;
		this.id_aeropuerto = id_aeropuerto;
		this.velocidad = velocidad;
		this.modelo = modelo;
		this.activado = activado;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAsientos() {
		return asientos;
	}
	public void setAsientos(int asientos) {
		this.asientos = asientos;
	}
	public int getId_aeropuerto() {
		return id_aeropuerto;
	}
	public void setId_aeropuerto(int id_aeropuerto) {
		this.id_aeropuerto = id_aeropuerto;
	}
	public float getVelocidad() {
		return velocidad;
	}
	public void setVelocidad(float velocidad) {
		this.velocidad = velocidad;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public boolean isActivado() {
		return activado;
	}
	public void setActivado(boolean activado) {
		this.activado = activado;
	}
	@Override
	public String toString() {
		String activo = (activado)?"\n\tActivado":"\n\tDesactivado";
		String listado = "\tModelo: " +modelo+"\n\tNúmero de asientos: "+asientos
		+"\n\tVelocidad máxima: "+velocidad + activo;
		return listado;
	}	
}
