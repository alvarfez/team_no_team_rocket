package team_no_team_rocket;

import java.util.Set;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;

/**
 * Prueba para realizar conexión con MongoDB.
 * @author j
 *
 */
public class BDMongo {
	/**
	 * Main del proyecto.
	 * @param args
	 */
	@SuppressWarnings({ "deprecation", "resource", "unused" })
	public static void main(String[] args) {
		System.out.println("Prueba conexión MongoDB");
		MongoClient mongo = null;
		mongo = new MongoClient("localhost", 27017);

		if (mongo != null) {

			//Si no existe la base de datos la crea
			DB db = new Mongo().getDB("Locales");

			//Crea una tabla si no existe y agrega datos
			DBCollection table = db.getCollection("Local");
			
			
			//Crea los objectos básicos
			BasicDBObject document1 = anyadirDocumento("Zubialde", "Puente de Deusto", 4);
			BasicDBObject document2 = anyadirDocumento("Cafe", "Lehendakari Aguirre", 3);
			BasicDBObject document3 = anyadirDocumento("Terraza", "Puente de Deusto", 2);
			BasicDBObject document4 = anyadirDocumento("Badulaque", "Springfield", 4);
			BasicDBObject document5 = anyadirDocumento("El bar de Moe", "Springfield", 4);
			BasicDBObject document6 = anyadirDocumento("La Tasca", "Lehendakari Aguirre", 5);
			

			//Insertar tablas
			table.insert(document1);
			table.insert(document2);
			table.insert(document3);
			table.insert(document4);
			table.insert(document5);
			table.insert(document6);

			//Actualiza la puntuacion de los bares con el nombre "Cafe"
			BasicDBObject updateScore = new BasicDBObject();
			updateScore.append("$set", new BasicDBObject().append("puntuacion", 5));

			BasicDBObject searchById = new BasicDBObject();
			searchById.append("nombre", "Cafe");

			table.updateMulti(searchById, updateScore);

			//Listar la tabla "local"
			System.out.println("Listar los registros de la tabla: ");
			DBCursor cur = table.find();
			while (cur.hasNext()) {
				System.out.println(" - " + cur.next().get("nombre") + " " + cur.curr().get("direccion") + " -- " + cur.curr().get("puntuacion") + ")");
			}
			System.out.println();

			//Listar de la tabla "local" aquellos que tengan un 4
			System.out.println("Listar los registros de la tabla cuyo nombre sea Jose: ");
			BasicDBObject query = new BasicDBObject();
			query.put("puntuacion", "4");

			DBCursor cur2 = table.find(query);
			while (cur2.hasNext()) {
				System.out.println(" - " + cur2.next().get("nombre") + " " + cur2.curr().get("direccion") + " -- " + cur2.curr().get("puntuacion") + ")");
			}
			System.out.println();

		} else {
			System.out.println("Error: Conexión no establecida");
		}
	}

	private static BasicDBObject anyadirDocumento(String nombreLoc,
			String direccion, int puntuacion) {
		
		BasicDBObject b = new BasicDBObject();
		b.put("nombre", nombreLoc);
		b.put("direccion", direccion);
		b.put("puntuacion", puntuacion);
		return b;
		
		
	}
}




