package team_no_team_rocket;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

/** Clase que hace el display de los ajustes en la VentanaPrincipal dependiendo si el usuario es
 * Local o Usuario enseñará diferentes opciones
 * @author Ander
 *
 */
public class PanelAjustes extends JPanel {
	
	private static PanelAjustes pAjustes;

	private static PanelAjustes pTemporal;	
	private static JPanel pTuNombre = new JPanel();
	private static JPanel pTusOfertas = new JPanel();
	private static JLabel laNombre = new JLabel();
	private static JLabel laOferta = new JLabel("Tus Ofertas: 	(2 clicks para editar/eliminar)");
	private static JList lOfertas;
	private static JButton bAnyadirOf = new JButton("Añadir Oferta");
	private static JButton bVolver = new JButton("Volver");
	
	private String nomUser;
	
	public PanelAjustes(int nivelAcreditacion, String nomUsuario){ // 1 = Local 0= Usuario
		pAjustes = this;
		this.nomUser = nomUsuario;
		if (nivelAcreditacion == 1){
			this.setLayout(new GridLayout(4,1));
			this.setSize(410,550);
			laNombre.setText("Tu Local:      "+"< "+nomUser+" >");
			pTuNombre.setLayout(new BorderLayout());
			pTuNombre.add(laNombre, "South");
			pTusOfertas.add(laOferta);
			pAjustes.add(pTuNombre);
			pAjustes.add(laOferta);
			lOfertas = new JList<Object>(); //TODO aquí hay q meter la lista de ofertas del local
			pAjustes.add(lOfertas);
			pAjustes.add(bAnyadirOf);
			pTemporal = pAjustes;
			

		}else{
			laNombre.setText("Tu Usuario:      "+"< "+nomUser+" >");
			pTuNombre.add(laNombre);
			pAjustes.add(pTuNombre);

		}
		pAjustes.validate();
		pAjustes.repaint();

		bAnyadirOf.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				pAjustes.removeAll();
				pAjustes.add(bVolver);
				pAjustes.repaint();
				pAjustes.revalidate();
//				PanelAjustes.creaPanelAnyadirof();
			}

		});

		bVolver.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			pAjustes = pTemporal;
			pAjustes.repaint();
			pAjustes.revalidate();
			
		}
	});
	}
	private static void creaPanelAnyadirof() {
		System.out.println("llega a crear panel");
		
	}
	public String getNomUsuario(){
		return nomUser;
	}
	public void setNomUsuario(String nomUsuario){
		nomUser = nomUsuario;
	}
	public static void main(String[] args) {
		JFrame v = new JFrame();
		v.setSize(410, 600);
		v.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		pAjustes = new PanelAjustes(1,"user");
		v.setSize(410, 600);
		v.add(pAjustes);
		v.setVisible(true);
	}
}
