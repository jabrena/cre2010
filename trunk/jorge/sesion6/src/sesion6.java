

	import lejos.nxt.*;
import lejos.robotics.navigation.*;
	
	
	public class sesion6 {
		//ACTUADORES
		//Navegacion local
		private static Motor leftMotor;
		private static Motor rightMotor;

		//Actuators
	    private static final float wheeldiameter = 3.0f;
	    private static final float wheelseparation = 12.5f;
	    private static TachoPilot pilot;
		
		
		private static SoundSensor sound;
		
		/**
		 * @param args
		 */
		public static void main(String[] args) {
			// TODO Auto-generated method stub

			//Instanciar
			leftMotor = Motor.A;
			rightMotor = Motor.C;
			leftMotor.setPower(100);
			rightMotor.setPower(100);
			pilot = new TachoPilot(wheeldiameter, wheelseparation, leftMotor, rightMotor, false);
	        pilot.setSpeed(500);

			
			//1.Rotar 1175 grados
			pilot.rotate(1175);
			//2.Espera de 2 segundos
			int miliseconds = 2000;
			try {Thread.sleep(miliseconds);} catch (Exception e) {}
			//3.Zig zag hacia derecha
			pilot.travelArc(-50,-25);
			//4.Zig zag hacia izquierda
			pilot.travelArc(50,25);
			 
			 
		}
}
