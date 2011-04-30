/*
 * Clase: LineFollower.
 * Descripción: Sigue una línea Negra.
 * Autor: Miguel Ruiz Nogués.
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;

import lejos.nxt.*;
import lejos.nxt.remote.RemoteMotor;
import lejos.robotics.navigation.TachoPilot;

public class LineFollower {
//Atributos:
		//Motores:
        private RemoteMotor motorleft;
        private RemoteMotor motorright;
        private TachoPilot pilot;

        private int power = 60 ;
        private int speed= 50;
               
        //Sensores:
        private LightSensor lightleft;
        private LightSensor lightright; 
        //private UltrasonicSensor ultrasonidos = new UltrasonicSensor(SensorPort.S3);

        //Variables de máximos y mínimos valores:
        private int valuelwhite = 70;
        private int valuelblack = 30;
        
        //Lectura por Teclado:
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
        String linea;    
        
//Constructor 
        public LineFollower(){
            //Motores:
            motorleft =  Motor.A;
            motorright =  Motor.C;  
            	//Power:
            motorright.setPower(power);
            motorleft.setPower(power);
                 //Velocidad.
            motorright.setSpeed(speed);
            motorleft.setSpeed(speed);
            //Sensores de Luz:   
            lightleft = new LightSensor(SensorPort.S1);
            lightright = new LightSensor(SensorPort.S2);
                   //Encender LEDs.
            lightright.setFloodlight(true);
            lightleft.setFloodlight(true);
            //pilot
            pilot = new TachoPilot(4f, 20.5f, motorleft, motorright, false);

     
    }

//Métodos:
        private void wait (int miliseconds) {
                try 
                {
                	Thread.sleep(miliseconds);
                } 
                catch (Exception e) 
                {
                	e.printStackTrace();
                }  
        }
        
        public void parar(){
                motorleft.stop();
                motorright.stop();
        }
        

        
        public void calibrar (){
        	//Color Negro:
                System.out.println("Calibrando Color NEGRO...(Pulse una enter para continuar)");
                try{
                	
         
                	linea = br.readLine();
                }
                catch(Exception e){ 
                	
                	e.printStackTrace();
                }          
                //Calibrar color Negro:
                lightright.calibrateLow();
                lightleft.calibrateLow();
                
            //Color Blanco:
                System.out.println("Calibrando Color BLANCO...(Pulse una enter para continuar)");
                try{
                	
                	
                	linea = br.readLine();
                }
                catch(Exception e){ 
                	
                	e.printStackTrace();
                }    
                //Calibrar color Negro:
                lightright.calibrateHigh();
                lightleft.calibrateHigh();

                
                
        }

        public void seguir_linea (){
    		System.out.println("Pulse enter para empezar a seguir la línea...");
            try{
                
            	linea = br.readLine();
            }
            catch(Exception e){ 
            	
            	e.printStackTrace();
            } 
        	//Bucle infinito de Tarea:        
        	while (true){
                        
                  //1. Los dos sensores ven Blanco - Avanzar.
                  if ((lightleft.readValue() > valuelwhite) &&  (lightright.readValue() > valuelwhite ))
                        {
                	  			//Avanzar
                                motorleft.forward();
                                motorright.forward();
                                wait(1);
                        }
                        //2. Sensor izquierdo Negro , Sensor derecho Blanco - IZQUIERDA
                        else if ((lightleft.readValue() < valuelblack)
                                        &&(lightright.readValue() > valuelwhite))
                        {

                                //Girar a la izquierda:
                                parar();
                                motorleft.backward();
                                motorright.forward();
                                wait(100);
                        //3. Sensor izquierdo Blannco, Sensor derecho Negro - DERECHA
                        }else if ((lightleft.readValue() > valuelwhite)
                                        &&(lightright.readValue() < valuelblack)){

                                //Gira a la derecha
                                parar();
                                motorleft.forward();
                                motorright.backward();
                                wait(100);

                                
                        }else{ 
                        		//En caso contrario.
                                parar(); 
                                pilot.travel(1);
                        }                                       

                }

        }
        
        public void evitar_obstaculo ()
        {
        	
        }
}

