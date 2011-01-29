import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.nxt.SensorPort;
import lejos.nxt.Sound;
import lejos.nxt.UltrasonicSensor;
import lejos.nxt.addon.NXTCam;


public class SMuxTest {
	
	   private static SensorMux smux;
	   private static UltrasonicSensor sonic;
	   
	   public static void main(String [] args){
	  
		   
		   sonic = new UltrasonicSensor(SensorPort.S1);
	         smux = new SensorMux(SensorPort.S2);
	         smux.configurate();
	        
		  	   int distance1 = 0;
			   int distance2 = 0;
			   int distance3 = 0;
			   int distance4 = 0;
	         
	        	 int distance = 0 ;
	         while(!Button.ESCAPE.isPressed()){
	        	 
	        	 /*
	             int miliseconds  =400;
	             distance1 = smux.getDistance(1);
	             try {Thread.sleep(miliseconds);} catch (Exception e) {}
	             distance2 = smux.getDistance(2);
	             try {Thread.sleep(miliseconds);} catch (Exception e) {}
	             distance3 = smux.getDistance(3);
	             try {Thread.sleep(miliseconds);} catch (Exception e) {}
	             distance4 = smux.getDistance(4);
	             try {Thread.sleep(miliseconds);} catch (Exception e) {}    
	             
	             LCD.drawString("     ", 0,   1);
	             LCD.drawString("     ", 0,   2);
	             LCD.drawString("     ", 0,   3);
	             LCD.drawString("     ", 0,   4);
	             LCD.drawString("1 "+ distance1, 0, 1);
	             LCD.drawString("2 "+ distance2, 0, 2);
	             LCD.drawString("3 "+ distance3, 0, 3);
	             LCD.drawString("4 "+ distance4, 0, 4);
	             LCD.refresh();
	             
	             */
	        	 

	             //if(Button.ENTER.isPressed()){
	            	 distance = sonic.getDistance();
	            	 distance1 = smux.getDistance(4);
	            	 LCD.drawString("       ", 0,   1);
	            	 LCD.drawString("       ", 0,   2);
	            	 LCD.drawString("1 "+ distance1, 0, 1);
	            	 LCD.drawString("1 "+ distance, 0, 2);
	            	 //Sound.beep();
		             try {Thread.sleep(100);} catch (Exception e) {}
	             //}
	 
	         }
	   }
}
