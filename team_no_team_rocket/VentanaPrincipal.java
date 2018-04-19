package team_no_team_rocket;


import javax.swing.*;

public class VentanaPrincipal extends JFrame{
	
	//Atributos de ventana
	private static JPanel pBotonera = new JPanel();
	private static JList<Local> lListaBares = new JList();
	private static JButton bAjustes = new JButton("Ajustes");
	private static JButton bRanking = new JButton("Ranking");
	private static JButton bInicio = new JButton("Inicio");
	private static JButton bBuscar = new JButton("Buscar");
	private static JButton bPerfil = new JButton("Perfil");
	
	public VentanaPrincipal(){
	
	//Aquí configuramos lo básico de la ventana
	this.setTitle("BilboPintxo");
	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	setSize(410,600);
	setLocation(120,0);
	
	//Asignamos los botones a la botonera y la botonera al sur del panel principal
	
	pBotonera.add(bAjustes);
	pBotonera.add(bRanking);
	pBotonera.add(bInicio);
	pBotonera.add(bBuscar);
	pBotonera.add(bPerfil);
	getContentPane().add(pBotonera, "South");
	
	
	}
	
	public static void main(String[] args) {
		VentanaPrincipal vp = new VentanaPrincipal();
		vp.setVisible(true);
	}
}
