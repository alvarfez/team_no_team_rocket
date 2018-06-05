package team_no_team_rocket;

import static org.neo4j.driver.v1.Values.parameters;

import org.neo4j.driver.internal.spi.Connection;

import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;
import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.StatementResult;
import org.neo4j.driver.v1.Transaction;
import org.neo4j.driver.v1.TransactionWork;

import static org.neo4j.driver.v1.Values.parameters;


public class BDNeo4j implements AutoCloseable
{
	public int n = 1;
	public Driver driver;
	public Session session;
	
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
			String codLocal = local.getCodBar();
			TipoLocal tipo = local.getTipo();
			String direccion = local.getDireccion();
			int telefono = local.getTelefono();
			
			session.run("CREATE (" +codLocal + ":Bar {codLocal:'"+ codLocal + "', "
					+ "propietario:'"+ propietario + "', "
					+ "nombre:'" + nombre +"'"
					+ ", tipo:'"+ tipo + "', "
					+ "direccion:'" +direccion+"', "
					+ "tfno:" + telefono + "})");
			
			System.out.println("CREATE (bar1:Bar {codLocal:'"+ codLocal + "', "
					+ "propietario:'"+ propietario + "', "
					+ "nombre:'" + nombre +"'"
					+ ", tipo:'"+ tipo + "', "
					+ "direccion:'" +direccion+"', "
					+ "tfno:" + telefono + "})");
			
			
		}catch (Exception e){
			
		}		
	}
	
	/** Metodo que borra el local y las ofertas realizadas
	 * @param codLocal codigo del Local
	 */
	public void borrarLocal(String codLocal){
		session.run("MATCH (b:Bar)\n WHERE b.codLocal =\""+codLocal+ "\""
				+ "\nDETACH DELETE b");
		
		System.out.println("MATCH (b:Bar)\nWHERE b.codLocal =\""+codLocal+ "\""
				+ "\nDETACH DELETE b");
		
		
		
	}
	
	public void anyadirOferta(String codLocal){
		
	}
	
	public static void main(String[] args) throws Exception {
		
		Local l2 =  new Local("Alvar", "Bar de Philadelfia", "bar", "calle margarita", 566354354);
		BDNeo4j n = new BDNeo4j();
		//n.anyadirLocal(l);
		//n.anyadirLocal(l2);
		n.borrarLocal("Bar0");
		System.out.println("Funciona?");
		
		
	}
	
	
}
