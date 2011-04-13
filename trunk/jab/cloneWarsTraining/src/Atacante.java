
//Area de importacion
import lejos.nxt.Motor;
import lejos.robotics.navigation.TachoPilot;

public class Atacante {

	//Area de propiedades
	private static Motor leftMotor;
	private static Motor rightMotor;
	private static TachoPilot pilot;
	private static float wheelDiameter = 0.0f;
	private static float wheelDistance = 0.0f;
	
	/*
	 * Constructor
	 */
	public Atacante(){
		wheelDiameter = 5.5f;
		wheelDistance = 15.0f;
		
		leftMotor = Motor.A;
		rightMotor = Motor.C;

		pilot = new TachoPilot(wheelDiameter, wheelDistance,
				leftMotor, rightMotor, false);
		
	}
	
	/**
	 * Programa principal
	 * @param args
	 */
	public static void main(String[] args) {

		Atacante aObj = new Atacante();

	}

}
