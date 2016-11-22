package yrj.psp;


public class Relevos1_3 {
	
	public static void main(String[] args) {
		int nCorredores = 4;
		Corredor[] corredores = new Corredor[nCorredores];
		Relevos relevos = new Relevos(nCorredores);
		for(int i = 0; i < nCorredores; i++){
			corredores[i] = new Corredor((i + 1), relevos);
			corredores[i].start();
		}
		relevos.start();
		for(int i = 0; i < nCorredores; i++){
			try {
				corredores[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}

class Corredor extends Thread{
	int numero;
	Relevos relevos;
	
	public Corredor(int numero, Relevos relevos) {
		this.numero = numero;
		this.relevos = relevos;
		System.out.println("Corredor "+numero+ " preparado para correr");
	}
	@Override
	public void run(){
		//Espera hasta que empiece la carrera
		while(!relevos.started) {
			relevos.waitting();
		}
		//Espera hasta que sea su turno
		while(relevos.getTurno() != numero){
			relevos.waitting();
		}
		//Es su turno, corre, y pasa el turno a otro
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("El corredor "+numero + " ha terminado de correr.");
		relevos.setTurno();
	}
}



class Relevos{
	int turno;
	public boolean started;
	int nCorredores;
	public Relevos(int nCorredores) {
		turno = 1;
		started = false;
		this.nCorredores = nCorredores;
	}
	public synchronized void waitting() {
		try {
			this.wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public int getTurno() {
		return turno;
	}
	public synchronized void setTurno(){
		turno ++;
		if (turno > nCorredores) {
			System.out.println("\nHan terminado todos los corredores, una carrera impresionante");
		} else
			this.notifyAll();
	}
	public synchronized void start() {
		System.out.println("\n**PIUM** Comienza la carrera\n");
		this.notify();
		started = true;
	}
}