package robot.robonce;
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
	Voicemanager vm;
	LightSensor light;
	Producto productos [];
    //Lectura por Teclado:
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
    String linea;   
	//Constructores:
	
	public Navegar (LightSensor light, Producto productos [], Voicemanager vm){
		
		this.light=light;
		this.productos=productos;
		this.vm=vm;
		
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
		   	System.out.println(light.readValue());
		   	System.out.println("Posicion: "+posicion);
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
        		/*
        		System.out.println("Escoga el producto.");
        		System.out.println("1.Pan");
            	System.out.println("2.Leche");
            	System.out.println("3.Cereales");
            	System.out.println("4.Mermelada");
            	*/
        		vm.say("Choose a product");
        		vm.say("ham");
        		vm.say("cheese");
        		vm.say("orange");
        		vm.say("rise");
        		vm.say("Good Bye");
        		try 
                {
                	Thread.sleep(500);
                } 
                catch (Exception e) 
                {
                	e.printStackTrace();
                }  
        		String opcion= vm.listen();
            	//System.out.println("He escuchado: "+opcion);
            	System.out.println("Leyendo: "+opcion);
            	int i=0;
            	boolean sw=false;
            	
            	for(i=0;i<4;i++)
            	{
                	if(productos[i].get_Nombre().equals(opcion))
                	{
                		sw=true;
                		opc=i;
                	}
            	}
            	
            	if(sw==false)
            	{
            		vm.say("Product not found.");
            		opc=-1;
            	}
   
            	//GOODBYE.
            	
            	 
            	/*
            	try{
                 	
                	opc = Integer.parseInt(br.readLine())-1;
                }
                catch(Exception e){ 
                	
                	e.printStackTrace();
                }  
                */
                
              
                
        	}while(opc<0 || opc>3);
        	
        	System.out.println("OPC es: "+opc);
        	return opc;
     }
	
}
