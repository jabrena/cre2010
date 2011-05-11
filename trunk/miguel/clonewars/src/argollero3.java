import lejos.nxt.*;
import lejos.robotics.navigation.TachoPilot;

public class argollero3 {
    
    private static Motor motorleft;
    private static Motor motorright;
    private static TachoPilot pilot;
    
    //private static LightSensor lightleft = new LightSensor(SensorPort.S1);
    private static LightSensor lightright = new LightSensor(SensorPort.S2); 
    private static final float wheeldiameter = 4.1f;
    private static final float wheelseparation = 16.0f ;
    private static UltrasonicSensor ultrasonidos;

    private static void main(String[] args) {
    	motorleft =  Motor.A;
        motorright =  Motor.C;
        
        ultrasonidos = new UltrasonicSensor(SensorPort.S1);
        
        
        //lightleft = new LightSensor(SensorPort.S1);
        lightright = new LightSensor(SensorPort.S2);
        // enciende la luz de los sensores
        lightright.setFloodlight(false);
        //lightleft.setFloodlight(true);
        
        
        pilot = new TachoPilot(wheeldiameter, wheelseparation, motorleft, motorright, false);
        pilot.setSpeed(400);
    
        Button.waitForPress();
       
    	while (ultrasonidos.getDistance()>50){
    		pilot.forward();
    	}
    	lightright.setFloodlight(true);
    	pilot.stop();
    	
    	
    }
    
    
}
