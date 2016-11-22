package yrj.psp;

import java.util.Random;

public class Supermercado1_4 {

	public static void main(String[] args) {
		Supermercado supermercado = new Supermercado();
	}
	
	public static int randomNum(int min, int max) {
		return (int)(Math.random() * (max - min)) + min;
	}

}
class Supermercado{
	public int nCajas;
	Caja[] cajas;
	Cliente[] clientes;
	int nClientes;
	int resultado;
	
	public Supermercado() {
		resultado = 0;
		nCajas = 3;
		nClientes = 10;
		cajas = new Caja[nCajas];
		clientes = new Cliente[nClientes];
		
		for (int i=0; i < nCajas; i++) {
			cajas[i] = new Caja((i+1), this);
		}
		
		for (int i=0; i < nClientes; i++) {
			clientes[i] = new Cliente(i, this);
			clientes[i].start();
		}
		for (int i=0; i < nClientes; i++) {
			try {
				clientes[i].join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("Comienzo del día");

		
		System.out.println("Fin del día");
	}
	public void pagar(int cliente) {
		int cajaRandom = Supermercado1_4.randomNum(0, nCajas - 1);
		while(cajas[cajaRandom].ocupada()) {
			System.out.println("La caja "+(cajaRandom +1) + " está ocupada, el cliente "+cliente+ " está esperando su turno.");
			clientes[cliente].waitting();
		}
		cajas[cajaRandom].ocupar(cliente);
		
	}
}
class Caja {
	int nCaja;
	Supermercado supermercado;
	boolean ocupada;
	
	public Caja(int nCaja, Supermercado supermercado) {
		this.nCaja = nCaja;
		this.supermercado = supermercado;
		this.ocupada = false;
	}

	public synchronized void ocupar(int nCliente) {
		this.ocupada = true;

		System.out.println("El cliente "+nCliente+ " está pagando en la caja "+nCaja);
		try {
			Thread.sleep(Supermercado1_4.randomNum(1000, 4000));
		} catch (InterruptedException e) {e.printStackTrace();}
		//Paga
		supermercado.resultado += Supermercado1_4.randomNum(5, 200);
		System.out.println("El cliente "+nCliente+ " ha terminado de pagar en la caja "+nCaja);
		this.ocupada = false;
	}

	public boolean ocupada() {
		return ocupada;
	}
}
class Cliente extends Thread {
	int nCliente;
	Supermercado supermercado;
	public Cliente(int nCliente, Supermercado supermercado) {
		this.nCliente = nCliente;
		this.supermercado = supermercado;
	}
	@Override
	public void run(){
		//Comprar
		System.out.println("El cliente " + nCliente + " está comprando");
		try {
			Thread.sleep(Supermercado1_4.randomNum(1000, 4000));
		} catch (InterruptedException e) {e.printStackTrace();}
		System.out.println("El cliente " + nCliente + " ha terminado de comprar");
		//Pagar
		pagar();
	}
	public synchronized void waitting() {
		try {
			this.wait();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void pagar() {
		supermercado.pagar(nCliente);
	}
}
