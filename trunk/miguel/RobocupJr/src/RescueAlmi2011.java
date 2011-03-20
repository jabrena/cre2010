import lejos.nxt.Button;
import lejos.nxt.SensorPort;
import lejos.nxt.addon.TiltSensor;


public class RescueAlmi2011 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TiltSensor tilt = new TiltSensor(SensorPort.S3);
		SigueLineas sl = new SigueLineas();
		sl.calibrar();
		
		//Tarea de seguir lineas
		
		int tiltValue = 0;
		tiltValue = tilt.getXTilt();
		int tiltUmbral = 100;
		
		//while(!tilt){
		while(tiltValue<tiltUmbral){
			sl.task();
		}
		
		//Tarea de la rampa
		
		Rampa rampa = new Rampa();
		
		while(tiltValue <= tiltUmbral){
			rampa.subir();
		}
		
		rampa.parar();
		
		
		
	}

}
