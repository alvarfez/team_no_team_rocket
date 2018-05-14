package team_no_team_rocket;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelMapa extends JPanel{

	public PanelMapa(){
		super();


		try {
			
			//Obtenemos la url de la imagen a obtener y donde la vamos a guardar
			String imageUrl = "https://maps.googleapis.com/maps/api/staticmap?center=43,2722,-2,9458&zoom=13&size=300x550&scale=2&maptype=roadmap";            
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
		this.add(new JLabel(new ImageIcon((new ImageIcon("image.jpg")).getImage().getScaledInstance(630, 600,
				java.awt.Image.SCALE_SMOOTH))));



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


}
