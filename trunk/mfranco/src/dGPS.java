import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.nxt.SensorPort;
import lejos.nxt.Sound;
import lejos.nxt.comm.RConsole;
import lejos.util.Delay;

public class dGPS {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
				
		RConsole.open();
		
		dGPSSensor d = new dGPSSensor(SensorPort.S1); 
		
		LCD.clearDisplay();
	 	LCD.drawString("dGPS Test", 0, 0);

		// Set lat/long of Cork, Ireland for testing
		if(d.setLatitude(53443207) < 0) {
			RConsole.println("Set latitude failed");
		}
		
		if(d.setLongitude(-6188554) < 0) {
			RConsole.println("Set longitude failed");
		}
			
		do {    
 	
			if(d.linkStatus()) {
				LCD.drawString("Link: UP  ", 0, 1);
				RConsole.print("Link: -- UP --   ");
			} else {
				LCD.drawString("Link: DOWN", 0, 1);
				RConsole.print("Link: -- DOWN --   ");
			}	 	

			int latt = d.getLat();
			int longt = d.getLong();
			int head = d.getHeading();
			int dist = d.getDistanceToDest();
			int time = d.getUTC();
			
			LCD.drawString("Latt: " + latt, 0, 2);
			LCD.drawString("Long: " + longt, 0, 3);
			LCD.drawString("Head: " + head, 0, 4);
			LCD.drawString("Dist: " + dist, 0, 5);
		 	LCD.drawString("Time: "+ d.getUTC(), 0, 6);	
			
		 	RConsole.print("Latt: " + latt + "    Long: " + longt);
		 	RConsole.print("  Dist: " + dist);
		 	RConsole.println("   Time: " + time);	 	

		 	Delay.msDelay(1000);
		} while(!Button.ENTER.isPressed());
 		
		LCD.drawString("Done", 0, 7);
	    
	    RConsole.println("Done");
	    RConsole.close();
	    
		Sound.pause(2000);
	}
}