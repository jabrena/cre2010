import lejos.nxt.*;
import lejos.robotics.navigation.*;
 
/**
* Clone Wars, Chapter 1
* @author Alex1 & Alex2
*/
public class Prueba3{
	public static void main( String[] args){
	 
		Button.waitForPress();
		 
		float wheeldiameter = 5.5f;
		float wheelseparation = 10.5f;
		 
		Pilot pilot = new TachoPilot( wheeldiameter, wheelseparation, Motor.A, Motor.C, false);
		//pilot.forward();
		pilot.travel(120);
		pilot.rotate(90);
		pilot.travel(186);
		pilot.rotate(-108);
		pilot.travel(300);
		pilot.rotate(-108);
		pilot.travel(40);
	}
}
