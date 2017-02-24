package yrj.psp;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.net.ssl.SSLSocket;

public class SrvHilo extends Thread{

	SSLSocket miSocket;
	
	public SrvHilo(SSLSocket socketAtencion) {
		this.miSocket = socketAtencion;
	}
	
	@Override
	public void run(){
		InputStreamReader is;
		try {
			is = new InputStreamReader(miSocket.getInputStream());
			BufferedReader br = new BufferedReader(is);
			String mensajeRecibido = br.readLine();
			System.out.println("Mensaje recibido: " + mensajeRecibido);
			
			//Ahora enviamos una respuesta hacia el cliente
			BufferedOutputStream bo = new BufferedOutputStream(miSocket.getOutputStream());
			PrintWriter pw = new PrintWriter(bo);
			byte[] mensajeEnBytes = mensajeRecibido.getBytes("utf8");
			MessageDigest sha = MessageDigest.getInstance("SHA-256");
			pw.println(sha.digest(mensajeEnBytes));
			pw.flush(); //IMPORTANTE
			pw.close();
			System.out.println("Cerrando el hilo cliente");
			miSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
