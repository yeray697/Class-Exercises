package yrj.psp;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateCrtKey;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.util.Base64;

import javax.crypto.Cipher;

public class AsymetricRSA {

	private static final String ALG = "RSA";
	
	public static String encriptar(String mensaje, KeyPair clave) throws Exception{
		Cipher rsaCifrado = Cipher.getInstance(ALG);
		rsaCifrado.init(Cipher.ENCRYPT_MODE, clave.getPublic());
		byte[] criptogramaBytes = Base64.getEncoder().encode(rsaCifrado.doFinal(mensaje.getBytes("UTF-8")));
		return new String(criptogramaBytes);
	}

	public static String desencriptar(String criptograma, KeyPair clave) throws Exception{
		Cipher rsaCifrado = Cipher.getInstance(ALG);
		rsaCifrado.init(Cipher.DECRYPT_MODE, clave.getPrivate());
		byte[] mensajeBytes = Base64.getDecoder().decode(criptograma.getBytes("utf8"));
		
		return new String(rsaCifrado.doFinal(mensajeBytes));
	}
	
	public static void mostrarInfoClave(KeyPair clave) throws Exception{
		KeyFactory factoria = KeyFactory.getInstance(ALG);
		RSAPublicKeySpec partePublica = factoria.getKeySpec(clave.getPublic(), RSAPublicKeySpec.class);
		RSAPrivateKeySpec partePrivada = factoria.getKeySpec(clave.getPrivate(),RSAPrivateKeySpec.class);

		System.out.println("clave PRIVADA: ");
		System.out.println("Módulus: "+partePrivada.getModulus());
		System.out.println("Exponentus: "+partePrivada.getPrivateExponent());
		System.out.println("\nclave PÚBLICA: ");
		System.out.println("Módulus: "+partePublica.getModulus());
		System.out.println("Exponentus: "+partePublica.getPublicExponent());
	}
	public static void main(String[] args) {
		String mensaje = "Anda que la que va a liar Paco, va a ser parda";
		System.out.println("Obteniendo el generador de claves RSA");
		KeyPairGenerator keygen;
		try {
			keygen = KeyPairGenerator.getInstance(ALG);
			System.out.println("Generando clave RSA");
			KeyPair clave = keygen.generateKeyPair();
			
			System.out.println("Información de la clave generada:");
			mostrarInfoClave(clave);
			String criptograma = encriptar(mensaje, clave);
			System.out.println("Mensaje cifrado:\n" + criptograma);
			System.out.println("Mensaje descifrado:\n"+desencriptar(criptograma, clave));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
