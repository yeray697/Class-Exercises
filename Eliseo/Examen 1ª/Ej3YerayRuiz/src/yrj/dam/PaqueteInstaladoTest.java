package yrj.dam;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * Class that starts two process linked, and it shows at the end the result of processB
 * Furthermore, it has main()
 * @author Yeray Ruiz Juárez
 */
public class PaqueteInstaladoTest {
	
	final static String DPKG = "dpkg";
	final static String GET_SELECTIONS = "--get-selections";
	final static String GREP = "grep";
	final static String UTF8 = "UTF-8";
	
	/**
	 * Initial method
	 * @param args
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public static void main(String[] args) throws InterruptedException, IOException {
		Process processA, processB;
		if (args.length >= 1 && args[0] != "") {
			//Initializing process
			String argument = args[0];
			processA = new ProcessBuilder(DPKG, GET_SELECTIONS).start();
			processB = new ProcessBuilder(GREP, argument).start();
			
			//Setting buffers
			BufferedReader readerA = new BufferedReader(new InputStreamReader(processA.getInputStream(), UTF8));
			BufferedWriter writerB = new BufferedWriter(new OutputStreamWriter(processB.getOutputStream(), UTF8));
	
			String resultado = "";
			
			//Passing from reader to writer buffer
			while((resultado = readerA.readLine()) != null) {
				writerB.write(resultado + "\n");
			}
			readerA.close();
			writerB.close();
			
			//Reading the input from the last process
			BufferedReader readerB = new BufferedReader(new InputStreamReader(processB.getInputStream(),"UTF-8"));
			while((resultado = readerB.readLine()) != null) {
				System.out.println(resultado);
			}
			readerB.close();

			processA.waitFor();
			processB.waitFor();
			
			System.out.println();
			System.out.println("Código de salida de "+ DPKG+ ": "+processA.exitValue());
			//If exitValue is 1, grep did not find anything
			System.out.println("Código de salida de "+ GREP+ ": "+processB.exitValue());

		} else {
			System.err.println("Tienes que pasar un parámetro para filtrar");
		}		
	}
}