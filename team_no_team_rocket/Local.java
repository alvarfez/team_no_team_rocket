package team_no_team_rocket;

import java.util.ArrayList;

import javax.swing.ImageIcon;

import com.sun.java.accessibility.util.java.awt.ListTranslator;
import com.sun.prism.Image;

public class Local {

	//Atributos de clase
	private String nombre;
	private TipoLocal tipo;
	private String direccion;
	private int telefono;
	private ArrayList<Oferta> listaOfertas;
	private ImageIcon foto;
	private Puntuacion puntuacion;
	

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
		this.puntuacion = new Puntuacion();
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
	
	/**	A�ade una oferta a Local, si no tiene ninguna, se crea la lista primero
	 * @param o Oferta a a�adir
	 */
	public void anyadirOferta(Oferta o){
		
		if (o != null && this.listaOfertas == null ){
			listaOfertas = new ArrayList<>();
			listaOfertas.add(o);
		} else {
			listaOfertas.add(o);
		}
		System.out.println("Tu oferta " + o.getNombre() + " ha sido correctamente a�adida.");
	}
	
	/** Dada una oferta o, si existe en la listaOFertas del objeto Local, la elimina.
	 * @param o
	 */
	public void eliminarOferta(Oferta o){
		
		if ( o != null ){
			for (Oferta of : listaOfertas){
				if (of.equals(o)){
					listaOfertas.remove(of);
					System.out.println("Tu oferta " + of.getNombre() + " ha sido correctamente eliminada.");
				}
			}
		}
	}

	/* Aqu� vienen todos los setters y getters
	 * 
	 */
	public ArrayList<Oferta> getListaOfertas(){
		return listaOfertas;
	}
	
	public void setOfertas( ArrayList<Oferta> lOfertas){
		this.listaOfertas = lOfertas;
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
	
	public Puntuacion getPuntuacion() {
		return puntuacion;
	}

	/** M�todo que devuelve el puntaje actual sobre 5 del local deseado
	 * @return 
	 */
	public double getPuntosSobre5() {
		return puntuacion.getPuntosSobreCinco();
	}

	public void setPuntuacion(Puntuacion puntuacion) {
		if (puntuacion.getPuntosSobreCinco() >= 0.0 && puntuacion.getPuntosSobreCinco() <= 5.0 ) {this.puntuacion = puntuacion;}else{System.out.println("Puntuaci�n fuera de los l�mites establecidos");}
	}
	

	public ImageIcon getFoto(){
		if (this.foto.equals(null)){
			ImageIcon ii = new ImageIcon("team_no_team_rocket/image.jpg");
			return ii;
		} else {
			return this.foto;
		}
	}
	
	public void setFoto( ImageIcon ii){
		this.foto = ii;
	}
	
	@Override
	public String toString() {
		return nombre + " " + tipo.name().toLowerCase() + " " + direccion;
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
