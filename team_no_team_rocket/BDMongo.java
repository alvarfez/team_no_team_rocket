package team_no_team_rocket;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.ServerAddress;

import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;

import org.bson.Document;
import java.util.Arrays;
import com.mongodb.Block;

import com.mongodb.client.MongoCursor;
import static com.mongodb.client.model.Filters.*;
import com.mongodb.client.result.DeleteResult;
import static com.mongodb.client.model.Updates.*;
import com.mongodb.client.result.UpdateResult;
import java.util.ArrayList;
import java.util.List;

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
		//Si no existe la base de datos la crea
		 
		
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
	public boolean anyadirLocal(String cod_Local, int puntuacion){
		database = mongoClient.getDatabase("Locales");
		collection = database.getCollection("local");

		ArrayList<String> array = new ArrayList<>();

		MongoCursor<Document> cursor = collection.find().iterator();
		try {
			while (cursor.hasNext()) {
				array.add(cursor.next().getString("codigoLocal"));
			}
		} finally {
			cursor.close();
		}
		System.out.println(array);
		boolean escribir = true;
		for(String s : array){
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
	
	/**
	 * Main del proyecto.
	 * @param args
	 */

	public static void main(String[] args) {
		BDMongo m = new BDMongo();
		boolean b = m.anyadirUsuario("Alfonso", "perro", 1);
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
				
		
	}
}




