import java.io.BufferedReader;
import java.io.InputStreamReader;

import lejos.nxt.LightSensor;

/*
 * Clase: Navegar
 * Descripción: Realiza la navegación
 * Autor: Miguel Ruiz Nogués.
 */

public class Navegar {
	//Atributos:
	LightSensor light;
	Producto productos [];
    //Lectura por Teclado:
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
    String linea;   
	//Constructores:
	
	public Navegar (LightSensor light, Producto productos []){
		
		this.light=light;
		this.productos=productos;
		
	}
	//Métodos:
	/*
	 * Identificat Posición.
	 * 
	 * Devuelve la posición del vector de productos donde se encuentra el robot, o -1 si no la conoce.
	 */
	public int identificar_posicion (){
		
		int posicion=-1;
		   	for (int i=0;i<4;i++)
        	{
        		if(productos[i].get_Color()==light.readValue())
            	{
            		
            		posicion=i;
            	}
        	}

		return posicion;
	}
	
	public void generar_trayectoria (){
		
	}
	/*
	 * DESTINO
	 * 
	 * Devuelve el elemento al que se desea ir.
	 */
	
	public int destino (){


        	int opc=0;
        	do{
        		System.out.println("Escoga el producto.");
        		System.out.println("1.Pan");
            	System.out.println("2.Leche");
            	System.out.println("3.Cereales");
            	System.out.println("4.Mermelada");
            	System.out.println("           Opción:");
            	
            	try{
                 	
                	opc = Integer.parseInt(br.readLine())-1;
                }
                catch(Exception e){ 
                	
                	e.printStackTrace();
                }  
                
                if(opc<0 || opc>3)
                {
                	System.out.println("Introduzca el resultado de nuevo.");
                }
                
        	}while(opc<0 || opc>3);
        	
        	return opc;
     }
	
}
