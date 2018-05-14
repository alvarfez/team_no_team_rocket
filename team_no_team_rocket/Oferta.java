package team_no_team_rocket;

import java.time.Duration;
import java.time.Period;

public class Oferta {
	
	
	//C:\Users\ALVAR\git\team_no_team_rocket\team_no_team_rocket\drivers
	
	private String codigo;
	private String nombre;
	private double precio;
	private String descripcion;
	private Local local;
	private int duracion;
	
	
	private static int numCod = 0;
	
	public Oferta(String nombre, double precio, String descripcion, int duracion) {
		super();
		this.nombre = nombre;
		this.precio = precio;
		this.codigo = obtenerCodigo(nombre);
		this.descripcion = descripcion;
		this.duracion = duracion;
	}

	/** Método que genera un código particular para cada oferta con las 3 primeras letras del nombre y un numero
	 * @param str el nombre de la oferta
	 * @return el código
	 */
	public static String obtenerCodigo(String str){
		String provisional = str.substring(0,3) + numCod;
		numCod ++;
		return provisional;
	}
	
	/* Getters y setters
	 * 
	 */
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Local getLocal() {
		return local;
	}

	public void setLocal(Local local) {
		this.local = local;
	}

	public int getDuracion() {
		return duracion;
	}
	
	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}
	
	public String toString(){
		return this.nombre +" " + this.precio + " €";
	}

}
