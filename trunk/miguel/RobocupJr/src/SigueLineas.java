import lejos.nxt.*;
import lejos.robotics.navigation.TachoPilot;
/*********************************************************************************************/

public class SigueLineas {
	/*********************************************************************************************/
		
	private Motor motorleft;
	private Motor motorright;
	private TachoPilot pilot;
	
	private LightSensor lightleft;
	private LightSensor lightright;
	private int valuellwhite = 0;
	private int valuelrwhite = 0;
	private int valuellblack = 0;
	private int valuelrblack = 0;	
	
	public int power = 70 ;
	/**instancio la CAM*/

	private final float wheeldiameter = 5.0f;
	private final float wheelseparation = 16.0f ;
	
/************************************************************************************/	
	/*********************************************************************************************/
	private void parar(){
		motorleft.stop();
		motorright.stop();

	}
	
	/*********************************************************************************************/
	
	public void calibrar (){
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
	
	public void task (){
		//enciendo los motores
		
		motorleft.forward();
		motorright.forward();

		motorright.setPower(power);
		motorleft.setPower(power);
		
		valuellwhite = valuellwhite - 10 ;
		valuelrwhite = valuelrwhite - 10 ;

		valuellblack = valuellblack + 10;
		valuelrblack = valuelrblack + 10;
		
		
		while(!Button.ESCAPE.isPressed()){
			 
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
	/******************************************
			}***************************************************/

	private void wait (int miliseconds) {
		try {Thread.sleep(miliseconds);} catch (Exception e) {}
		
	}
	/*********************************************************************************************/
	
	/*
	 * 
	 */
	public SigueLineas(){
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
	}
	
	/*
	 public static void main(String[] args){

		
		//programa principal
		
		calibrar();
		Button.waitForPress();
		siguelineas();
		
		
	 }

*/
	
	
	 /*********************************************************************************************/
		
}
