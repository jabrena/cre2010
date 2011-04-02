import lejos.nxt.*;
/**
 * A simple line follower for the LEGO 9797 car with
 * a light sensor. Before the car is started on a line
 * a BlackWhiteSensor is calibrated to adapt to different
 * light conditions and colors.
 * 
 * The light sensor should be connected to port 3. The
 * left motor should be connected to port C and the right 
 * motor to port B.
 * 
 * @author  Ole Caprani
 * @version 23.08.07
 */
public class LineFollowerCal
{

  private static BlackWhiteSensor sensor;
  
  // Porportional range
  private static int max_power = 100;
  private static int min_power = 0;
  
  private static int Tp = 66;
  private static int offset = 45;
  private static int dT = 5;   // ms
  private static int Pc = 800; // ms, estimate depends on Tp and Kp
  
  /* Test0 
  private static float Kp = 2.0F;
  private static float Ki = 0.0F;
  private static float Kd = 0.0F;
  */
  /* Test1 
  private static float Kp =  1.20F;
  private static float Ki =  0.03F;
  private static float Kd = 12.00F;
  */
  /* Test2
  private static float Kp =  2.40F;
  private static float Ki =  0.08F;
  private static float Kd = 18.00F;
  */
  /* Test3 good - max_power = 90, min_power = 60, Tp = 75
  private static float Kp =  1.80F;
  private static float Ki =  0.045F;
  private static float Kd = 18.00F;
  */
  /* Test4 better - max_power = 100, min_power = 40, Tp = 80
  private static float Kp =  2.40F;
  private static float Ki =  0.034F;
  private static float Kd = 42.00F;
  */
  /* Test5, OK */
  private static float Kp =  3.00F;
  private static float Ki =  0.05F;
  private static float Kd = 45.00F;
 

  private int lastError;
  private float integral = 0;
  private static int powerA;
  private static int powerC;

  public LineFollowerCal() {
     sensor = new BlackWhiteSensor(SensorPort.S3);
  }
  
  public void computePIDConstants() {
	  int maxPowerDiff = Tp - min_power;
	  float maxError = sensor.getWhiteToBlackSpan()/2;
	  float Kc = maxPowerDiff/maxError;
	  Kp = 0.6F*Kc;
	  Ki = 2*Kp*((float)dT/(float)Pc);
	  Kd = Kp*((float)Pc/(8*dT));  
}
  
  public void calibrate() {
	 sensor.calibrate();
	 offset = sensor.getThreshold();
	 //computePIDConstants();
  }
  
  public int limitPower(int p)
  {
	  if (p < min_power) return min_power;
	  if (p > max_power) return max_power;
	  return p;
  }
  
  public void pidCalculate(int lvalue) {
	 int turn;
	 int error;
	 float derivative = 0;
	 
 	 error = lvalue - offset;
 	 integral = (integral*2)/3 + error;
 	 derivative = error - lastError;
 	 turn = (int)(Kp*error + Ki*integral + Kd*derivative);	 
 	 
 	 powerA = limitPower(Tp + turn); 
 	 powerC = limitPower(Tp - turn);
 	 
 	 lastError = error;
 }
  
  public static void main (String[] aArg)
  throws Exception
  {
	 final int power = 80;
	 int LightValue;
	 int time = 0;
	 int whiteCount = 0;
	 boolean dir = true;
	 
	 LineFollowerCal lineFollower = new LineFollowerCal(); 
	 
	 lineFollower.calibrate();

	 LCD.drawString("Kp [*10] ", 0, 4);
     LCD.drawInt((int)(Kp*10),4,10,4);
	 LCD.drawString("Ki [*1000]", 0, 5);
     LCD.drawInt((int)(Ki*1000),4,10,5);
	 LCD.drawString("Kd ", 0, 6);
     LCD.drawInt((int)(Kd),4,10,6);
	 
	 while (Button.ENTER.isPressed());
	 LCD.drawString("Press ENTER     ", 0, 0);
	 LCD.drawString("to start        ", 0, 1);
	 LCD.drawString("Line follower   ", 0, 2);
	 while (!Button.ENTER.isPressed());
	 while (Button.ENTER.isPressed());
	 
     LCD.clear();
	 LCD.drawString("JoseMariaKim    ", 0, 0);
	 LCD.drawString("Robot Race      ", 0, 1);
     LCD.drawString("Light: ", 0, 2); 
     LCD.drawString("Time: ", 0, 3); 
	 
     while (! Button.ESCAPE.isPressed())
     {
    	 if (Button.ENTER.isPressed())
    		 time = 0;
    	 
    	 LightValue = sensor.light();
	     LCD.drawInt(LightValue,4,10,2);
	     LCD.drawInt(time,4,10,3);
	     LCD.refresh();
     
	     lineFollower.pidCalculate(LightValue);
	     
	     // Change direction if white seen for a long time
	     if (sensor.black())
	    	 whiteCount = 0;
	     else
	    	 whiteCount++;
	     
	     if (whiteCount == 100) // Seen white in 500 ms
	    	 dir = !dir;
	    	 
	     //if (dir)
	    	 Car.forward(powerA, powerC);
	     //else
	     //	 Car.forward(powerC, powerA);
	     	     
	     Thread.sleep(dT);
	     time++;
	     
     }
     
     Car.stop();
     LCD.clear();
     LCD.drawString("Program stopped", 0, 0);
     LCD.refresh();
   }
}
