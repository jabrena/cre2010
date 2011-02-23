import lejos.nxt.*;
import lejos.nxt.addon.TiltSensor;

public class RescueJ1 {
	
	//DEFINIR EL ROBOT
	
	//ACTUADORES
	//Navegacion local
	private static Motor leftMotor;
	private static Motor rightMotor;
	
	//Interaccion
	private static Motor armMotor;
	
	//SENSORES
	//Deteccion de lata
	private static TouchSensor touch;
	//private static TouchSensor rightTouch;
	//Sigue lineas
	private static LightSensor leftLight;
	private static LightSensor rightLight;
	
	private static SoundSensor sound;
	
	//Rampa
	private static TiltSensor tilt;
	private static int umbralTilt = 100; // Valor limite
	private static int tiltActual = 0;
	
	//Estados
	private static boolean state1 = false;
	private static boolean state2 = false;
	private static boolean state3 = false;
	private static boolean state4 = false;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		//Instanciar
		leftMotor = Motor.A;
		rightMotor = Motor.C;
		
		//La deteccion de linea
		leftLight = new LightSensor(SensorPort.S1);
		rightLight = new LightSensor(SensorPort.S2);
		//La deteccion de lata
		touch = new TouchSensor(SensorPort.S3);
		//rightTouch = new TouchSensor(SensorPort.S4);
		
		tilt = new TiltSensor(SensorPort.S4);
		
		
		state1 = true;
		
		//Bucle de control
		while(!Button.ESCAPE.isPressed()){
			
			tiltActual = tilt.getYTilt();
			
			if(state1){
				seguirLinea();
			}
			
			if(tiltActual >= umbralTilt){
				state1 = false;
				state2 = true;
			}
			
			if(state2){
				subirRampa1();
			}
			
			if(tiltActual >= umbralTilt){
				state2 = false;
				state3 = true;
			}
			
			if(state3){
				subirRampa2();
			}
			
			
			
			if(state4){
				detectarLata();
			}
			
		}
		
		

	}

	//Metodo que permite seguir Lineas
	static private void seguirLinea(){
		//Tu codigo con los 2 sensores de luz
		//Tu codigo con el sensor NXTLineLeader
	}
	
	static private void subirRampa1(){
		//avanzar un poco;
		
		//parar
		
		//leerVariable
		tiltActual = tilt.getYTilt();
	}
	
	static private void subirRampa2(){
		
		//Mientras haya inclinacion
		while(tilt.getYTilt() >= 50){
			//Avanzar
		}
		//Parar
		
		//Avanzar
		
		//Entrar en sala
		
		//Cambiar estado
		state3 = false;
		state4 = true;
		
	}
	
	static private void detectarLata(){
		//Leer la camara
		
		//Detectar objeto
		
		//Centrar orientacion
		
		//Avanzar
	}
}
