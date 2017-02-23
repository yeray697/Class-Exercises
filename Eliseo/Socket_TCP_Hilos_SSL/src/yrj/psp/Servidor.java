package yrj.psp;

import java.io.IOException;

import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;

public class Servidor {

	public int PUESTO = 5555;
	
	public Servidor() throws IOException{
		System.out.println("Obteniendo factoría de socket del servidor");
		SSLServerSocketFactory socketSRVFactory = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
		System.out.println("Creando el socket de atención");
		SSLServerSocket socketSRV = (SSLServerSocket) socketSRVFactory.createServerSocket(PUESTO);
		while(true){
			System.out.println("Aceptando conexiones...");
			SSLSocket socketAtencion = (SSLSocket) socketSRV.accept();
			new SrvHilo(socketAtencion).start();
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		System.setProperty("javax.net.ssl.keyStore", "./cert/AlmacenSRV");
		System.setProperty("javax.net.ssl.keyStorePassword", "123456");

		System.setProperty("javax.net.ssl.trustStore", "./cert/AlmacenSRV");
		System.setProperty("javax.net.ssl.trustStorePassword", "123456");
		new Servidor();
	}
	
}
