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
	//Paneles de tab
	private static JTabbedPane tab = new JTabbedPane(); //Creacion del contenedor de pestañas
	private static PanelMapa panelMapa = new PanelMapa(); //Pestaña del mapa
	private static JPanel usuario = new PanelUsuario(); //TODO pestaña del usuario
	private static JPanel ranking; //TODO pestaña del ranking
	private static JPanel inicio = new JPanel(); //TODO pestaña del ranking
	//Atributos Oferta pulsada
	private static JPanel pOferta = new JPanel();
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
	cambiaRenderer(lListaBares);

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
	
	//Añadimos los locales a la lista
	dlmSeleccionar.addElement(l1);
	dlmSeleccionar.addElement(l2);
	dlmSeleccionar.addElement(l3);
	
	ranking = new PanelRanking(lListaBares);
	//Añadimos las ofertas a la lista [ASÍ ES COMO DEBIERA SER PERO DE MOMENTO VAMOS A DEJARLO COMO ESTABA HASTA QUE TENGAMOS EL ListCellRenderer
//	dlmSeleccionar.addElement(l1.getListaOfertas().get(0));
//	dlmSeleccionar.addElement(l2.getListaOfertas().get(0));
//	dlmSeleccionar.addElement(l3.getListaOfertas().get(0));

	//Asignamos la lista al panel central y añadimos en el contenedor de pestañas
	pCentral.add(lListaBares);	
	JScrollPane spListas = new JScrollPane(pCentral);
	inicio.add(spListas);
	tab.addTab("Usuario", null, usuario, "No hace nada");
	tab.addTab("Inicio", null, inicio, "Lista de bares en tiempo real");
	tab.addTab("Ranking", null, ranking, "No hace nada");
	tab.addTab("Mapa", null, panelMapa, "Mapa de Deusto");

	
	

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
				abrirOferta(posicionActual, VentanaPrincipal.inicio); // abre Oferta seleccionada 
			}
		}
	}
	});	
		
// Vuelve desde la oferta al panel inicio
	bVolver.addActionListener(new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent e) {	
			pCentral.removeAll();
			inicio.remove(pCentral);
			pCentral.add(lListaBares);
			inicio.add(pCentral, BorderLayout.CENTER);
			inicio.revalidate();	
			inicio.repaint();
		}
	});
		
	}
	
	
	
	public static void main(String[] args) throws SQLException {
		//COMPROBAMOS EL USUARIO ANTES DE INICIAR LA VENTANA
		boolean correcto = false;
//		while (!correcto){
//			correcto = Util.comprobarUsuario();
//		}
//		Util.comprobarUsuario();
		VentanaPrincipal vp = new VentanaPrincipal();
		vp.setVisible(true);
		
//		System.out.println("select nombre, contraseña from usuario where "
//				+ "usuario.nombre = '" + "alvar"+ "'");
//		
				
	}
	
	/** Método que abre el local y la oferta correspondiente en la ventana 
	 * @param posActual recibe la posiciónActual de la JList en la que se clica 2 veces
	 */
	public static void abrirOferta( int posActual, JPanel panel ){
		if (dlmSeleccionar.getElementAt(posActual) instanceof Local){
			Local l = (Local) dlmSeleccionar.getElementAt(posActual);
			// preparamos panel Oferta
			lNomOferta.setText(l.getListaOfertas().get(0).getNombre());
			lDescOferta.setText(l.getListaOfertas().get(0).getDescripcion());
			lPrecio.setText(l.getListaOfertas().get(0).getPrecio()+" €");
			// FALTA POR IMPLEMENTAR
			lDistOferta.setText("'Distancia hasta usuario'");
			// lDistOferta.setText(getOferta().getDistAUser());
			// Hacemos el display de la oferta
			pOferta.setLayout(new GridLayout(6, 2));
			pOferta.add(atr1);pOferta.add(lNomLocal);
			pOferta.add(atr2);pOferta.add(lNomOferta);
			pOferta.add(atr3);pOferta.add(lDescOferta);
			pOferta.add(atr4);pOferta.add(lPrecio);
			pOferta.add(atr5);pOferta.add(lDistOferta);
			pOferta.add(bVolver);
			// preparamos la foto y el nombre del local
			lFoto.setIcon((l.getFoto()));
			lNomLocal.setText(l.getNombre());
			//Borramos lo que había en el panel
			pCentral.removeAll();
			panel.remove(pCentral);
			//Lo llenamos con nuevo Layout
			pCentral.setLayout(new GridLayout(2, 1));
			pCentral.add(lFoto);
			pCentral.add(pOferta);
			panel.add(pCentral, BorderLayout.CENTER);
			panel.revalidate();
			panel.repaint();	
		}
	}
	public void cambiaRenderer(JList<?> lista){
		lista.setCellRenderer(new DefaultListCellRenderer(){
			JPanel p = null;
			JPanel panelPartido = null;
			JPanel pParaFoto = null;
			JLabel lFoto = null;
			JLabel lNomLocal = null;
			JLabel lNomOferta = null;
			JLabel lDistancia = null;

			@Override
			public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
					boolean cellHasFocus) {
				//Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
				// Creamos el panel que queramos que tenga el display deseado, necesitaremos método probablemente
				if (p==null) p = creaPanelLista();
				p.setOpaque(true);
				p.setBorder(new LineBorder(Color.BLUE));
				// Este es el local que pasamos cada vez
				Local l = (Local) value;
				//Aquí insertamos los datos personalizadosç

				lFoto.setIcon( l.getFoto());
				lNomLocal.setText(l.getNombre());
				lNomOferta.setText(l.getListaOfertas().get(0).getNombre());
				lDistancia.setText("Distancia a usuario");
		          

				//Aquí pone el background a COLOR cuando se selecciona y cuando NO a BLANCO
				if (isSelected){
					pParaFoto.setBackground(Color.blue);
					panelPartido.setBackground(Color.blue);
					p.setBackground(Color.blue);
				} else if (!isSelected){
					pParaFoto.setBackground(Color.white);
					panelPartido.setBackground(Color.white);
					p.setBackground(Color.white);
				}
				
				return p;
			}
			public JPanel creaPanelLista(){

				p = new JPanel();
				lNomLocal = new JLabel();
				lNomOferta = new JLabel();
				lFoto = new JLabel();
				lDistancia = new JLabel();
				panelPartido = new JPanel();
				pParaFoto = new JPanel();
				p.setLayout(new GridLayout(1,3));
				panelPartido.setLayout(new GridLayout(2,1));
				pParaFoto.add(lFoto);		
				p.add(pParaFoto);
				panelPartido.add(lNomLocal);
				panelPartido.add(lNomOferta);
				p.add(panelPartido);
				p.add(lDistancia);
				return p;
			}
		});
	}
}