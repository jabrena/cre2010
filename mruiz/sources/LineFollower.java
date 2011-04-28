/*
 * 
 */
package PFC.Robot;

import lejos.nxt.LightSensor;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.nxt.remote.RemoteMotor;
import lejos.robotics.navigation.TachoPilot;

import java.io.*;

public class LineFollower extends Thread {
	//Atributos:
	private LightSensor light_izq;
	private LightSensor light_der;
	private TachoPilot pilot;
	//Para la lectura por teclado.
	private BufferedReader lectura;
	private String tecla;
	//Datos para PILOT
	private final float wheeldiameter = 1.7f;
    private final float wheelseparation = 7.5f ;
    private RemoteMotor left_motor= Motor.A;
    private RemoteMotor right_motor=Motor.C;
    
     //Constructor:
     public LineFollower(){
     	//Inicialización de Atributos.
  		light_izq=new LightSensor(SensorPort.S1);
  		light_der=new LightSensor(SensorPort.S2);
  		pilot = new TachoPilot(wheeldiameter, wheelseparation,left_motor, right_motor, false);
 		lectura=new BufferedReader(new InputStreamReader(System.in));
 		tecla=null;
 		
 		//Calibrar 
 		calibrar();

     }
 
     //Métodos
     
     public void calibrar () {


 	//SENSOR 1
 		
 		System.out.println("PUERTO 1 sobre la lína NEGRA y pulsar  tecla...");
 		//Espera hasta que la tecla está pulsada.
 		try{
 			tecla=lectura.readLine();
 		}
 		catch (IOException ex){
 			ex.printStackTrace();
 		}
 		if(tecla!=null)
 		{
 			light_izq.calibrateLow();//Calibramos el color negro.
 		}
 		tecla=null;
 		System.out.println("PUERTO 1 sobre la lína BLANCA y pulsar  tecla...");
 		//Esperamos que se pulse una tecla.
 		try{
 			tecla=lectura.readLine();
 		}
 		catch (IOException ex){
 			ex.printStackTrace();
 		}
 		
 		if(tecla!=null)
 		{

 			light_izq.calibrateHigh();//Calibramos el color Blanco.

 		}
 		tecla=null;
 	//SENSOR 2
 		System.out.println("PUERTO 2 sobre la lína NEGRA y pulsar  tecla...");
 		
 		//Espera hasta que la tecla está pulsada.
 		try{
 			tecla=lectura.readLine();
 		}
 		catch (IOException ex){
 			ex.printStackTrace();
 		}
 		
 		if(tecla!=null)
 		{
 			light_der.calibrateLow();//Calibramos el color negro.
 		}
 		tecla=null;
 		System.out.println("PUERTO 2 sobre la lína BLANCA y pulsar  tecla...");
 		
 		//Esperamos que se pulse una tecla.
 		try{
 			tecla=lectura.readLine();
 		}
 		catch (IOException ex){
 			ex.printStackTrace();
 		}
 		
 		if(tecla!=null)
 		{

 			light_der.calibrateHigh();//Calibramos el color Blanco.

 		}
 	//FIN:
 		System.out.println("Sensores Calibrados.Pulse una tecla para continuar");
 		try{
 			tecla=lectura.readLine();
 		}
 		catch (IOException ex){
 			ex.printStackTrace();
 		}
 		
     }
     
     //Seguir Recto:( Los dos sensores en blanco).
     public void straight (){
    	 pilot.forward();
    	 return;
     }
     //Un poco a la izquierda: (Senor izquierdo ve un poco negro).
     public void left_slight (){
    	 pilot.rotate(-10);
    	 return;
     }
     //Un poco a la derecha: (Sensor derecho ve un poco negro)
     public void right_slight (){
    	 pilot.rotate(10);
    	 return;
     }
     //Fuerte a la Izquierda: (Sensor izquierdo ve todo negro)
     public void left_hard (){
    	 pilot.rotate(- 30);
    	 return;
     }
     //Fuerte a la derecha: (Sensor derecho ve un todo negro)
     public void right_hard (){
    	 pilot.rotate(30);
    	 return;
     }
     
     public void run(){
    	 while(true){
    		 //Los dos sensores en blanco
    		 if(light_der.readValue()>90 || light_izq.readValue()>90){
    			 straight();
    		 }
    		 //Senor izquierdo ve un poco negro
    		 else if(light_izq.readValue()<90 || light_izq.readValue()>10 || light_izq.readValue()>90)
    		 {
    			 left_slight ();
    		 }
    		 //Sensor derecho ve un poco negro
    		 else if(light_der.readValue()<90 || light_der.readValue()>10 || light_der.readValue()>90)
    		 {
    			 right_slight ();
    		 }
    		 //Sensor izquierdo ve todo negro
    		 else if(light_izq.readValue()>90)
    		 {
    			 left_hard ();
    		 }
    		 //Sensor derecho ve un todo negro
    		 else if(light_der.readValue()>90)
    		 {
    			 right_hard ();
    		 }
    		 
    		 else
    		 {
    			System.out.println( light_der.readValue());
    			System.out.println( light_izq.readValue());
    		 }
    	 }
     }


}


