package yrj.thread.psp;

public class ThreadExample {
	public static void main(String args[]){
		//new HelloThread().start();
		new HelloThread2().start();
		System.out.println("Hola desde el hilo principal!");
		System.out.println("Proceso acabado");
	}
}

class HelloThread2 extends Thread{

	@Override
	public void run() {
		super.run();
		System.out.println("Hola desde el hilo creado");
		int contador = 1;
		while(true) {
			System.out.println(contador++);
		}
	}	
}

class HelloThread implements Runnable{

	Thread t;
	public HelloThread() {
		t = new Thread(this, "Nuevo Thread");
		System.out.println("Creado hilo:" + t);
	}
	@Override
	public void run() {
		System.out.println("Hola desde el hilo creado!");
		int contador = 1;
		while(true) {
			System.out.println(contador++);
		}
	}
	public void start() {
		t.start();
	}
}
