package team_no_team_rocket;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.util.TreeMap;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListModel;

public class PanelInicio extends JPanel {
	
	private static PanelInicio pInicio;
	private static JList lpanel;
	private static JScrollPane spListas;
	private static TreeMap rankingBares;
	
	private static int posicionActual;
	private static int posicionSeleccion;
	private static JButton bVolver = new JButton( "Volver" );
	
	
	public PanelInicio(ListModel<Object> lista){
		
		pInicio = this;		
		lpanel = new JList<Object>(lista);
		lpanel.setBackground(Color.white);
		lpanel.setFixedCellHeight(50);
		lpanel.setFixedCellWidth(390);
		Util.cambiaRenderer(lpanel,0);
		spListas = new JScrollPane(lpanel);
		pInicio.add(spListas);
		pInicio.validate();
		pInicio.repaint();
		lpanel.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(java.awt.event.MouseEvent e) {

				if (e.getClickCount()==1){
					posicionSeleccion = lpanel.locationToIndex(e.getPoint());
				}
				if (e.getClickCount()==2){
					if (lpanel.getSelectedIndex()!= -1) {
						posicionActual = lpanel.locationToIndex(e.getPoint());
						Util.abrirInfo(lista, posicionActual, pInicio ,0, bVolver);
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

				pInicio.removeAll();
//				pInicio = new PanelInicio(VentanaPrincipal.dameModeloLista());
				vuelvePanelInicio(VentanaPrincipal.dameModeloLista());
				pInicio.revalidate();	
				pInicio.repaint();
				System.out.println("Lo borro pero no vuelvo bien");
			}
		});


	}
		public void vuelvePanelInicio(ListModel<Object> lista){
			lpanel = new JList<Object>(lista);
			lpanel.setBackground(Color.white);
			lpanel.setFixedCellHeight(50);
			lpanel.setFixedCellWidth(390);
			Util.cambiaRenderer(lpanel,0);
			spListas.add(lpanel);
			pInicio.add(spListas);
			pInicio.validate();
			pInicio.repaint();
			
		}
}
