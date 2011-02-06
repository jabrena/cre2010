import lejos.subsumption.*;
import lejos.nxt.*;

public class BatteryLow implements Behavior {
   private float LOW_LEVEL;

   private static final short [] note = {
      2349,115, 0,5, 1760,165, 0,35, 1760,28, 0,13, 1976,23,
      0,18, 1760,18, 0,23, 1568,15, 0,25, 1480,103, 0,18,
      1175,180, 0,20, 1760,18, 0,23, 1976,20, 0,20, 1760,15,
      0,25, 1568,15, 0,25, 2217,98, 0,23, 1760,88, 0,33, 1760,
      75, 0,5, 1760,20, 0,20, 1760,20, 0,20, 1976,18, 0,23,
      1760,18, 0,23, 2217,225, 0,15, 2217,218};

   public BatteryLow(float volts) {
      LOW_LEVEL = volts;
   }

   public boolean takeControl() {
      float voltLevel = Battery.getVoltage();
      System.out.println("Voltage " + voltLevel);

      return voltLevel < LOW_LEVEL;
   }

   public void suppress() {
      // Nothing to suppress
   }

   public void action() {
      play();
      try{Thread.sleep(3000);}catch(Exception e) {}
      System.exit(0);
   }

   public static void play() {
      for(int i=0;i <note.length; i+=2) {
         final short w = note[i+1];
         Sound.playTone(note[i], w);
         try {
            Thread.sleep(w*10);
         } catch (InterruptedException e) {}
      }
   }
}

