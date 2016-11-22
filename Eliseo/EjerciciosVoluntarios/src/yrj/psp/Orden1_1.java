package yrj.psp;

public class Orden1_1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Orden();
	}

}

class Orden {
	Hilo hilo1;
	Hilo hilo2;
	boolean finalizado2;
	private void start() {
		finalizado2 = false;
		hilo1 = new Hilo(1, this);
		hilo2 = new Hilo(2, this);
		hilo1.start();
		hilo2.start();
		try {
			hilo1.join();
			hilo2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public Orden() {
		start();
	}
	public boolean is2Finalized() {
		return finalizado2;
	}
	public synchronized void waitting() {
		try {
			this.wait();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	public synchronized void notifying() {
			this.notify();		
	}
}
class Hilo extends Thread{
	int posicion;
	Orden orden;
	public Hilo(int posicion, Orden orden) {
		this.posicion = posicion;
		this.orden = orden;
	}
	@Override
	public void run(){
		if (posicion == 1) {
			while(!orden.is2Finalized()){
				orden.waitting();
			}
			System.out.println("Hola, soy el hilo número " + this.posicion);
			
		} else {
			System.out.println("Hola, soy el hilo número " + this.posicion);
			orden.finalizado2 = true;
			orden.notifying();
		}
	}
}
