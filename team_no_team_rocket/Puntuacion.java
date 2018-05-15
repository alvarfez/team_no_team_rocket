package team_no_team_rocket;

/** Clase que se encargará de llevar la puntuación sobre 5 de los locales 
 * @author Ander
 *
 */
public class Puntuacion {

	private double puntosSobreCinco;
	private int vecesPuntuado;
	private double sumaPuntuacion = 0;
	
	public Puntuacion(){
		this.vecesPuntuado = 0;
	}
	public Puntuacion(double puntuacion){
		this.vecesPuntuado = 1 ;
		this.sumaPuntuacion += puntuacion;
		this.puntosSobreCinco = puntuacion;
		
	}
	/** Suma la puntuación indicada y hace la media con las ya sumadas
	 * @param puntuacion
	 */
	public void sumaPuntuacion(double puntuacion){

		// AQUÍ DEBE IR LA COMPROBACIÓN DE QUE LA PUNTUACIÓN ES VÁLIDA
		
		this.vecesPuntuado += 1 ;
		this.puntosSobreCinco = ( puntuacion + this.sumaPuntuacion ) / this.vecesPuntuado;
		this.sumaPuntuacion += puntuacion; 
		System.out.println("Tu puntuación media es de " + this.puntosSobreCinco);
	}

	public double getPuntosSobreCinco() {
		return puntosSobreCinco;
	}

	public void setPuntosSobreCinco(double puntosSobreCinco) {
		this.puntosSobreCinco = puntosSobreCinco;
	}

	public int getVecesPuntuado() {
		return vecesPuntuado;
	}

	public void setVecesPuntuado(int vecesPuntuado) {
		this.vecesPuntuado = vecesPuntuado;
	}

	public double getSumaPuntuacion() {
		return sumaPuntuacion;
	}

	public void setSumaPuntuacion(double sumaPuntuacion) {
		this.sumaPuntuacion = sumaPuntuacion;
	}
	
	public static void main(String[] args) {
		Puntuacion p = new Puntuacion();
		p.sumaPuntuacion(7);
		p.sumaPuntuacion(3);
		p.sumaPuntuacion(3.7);
		System.out.println(p.vecesPuntuado + " " + p.sumaPuntuacion);
		
	}
}
