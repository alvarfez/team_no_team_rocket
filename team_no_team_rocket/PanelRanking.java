package team_no_team_rocket;

import java.util.TreeMap;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListModel;

public class PanelRanking extends JPanel {

	private static JList panel;
	private static JScrollPane spListas;
	private static TreeMap rankingBares;
	
	public PanelRanking(ListModel<Object> lista){
		
		BDMongo bdMongo = new BDMongo();
		rankingBares = bdMongo.obtenerRankingBares();
	// AQUÍ CON EL TREEMAP SE SACAN LOS LOCALES DE NEO4J 
		
		panel = new JList<Object>(lista);
		spListas = new JScrollPane(panel);
		this.add(spListas);
		this.validate();
		this.repaint();

	}
	
}
