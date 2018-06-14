package team_no_team_rocket;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.ServerAddress;

import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;

import org.bson.Document;
import java.util.Arrays;
import java.util.Collections;

import com.mongodb.Block;

import com.mongodb.client.MongoCursor;
import static com.mongodb.client.model.Filters.*;
import com.mongodb.client.result.DeleteResult;
import static com.mongodb.client.model.Updates.*;
import com.mongodb.client.result.UpdateResult;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * Prueba para realizar conexión con MongoDB.
 * @author j
 *
 */
public class BDMongo {
	public  MongoCollection<Document> collection;
	public  MongoDatabase database;
	public  MongoClient mongoClient;
	
	public BDMongo(){
		mongoClient = new MongoClient( "localhost" , 27017 ); 
		
	}

	/**Metodo que añade un usuario en la base de datos de usuarios de Mongo si no existe 
	 * previamente
	 * @param nombre del usuario
	 * @param pass contraseña del usuario
	 * @param categoria 0 si es un usuario y 1 si es un local
	 * @return true si no existe previamente y false si existe
	 */
	public boolean anyadirUsuario(String nombre, String pass, int categoria){
		database = mongoClient.getDatabase("Usuarios");
		collection = database.getCollection("usuario");
		ArrayList<String> array = new ArrayList<>();
		
		MongoCursor<Document> cursor = collection.find().iterator();
		try {
		    while (cursor.hasNext()) {
		       array.add(cursor.next().getString("nombre"));
		    }
		} finally {
		    cursor.close();
		}
		System.out.println(array);
		boolean escribir = true;
		for(String s : array){
			if (s.equals(nombre)){
				escribir = false;
			}
		}
		if(escribir){
			Document doc = new Document("nombre", nombre)
					.append("password", pass)
					.append("categoria", categoria);
			
			collection.insertOne(doc);
			System.out.println("Eureka");
			return true;
		}else{
			System.out.println("El usuario ya existe");
			return false;
		}

	}

	/** Metodo que añade un local en la base de datos de puntuaciones de Mongo si no existe 
	 * previamente
	 * @param cod_Local codigo del Local a añadir
	 * @param puntuacion puntuación del bar
	 * @return true si no existe previamente y false si existe
	 */
	public boolean anyadirLocal(Integer cod_Local, Double puntuacion){
		database = mongoClient.getDatabase("Locales");
		collection = database.getCollection("local");

		ArrayList<Integer> array = new ArrayList<>();

		MongoCursor<Document> cursor = collection.find().iterator();
		try {
			while (cursor.hasNext()) {
				array.add(cursor.next().getInteger("codigoLocal"));
			}
		} finally {
			cursor.close();
		}
		System.out.println(array);
		boolean escribir = true;
		for(Integer s : array){
			if (s.equals(cod_Local)){
				escribir = false;
			}
		}
		if(escribir){
			Document doc = new Document("codigoLocal", cod_Local)
					.append("puntuacion", puntuacion);
			
			collection.insertOne(doc);
			return true;
		}else{
			System.out.println("El local ya existe");
			return false;
		}
		
	}
	
	/** Metodo que comprueba la validacion de datos de usuario
	 * @param nombre del usuario
	 * @param password del usuario
	 * @return true si el par nombre-password es correcto / false en caso contrario
	 */
	public boolean comprobarUsuario(String nombre, String password){
		database = mongoClient.getDatabase("Usuarios");
		collection = database.getCollection("usuario");
		
		Document myDoc = collection.find(eq("nombre", nombre)).first();
		if( password.equals(myDoc.getString("password"))){
			System.out.println("Si");
			return true;
		}else System.out.println("No");return false;
		
		

	
	}
	
	/** Metodo que obtiene la categoría del usuario
	 * @param nombre del usuario a buscar su categoria
	 * @return String "local" si el usuario es un local y String "usuario" si es un usuario
	 */
	public String obtenerCategoria(String nombre){
		database = mongoClient.getDatabase("Usuarios");
		collection = database.getCollection("usuario");
		
		Document myDoc = collection.find(eq("nombre", nombre)).first();
		Integer res = myDoc.getInteger("categoria");
		if(res == 0) return "usuario";
		else return "local";
		
	}
	
	/**Metodo que devuelve un ranking de los bares que hay en la base de Datos
	 * @return TreeMap<Puntuacion, codigoLocal> ordenador de mayor puntuacion a menor
	 */
	public TreeMap<Double, Integer> obtenerRankingBares(){
		database = mongoClient.getDatabase("Locales");
		collection = database.getCollection("local");

		TreeMap<Double, Integer> array = new TreeMap<>(Collections.reverseOrder());

		MongoCursor<Document> cursor = collection.find().iterator();
		try {
			while (cursor.hasNext()) {
				System.out.println(array);
				Document mydoc = cursor.next();
				array.put(mydoc.getDouble("puntuacion"), mydoc.getInteger("codigoLocal"));
			}
		} finally {
			cursor.close();
		}
		return array;
	}
	
	/** Metodo que obtiene la puntuacion de un bar mediante su codigo
	 * @param codLocal codigo del Local a obtener la puntuacion
	 * @return puntuacion del local
	 */
	public Double obtenerPuntuacion(Integer codLocal){
		database = mongoClient.getDatabase("Locales");
		collection = database.getCollection("local");
		
		return collection.find(eq("codigoLocal", codLocal)).first().getDouble("codigoLocal");
	}
	
	public void actualizarPuntuacion(Integer codLocal, Puntuacion puntuacion){
		database = mongoClient.getDatabase("Locales");
		collection = database.getCollection("local");
		
		Document mydoc =  collection.find(eq("codigoLocal", codLocal)).first();
		collection.deleteOne(mydoc);
		mydoc.replace("puntuacion", puntuacion.getPuntosSobreCinco());
		
		collection.insertOne(mydoc);
		System.out.println(mydoc.get("puntuacion"));
		
	}
	
	/**
	 * Main del proyecto.
	 * @param args
	 */

	public static void main(String[] args) {
		BDMongo m = new BDMongo();
		
		
		
		boolean b = m.comprobarUsuario("Ander", "loco");
//		database = mongoClient.getDatabase("Usuarios");
//		collection = database.getCollection("usuario");
//		try{
//			Document myDoc = collection.find(eq("nombre", "Alvar")).first();
//			System.out.println(myDoc.get("nombre").toString());
//			String s = "Alvar";
//			if(s.equals(myDoc.getString("nombre"))) System.out.println("Si");
//		}catch(NullPointerException e){
//			
//		}
//		
		System.out.println(m.obtenerRankingBares());
		System.out.println(m.obtenerCategoria("Ander"));
		System.out.println("");	
		Puntuacion p = new Puntuacion();
		m.actualizarPuntuacion(0, p);
//		m.anyadirLocal(1, 3.0);
//		m.anyadirLocal(2, 4.0);
//		m.anyadirLocal(3, 5.0);
		
	}
}




