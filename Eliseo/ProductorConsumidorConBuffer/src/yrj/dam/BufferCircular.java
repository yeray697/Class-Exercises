package yrj.dam;

public class BufferCircular implements IBufer {

	private int bufer[] = {-1,-1,-1};
	private int contadorOcupado = 0;
	private int posLectura;
	private int posEscritura = 0;
	
	@Override
	public void escribir(int valor) {
		String hilollamador = Thread.currentThread().getName();
		while (contadorOcupado == bufer.length) {
			System.out.println(hilollamador + " trata de escribir");
			mostrarEstado("Bufer lleno. "+ hilollamador + " espera");
			mostrarSalida();
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		bufer[posEscritura] = valor;
		contadorOcupado++;
		posEscritura = (posEscritura + 1 ) % bufer.length;
		mostrarSalida();
		mostrarEstado(hilollamador + " consigue escribir "+ valor);
		notify();
	}

	@Override
	public int leer() {
		String hilollamador = Thread.currentThread().getName();
		while (contadorOcupado == 0) {
			System.out.println(hilollamador + " trata de leer");
			mostrarEstado("Bufer vacío. "+ hilollamador + " debe esperar");
			mostrarSalida();
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		int valor = bufer[posLectura];
		contadorOcupado--;
		posLectura = (posLectura + 1 ) % bufer.length;
		mostrarSalida();
		mostrarEstado(hilollamador + " consigue leer "+ valor);
		notify();
		return valor;
	}

	@Override
	public void mostrarEstado(String cadena) {
		//TODO no me ha dado tiempo :S
	}

	@Override
	public void mostrarSalida() {
		for (int i = 0; i < bufer.length; i++) {
//TODO no me ha dado tiempo :S
			System.out.println("");	
		}
	}
}
