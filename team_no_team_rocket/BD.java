package team_no_team_rocket;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BD {
	private static final String HOST = "ec2-54-235-90-200.compute-1.amazonaws.com";
	private static final String DATABASE = "d1d78lhtepp4ur";
	private static final String USERNAME = "neytofbzjlytrh";
	private static final String PASSWORD = "f90c526f893be259ef79626e262b31e7e8b668d5c1d789017b1fc814b13245d9";
	private static final String PORT = "5432";
	private static final String DBURL = "jdbc:postgresql://" + HOST + ":" + PORT + "/" + DATABASE + "?sslmode=require";
	
	public static Connection getConnection() throws SQLException{
		Connection conn = null;
		try{
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection(DBURL, USERNAME, PASSWORD);
		}
		catch (ClassNotFoundException ex){
			ex.printStackTrace();		
		}
		
		return conn;
		
	};
	
	public static void main(String[] args) throws SQLException {
		
		//CREACION DE LA TABLA USUARIOS CON EL ADMIN NO EJECUTAR MAS VECES
		/*
		Connection connection = getConnection();
        Statement stmt = connection.createStatement();
        // stmt.executeUpdate("DROP TABLE IF EXISTS ticks");  // Si se quiere iniciar la tabla desde 0
        try {
        	stmt.executeUpdate("create table usuario (nombre varchar(15), password varchar(10))");
        	stmt.executeUpdate("insert into usuario values('admin', 'admin')");
        } catch (SQLException e) {
        	e.printStackTrace();
        }
        */
	
	}
}

