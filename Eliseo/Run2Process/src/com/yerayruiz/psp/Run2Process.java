package com.yerayruiz.psp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Run2Process {

	public static void main(String[] args) {
		ProcessBuilder pb1 = new ProcessBuilder("ls","-l","/home/usuario/");
		ProcessBuilder pb2 = new ProcessBuilder("tr","a","@");
		
		try{
			Process p1 = pb1.start();
			Process p2 = pb2.start();
			
			MostrarSalidaProceso(p1,p2);
			System.exit(0);
		}
		catch (IOException ex){
			System.err.println("Error de E/S");
			System.exit(-1);
		}
	}

	private static void MostrarSalidaProceso(Process p1, Process p2) {
		BufferedReader salida1 = null;
		BufferedWriter entrada2= null;
		try {
				salida1 = new BufferedReader(new InputStreamReader(p1.getInputStream(),"UTF-8"));
				entrada2 = new BufferedWriter(new OutputStreamWriter(p2.getOutputStream(),"UTF-8"));
				
				String resultado1 = "";
				while ((resultado1 = salida1.readLine()) != null) {
					entrada2.write(resultado1+"\n");
				}
				salida1.close();
				entrada2.close();
				
				BufferedReader salida2 = new BufferedReader(new InputStreamReader(p2.getInputStream(), "UTF-8"));
				String resultado2;
				while ((resultado2 = salida2.readLine()) != null) {
					System.out.println(resultado2);
				}
				salida2.close();
				int finA = p1.waitFor();
				int finB = p2.waitFor();
				System.out.println("\nProcesos hijos finalizados y con salida "+finA+" y "+finB);

		} catch(Exception e){
			System.err.println(e.getMessage());
		}
		finally {
		}

	}

}
