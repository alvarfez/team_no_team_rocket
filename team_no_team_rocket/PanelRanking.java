package team_no_team_rocket;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.util.TreeMap;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListModel;

public class PanelRanking extends JPanel {
	
	private static PanelRanking pRanking;
	private static JList lpanel;
	private static JScrollPane spListas;
	private static TreeMap rankingBares;
	
	private static int posicionActual;
	private static int posicionSeleccion;
	private static JButton bVolver = new JButton( "Volver" );

	
	public PanelRanking(ListModel<Object> lista){

		pRanking = this;
		BDMongo bdMongo = new BDMongo();
		rankingBares = bdMongo.obtenerRankingBares();
	// AQU� CON EL TREEMAP SE SACAN LOS LOCALES DE NEO4J 
		
		lpanel = new JList<Object>(lista);
		lpanel.setBackground(Color.white);
		lpanel.setFixedCellHeight(50);
		lpanel.setFixedCellWidth(390);
		Util.cambiaRenderer(lpanel,1);
		spListas = new JScrollPane(lpanel);
		pRanking.add(spListas);
		pRanking.validate();
		pRanking.repaint();
		lpanel.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(java.awt.event.MouseEvent e) {

				if (e.getClickCount()==1){
					posicionSeleccion = lpanel.locationToIndex(e.getPoint());
				}
				if (e.getClickCount()==2){
					if (lpanel.getSelectedIndex()!= -1) {
						posicionActual = lpanel.locationToIndex(e.getPoint());
						Util.abrirInfo(lista, posicionActual, pRanking ,1, bVolver); 
						pRanking.revalidate();
						pRanking.repaint();
						// abre Oferta seleccionada 
					}
				}
			}
			});	
		bVolver.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {	

				pRanking.removeAll();
//				pRanking = new PanelRanking(VentanaPrincipal.dameModeloLista());
				vuelvePanelRanking(VentanaPrincipal.dameModeloLista());
				pRanking.revalidate();	
				pRanking.repaint();
				System.out.println("Lo borro pero no pinto el panel de nuevo en el tab");
			}
		});
		
	}
	public void vuelvePanelRanking(ListModel<Object> lista){
		lpanel = new JList<Object>(lista);
		lpanel.setBackground(Color.white);
		lpanel.setFixedCellHeight(50);
		lpanel.setFixedCellWidth(390);
		Util.cambiaRenderer(lpanel,1);
		spListas.add(lpanel);
		pRanking.add(spListas);
		pRanking.validate();
		pRanking.repaint();
		
	}
	
}
