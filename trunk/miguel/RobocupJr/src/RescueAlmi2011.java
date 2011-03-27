import lejos.nxt.*;
import lejos.nxt.SensorPort;
import lejos.nxt.addon.TiltSensor;

public class RescueAlmi2011 {

	public static void main(String[] args) throws InterruptedException {
		TiltSensor tilt = new TiltSensor(SensorPort.S4);
		SigueLineas sl = new SigueLineas();
		//Rampa Rampa = new Rampa(); 
		sl.calibrar();
		Button.waitForPress();
		//Tarea de seguir lineas

		int tiltValue = 0;
		tiltValue = tilt.getXTilt();
		int tiltUmbral = 15;

		while (!Button.ESCAPE.isPressed()){
			
			//tiltValue = tilt.getXTilt();
			//while(tiltValue<tiltUmbral){
				sl.task();
				//tiltValue = tilt.getXTilt();
			//}

			//Rampa.subir();
			//Rampa.wait(1000);


		/**	while(tiltValue>tiltUmbral){
				tiltValue = tilt.getXTilt();

				Rampa.subir();
				Rampa.wait(1000);
				Rampa.girar();
			}

			//Tarea de la rampa

			sl.parar();
			sl.EntrarSala();
		 	*/
		}

	}

}