package team_no_team_rocket;

import java.awt.Color;
import java.util.TreeMap;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListModel;

public class PanelRanking extends JPanel {

	private static JList lpanel;
	private static JScrollPane spListas;
	private static TreeMap rankingBares;
	
	public PanelRanking(ListModel<Object> lista){
		
		BDMongo bdMongo = new BDMongo();
		rankingBares = bdMongo.obtenerRankingBares();
	// AQUÍ CON EL TREEMAP SE SACAN LOS LOCALES DE NEO4J 
		
		lpanel = new JList<Object>(lista);
		lpanel.setBackground(Color.white);
		lpanel.setFixedCellHeight(50);
		lpanel.setFixedCellWidth(390);
		Util.cambiaRenderer(lpanel,1);
		spListas = new JScrollPane(lpanel);
		this.add(spListas);
		this.validate();
		this.repaint();

	}
	
}
