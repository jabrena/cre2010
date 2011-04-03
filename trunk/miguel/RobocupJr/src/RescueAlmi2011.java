import lejos.nxt.*;

public class RescueAlmi2011 {

	public static void main(String[] args) {


		SigueLineas sl = new SigueLineas();
		TouchSensor tacto = new TouchSensor(SensorPort.S4);
		sl.calibrar();
		

		Button.waitForPress();
		
		//Tarea de seguir lineas
		sl.SalaArriba();
		
			/***while (!tacto.isPressed()){
				sl.task();
			}
			
			Sound.beep();
			sl.girar();
			sl.avanzar();
			while (!Button.ESCAPE.isPressed())
			sl.task2();
		*/
		}
		//Tarea de la rampa
		

		//sl.parar();
		//sl.wait(1000);
			}
		


		//	sl.EntrarSala();





