import lejos.nxt.Button;

/**
 * En la primera sesion el alumno aprendera a crear el programa mas sencillo que
 * se puede hacer con Java. El alumno aprendera de la importancia de saber 
 * entender Eclipse cuando hay errores, saber entenderlos, la inclusion de la
 * libreria LeJOS. El uso de la carpeta lib en un proyecto Java.
 * 
 * El alumno aprendera a crear documentacion y guardar el proyecto en un repositorio
 * SVN.
 * 
 * En la clase el alumno tambien aprende a manejar:
 * 
 * Comandos Unix para acceder a las carpetas y llegar a la carpeta bin
 * Comandos LeJOS para compilar/linkar
 * Subir ficheros a subversion
 * 
 * @author Juan Antonio Brenha Moral
 *
 */
public class HelloWorld {
	
	public static void main (String[] args) {
		System.out.println("Hola Mundo");
		Button.waitForPress();
	}
}