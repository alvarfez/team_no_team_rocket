package team_no_team_rocket;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class SliderPanel extends JPanel{
	
	public JSlider slider = new JSlider();
	public JSlider sliderM = new JSlider();
	private static JPanel panelSliders = new JPanel();
	private static JLabel horaInicio = new JLabel("Hora Inicio");
	private static JLabel horaFin = new JLabel("Hora Fin");
	private static JLabel lHora = new JLabel("Hora a guardar: ");
	
	
	public SliderPanel(){
		
		this.setSize(400,300);
		slider.setMaximum(15);
		slider.setMinimum(0);
		slider.setValue(9);
		sliderM.setMaximum(16);
		sliderM.setMinimum(1);
		sliderM.setValue(10);
		

		slider.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				if (!slider.getValueIsAdjusting()){
					lHora.setText("Hora a guardar: " + (slider.getValue()+8) +":00 - "+ (sliderM.getValue()+8) + ":00");
					if(slider.getValue()>=sliderM.getValue()){
						sliderM.setValue(slider.getValue()+1);
					}
				}
				
			}
		});
		sliderM.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				if (!slider.getValueIsAdjusting()){
					lHora.setText("Hora a guardar: " + (slider.getValue()+8) +":00 - "+ (sliderM.getValue()+8) + ":00");
					if(sliderM.getValue()<=slider.getValue()){
						slider.setValue(sliderM.getValue()-1);
					}
				}
			}
		});
		panelSliders.setLayout(new GridLayout(3,2));
		panelSliders.add(horaInicio);panelSliders.add(slider);
		panelSliders.add(horaFin);panelSliders.add(sliderM);
		lHora.setText("Hora a guardar: " + (slider.getValue()+12) +":00 - "+ (sliderM.getValue()+12) + ":00");
		panelSliders.add(lHora);
		this.add(panelSliders);
		
	}
	
	public static void main(String[] args) {
		JFrame v = new JFrame();
		v.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		v.setSize(400, 300);
		SliderPanel p = new SliderPanel();
		v.add(p);
		v.setVisible(true);
	}  
}
