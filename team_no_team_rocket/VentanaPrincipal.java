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
	public static DefaultListModel<Object> dlmSeleccionar;
	private static JPanel pBotonera = new JPanel();
	private static JPanel pCentral = new JPanel();
	//Lista y posiciones
	//public static DefaultListModel<Object> dlmSeleccionar;
	private static JList<Object> lListaBares;
	private static int posicionActual;
	private static int posicionSeleccion;
	//Botones	
	private static JButton bRanking = new JButton("Ranking");
	private static JButton bInicio = new JButton("Inicio");
	private static JButton bMapa= new JButton("Mapa");
	private static JButton bPerfil = new JButton("Perfil");
	private static JButton bVolver = new JButton( "Volver" );
	
	public VentanaPrincipal() throws SQLException{
	
	//Aqu� configuramos lo b�sico de la ventana
	this.setTitle("BilboPintxo");
	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	setSize(410,600);
	setLocation(120,0);
	
	// Creamos la lista y el modelo de lista
	
	dlmSeleccionar = new DefaultListModel<>();
	lListaBares = new JList<Object>(dlmSeleccionar);
	
	// Modificamos la lista para que tenga las caracter�sticas que deseamos
	
	
	lListaBares.setBackground(Color.LIGHT_GRAY);
	lListaBares.setFixedCellHeight(50);
	lListaBares.setFixedCellWidth(390);

// PRUEBA DE JLIST
	// Creaci�n locales
	Local l1 = new Local("Zubialde","bar", "1", 10);
	Local l2 = new Local("Caf�","bar", "1", 10);
	Local l3 = new Local("Terraza","bar", "1", 10);
	
	Local l4 = new Local("Badulaque","bar", "1", 10);
	Local l5 = new Local("El bar de Moe","bar", "1", 10);
	Local l6 = new Local("La tasca","bar", "1", 10);
	
	ImageIcon i1 = new ImageIcon("team_no_team_rocket.fotos/3escobas.jpg");
	ImageIcon i2 = new ImageIcon("team_no_team_rocket.fotos/3escobas.jpg");
	ImageIcon i3 = new ImageIcon("team_no_team_rocket.fotos/3escobas.jpg");
	
	
	dlmSeleccionar.addElement(l1);
	dlmSeleccionar.addElement(l2);
	dlmSeleccionar.addElement(l3);

	
	
	
	//Asignamos los botones a la botonera y la botonera al sur del panel principal
	pCentral.add(lListaBares);
	
	pBotonera.add(bPerfil);
	pBotonera.add(bRanking);
	pBotonera.add(bInicio);
	pBotonera.add(bMapa);
		
	getContentPane().add(pBotonera, "North");
	add(pCentral, "Center");
	
	
	
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
		
	bInicio.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {	
			//Borramos lo que hay en la aplicaci�n y pintamos el panel de INICIO
			pCentral.removeAll();
			getContentPane().remove(pCentral);
			pCentral.add(lListaBares);
			getContentPane().add(pCentral, BorderLayout.CENTER);
			getContentPane().revalidate();	
			getContentPane().repaint();
			
	
		}
	});
	bMapa.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			//Borramos lo que hay en el centro y ponemos el panel de MAPA
			PanelMapa vm = new PanelMapa();
			pCentral.removeAll();
			getContentPane().remove(pCentral);
			pCentral.add(vm);
			getContentPane().add(pCentral, BorderLayout.CENTER);
			getContentPane().revalidate();
			getContentPane().repaint();
		}
		
	});
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
		

//		System.out.println("select nombre, contrase�a from usuario where "
//				+ "usuario.nombre = '" + "alvar"+ "'");
//		
				
	}
	
	/** M�todo que abre el local y la oferta correspondiente en la ventana 
	 * @param posActual recibe la posici�nActual de la JList en la que se clica 2 veces
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
