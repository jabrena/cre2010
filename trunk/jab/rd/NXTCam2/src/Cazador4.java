import java.awt.Rectangle;
import lejos.nxt.*;
import lejos.nxt.addon.CompassSensor;
import lejos.robotics.navigation.TachoPilot;
 
/**
 * Class to display the rectangles that the camera picks up on the NXT screen
 * @author Juan Antonio Brenha Moral
 *
 */
public class Cazador4{
 
	private static NXTCamProcessor NCP;
	private static UltrasonicSensor sonic;
	private static final int minimumDistance = 30;
	private static CompassSensor compass;

    private static final float wheeldiameter = 5.5f;
    private static final float wheelseparation = 10.5f;
    private static TachoPilot pilot;
	
	public static void main(String [] args){
 
        sonic = new UltrasonicSensor(SensorPort.S1);
        NCP = new NXTCamProcessor(SensorPort.S2);   
        compass = new CompassSensor(SensorPort.S3);
        pilot = new TachoPilot(wheeldiameter, wheelseparation, Motor.A, Motor.C, false);
 
        Rectangle rectangleObj = new Rectangle();
    	int quadrant = 0;
    	int distance = 0;
    	float angle = 0;
    	int iteration = 0;
        
        //RConsole.openBluetooth(0);
        //RConsole.open();
        //RConsole.println("Cazador3: Init");
 
        while(!Button.ESCAPE.isPressed()){

        	rectangleObj = NCP.getMaximumRectangle();
        	distance = getDistanceEstimation(sonic);
        	angle = compass.getDegreesCartesian();
        	iteration++;     	
        	        	
        	if(distance >= minimumDistance){
	        	if(rectangleObj.width > 0){
	        		NXTCamUtils.drawRectangle(rectangleObj);
	        		quadrant = getQuadrant(rectangleObj);
	        		showDebugUI(quadrant,distance,angle,iteration);
	        		takeActions(quadrant);
	        	}
        	}else{
        		pilot.stop();
        	}
        }
	}
	
	private static int getQuadrant(Rectangle rectangleObj){
		 
		int nx=NXTCamUtils.xscale(rectangleObj.x);
		int q = 0;
 
		if((nx>0) && (nx <=20)){
			q=1;
		}else if((nx> 20) && (nx <=40)){
			q=2;
		}else if((nx> 40) && (nx <=60)){
			//Center
			q=3;
		}else if((nx > 60) && (nx <=80)){
			q=4;
		}else if((nx > 80) && (nx <=100)){
			q=5;
		}else{
			q = 99;
		}
		
		return q;
	}
	
	private static void showDebugUI(int quadrant,int distance,float angle, int iteration){
	     LCD.drawString("                ", 0,0);
	     LCD.drawString("                ", 0,1);
	     LCD.drawString("                ", 0,2);
	     LCD.drawString("                ", 0,3);
	     LCD.drawString("Q: "+ quadrant, 0, 0);
	     LCD.drawString("D: "+ distance, 0, 1);
	     LCD.drawString("A: "+ angle, 0, 2);
	     LCD.drawString("T: "+ iteration, 0, 3);
	     LCD.refresh();
	}
	
	private static void takeActions(int q){

		if(q == 1){
			pilot.rotate(20);
		}else if(q == 2){
			pilot.rotate(10);
		}else if(q == 3){
			pilot.travel(5);
		}else if(q == 4){
			pilot.rotate(-10);			
		}else if(q == 5){
			pilot.rotate(-20);
		}else{
			pilot.rotate(20);
		}

	}
	
	private static int getDistanceEstimation(UltrasonicSensor s){
		/*
		int[] distances = new int[5];
		s.getDistances(distances);
		double median = Statistics.median(distances);

		int val=0;
		val = (int) median;
		*/
		
		int val=0;
		for (int i=0; i<3; i++)
			val += s.getDistance();
		val /= 3;

		return val;
	}
	
    public static float median(int[] m) {
        int middle = m.length/2;  // subscript of middle element
        if (m.length%2 == 1) {
            // Odd number of elements -- return the middle one.
            return m[middle];
        } else {
           // Even number -- return average of middle two
           // Must cast the numbers to double before dividing.
           return (m[middle-1] + m[middle]) / 2;
        }
    }//end method median
}
