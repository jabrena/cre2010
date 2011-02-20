import java.awt.Rectangle;
import javax.microedition.lcdui.Graphics;
import lejos.nxt.LCD;

/**
 * 
 * Every image processed into NXTCam sensor is larger than the area 
 * available in LCD inside NXT Brick. 
 * This class solve the problem scaling the image into NXTCam
 * 
 * @author Juan Antonio Brenha Moral
 *
 */
public class NXTCamUtils {

           // NXT Display is 100 x 64
           // Camera view is 176 x 144
           private static int CAM_WIDTH=176;
           private static int CAM_HEIGHT=144;

           public static int xscale(int x){
                        // multiply by 1000,add 500 and divide by 1000 again
                        // so that integer division will round off
                        // to the closer whole number
                        // I know there are better ways but its only a test class :)
                        return ((((x*1000)/CAM_WIDTH)*LCD.SCREEN_WIDTH)+500)/1000;
           }


           public static int yscale(int y){
                   return ((((y*1000)/CAM_HEIGHT)*LCD.SCREEN_HEIGHT)+500)/1000;
           }
           
           public static void drawRectangle(Rectangle rectangleObj){
               Graphics g = new Graphics();
               g.clear();
               g.drawRect(xscale(rectangleObj.x),yscale(rectangleObj.y),
                         xscale(rectangleObj.width),yscale(rectangleObj.height));
               g.refresh ();
           }
}
