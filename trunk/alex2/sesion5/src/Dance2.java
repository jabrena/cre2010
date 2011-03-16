		
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
				//14.Hace un giro de 30º
				pilot.rotate(30);
				//15.Espera de 0'3 segundos
				try {Thread.sleep(miliseconds);} catch (Exception e) {}
				miliseconds = 300;
				//16.Hace un giro de 30º
				pilot.rotate(30);
				//17.Espera de 0'3 segundos
				try {Thread.sleep(miliseconds);} catch (Exception e) {}
				miliseconds = 500;
				//18.Hace un giro de 30º
				pilot.rotate(30);
				//19.Espera de 0'3 segundos
				try {Thread.sleep(miliseconds);} catch (Exception e) {}
				miliseconds = 500;
				//20.Hace un giro de 30º
				pilot.rotate(30);
				//21.Espera de 0'3 segundos
				try {Thread.sleep(miliseconds);} catch (Exception e) {}
				miliseconds = 300;
				//22.Hace un giro de 30º
				pilot.rotate(30);
				//23.Espera de 0'3 segundos
				try {Thread.sleep(miliseconds);} catch (Exception e) {}
				miliseconds = 300;
				//24.Hace un giro de 30º
				pilot.rotate(30);
				//25.Espera de 0'3 segundos
				try {Thread.sleep(miliseconds);} catch (Exception e) {}
				miliseconds = 300;
				//26.Hace un giro de 30º
				pilot.rotate(30);
				//27.Espera de 0'3 segundos
				try {Thread.sleep(miliseconds);} catch (Exception e) {}
				miliseconds = 300;
				//28.Hace un giro de 30º
				pilot.rotate(30);
				//29.Espera de 0'3 segundos
				try {Thread.sleep(miliseconds);} catch (Exception e) {}
				miliseconds = 300;
				//30.Hace un giro de 30º
				pilot.rotate(30);
				//31.Espera de 0'3 segundos
				try {Thread.sleep(miliseconds);} catch (Exception e) {}
				miliseconds = 300;
				//32.Hace un giro de 30º
				pilot.rotate(30);
				//33.Espera de 0'3 segundos
				try {Thread.sleep(miliseconds);} catch (Exception e) {}
				miliseconds = 300;
				//34.Hace un giro de 30º
				pilot.rotate(30);
				//35.Espera de 0'3 segundos
				try {Thread.sleep(miliseconds);} catch (Exception e) {}
				miliseconds = 300;
				//36.Hace un giro de 30º
				pilot.rotate(30);
				//37.Realiza una espera de 1 segundo
				miliseconds = 1000;
				try {Thread.sleep(miliseconds);} catch (Exception e) {}
				//38.El motor de los brazos para
				armMotor.stop();
				//39.Realiza 4 vueltas
				pilot.rotate(1455);
				//40.Hace una espera de 0'5 segundos
				miliseconds = 500;
				try {Thread.sleep(miliseconds);} catch (Exception e) {}
				//41.Retrocede hacia atras
				pilot.backward();
				//42.Hace una espera de 5 segundos
				miliseconds = 5000;
				try {Thread.sleep(miliseconds);} catch (Exception e) {}		
				//43.Hace un giro de 360º
				pilot.rotate(360);
				//44.Retrocede hacia atras
				pilot.forward();
				//45.Hace una espera de 5 segundos
				miliseconds = 5000;
				try {Thread.sleep(miliseconds);} catch (Exception e) {}		
				//46.Los motores A y B se paran
				pilot.stop();
				//47.Gira los brazos hacia atras 5,5 segundos
				armMotor.backward();
				miliseconds = 5500;
				try {Thread.sleep(miliseconds);} catch (Exception e) {}
				//48.El motor de los brazos se para
				armMotor.stop();
				//49.Gira 360º hacia un lado
				pilot.rotate(360);
				//50.Gira 360º hacia el otro lado
				pilot.rotate(-360);
				//51.Giro hacia el lado derecho mientras avanza
				pilot.travelArc(25, 10);
				//52.Vuelve a la posicion inicial
				pilot.travelArc(-25, -10);	
				//53.Espera de 1 segundo
				miliseconds = 1000;
				//54.Gira hacia el lado izquierdo mientras avanza
				pilot.travelArc(-12, -10);
				//55.Vuelve nuevamente a la posicion inicial
				pilot.travelArc(12, 10);
				//56.Gira los brazos hacia alante
				armMotor.forward();
				miliseconds = 1000;
				try {Thread.sleep(miliseconds);} catch (Exception e) {}
				
				
				
				
				
				
				
				
				
				
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			}
			
			
			

	}

