package model;

public class Direccion {
	private int id;
	private String pais,ciudad,calle;
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
}
