package yrj.psp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Streams {
	public static void main(String[] args) {
		try {
			Process processA = new ProcessBuilder("ls","/home/usuario/Escritorio/").start();
			Process processB = new ProcessBuilder("tr","a", "@").start();
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(processA.getInputStream(), "UTF-8"));
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(processB.getOutputStream(), "UTF-8"));
			
			String resultado = "";
			System.out.println("Proceso A: ");
			while ((resultado = reader.readLine()) != null) {
				System.out.println(resultado);
				writer.write(resultado + "\n");
			}
			reader.close();
			writer.close();

			System.out.println("\nProceso A + B:");
			BufferedReader reader2 = new BufferedReader(new InputStreamReader(processB.getInputStream(), "UTF-8"));
			while ((resultado = reader2.readLine()) != null) {
				System.out.println(resultado);
			}
			reader2.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
