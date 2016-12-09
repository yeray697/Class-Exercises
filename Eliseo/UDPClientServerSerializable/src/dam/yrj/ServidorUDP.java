package dam.yrj;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class ServidorUDP {

	public ServidorUDP() {
		DatagramSocket socket = null;
		try {
			socket = new DatagramSocket(Constantes.PUERTO_SERVIDOR,InetAddress.getByName(Constantes.HOST_SERVIDOR));
			DatagramPacket dato = new DatagramPacket(new byte[255], 255);
			DatoUDP aux;
			while (true) {
				System.out.println("Escuchando en el puerto: " + socket.getLocalPort());
				try {
					socket.receive(dato);
					System.out.println("Dato recibido de " + dato.getAddress().getHostName()+":");
					aux = DatoUDP.fromByteArray(dato.getData());
					System.out.println("Texto:  " + aux.cadenaTexto);
					System.out.println("Número: " + aux.cadenaNumero);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} catch (SocketException | UnknownHostException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new ServidorUDP();
	}

}
