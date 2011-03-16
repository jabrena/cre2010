import lejos.nxt.Button;


public class RescueAlmi2011 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		SigueLineas sl = new SigueLineas();
		sl.calibrar();
		
		boolean tilt = false;
		
		//Tarea de seguir lineas
		
		int tiltValue = 0;
		int tiltUmbral = 100;
		
		//while(!tilt){
		while(!Button.ESCAPE.isPressed()){
			sl.task();
		}
		
		//Tarea de la rampa
		
		Rampa rampa = new Rampa();
		
		while(tiltValue <= tiltUmbral){
			rampa.subir();
		}
		
		rampa.parar();
		
		
		
	}

}
