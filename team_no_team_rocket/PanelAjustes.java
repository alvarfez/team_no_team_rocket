package team_no_team_rocket;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

/** Clase que hace el display de los ajustes en la VentanaPrincipal dependiendo si el usuario es
 * Local o Usuario enseñará diferentes opciones
 * @author Ander
 *
 */
public class PanelAjustes extends JPanel {

	private static JPanel pTuNombre = new JPanel();
	private static JPanel pTusOfertas = new JPanel();
	private static JLabel laNombre = new JLabel();
	private static JLabel laOferta = new JLabel("Tus Ofertas: 	(2 clicks para editar/eliminar)");
	private static JList lOfertas;
	private static JButton bAnyadirOf = new JButton("Añadir Oferta");
	
	public PanelAjustes(int nivelAcreditacion){ // 1 = Local 0= Usuario
		if (nivelAcreditacion == 1){
			this.setLayout(new GridLayout(4,1));
			laNombre.setText("Tu Local:      <NOMBRE>");
			pTuNombre.add(laNombre);
			pTusOfertas.add(laOferta);
			this.add(pTuNombre);
			this.add(laOferta);
			lOfertas = new JList<Object>(); //TODO aquí hay q meter la lista de ofertas del local
			this.add(lOfertas);
			this.add(bAnyadirOf);

			bAnyadirOf.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {

					// TODO Opciones para añadir oferta en el Local correspondiente

				}
			});

		}else{
			laNombre.setText("Tu Usuario:      <NOMBRE>");
			pTuNombre.add(laNombre);
			this.add(pTuNombre);
		}
	}
	
}
