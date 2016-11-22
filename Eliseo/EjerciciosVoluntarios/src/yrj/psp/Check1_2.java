package yrj.psp;

public class Check1_2 {

	public static void main(String[] args) {
		Buffers buffers = new Buffers();
		Escritor escritor = new  Escritor(buffers);
		Lector lector = new  Lector(buffers);
		escritor.start();
		lector.start();
		try {
			escritor.join();
			lector.join();
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
	}
}
class Buffers{
	public int[] buffer;
	public Buffers() {
		buffer = new int[10000];
	}
	public void write(int posicion) {
		buffer[posicion] ++;
	}
	public int read(int posicion) {
		return buffer[posicion];
	}
}
class Escritor extends Thread{
	Buffers buffer;
	int posicion;
	
	public Escritor(Buffers buffer) {
		this.buffer = buffer;
		this.posicion = 0;
	}
	@Override
	public void run() {
		while (true) {
			buffer.write(posicion);
			posicion ++;
			if (posicion == 10000) {
				posicion = 0;
			}
		}
	}
}
class Lector extends Thread{
	Buffers buffer;
	int posicion;
	int current;
	int last;
	
	public Lector(Buffers buffer) {
		this.buffer = buffer;
		this.posicion = 0;
	}
	@Override
	public void run() {
		while(true) {
			current = buffer.read(posicion);
			if (current == last) {
				posicion ++;
				if (posicion == 10000) {
					posicion = 0;
				}
				last = current;
			} else {
				System.err.println("Error");
			}
		}
	}
}
