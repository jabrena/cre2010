package robot.robonce;

import java.util.Locale;

import javax.speech.EngineCreate;
import javax.speech.EngineList;
import javax.speech.synthesis.Synthesizer;
import javax.speech.synthesis.SynthesizerModeDesc;

import com.sun.speech.freetts.jsapi.FreeTTSEngineCentral;

import edu.cmu.sphinx.recognizer.Recognizer;

public class Tts {
	
	//Atributos:
	Synthesizer synthesizer;
	Recognizer recognizer;

	//Constructores:
	public Tts (){
		//TTS Set UP
		try {
		
			// create SynthesizerModeDesc that will match the FreeTTS Synthesizer
			SynthesizerModeDesc modeDesc = new SynthesizerModeDesc(null,"general", Locale.ENGLISH, Boolean.FALSE, null);
			
			FreeTTSEngineCentral central = new FreeTTSEngineCentral();
			 synthesizer = null;
			
			EngineList list = central.createEngineList(modeDesc);
			if (list.size() > 0) {
				EngineCreate creator = (EngineCreate) list.get(0);
				synthesizer = (Synthesizer) creator.createEngine();
			}
		
			if (synthesizer == null) {
				System.err.println("Cannot create synthesizer");
				System.exit(1);
			}
							
			//get ready to speak
			synthesizer.allocate();
			synthesizer.resume();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	//Métodos
	
	public void say (String frase){
		try{

				
			synthesizer.speakPlainText(frase, null);
			synthesizer.waitEngineState(Synthesizer.QUEUE_EMPTY);
			//synthesizer.
		
			//synthesizer.deallocate();	
		}
		catch (Exception e){
			e.printStackTrace();

		}
	}
}
