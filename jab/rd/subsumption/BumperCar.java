import lejos.subsumption.*;

public class BumperCar {
   public static void main(String [] args) {
      Behavior b1 = new DriveForward();
      Behavior b2 = new BatteryLow(6.5f);
      Behavior b3 = new HitWall();
      Behavior [] bArray = {b1, b2, b3};
      Arbitrator arby = new Arbitrator(bArray);
      arby.start();
   }
}
