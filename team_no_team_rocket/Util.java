package team_no_team_rocket;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTabbedPane;
import javax.swing.ListModel;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_COLOR_BURNPeer;

public class Util {

	//Atributos Info
	private static JPanel pInfo = new JPanel();
	private static JLabel lNomLocal = new JLabel();
	private static JLabel lNomOferta = new JLabel();
	private static JLabel lDescOferta = new JLabel();
	private static JLabel lPrecio = new JLabel();
	private static JLabel lFoto = new JLabel();
	private static JLabel lDistOferta = new JLabel();
	// Labels para indicación en oferta
	private static JLabel atr1 = new JLabel("Local: ");
	private static JLabel atr2 = new JLabel("Oferta: ");
	private static JLabel atr3 = new JLabel("Descripción: ");
	private static JLabel atr4 = new JLabel("Precio: ");
	private static JLabel atr5 = new JLabel("Distancia: ");
	private static JButton bVotar;
	private static JSlider js;
	private static Double cont;
	


	private static int numCod = 0;


	/** Metodo que comprueba si el numero de caracteres del telefono es correcto y devuelve 
	 * true. En caso de ser incorrecto devuelve False y en caso de ser null lo completa 
	 * aleatoriamente como nosotros queremos.
	 * @param telefono 
	 */
	public static void LimitarCaracteres(){
		
	}
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
	/** Método que genera un código particular para cada oferta con las 3 primeras letras del nombre y un numero
	 * @param str el nombre de la oferta
	 * @return el código
	 */
	public static String obtenerCodigo(String str){
		String provisional = str.substring(0,3) + numCod;
		numCod ++;
		return provisional;
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
			String s = rs.getString("password");
			//Si el string es igual que la contraseña introducida devuelve true
			if(s.equals(password_input) ){
				System.out.println("Contraseña correcta");
				return true;
			}else{
				System.out.println("Contraseña incorrecta");
				return false; //sino devuelve false
			}
	
		}
		//TODO introducir manera de saber si el usuario no está creado y de permitir registrarse
		return false;		
		
		
	}
	
	/** Pasándole un JList convierte el render al deseado dependiendo de si es Inicio o Ranking
	 * @param lista JList donde está la lista a la q hay q cambiar el render
	 * @param modelo 1--> modelo para Ranking 0--> modelo para Inicio
	 */
	public static void cambiaRenderer(JList<?> lista, int modelo){
		lista.setCellRenderer(new DefaultListCellRenderer(){
			JPanel p = null;
			JPanel panelPartido = null;
			JPanel pParaFoto = null;
			JPanel pParaNumero = null;
			JLabel lFoto = null;
			JLabel lNomLocal = null;
			JLabel lNomOferta = null;
			JLabel lDistancia = null;
			JLabel lNumero = null;
			@Override
			public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
					boolean cellHasFocus) {
				//Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
				// Creamos el panel que queramos que tenga el display deseado, necesitaremos método probablemente
				if (p==null){
					if (modelo ==0){p = creaPanelLista(0);
					}else if (modelo==1) { p = creaPanelLista(1);}
				}
					p.setOpaque(true);
					p.setBorder(new LineBorder(Color.BLUE));
					// Este es el local que pasamos cada vez
					Local l = (Local) value;
					//Aquí insertamos los datos personalizadosç
					if (modelo == 1){
						lNumero.setText(index+1+"");
					}
					lFoto.setIcon( l.getFoto());
					lNomLocal.setText(l.getNombre());
					//lNomOferta.setText(l.getListaOfertas().get(0).getNombre());
					lDistancia.setText("Distancia a usuario");


					//Aquí pone el background a COLOR cuando se selecciona y cuando NO a BLANCO
					if (isSelected){
						pParaNumero.setBackground(Color.blue);
						pParaFoto.setBackground(Color.blue);
						panelPartido.setBackground(Color.blue);
						p.setBackground(Color.blue);
					} else if (!isSelected){
						if (modelo==1){
							pParaNumero.setBackground(Color.pink);
							pParaFoto.setBackground(Color.pink);
							panelPartido.setBackground(Color.pink);
							p.setBackground(Color.pink);
						}else if (modelo==0){
							pParaNumero.setBackground(Color.white);
							pParaFoto.setBackground(Color.white);
							panelPartido.setBackground(Color.white);
							p.setBackground(Color.white);
						}
					}
					return p;
				
			}
			public JPanel creaPanelLista(int modelo){
				
				p = new JPanel();
				lNomLocal = new JLabel();
				lNomOferta = new JLabel();
				lFoto = new JLabel();
				lDistancia = new JLabel();
				panelPartido = new JPanel();
				pParaFoto = new JPanel();
				pParaNumero = new JPanel();
				lNumero = new JLabel();
				panelPartido.setLayout(new GridLayout(2,1));
				if (modelo == 0){ 
					p.setLayout(new GridLayout(1,3));
				}else if (modelo ==1){
					p.setLayout(new GridLayout(1,4));
					pParaNumero.setLayout(new GridLayout(1,2));
					lNumero.setFont(new Font("negrita",Font.PLAIN,14));
					pParaNumero.add(lNumero);
					pParaNumero.add(lNomLocal);
					p.add(pParaNumero);
				}
				pParaFoto.add(lFoto);		
				p.add(pParaFoto);
				if (modelo == 0){
					panelPartido.add(lNomLocal);
					panelPartido.add(lNomOferta);
					p.add(panelPartido);
				}
				p.add(lDistancia);
				return p;
			}
		});
	}

	/** Método que abre el local y la oferta correspondiente en la ventana 
	 * @param posActual recibe la posiciónActual de la JList en la que se clica 2 veces
	 * @param modelo 1 --> ranking / 0 --> inicio 
	 */
	public static void abrirInfo( ListModel dlmSeleccionar, int posActual, JPanel panel, int modelo, JButton bVolver){

		if (dlmSeleccionar.getElementAt(posActual) instanceof Local){
			Local l = (Local) dlmSeleccionar.getElementAt(posActual);
			pInfo.removeAll();
			if (modelo==0){
				// preparamos panel Oferta
				BDNeo4j bd = new BDNeo4j();
				ArrayList<Oferta> alOfertas = bd.getOfertas(l.getCodBar());
				for(Oferta o : alOfertas){ 
					if (o.isActiva()){
						lNomOferta.setText(o.getNombre());
						lDescOferta.setText(o.getDescripcion());
						lPrecio.setText(o.getPrecio()+" €");
					}
				}

				// FALTA POR IMPLEMENTAR
				lDistOferta.setText("'Distancia hasta usuario'");
				// lDistOferta.setText(getOferta().getDistAUser());
				// Hacemos el display de la oferta
				pInfo.setLayout(new GridLayout(6, 2));
				pInfo.add(atr1);pInfo.add(lNomLocal);
				pInfo.add(atr2);pInfo.add(lNomOferta);
				pInfo.add(atr3);pInfo.add(lDescOferta);
				pInfo.add(atr4);pInfo.add(lPrecio);
				pInfo.add(atr5);pInfo.add(lDistOferta);
				pInfo.add(bVolver);
			}
			// preparamos la foto y el nombre del local
			lFoto.setIcon((l.getFoto()));
			lNomLocal.setText(l.getNombre());
			//Borramos lo que había en el panel
			panel.removeAll();
			//Lo llenamos con nuevo Layout
			panel.setLayout(new GridLayout(2, 1));
			panel.add(lFoto);
			if (modelo == 0){
				panel.add(pInfo);
			} else if (modelo == 1) {
				bVotar = new JButton("Votar");
				js = new JSlider(JSlider.HORIZONTAL, 0, 5, 2);
				js.setMajorTickSpacing(5);
				js.setMinorTickSpacing(0);
				js.setPaintTicks(true);
				js.setPaintLabels(true);
				
				pInfo.add(lNomLocal);
				pInfo.add(bVolver);
				pInfo.add(js);
				pInfo.add(bVotar);
				panel.add(pInfo);
				
				js.addChangeListener(new ChangeListener() {
					
					@Override
					public void stateChanged(ChangeEvent e) {
						JSlider source = (JSlider)e.getSource();
						if (!source.getValueIsAdjusting()) {
							cont = (double)source.getValue();
						
					}
				}}
				);
				
				bVotar.addActionListener(new ActionListener() {	
					@Override
					public void actionPerformed(ActionEvent e) {
						BDMongo bd = new BDMongo();
						Puntuacion p = new Puntuacion();
						p.sumaPuntuacion(cont);
						bd.actualizarPuntuacion(l.getCodBar(), p);
						
						
					}
				});
			}
			panel.revalidate();
		}		
	}

}		
