import lejos.nxt.Motor;
import lejos.nxt.SoundSensor;
import lejos.robotics.navigation.TachoPilot;


public class Dance1 {

	//ACTUADORES
	//Navegacion local
	private static Motor leftMotor;
	private static Motor rightMotor;

	//Actuators
    private static final float wheeldiameter = 5.5f;
    private static final float wheelseparation = 10.5f;
    private static TachoPilot pilot;
	
	//Interaccion
	private static Motor armMotor;
	
	private static SoundSensor sound;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//Instanciar
		leftMotor = Motor.A;
		rightMotor = Motor.C;
        pilot = new TachoPilot(wheeldiameter, wheelseparation, leftMotor, rightMotor, false);
        
		
		armMotor = Motor.B;
		
		//1.Espera de 3 segundos
		int miliseconds = 3000;
		try {Thread.sleep(miliseconds);} catch (Exception e) {}
		//2.Giro hacia el lado derecho
		pilot.arc(90);
		//3.Giro hacia el lado izquierdo
		pilot.arc(-90);
	}

}
