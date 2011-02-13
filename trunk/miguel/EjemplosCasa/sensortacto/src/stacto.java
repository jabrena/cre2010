import lejos.nxt.*;

/**
 *@author Miguel 
 */
public class stacto {
	//Aqui le doy un puerto al sensor de tacto 
	//le voy a llamar tacto y le digo que esta en el puerto1
	private static TouchSensor tacto = new TouchSensor(SensorPort.S1);
	
	/**Programa principal*/
	public static void main(String[] args){
	// hasta que no pulse el boton naranja no empieza el programa
		Button.waitForPress();
		
	/**Aqui se puede escribir cualquier movimiento yo he elegido uno simple*/
		
	//Aqui digo que los motores A y B empiezen a moverse hacia delante
		 Motor.A.forward();
		 Motor.B.forward();
	//Aqui les asigno una potencia
		 Motor.A.setSpeed(800);
		 Motor.B.setSpeed(800);
	//Aqui le digo que avance hasta que choque el sensor de tacto
		 /**el while significa mientras*/
		 while (!tacto.isPressed()) ;
	//con esto avanza en linea recta hasta que se choque
	
	
	}
	/** Fin del programa */
}

