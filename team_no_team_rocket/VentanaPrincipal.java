package team_no_team_rocket;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.PopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.ListUI;

import com.sun.glass.events.MouseEvent;
import com.sun.glass.events.WindowEvent;

import javafx.scene.layout.Border;

public class VentanaPrincipal extends JFrame{
	
//Atributos de ventana
	//Paneles
	private static JPanel pBotonera = new JPanel();
	//Lista y posiciones
	public static DefaultListModel<Object> dlmSeleccionar;
	private static JList<Object> lListaBares;
	private static int posicionActual;
	private static int posicionSeleccion;
	//Paneles de tab
	private static JTabbedPane tab = new JTabbedPane(); //Creacion del contenedor de pestañas
	private static PanelMapa panelMapa = new PanelMapa(); //Pestaña del mapa
	private static JPanel ajustesLocal = new PanelAjustes(1); //TODO pestaña del usuario
	private static JPanel ajustesUsuario = new PanelAjustes(0); //TODO pestaña del usuario
	private static JPanel ranking; //TODO pestaña del ranking
	private static JPanel inicio; //TODO pestaña del ranking
	
	public VentanaPrincipal( String nombreUsuario ) throws SQLException{
	
	//Configuramos lo básico de la ventana
	this.setTitle("BilboPintxo");
	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	setSize(410,600);
	setLocation(120,0);
	
	// Creamos la lista y el modelo de lista
	dlmSeleccionar = new DefaultListModel<>();
	lListaBares = new JList<Object>(dlmSeleccionar);
	
	// Modificamos la lista para que tenga las características que deseamos	
	lListaBares.setBackground(Color.LIGHT_GRAY);
	lListaBares.setFixedCellHeight(50);
	lListaBares.setFixedCellWidth(390);
	Util.cambiaRenderer(lListaBares, 0);

// PRUEBA DE JLIST
	// Creación locales
	Local l1 = new Local( "Zubialde","bar", "1", 1);
	Local l2 = new Local("Café","bar", "1", 2);
	Local l3 = new Local( "Terraza","bar", "1", 3);
	Local l4 = new Local("Badulaque","bar", "1", 10);
	Local l5 = new Local("El bar de Moe","bar", "1", 10);
	Local l6 = new Local( "La tasca","bar", "1", 10);
	// Creación imágenes
	ImageIcon i1 = new ImageIcon("bin/team_no_team_rocket/fotos/3escobas.jpg");
	ImageIcon i2 = new ImageIcon("bin/team_no_team_rocket/fotos/badulaque.jpg");
	ImageIcon i3 = new ImageIcon("bin/team_no_team_rocket/fotos/barDeMoe.jpg");
	l1.setFoto(i1);
	l2.setFoto(i2);
	l3.setFoto(i3);
	// Creación y adición de ofertas
	Oferta o1 = new Oferta("3x2", 3.0, "3 pintxos por 2" , new Date(), new Date() );
	Oferta o2 = new Oferta("Desayuno", 4.0, "3 pintxos por 2" , new Date(), new Date() );
	Oferta o3 = new Oferta("2x1", 2.0, "2 pintxos por 1" , new Date(), new Date() );
	Oferta o4 = new Oferta("PintxoPote", 3.0, "Pintxo + pote" , new Date(), new Date() );
	l1.anyadirOferta(o1);
	l1.anyadirOferta(o2);
	l2.anyadirOferta(o3);
	l3.anyadirOferta(o4);
	//Añadimos los locales a la lista[MIENTRAS NO LOS SAQUEMOS DE NEO4J]
	dlmSeleccionar.addElement(l1);
	dlmSeleccionar.addElement(l2);
	dlmSeleccionar.addElement(l3);
	//Creamos los paneles Inicio y Ranking con la lista ya hecha
	inicio = new PanelInicio(dlmSeleccionar);
	ranking = new PanelRanking(dlmSeleccionar);

	//Añadimos en el contenedor de pestañas y el propio contenedor a la ventana
	tab.addTab("Inicio", null, inicio, "Lista de locales en tiempo real");
	tab.addTab("Ranking", null, ranking, "Top Locales");
	tab.addTab("Mapa", null, panelMapa, "Mapa de Deusto");
	if (new BDMongo().obtenerCategoria(nombreUsuario).equals("local")){
		tab.addTab("Ajustes", null, ajustesLocal, "Ajustes de local");
	}else{
		tab.addTab("Ajustes", null, ajustesUsuario, "Ajustes de usuario");
		//TODO meter panel ajustes de usuario normal
	}
	this.getContentPane().add(tab);		
	}

	/** Método temporal mientras la lista no salga de neo4J
	 * @param panel
	 */
	public static DefaultListModel dameModeloLista(){
		return dlmSeleccionar;
		
	}
	
	public static void main(String[] args) throws SQLException {
		//COMPROBAMOS EL USUARIO ANTES DE INICIAR LA VENTANA
		VentanaPrincipal vp = new VentanaPrincipal("Ander");
		vp.setVisible(true);
		
//		System.out.println("select nombre, contraseña from usuario where "
//				+ "usuario.nombre = '" + "alvar"+ "'");
//					
	}
}