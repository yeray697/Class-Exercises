package yrj.dam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.invoke.ConstantCallSite;

/**
 * Crear un hilo en Java que realice el cálculo de los primeros N números de Fibonacci.
 * La sucesión de Fibonacci comienza con los números 1 y 1 y el siguiente elemento se calcula como la suma de los dos anteriores.
 * El parámetro N se suministra al llamar al constructor de la clase que implemente el hilo.
 * Usar para esta solución el interfaz Runnable.
 * Realizar una implementación iterativa (no recursiva) con dos hilos.
 * 
 * @author yeray697
 * @version 1.0
 */
public class Fibonacci {
	private final int GREATERTHAN46 = -1;
	private final int NEGATIVENUMBER = -2;
	private final int NONUMBER = -3;
	/**
	 * First method that is executed
	 * @param args Arguments that you can pass
	 */
	public static void main(String[] args)
	{
		int number = -1;
		//If you do not pass a number on arguments, the application ask for a number until you enter a number greater or equal than 0
		if(args.length == 0) {
			number = AskForNumber();
		}
		else {
			try {
				number = Integer.parseInt(args[0]);
				if (number > 46)
					number = GREATERTHAN46;
				else if (number < 0)
					number = NEGATIVENUMBER;
			} catch (Exception e) {
				number = NONUMBER;
			}
		}
		switch (number) {
		case -1:
			System.out.println("El número tiene que ser mayor que 46 para no alcanzar Integer.MAX_VALUE");			
			break;
		case -2:
			System.out.println("El número no puede ser negativo");			
			break;
		case -3:
			System.out.println("No has introducido un número válido");			
			break;
		default:
			CalculateFibonacciIterative(number);
			//CalculateFibonacciRecursive(number);
			break;
		}
	}

	/**
	 * Calculate what Fibonacci number is at the position you passed
	 * @param number Position you want to calculate
	 */
	private static void CalculateFibonacciIterative(int number) {
		int result = 0;
		if (number == 0) {
			result = 0;
		}else if (number == 1 || number == 2) {
			result = 1;
		} else {
			FibonacciIterativeThread f1;
			FibonacciIterativeThread f2;
			
			f1 = new FibonacciIterativeThread(number-1);
			f2 = new FibonacciIterativeThread(number-2);
			f1.start();
			f2.start();
			f1.join();
			f2.join();
			
			result = f1.getAnswer() + f2.getAnswer();
		}
		System.out.println("Fibonacci(" + number + ") = " + result);
	}

	/**
	 * Calculate what Fibonacci number is at the position you passed
	 * @param number Position you want to calculate
	 */
	private static void CalculateFibonacciRecursive(int number) {
		try {
			FibonacciRecursiveThread f = new FibonacciRecursiveThread( number );
			f.start();
			f.join();
			//Answer
			if ( f.getAnswer() >= 0) {
				System.out.println("Si el número es muy grande (23 o más en mi ordenador) puede producir OutOfMemoryError y mostrar un resultado erróneo");
				System.out.println("Fibonacci(" + number + ") = " + f.getAnswer());
			} else 
				System.out.println("No has escrito un número válido");		
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

	/**
	 * Method that ask for a number greater or equal than 0. If you do not write a number, it ask it again until you write a number
	 * @return Return the number you wrote
	 */
	private static int AskForNumber() {
		int number = -1;
		String input;
		do {
			System.out.println("Escribe el número de la posición que quieres calcular de la serie de Fibonacci.");
			try {
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
				input = bufferedReader.readLine();
				number = Integer.parseInt(input);
				if (number > 46) { //47 reachs Integer limit
					number = -1;
					System.out.println("Escribe un número menor o igual a 46");
				} else if (number < 0)
					System.out.println("El número no puede ser negativo");		
			} catch (NumberFormatException ex) {
				System.out.println("No has escrito un número válido.");
			} catch (IOException e) {
					e.printStackTrace();
			}
		} while ( number < 0 );
		return number;
	}
}

/**
 * Class that extends from Thread that calculate Fibonacci numbers using multi thread
 * @author Yeray Ruiz
 */
class FibonacciIterativeThread implements Runnable {
	private int number = 0;
	private int answer;
	private Thread thread;

    /**
     * Constructor
     * @param number Position you want to calculate
     */
	public FibonacciIterativeThread(int number) {
		this.number = number;
		thread = new Thread(this);
	}

    /**
     * Method implemented from Runnable that run a thread
     */
	@Override
	public void run() {
		int prev = 0;
		int next = 1;
		for (int i = 2; i <= number; i++) {
			next = prev + next;
			prev = next - prev;
		}
		this.answer = next;
	}
	
	/**
	 * Method that call join of the thread
	 */
	public void join() {
		try {
			this.thread.join();
		} catch (InterruptedException e) {
		}
	}

	/**
	 * Method that start the thread
	 */
	public void start() {
		this.thread.start();
	}
    
    /**
     * Get the Fibonacci number position you entered
     * @return Return the answer
     */
	public int getAnswer() {
		return this.answer;
	}
}

/**
 * Class that extends from Thread that calculate Fibonacci numbers using multi thread
 * @author Yeray Ruiz
 */
class FibonacciRecursiveThread extends Thread
{
    private int number; //Position you want to calculate
    private int answer; //Answer of Fibonacci number position you entered

    /**
     * Constructor
     * @param number Position you want to calculate
     */
    public FibonacciRecursiveThread( int number ) {
        this.number = number;
        this.answer = -1;
    }

    /**
     * Method extended from Thread that run a thread
     */
    public void run() {
    	if ( number <= 0 )
    		answer = number;
    	else if ( number == 1 || number == 2 )
            answer = 1;
        else {
            try {
            	FibonacciRecursiveThread f1 = new FibonacciRecursiveThread( number - 1 );
            	FibonacciRecursiveThread f2 = new FibonacciRecursiveThread( number - 2 );
                f1.start();
                f2.start();
                f1.join();
                f2.join();
                answer = f1.answer + f2.answer;
            }
            catch(InterruptedException ex) { 
            	System.out.println(ex.getMessage());
            }
        }
    }
    
    /**
     * Get the Fibonacci number position you entered
     * @return Return the answer
     */
    public int getAnswer() {
    	return this.answer;
    }
}
