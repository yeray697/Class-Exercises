package yrj.dam;

/**
 * Class that receive pieces one time every day
 * 
 * @author Yeray Ruiz Juárez
 * @version 1.0
 */
public class Receive extends Thread {
	private final static int ONE_DAY = 2400;				//1000 = 1  hour, 2400 = 24 hours = 1 day
	private final static int MIN_PIECES_RECEIVED = 2000;	//Minimum pieces that can receive
	private final static int MAX_PIECES_RECEIVED = 2500;	//Maximum pieces that can receive
	Store store;											//Place where it is stored

	public Receive(Store store) {
		this.store = store;
	}
	@Override
	public void run(){
		while (!store.isThereAnError()) {
			int received = StoreExercise.randomInt(MIN_PIECES_RECEIVED, MAX_PIECES_RECEIVED);
			store.receivePieces(received);
			try {
				this.sleep(ONE_DAY);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
