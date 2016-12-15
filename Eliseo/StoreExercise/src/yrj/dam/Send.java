package yrj.dam;

public class Send extends Thread {
	private final static int EIGHT_HOURS = 800;
	private final static int MIN_PIECES_SENT = 400;
	private final static int MAX_PIECES_SENT = 1000;
	Store store;
	
	public Send(Store store) {
		this.store = store;
	}
	@Override
	public void run(){
		while (!store.isThereAnError()) {
			int sent = StoreExercise.randomInt(MIN_PIECES_SENT, MAX_PIECES_SENT);
			store.sendPieces(sent);
			try {
				this.sleep(EIGHT_HOURS);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
