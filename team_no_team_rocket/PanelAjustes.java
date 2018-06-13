package team_no_team_rocket;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.util.Date;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/** Clase que hace el display de los ajustes en la VentanaPrincipal dependiendo si el usuario es
 * Local o Usuario enseñará diferentes opciones
 * @author Ander
 *
 */
public class PanelAjustes extends JPanel {
	
	private static PanelAjustes pAjustes;
	
	private static JPanel pTuNombre = new JPanel();
	private static JPanel pTusOfertas = new JPanel();
	private static JLabel laNombre = new JLabel();
	private static JLabel laOferta = new JLabel("Tus Ofertas: 	(2 clicks para editar/eliminar)");
	private static JList<Oferta> lOfertas;
	private static DefaultListModel<Oferta> lmOfertas;
	private static JButton bAnyadirOf = new JButton("Añadir nueva oferta");
	private static JButton bVolver = new JButton("Volver");
	private static JButton bEliminar = new JButton("Eliminar Oferta");
	
	// Panel para añadir oferta 
	private static JPanel datosCuadro;
	private static JLabel nom = new JLabel(" Nombre : "); private static JTextField tfNom = new JTextField();
	private static JLabel precio = new JLabel(" Precio : "); private static JTextField tfPrecio = new JTextField();
	private static JLabel hora = new JLabel(" Hora : ");	private static JTextField tfHora = new JTextField();
	private static JLabel dias = new JLabel(" Días activa : "); private static JPopupMenu cbDias = new JPopupMenu();

	private static JCheckBox lunes = new JCheckBox("L");private static JCheckBox martes = new JCheckBox("M");
	private static JCheckBox miercoles = new JCheckBox("X");private static JCheckBox jueves = new JCheckBox("J");
	private static JCheckBox viernes = new JCheckBox("V");private static JCheckBox sabado = new JCheckBox("S");
	private static JCheckBox domingo = new JCheckBox("D");
	
	private static JPanel pNom = new JPanel();
	private static JPanel pPrecio = new JPanel();
	private static JPanel pHora = new JPanel();
	private static JPanel pDias = new JPanel();	
	private static JPanel pBotones = new JPanel();
	private static JButton bAnyadir = new JButton("Añadir Oferta");
	
	private static int posicionOferta;
	
	private String nomUser;
	
