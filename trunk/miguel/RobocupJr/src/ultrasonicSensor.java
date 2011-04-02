import lejos.nxt.*;

public class ultrasonicSensor {
	
	public UltrasonicSensor sensorUltrasonidos = new UltrasonicSensor(SensorPort.S1);
	
	 public void main(String[] args){
		
		 while (!Button.ESCAPE.isPressed()){
			 	int ValueUltrasonidos =0 ;
			 	LCD.clear();		
				ValueUltrasonidos = sensorUltrasonidos.getDistance();
				System.out.println(ValueUltrasonidos);					
				Button.waitForPress();
				try {Thread.sleep(1000);} catch (Exception e) {} 
					
			}
	 }
}
