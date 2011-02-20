import java.awt.Rectangle;

import lejos.nxt.*;
import lejos.nxt.addon.NXTCam;

public class NXTCamProcessor {

	private static NXTCam camera;
    private Rectangle rectangleObj1 = new Rectangle();
    private final int MAXIMUM_OBJECTS_DETECTED = 8;
	
	public NXTCamProcessor(I2CPort port){
		camera = new NXTCam(port);
		initCamera();
	}
	
	private void initCamera(){
        //Configuramos
        camera.sendCommand('A');
        camera.sendCommand('E');
	}
	
	public void showObjectDetected(){
        int objectCount=camera.getNumberOfObjects();
        
        if((objectCount >0) && (objectCount<MAXIMUM_OBJECTS_DETECTED)){

        	//Clear LCD
			for(int i=0;i<8;i++){
				LCD.drawString("                ", 0,i);
			}
			//Show Objects
			for (int i=0;i<objectCount;i++) {
				Rectangle r = camera.getRectangle(i);
				LCD.drawInt(camera.getObjectColor(i), 1, 0, i);
				LCD.drawInt(r.width, 3, 2, i);
				LCD.drawInt(r.height, 3, 5, i);
				LCD.drawInt((r.width * r.height), 4, 9, i);
			}
        }
	}
	
    public Rectangle getMaximumRectangle(){

         int objectCount = 0;
         //int max_area = 0;
         //double x =0;
         //int area =0;
         //Rectangle rectangleObj = new Rectangle();
 
         objectCount=camera.getNumberOfObjects();
         
         if((objectCount >0) && (objectCount<MAXIMUM_OBJECTS_DETECTED)){
 
        	 rectangleObj1 = camera.getRectangle(0);
        	 /*
				for (int i=0;i<objectCount;i++) {
					Rectangle r = camera.getRectangle(i);
					//if (r.height > 30 && r.width > 30) {
						LCD.drawInt(camera.getObjectColor(i), 1, 0, i);
						LCD.drawInt(r.width, 3, 2, i);
						LCD.drawInt(r.height, 3, 5, i);
						LCD.drawInt((r.width * r.height), 4, 9, i);
					//}
					
				}
        	 */
				/*
             //Calculo de objeto mas grande
        	 for(int i =0; i<objectCount; i++){
        		 Rectangle rect = camera.getRectangle(i);
                 //1. Calculo de area
        		 area=rect.width*rect.height;
 
                 //2. Preguntamos si esa area es mayor que nuestro maximo
        		 if (max_area <= area){
        			 //2.1 si es mayor: actualizar
        			 max_area = area;
        			 rectangleObj1 = rect;
        		 }
        	 }
        	 */
         }
         return rectangleObj1;
    }

}
