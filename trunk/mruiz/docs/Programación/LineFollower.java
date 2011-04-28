package PFC.Robot;

import lejos.nxt.LightSensor;
import lejos.nxt.SensorPort;
import java.io.*;

public class LineFollower extends Thread {
	//Atributos:
	LightSensor light1;
	LightSensor light2;
	
	//Para la lectura por teclado.
	BufferedReader lectura;
	String tecla;
	
     //Constructor:
     public LineFollower(){
     	//Inicialización de Atributos.
  		light1=new LightSensor(SensorPort.S1);
  		light2=new LightSensor(SensorPort.S2);
 		lectura=new BufferedReader(new InputStreamReader(System.in));
 		tecla=null;
 		
 		//Calibrar 
 		calibrar();
 		aux();
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
 			light1.calibrateLow();//Calibramos el color negro.
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

 			light1.calibrateHigh();//Calibramos el color Blanco.

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
 			light2.calibrateLow();//Calibramos el color negro.
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

 			light2.calibrateHigh();//Calibramos el color Blanco.

 		}
 	//FIN:
 		System.out.println("Sensores Calibrados");
    	 
     }
     
     public void aux () {
    	System.out.println(light1.readValue());
     }

}


