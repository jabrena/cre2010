import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.nxt.LightSensor;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
public class SigueLineasP {

  /**
   * @param args
   */
  public static void main(String[] args) throws Exception
  {
    LightSensor sluz = new LightSensor(SensorPort.S1);
    int error, max, min, med;
   
    min = sluz.readNormalizedValue();
    LCD.drawString("Valor minimo:", 2, 3);
    LCD.drawInt(min, 4, 6, 5);
    Thread.sleep(2000);
   
    Motor.A.rotate(-90,true);
    Motor.B.rotate(90);
    max = sluz.readNormalizedValue();
    LCD.clear();
    LCD.drawString("Valor maximo:", 2, 3);
    LCD.drawInt(max, 4, 6, 5);
    Thread.sleep(2000);
   
    Motor.A.rotate(45,true);
    Motor.B.rotate(-45);
    med = (max + min)/ 2;
    LCD.clear();
    LCD.drawString("Valor medio:", 2, 3);
    LCD.drawInt(med, 4, 6, 5);
    Thread.sleep(2000);
   
    Motor.A.forward();
    Motor.B.forward();
    LCD.clear();
    LCD.drawString("Pulse ESCAPE", 2, 3);
    LCD.drawString("para parar", 3, 5);
    while (true) {
      error = med - sluz.readNormalizedValue();
      Motor.A.setSpeed(300 - error);
      Motor.B.setSpeed(300 + error);
      if (Button.ESCAPE.isPressed()){
          break;
      }
    }
    // TODO Auto-generated method stub
  }

}