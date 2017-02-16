package dam.psp;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

public class CifradoAES {

	private static String cifrado = "AES";
	
	public static SecretKey obtenerClaveOpaca(int longitud) throws NoSuchAlgorithmException {
		KeyGenerator claveInstancia = KeyGenerator.getInstance(cifrado);
		claveInstancia.init(longitud); //Por defecto, 128b
		return claveInstancia.generateKey();
	}
	
	public static SecretKeySpec obtenerClaveTransparente(String miClave) throws UnsupportedEncodingException, NoSuchAlgorithmException{
		byte[] miClaveEnBytes = miClave.getBytes("utf8");
		miClaveEnBytes = DigestUtils.sha1Hex(miClaveEnBytes).getBytes();
		System.out.println("El hash sha1 de la clave es " + new String(miClaveEnBytes));
		miClaveEnBytes = Arrays.copyOf(miClaveEnBytes, 16);
		return new SecretKeySpec(miClaveEnBytes, cifrado);
	}
	
	public static String encriptar(String mensaje, SecretKey clave) throws Exception{
		Cipher cipher = Cipher.getInstance(cifrado);
		cipher.init(Cipher.ENCRYPT_MODE, clave);
		byte[] encVal = cipher.doFinal(mensaje.getBytes("UTF8"));
		byte[] criptogramaEnBytes = Base64.encodeBase64(encVal);
		return new String(criptogramaEnBytes);
	}
	
	public static String desencriptar(String criptograma, SecretKey clave) throws Exception{
		
		Cipher cipher = Cipher.getInstance(cifrado);
		cipher.init(Cipher.DECRYPT_MODE, clave);
		byte[] decValue = Base64.decodeBase64(criptograma.getBytes("UTF8"));
		byte[] decryptVal = cipher.doFinal(decValue);
		return new String(decryptVal);
	}	

	public static void main(String[] args) {
		String mensaje = "Vaya melón que tiene Cicerón un viernes por la tarde en el Tivolí";
		//String miClave = "En ocasiones veo unicornios rosas";
		String miClave = "123";
		
		try {
			SecretKey miClaveOpaca = CifradoAES.obtenerClaveOpaca(256);
			System.out.println("Mensaje en claro: " + mensaje);
			
			String criptograma = CifradoAES.encriptar(mensaje, miClaveOpaca);
			System.out.println("Criptograma: " + criptograma);
			System.out.println("Desencriptando: " + CifradoAES.desencriptar(criptograma, miClaveOpaca));
			
			//Creamos ahora una clave TRANSPARENTE, usando nuestra frase de paso en particular
			SecretKeySpec claveT = CifradoAES.obtenerClaveTransparente(miClave);
			criptograma = CifradoAES.encriptar(mensaje, claveT);
			
			System.out.println("Criptograma: " + criptograma);
			System.out.println("Desencriptando: " + CifradoAES.desencriptar(criptograma, claveT));
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
