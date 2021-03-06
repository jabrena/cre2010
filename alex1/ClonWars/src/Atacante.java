
//Area de importacion
import lejos.nxt.Motor;
import lejos.robotics.navigation.TachoPilot;
import lejos.util.Delay;

public class Atacante {

	//Area de propiedades
	private static Motor leftMotor;
	private static Motor rightMotor;
	private static Motor centralMotor;
	private static TachoPilot pilot;
	private static float wheelDiameter = 0.0f;
	private static float wheelDistance = 0.0f;
	private static Brazo b;
	/*
	 * Constructor
	 */
	public Atacante(){
		wheelDiameter = 5.5f;
		wheelDistance = 15.5f;
		
		leftMotor = Motor.A;
		rightMotor = Motor.C;
		centralMotor = Motor.B;

		pilot = new TachoPilot(wheelDiameter, wheelDistance,
				leftMotor, rightMotor, false);

	}
	
	/**
	 * Programa principal
	 * @param args
	 */
	public static void main(String[] args) {

		Atacante aObj = new Atacante();
		

		pilot.setSpeed(420);
		//1.Espera de 3 segundos
		int miliseconds = 0;		
		//b = new Brazo();
		//2.avanza 190cm
		pilot.travel(420);
		//3.Gira 90º
		pilot.rotate(120);
		//4.Avanza 350cm
		pilot.travel(120);
		
		//5.girar 90º		pilot.rotate(-120);
		//6.avanza 100cm
	
	
	
	
	}}







