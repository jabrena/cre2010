package robot.robonce;
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
        private LightSensor lightproducto;
        //private UltrasonicSensor ultrasonidos = new UltrasonicSensor(SensorPort.S3);

        //Variables de máximos y mínimos valores:
        private int valuelwhite = 70;
        private int valuelblack = 30;
        
        //Lectura por Teclado:
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
        String linea;    
        
        //Productos:
        Producto productos [];
        //Voice Manager:
        Voicemanager vm;
        
        //Navegar:
        Navegar navegar;
        
//Constructor 
        public LineFollower(LightSensor light, Producto [] productos,Voicemanager vm   ){
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
            lightproducto=light;
                   //Encender LEDs.
            lightright.setFloodlight(true);
            lightleft.setFloodlight(true);
            lightproducto.setFloodlight(true);
            System.out.println(lightproducto.readValue());
            //pilot
            pilot = new TachoPilot(4f, 20.5f, motorleft, motorright, false);

            //Vector de Productos
            this.productos=productos;
            //Voice Manager:
             this.vm=vm;
            //Navegar:
            navegar=new Navegar(lightproducto,productos, vm);
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
        /*
         * Seguir Linea.
         */

        public void seguir_linea (){
    		System.out.println("Pulse enter para empezar a seguir la línea...");
            try{
                
            	linea = br.readLine();
            }
            catch(Exception e){ 
            	
            	e.printStackTrace();
            } 
            
            boolean sw = true;
            int opc =navegar.destino();
    		//Comprobando producto.
            sw=comprobar_productos(opc);
            
        	//Bucle infinito de Tarea:   
        	while (sw==true){


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
 
          		//Comprobando producto.
                  sw=comprobar_productos(opc);
                                              

                }
        	parar();
        	
            vm.say("Product");
            vm.say(productos[opc].get_Nombre());
            vm.say("Found");  	
     
        	
        }
        
        public void evitar_obstaculo ()
        {
        	
        }
        /*
         * Combrobar Productos
         */
        
        public boolean comprobar_productos (int opc)
        {
        	boolean sw = true;
        	if(navegar.identificar_posicion()==opc)
        	{
        		sw=false;
        	}
        	System.out.println("Opcion: "+opc);

        	return sw;
        }
       
}