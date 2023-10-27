package model;

public class Direccion {
	private int id;
	private int numero;
	public Direccion(int id, String pais, String ciudad, String calle, int numero) {
		super();
		this.id = id;
		this.pais = pais;
		this.ciudad = ciudad;
		this.calle = calle;
		this.numero = numero;
	}
	public Direccion(String pais, String ciudad, String calle, int numero) {
		super();
		this.id = -1;
		this.pais = pais;
		this.ciudad = ciudad;
		this.calle = calle;
		this.numero = numero;
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
	
	public String getCalle() {
		return calle;
	}
	public void setCalle(String calle) {
		this.calle = calle;
	}
	
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	private String pais,ciudad,calle;
}
