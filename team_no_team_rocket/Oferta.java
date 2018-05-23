package team_no_team_rocket;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Period;
import java.util.Date;
import java.util.GregorianCalendar;

public class Oferta {
	
	
	//C:\Users\ALVAR\git\team_no_team_rocket\team_no_team_rocket\drivers
	
	private String codOferta;
	private String nombre;
	private double precio;
	private String descripcion;
	private Local local;
	private int duracion;
	private Date fchaHraInicio;
	private Date fchaHraFin;
	private boolean[] diasActiva;
	
	

	/** Constructor de la clase Oferta
	 * @param cod_oferta codigo de la oferta
	 * @param nombre de la oferta
	 * @param precio que tiene la oferta
	 * @param descripcion detallada de como va a ser la oferta
	 * @param fchaHraInicio Fecha de inicio de la oferta con formato YYYY/MM/DD HH:MM:SS
	 * @param fchaHraFin Fecha de fin de la oferta con formato YYYY/MM/DD HH:MM:SS	
	 */
	public Oferta(String nombre, double precio, String descripcion,
			Date fchaHraInicio, Date fchaHraFin) {
		super();
		this.codOferta = Util.obtenerCodigo(nombre);
		this.nombre = nombre;
		this.precio = precio;
		this.descripcion = descripcion;
		this.fchaHraInicio = fchaHraInicio;
		this.fchaHraFin = fchaHraFin;
		this.diasActiva = new boolean[7];
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

	public Date getFchaHraInicio() {
		return fchaHraInicio;
	}

	public void setFchaHraInicio(Date fchaHraInicio) {
		this.fchaHraInicio = fchaHraInicio;
	}

	public Date getFchaHraFin() {
		return fchaHraFin;
	}

	public void setFchaHraFin(Date fchaHraFin) {
		this.fchaHraFin = fchaHraFin;
	}

	public boolean[] getDiasActiva() {
		return diasActiva;
	}

	public void setDiasActiva(boolean[] diasActiva) {
		this.diasActiva = diasActiva;
	}
	
	/** Método que devuelve un booleano según si la oferta dada está activa o no
	 * @return boolean 
	 */
	public boolean isActiva(){
		Date d1 = new Date();
		GregorianCalendar gcHoy = new GregorianCalendar();
		GregorianCalendar gcOfertaInicio = new GregorianCalendar();
		GregorianCalendar gcOfertaFin = new GregorianCalendar();
		gcHoy.setTime(d1);
		gcOfertaInicio.setTime(fchaHraInicio);
		gcOfertaFin.setTime(fchaHraFin);
		int diaDeHoy = gcHoy.get(GregorianCalendar.DAY_OF_WEEK);
		int horaDeHoy = gcHoy.get(GregorianCalendar.HOUR_OF_DAY);
		int horaOfertaInicio = gcOfertaInicio.get(GregorianCalendar.HOUR_OF_DAY);
		int horaOfertaFin = gcOfertaInicio.get(GregorianCalendar.HOUR_OF_DAY);
		
		if (horaDeHoy>=horaOfertaInicio && horaDeHoy<horaOfertaFin ){
			if(diasActiva[diaDeHoy] || diasActiva == null){
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
		
	}
	public static void main(String[] args) {
		Date d1 = new Date();
		long dia2 = d1.getTime() + 10000;
		Date d2 = new Date(dia2);
		boolean[] misDias = new boolean[7];
		misDias[3] = true;
		Oferta o = new Oferta("ejemplo",42,"Comprobar si funciona isActiva()", d1,d2);
		o.setDiasActiva(misDias);
		System.out.println(o.isActiva());
	}
}
	
