package team_no_team_rocket;

import static org.neo4j.driver.v1.Values.parameters;

import java.util.ArrayList;
import java.util.Date;

import org.neo4j.driver.internal.spi.Connection;

import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;
import org.neo4j.driver.v1.Record;
import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.Statement;
import org.neo4j.driver.v1.StatementResult;
import org.neo4j.driver.v1.Transaction;
import org.neo4j.driver.v1.TransactionWork;

import static org.neo4j.driver.v1.Values.parameters;


/**
 * @author ALVAR
 *
 */
public class BDNeo4j implements AutoCloseable
{
	public int n = 1;
	public static Driver driver;
	public static Session session;
	
	public BDNeo4j(){
		driver = GraphDatabase.driver("bolt://127.0.0.1:7687");
		session = driver.session();
	}

	@Override
	public void close() throws Exception {
		driver.close();
		
	}
	
	/** Metodo que añade un local a la base de datos
	 * @param local a añadir a la bd
	 */
	public void anyadirLocal(Local local){
		try{
			String nombre = local.getNombre();
			String propietario = local.getPropietario();
			Integer codLocal = local.getCodBar();
			TipoLocal tipo = local.getTipo();
			String direccion = local.getDireccion();
			int telefono = local.getTelefono();
			
			session.run("CREATE (bar"+codLocal+":Bar {codLocal:'"+ codLocal + "', "
					+ "propietario:'"+ propietario + "', "
					+ "nombre:'" + nombre +"'"
					+ ", tipo:'"+ tipo + "', "
					+ "direccion:'" +direccion+"', "
					+ "tfno:" + telefono + "})");
			
//			System.out.println("CREATE (bar1:Bar {codLocal:'"+ codLocal + "', "
//					+ "propietario:'"+ propietario + "', "
//					+ "nombre:'" + nombre +"'"
//					+ ", tipo:'"+ tipo + "', "
//					+ "direccion:'" +direccion+"', "
//					+ "tfno:" + telefono + "})");
		}catch (Exception e){

		}		
	}
	
	/** Metodo que borra el local y las ofertas realizadas
	 * @param codLocal codigo del Local
	 */
	public void borrarLocal(Integer codLocal){
		session.run("MATCH (b:Bar)\n WHERE b.codLocal =\""+codLocal+ "\""
				+ "\nDETACH DELETE b");
		
		//System.out.println("MATCH (b:Bar)\nWHERE b.codLocal =\""+codLocal+ "\""
		//		+ "\nDETACH DELETE b");
		
	}
	
	/**Metodo que obtiene los codigos de los locales de la BD para obtener el ultimo
	 * @return Devuelve el codigo que el Local tendrá al crearse
	 */
	public static Integer getCodigoLocal(){
		try{
			StatementResult result = session.run("MATCH (b:Bar)\nRETURN b.codLocal");
			Integer contador = 0;
			Integer contadorTemp = 0;
			if(result.hasNext()){
				while(result.hasNext()){
					contador = Integer.parseInt(result.next().get(0).asString());
					if(contador > contadorTemp){
						contadorTemp = contador;
					}
				}
				return contadorTemp + 1;
			}else{
				contador = 0;
				return contadorTemp;
			}
		}catch(NullPointerException e){
			e.printStackTrace();
		}
		return 0;
	}
	
	/**Metodo que obtiene los codigos de Oferta para obtener el último creado
	 * @return Devuelve el codigo que la oferta a crear tendrá
	 */
	public static Integer getCodigoOferta(){
		try{
			StatementResult result = session.run("MATCH (o:Oferta)\nRETURN o.codOferta");
			Integer contador = 0;
			Integer contadorTemp = 0;
			if(result.hasNext()){
				while(result.hasNext()){
					contador = Integer.parseInt(result.next().get(0).asString());
					if(contador > contadorTemp){
						contadorTemp = contador;
					}	
				}
				return contadorTemp + 1;
			}else{
				contador = 0;
				return contadorTemp;
			}
		}catch(NullPointerException e){
			e.printStackTrace();
		}
		return 0;
	}
	
	/**Metodo que crea una oferta y crea una relación con el Local que la crea
	 * @param codLocal codigo del Local que crea la oferta
	 * @param oferta Oferta a introducir en la BD
	 */
	public void anyadirOferta(Integer codLocal, Oferta oferta){
		Integer codOferta = oferta.getCodigo();
		String nombre = oferta.getNombre();
		double precio = oferta.getPrecio();
		String descripcion = oferta.getDescripcion();
		Date fchaHraInicio = oferta.getFchaHraInicio();
		Date fchaHraFin = oferta.getFchaHraFin();
		
		session.run("CREATE (oferta"+codOferta+":Oferta {codOferta:'"+ codOferta + "', "
				+ "nombre:'"+ nombre + "', "
				+ "precio:'" + precio +"'"
				+ ", descripcion:'"+ descripcion + "', "
				+ "FechaHraInicio:'" +fchaHraInicio+"', "
				+ "FechaHraFin:'" + fchaHraFin + "'})");
		
		session.run("MATCH (b:Bar), (o:Oferta)"+
				"\nWHERE b.codLocal ='" +codLocal+ "' AND o.codOferta = '"+codOferta+"'"+
				"\nCREATE (b)-[h:HACE_OFERTA]->(o)");
	}
	
