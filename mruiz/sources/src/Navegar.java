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
	
	//Constructores:
	
	public Navegar (LightSensor light, Producto productos []){
		
		this.light=light;
		this.productos=productos;
		
	}
	//Métodos:
	
	public int identificar_posicion (){
		int posicion=-1;
		   	for (int i=0;i<4;i++)
        	{
        		if(productos[i].comprobar_producto(light.readValue())==true)
            	{
            		
            		posicion=i;
            	}
        	}

		return posicion;
	}
	
	public void generar_trayectoria (){
		
	}
}
