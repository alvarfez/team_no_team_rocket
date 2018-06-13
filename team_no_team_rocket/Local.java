package team_no_team_rocket;

import java.util.ArrayList;
import java.util.Comparator;

import javax.swing.ImageIcon;

import com.sun.java.accessibility.util.java.awt.ListTranslator;
import com.sun.prism.Image;

public class Local implements Comparable {

	//Atributos de clase
	private Integer codLocal;
	private String nombre;
	private String propietario;
	private TipoLocal tipo;
	private String direccion;
	private int telefono;
	private ArrayList<Oferta> listaOfertas;
	private ImageIcon foto;
	private Puntuacion puntuacion;

	/** Constructor de clase Bar
	 * @param cod_bar --> codigo del Local
	 * @param nombre --> nombre del Local
	 * @param tipo --> tipo de servicio = ENUM { BAR_CAFETERIA, RESTAURANTE, TABERNA, PUB }
	 * @param direccion --> calle y numero = String 
	 * @param telefono --> tlfno del local
	 */
	public Local( String propietario, String nombre, String tipo, String direccion, int telefono){
		super();
		this.propietario = propietario;
		this.codLocal = BDNeo4j.getCodigoLocal();
		this.nombre = nombre;
		this.tipo = convertirATipoLocal(tipo);
		this.direccion = direccion;
		this.telefono = telefono;
		this.puntuacion = new Puntuacion();
		
	}
	
	public Local(){
	}
	
	public String getPropietario() {
		return propietario;
	}

	public void setPropietario(String propietario) {
		this.propietario = propietario;
	}

	/** Método que pasa un string a un enum de TipoLocal
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
	
	/**	Añade una oferta a Local, si no tiene ninguna, se crea la lista primero
	 * @param o Oferta a añadir
	 */
	public void anyadirOferta(Oferta o){
		
		if (o != null && this.listaOfertas == null ){
			listaOfertas = new ArrayList<>();
			listaOfertas.add(o);
		} else {
			listaOfertas.add(o);
		}
		System.out.println("Tu oferta " + o.getNombre() + " ha sido correctamente añadida.");
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

	/* Aquí vienen todos los setters y getters
	 * 
	 */
	
	public int getCodBar() {
		return codLocal;
	}

	public void setCodBar(int codBar) {
		this.codLocal = codBar;
	}
	
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
	
	@Override
	public String toString() {
		return nombre + " " + tipo.name().toLowerCase() + " " + direccion;
	}
	
	public ImageIcon getFoto(){
		if (this.foto==null){
			ImageIcon ii = new ImageIcon("bin/team_no_team_rocket/fotos/3escobas.jpg");
			return ii;
		} else {
			return this.foto;
		}
	}
	public void setFoto( ImageIcon ii){
		this.foto = ii;
	}	
	

	public Puntuacion getPuntuacion() {
		return puntuacion;
	}

	public void setPuntuacion(Puntuacion puntuacion) {
		this.puntuacion = puntuacion;
	}

	public void setListaOfertas(ArrayList<Oferta> listaOfertas) {
		this.listaOfertas = listaOfertas;
	}

	public static void main(String[] args) {

//		Local l1 = new Local("Zubialde","bar", "1", 5);
//		Local l2 = new Local("Café","pub", "1", 4);
//		Local l3 = new Local("Terraza","restaurante", "1", 10);
//		
//		System.out.println(l1.toString());
//		System.out.println(l2.toString());
//		System.out.println(l3.toString());
//		l1.puntuacion.sumaPuntuacion(4);
//		l2.puntuacion.sumaPuntuacion(5);
//		System.out.println(l1.compareTo(l2));
		
	}

//	@Override
//	public int compare(Object arg0, Object arg1) {
//		if (((Local)arg0).puntuacion.getPuntosSobreCinco() < ((Local)arg1).puntuacion.getPuntosSobreCinco()){
//			return 1;
//		} else if (((Local)arg0).puntuacion.getPuntosSobreCinco() >= ((Local)arg1).puntuacion.getPuntosSobreCinco()){
//			return -1;
//		}
//		return 0;
//	}

	@Override
	public int compareTo(Object arg0) {
		if (this.puntuacion.getPuntosSobreCinco()<((Local)arg0).puntuacion.getPuntosSobreCinco()){
			return -1;
			} else if (this.puntuacion.getPuntosSobreCinco() >= ((Local)arg0).puntuacion.getPuntosSobreCinco()){
			return 1;
			}
		return 0;
	}


}
