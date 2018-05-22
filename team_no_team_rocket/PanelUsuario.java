package team_no_team_rocket;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class PanelUsuario extends JPanel {
	
	private JPanel pSuperior = new JPanel();
	private JPanel pSupCentral = new JPanel();
	private JPanel pInferior = new JPanel();
	private JPanel pInfCentral = new JPanel();
	private JPanel pSupIzq = new JPanel();
	private JPanel pSupDcha = new JPanel();
	private JPanel pInfIzq = new JPanel();
	private JPanel pInfDcha = new JPanel();
	private JPanel panelvacio = new JPanel();
	private JLabel login = new JLabel("Login");
	private JLabel registrar = new JLabel("Registrar");
	private JTextField nombreTA = new JTextField("      Introduce nombre");
	private JTextField nombreTA2 = new JTextField("     Introduce nombre");
	private JLabel nombre = new JLabel("Nombre");
	private JLabel nombre2 = new JLabel("Nombre");
	private JTextField passwordTA = new JTextField("     Introduce contraseña");
	private JLabel password = new JLabel("Contraseña");
	private JLabel password2 = new JLabel("Contraseña");
	private JPasswordField passwordfield = new JPasswordField();
	private JLabel categoria = new JLabel("Categoria");
	private JComboBox<String> cbCategoria = new JComboBox<>();
	private JButton blogin = new JButton("Login");
	private JButton bregister = new JButton("Registrar");

	
	public PanelUsuario(){
		
		//Paneles para dejar todo bonito
		blogin.setBackground(new Color(82,69,242));
		bregister.setBackground(new Color(82,69,242));
		this.setLayout(new GridLayout(2,1));
		
		pSupIzq.setLayout(new GridLayout(4, 1));
		pSupIzq.add(login);
		pSupIzq.add(nombre);
		pSupIzq.add(password);
		pSupIzq.add(new JPanel());
		
		pSupDcha.setLayout(new GridLayout(4,1,1, 60));
		pSupDcha.add(new JPanel());
		pSupDcha.add(nombreTA);
		pSupDcha.add(passwordfield);
		pSupDcha.add(blogin);
		
		pSupCentral.setLayout(new GridLayout(1,2));
		pSupCentral.add(pSupIzq);
		pSupCentral.add(pSupDcha);
		
		pSuperior.setLayout(new BorderLayout());
		pSuperior.add(new JPanel(), "East");
		pSuperior.add(new JPanel(), "West");
		pSuperior.add(pSupCentral, "Center");
		
		
		pInfIzq.setLayout(new GridLayout(6,1,1,30));
		pInfIzq.add(new JSeparator());
		pInfIzq.add(registrar);
		pInfIzq.add(nombre2);
		pInfIzq.add(password2);
		pInfIzq.add(categoria);
		pInfIzq.add(new JPanel());
		
		pInfDcha.setLayout(new GridLayout(6,1,1,30));
		pInfDcha.add(new JSeparator());
		pInfDcha.add(new JPanel());
		pInfDcha.add(nombreTA2);
		pInfDcha.add(passwordTA);
		pInfDcha.add(cbCategoria);
		pInfDcha.add(bregister);
		
		pInfCentral.setLayout(new GridLayout(1,2));
		pInfCentral.add(pInfIzq);
		pInfCentral.add(pInfDcha);
		
		pInferior.setLayout(new BorderLayout());
		pInferior.add(new JPanel(), "East");
		pInferior.add(new JPanel(), "West");
		pInferior.add(pInfCentral, "Center");
		
		cbCategoria.addItem("Usuario");
		cbCategoria.addItem("Local");
		this.add(pSuperior);
		this.add(pInferior);
		
		
		/**Cada vez que se clicka el boton de registro comprueba y si se puede registra 
		 * un usuario en la base de datos de Mongo de Usuarios
		 */
		bregister.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				BDMongo m = new BDMongo();
				boolean b = m.anyadirUsuario(nombreTA2.getText(), passwordTA.getText() , cbCategoria.getSelectedIndex());
				if(b){
				
					JOptionPane.showMessageDialog(null, "El usuario se ha registrado correctamente");
				}else{
					System.out.println(nombre2.getText());
					JOptionPane.showMessageDialog(null, "No se ha podido registrar el usuario. "
							+ "Ya existe un usuario con ese nombre");
				}
			}
		});
		
		
		
		
		
		
		
		
		
		
	
	}
	
	
	public static void main(String[] args) {
		JFrame v = new JFrame();
		v.setSize(410,600);
		v.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		PanelUsuario p = new PanelUsuario();
		v.add(p);
		
		v.setVisible(true);
	}
}
