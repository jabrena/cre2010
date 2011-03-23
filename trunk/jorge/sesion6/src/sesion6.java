

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
		private static Motor armMotor;
		
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

			
			//1.Rotar 1165 grados
			pilot.rotate(1165);
			//2.Espera de 1 segundos
			int miliseconds = 1000;
			try {Thread.sleep(miliseconds);} catch (Exception e) {}
			//3.Zig zag hacia derecha
			pilot.travelArc(-90,-35);
			//4.Zig zag hacia izquierda
			pilot.travelArc(65,25); 
			//5.Rotar 360 grados
			pilot.rotate(360);
			//6.Ir hacia delante
			pilot.forward();
			miliseconds = 6000;
			//7.Zig Zag hacia derecha
			pilot.travelArc(-100,-90);
			//8.Zig Zag hacia izquierda
			pilot.travelArc(100,90);
			//9.Ir hacia delante
			pilot.forward();
			miliseconds = 2000;
			//10.Ir hacia atrás
			pilot.backward();
			miliseconds = 3000;
			//11.Mover brazos hacia delante 4 segundos
			armMotor.forward();
			miliseconds = 4000;
			//12.El motor de los brazos se para
			armMotor.stop();	
			//13.Mover brazos hacia atras 3.5 segundos
			armMotor.backward();
			miliseconds = 3500;
			
			
			
			
	
			
			
	
	
			
			
			
			
			
			

			







			
			
			

			
			

			
			
			
			




			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
	
		}
}
