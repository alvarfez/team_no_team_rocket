package team_no_team_rocket;

import java.util.ArrayList;

public class Local {

	//Atributos de clase
	private String nombre;
	private TipoLocal tipo;
	private String direccion;
	private int telefono;
	private ArrayList<Oferta> ListaOfertas;
	
	/** Constructor de clase Bar
	 * @param nombre --> nombre del Local
	 * @param tipo --> tipo de servicio = ENUM { BAR_CAFETERIA, RESTAURANTE, TABERNA, PUB }
	 * @param direccion --> calle y numero = String 
	 * @param telefono --> tlfno del local
	 */
	public Local( String nombre, String tipo, String direccion, int telefono){
		super();
		this.nombre = nombre;
		this.tipo = convertirATipoLocal(tipo);
		//this.tipo = tipo;
		this.direccion = direccion;
		this.telefono = telefono;
		
	}
	
	/** M�todo que pasa un string a un enum de TipoLocal
	 * @param tipo --> el tipo en String 
	 * @return --> devuelve el TipoLocal correspondiente
	 */
	public static TipoLocal convertirATipoLocal(String tipo){
		if (tipo.toLowerCase() == "bar"){
			return TipoLocal.BAR_CAFETERIA;
		} else if (tipo.toLowerCase() == "restaurante"){
			return TipoLocal.RESTAURANTE;
		} else if (tipo.toLowerCase() == "pub"){
			return TipoLocal.PUB;
		} else if (tipo.toLowerCase() == "taberna"){
			return TipoLocal.TABERNA;
		} else {
			return null;
		}
	}

	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public TipoLocal getTipo() {
		return tipo;
	}

	public void setTipo(TipoLocal tipo) {
		this.tipo = tipo;
	}

	public  String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public int getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	@Override
	public String toString() {
		return nombre + " " + tipo.name() + " " + direccion;
	}
	
	public static void main(String[] args) {

		Local l1 = new Local("Zubialde","bar", "1", 10);
		Local l2 = new Local("Caf�","pub", "1", 10);
		Local l3 = new Local("Terraza","restaurante", "1", 10);
		
		System.out.println(l1.toString());
		System.out.println(l2.toString());
		System.out.println(l3.toString());
		
		
	}
}
