package gps;

import compasNavigator;
import lejos.navigation.CompassNavigator;
import lejos.navigation.CompassPilot;
import lejos.nxt.*;

/**
* @author Martijn ten Bhömer
* 
*/

public class i2c_test extends Thread {
    //variable para almacenar la posición actual
    private static double[] currentPos = new double[] { 0.0, 0.0 };

    //matriz con los puntos para viajar a 
    private static double[][] travelTo = new double[][] {
            { 51.181465, 5.9576449 }, { 51.181652, 5.9582815 } };
    
    //punto de destino actual desde la matriz destino
    public static int gotoPoint = 0;

    //cadenas temporales para almacenar la latitud y longitud
    String latitude;
    String longitude;

    //floats para almacenar la latitud y longitud cuando se les ha encasillado
    float latitude_decimals = 0.0f;
    float longitude_decimals = 0.0f;

    //variables para ver si el robot está dentro del rango de su destino
    public static boolean inrange = false;
    double distance = 0.0f;

    //el ángulo entre el punto actual y destino
    public static float bearing = 0.0f;
    int distance_meters = 0;

    //variable para comprobar si estamos utilizando datos antiguos o nuevos
    boolean buffer = false;

    //iniciar las clases
    public static GPSSensor gps;
    public static CompassPilot pilot;
    public static CompassNavigator navigator;

    public static void main(String[] args) {
        LCD.clear();
        LCD.drawString("calibrating", 0, 0);
        LCD.refresh();

        gps = new GPSSensor(SensorPort.S1); // crear un nuevo objeto GPSSensor

    //variables Compass navigator
    float WheelDiameter = 5.6f;
    float TrackWidth = 18;

    //Maxima velocidad 
    Motor.A.setPower(100);
    Motor.B.setPower(100);

    //Crear el objeto Compass navigator, esto nos ayudará a navegar
    //uso del compass digital.
    pilot = new CompassPilot(SensorPort.S2, WheelDiameter, TrackWidth,
            Motor.A, Motor.B);
    navigator = new CompassNavigator(pilot);
    navigator.calibrateCompass();

    //Crear y activar el hilo de navegación
    Thread gpsThread = new i2c_test();
    gpsThread.start();

    while (true) {
        // todos los botones y salidas de LCD
        LCD.drawString("heading " + pilot.getAngle(), 0, 5);
        LCD.refresh();

        if (Button.LEFT.isPressed()) {
            LCD.clear();
            LCD.drawString("calibrating", 0, 0);
            LCD.refresh();
            navigator.calibrateCompass();
        }

        if (Button.ESCAPE.isPressed()) {
            System.exit(0);
        }
     }
}
public void run() {
    while (true) {
       	// leer la información de latitud
	latitude = gps.doReading(2);

	// pausa de 200 ms
	try {
	    Thread.sleep(200);
	} catch (Exception E) {
	}

	//leer los datos de longitud
	longitude = gps.doReading(4);

	//convertir latitud y longitud en grados decimales
	latitude_decimals = gps.toDecimalDegrees(latitude, "lat");
	longitude_decimals = gps.toDecimalDegrees(longitude, "long");

	buffer = false;

	if (latitude_decimals > 0) {
	    // tiene que ser mayor que cero
	    currentPos[0] = latitude_decimals;
	} else {
	    // sino utilizamos los datos desde el buffer
	    buffer = true;
	}
	if (longitude_decimals > 0) {

	currentPos[1] = longitude_decimals;
    } else {
	buffer = true;
    }

	//calcular los datos utilizando la ley de los cosenos esférica, 
	//por desgracia la función raíz cuadrada de Lejos no es lo suficientemente bueno 
	//distance = gps.calculateDistance(currentPos[0],
	//currentPos[1],
	//travelTo[0][0], travelTo[0][1]);
	//distance_meters = (int)(Math.abs(distance));
	
	// Por lo tanto (por ahora) usar la función Inrange 
	inrange = gps.inRange(currentPos[0], currentPos[1],
	        travelTo[gotoPoint][0], travelTo[gotoPoint][1]);

	// Calcular el rumbo en relación con el destino 
	bearing = (int) Math.abs(gps.calculateBearing(currentPos[0],
	        currentPos[1], travelTo[gotoPoint][0],
	        travelTo[gotoPoint][1]));
	
	// Imprimir todo en la pantalla
	LCD.clear();
	LCD.drawString("lat " + currentPos[0], 0, 0);
	LCD.drawString("long " + currentPos[1], 0, 1);
	LCD.drawString("inrange " + inrange, 0, 3);
	LCD.drawString("bearing " + bearing, 0, 4);
	LCD.drawString("buffer " + buffer, 0, 6);
	LCD.refresh();

	// Deja que el robot gire al rumbo calculado utilizando el compass digital
	
	pilot.rotateTo((int) bearing);

	if (!inrange) {
	// si no llega todavia lo controlaremos por dos segundos

	    Motor.A.forward();
	    Motor.B.forward();

	    try {
	        Thread.sleep(2000);
	    } catch (Exception E) {
	    }

	    Motor.A.stop();
	    Motor.B.stop();
	} else {	
	// si llegamos ir al siguiente punto de destino de la matriz
	    if (gotoPoint == 0) {
	        gotoPoint = 1;
	    } else {
	        gotoPoint = 0;
	    }
	}

	LCD.clear();
	LCD.drawString("lat " + currentPos[0], 0, 0);
	LCD.drawString("long " + currentPos[1], 0, 1);
	LCD.drawString("inrange " + inrange, 0, 3);
	LCD.drawString("bearing " + bearing, 0, 4);
	LCD.drawString("point " + gotoPoint, 0, 6);
	LCD.refresh();

	try {
	    Thread.sleep(1000);
	} catch (Exception E) {
        }
      }
   }
}

