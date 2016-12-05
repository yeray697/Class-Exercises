package yrj.dam;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class ServidorUDP {
	public static final int SRVPort = 5555;
	public static final String SRVIP = "0.0.0.0";
	
	public ServidorUDP() {
		DatagramSocket socket = null;
		try {
			socket = new DatagramSocket(SRVPort,InetAddress.getByName(SRVIP));
			//Datagrama para recivir mensajes por el socket
			DatagramPacket dato = new DatagramPacket(new byte[255], 255);
			System.out.println("Servidor conectado al socket: " + 
			socket.getLocalSocketAddress());
			String received = "";
			while (true) {
				try {
					socket.receive(dato);
				} catch (IOException e) {
					e.printStackTrace();
				}
				System.out.println("Recibido dato de: " + dato.getAddress().getHostName()+":");
				//Deserializamos, suponemos que era un String
				received = new String(dato.getData(),dato.getOffset(),dato.getLength());
				System.out.println(received);
			}
		} catch (SocketException | UnknownHostException e) {
			e.printStackTrace();
		} finally {
			if (socket != null) {
				socket.close();
			}
		}
	}
	public static void main(String[] args) {
			new ServidorUDP();
	}
	
}
