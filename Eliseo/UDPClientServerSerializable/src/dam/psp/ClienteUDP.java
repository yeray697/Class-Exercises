package dam.psp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ClienteUDP {

	public ClienteUDP() {
		DatagramSocket socket = null;
		try {
			socket = new DatagramSocket(Constantes.PUERTO_CLIENTE,
					InetAddress.getByName(Constantes.HOST_CLIENTE));
			//DatoUDP elDato = new DatoUDP("Vendo opel corsa", 659845545);
			DatoUDP elDato = new DatoUDP("Contador asc/desc pls", 6969);
			byte[] elDatoEnBytes = elDato.toByteArray();
			DatagramPacket packet = new DatagramPacket(elDatoEnBytes, 
					elDatoEnBytes.length, 
					//InetAddress.getByName(Constantes.HOST_SERVIDOR),
					//Constantes.PUERTO_SERVIDOR);
					InetAddress.getByName("192.168.3.57"),
					Constantes.PUERTO_SERVIDOR);
			for (int i = 0; i < 3; i++) {
				System.out.println("Envío de dato " + i);
				socket.send(packet);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (socket != null) {
				socket.close();
				socket = null;
			}
		}
		
	}
	public static void main(String[] args) {
		new ClienteUDP();
	}

}
