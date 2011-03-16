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
		
		//while(!tilt){
		while(!Button.ESCAPE.isPressed()){
			sl.task();
		}
		
	}

}
