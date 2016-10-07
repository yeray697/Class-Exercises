package com.yerayruiz.psp;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.util.Arrays;

public class RunProcess {
	public static void main(String[] args){
		if(args.length <= 0){
			System.err.println("Debe ser un ejecutable");
			System.exit(-1);
		}
		ProcessBuilder pb = new ProcessBuilder(args);
		
		String pid;
		pid = ManagementFactory.getRuntimeMXBean().getName();
		System.out.println("El PID del padre es "+pid);
		
		try {
			Process proceso = pb.start();
			int retorno = proceso.waitFor();
			
			System.out.println("La ejecución del proceso "+ Arrays.toString(args) + " devuelve "+retorno);
		}
		catch(IOException e){
			System.err.println("Error general de E/S");
			System.exit(-1);
		} catch (InterruptedException e) {
			System.err.println("El proceso hijo finalizó de forma inesperada");
			System.exit(-1);
		}
	}
}
