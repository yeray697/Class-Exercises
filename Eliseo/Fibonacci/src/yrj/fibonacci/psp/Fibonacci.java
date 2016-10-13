package yrj.fibonacci.psp;

public class Fibonacci {

	static int numero = 70;
	public static void main(String[] args) {
		System.out.println("Calculando fibonacci de "+numero);
		System.out.println("Resultado: "+fibonacciResolver(numero));
	}
	private static int fibonacciResolver(int nVeces) {
		int resultado = 1;
		int contador = 2;
		int aux = 1;
		if (nVeces == 0)
			resultado = 0;		
		else if (nVeces == 1 || nVeces == 2)
			resultado = 1;
		else {
			while (contador < nVeces) {
				System.out.println(contador+" -> "+resultado);
				resultado+=aux;
				aux = resultado - aux;
				contador++;
			}
		}
		return resultado;
	}
}
class FibonacciThread implements Runnable{
	Thread thread;
	public FibonacciThread(int nVeces) {
		
	}
	@Override
	public void run() {
		
	}
}
