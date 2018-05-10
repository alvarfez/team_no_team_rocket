package team_no_team_rocket;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.ListUI;

import com.sun.glass.events.MouseEvent;
import com.sun.glass.events.WindowEvent;

public class VentanaPrincipal extends JFrame{
	
	//Atributos de ventana
	public static DefaultListModel<Object> dlmSeleccionar;
	private static JPanel pBotonera = new JPanel();
	private static JPanel pCentral = new JPanel();
	
	private static JPanel pSuperior = new JPanel();
	private static JPanel pMedio = new JPanel();
	private static JPanel pInferior = new JPanel();
	
	private static JList<Object> lListaBares;
	private static int posicionActual;
	private static int posicionSeleccion;
	
	
	private static JButton bRanking = new JButton("Ranking");
	private static JButton bInicio = new JButton("Inicio");
	private static JButton bMapa= new JButton("Mapa");
	private static JButton bPerfil = new JButton("Perfil");
	
	private static JTextArea b1 = new JTextArea("Bar1");
	private static JTextArea b2 = new JTextArea("Bar2");
	private static JTextArea b3 = new JTextArea("Bar3");
	
	public VentanaPrincipal() throws SQLException{
	
	//Aquí configuramos lo básico de la ventana
	this.setTitle("BilboPintxo");
	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	setSize(410,600);
	setLocation(120,0);
	
	// Creamos la lista y el modelo de lista
	
	dlmSeleccionar = new DefaultListModel<>();
	lListaBares = new JList<Object>(dlmSeleccionar);
	
	// Modificamos la lista para que tenga las características que deseamos
	
//	ListUI lUI = new ListUI();
	
	lListaBares.setBackground(Color.cyan);
//	lListaBares.setUI();
	lListaBares.setFixedCellHeight(50);
	lListaBares.setFixedCellWidth(100);
	// PRUEBA DE JLIST
	Local l1 = new Local("Zubialde","bar", "1", 10);
	Local l2 = new Local("Café","bar", "1", 10);
	Local l3 = new Local("Terraza","bar", "1", 10);
	
	Local l4 = new Local("Badulaque","bar", "1", 10);
	Local l5 = new Local("El bar de Moe","bar", "1", 10);
	Local l6 = new Local("La tasca","bar", "1", 10);

	JPanel p1 = new JPanel();
	JPanel p2 = new JPanel();
	JPanel p3 = new JPanel();
	
	p1.setLayout(new GridLayout(2,3));
	p2.setLayout(new GridLayout(2,3));
	p3.setLayout(new GridLayout(2,3));
	
	p1.add(new JTextArea(l1.getNombre()));
	System.out.println(p1.toString());
	
	dlmSeleccionar.addElement(p1);
	dlmSeleccionar.addElement(p1);
	dlmSeleccionar.addElement(p1);
	
	
//	ArrayList<Local> locales = new ArrayList<>();
//	locales.add(l1);locales.add(l2);locales.add(l3);
//
//	for (Local l : locales){
//		dlmSeleccionar.addElement(l.toString());
//	}
//	
	
	// FIN PRUEBA
	
	
	
	//Asignamos los botones a la botonera y la botonera al sur del panel principal
	
	pBotonera.add(bPerfil);
	pBotonera.add(bRanking);
	pBotonera.add(bInicio);
	pBotonera.add(bMapa);
	
	
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
	
	
	getContentPane().add(lListaBares, "Center");  // metemos la lista de locales/ofertas en el panel central
//	getContentPane().add(pCentral,"Center");
	getContentPane().add(pBotonera, "North");
	
	
	
		// Eventos de JList
	lListaBares.addMouseListener(new MouseListener() {

	@Override
	public void mouseClicked(java.awt.event.MouseEvent e) {

		if (e.getClickCount()==1){
			posicionSeleccion = lListaBares.locationToIndex(e.getPoint());
		}
		if (e.getClickCount()==2){
			if (lListaBares.getSelectedIndex()!= -1) {
				posicionActual = lListaBares.locationToIndex(e.getPoint());
			}
		}
	}
	@Override
	public void mouseEntered(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	});	
	lListaBares.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
			}
		});
		
	
	}
	
	public static void main(String[] args) throws SQLException {
		//COMPROBAMOS EL USUARIO ANTES DE INICIAR LA VENTANA
		boolean correcto = false;
//		while (!correcto){
//			correcto = Util.comprobarUsuario();
//		}
		Util.comprobarUsuario();
		VentanaPrincipal vp = new VentanaPrincipal();
		vp.setVisible(true);
		
//		System.out.println("select nombre, contraseña from usuario where "
//				+ "usuario.nombre = '" + "alvar"+ "'");
//		
		
		
		
		
	}
}