	public PanelAjustes(int nivelAcreditacion, String nomUsuario){ // 1 = Local 0= Usuario
		pAjustes = this;
		this.nomUser = nomUsuario;
		if (nivelAcreditacion == 1){
			this.setLayout(new GridLayout(4,1));
			this.setSize(410,550);
			laNombre.setText("Tu Local:      "+"< "+nomUser+" >");
			pTuNombre.setLayout(new BorderLayout());
			pTuNombre.add(laNombre, "Center");
			pTusOfertas.add(laOferta);
			pAjustes.add(pTuNombre);
			pAjustes.add(laOferta);
			lmOfertas = new DefaultListModel<>();
			lOfertas = new JList<Oferta>(lmOfertas); //TODO aquí hay q meter la lista de ofertas del local
			
			pAjustes.add(lOfertas);
			pAjustes.add(bAnyadirOf);

		}else{
			laNombre.setText("Tu Usuario:      "+"< "+nomUser+" >");
			pTuNombre.add(laNombre);
			pAjustes.add(pTuNombre);

		}
		pAjustes.revalidate();
		pAjustes.repaint();

		lOfertas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent e) {

				if (e.getClickCount()==1){
					lOfertas.locationToIndex(e.getPoint());
				}
				if (e.getClickCount()==2){
					if (lOfertas.getSelectedIndex()!= -1) {
						posicionOferta = lOfertas.locationToIndex(e.getPoint());
						creaPanelEditarOf(lmOfertas.getElementAt(posicionOferta));
						pAjustes.revalidate();
						pAjustes.repaint();
						// abre Oferta seleccionada 
					}
				}
			}
			});	
		bAnyadirOf.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				creaPanelAnyadirof();
			}

		});

		bAnyadir.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				if (comprobarInfo()){
					// método para añadir oferta ALVAR
					System.out.println("Hola?");
				} else {
					laNombre.setText("Faltan datos por rellenar");
				}
			}

		});
		bEliminar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Oferta " + lmOfertas.getElementAt(posicionOferta).getNombre() + " elminada");
				lmOfertas.remove(posicionOferta);
				//eliminarOf(lmOfertas.getElementAt(posicionOferta));				
				bVolver.doClick();
			}
		});

		bVolver.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			pAjustes.removeAll();
			pAjustes.setLayout(new GridLayout(4,1));
			laNombre.setText("Tu Local:      "+"< "+nomUser+" >");
			pAjustes.add(pTuNombre);
			pAjustes.add(laOferta);
			pAjustes.add(lOfertas);
			pAjustes.add(bAnyadirOf);
			pAjustes.revalidate();
			pAjustes.getParent().revalidate();
			pAjustes.getParent().repaint();			
		}
	});
	}
	private static void creaPanelAnyadirof() {
		pAjustes.removeAll();
		pBotones.removeAll();
		
		if (datosCuadro == null){
			datosCuadro = new JPanel();
			datosCuadro.setLayout(new GridLayout(2,2));
			pNom.setLayout(new GridLayout(1,2));pPrecio.setLayout(new GridLayout(1,2));pHora.setLayout(new GridLayout(1,2));pDias.setLayout(new GridLayout(1,2));
			cbDias.add(lunes);cbDias.add(martes);cbDias.add(miercoles);cbDias.add(jueves);cbDias.add(viernes);
			cbDias.add(sabado);cbDias.add(domingo);
			
			pNom.add(nom);pNom.add(tfNom);
			pPrecio.add(precio);pPrecio.add(tfPrecio);
			pHora.add(hora);pHora.add(tfHora);
			pDias.setLayout(new GridLayout(4,2));
			pDias.add(dias);pDias.add(lunes);pDias.add(martes);pDias.add(miercoles);pDias.add(jueves);pDias.add(viernes);pDias.add(sabado);pDias.add(domingo);
			datosCuadro.add(pNom); datosCuadro.add(pPrecio);
			datosCuadro.add(pHora); datosCuadro.add(pDias);
		}
		pAjustes.setLayout(new GridLayout(3,1));
		laNombre.setText("Añadir Oferta");
		pAjustes.add(pTuNombre);
		pAjustes.add(datosCuadro);
		pBotones.setLayout(new GridLayout(1,2));
		bAnyadir.setText("Añadir Oferta");
		pBotones.add(bAnyadir);
		pBotones.add(bVolver);
		pAjustes.add(pBotones);
		pAjustes.revalidate();
		pAjustes.getParent().revalidate();
		pAjustes.getParent().repaint();
		
	}
	
	private static void creaPanelEditarOf( Oferta o) {
		pAjustes.removeAll();
		pBotones.removeAll();
		
		if (datosCuadro == null){
			datosCuadro = new JPanel();
			datosCuadro.setLayout(new GridLayout(2,2));
			pNom.setLayout(new GridLayout(1,2));pPrecio.setLayout(new GridLayout(1,2));pHora.setLayout(new GridLayout(1,2));pDias.setLayout(new GridLayout(1,2));
			cbDias.add(lunes);cbDias.add(martes);cbDias.add(miercoles);cbDias.add(jueves);cbDias.add(viernes);
			cbDias.add(sabado);cbDias.add(domingo);
						
			pNom.add(nom);pNom.add(tfNom);
			pPrecio.add(precio);pPrecio.add(tfPrecio);
			pHora.add(hora);pHora.add(tfHora);
			pDias.add(dias);pDias.add(cbDias);
			datosCuadro.add(pNom); datosCuadro.add(pPrecio);
			datosCuadro.add(pHora); datosCuadro.add(pDias);
		}
		tfNom.setText(o.getNombre());
		tfPrecio.setText(o.getPrecio()+"");
		tfHora.setText(o.getDuracion()+"");

		pAjustes.setLayout(new GridLayout(3,1));
		laNombre.setText("Editar Oferta");
		pAjustes.add(pTuNombre);
		pAjustes.add(datosCuadro);
		pBotones.setLayout(new GridLayout(1,3));
		bAnyadir.setText("Editar");
		pBotones.add(bAnyadir);
		pBotones.add(bEliminar);
		pBotones.add(bVolver);
		pAjustes.add(pBotones);
		pAjustes.revalidate();
		pAjustes.getParent().revalidate();
		pAjustes.getParent().repaint();
		
	}

	public String getNomUsuario(){
		return nomUser;
	}
	public void setNomUsuario(String nomUsuario){
		nomUser = nomUsuario;
	}
	private boolean comprobarInfo() {
			
		if ( tfHora.getText()!= null && tfNom.getText()!= null && tfPrecio.getText()!= null ){ // falta la comprobación de q no sea vacío
			
			return true;
		}
		return false;
	}

	public static void main(String[] args) {
		JFrame v = new JFrame();
		v.setSize(410, 600);
		v.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		pAjustes = new PanelAjustes(1,"user");
		v.setSize(410, 600);
		v.add(pAjustes);
		v.setVisible(true);
		Oferta o1 = new Oferta("3x2", 3.0, "3 pintxos por 2" , new Date(), new Date() );
		Oferta o2 = new Oferta("Desayuno", 4.0, "3 pintxos por 2" , new Date(), new Date() );
		Oferta o3 = new Oferta("2x1", 2.0, "2 pintxos por 1" , new Date(), new Date() );
		Oferta o4 = new Oferta("PintxoPote", 3.0, "Pintxo + pote" , new Date(), new Date() );
		lmOfertas.addElement(o1);lmOfertas.addElement(o2);lmOfertas.addElement(o3);lmOfertas.addElement(o4);
	}
}
