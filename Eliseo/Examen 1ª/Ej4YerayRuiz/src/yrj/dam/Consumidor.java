package yrj.dam;

import java.util.Date;
import java.util.ArrayList;

/**
 * Class that consumes a message from productor
 * Furthermore, it has the main()
 * @author Yeray Ruiz Juárez
 */
class Consumidor extends Thread {
	Productor prod;

	/**
	 * Initial method
	 * @param args Null/Empty
	 */
	public static void main(String[] args) {
		Productor productor = new Productor();
		Consumidor consumidor = new Consumidor(productor);
		productor.start();
		consumidor.start();
		try {
			productor.join();
			consumidor.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Constructor
	 * @param prod Object used to call leerMensaje()
	 */
	public Consumidor(Productor prod) {
		this.prod = prod;
	}

	/**
	 * Method called when Thread starts
	 */
	@Override
	public void run() {
		while(true) {
			System.out.println(this.prod.leerMensaje());
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

/**
 * Method that produces message, and return message
 * @author Yeray Ruiz Juárez *
 */
class Productor extends Thread{
	private final int COLAMAXIMA = 5; //Limit of mensajes's size
	private ArrayList<String> mensajes;
	
	/**
	 * Constructor
	 */
	public Productor() {
		mensajes = new ArrayList<>();
	}
	
	/**
	 * Method called when Thread starts
	 */
	@Override
	public void run() {
		while(true) {
			try {
				escribirMensaje();
				System.out.println("Fecha emitida");
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Method that read the message with position 0
	 * @return Return the message with the date
	 */
	public synchronized String leerMensaje(){
		while(mensajes.size() == 0) {
			try {
				wait();
			} catch (InterruptedException e) {
			}
		}
		String mensaje = mensajes.get(0);
		mensajes.remove(0);
		notify();
		return mensaje;
	}
	
	/**
	 * Method that add a message with the current date
	 */
	private synchronized void escribirMensaje() {
		while(mensajes.size() >= COLAMAXIMA) {
			try {
				wait();
			} catch (InterruptedException e) {
			}
		}
		mensajes.add(new Date().toString());
		notify();
	}
}