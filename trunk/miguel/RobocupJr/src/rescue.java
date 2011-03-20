import lejos.nxt.*;
import lejos.robotics.navigation.TachoPilot;
/*********************************************************************************************/

public class rescue {
	/*********************************************************************************************/
		
	private static Motor motorleft;
	private static Motor motorright;
	private static TachoPilot pilot;
	
	private static LightSensor lightleft;
	private static LightSensor lightright;
	private static int valuellwhite = 0;
	private static int valuelrwhite = 0;
	private static int valuellblack = 0;
	private static int valuelrblack = 0;	
	
	public static int power = 70 ;
	/**instancio la CAM*/

	private static final float wheeldiameter = 5.0f;
	private static final float wheelseparation = 16.0f ;
	
/************************************************************************************/	
	/*********************************************************************************************/
	private static void parar(){
		motorleft.stop();
		motorright.stop();

	}
	
	/*********************************************************************************************/
	
	private static void calibrar (){
		System.out.println("calibrarblanco");
		
		Button.waitForPress();
		valuellwhite = lightleft.readValue();
		valuelrwhite = lightright.readValue();
		
		System.out.println(valuellwhite);
		System.out.println(valuelrwhite);
		System.out.println("calibrarnegro");
		
		Button.waitForPress();
		
		valuellblack = lightleft.readValue();
		valuelrblack = lightright.readValue();
		
		System.out.println(valuellblack);
		System.out.println(valuellblack);
		System.out.println("calibrado");
		
		
	}
	/*********************************************************************************************/
	
	private static void siguelineas (){
		//enciendo los motores
		
		motorleft.forward();
		motorright.forward();

		motorright.setPower(power);
		motorleft.setPower(power);
		
		valuellwhite = valuellwhite - 10 ;
		valuelrwhite = valuelrwhite - 10 ;

		valuellblack = valuellblack + 10;
		valuelrblack = valuelrblack + 10;
		
		
		while(/**tilt indique llano*/){
			 
			//Si los dos sensores ven blanco
			if ((lightleft.readValue() > valuellwhite) 
					&&  (lightright.readValue() > valuelrwhite ))
			{
				//Avanzas
				motorleft.forward();
				motorright.forward();
				wait (1);
			}
			//Sensor izquierdo ve negro y el sensor derecho blanco
			else if ((lightleft.readValue() < valuellblack)
						&&(lightright.readValue() > valuelrwhite))
			{
				parar();
				//gira a la izquierda
				pilot.rotate(10);
			//Si el sensor izquierdo blanco y el derecho negro
			}else if ((lightleft.readValue() > valuellwhite)
					&&(lightright.readValue() < valuelrblack)){
				
				parar();
				//gira a la derecha
				pilot.rotate(-10);
				
				wait(1);
				
			}else{ 
				parar(); 
				LCD.drawString("Otros", 0,7);
			}
		}
		
	}
	/*********************************************************************************************/

	private static void wait (int miliseconds) {
		try {Thread.sleep(miliseconds);} catch (Exception e) {}
		
	}
	/*********************************************************************************************/
	private static void rampa (){
		
		motorleft.forward();
		motorright.forward();
		motorleft.setspeed(800);
		motorright.setspeed(800);
		
		while (/**tilt indique inclinación*/){
			wait(1);
		}
	
		parar();
	} 
	
	/*********************************************************************************************/
	
	 public static void main(String[] args){
		//Instanciar 
		motorleft =  Motor.A;
		motorright =  Motor.C;

	 
		lightleft = new LightSensor(SensorPort.S1);
		lightright = new LightSensor(SensorPort.S2);
		// enciende la luz de los sensores
		lightright.setFloodlight(true);
		lightleft.setFloodlight(true);
		
		pilot = new TachoPilot(wheeldiameter, wheelseparation, motorleft, motorright, false);
		pilot.setSpeed(300);
		
		/*********************************************************************************************/
		/*********************************************************************************************/
		//programa principal
		
		calibrar();
		Button.waitForPress();
		siguelineas();
		
		
	 }
	 /*********************************************************************************************/
		
}
