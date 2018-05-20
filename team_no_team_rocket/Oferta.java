package team_no_team_rocket;

import java.time.Duration;
import java.time.Period;
import java.util.Date;

public class Oferta {
	
	
	//C:\Users\ALVAR\git\team_no_team_rocket\team_no_team_rocket\drivers
	
	private String codOferta;
	private String nombre;
	private double precio;
	private String descripcion;
	private Local local;
	private int duracion;
	private String fchaHraInicio;
	private String fchaHraFin;
	
	

	/** Constructor de la clase Oferta
	 * @param cod_oferta codigo de la oferta
	 * @param nombre de la oferta
	 * @param precio que tiene la oferta
	 * @param descripcion detallada de como va a ser la oferta
	 * @param fchaHraInicio Fecha de inicio de la oferta con formato YYYY/MM/DD HH:MM:SS
	 * @param fchaHraFin Fecha de fin de la oferta con formato YYYY/MM/DD HH:MM:SS	
	 */
	public Oferta(String nombre, double precio, String descripcion,
			String fchaHraInicio, String fchaHraFin) {
		super();
		this.codOferta = Util.obtenerCodigo(nombre);
		this.nombre = nombre;
		this.precio = precio;
		this.descripcion = descripcion;
		this.fchaHraInicio = fchaHraInicio;
		this.fchaHraFin = fchaHraFin;
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
		return codOferta;
	}

	public void setCodigo(String codigo) {
		this.codOferta = codigo;
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

	public String getFchaHraInicio() {
		return fchaHraInicio;
	}

	public void setF_h_inicio(String fchaHraInicio) {
		this.fchaHraInicio = fchaHraInicio;
	}

	public String getF_h_fin() {
		return fchaHraFin;
	}

	public void setF_h_fin(String fchaHraFin) {
		this.fchaHraFin = fchaHraFin;
	}

}
