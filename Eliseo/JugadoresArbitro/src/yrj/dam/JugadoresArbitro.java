package yrj.dam;

import java.util.Iterator;

public class JugadoresArbitro {

	public static void main(String[] args) {
		int nJugadores = 5;
		Arbitro collina = new Arbitro(nJugadores);
		for (int i = 1; i <= nJugadores; i++) {
			new Jugador(i,collina).start();
		}
	}
}

class Arbitro {
	private int numJugadores;
	private int turno; //A quién le toca jugar
	private int objetivo; //Número a adivinar
	private boolean acertado; //Flag para determinar si hemos terminado
	public final int MAXIMO = 100;
	
	public Arbitro(int nJugadores) {
		this.numJugadores = nJugadores;
		objetivo = 1 + (int) (MAXIMO * Math.random());
		turno = 1 + (int) (numJugadores * Math.random());
		System.out.println("El árbitro ha sacado el número: "+objetivo);
	}
	public synchronized boolean seAcabo() {
		return acertado;
	}
	public synchronized int esTurnoDe() {
		return turno;
	}
	public void jugar(int jugador, int jugada) {
			if (jugador == turno) {
				System.out.println("El jugador "+ jugador + " ha dicho: "+jugada);
				if (objetivo == jugada) {
					acertado = true;
					System.out.println("El jugador "+ jugador + " ha acertado el número");
				} else {
					turno = ((turno++) % numJugadores) + 1;
					notify();
				}
			}else
				System.out.println("El jugador " + jugador + " está haciendo trampas");
	}
}
class Jugador extends Thread{
	int id;
	Arbitro arbitro;
	public Jugador(int dorsal, Arbitro arbitro) {
		this.id = dorsal;
		this.arbitro = arbitro;
	}

	@Override
	public void run(){
		while (!arbitro.seAcabo()) {
			if (arbitro.esTurnoDe() == this.id) {
				int jugada = 1 + (int) (arbitro.MAXIMO * Math.random());
				arbitro.jugar(id,jugada);
			}
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}		
	}
}
