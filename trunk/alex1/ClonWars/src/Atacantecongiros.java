

//Area de importacion
import lejos.nxt.Motor;
import lejos.robotics.navigation.TachoPilot;
import lejos.util.Delay;

public class Atacantecongiros {

	//Area de propiedades
	private static Motor leftMotor;
	private static Motor rightMotor;
	private static Motor centralMotor;
	private static TachoPilot pilot;
	private static float wheelDiameter = 0.0f;
	private static float wheelDistance = 0.0f;
	
	/*
	 * Constructor
	 */
	public Atacantecongiros(){
		wheelDiameter = 5.5f;
		wheelDistance = 14.0f;
		
		leftMotor = Motor.B;
		rightMotor = Motor.C;
		centralMotor = Motor.A;

		pilot = new TachoPilot(wheelDiameter, wheelDistance,
				leftMotor, rightMotor, false);
		
	}
	
	/**
	 * Programa principal
	 * @param args
	 */
	public static void main(String[] args) {

		Atacantecongiros aObj = new Atacantecongiros();
		

		pilot.setSpeed(420);
		//1.Espera de 3 segundos
		int miliseconds = 0;		
		//b = new Brazo();
		//2.avanza 190cm
		pilot.travel(440);
		//3.Gira 90º
		pilot.rotate(90);
		centralMotor.forward();
		Delay.msDelay(200);
		centralMotor.backward();
		pilot.travel(30);
		
		//4.Avanza 350cm
		pilot.travel(120);
		centralMotor.forward();
		Delay.msDelay(200);
		centralMotor.backward();
		Delay.msDelay(200);
		centralMotor.forward();
		Delay.msDelay(200);
		centralMotor.backward();
		Delay.msDelay(200);
		centralMotor.forward();
		Delay.msDelay(200);
		centralMotor.backward();Delay.msDelay(200);
		Delay.msDelay(200);
		pilot.travel(30);
		//5.girar 90º		pilot.rotate(-120);
		//6.avanza 100cm
	
	
	
	
	}}
