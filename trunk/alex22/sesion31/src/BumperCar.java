import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;


public class BumperCar {
   public static void main(String [] args) {
      Behavior b1 = new DriveForward();
      Behavior b2 = new HitWall();
      Behavior [] bArray = {b1, b2};
      Arbitrator arby = new Arbitrator(bArray);
      arby.start();
   }
}
