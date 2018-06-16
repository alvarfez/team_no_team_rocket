package team_no_team_rocket;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelMapa extends JPanel{

	private JPanel pBotonera = new JPanel();
	private JLabel lMapa;
	private JButton bMas= new JButton("+");
	private JButton bMenos= new JButton("-");
	private JButton bNorte= new JButton("Arr");
	private JButton bSur= new JButton("Ab");
	private JButton bEste= new JButton("Der");
	private JButton bOeste = new JButton("Izq");
	
	public PanelMapa(){
		super();
		this.setLayout(new BorderLayout());
		sacaMapa();
		pBotonera.add(bMas);pBotonera.add(bMenos);pBotonera.add(bNorte);pBotonera.add(bSur);pBotonera.add(bEste);pBotonera.add(bOeste);
		this.add(pBotonera,"South");
		addComponentListener(new ComponentAdapter() {
			
			@Override
			public void componentShown(ComponentEvent arg0) {
				
			}
			});
		bMas.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if ( zoom < 20 ){
					zoom++;
					proporcionX /= 2.0;
					proporcionY /= 2.0;
					sacaMapa();
				}
				
				
			}
		});

		bMenos.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if ( zoom > 13 ){
					zoom--;
					proporcionX *= 2.0;
					proporcionY *= 2.0;

					sacaMapa();
				}
				
			}
		});
		bNorte.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				lat += 0.0001;
				sacaMapa();
				
			}
		});
		bSur.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				lat -= 0.0001;
				sacaMapa();
				
			}
		});
		bEste.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				longi += 0.0001;
				sacaMapa();
				
			}
		});

		bOeste.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				longi -= 0.0001;
				sacaMapa();
				
			}
		});

		

	}
	public static void main(String[] args) {
		//Pruebas
		JFrame v = new JFrame();
		v.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		JPanel j = new JPanel();
		JButton b = new JButton("borra");
		j.setLayout(new BorderLayout());
		PanelMapa vm = new PanelMapa();
		j.add(vm);
		j.add(b, "North");
		v.add(j);
		v.setVisible(true);

		b.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				j.removeAll();
				v.getContentPane().remove(j);
				v.getContentPane().revalidate();


			}
		});

	}
	int zoom = 20;
	double lat = 43.2719;
	double longi = -2.9397;
	private void sacaMapa(){
		try {
			
			//Obtenemos la url de la imagen a obtener y donde la vamos a guardar
			String imageUrl = "https://maps.googleapis.com/maps/api/staticmap?center="
				+lat+","+longi+"&zoom="+ zoom +"&size=389x497&scale=2&maptype=roadmap";            
			String destinationFile = "image.jpg";
			
			//Dibujamos en pantalla el mapa
			URL url = new URL(imageUrl);
			InputStream is = url.openStream();
			OutputStream os = new FileOutputStream(destinationFile);

			byte[] b = new byte[2048];
			int length;

			while ((length = is.read(b)) != -1) {
				os.write(b, 0, length);
			}

			is.close();
			os.close();

		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		//Y lo pintamos
		if (lMapa == null ){
			
		lMapa = new MiJLabel(new ImageIcon((new ImageIcon("image.jpg")).getImage().getScaledInstance(389, 497,
				java.awt.Image.SCALE_SMOOTH)));
		this.add(lMapa, "Center");
		
		} else {
			lMapa.setIcon(new ImageIcon((new ImageIcon("image.jpg")).getImage().getScaledInstance(389, 497,
				java.awt.Image.SCALE_SMOOTH)));
		}
	}
	
	private class MiJLabel extends JLabel{
		public MiJLabel(ImageIcon ii ){
			super(ii);
		}
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D)g;
			for (int i = 0; i<latis.size(); i++ ){
				double latiBar = latis.get(i);
				double longiBar = longis.get(i);
				double difX = (longi - longiBar)/ proporcionX;
				double difY = (lat - latiBar)/ proporcionY;
				g2.fillOval((int)(getWidth()/2+difX-10),(int)(getHeight()/2+difY-10) , 20, 20);
			}
			g2.drawOval((int)(getWidth()/2-10),(int)(getHeight()/2-10) , 20, 20);
			System.out.println(longi + " "+ lat);
		}
	}
	private static ArrayList<Double> latis = new ArrayList<>();
	private static ArrayList<Double> longis = new ArrayList<>();
	private double proporcionX = -0.0005/373;
	private double proporcionY = 0.0005/513;
	
	
	public static void initBares(){ //se llama una vez a initBares desde donde se cargan los Bares
		latis = new ArrayList<>();
		longis = new ArrayList<>();
	}
	public static void nuevoBar(double lati, double longi){ // se llama por cada bar q tenga coordenadas
		latis.add(lati);
		longis.add(longi);
	}
	static{
		latis.add(43.270074); longis.add(-2.941474);
		latis.add(43.270066); longis.add(-2.941230);
		latis.add(43.271751); longis.add(-2.946198);		
//		Zubialde: 43.270074, -2.941474
//		La Terraza: 43.270066, -2.941230
//		El café de deusto: 43.271751, -2.946198
		
	}
	// 190 257
	
}
