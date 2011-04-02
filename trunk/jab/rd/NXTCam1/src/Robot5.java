import lejos.nxt.*;

/**
 *
 * @author mr.rick
 *
 */
public class Robot5 {

        /**
         *
         */
        private Motor leftMotor;
        private Motor rightMotor;

        /**
         *
         */
        public Robot5(){
                leftMotor = Motor.A;
                rightMotor = Motor.C;
        }

        public void forward(int miliseconds){
                leftMotor.forward();
                rightMotor.forward();
                try {Thread.sleep(miliseconds);} catch (Exception e) {}
        }

        public void backward(int miliseconds){
                leftMotor.backward();
                rightMotor.backward();
                try {Thread.sleep(miliseconds);} catch (Exception e) {}
        }


        public void turnLeft(int miliseconds){
                leftMotor.stop();
                rightMotor.forward();
                try {Thread.sleep(miliseconds);} catch (Exception e) {}
        }

        public void turnRight(int miliseconds){
                leftMotor.forward();
                rightMotor.stop();
                try {Thread.sleep(miliseconds);} catch (Exception e) {}
        }
        public void stop(){
                leftMotor.stop();
                rightMotor.stop();
        }

}
