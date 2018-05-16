package team_no_team_rocket;

import java.time.Duration;
import java.time.Period;
import java.util.Date;

public class Oferta {
	
	
	//C:\Users\ALVAR\git\team_no_team_rocket\team_no_team_rocket\drivers
	
	private int cod_oferta;
	private String codigo;
	private String nombre;
	private double precio;
	private String descripcion;
	private Local local;
	private int duracion;
	private String f_h_inicio;
	private String f_h_fin;
	
	
	private static int numCod = 0;
	
	/** Constructor de la clase Oferta
	 * @param cod_oferta codigo de la oferta
	 * @param nombre de la oferta
	 * @param precio que tiene la oferta
	 * @param descripcion detallada de como va a ser la oferta
	 * @param f_h_inicio Fecha de inicio de la oferta con formato YYYY/MM/DD HH:MM:SS
	 * @param f_h_fin Fecha de fin de la oferta con formato YYYY/MM/DD HH:MM:SS	
	 */
	public Oferta(int cod_oferta, String nombre, double precio, String descripcion,
			String f_h_inicio, String f_h_fin) {
		super();
		this.cod_oferta = cod_oferta;
		this.nombre = nombre;
		this.precio = precio;
		this.codigo = obtenerCodigo(nombre);
		this.descripcion = descripcion;
		this.f_h_inicio = f_h_inicio;
		this.f_h_fin = f_h_fin;
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

	public int getCod_oferta() {
		return cod_oferta;
	}

	public void setCod_oferta(int cod_oferta) {
		this.cod_oferta = cod_oferta;
	}

	public String getF_h_inicio() {
		return f_h_inicio;
	}

	public void setF_h_inicio(String f_h_inicio) {
		this.f_h_inicio = f_h_inicio;
	}

	public String getF_h_fin() {
		return f_h_fin;
	}

	public void setF_h_fin(String f_h_fin) {
		this.f_h_fin = f_h_fin;
	}

}
