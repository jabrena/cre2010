import lejos.nxt.*;
/**
 * A simple line follower for the LEGO 9797 car with
 * a single light sensor.
 * 
 * The light sensor should be connected to port 3. The
 * left motor should be connected to port C and the right 
 * motor to port B.
 * 
 * Variables initialized with a constant string are used 
 * in the LCD.drawString calls of the control loop to 
 * avoid garbage collection. 
 * 
 * @author  Ole Caprani
 * @version 22.08.08
 */


/*********************************************************************************************/
public class Siguelineas {
	
	private static LightSensor lightleft;
	private static LightSensor lightright;
	private static int valuellwhite = 0;
	private static int valuelrwhite = 0;
	private static int valuellblack = 0;
	private static int valuelrblack = 0;
	
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

	public static void main (String[] aArg)
	throws Exception
	{
		String left = "Turn left ";
		String right= "Turn right";
		
		LightSensor light = new LightSensor(SensorPort.S3);
		final int blackWhiteThreshold = 50;
		
		LightSensor black = new LightSensor(SensorPort.S1);
		final int whiteBlackThreshold = 50;
		
		final int forward = 1;
		final int stop = 3;
		final int flt = 4;
		final int power = 80;
	
		
		Motor.B.setSpeed(100);
		Motor.C.setSpeed(100);
		
		
		// Use the light sensor as a reflection sensor
		light.setFloodlight(true);
		LCD.drawString("Light %: ", 0, 0);
				
		// Show light percent until LEFT is pressed
		LCD.drawString("Press LEFT", 0, 2);
		LCD.drawString("to start", 0, 3);
		while (! Button.LEFT.isPressed()){
			LCD.drawInt(light.readValue(), 3, 9, 0);
		}(light.readValue() > whiteBlackThreshold)
		
		// Follow line until ESCAPE is pressed
		LCD.drawString("Press ESCAPE", 0, 2);
		LCD.drawString("to stop ", 0, 3);
		while (! Button.ESCAPE.isPressed()){
			//condicion1 = ver blanco
			if (light.readValue() > blackWhiteThreshold){
				// On white, turn right
				LCD.drawString(right, 0, 1);
				//MotorPort.B.controlMotor(0,stop);
				//MotorPort.C.cont(light.readValue() > whiteBlackThreshold)rolMotor(power, forward);
				
				Motor.B.backward();
				Motor.C.backward();
				int miliseconds = 50;
				try {Thread.sleep(miliseconds);} catch (Exception e) {}
				
			}
			
			if (light.readValue() > whiteBlackThreshold){
				// On white, turn right
				LCD.drawString(right, 0, 1);
				//MotorPort.B.controlMotor(0,stop);
				//MotorPort.C.controlMotor(power, forward);
				
				Motor.B.backward();
				Motor.C.backward();
				int miliseconds = 50;
				try {Thread.sleep(miliseconds);} catch (Exception e) {}
				
			}
			
			
			else{
				// On black, turn left
				LCD.drawString(left, 0, 1);				
				//MotorPort.B.controlMotor(power, forward);
				//MotorPort.C.controlMotor(0,stop);
				
				Motor.B.backward();
				Motor.C.stop();
				
				int miliseconds = 50;
				try {Thread.sleep(miliseconds);} catch (Exception e) {}
				
			else{
					// On white, turn right
					LCD.drawString(right, 0, 1);
					//MotorPort.B.controlMotor(0,stop);
					//MotorPort.C.controlMotor(power, forward);
					
					Motor.B.backward();
					Motor.C.stop();
					int miliseconds = 50;
					try {Thread.sleep(miliseconds);} catch (Exception e) {}
					
				}
			}
			LCD.drawInt(light.readValue(), 3, 9, 0);
			Thread.sleep(10);
			
}
		
		// Stop car gently with free wheel drive
		//MotorPort.B.controlMotor(0,flt);
		//MotorPort.C.controlMotor(0,flt);
		
		Motor.B.stop();
		Motor.C.stop();
		
		LCD.clear();
		LCD.drawString("Program stopped", 0, 0);
		Thread.sleep(1000);
	}
}
		
		
