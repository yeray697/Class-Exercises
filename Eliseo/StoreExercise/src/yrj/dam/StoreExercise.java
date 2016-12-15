package yrj.dam;

import java.util.Random;

public class StoreExercise {

	private static final int INITIAL_PIECES = 8000;
	public static void main(String[] args) {
		Store store;
		Send send;
		Receive receive;

		System.out.println("INICIO");
		
		store = new Store(INITIAL_PIECES);
		send = new Send(store);
		receive = new Receive(store);
		
		receive.start();
		send.start();
		
		try {
			receive.join();
			send.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("\nFIN");
	}
	public static int randomInt(int min, int max) {
		return min + (int)(Math.random() * (max - min));
	}
}
