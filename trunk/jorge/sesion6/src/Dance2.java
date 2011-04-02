		
		import lejos.nxt.*;
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
				armMotor = Motor.B; 
				leftMotor.setPower(100);
				rightMotor.setPower(100);
				pilot = new TachoPilot(wheeldiameter, wheelseparation, leftMotor, rightMotor, false);
		        pilot.setSpeed(500);
				
				armMotor = Motor.B;
				
				//1.Espera de 3 segundos
				int miliseconds = 3000;
				try {Thread.sleep(miliseconds);} catch (Exception e) {}
				//2.Giro hacia el lado derecho mientras avanza
				pilot.travelArc(25, 10);
				//3.Vuelve a la posicion inicial
				pilot.travelArc(-25, -10);	
				//4.Espera de 1 segundo
				miliseconds = 1000;
				//5.Gira hacia el lado izquierdo mientras avanza
				pilot.travelArc(-12, -10);
				//6.Vuelve nuevamente a la posicion inicial
				pilot.travelArc(12, 10);
				//2.Giro hacia el lado derecho mientras avanza
				pilot.travelArc(25, 10);
				//3.Vuelve a la posicion inicial
				pilot.travelArc(-25, -10);	
				//4.Espera de 1 segundo
				miliseconds = 1000;
				//5.Gira hacia el lado izquierdo mientras avanza
				pilot.travelArc(-12, -10);
				//6.Vuelve nuevamente a la posicion inicial
				pilot.travelArc(12, 10);
				//2.Giro hacia el lado derecho mientras avanza
				pilot.travelArc(25, 10);
				//3.Vuelve a la posicion inicial
				pilot.travelArc(-25, -10);	
				//4.Espera de 1 segundo
				miliseconds = 1000;
				//5.Gira hacia el lado izquierdo mientras avanza
				pilot.travelArc(-12, -10);
				//6.Vuelve nuevamente a la posicion inicial
				pilot.travelArc(12, 10);
				//7.Hace un giro de 270º
				pilot.rotate(275);
				try {Thread.sleep(miliseconds);} catch (Exception e) {}
				//8.Avanza hacia atras 50 cm
				pilot.travel(-50);
				//9.Hace otro giro de 180º
				pilot.rotate(180);
				try {Thread.sleep(miliseconds);} catch (Exception e) {}
				//10.Avanza hacia atras 50 cm
				pilot.travel(-50);
				//11.Gira 90º
				pilot.rotate(-90);
				//12.Gira los brazos hacia atras
				armMotor.backward();
				miliseconds = 1100;
				try {Thread.sleep(miliseconds);} catch (Exception e) {}
				//13.Acto seguido gira levemente los brazos hacia alante
				armMotor.forward();
				miliseconds = 100;
				try {Thread.sleep(miliseconds);} catch (Exception e) {}
			
			}
			
			
			

	}

