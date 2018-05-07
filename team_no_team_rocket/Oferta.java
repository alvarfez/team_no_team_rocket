package team_no_team_rocket;

public class Oferta {
	
	private String nombre;
	private double precio;
	private String codigo;
	private String descripcion;
	private Horario horario;
	private Local local;
	
	private static int numCod = 0;
	
	public Oferta(Local local, String nombre, double precio, String descripcion, Horario horario) {
		super();
		this.local = local;
		this.nombre = nombre;
		this.precio = precio;
		this.codigo = obtenerCodigo(nombre);
		this.descripcion = descripcion;
		this.setHorario(horario);
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

	public Horario getHorario() {
		return horario;
	}

	public void setHorario(Horario horario) {
		this.horario = horario;
	}
	
	

}
