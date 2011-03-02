		
		import lejos.nxt.Motor;
		import lejos.nxt.SoundSensor;
		import lejos.robotics.navigation.TachoPilot;


		public class Dance2 {

			//ACTUADORES
			//Navegacion local
			private static Motor leftMotor;
			private static Motor rightMotor;

			//Actuators
		    private static final float wheeldiameter = 3.0f;
		    private static final float wheelseparation = 12.5f;
		    private static TachoPilot pilot;
			
			//Interaccion
			private static Motor armMotor;
			
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
				
				armMotor = Motor.B;
				
				//1.Espera de 3 segundos
				int miliseconds = 3000;
				try {Thread.sleep(miliseconds);} catch (Exception e) {}
				//2.Giro hacia el lado derecho
				pilot.travelArc(-25, -10);
				//3.Giro hacia el lado izquierdo
				pilot.travelArc(12, 10);	
				pilot.travelArc(-12, -10);
				
			}

	}

