package team_no_team_rocket;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;
import java.util.Date;
import java.util.TreeMap;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.ListModel;

import javafx.scene.layout.Border;

public class PanelInicio extends JPanel {
	
	private static PanelInicio pInicio;
	private static JList lListaLocales;
	private static JList ltemporal;

	private static JScrollPane spListas;
	private static TreeMap rankingBares;
	
	private static int posicionActual;
	private static JButton bVolver = new JButton( "Volver" );
	
	
	public PanelInicio(){
		
		pInicio = this;		
	    BDNeo4j bd = new BDNeo4j();
	    ArrayList<Local> alLocales = bd.getTodosLosLocales();
		DefaultListModel<Local> dlmLocales = new DefaultListModel<>();
		for(Local l : alLocales) dlmLocales.addElement(l);
		ltemporal = new JList<Local>(dlmLocales);
		lListaLocales = ltemporal;
		lListaLocales.setBackground(Color.white);
		lListaLocales.setFixedCellHeight(50);
		lListaLocales.setFixedCellWidth(390);
		Util.cambiaRenderer(lListaLocales,0);
		spListas = new JScrollPane(lListaLocales);
		pInicio.setLayout(new BorderLayout());
		pInicio.add(spListas,"Center");
		pInicio.validate();
		pInicio.repaint();
		lListaLocales.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(java.awt.event.MouseEvent e) {

				if (e.getClickCount()==1){
					lListaLocales.locationToIndex(e.getPoint());
				}
				if (e.getClickCount()==2){
					if (lListaLocales.getSelectedIndex()!= -1) {
						posicionActual = lListaLocales.locationToIndex(e.getPoint());
						Util.abrirInfo(dlmLocales, posicionActual, pInicio ,0, bVolver);
						pInicio.revalidate();
						pInicio.repaint();
						// abre Oferta seleccionada 
					}
				}
			}
			});	
		bVolver.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {	

				vuelvePanelInicio();
				
			}
		});


	}
		public void vuelvePanelInicio(){
			pInicio.removeAll();
			lListaLocales = ltemporal;
			pInicio.setLayout(new BorderLayout());
			pInicio.add(spListas, "Center");
			spListas.revalidate();
			pInicio.revalidate();
			pInicio.getParent().revalidate();
			pInicio.getParent().repaint();

			
		}
		public static void main(String[] args) {
			JFrame v = new JFrame();
			v.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//			DefaultListModel dlmSeleccionar = new DefaultListModel<>();
//			JList lListaBares = new JList<Object>(dlmSeleccionar);
//			
//			// Modificamos la lista para que tenga las características que deseamos	
//			lListaBares.setBackground(Color.LIGHT_GRAY);
//			lListaBares.setFixedCellHeight(50);
//			lListaBares.setFixedCellWidth(390);
////			Util.cambiaRenderer(lListaBares, 0);
//
//		// PRUEBA DE JLIST
//			Local l1 = new Local("Ander", "Zubialde","bar", "1", 1);ImageIcon i1 = new ImageIcon("bin/team_no_team_rocket/fotos/3escobas.jpg");		l1.setFoto(i1);
//			Local l2 = new Local("Ander","Café","bar", "1", 2);		ImageIcon i2 = new ImageIcon("bin/team_no_team_rocket/fotos/badulaque.jpg");l2.setFoto(i2);
//			Local l3 = new Local("Ander", "Terraza","bar", "1", 3);	ImageIcon i3 = new ImageIcon("bin/team_no_team_rocket/fotos/barDeMoe.jpg");l3.setFoto(i3);
//			Local l4 = new Local("Ander","Badulaque","bar", "1", 10);
//			Local l5 = new Local("Ander","El bar de Moe","bar", "1", 10);
//			Local l6 = new Local("Ander","La tasca","bar", "1", 10);
//			// Creación y adición de ofertas
//			Oferta o1 = new Oferta("3x2", 3.0, "3 pintxos por 2" , new Date(), new Date() );
//			Oferta o2 = new Oferta("Desayuno", 4.0, "3 pintxos por 2" , new Date(), new Date() );
//			Oferta o3 = new Oferta("2x1", 2.0, "2 pintxos por 1" , new Date(), new Date() );
//			Oferta o4 = new Oferta("PintxoPote", 3.0, "Pintxo + pote" , new Date(), new Date() );
//			l1.anyadirOferta(o1);
//			l1.anyadirOferta(o2);
//			l2.anyadirOferta(o3);
//			l3.anyadirOferta(o4);
//			//Añadimos los locales a la lista[MIENTRAS NO LOS SAQUEMOS DE NEO4J]
//			dlmSeleccionar.addElement(l1);
//			dlmSeleccionar.addElement(l2);
//			dlmSeleccionar.addElement(l3);
			

			pInicio = new PanelInicio();
			v.setSize(410, 600);
			v.add(pInicio);
			v.setVisible(true);
		}

}
