
//Area de importacion
import lejos.nxt.Motor;
import lejos.robotics.navigation.TachoPilot;

public class Atacante4 {

	//Area de propiedades
	private static Motor leftMotor;
	private static Motor rightMotor;
	private static TachoPilot pilot;
	private static float wheelDiameter = 0.0f;
	private static float wheelDistance = 0.0f;
	
	/*
	 * Constructor
	 */
	public Atacante4(){
		wheelDiameter = 5.5f;
		wheelDistance = 15.5f;
		
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

		Atacante4 aObj = new Atacante4();

		pilot.setSpeed(800);
		
		//1.Espera de 3 segundos
		int miliseconds = 0;
		//2.avanza 190cm
		pilot.travel(290);
		//3.Gira 90º
		pilot.rotate(150);
		//4.Avanza 350cm
		pilot.travel(100);
		//5.Gira 90 grados
		pilot.rotate(-110);
		//6.Avanza 218cm
		pilot.travel(120);
		pilot.rotate(110);
		pilot.travel(40);
	
	
	}}







