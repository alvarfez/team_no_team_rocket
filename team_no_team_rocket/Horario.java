package team_no_team_rocket;

import java.lang.reflect.Array;

public class Horario {
	private int horaInicio;
	private int horaFinal;
	private Character[] diasSemana = new Character[6];
	
	private static Character[] modeloDiasSemana = {'L', 'M', 'X', 'J', 'V', 'S', 'D'}; 

	
	public Horario(int horaInicio, int horaFinal, Character[] dias){
		this.horaInicio = Util.entradaHoras(horaInicio);
		this.horaFinal = Util.entradaHoras(horaFinal);
		this.diasSemana = dias;
	}
	
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
