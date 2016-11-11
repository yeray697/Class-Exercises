package yrj.dam;

public class BufferCircular implements IBufer {

	private int bufer[] = {-1,-1,-1};
	private int contadorOcupado = 0;
	private int posLectura;
	private int posEscritura = 0;
	
	@Override
	public synchronized void escribir(int valor) {
		String hilollamador = Thread.currentThread().getName();
		while (contadorOcupado == bufer.length) {
			try {
				System.err.println(hilollamador + " trata de escribir");
				mostrarEstado("Bufer lleno. "+ hilollamador + " espera");
				mostrarSalida();
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
	public synchronized int leer() {
		String hilollamador = Thread.currentThread().getName();
		
		while (contadorOcupado == 0) {
			try {
				System.err.println(hilollamador + " trata de leer");
				mostrarEstado("\nBufer vacío. "+ hilollamador + " debe esperar");
				mostrarSalida();
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
		StringBuffer linea = new StringBuffer(cadena);
		linea.setLength(80);
		linea.append(this.bufer + " " + contadorOcupado);
		System.out.println(linea + "\n");
	}

	@Override
	public String mostrarSalida() {
		String salida = "(huecos ocupados: " + contadorOcupado + ")\nhuecos: ";
		for (int i = 0; i < bufer.length; i++) {
			salida += "  " + bufer[i] + " ";
		}
		salida += "\n	";
		for (int i = 0; i < bufer.length; i++) {
			salida += "---- ";
		}
		salida += "\n	";
		for (int i = 0; i < bufer.length; i++) {
			if (i == posEscritura && posEscritura == posLectura) {
				salida += " EL   ";
			} else if (i == posEscritura) {
				salida += " E   ";
			} else if (i == posLectura) {
				salida += " L   ";
			} else
				salida  += "   ";
		}
		salida += "\n	";
		System.out.println(salida);
		return salida;
	}
}
