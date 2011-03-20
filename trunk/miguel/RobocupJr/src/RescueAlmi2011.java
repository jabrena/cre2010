import lejos.nxt.Button;
import lejos.nxt.SensorPort;
import lejos.nxt.addon.TiltSensor;


public class RescueAlmi2011 {

	public static void main(String[] args) throws InterruptedException {
		TiltSensor tilt = new TiltSensor(SensorPort.S3);
		SigueLineas sl = new SigueLineas();
		sl.calibrar();
		
		//Tarea de seguir lineas
		
		int tiltValue = 0;
		tiltValue = tilt.getXTilt();
		int tiltUmbral = 100;
		
		while (!Button.ESCAPE.isPressed()){
		tiltValue = tilt.getXTilt();
		
		while(tiltValue<tiltUmbral){
			sl.task();
		}
		
		//Tarea de la rampa
		
		Rampa rampa = new Rampa();
		
		rampa.subir();
		while(tiltValue<tiltUmbral){
			sl.wait(1);
		}
		sl.parar();
		sl.EntrarSala();
		
		}

	}

}