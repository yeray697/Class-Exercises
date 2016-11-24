package yrj.psp;

import com.sun.org.apache.bcel.internal.generic.ReturnaddressType;

public class MediaMultiThread {
	public static void main(String[] args) {
		int[] array = crearArray(100);
		double resultado1 = mediaUnHilo(array);
		double resultado2 = 0;
		System.out.println("\n\nResultado con un hilo: "+ resultado1);
		int nHilos = 10;
		Calculador[] calculadores = new Calculador[nHilos];
		for (int i = 0; i < nHilos; i++) {
			calculadores[i] = new Calculador(array,i, nHilos);
		}
		System.out.println("Comenzando hilos: ");
		for (int i = 0; i < nHilos; i++) {
			calculadores[i].start();
		}
		for (int i = 0; i < nHilos; i++) {
			try {
				calculadores[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		for (int i = 0; i < nHilos; i++) {
			resultado2 += calculadores[i].getResultado();
		}
		resultado2 = resultado2 / nHilos;
		System.out.println("Resultado con "+nHilos+ " hilos: " + resultado2);
	}
	private static double mediaUnHilo(int[] array) {
		double suma = 0;
		for (int i = 0; i < array.length; i++) {
			suma += array[i];
			System.out.print(array[i] + ", ");
		}
		return (suma / (double) array.length);
	}
	
	public static int[] crearArray(int numero) {
		int[] array = new int[numero];
		int max = 10;
		int min = 5;
		
		for (int i = 0; i < array.length; i++) {
			array[i] = (int) (Math.random() * (max - min)) + min;
			
		}
		
		return array;
	}
}

class Calculador extends Thread{
	int[] array;
	int nHilos;
	double resultado;
	
	public Calculador(int[] arrayOld,int idHilo, int nHilos) {
		int posicion = arrayOld.length / nHilos * idHilo;
		this.array = new int[arrayOld.length / nHilos];
		for (int i = 0; i < array.length; i++) {
			array[i] = arrayOld[i + posicion];
		}
		resultado = -1;
	}
	
	@Override
	public void run(){
		double suma = 0;
		for (int i = 0; i < array.length; i++) {
			suma += array[i];
		}
		resultado = (suma / (double)array.length);
	}
	
	public double getResultado() {
		return this.resultado;		
	}
}
