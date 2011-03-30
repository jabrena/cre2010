

	import lejos.nxt.*;
import lejos.robotics.navigation.*;
	
	
	public class sesion6 {
		//ACTUADORES
		//Navegacion local
		private static Motor leftMotor;
		private static Motor rightMotor;

		private static final float wheelseparation = 12.5f;
		//Actuators
	    private static final float wheeldiameter = 3.0f;
	    private static TachoPilot pilot;
		
	    private static LightSensor light;
		private static SoundSensor sound;
		private static Motor armMotor;
		
		/**
		 * @param args
		 */
		public static void main(String[] args){
	
			light = new LightSensor(SensorPort.S1);

			//Instanciar
			leftMotor = Motor.A;
			rightMotor = Motor.C;	
			leftMotor.setPower(100);
			rightMotor.setPower(100);
			pilot = new TachoPilot(wheeldiameter, wheelseparation, leftMotor, rightMotor, false);
	        pilot.setSpeed(500);
	        light = new LightSensor(SensorPort.S1);
			armMotor = Motor.B;
	        
			int miliseconds = 1000;
			
			
			//1.Rotar 1065 grados
			pilot.rotate(1065);
			//2.Espera de 1 segundos
			try {Thread.sleep(miliseconds);} catch (Exception e) {}
			//3.Avanzar con los brazos moviéndose
			armMotor = Motor.B;
			rightMotor = Motor.C;
			armMotor.forward();
			pilot.forward();
			miliseconds = 3000;
			//4.Rotar 360 grados
			pilot.rotate(360);
			//5.Ir hacia delante
			pilot.forward();
			miliseconds = 4000;
			//6.Ir hacia atrás
            pilot.backward();
			miliseconds = 2000;
			//7.Mover brazos hacia delante 4 segundos
			armMotor.forward();
			miliseconds = 4000;
			try {Thread.sleep(miliseconds);} catch (Exception e) {}
			//8.El motor de los brazos se para
			armMotor.stop();	
			//9.Mover brazos hacia atras 3.5 segundos
			armMotor.backward();
			miliseconds = 3500;
			//10.Mover brazos hacia atras 3.5 segundos
			armMotor.forward();
			try {Thread.sleep(miliseconds);} catch (Exception e) {}
			//11.Sensor luz
			light.setFloodlight(true);
			//12.Ir hacia delante
			pilot.forward();
			miliseconds = 2000;
			//15.Ir hacia atrás
			pilot.backward();
			miliseconds =2000;
			//16.Rotar 360 grados
			pilot.rotate(360);
			//17.Mover brazos hacia delante 2 segundos
			armMotor.forward();
			miliseconds = 2000;
			//18.Mover brazos hacia atrás 4 segundos
			armMotor.backward();
			miliseconds = 4000;
			//19.Sensor de luz extremo
			light.getHigh();
			//20.Ir hacia delante
			pilot.forward();
			miliseconds = 3000;
			//21.Ir hacia atrás
			pilot.backward();
			miliseconds = 2000;
			//22.Rotar 360 grados
			pilot.rotate(360);
			//23.Ir hacia delante
			pilot.forward();
			miliseconds = 1000;
			//24.Ir hacia atras
			pilot.backward();
			miliseconds = 2000;
			//25.Parar
			pilot.stop();
			miliseconds = 2000;
			//26.Mover brazos hacia delante medio segundo
			armMotor.forward();
			miliseconds = 250;
			//27.Mover brazos hacia atrás medio segundo
			armMotor.backward();
			miliseconds = 250;
			//28.Ir hacia atras
			pilot.backward();
			miliseconds = 1000;
			//29.Se para un segundo
			pilot.stop();
			miliseconds = 1000;
			//30.Ir hacia atras
			pilot.backward();
			miliseconds = 1000;
			//31.Para sensor de luz
			light.calibrateLow();
			miliseconds = 2000;
			//32.Ir hacia delante
			pilot.forward();
			miliseconds = 2000;
			//33.Rotar 360 grados
			pilot.rotate(360);
			//34.Mover brazos mientras rota
			armMotor.forward();
			miliseconds = 4000;
			pilot.backward();
			miliseconds = 4000;
			//35.Mover brazos mientras rota
			armMotor.backward();
			miliseconds = 3000 ;
			pilot.forward();
			miliseconds = 3000 ;
			//36.Ir hacia delante
			pilot.forward();
			miliseconds = 2000;
			//37.Rotar 360 grados
			pilot.rotate(360);
			//38.Ir hacia atras
			pilot.backward();
			//39.Se para
			pilot.stop();
			miliseconds = 3000 ;
			//40.Ir hacia atras
			pilot.backward();
			miliseconds = 2000;
			//41.Ir hacia delante
			pilot.forward();
			miliseconds = 4000;
			//42.Mover brazos mientras avanza
			armMotor.backward();
			miliseconds = 3500;
			pilot.forward();
			miliseconds = 3500;
			//43.El motor de los brazos se para
			armMotor.stop();
			//44.Rotar una vuelta
			pilot.rotate(360);
			//45.Gira los brazos hacia alante
			armMotor.forward();
			miliseconds = 1000;
			//46.Giro hacia el lado derecho mientras avanza
			pilot.travelArc(20, 30);
			//47.Posicion inicial
			pilot.travelArc(-20, -30);
			//48.Rotar dos vueltas
			pilot.rotate(720);
			//49.Se para
			pilot.stop();
			miliseconds = 2000;
			//50.Ir hacia atras
			pilot.backward();
			miliseconds = 2000;
			//51.Rotar 360 grados
			pilot.rotate(360);
			//52.Mover brazos hacia delante 2 segundos
			armMotor.forward();
			miliseconds = 2000;
			//53.Mover brazos hacia atrás 4 segundos
			armMotor.backward();
			miliseconds = 4000;
			//54.Mover brazos mientras rota
			armMotor.backward();
			miliseconds = 3000 ;
			pilot.forward();
			miliseconds = 3000 ;
			//55.Ir hacia delante
			pilot.forward();
			miliseconds = 2000;
			//56.Rotar 360 grados
			pilot.rotate(360);
			//57.Ir hacia atras
			pilot.backward();
			miliseconds = 7000;
			//58.Se para
			pilot.stop();
			miliseconds = 3000 ;
			//59.Ir hacia atras
			pilot.backward();
			miliseconds = 2000;
			//60.Rotar 1440 grados
			pilot.rotate(1440); 
			//61.Ir hacia atras
			pilot.backward();
			miliseconds = 5000;
			armMotor.forward();
			miliseconds = 500;
			armMotor.backward();
			miliseconds = 500;
			//62.Rotar mientras mueve los brazos
			pilot.rotate(1440);
			armMotor.backward();
			miliseconds = 5000;
			//63.Ir hacia delante
			pilot.forward();
			miliseconds = 1000;
			//64.Ir hacia atras
			pilot.backward();
			miliseconds = 2000;
			//65.Parar
			pilot.stop();
			miliseconds = 2000;
			//66.Rotar 360 grados
			pilot.rotate(360);
			//67.Ir hacia delante
			pilot.forward();
			miliseconds = 1000;
			//68.Se para
			pilot.stop();
			miliseconds = 4000;
			//69.Ir hacia atras mientras mueve los brazos
			pilot.forward();
			miliseconds = 4000;
			//70.Se para
			pilot.stop();
			miliseconds = 3000;
			//71.Los brazos se mueven hacia atras
			armMotor.forward();
			miliseconds = 7000;
			//72.Los brazos se mueven hacia delante
			armMotor.forward();
			miliseconds = 7000;
			//73.Ir hacia delante
			pilot.forward();
			miliseconds = 2000;
			//74.Rotar 360 grados
			pilot.rotate(360);
			//75.Ir hacia atras
			pilot.backward();
			miliseconds = 7000;
			//78.Se para
			pilot.stop();
			miliseconds = 3000 ;
			//79.Ir hacia atras
			pilot.backward();
			miliseconds = 2000;
			//80.Rotar 720 grados
			pilot.rotate(720); 
			//81.Rotar mientras mueve los brazos
			pilot.rotate(720);
			armMotor.backward();
			miliseconds = 6000;
			//82.Se para
			pilot.stop();
			miliseconds = 3000;
			//83.Ir hacia atras
			pilot.backward();
			miliseconds = 4000;
			//84.Rotar 720 grados
			pilot.rotate(720);
			//85.Ir hacia delante
			pilot.forward();
			miliseconds = 4000;
			//86.Ir hacia atras mientras mueve brazos hacia atras
			pilot.backward();
			miliseconds = 3000;
			armMotor.backward();
			miliseconds = 3000;
			//87.Se para
			pilot.stop();
			miliseconds = 2000;
			//88.Ir hacia delante
			pilot.forward();
			miliseconds = 3000;
			//89.Ir hacia atras
			pilot.backward();
			miliseconds = 2000;
			//90.Rotar 360 grados
			pilot.rotate(360);
			//91.Ir hacia atras
			pilot.backward();
			miliseconds = 5000;
			
			
			
			
			
		
			
		
			
		
			
			
	
		
		
		
			
	
		}
}
