package robot.robonce;



public class Voicemanager {
	//Atributos:
	Tts tts;
	Sphnix4 sp4;
	

	//Constructores:
	public Voicemanager () {
	//TTS 
		tts= new Tts ();
		try{
			sp4=new Sphnix4();

		}
		catch(Exception e){
			
		}
	
	}
	
	//Métodos:
	
	public void say (String frase){
		tts.say(frase);
	}
	
	public String listen (){
		
		String escuchado= "";
		
		escuchado = sp4.listen();
		
		return escuchado;
	}

}

