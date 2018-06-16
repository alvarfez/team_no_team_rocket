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
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.ListUI;

import com.sun.glass.events.MouseEvent;
import com.sun.glass.events.WindowEvent;

import javafx.scene.layout.Border;

public class VentanaPrincipal extends JFrame{
	
//Atributos de ventana
	//Lista y posiciones
	public static DefaultListModel<Object> dlmSeleccionar;
	private static JList<Object> lListaBares;
	private static int posicionActual;
	private static int posicionSeleccion;
	//Paneles de tab
	private static JTabbedPane tab = new JTabbedPane(); //Creacion del contenedor de pestañas
	private static PanelMapa panelMapa = new PanelMapa(); //Pestaña del mapa
	private static PanelAjustes ajustesLocal;  //TODO tab del usuario
	private static PanelAjustes ajustesUsuario; //TODO tab del usuario
	private static PanelRanking ranking; //TODO tab del ranking
	private static PanelInicio inicio; //TODO tab del inicio

	public VentanaPrincipal( String nombreUsuario ) throws Exception{

		//Configuramos lo básico de la ventana
		this.setTitle("BilboPintxo");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(410,600);
		setLocation(120,0);

		//Creamos los paneles Inicio y Ranking con la lista ya hecha
		inicio = new PanelInicio();
		ranking = new PanelRanking();
		//Añadimos en el contenedor de pestañas y el propio contenedor a la ventana
		tab.addTab("Inicio", null, inicio, "Lista de locales en tiempo real");
		tab.addTab("Ranking", null, ranking, "Top Locales");
		tab.addTab("Mapa", null, panelMapa, "Mapa de Deusto");
		if (new BDMongo().obtenerCategoria(nombreUsuario).equals("local")){
			PanelAjustes.init(1, nombreUsuario); //TODO tab del usuario
			tab.addTab("Ajustes", null, PanelAjustes.getPanel(), "Ajustes de local");
		}else{
			PanelAjustes.init(0, nombreUsuario); 
			tab.addTab("Ajustes", null, PanelAjustes.getPanel(), "Ajustes de usuario");
			//meter panel ajustes de usuario normal
		}
		this.getContentPane().add(tab);
		this.revalidate();
		tab.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				tab.revalidate();
				tab.repaint();
				
			}
		});

	}
	public static void main(String[] args) throws Exception {
		//COMPROBAMOS EL USUARIO ANTES DE INICIAR LA VENTANA
		VentanaPrincipal vp = new VentanaPrincipal("Ander");
		vp.setVisible(true);
		
//		System.out.println("select nombre, contraseña from usuario where "
//				+ "usuario.nombre = '" + "alvar"+ "'");
//					
	}
}