package yrj.psp;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.UnknownHostException;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

public class Cliente {

	static final String DEST = "localhost";
	static final int PUERTO = 5555;
	
	public Cliente(String mensaje) throws UnknownHostException, IOException {
		SSLSocketFactory socketCLIFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
		System.out.println("Creando socket en el cliente");
		SSLSocket socketCli = (SSLSocket) socketCLIFactory.createSocket(DEST, PUERTO);
		
		mostrarCifrados(socketCli);
		
		BufferedOutputStream bo = new BufferedOutputStream(socketCli.getOutputStream());
		PrintWriter pw = new PrintWriter(bo);
		pw.println(mensaje);
		pw.flush(); //ojo
		
		//Ahora nos preparamos para recibir la respuesta del servidor
		InputStreamReader is = new InputStreamReader(socketCli.getInputStream());
		BufferedReader br = new BufferedReader(is);
		
		System.out.println("Mensaje devuelto desde el servidor: "+br.readLine());
		
		System.out.println("Cerrando socket");
		pw.close();
		socketCli.close();
		System.out.println("Finalizado...");
	}
	
	public static void main(String[] args) throws IOException {
		System.setProperty("javax.net.ssl.keyStore", "./cert/yerayCLI");
		System.setProperty("javax.net.ssl.keyStorePassword", "12345678");

		System.setProperty("javax.net.ssl.trustStore", "./cert/yerayCLI");
		System.setProperty("javax.net.ssl.trustStorePassword", "12345678");
		
		String mensaje = "Cago en los muertos de JAVA veinte millones de veces";
		new Cliente(mensaje);
	}
	
	private void mostrarCifrados(SSLSocket socket){
		String[] protocolosSoportados = socket.getSupportedProtocols();
		System.out.println("Protocolos soportados: ");
		for (int i = 0; i < protocolosSoportados.length; i++) {
			System.out.println(protocolosSoportados[i]);
		}
		
		String[] protocolosMolones = new String[1];
		protocolosMolones[0] = "TLSv1.2";
		socket.setEnabledProtocols(protocolosMolones);

		String[] protocolos = socket.getEnabledProtocols();
		System.out.println("Protocolos habilitados: ");
		for (int i = 0; i < protocolos.length; i++) {
			System.out.println(protocolos[i]);
		}
		
		protocolos = socket.getEnabledProtocols();
		System.out.println("Protocolos habilitados: ");
		for (int i = 0; i < protocolos.length; i++) {
			System.out.println(protocolos[i]);
		}
	}
}
