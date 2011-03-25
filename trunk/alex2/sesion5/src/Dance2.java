		
		import java.util.Random;

import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.nxt.LightSensor;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.nxt.Sound;
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
			private static LightSensor light;
			private static SoundSensor sound;
			
			private static void bloque1(){
				
				Sound.beep();
				
				//1.Espera de 3 segundos
				int miliseconds = 0;
				//2.Giro hacia el lado derecho mientras avanza
				pilot.travelArc(12, 10);
				//3.Vuelve a la posicion inicial
				pilot.travelArc(-12, -10);	
				//4.Espera de 1 segundo
				miliseconds = 1000;
				//5.Gira hacia el lado izquierdo mientras avanza
				pilot.travelArc(-12, -10);
				//6.Vuelve nuevamente a la posicion inicial
				pilot.travelArc(12, 10);
				//2.Giro hacia el lado derecho mientras avanza
				pilot.travelArc(12, 10);
				//3.Vuelve a la posicion inicial
				pilot.travelArc(-12, -10);	
				//4.Espera de 1 segundo
				miliseconds = 1000;
				//5.Gira hacia el lado izquierdo mientras avanza
				pilot.travelArc(-12, -10);
				//6.Vuelve nuevamente a la posicion inicial
				pilot.travelArc(12, 10);
				//2.Giro hacia el lado derecho mientras avanza
				pilot.travelArc(12, 10);
				//3.Vuelve a la posicion inicial
				pilot.travelArc(-12, -10);	
				//4.Espera de 1 segundo
				miliseconds = 1000;
				//5.Gira hacia el lado izquierdo mientras avanza
				pilot.travelArc(-12, -10);
				//6.Vuelve nuevamente a la posicion inicial
				pilot.travelArc(12, 10);
			}
			
			private static void bloque2(){
				
				Sound.beep();
				
				int miliseconds = 0;
				
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
			
			private static void bloque3(){
				
				Sound.beep();
				
				int miliseconds = 0;
				

				
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
			}
			
			private static void bloque4(){
				
				Sound.beep();
				
				int miliseconds = 0;
				
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
			}
			
			private static void bloque5(){
				
				Sound.beep();
				
				int miliseconds = 0;
				
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
				pilot.travelArc(12, 10);
				//52.Vuelve a la posicion inicial
				pilot.travelArc(-12, -10);	
				//53.Espera de 1 segundo
				miliseconds = 1000;
				try {Thread.sleep(miliseconds);} catch (Exception e) {}
				//54.Gira hacia el lado izquierdo mientras avanza
				pilot.travelArc(-12, -10);
				//55.Vuelve nuevamente a la posicion inicial
				pilot.travelArc(12, 10);
				//56.Gira los brazos hacia alante
				armMotor.forward();
				miliseconds = 1000;
				try {Thread.sleep(miliseconds);} catch (Exception e) {}
				//57.Los brazos paran
				armMotor.stop();
			}
			
			private static void bloque6(){
				
				Sound.beep();
				
				int miliseconds = 0;

				//58.Giro hacia el lado derecho mientras avanza
				pilot.travelArc(20, 30);
				//59.Giro hacia el lado derecho mientras avanza
				pilot.travelArc(20, 30);
				//60.Giro hacia el lado derecho mientras avanza
				pilot.travelArc(20, 30);
				//61.Giro hacia el lado derecho mientras avanza
				pilot.travelArc(20, 30);
				//62.Giro hacia el lado derecho mientras avanza
				pilot.travelArc(20, 30);
				//63.Giro hacia el lado derecho mientras avanza
				pilot.travelArc(20, 30);
				//64.Giro hacia el lado derecho mientras avanza
				pilot.travelArc(20, 30);
				//65.Giro hacia el lado derecho mientras avanza
				pilot.travelArc(20, 30);
				//66.Giro hacia el lado derecho mientras avanza
				pilot.travelArc(20, 30);
				//67.Giro hacia el lado derecho mientras avanza
				pilot.travelArc(20, 30);
				//68.Giro hacia el lado derecho mientras avanza
				pilot.travelArc(20, 30);
				//69.Giro hacia el lado derecho mientras avanza
				pilot.travelArc(20, 30);
				//70.Giro hacia el lado derecho mientras avanza
				pilot.travelArc(20, 30);
				//71.Giro hacia el lado derecho mientras avanza
				pilot.travelArc(20, 30);
				//72.Giro hacia el lado derecho mientras avanza
				pilot.travelArc(20, 30);
				//73.Giro hacia el lado derecho mientras avanza
				pilot.travelArc(20, 30);
				//74.Giro hacia el lado derecho mientras avanza
				pilot.travelArc(20, 30);
				//75.Giro hacia el lado derecho mientras avanza
				pilot.travelArc(20, 30);
				//76.Giro hacia el lado derecho mientras avanza
				pilot.travelArc(20, 30);

			}
			
			private static void bloque7(){
				int soundValue = 0;

				//while(!Button.ENTER.isPressed()){
				for(int i = 0; i<=10000;i++){	
					
					soundValue = sound.readValue();
					LCD.drawString("V:"+ soundValue, 0,3);
					
					int miliseconds = 100;
					
					if((soundValue > 40)&&
					   (soundValue < 70)){
						armMotor.forward();
						try {Thread.sleep(miliseconds);} catch (Exception e) {}
						armMotor.stop();
						armMotor.forward();
						try {Thread.sleep(miliseconds);} catch (Exception e) {}
						armMotor.stop();
						luz();
					}
					
					if((soundValue > 40)&&
					   (soundValue < 70)){
						armMotor.backward();
						try {Thread.sleep(miliseconds);} catch (Exception e) {}
						armMotor.stop();
						armMotor.backward();
						try {Thread.sleep(miliseconds);} catch (Exception e) {}
						armMotor.stop();
						luz();
					}
					
					if(soundValue > 85){
						pilot.rotate(360);
					}
					
					if((soundValue > 40)&&
					(soundValue < 60)){
						pilot.forward();
					}
					
					if((soundValue > 45)&&
					   (soundValue < 70)){	
						light.setFloodlight(true);
					}else{
						light.setFloodlight(false);
					
					}
					if((soundValue > 60)&&
					(soundValue < 80))	{
						pilot.backward();
					
					if((soundValue > 70)&&
					   (soundValue < 100))		
						armMotor.smoothAcceleration(true);
						

					}
					LCD.drawString("I:"+ i, 0,4);
				}
			}
			

		
			public static void main(String[] args) {

				//Instanciar
				leftMotor = Motor.A;
				rightMotor = Motor.C;
				armMotor = Motor.B; 
				leftMotor.setPower(100);
				rightMotor.setPower(100);
				pilot = new TachoPilot(wheeldiameter, wheelseparation, leftMotor, rightMotor, false);
		        pilot.setSpeed(500);
				
				armMotor = Motor.B;
				
				sound = new SoundSensor(SensorPort.S2);
				light = new LightSensor(SensorPort.S3);

				
				
			
				
				/*
				bloque1();
				bloque2();
				bloque3();
				bloque4();
				luz();
				bloque5();
				luz();
				bloque6();
				
				*/				
				
				bloque7();
			}
			
			private static void While(boolean b) {
				// TODO Auto-generated method stub
				
			}

			private static void luz(){
				Random random = new Random();
				int randomValue = 0;
				randomValue = random.nextInt(10);
				
				if(randomValue > 5){
					light.setFloodlight(true);
				}else{
					light.setFloodlight(false);
				}
			}
			

	}

