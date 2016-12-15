package yrj.dam;

/**
 * Class that simulate a piece store, with a piece limit.
 * If the store receive more than he can afford, the other threads stop.
 * If the piece store is empty, the other threads stop.
 * 
 * @author Yeray Ruiz Juárez
 * @version 1.0
 */
public class Store {
	private final static int MAX_PIECES_STORED = 20000; //Limit pieces stored
	private int nPieces;								//Current pieces stored
	private boolean error;								//It is true if (pieces > MAX_PIECED_STORED || pieces < 0)
	private int dayCounter;								//Integer used to count how many days has been online
	
	/**
	 * Constructor
	 * @param nPieces Initial number of pieces
	 */
	public Store(int nPieces){
		this.nPieces = nPieces;
		this.error = false;
		this.dayCounter = 1;
	}

	/**
	 * Method that add pieces (send) and check if can not afford more pieces
	 * @param sent Number of pieces sent
	 */
	public synchronized void sendPieces(int sent) {
		System.out.println("Llegan "+ sent + " piezas.");
		nPieces += sent;
		if (nPieces > MAX_PIECES_STORED) {
			this.error = true;
			System.out.println("No caben más piezas!!");
		} else {
			System.out.println(getPiecesCount());
		}
	}
	
	/**
	 * Method that subtract pieces (receive) and check if there is not stock
	 * @param received Number of pieces received
	 */
	public synchronized void receivePieces(int received) {
		//Start new day
		System.out.println("\nDÍA " + dayCounter);
		//Receive
		System.out.println("Pedido de "+ received + " piezas.");
		nPieces -= received;
		if (nPieces < 0) {
			this.error = true;
			System.out.println("Faltan piezas!!");
		} else {
			System.out.println(getPiecesCount());
		}
		//Next day
		dayCounter++;
	}
	
	/**
	 * Return a formated string with the number of pieces
	 * @return Return a formated string with the number of pieces
	 */
	public String getPiecesCount(){
		return "Hay "+ this.nPieces + " piezas en el almacén.";
	}

	/**
	 * Return a boolean that indicates if there is an error
	 * @return Return a boolean that indicates if there is an error
	 */
	public boolean isThereAnError(){
		return this.error;
	}
}
