package team_no_team_rocket;


import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.*;

public class VentanaPrincipal extends JFrame{
	
	//Atributos de ventana
	public static DefaultListModel<Object> dlmSeleccionar;
	private static JPanel pBotonera = new JPanel();
	private static JPanel pCentral = new JPanel();
	
	private static JPanel pSuperior = new JPanel();
	private static JPanel pMedio = new JPanel();
	private static JPanel pInferior = new JPanel();
	
	private static JList<Object> lListaBares;
	private static JButton bAjustes = new JButton("Ajustes");
	private static JButton bRanking = new JButton("Ranking");
	private static JButton bInicio = new JButton("Inicio");
	private static JButton bBuscar = new JButton("Buscar");
	private static JButton bPerfil = new JButton("Perfil");
	
	private static JTextArea b1 = new JTextArea("Bar1");
	private static JTextArea b2 = new JTextArea("Bar2");
	private static JTextArea b3 = new JTextArea("Bar3");
	
	public VentanaPrincipal(){
	
	//Aquí configuramos lo básico de la ventana
	this.setTitle("BilboPintxo");
	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	setSize(410,600);
	setLocation(120,0);
	
	//Asignamos los botones a la botonera y la botonera al sur del panel principal
	
	pBotonera.add(bAjustes);
	pBotonera.add(bRanking);
	pBotonera.add(bInicio);
	pBotonera.add(bBuscar);
	pBotonera.add(bPerfil);
	
	//Tenemos tres bares por pantalla
	
	pSuperior.setLayout(new GridLayout(2,2));
	pMedio.setLayout(new GridLayout(2,2));
	pInferior.setLayout(new GridLayout(2,2));
	
	pSuperior.add(b1);
	pMedio.add(b2);
	pInferior.add(b3);
	
	pCentral.setLayout(new GridLayout(3,1));
	pCentral.add(pSuperior, "North");
	pCentral.add(pInferior, "South");
	pCentral.add(pMedio);
	
	
	getContentPane().add(pCentral,"Center");
	getContentPane().add(pBotonera, "South");
	
//	dlmSeleccionar = new DefaultListModel<>();
//	lListaBares = new JList<Object>(dlmSeleccionar);
//	getContentPane().add(lListaBares, "Center");
	
	}
	
	public static void main(String[] args) {
		VentanaPrincipal vp = new VentanaPrincipal();
		vp.setVisible(true);
		
		
		
		
		Local l1 = new Local("Zubialde","Bar", "1", 10);
		Local l2 = new Local("Café","Bar", "1", 10);
		Local l3 = new Local("Terraza","Bar", "1", 10);
		
	
		
		
	}
}
