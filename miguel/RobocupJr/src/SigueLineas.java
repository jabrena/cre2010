import lejos.nxt.*;
import lejos.robotics.navigation.TachoPilot;
/*********************************************************************************************/

public class SigueLineas {
        /*********************************************************************************************/
                
        private Motor motorleft;
        private Motor motorright;
        private TachoPilot pilot;
        private final float wheeldiameter = 5.0f;
        private final float wheelseparation = 16.0f ;
        public int power = 30 ;
        
        private LightSensor lightleft;
        private LightSensor lightright;
        private int valuellwhite = 0;
        private int valuelrwhite = 0;
        private int valuellblack = 0;
        private int valuelrblack = 0;
        private UltrasonicSensor ultrasonidos = new UltrasonicSensor(SensorPort.S3);
                
/************************************************************************************/  
        private void wait (int miliseconds) {
                try {Thread.sleep(miliseconds);} catch (Exception e) {}
                
        }
        /*********************************************************************************************/
        public void parar(){
                motorleft.stop();
                motorright.stop();
        }
        /*********************************************************************************************/
        
        public void calibrar (){

        		System.out.println("calibrarblanco");
                
                Button.waitForPress();
                valuellwhite = lightleft.readValue();
                valuelrwhite = lightright.readValue();
                
                System.out.println(valuellwhite);
                System.out.println(valuelrwhite);
                System.out.println("calibrarnegro");  
                
                Button.waitForPress();
                
                valuellblack = lightleft.readValue();
                valuelrblack = lightright.readValue();
                
                System.out.println(valuellblack);
                System.out.println(valuellblack);
                System.out.println("calibrado");
                
                valuellwhite = valuellwhite - 10 ;
                valuelrwhite = valuelrwhite - 10 ;

                valuellblack = valuellblack + 10;
                valuelrblack = valuelrblack + 10;

                
                
        }
        /*********************************************************************************************/

        public void task (){
        	
        	motorright.setPower(30);
        	motorleft.setPower(30);
        	if (ultrasonidos.getDistance()>10)
        	{
        		/**si no hay lata haz el siguelineas*/
        		//Si los dos sensores ven blanco
        		if ((lightleft.readValue() > valuellwhite) 
        				&&  (lightright.readValue() > valuelrwhite ))
        		{
        			//Avanzas

        			motorleft.forward();
        			motorright.forward();
        			wait(1);
        		}
        		//Sensor izquierdo ve negro y el sensor derecho blanco
        		else if ((lightleft.readValue() < valuellblack)
        				&&(lightright.readValue() > valuelrwhite))
        		{

        			//gira a la izquierda
        			parar();
        			motorleft.backward();
        			motorright.forward();
        			wait(100);
        			//Si el sensor izquierdo blanco y el derecho negro
        		}else if ((lightleft.readValue() > valuellwhite)
        				&&(lightright.readValue() < valuelrblack)){

        			//gira a la derecha
        			parar();

        			motorleft.forward();
        			motorright.backward();
        			wait(100);


        		}else{ 
        			parar(); 
        			LCD.drawString("Otros", 0,7);
        			pilot.travel(1);
        		}                                       

        	}else{
        		Sound.beep();
        		parar();
        		wait(1000);
        		/**si hay lata*/
        		int dist1 = 14;
        		//esquivar lata
        		//girar hacia la derecha 45º
        		pilot.rotate(-70);
        		//avanzar 
        		pilot.travel(dist1);
        		//girar a la izquierda
        		pilot.rotate(65);

        		pilot.travel(22);

        		pilot.rotate(60);
        		pilot.travel(3);
        		//incorporarse                                   
        		while ((lightleft.readValue() > valuellwhite) 
        				&&  (lightright.readValue() > valuelrwhite )){

        			motorleft.forward();
        			motorright.forward();
        			wait(1);
        			Sound.beep();
        			

        		}
        		pilot.travel(3);
        		pilot.rotate(-45);
        	}

        }


        /*****************************************************************************************/
        public void task2 (){
        	motorright.setPower(70);
        	motorleft.setPower(70);
        	
        		if ((lightleft.readValue() > valuellwhite) 
        				&&  (lightright.readValue() > valuelrwhite ))
        		{
        			//Avanzas

        			motorleft.forward();
        			motorright.forward();
        			wait(1);
        		}
        		//Sensor izquierdo ve negro y el sensor derecho blanco
        		else if ((lightleft.readValue() < valuellblack)
        				&&(lightright.readValue() > valuelrwhite))
        		{

        			//gira a la izquierda
        			parar();
        			motorleft.backward();
        			motorright.forward();
        			wait(100);
        			//Si el sensor izquierdo blanco y el derecho negro
        		}else if ((lightleft.readValue() > valuellwhite)
        				&&(lightright.readValue() < valuelrblack)){

        			//gira a la derecha
        			parar();

        			motorleft.forward();
        			motorright.backward();
        			wait(100);


        		}else{ 
        			parar(); 
        			LCD.drawString("Otros", 0,7);
        			pilot.travel(1);
        		}                                       

        }

         /********************************************************************************************/

        public void EntrarSala(){ 
                pilot.travel(100);
                pilot.rotate(90);
                pilot.travel(100);
                
        }
        /*********************************************************************************************/
        
        /*
         * 
         */
        public SigueLineas(){
                //Instanciar 
                motorleft =  Motor.A;
                motorright =  Motor.C;

                pilot = new TachoPilot(wheeldiameter, wheelseparation, motorleft, motorright, false);
                pilot.setSpeed(300);
                
                motorright.setPower(power);
                motorleft.setPower(power);
                
         
                lightleft = new LightSensor(SensorPort.S1);
                lightright = new LightSensor(SensorPort.S2);
                // enciende la luz de los sensores
                lightright.setFloodlight(true);
                lightleft.setFloodlight(true);
                

                
        }

        
        /*
         public static void main(String[] args){

                
                //programa principal
                
                calibrar();
                Button.waitForPress();
                siguelineas();
                
                
         }

*/
}