package yrj.psp;

import java.io.FileInputStream;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.PrivateKey;
import java.security.Security;
import java.security.cert.Certificate;
import java.util.Enumeration;

import org.bouncycastle.crypto.tls.DigestAlgorithm;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import com.itextpdf.text.pdf.security.MakeSignature.CryptoStandard;

public class Firma {
	
	private static final String rutaCertificado = "cert/certificado.p12";
	private static final String password = "password";
	private static final String SRC = "docs/documento.pdf";
	private static final String DEST = SRC.substring(0, SRC.lastIndexOf(".")) + "signed.pdf";
	
	public static void main(String[] args) throws Exception {
		BouncyCastleProvider provider = new BouncyCastleProvider();
		Security.addProvider(provider);
		
		KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
		
		ks.load(new FileInputStream(rutaCertificado), password.toCharArray());
		
		String alias = (String)ks.aliases().nextElement();
		mostrarAlmacen(ks);
		
		System.out.println("Alias: " + alias);
		
		PrivateKey pk = (PrivateKey)ks.getKey(alias, password.toCharArray());
		
		System.out.println("clave: " + pk.getAlgorithm() + " formato: "+pk.getFormat());
		
		Certificate[] chain = ks.getCertificateChain(alias);
		
		Firma app = new Firma();
		app.sign(SRC,DEST,chain,pk,DigestAlgorithm.SHA256,
				provider.getName(),CryptoStandard.CMS,"Test de firma","Málaga",
				null,null,null,0);
		
	}
	private void sign(String src2, String dest2, Certificate[] chain, PrivateKey pk, int sha256, String name,
			CryptoStandard cms, String string, String string2, Object object, Object object2, Object object3, int i) {
		// TODO Auto-generated method stub
		
	}
	public void mostrarAlmacen(KeyStore ks) throws Exception{
		Enumeration<String>enumeracion = ks.aliases();
		while (enumeracion.hasMoreElements()) {
			String alias = (String) enumeracion.nextElement();
			System.out.println(x);
		}
	}
}
