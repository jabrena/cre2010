package NXT.Calibration;

import lejos.nxt.LightSensor;
import lejos.nxt.SensorPort;
import lejos.nxt.remote.NXTCommand;
import lejos.pc.comm.NXTComm;
import lejos.pc.comm.NXTConnector;

import java.io.*;
/*
 * Conectar el Color sensor al Puerto 1.
 */
public class LightSensor_Calibration {
	
	public static void main (String [] args)throws IOException, InterruptedException{
//Conexión con el Brick.	
	 NXTConnector conn = new NXTConnector();

	    if (!conn.connectTo("MIGUELO", NXTComm.LCP)) {
	      System.err.println("Conexión Fallida");
	      System.exit(1);
	    }
	NXTCommand.getSingleton().setNXTComm(conn.getNXTComm());
//Inicialización del Sensor de Luz:
	LightSensor light1 = new LightSensor(SensorPort.S1);
//Para la lectura por teclado
	BufferedReader lectura= new BufferedReader(new InputStreamReader(System.in));
	String tecla=null;
	
	System.out.println("Calibrando Sensor en Puerto 1...");
	System.out.println("Ponga el sensor en la línea negra y pulse una tecla.");
	//Espera hasta que la tecla está pulsada.
	tecla=lectura.readLine();
	
	if(tecla!=null)
	{
		light1.calibrateLow();//Calibramos el color negro.
		
	}
	tecla=null;
	System.out.println("Ponga el sensor en la línea blanca y pulse una tecla.");
	//Esperamos que se pulse una tecla.
	tecla=lectura.readLine();
	
	if(tecla!=null)
	{

		light1.calibrateHigh();//Calibramos el color Blanco.

	}
	//Prueba de que ha funcionado:
	for(int i=0;i<100;i++){
		System.out.println("light = " + light1.readValue());
		Thread.sleep(100);
	}
	conn.close();

	}
	
}
	
