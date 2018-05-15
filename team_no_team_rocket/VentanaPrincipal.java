package team_no_team_rocket;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

	//Paneles
	private static JPanel pBotonera = new JPanel();
	private static JPanel pCentral = new JPanel();
	//Lista y posiciones
	public static DefaultListModel<Object> dlmSeleccionar;
	private static JList<Object> lListaBares;
	private static int posicionActual;
	private static int posicionSeleccion;
	//Botones	
	private static JButton bRanking = new JButton("Ranking");
	private static JButton bInicio = new JButton("Inicio");
	private static JButton bMapa= new JButton("Mapa");
	private static JButton bPerfil = new JButton("Perfil");
	private static JButton bVolver = new JButton( "Volver" );

	private static JTabbedPane tab = new JTabbedPane(); //Creacion del contenedor de pestañas
	private static PanelMapa panelMapa = new PanelMapa(); //Pestaña del mapa
	private static JPanel usuario = new JPanel(); //TODO pestaña del usuario
	private static JPanel ranking = new JPanel(); //TODO pestaña del ranking
	
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
	
	
	lListaBares.setBackground(Color.LIGHT_GRAY);
	lListaBares.setFixedCellHeight(50);
	lListaBares.setFixedCellWidth(390);

// PRUEBA DE JLIST
	// Creación locales
	Local l1 = new Local("Zubialde","bar", "1", 10);
	Local l2 = new Local("Café","bar", "1", 10);
	Local l3 = new Local("Terraza","bar", "1", 10);
	
	Local l4 = new Local("Badulaque","bar", "1", 10);
	Local l5 = new Local("El bar de Moe","bar", "1", 10);
	Local l6 = new Local("La tasca","bar", "1", 10);
	// Creación imágenes
	ImageIcon i1 = new ImageIcon("team_no_team_rocket.fotos/3escobas.jpg");
	ImageIcon i2 = new ImageIcon("team_no_team_rocket.fotos/3escobas.jpg");
	ImageIcon i3 = new ImageIcon("team_no_team_rocket.fotos/3escobas.jpg");
	// Creación y adición de ofertas
	Oferta o1 = new Oferta("3x2", 3.0, "3 pintxos por 2" , 3600);
	Oferta o2 = new Oferta("Desayuno", 4.0, "3 pintxos por 2" , 3600);
	Oferta o3 = new Oferta("2x1", 2.0, "2 pintxos por 1" , 3600);
	Oferta o4 = new Oferta("PintxoPote", 3.0, "Pintxo + pote" , 3600);
	l1.anyadirOferta(o1);
	l1.anyadirOferta(o2);
	l2.anyadirOferta(o3);
	l3.anyadirOferta(o4);
	//Añadimos los locales a la lista
	dlmSeleccionar.addElement(l1);
	dlmSeleccionar.addElement(l2);
	dlmSeleccionar.addElement(l3);
	
	//Añadimos las ofertas a la lista [ASÍ ES COMO DEBIERA SER PERO DE MOMENTO VAMOS A DEJARLO COMO ESTABA HASTA QUE TENGAMOS EL ListCellRenderer
//	dlmSeleccionar.addElement(l1.getListaOfertas().get(0));
//	dlmSeleccionar.addElement(l2.getListaOfertas().get(0));
//	dlmSeleccionar.addElement(l3.getListaOfertas().get(0));

	//Asignamos la lista al panel central y añadimos en el contenedor de pestañas
	pCentral.add(lListaBares);	
	tab.addTab("Usuario", null, usuario, "No hace nada");
	tab.addTab("Inicio", null, pCentral, "Lista de bares en tiempo real");
	tab.addTab("Ranking", null, ranking, "No hace nada");
	tab.addTab("Mapa", null, panelMapa, "Mapa de Deusto");

	JScrollPane spListas = new JScrollPane(pCentral);
	add(spListas, "Center");

	this.getContentPane().add(tab);
	
	
		// Eventos de JList
	lListaBares.addMouseListener(new MouseAdapter() {

	@Override
	public void mouseClicked(java.awt.event.MouseEvent e) {

		if (e.getClickCount()==1){
			posicionSeleccion = lListaBares.locationToIndex(e.getPoint());
		}
		if (e.getClickCount()==2){
			if (lListaBares.getSelectedIndex()!= -1) {
				posicionActual = lListaBares.locationToIndex(e.getPoint());
				abrirOferta(posicionActual, VentanaPrincipal.this); // abre Oferta seleccionada 
			}
		}
	}
	});	
		
// Este boton no sirve pero lo dejo para un futuro
	bVolver.addActionListener(new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent e) {	
			pCentral.removeAll();
			getContentPane().remove(pCentral);
			pCentral.add(lListaBares);
			getContentPane().add(pCentral, BorderLayout.CENTER);
			getContentPane().revalidate();	
			getContentPane().repaint();
		}
	});
		
	}
	
	
	
	public static void main(String[] args) throws SQLException {
		//COMPROBAMOS EL USUARIO ANTES DE INICIAR LA VENTANA
		boolean correcto = false;
//		while (!correcto){
//			correcto = Util.comprobarUsuario();
//		}
		//Util.comprobarUsuario();
		VentanaPrincipal vp = new VentanaPrincipal();
		vp.setVisible(true);
		
//		System.out.println("select nombre, contraseña from usuario where "
//				+ "usuario.nombre = '" + "alvar"+ "'");
//		
				
	}
	
	/** Método que abre el local y la oferta correspondiente en la ventana 
	 * @param posActual recibe la posiciónActual de la JList en la que se clica 2 veces
	 */
	public static void abrirOferta( int posActual, VentanaPrincipal vp ){
		JLabel lOferta = new JLabel();
		if (dlmSeleccionar.getElementAt(posActual) instanceof Local){
			Local l = (Local) dlmSeleccionar.getElementAt(posActual);
			lOferta.setText(l.getNombre());
//			lOferta.setIcon(l.getFoto());
			pCentral.removeAll();
			vp.getContentPane().remove(pCentral);
			pCentral.add(bVolver);
			pCentral.add(lOferta);
			vp.getContentPane().add(pCentral, BorderLayout.CENTER);
			vp.getContentPane().revalidate();
			vp.getContentPane().repaint();
			
		}
		
		
	}
}


