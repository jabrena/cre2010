import lejos.nxt.*;
 
public class USSensor1 {
 
    private static UltrasonicSensor US;
 
    public static void main(String[] args){
 
        US = new UltrasonicSensor(SensorPort.S4);
 
        int distance = 0;
 
        //Sound.setVolume(Sound.VOL_MAX);
 
        while(!Button.ESCAPE.isPressed()){
 
            distance = US.getDistance();
 
            Sound.playTone(distance + 400, 100);
 
            LCD.drawString("   ", 0, 0);
 
            LCD.drawString("" + distance, 0, 0);
 
        }
 
    }
 
}