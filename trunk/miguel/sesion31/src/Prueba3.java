import lejos.nxt.*;
import lejos.robotics.navigation.*;
/**
 *  By Miguel
 */  class Prueba3
{
	public static void main( String[] args)
	{
	 
		//float cm = inches * 2.54f;
		Button.waitForPress();
	 
		float wheeldiameter = 5.5f;
		float wheelseparation = 20f;
	 
		Pilot pilot = new TachoPilot( wheeldiameter, wheelseparation, Motor.A, Motor.C, false);
			pilot.travel(170);
			Motor.A.stop();
			Motor.C.stop();
			 try {Thread.sleep(1000);} catch (Exception e) {}
			pilot.rotate(180);
			pilot.travel(400);
			Motor.A.stop();
			Motor.C.stop();
			 try {Thread.sleep(1000);} catch (Exception e) {}
			pilot.rotate(180);
			
			
	}
}