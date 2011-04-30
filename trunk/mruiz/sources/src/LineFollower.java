/*
 * Clase: LineFollower.
 * Descripción: Sigue una línea Negra.
 * Autor: Miguel Ruiz Nogués.
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;

import lejos.nxt.*;
import lejos.nxt.remote.RemoteMotor;
import lejos.robotics.Colors.Color;
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
        Producto prod1;
        Producto prod2;
        Producto prod3;
        Producto prod4;
        
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
            lightproducto=new LightSensor(SensorPort.S4);
                   //Encender LEDs.
            lightright.setFloodlight(true);
            lightleft.setFloodlight(true);
            lightproducto.setFloodlight(true);
            System.out.println(lightproducto.readValue());
            //pilot
            pilot = new TachoPilot(4f, 20.5f, motorleft, motorright, false);
/*
 * Lista de Productos.
 */
             prod1 =new Producto  (001, "Pan",lightproducto);
             prod2= new Producto  (002, "Leche",lightproducto);
             prod3= new Producto  (003, "Cereales",lightproducto);
             prod4= new Producto  (004, "Mermelada",lightproducto);
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
            int opc =menu();
        	//Bucle infinito de Tarea:   
        	while (sw==true){
                  sw=comprobar_productos(opc);
                 /*
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
                   */                             

                }
        	parar();
        }
        
        public void evitar_obstaculo ()
        {
        	
        }
        /*
         * Combrobar Productos
         */
        
        public boolean comprobar_productos (int prod)
        {
        	System.out.println("COmprobando productos..");
        	boolean sw = true;
        	int value= lightproducto.readValue();
        	
        	if(prod1.comprobar_producto(value)==true && prod==1)
        	{
        		sw=false;
        		System.out.println("Producto"+prod1.get_Nombre()+"Encontrado");
        	}
        	else if(prod2.comprobar_producto(value)==true && prod==2)
        	{
        		sw=false;
        		System.out.println("Producto"+prod2.get_Nombre()+"Encontrado");
        	}
        	else if(prod3.comprobar_producto(value)==true && prod==3)
        	{
        		sw=false;
        		System.out.println("Producto"+prod3.get_Nombre()+"Encontrado");
        	}
        	else if(prod4.comprobar_producto(value)==true && prod==4)
        	{
        		sw=false;
        		System.out.println("Producto"+prod4.get_Nombre()+"Encontrado");
        	}
        	else{
        		System.out.println("producto no encontrado.");
        	}
        	return sw;
        }
        /*
         * MENU 
         */
        public int menu ()
        {
        	int opc=0;
        	do{
        		System.out.println("Escoga el producto.");
        		System.out.println("1.Pan");
            	System.out.println("2.Leche");
            	System.out.println("3.Cereales");
            	System.out.println("4.Mermelada");
            	System.out.println("           Opción:");
            	
            	try{
                 	
                	opc = Integer.parseInt(br.readLine());
                }
                catch(Exception e){ 
                	
                	e.printStackTrace();
                }  
                if(opc<1 || opc>4)
                {
                	System.out.println("Introduzca el resultado de nuevo.");
                }
        	}while(opc<1 || opc>4);
        	return opc;
        }
}
