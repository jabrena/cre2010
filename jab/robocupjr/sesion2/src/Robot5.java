import lejos.nxt.*;
 
/**
 *
 * @author mr.rick
 *
 */
public class Robot5 {
 
	//Variables
    private Motor leftMotor;
    private Motor rightMotor;
 
    /**
     * El constructor
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
 

    public static void main(String[] args) {

        Robot5 robot = new Robot5();
    	//AQUI VAMOS A PONER LAS INSTRUCCIONES        
        robot.forward(8000);
        robot.turnRight(500);
 
    }
 
}