/*
 * Clase: Robot
 * Descripción: Gestiona el Robot.
 * Autor: Miguel Ruiz Nogués.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

import lejos.nxt.LightSensor;
import lejos.nxt.SensorPort;
import lejos.nxt.remote.NXTCommand;
import lejos.pc.comm.NXTComm;
import lejos.pc.comm.NXTConnector;

public class Robot {


    
        public static void main(String[] args) throws IOException {
        //Crear Conexión:       
                NXTConnector conn = new NXTConnector();

            if (!conn.connectTo("MIGUELO", NXTComm.LCP)) {
              System.err.println("Fallo en la Conexión");
              System.exit(1);
            }
            NXTCommand.getSingleton().setNXTComm(conn.getNXTComm());
            LightSensor lightproducto=new LightSensor(SensorPort.S4);
    
            //Crear Vector de productos:
            Producto productos[]= new Producto [4];
            productos[0] =new Producto  (001, "Pan",lightproducto);
            productos[1]= new Producto  (002, "Leche",lightproducto);
            productos[2]= new Producto  (003, "Cereales",lightproducto);
            productos[3]= new Producto  (004, "Mermelada",lightproducto);
             
       //LineFollower:
                       
            LineFollower linefollower=new LineFollower(lightproducto,productos);
            linefollower.calibrar();
            linefollower.seguir_linea();

        //Cerrar Conexión:
        
        conn.close();


             	
        }
}