import lejos.nxt.*;
import lejos.robotics.navigation.TachoPilot;

public class Rampa {
	
	private Motor motorleft;
	private Motor motorright;
	private TachoPilot pilot;
	private final float wheeldiameter = 5.0f;
	private final float wheelseparation = 16.0f ;
	public int power = 80;
	
	public void parar(){
		pilot.stop();
		
	}	
	public void subir(){
		pilot.forward();
		
	}
	public void girar (){
		pilot.rotate(10);
	}
	
	public void subirRampa(){
		
	}
	public  void wait (int miliseconds) {
		try {Thread.sleep(miliseconds);} catch (Exception e) {}
 
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
