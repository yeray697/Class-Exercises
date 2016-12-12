package dam.psp;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class DatoUDP implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String cadenaTexto;
	public Integer cadenaNumero;
	
	public DatoUDP(String texto, Integer numero) {
		this.cadenaTexto = texto;
		this.cadenaNumero = numero;
		System.out.println(cadenaTexto + ", " + cadenaNumero);
	}
	
	//Serializador
	public byte[] toByteArray(){
		//Se hace la conversión usando un ByteArrayOutputStream y
		// un objet ObjectOutputStream
		ByteArrayOutputStream bytes = new ByteArrayOutputStream();
		try {
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(bytes);
			objectOutputStream.writeObject(this);
			objectOutputStream.close();
			return bytes.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	//Deserializador
	public static DatoUDP fromByteArray(byte[] bytes){
		//Se hace la conversión usando un ByteArrayInputStream y
		// un objet ObjectInputStream
		DatoUDP aux = null;
		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
		try {
			ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
			aux = (DatoUDP) objectInputStream.readObject();
			objectInputStream.close();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return aux;
	}
}
