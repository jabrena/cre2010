import lejos.nxt.*;
 
public class Robot5 {
 
    private Motor leftMotor;
    private Motor rightMotor;
    
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
 
    public void stop(){
        leftMotor.stop();
        rightMotor.stop();
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
 
    /**
     * @param args
     */
    public static void main(String[] args) {
    	
    	Button.waitForPress();
        Robot5 robot = new Robot5();
        robot.forward(8000);
        robot.turnLeft(1550);
        robot.forward(9500);
        robot.turnRight(1700);
        robot.forward(14000);
        robot.turnRight(1700);
        robot.forward(1100);
    }
 
}