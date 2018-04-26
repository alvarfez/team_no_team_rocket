package team_no_team_rocket;

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
	public static void main(String[] args) {
		 int telefono = 645456789;
		 boolean es = EntradaTlfno(telefono);
		 if(es){
			 System.out.println("Funciona");
		 }
		 else{
			 System.out.println("No funciona");
		 }
		 
	}
}