	/**Metodo que elimina una oferta y sus respectivas relaciones
	 * @param codOferta Codigo de la oferta a borrar
	 */
	public void borrarOferta(Integer codOferta){
		session.run("MATCH (o:Oferta)\n WHERE o.codOferta =\""+codOferta+ "\""
				+ "\nDETACH DELETE o");
		
	}
	
	/** Metodo que obtiene una lista de los locales que tiene un usuario
	 * @param propietario usuario que tiene los locales
	 * @return ArrayList de locales
	 */
	@SuppressWarnings("null")
	public ArrayList<Local> getLocales(String propietario){
		StatementResult result = session.run("MATCH (b:Bar)"
				+"\nWHERE b.propietario = '"+ propietario + "'" 
				+ "\nRETURN b.propietario, b.nombre, b.tipo, b.direccion, b.tfno");
		
		ArrayList<Local> array = new ArrayList<>();
		while(result.hasNext()){
			Local local =  new Local();
			Record record = result.next();
			local.setPropietario(record.get(0).asString());
			local.setNombre(record.get(1).asString());
			local.setTipo(TipoLocal.BAR_CAFETERIA);
			local.setDireccion(record.get(3).asString());
			local.setTelefono(record.get(4).asInt());
			
			array.add(local);			
		}
		return array;
	}
	
	/**Metodo que obtiene un Local 
	 * @param codLocal Codigo del local a obtener
	 * @return Local obtenido
	 */
	public Local getLocal(Integer codLocal){
		StatementResult result = session.run("MATCH (b:Bar)"
				+"\nWHERE b.codLocal = '"+ codLocal  + "'" 
				+ "\nRETURN b.propietario, b.nombre, b.tipo, b.direccion, b.tfno");
		
		Local local =  new Local();
		Record record = result.next();
		local.setPropietario(record.get(0).asString());
		local.setNombre(record.get(1).asString());
		local.setTipo(TipoLocal.BAR_CAFETERIA);
		local.setDireccion(record.get(3).asString());
		local.setTelefono(record.get(4).asInt());
		
		return local;
	}
	
	/**Metodo que devuelve la lista de ofertas que tiene un Local
	 * @param codLocal codigo del local que tiene las ofertas
	 * @return ArrayList con las ofertas del local
	 */
	public ArrayList<Oferta> getOfertas(Integer codLocal){
		StatementResult result = session.run("MATCH (b:Bar)"
				+"\nWHERE b.codLocal = '"+ codLocal + "'" 
				+ "\nRETURN o.nombre, o.precio, o.descripcion, o.FechaHraInicio, o.FechaHraFin");
		
		ArrayList<Oferta> array = new ArrayList<>();
		while(result.hasNext()){
			Oferta oferta =  new Oferta();
			Record record = result.next();
			oferta.setNombre(record.get(0).asString());
			oferta.setPrecio(record.get(1).asDouble());
			oferta.setDescripcion(record.get(2).asString());
			oferta.setFchaHraInicio(new Date(record.get(3).asInt()));
			oferta.setFchaHraFin(new Date(record.get(4).asInt()));
			
			array.add(oferta);			
		}
		return array;
	}
	
	public static void main(String[] args) throws Exception {
		
		
		BDNeo4j n = new BDNeo4j();
//		Local l1 = new Local("Ander", "Bar de JIJI", "bar", "Calle las fELIPE", 656345453);
		Local l2 =  new Local("Alvar", "Bar de Chicago", "bar", "calle Florida", 566354354);
		Date d1 = new Date();
		long dia2 = d1.getTime() + 10000000;
		Date d2 = new Date(dia2);
		boolean[] misDias = new boolean[7];
		misDias[5] = true;
		Oferta oferta = new Oferta("eemplo",42,"Ganas de que funcione", d1,d2);
		oferta.setDiasActiva(misDias);
		Integer codOferta = oferta.getCodigo();
		String nombre = oferta.getNombre();
		double precio = oferta.getPrecio();
		String descripcion = oferta.getDescripcion();
		Date fchaHraInicio = oferta.getFchaHraInicio();
		Date fchaHraFin = oferta.getFchaHraFin();
		
		n.anyadirLocal(l2);
//		n.anyadirLocal(l2);
//		
//		System.out.println("Funciona?");
//		
//		n.borrarLocal("Bar0");
//		n.borrarLocal(2);
//		getCodigo();
//		n.close();
		n.anyadirOferta(0, oferta );
		
//		n.borrarOferta(1);
//		n.borrarOferta(0);
//		n.getNextCodigo();
		System.out.println(n.getLocales("Ander"));
	}
	
	
}
