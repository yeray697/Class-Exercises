package yrj.dam;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;

public class ClienteUDP {
	public static final int SRVPort = 5555;
	//public static final String SRVIP = "192.168.2.107";
	public static final String SRVIP = "192.168.3.57";
	
	public static final int CLIPort = 5556;
	public static final String CLIIP = "0.0.0.0";
	
	public ClienteUDP(String messageToSend){
		String message = messageToSend;
		DatagramSocket socket = null;
		try {
			socket = new DatagramSocket(CLIPort,InetAddress.getByName(CLIIP));
			DatagramPacket packet = new DatagramPacket(message.getBytes(), 255);
			packet.setAddress(InetAddress.getByName(SRVIP));
			packet.setPort(SRVPort);
			socket.send(packet);
			socket.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	public static void main(String[] args) {
		new ClienteUDP("Holi");
	}
	
}
