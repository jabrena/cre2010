package robot.robonce;

	
import java.io.IOException;

import edu.cmu.sphinx.frontend.util.Microphone;
import edu.cmu.sphinx.recognizer.Recognizer;
import edu.cmu.sphinx.result.Result;
import edu.cmu.sphinx.util.props.ConfigurationManager;
import edu.cmu.sphinx.util.props.PropertyException;

public class Sphnix4 {

	//ATributos:
	ConfigurationManager cm;
	Recognizer recognizer;
	
	//Constructores:
	public Sphnix4 () throws IOException, PropertyException, InstantiationException{
	
        cm = new ConfigurationManager(Navegar.class.getResource("voice.config.xml"));
        
        
        recognizer = (Recognizer) cm.lookup("recognizer");
        recognizer.allocate();
 
        // start the microphone or exit if the program if this is not possible
        Microphone microphone = (Microphone) cm.lookup("microphone");
        if (!microphone.startRecording()) {
            System.out.println("Cannot start microphone.");
            recognizer.deallocate();
            System.exit(1);
        }
	}
	
	//Metodos:


	public String listen (){

		String resultText  = "";
  
        Result result = recognizer.recognize();
        
        if (result != null) {
            resultText = result.getBestFinalResultNoFiller();
        }else {
            System.out.println("I can't hear what you said.\n");
        }
        return resultText;
	}
	
}
