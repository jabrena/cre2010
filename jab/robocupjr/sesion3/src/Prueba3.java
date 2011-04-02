import lejos.nxt.*;
import lejos.robotics.navigation.*;
 
/**
* Clone Wars, Chapter 2
* @author Alvaro & Irene
*/
public class Prueba3
{
	public static void main( String[] args)
	{
	 
	//float cm = inches * 2.54f;
	Button.waitForPress();
	 
	float wheeldiameter = 5.5f;
	float wheelseparation = 8.5f;
	 
	Pilot pilot = new TachoPilot( wheeldiameter, wheelseparation, Motor.A, Motor.C, false);
	pilot.travel(165);
	pilot.rotate(100);

	 
	}
}