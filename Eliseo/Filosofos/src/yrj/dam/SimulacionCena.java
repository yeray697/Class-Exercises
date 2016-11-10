package yrj.dam;

import java.util.Random;

class Palillo {
	int numero;
	boolean enUso;
	public Palillo(int x) {
		numero = x;
		enUso = false;
	}
	public synchronized void coger(){
		while (enUso) {
			System.out.println("Palillo " + numero + " ocupado, espera");
			try {
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		enUso = true;
		System.out.println("Palillo " + numero + " ha sido cogido!");
	}
	public synchronized void soltar(){
		enUso = false;
		System.out.println("Palillo " + numero + " ha sido soltado");
		this.notify();
	}
}
class Cena{
	Palillo palillos[];
	int comensales = 0;
	public Cena(int invitados) {
		this.comensales = invitados;
		palillos = new Palillo[this.comensales];
		for (int i = 0; i < palillos.length; i++) {
			palillos[i] = new Palillo(i);
			
		}
	}

	public Palillo cogerPalillo(int x){
		return palillos[x];
	}
	
	public int cogerPalilloDer(int x) {
		return (x + 1) %comensales;
	}

	public int cogerPalilloIzq(int x){
		return x;
	}
}

class Filosofo extends Thread{
	private Cena cena;
	private int pizq, pder;
	private int numero; //Posición en la mesa
	private int veces; //Veces que va a cenar
	public Filosofo(int num, int almuerzos, Cena unaCena) {
		this.numero = num;
		this.veces = almuerzos;
		this.cena = unaCena;
		pizq = cena.cogerPalilloIzq(this.numero);
		pder = cena.cogerPalilloDer(this.numero);
	}
	
	public void	pensar() {
		//Tiempo aleatorio entre 1 y 1000 ms
		int minimum = 1,
				maximum = 1000;
		System.out.println("Filósofo "+numero+" está pensando");
		randomSleep(minimum, maximum);
		System.out.println("Filósofo "+numero+" ha terminado de pensar y tiene hambre");
	}

	public void	cogerPalillos() {
		if (numero % 2 == 0) {
			cena.cogerPalillo(pizq).coger();
			cena.cogerPalillo(pder).coger();
		} else {
			cena.cogerPalillo(pizq).coger();
			cena.cogerPalillo(pder).coger();
			//cena.cogerPalillo(pizq).coger();
		}		
	}
	public void	comer() {
		//Tiempo aleatorio entre 1 y 2000 ms
		int minimum = 3,
				maximum = 5000;
		System.out.println("Filósofo "+numero+" está comiendo");
		randomSleep(minimum, maximum);
		//System.out.println("Filósofo "+numero+" ha terminado de comer");
	}
	public void soltarPalillos(){
		System.out.println("Filósofo "+numero+" suelta sus palillos");
		cena.cogerPalillo(pizq).soltar();
		cena.cogerPalillo(pder).soltar();
	}
	
	@Override
	public void run(){
		for (int i = 0; i < veces; i++) {
			pensar();
			cogerPalillos();
			comer();
			soltarPalillos();
		}
	}

	private void randomSleep(int minimum, int maximum) {
		int randomNum = minimum + (int)(Math.random() * (maximum + 1)); 
		try {
			Thread.sleep(randomNum);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

public class SimulacionCena {

	public static void main(String[] args) {
		int comensales = Integer.parseInt(args[0]);
		int almuerzos = Integer.parseInt(args[1]); //Cantidad de veces que van a comer
		
		Cena cena = new Cena(comensales);
		Filosofo[] filosofos = new Filosofo[comensales];
		for (int i = 0; i < comensales; i++) {
			filosofos[i] = new Filosofo(i, almuerzos, cena);
			filosofos[i].start();
		}
		for (int i = 0; i < comensales; i++) {
			try {
				filosofos[i].join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
