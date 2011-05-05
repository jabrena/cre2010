package robot.robonce;
/*
 * Clase: Producto.
 * Descripción: Producto del supermercado.
 * Autor: Miguel Ruiz Nogués.
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;

import lejos.nxt.LightSensor;

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
	public Producto(int clave, String nombre, LightSensor lightproducto,int color){
		
		this.clave_producto=clave;
		this.nombre=nombre;
		light = lightproducto;
		this.color=obtener_color();
		

	}
	
	
	
	//Métodos:
	/*
	 * OBTENER COLOR
	 * Asocia el color medido con el producto.
	 */
	public int obtener_color (){
		
		
		System.out.println("Producto "+nombre+". Situe el sensor en el color deseado y pulse enter...");
		
        try{
         	linea = br.readLine();
        }
        catch(Exception e){ 
        	
        	e.printStackTrace();
        } 
        

        int valor = light.readValue();
       
        System.out.println("COLOR: "+valor);
        return valor;
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
