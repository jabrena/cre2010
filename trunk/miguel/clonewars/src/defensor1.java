import lejos.nxt.*;

public class defensor1 {
	  private static Motor motorbase = Motor.A;
	  private static Motor motormedio = Motor.B;
      private static Motor motorarriba = Motor.C;
      private static UltrasonicSensor ultrasonidosl = new UltrasonicSensor(SensorPort.S1);
      private static UltrasonicSensor ultrasonidosr = new UltrasonicSensor(SensorPort.S2);

 	  private static void wait (int miliseconds) {
          try {Thread.sleep(miliseconds);} catch (Exception e) {}
          
  }
 	  
      public static void main(String[] args) {
   
    	  
    	  while (!Button.ESCAPE.isPressed()){



    		  while ((ultrasonidosl.getDistance() > 20)
    				  ||(ultrasonidosr.getDistance() > 20)){

    		  }
    		  if  (ultrasonidosl.getDistance() > 20){
    			  motorbase.forward();
    			  wait(500);
    			  motorbase.stop();
    			  
    			  motormedio.forward();
    			  motorarriba.forward();
    			  wait(100000);
    			  
    		  }
    		  else if (ultrasonidosr.getDistance() > 20){
    			  motorbase.forward();
    			  wait(500);
    			  motorbase.stop();
    			  motormedio.forward();
    			  motorarriba.forward();
    			  wait(100000);
    		  }
    	  }

      }
}