import lejos.nxt.*;
 
public class LightTest {
  public static void main(String[] args) throws Exception {
    LightSensor light = new LightSensor(SensorPort.S1);
 
    while (true) {
      LCD.drawInt(light.readValue(), 4, 0, 0);
      LCD.drawInt(light.readNormalizedValue(), 4, 0, 1);
      LCD.drawInt(SensorPort.S1.readRawValue(), 4, 0, 2);
      LCD.drawInt(SensorPort.S1.readValue(), 4, 0, 3);
    }
  }
}