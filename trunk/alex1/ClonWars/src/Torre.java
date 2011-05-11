import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.nxt.UltrasonicSensor;

public class Torre {
	//Area de propiedades
	private static Motor armMotor;
	private static UltrasonicSensor ultrasonic;
	public static void main(String[] args) {
		armMotor = Motor.B;
		ultrasonic = new UltrasonicSensor(SensorPort.S1);
		
		int distance = 0;
		while(!Button.ESCAPE.isPressed()){
		
			distance = ultrasonic.getDistance();
			LCD.drawString(""+distance,0,0);
	
			//Mide si detecta un enemigo
			if(distance <= 50){
				armMotor.forward();
				int miliseconds = 1000;
				try {Thread.sleep(miliseconds);} catch (Exception e) {}
			}

		}
	}		
}


