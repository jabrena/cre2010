import lejos.nxt.Motor;
import lejos.robotics.navigation.TachoPilot;


public class Detector1 {

	static private Motor motorleft;
	static private Motor motorright;
	static private TachoPilot pilot;
	static private final float wheeldiameter = 8.0f;
	static private final float wheelseparation = 11.0f ;
	static public int power = 70 ;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Instanciar 
		motorleft =  Motor.A;
		motorright =  Motor.C;
		
		pilot = new TachoPilot(wheeldiameter, wheelseparation, motorleft, motorright, false);
		pilot.setSpeed(300);
		
		pilot.travel(40);
	}

}
