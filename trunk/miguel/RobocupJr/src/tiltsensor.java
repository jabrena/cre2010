import lejos.nxt.*;
import lejos.nxt.addon.*;

public class tiltsensor {
     		
static TiltSensor tilt = new TiltSensor(SensorPort.S3);
public static int tiltvalue = 0;

		public static void main(String[] args) {
			
			while (!Button.ESCAPE.isPressed()){
				tiltvalue = 0;
				LCD.clear();		
				tiltvalue = tilt.getXTilt();
				System.out.println(tiltvalue);					
				Button.waitForPress();
				try {Thread.sleep(1000);} catch (Exception e) {} 
					
			}
		}	
	} 