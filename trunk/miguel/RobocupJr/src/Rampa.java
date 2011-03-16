import lejos.nxt.*;
import lejos.robotics.navigation.TachoPilot;

public class Rampa {
	
	private Motor motorleft;
	private Motor motorright;
	private TachoPilot pilot;
	private final float wheeldiameter = 5.0f;
	private final float wheelseparation = 16.0f ;
	public int power = 70;
	
	public void parar(){
		pilot.stop();
		
	}	
	public void subir(){
		pilot.forward();
	}
	
	public Rampa(){
		//Instanciar 
		motorleft =  Motor.A;
		motorright =  Motor.C;

		pilot = new TachoPilot(wheeldiameter, wheelseparation, motorleft, motorright, false);
		pilot.setSpeed(800);
		
		motorright.setPower(power);
		motorleft.setPower(power);
		
	}
	
		
	
	public static void main(String[] args) {
				
	}
}
