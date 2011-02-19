import lejos.nxt.*;
     
     public class DisplayOnly {
        
        static LineLeader liner = new LineLeader(SensorPort.S3);
        
        public static void main(String[] args) throws InterruptedException {
           DisplayOnly display = new DisplayOnly();
        }
        
        public DisplayOnly() throws InterruptedException  {
           liner.setSleep('P');  // wake it up; turn it on.
     //      liner.setSetPoint(100);
           LCD.drawString("Light settings: ", 0, 0);
           LCD.drawString("Place robot over", 0, 1);
           LCD.drawString("white area ", 0, 2);
           LCD.drawString("Press orange btn", 0, 3);
           Button.ENTER.waitForPressAndRelease();
           LCD.drawString("**** wait ******", 0, 3);
           liner.calibrateSensor('W');
           wait(150);
           LCD.clear();
           LCD.drawString("Place robot over", 0, 4);
           LCD.drawString("black area ", 0, 5);
           LCD.drawString("Press orange btn", 0, 6);
           Button.ENTER.waitForPressAndRelease();
           LCD.drawString("**** wait ******", 0, 6);
           liner.calibrateSensor('B');
           LCD.clear();
           LCD.drawString("SetPoint=" + liner.getSetPoint(), 0, 0);
           LCD.drawString("Kp=" + liner.getKp() + " " + "KpDiv=" + liner.getKpDivisor(), 0, 1);
           LCD.drawString("Ki=" + liner.getKi() + " " + "KiDiv=" + liner.getKiDivisor(), 0, 2);
           LCD.drawString("Kd=" + liner.getKd() + " " + "KdDiv=" + liner.getKdDivisor(), 0, 3);
           LCD.drawString("Steering = ", 0, 5);
           LCD.drawString("Result   = ", 0, 6);
           LCD.drawString("Average  = ", 0, 7);
           
           while (!Button.ESCAPE.isPressed())
           {
              LCD.drawString("      ", 11, 5);
              LCD.drawString("      ", 11, 6);
              LCD.drawString("      ", 11, 7);
              LCD.drawInt(liner.getSteering(), 11, 5);
              LCD.drawInt(liner.getResult(), 11, 6);
              LCD.drawInt(liner.getAverage(), 11, 7);
              wait(100);
           }
        }   
        
         public static void wait(int milliseconds) throws InterruptedException {
           Thread.sleep(milliseconds);
        }
     }       
