package team_no_team_rocket;

import java.util.ArrayList;

public class Local {

	//Atributos de clase
	private static String nombre;
	private static TipoLocal tipo;
	private static String direccion;
	private static int telefono;
	private static ArrayList<Oferta> ListaOfertas;
	
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
		this.direccion = direccion;
		this.telefono = telefono;
		
	}
	
	/** Método que pasa un string a un enum de TipoLocal
	 * @param tipo --> el tipo en String 
	 * @return --> devuelve el TipoLocal correspondiente
	 */
	public static TipoLocal convertirATipoLocal(String tipo){
		if (tipo == "Bar" || tipo == "bar" || tipo == "BAR"){
			return TipoLocal.BAR_CAFETERIA;
		} else if (tipo == "Restaurante" || tipo == "restaurante" || tipo == "RESTAURANTE"){
			return TipoLocal.RESTAURANTE;
		} else if (tipo == "Pub" || tipo == "pub" || tipo == "PUB"){
			return TipoLocal.PUB;
		} else if (tipo == "Taberna" || tipo == "taberna" || tipo == "TABERNA"){
			return TipoLocal.TABERNA;
		} else {
			return null;
		}
	}

	public static String getNombre() {
		return nombre;
	}

	public static void setNombre(String nombre) {
		Local.nombre = nombre;
	}

	public static TipoLocal getTipo() {
		return tipo;
	}

	public static void setTipo(TipoLocal tipo) {
		Local.tipo = tipo;
	}

	public static String getDireccion() {
		return direccion;
	}

	public static void setDireccion(String direccion) {
		Local.direccion = direccion;
	}

	public static int getTelefono() {
		return telefono;
	}

	public static void setTelefono(int telefono) {
		Local.telefono = telefono;
	}
}
