package yrj.psp;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyPairGeneratorSpi;
import java.security.NoSuchAlgorithmException;
import java.security.Signature;
import java.util.Base64;

public class EjemploFirma1 {

	private static final String ALG1 = "DSA";
	private static final String ALG2 = "RSA";
	private static final String PROTO1 = "DSA";
	private static final String PROTO2 = "SHA256WithRSA";
	
	private static String firmar(String mensaje, KeyPair clave) throws Exception {
		Signature firmaContenedor = Signature.getInstance(PROTO1);
		System.out.println("Firmando mensaje con la clave privada...");
		firmaContenedor.initSign(clave.getPrivate());
		firmaContenedor.update(mensaje.getBytes());
		byte[] firma = firmaContenedor.sign();
		return Base64.getEncoder().encodeToString(firma);
	}
	
	private static boolean esFirmaValida(String mensajeEnClaro, String mensajeFirmado, KeyPair clave) throws Exception {
		Signature firmaContenedor = Signature.getInstance(PROTO1);
		byte[] mensajeBytes = Base64.getDecoder().decode(mensajeFirmado.getBytes("UTF8"));
		
		firmaContenedor.initVerify(clave.getPublic());;
		firmaContenedor.update(mensajeEnClaro.getBytes("UTF8"));
		
		return firmaContenedor.verify(mensajeBytes);
	}
	
	public static void main(String[] args) {
		try {
			String mensaje = "Enhorabuena a Paco que hoy nos lleva de excursión";
			System.out.println("Obteniendo el generador de claves " + ALG1);
			KeyPairGenerator keygen = KeyPairGenerator.getInstance(ALG1);
			KeyPair clave = keygen.generateKeyPair();
			System.out.println("Generando el par de claves");
			
			String mensajeFirmado = firmar(mensaje, clave);

			System.out.println("Comprobando validez del texto firmado...");
			if (esFirmaValida(mensaje, mensajeFirmado, clave)) {
				System.out.println("OK!");
			} else {
				System.out.println("ERROR");
			}
			
			//A continuación alteramos el mensaje deliberadamente
			String mensajeAlterado = mensaje + ".";
			if (esFirmaValida(mensajeAlterado, mensajeFirmado, clave)) {
				System.out.println("OK!");
			} else {
				System.out.println("ERROR");
			}
			
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
