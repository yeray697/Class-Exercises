package dam.yrj;

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
	public int cadenaNumero;
	
	public DatoUDP(String texto, int numero) {
		this.cadenaNumero = numero;
		this.cadenaTexto = texto;
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
