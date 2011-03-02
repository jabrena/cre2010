import lejos.nxt.*;
/*********************************************************************************************/

public class rescue {
	/*********************************************************************************************/
		
	private static Motor motorleft;
	private static Motor motorright;
	
	private static LightSensor lightleft;
	private static LightSensor lightright;
	private static int valuellwhite = 0;
	private static int valuelrwhite = 0;
	private static int valuellblack = 0;
	private static int valuelrblack = 0;	
	
	public static int power = 100 ;
	/**instancio la CAM*/
	
	/*********************************************************************************************/
	/*********************************************************************************************/
	
	private static void calibrar (){
		System.out.println("calibrarblanco");
		Button.waitForPress();
		valuellwhite = lightleft.readValue();
		valuelrwhite = lightright.readValue();

		System.out.println("calibrarnegro");
		Button.waitForPress();
		valuellblack = lightleft.readValue();
		valuelrblack = lightright.readValue();
		System.out.println("calibrado");
		
		
	}
	/*********************************************************************************************/
	
	private static void siguelineas (){
		//enciendo los motores
		motorleft.forward();
		//motorright.forward();

		//doy potencia a los motores
		motorleft.setPower(power);
		//motorright.setPower(power);
		
		
	}
	/*********************************************************************************************/
	
	private static void wait (int miliseconds) {
		try {Thread.sleep(miliseconds);} catch (Exception e) {}
		
	}
	/*********************************************************************************************/
	
	 public static void main(String[] args){
		//Instanciar 
		motorleft =  Motor.A;
		motorright =  Motor.C;
		
		lightleft = new LightSensor(SensorPort.S1);
		lightright = new LightSensor(SensorPort.S2);
		//  enciende la luz de los sensores
		lightright.setFloodlight(true);
		lightleft.setFloodlight(true);
		
		/*********************************************************************************************/
		/*********************************************************************************************/
		//programa principal
		
		calibrar();
		wait(5000);
		/**siguelineas*/
		
		
	 }
	 /*********************************************************************************************/
		
}
