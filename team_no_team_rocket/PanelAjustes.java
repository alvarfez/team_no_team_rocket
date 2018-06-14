package team_no_team_rocket;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.util.ArrayList;
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

	private static Local localElegido;
	private static ArrayList<Oferta> ofertasElegidas;
	
	private static JPanel datosCuadro;
	//Panel para añadir local
	private static JLabel direccion = new JLabel(" Dirección : "); private static JTextField tfDir = new JTextField();
	private static JLabel tfno = new JLabel(" Teléfono : ");	private static JTextField tfTfno = new JTextField();
	
	// Panel para añadir oferta 
	private static JLabel nom = new JLabel(" Nombre : "); private static JTextField tfNom = new JTextField();
	private static JLabel precio = new JLabel(" Precio : "); private static JTextField tfPrecio = new JTextField();
	private static JLabel dias = new JLabel(" Días activa : "); private static JPopupMenu cbDias = new JPopupMenu();
	private static JLabel desc = new JLabel(" Descripción : "); private static JTextField tfDesc = new JTextField(); 
	private static JButton bAnyadirLocal = new JButton("Añadir local");
	private static JButton bAnyadLoNeo = new JButton("Añadir a la BD");
	
	private static JCheckBox lunes = new JCheckBox("L");private static JCheckBox martes = new JCheckBox("M");
	private static JCheckBox miercoles = new JCheckBox("X");private static JCheckBox jueves = new JCheckBox("J");
	private static JCheckBox viernes = new JCheckBox("V");private static JCheckBox sabado = new JCheckBox("S");
	private static JCheckBox domingo = new JCheckBox("D");
	
	private static JPanel pNom = new JPanel();
	private static JPanel pDir = new JPanel();
	private static JPanel pTfno = new JPanel();
	private static JPanel pPrecio = new JPanel();
	private static JPanel pDias = new JPanel();	
	private static JPanel pDesc = new JPanel();
	private static JPanel pBotones = new JPanel();
	private static JButton bAnyadir = new JButton("Añadir Oferta");
	private static SliderPanel pSlider = new SliderPanel();
	
	private static int posicionOferta;
	
	private String nomUser;
	
	public PanelAjustes(int nivelAcreditacion, String nomUsuario) throws Exception{ // 1 = Local 0= Usuario
		pAjustes = this;
		this.nomUser = nomUsuario;
		if (nivelAcreditacion == 1){
//			this.setLayout(new GridLayout(4,1));
			this.setSize(410,550);
			BDNeo4j bd = new BDNeo4j();
			ArrayList<Local> alLocales = bd.getLocales(nomUser);
			bd.close();
			if (alLocales.isEmpty()){
				laNombre.setText("Parece que no tienes ningún local añadido, empieza ahora añadiendo un local");
				pTuNombre.add(laNombre);
				pAjustes.add(pTuNombre);
				pAjustes.add(bAnyadirLocal);
				
			} else if (alLocales.size() == 1){ 
				localElegido = alLocales.get(0);
				lmOfertas = new DefaultListModel<>();
				ArrayList<Oferta> alOfertas = bd.getOfertas(alLocales.get(0).getCodBar());
				for (Oferta o : alOfertas){
					lmOfertas.addElement(o);
				}
				lOfertas = new JList<Oferta>(lmOfertas);
				laNombre.setText("Tu Local:      "+"< "+localElegido.getNombre()+" >");
				pTuNombre.setLayout(new BorderLayout());
				pTuNombre.add(laNombre, "Center");
				pTusOfertas.add(laOferta);
				pAjustes.setLayout(new GridLayout(4,1));
				pAjustes.add(pTuNombre);
				pAjustes.add(laOferta);
				pTuNombre.add(laNombre);
				pAjustes.add(lOfertas);
				pAjustes.add(bAnyadirOf);
			
			}
//			else{
//				laNombre.setText("Elige uno de tus locales");
//				pTuNombre.add(laNombre);
//				pAjustes.add(pTuNombre);
//				 
//				else{
//				for ( Local l : alLocales){
//					
//					JButton b = new JButton(l.getNombre());
//					pAjustes.add(b);
//				}
//			}
//			laNombre.setText("Tu Local:      "+"< "+nomUser+" >");
//			pTuNombre.setLayout(new BorderLayout());
//			pTuNombre.add(laNombre, "Center");
//			pTusOfertas.add(laOferta);
//			pAjustes.add(pTuNombre);
//			pAjustes.add(laOferta);
			
// AQUÍ SOLO ENTRA SI EL USUARIO NO ES UN USUARIO TIPO LOCAL
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
		bAnyadirLocal.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				creaPanelAnyadirLocal();
				
			}
		});
		bAnyadLoNeo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				BDNeo4j bd = new BDNeo4j();
				Local l = new Local(nomUser, tfNom.getText(), "bar" , tfDir.getText(), Integer.parseInt(tfTfno.getText()));
				bd.anyadirLocal(l);
				localElegido = l;
				try {
					bd.close();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				bVolver.doClick();
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
			BDNeo4j bd = new BDNeo4j();
			ofertasElegidas = bd.getOfertas(localElegido.getCodBar());
			try {
				bd.close();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			while (lmOfertas.getSize()!=0) lmOfertas.remove(0);
			for (Oferta o: ofertasElegidas){
				lmOfertas.addElement(o);
			}
			pAjustes.setLayout(new GridLayout(4,1));
			laNombre.setText("Tu Local:      "+"< "+localElegido.getNombre()+" >");
			pAjustes.add(pTuNombre);
			pAjustes.add(laOferta);
			pAjustes.add(lOfertas);
			pAjustes.add(bAnyadirOf);
			lOfertas.revalidate();
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
			datosCuadro.setLayout(new GridLayout(3,2));
			pNom.setLayout(new GridLayout(1,2));pPrecio.setLayout(new GridLayout(1,2));pDias.setLayout(new GridLayout(1,2));pDesc.setLayout(new GridLayout(1,2));

			pNom.add(nom);pNom.add(tfNom);
			pPrecio.add(precio);pPrecio.add(tfPrecio);
			pDesc.add(desc); pDesc.add(tfDesc);
			pDias.setLayout(new GridLayout(4,2));
			pDias.add(dias);pDias.add(lunes);pDias.add(martes);pDias.add(miercoles);pDias.add(jueves);pDias.add(viernes);pDias.add(sabado);pDias.add(domingo);
			datosCuadro.add(pNom); datosCuadro.add(pPrecio);
			datosCuadro.add(pDias); datosCuadro.add(pSlider);
			datosCuadro.add(pDesc);
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
			datosCuadro.setLayout(new GridLayout(3,2));
			pNom.setLayout(new GridLayout(1,2));pPrecio.setLayout(new GridLayout(1,2));pDias.setLayout(new GridLayout(1,2));
						
			pNom.add(nom);pNom.add(tfNom);
			pPrecio.add(precio);pPrecio.add(tfPrecio);
			pDesc.add(desc);pDesc.add(tfDesc);
			pDias.add(dias);pDias.add(cbDias);
			datosCuadro.add(pNom); datosCuadro.add(pPrecio);
			datosCuadro.add(pDesc); datosCuadro.add(pDias);
			datosCuadro.add(pSlider);
		}
		tfNom.setText(o.getNombre());
		tfPrecio.setText(o.getPrecio()+"");

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
	private static void creaPanelAnyadirLocal(){
		pAjustes.removeAll();
		pBotones.removeAll();
		
		if (datosCuadro == null){
			datosCuadro = new JPanel();
			datosCuadro.setLayout(new GridLayout(2,2));
			pNom.setLayout(new GridLayout(1,2));pDir.setLayout(new GridLayout(1,2));pTfno.setLayout(new GridLayout(1,2));
			
			pNom.add(nom);pNom.add(tfNom);
			pDir.add(direccion);pDir.add(tfDir);
			pTfno.add(tfno);pTfno.add(tfTfno);
			datosCuadro.add(pNom); datosCuadro.add(pDir);
			datosCuadro.add(pTfno); 
		}
		pAjustes.setLayout(new GridLayout(3,1));
		laNombre.setText("Añadir Local");
		pAjustes.add(pTuNombre);
		pAjustes.add(datosCuadro);
		bAnyadLoNeo.setText("Añadir Local");
		pAjustes.add(bAnyadLoNeo);
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
			
		if ( tfNom.getText()!= null && tfPrecio.getText()!= null ){ // falta la comprobación de q no sea vacío
			
			return true;
		}
		return false;
	}

	public static void main(String[] args) throws Exception {
		JFrame v = new JFrame();
		v.setSize(410, 600);
		v.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		pAjustes = new PanelAjustes(1,"Ander");
		v.setSize(410, 600);
		v.add(pAjustes);
		v.setVisible(true);
		Oferta o1 = new Oferta("3x2", 3.0, "3 pintxos por 2" , new Date(), new Date() );
		Oferta o2 = new Oferta("Desayuno", 4.0, "3 pintxos por 2" , new Date(), new Date() );
		Oferta o3 = new Oferta("2x1", 2.0, "2 pintxos por 1" , new Date(), new Date() );
		Oferta o4 = new Oferta("PintxoPote", 3.0, "Pintxo + pote" , new Date(), new Date() );
//		lmOfertas.addElement(o1);lmOfertas.addElement(o2);lmOfertas.addElement(o3);lmOfertas.addElement(o4);
	}
}
