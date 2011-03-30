import lejos.nxt.*;
import lejos.nxt.addon.TiltSensor;
import lejos.util.Delay;

public class RescueAlmi2011 {

	public static void main(String[] args) {

		//
		int tiltValue = 0;
		int tiltUmbral = 15;
		TiltSensor tilt = new TiltSensor(SensorPort.S4);
		SigueLineas sl = new SigueLineas();

		sl.calibrar();

		Button.waitForPress();
		
		//Tarea de seguir lineas

		tiltValue = tilt.getXTilt();
		System.out.println("" + tiltValue);

		
			while (((tiltValue>100))
				&& (tiltValue<7)){
			
				System.out.println("" + tiltValue);
				tiltValue = tilt.getXTilt();
				//Delay.msDelay(1000);
				sl.task();
			}
			Sound.beep();


		/**while(tiltValue>tiltUmbral){
			sl.task2();
			tiltValue = tilt.getXTilt();

		}
*/
		//Tarea de la rampa
		

		//sl.parar();
		//sl.wait(1000);
			}
		}


		//	sl.EntrarSala();





