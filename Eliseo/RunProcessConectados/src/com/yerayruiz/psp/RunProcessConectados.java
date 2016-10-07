package com.yerayruiz.psp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RunProcessConectados {
	public static void main(String[] args) {
		if (args.length <= 0){
			System.err.println("Falta el parámetro a ejecutar");
			System.exit(-1);
		}
		ProcessBuilder pb = new ProcessBuilder(args);
		pb.redirectErrorStream(true);
		
		try{
			Process proceso = pb.start();
			
			MostrarSalidaProceso(proceso);
			System.exit(0);
		}
		catch (IOException ex){
			System.err.println("Error de E/S");
			System.exit(-1);
		}
	}

	private static void MostrarSalidaProceso(Process proceso) {
		InputStreamReader lector = null;
		BufferedReader br = null;
		try {
		int retorno = proceso.waitFor();
		System.out.println("La ejecución del proceso devuelve: " + retorno);
		lector = new InputStreamReader(proceso.getInputStream(), "UTF-8");
		String linea;
		br = new BufferedReader(lector);
		while ((linea = br.readLine()) != null) {
			System.out.println(linea);
		}
		} catch(Exception e){
			System.err.println(e.getMessage());
		}
		finally {
			if(br != null)
				try {
					br.close();
				} catch (Exception e) {
					System.err.println(e.getMessage());
					
				}
		}
		
	}
}
