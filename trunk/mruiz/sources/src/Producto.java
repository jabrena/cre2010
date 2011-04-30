import java.io.BufferedReader;
import java.io.InputStreamReader;

import lejos.nxt.ColorLightSensor;
import lejos.nxt.LightSensor;
import lejos.nxt.SensorPort;
import lejos.robotics.Colors.Color;

/*
 * Clase: Producto.
 * Descripción: Producto del supermercado.
 * Autor: Miguel Ruiz Nogués.
 */
public class Producto {
	//Atributos:
	private int clave_producto;
	private int color;
	private String nombre;
    //Lectura por Teclado:
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
    String linea;   
    //Light Sensor:
    LightSensor light;
    
	//Constructor
	public Producto(int clave, String nombre, LightSensor lightproducto){
		
		this.clave_producto=clave;
		this.nombre=nombre;
		light = lightproducto;
		color=obtener_color();
		

	}
	
	
	
	//Métodos:
	public int obtener_color (){
		
		System.out.println("Obteniendo el color medio de: "+nombre);
		
		System.out.println("Situe el sensor en el color deseado y pulse enter...");
        try{
            
        	linea = br.readLine();
        }
        catch(Exception e){ 
        	
        	e.printStackTrace();
        } 
        

        int valor = light.readValue();
       
        return valor;
	}
	
	public boolean comprobar_producto (int value){
		
		boolean sw = false;
		
		if(color==value){
			sw=true;
		}
		
		return sw;
	}
	public int get_Clave()
	{
		return clave_producto;
	}
	
	public int get_Color ()
	{
		return color;
	}
	public String get_Nombre (){
		return nombre;
	}
	
}
