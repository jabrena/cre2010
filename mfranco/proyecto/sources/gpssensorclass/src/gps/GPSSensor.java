package gps;

import lejos.nxt.I2CPort;
import lejos.nxt.I2CSensor;

/**
* @author Martijn ten Bhömer
* 
*/


public class GPSSensor extends I2CSensor {

     public GPSSensor(I2CPort port) {
         super(port); //reemplazo de I2CPort class
     }
     public String gpsTransaction(int dataType) {
         //variables
         String message = ""; // cadena para almacenar el mensaje de salida de esta
                              // funcion
         boolean data = false; // boolean para comprobar si el tipo de datos es correcto 
                              // que fue recibido por el sensor
         int returnInt = 0; // entero que contiene el tipo de datos recibidos por 
                            // arduino
         byte[] readResponse = new byte[1]; // matriz de bytes para almacenar la respuesta 
                                            // del sensor
         byte[] readData = new byte[11]; // matriz de bytes para almacenar el dato real 
                                         // del sensor
         byte byteChar; // un solo byte utilizado como un buffer 
         
         // Inicio de la conexión I2C con el sensor, la dirección del sensor 
         // es 127
         setAddress(127);
/*
* La aplicación de Arduino I2C no es capaz de utilizar los registros,
* Por lo tanto, he escrito un protocolo que permite buscar de manera específica
* Los datos del sensor GPS. Pasos del protocolo: 
* 
* 1. lecturas rápidas dentro de 10 ms entre sí para decidir qué tipo
* De los datos (por ejemplo, 2 x leer con rotura de 10ms es la latitud, la lectura de 4x
* Es la longitud)
* 2. Una pausa de 30 ms para indicar que el tipo de datos han sido
* Transferidos.
* 3. Leer el sensor para recibir una confirmación del tipo de datos.
* 4. Leer el sensor para recibir 35 bytes (carácter #) para
* Indicar el inicio de la transferencia de datos.
* 5. Reciba una matriz de bytes con los
* datos reales. 
* 
* 
*/
    // Protocolo paso 1
    for (int h = 1; h <= dataType + 1; h++) {
        getData(0, readResponse, 1);
        try {
            Thread.sleep(10);
        } catch (Exception e) {
        }
}
     // Protocolo paso 2
     try {
         Thread.sleep(30);
     } catch (Exception E) {
     }

     // Protocolo paso 3
     try {
         getData(0, readResponse, 1);
     } catch (Exception e) {
         readResponse[0] = 0;
     }

     byteChar = readResponse[0];
     returnInt = (int) byteChar; // convertir un byte a entero

     if (returnInt == dataType) { // comprobar si el entero recibido del
                                  // sensor es igual al tipo de datos
                                  // que queremos
         data = true;
      } else {
         message = "error"; // si el paso ha fallado, el mensaje de error será
                            // enviado
         data = false;
      }

      if (data) { // si no hubo error continuar con el protocolo
         try {
             Thread.sleep(10);
          } catch (Exception e) {
          }

          // Protocolo paso 4
          try {
              getData(0, readResponse, 1);
          } catch (Exception e) {
              readResponse[0] = 0;
          }

          if (readResponse[0] == 35) // 35 es el signo #, significa que la transferencia de datos
                                     // esta empezando despues de aqui
          {
              // Protocolo paso 5
              try {
                  Thread.sleep(10);
              } catch (Exception e) {
              }
            
              try {
                  getData(0, readData, 11); // leer la cadena
                                            //(transferencia en bytes) del
                                            // sensor
              } catch (Exception e) {
                  for (int i = 0; i < readData.length; i++) {
                       readData[i] = 0;
                  }
              }

              for (int j = 0; j < readData.length; j++) {
                  String tempstring = ""; // crear una cadena de bytes, 
                                          // Por el encasillamiento de los bytes 
                                          // a chars
                  tempstring += (char) readData[j];

                  try {
                      int tempint = Integer.parseInt(tempstring);
                      // Para la aplicación actual solo se usaran enteros 
                      // comprobar si se trata de una cadena
                      message += tempstring;
                   } catch (Exception e) {
                   }
                }
            } else {
                message = "error";
            }
      }
      return (message);
}
 
public String doReading(int dataType) {
/* Esta función es una función de "rescate" para la actual funcion "gpsTransaction"
* Si el gpsTransaction falla, esta función se repetirá 
* por una cantidad fija de veces.
*/

       String message = ""; // variable para almacenar el mensaje por el sensor 
       boolean succes = false;
       int counter = 0; // contador para ver cuántas veces hemos lo hemos intentado 

       while (!succes) {
           message = gpsTransaction(dataType); // la llamada al sensor GPS 
                                               // Función de transacción 

           if (counter >= 5) { // si no recibimos un resultado de cinco 
                               // veces, dejamos de tratar esta convocatoria 
               message = "timeout";
           }

           if (message.equals("error")) {
           // Si se recibe un error de la función GPS de transacción entonces,
           // no tendremos exito
               succes = false;
               message = "error";
           } else {
           //Si no lo hemos conseguido y podemos dejar de intentarlo. 
               succes = true;
           }
 
           counter++;
           
           try {
               /*
                * Pausa por un corto tiempo antes de volver a intentarlo.
                * Tenga en cuenta que el total de repeticiones de esta función 
                * No debe ser más grande que el tiempo entre lecturas de los sensores. 
                */
               Thread.sleep(100);
           } catch (Exception e) {
           }
      }
      return (message);
}

public float toDecimalDegrees(String x, String type) {
    /*
     * Las variables de latitud y longitud a partir del sensor GPS se encuentran en 
     * formato de grados y minutos.
     * Para poder hacer los cálculos con que tenemos que convertir a 
     * grados decimales.
     * Podemos hacer esto mediante el fraccionamiento de los grados y parte minutos,
     * la parte en minutos por 60.
     */
    float degrees = 0.0f;
    float minutes = 0.0f;
    float decimals = 0.0f;
    String temp;
 
    if (type.equals("lat")) {
        try {
            degrees = Integer.parseInt(x.substring(0, 2));
            temp = x.substring(2, 4) + "." + x.substring(4, x.length());
            minutes = Float.parseFloat(temp);
            minutes = minutes / 60;
            decimals = degrees + minutes;
         } catch (Exception e) {
             decimals = 0.0f;
         }
} else if (type.equals("long")) {
    try {
        degrees = Integer.parseInt(x.substring(0, 3));
        temp = x.substring(3, 5) + "." + x.substring(5, x.length());
        minutes = Float.parseFloat(temp);
        minutes = minutes / 60;
        decimals = degrees + minutes;
     } catch (Exception e) {
         decimals = 0.0f;
     }
  }
  return decimals;
}

public float calculateBearing(double lat_from, double long_from,
        double lat_to, double long_to) {
    /*
     * Teniendo el angulo entre dos puntos latitud, longitud.
     * Esto es necesario para navegar por el robot en la dirección correcta. 
     */
     // primero todos los puntos son calculados en grados a radianes 
     double lat_from_rad = Math.toRadians(lat_from);
     double lat_to_rad = Math.toRadians(lat_to);
     double long_from_rad = Math.toRadians(long_from);
     double long_to_rad = Math.toRadians(long_to);

     // La distancia entre los dos puntos de longitud 
     double dLong = long_to_rad - long_from_rad;
    
     // Cálculo del ángulo de orientación 
     double y = Math.sin(dLong) * Math.cos(lat_to_rad);
     double x = Math.cos(lat_from_rad) * Math.sin(lat_to_rad)
             - Math.sin(lat_from_rad) * Math.cos(lat_to_rad)
             * Math.cos(dLong);
     return toBearing((float) (Math.atan2(y, x)));
}

public double calculateDistance(double lat1, double lon1, double lat2,
       double lon2) {
    /* Cálculo de la distancia con dos puntos de latitud y longitud 
     * Usando la ley de los cosenos esférica. Esta fórmula utiliza una esférica 
     * modelo de la tierra
     */

     double L1 = lat1;
     double G1 = lon1;
     double L2 = lat2;
     double G2 = lon2;
     
     //convertir a radianes

     L1= Math.toRadians(L1);
     L2= Math.toRadians(L2);
     G1= Math.toRadians(G1);
     G2= Math.toRadians(G2);

     double a = Math.pow(Math.sin((L2 - L1) / 2), 2) + Math.cos(L1)
             * Math.cos(L2) * Math.pow(Math.sin((G2 - G1) / 2), 2);

     // Distancia ortodrómica en radianes 
     double angle = Math.toDegrees(Math.sqrt(a));

     // Cada grado en un gran círculo de la Tierra es de 60 millas náuticas (111 120 
     // metros)
     double distance = 111120 * angle;

     return distance;
}

public boolean inRange(double lat1, double lon1, double lat2,
        double lon2) {
    /* Un método para ver si el robot está en el rango de latitud y longitud 
     * Punto que utiliza menos potencia de cálculo. Esto es necesario porque 
     * Tanto la Haversine y la ley de los cosenos esférica no puede ser 
     * calculado en Lejos.
     */
     boolean inrange = false;
     float difference_lat = (float)Math.abs(lat2 - lat1);
     float difference_lon = (float)Math.abs(lon2 - lon1);

     if(difference_lat < 0.0001 && difference_lon < 0.0001)
     {
          inrange = true;
     }
     
     return inrange;
 }

 private float toBearing(float rad) // convertir radianes a grados (como 
 // rumbo: 0...360)
 {
      return (float) ((int) (Math.toDegrees(rad)) % 360);
 }
}
