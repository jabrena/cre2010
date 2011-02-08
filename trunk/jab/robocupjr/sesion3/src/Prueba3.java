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
 
Pilot pilot = new TachoPilot( wheeldiameter, wheelseparation, Motor.A, Motor.C, true);
//pilot.forward();
pilot.travel(-165f);
pilot.rotate(-100);
pilot.travel (-35f);
pilot.rotate(100);
pilot.travel (-170f);
pilot.rotate (100);
pilot.travel (-45f);
pilot.rotate (-100);
pilot.travel (-40);
pilot.rotate (-100);
pilot.travel (-40);
//pilot.travel(-12,true);
 
}
}