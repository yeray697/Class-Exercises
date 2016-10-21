package dam.psp;

class GlobalVar{
	public static int c1 = 0;
	public static int c2 = 0;

	private static final Object mutex1 = new Object();
	private static final Object mutex2 = new Object();
	
	public static void incrementarC1() {
		synchronized (mutex1) {
			c1++;
		}
	}

	public static void incrementarC2() {
		synchronized (mutex2) {
			c2++;
		}
	}
	
}

class DosMutex extends Thread {
	@Override
	public void run(){
		GlobalVar.incrementarC1();
		GlobalVar.incrementarC2();
	}
}

public class HilosExclusionMutua {
	public static void main(String[] args) {
		int N = Integer.parseInt(args[0]);
		DosMutex hilos[];
		System.out.println("Creando "+N + " hilos");
		hilos = new DosMutex[N];
		for(int i=0; i<N; i++){
			hilos[i] = new DosMutex();
			hilos[i].start();
		}
		for(int i=0; i<N; i++){
			try {
				hilos[i].join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("C1 = "+GlobalVar.c1);
		System.out.println("C2 = "+GlobalVar.c2);
	}

}
