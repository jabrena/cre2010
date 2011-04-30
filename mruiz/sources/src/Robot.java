/*
 * Clase: Robot
 * Descripción: Gestiona el Robot.
 * Autor: Miguel Ruiz Nogués.
 */

import java.io.IOException;
import lejos.nxt.remote.NXTCommand;
import lejos.pc.comm.NXTComm;
import lejos.pc.comm.NXTConnector;

public class Robot {

        /**
         * @param args
         * @throws IOException 
         * @throws IOException 
         * @throws IOException  
         */
        public static void main(String[] args) throws IOException {
        //Crear Conexión:       
                NXTConnector conn = new NXTConnector();

            if (!conn.connectTo("MIGUELO", NXTComm.LCP)) {
              System.err.println("Conexión Fallida");
              System.exit(1);
            }
            NXTCommand.getSingleton().setNXTComm(conn.getNXTComm());
       //LineFollower:
            LineFollower linefollower=new LineFollower();
            linefollower.calibrar();
            linefollower.seguir_linea();
        //Creación de Productos:

           

        //Cerrar Conexión:
        
        conn.close();


        }

}
