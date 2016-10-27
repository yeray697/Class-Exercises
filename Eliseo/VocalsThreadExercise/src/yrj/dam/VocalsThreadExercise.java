package yrj.dam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Crea un clase Java que utilice 5 hilos para contar el número de vocales que hay en un determinado texto. 
 * Cada hilo se encargará de contar una vocal diferente. 
 * Todos los hilos actualizarán la misma variable común que indica el número total de vocales encontradas. 
 * Para evitar condiciones de carrera (data race) hay que utilizar métodos synchronized.
 * 
 * @author yeray697
 * @version 1.0
 */
public class VocalsThreadExercise {

	/**
	 * First method that is executed
	 * @param args Arguments that you can pass
	 */
	public static void main(String[] args) {
		String text = "";
		//If you do not pass a String on arguments, the application ask for a String
		if(args.length == 0) {
			text = askForText();
		}
		else {
			text = args[0];
		}
		countVocals(text);
	}
	
	/**
	 * Method that count vocals that text contains
	 * @param text Text that will be analyzed
	 */
	private static void countVocals(String text) {
		//Initializing threads
		VocalThread aThread,eThread,iThread,oThread,uThread;
		aThread = new VocalThread('a', text);
		eThread = new VocalThread('e', text);
		iThread = new VocalThread('i', text);
		oThread = new VocalThread('o', text);
		uThread = new VocalThread('u', text);
		//Starting threads
		aThread.start();
		eThread.start();
		iThread.start();
		oThread.start();
		uThread.start();
		//Joining threads
		aThread.join();
		eThread.join();
		iThread.join();
		oThread.join();
		uThread.join();
		//Getting the number of vocals that appears
		System.out.println("Número de vocales que aparecen: "+GlobalVar.getVocals());
	}

	/**
	 * Method that ask for a text. If you do not write anything, it ask it again until you write something
	 * @return Return the text you wrote
	 */
	private static String askForText() {
		String input = "";
		do {
			try {
				System.out.println("Escribe el texto del cual se contarán el número de vocales que contiene.");
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
				input = bufferedReader.readLine();
				if (input.trim().length() == 0) {
					input = "";
					System.out.println("No has escrito nada");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} while ( input == "" );
		return input;
	}
}

/**
 * Class that implements Runnable that calculate how many vocals have a text
 * @author Yeray Ruiz
 */
class VocalThread implements Runnable{

	Thread thread;
	String text;	//Text where we will try to search vocals
	char vocal;		//Vocal that is going to be searched in the text
	
	/**
	 * Constructor
	 * @param vocal Char that will be searched at the text
	 * @param text Text where char will be searched
	 */
	public VocalThread(char vocal, String text) {
		this.thread = new Thread(this);
		this.vocal = vocal;
		this.text = text;
	}

    /**
     * Method implemented from Runnable that run a thread
     */
	@Override
	public void run() {
		for (int i = 0; i < text.length(); i++) {
			if (this.vocal == text.charAt(i)) {
				GlobalVar.increaseVocal();
			}
		}
	}
	
	/**
	 * Method that call start the thread
	 */
	public void start() {
		this.thread.start();
	}
	
	/**
	 * Method that join the thread
	 */
	public void join() {
		try {
			this.thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

/**
 * Class that increases a variable, using a synchronized sentence
 * @author Yeray Ruiz
 */
class GlobalVar{
	private static int vocals = 0; //Number of vocals found

	private static final Object mutex = new Object();
	
	/**
	 * Method that increase the variable 'vocals'
	 */
	public static void increaseVocal() {
		synchronized (mutex) {
			vocals++;
		}
	}
	
	/**
	 * Method that get the number of vocals found
	 * @return Number of vocals found
	 */
	public static int getVocals() {
		return vocals;
	}
}
