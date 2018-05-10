package team_no_team_rocket;

import java.sql.*;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class Util {
	
	
	/** Metodo que comprueba si el numero de caracteres del telefono es correcto y devuelve 
	 * true. En caso de ser incorrecto devuelve False y en caso de ser null lo completa 
	 * aleatoriamente como nosotros queremos.
	 * @param telefono 
	 */
	public static boolean EntradaTlfno(Integer telefono){
		if(telefono == null){
			//TODO generar lista bares para ir sumando +1
			return false;
		}			
		else{				
			if(telefono.toString().length() != 9){
				System.out.println("El numero de telefono no es correcto. Debe tener 9 dígitos");
				return false;
			}				
			else{
				System.out.println("Dato introducido correctamente");
				return true;
				}
			}
			
	 		
	 	}

	/** MÉTODO BASADO EN CÓDIGO DE WEB: https://medium.com/@manuelmato/c%C3%B3mo-validar-un-dni-en-java-6d7ce7d764aa
	 * Entrada robusta para DNIs, devuelve true el dni introducido tiene una letra y 8 numeros, false si no
	 * @param dni --> String del dni 
	 * @return
	 */
	public static boolean entradaDNI( String dni ){
		String letraMayuscula = "";
		if (dni.length()!=9||Character.isLetter(dni.charAt(8))==false){
			return false;
		}
		letraMayuscula = dni.substring(8).toUpperCase();
		if (soloNumeros(dni) == true){
			return true;
		}else{
			return false;
			
		}
}
	
	/**MÉTODO BASADO EN CÓDIGO DE WEB: https://medium.com/@manuelmato/c%C3%B3mo-validar-un-dni-en-java-6d7ce7d764aa
	 * Comprueba que el DNI es
	 * @param dni --> String DNI
	 * @return
	 */
	public static boolean soloNumeros(String dni){
		int i,j = 0;
		String numero = "";
		String miDNI = "";
		String[] nums = { "0","1","2","3","4","5","6","7","8","9"};
		
		for (i=0; i<dni.length()-1;i++){
			numero = dni.substring(i, i+1);
		
			for (j=0; j<nums.length;j++){
				if (numero.equals(nums[j])){
					miDNI += nums[j];
				}
			}
		}
		if(miDNI.length() != 8){
			return false;	
		}else{
			return true;
		}
	}
	
	/** Método que lo único que hace es devolver la hora si está entre 0 y 24 y si no devuelve -1
	 * @param hora
	 * @return
	 */
	public static int entradaHoras(int hora){
		if (hora < 24 && hora >= 0){
			return hora;
		} else {
			return -1;
		}		
	}
	
	/** Metodo que comprueba si el usuario introducido existe (en un futuro) y si la 
	 * contraseña es correcta
	 * @return True si es correcta o False si es incorrecta
	 * @throws SQLException
	 */
	public static boolean comprobarUsuario() throws SQLException{
		//USUARIO: admin PASSWORD: admin
		Connection connection = null;
		//Obtenemos el nombre de usuario 
		String usuario_input = JOptionPane.showInputDialog("Introduce el nombre de usuario");
		//Obtenemos la contraseña del usuario
		String password_input = JOptionPane.showInputDialog("Introduce la contraseña");
		try{
			//Generamos la conexion --> VER: BD.getConnection (aunque es un coñazo)(funciona)
			 connection = BD.getConnection();	
		} catch (SQLException ex){
			ex.printStackTrace();
			System.err.println(ex.getClass().getName()+ ": " + ex.getMessage());
			System.out.println("Ha fallado la conexion. Comprueba el error en la consola");
		}
		//Creamos el statement con la consulta:
		// select nombre, password from usuario where usuario.nombre = 'admin'
		Statement stmt = connection.createStatement(); 
		ResultSet rs = stmt.executeQuery("select nombre, password from usuario where "
				+ "usuario.nombre = '" + usuario_input + "'");
		
		//hacemos un registro por todas las respuestas y las imprimimos en pantalla
		while(rs.next()){
			//Imprime perfectamente "admin" 
			System.out.println(rs.getString("password"));
		}
		return false;
		
		
		
	}
	
		
}