package com.yrj.psp;
class Contador{
	public static volatile int cuenta = 0;
}
class Sumador extends Thread{
	public void run() {
		for (int i = 0; i < 10000; i++) {
			Contador.cuenta++;
		}
	}
}
class Restador extends Thread{
	public void run() {
		for (int i = 0; i < 5000; i++) {
			Contador.cuenta--;
		}
	}
}
public class HilosTestCondicionCarrera {

	public static void main(String[] args) {
		Sumador sumador = new Sumador();
		Restador restador = new Restador();
		sumador.start();
		restador.start();
		try {
			sumador.join();
			restador.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Contador vale finalmente: "+Contador.cuenta);
	}

}
