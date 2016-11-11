package yrj.dam;

class Productor extends Thread{
	private IBufer compartido;
	
	public Productor(IBufer elBufer) {
		super("Productor");
		compartido = elBufer;
	}
	@Override
	public void run(){
		for (int i = 0; i < 20; i++) {
			try {
				Thread.sleep((int)(Math.random() * 3001));
				compartido.escribir(i);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println(getName() + " terminó de producir datos");
	}
}class Consumidor extends Thread{
	private IBufer compartido;
	public Consumidor (IBufer elBufer) {
		super("Consumidor");
		compartido = elBufer;
	}
	
	@Override
	public void run(){
		int suma = 0;
		for (int i = 0; i < 10; i++) {
			try {
				Thread.sleep((int)(Math.random() * 3001));
				suma+= compartido.leer();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println(getName()+" terminó de producir datos");
	}
}
public class PruebaBufferCircular {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		IBufer elBuffer = new BufferCircular();
		Productor prod = new Productor(elBuffer);
		Consumidor consu= new Consumidor(elBuffer);
		Consumidor consu2= new Consumidor(elBuffer);
		
		prod.start();
		consu.start();
		consu2.start();
		try {
			prod.join();
			consu.join();
			consu2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}