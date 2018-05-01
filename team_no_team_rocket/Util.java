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
	
		
}