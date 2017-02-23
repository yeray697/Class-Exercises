package yrj.psp;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.PrivateKey;
import java.security.Security;
import java.security.cert.Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;

import org.bouncycastle.crypto.tls.DigestAlgorithm;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfSignatureAppearance;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.security.BouncyCastleDigest;
import com.itextpdf.text.pdf.security.CrlClient;
import com.itextpdf.text.pdf.security.DigestAlgorithms;
import com.itextpdf.text.pdf.security.ExternalDigest;
import com.itextpdf.text.pdf.security.ExternalSignature;
import com.itextpdf.text.pdf.security.MakeSignature;
import com.itextpdf.text.pdf.security.OcspClient;
import com.itextpdf.text.pdf.security.PdfPKCS7;
import com.itextpdf.text.pdf.security.PrivateKeySignature;
import com.itextpdf.text.pdf.security.TSAClient;
import com.itextpdf.text.pdf.security.MakeSignature.CryptoStandard;

public class Firma {
	
	private static final String rutaCertificado = "cert/certificado.p12";
	private static final String password = "password";
	private static final String SRC = "doc/documento.pdf";
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
		app.sign(SRC,DEST,chain,pk,DigestAlgorithms.SHA256,
				provider.getName(),CryptoStandard.CMS,"Test de firma","Málaga",
				null,null,null,0);
		app.verifySignature(fields, name)
		
	}
	
	private PdfPKCS7 verifySignature(AcroFields fields, String name) throws Exception{
		System.out.println("La firma cubre todo el documento: " + fields.signatureCoversWholeDocument(name));
		System.out.println("Revisión #: " + fields.getRevision(name) + " de " + fields.getTotalRevisions());
		PdfPKCS7 pkcs7 = fields.verifySignature(name);
		
		System.out.println("Verificado? " + pkcs7.verify());
		
		return pkcs7;
	}
	
	private void verifySignautre(String path) throws Exception{
		System.out.println(path);
		PdfReader reader = new PdfReader(path);
		AcroFields fields = reader.getAcroFields();
		ArrayList<String>names = fields.getSignatureNames();
		for (String name : names) {
			System.out.println("====" + name + " ====");
			verifySignature(fields, name);
		}
		System.out.println();
	}
	
	private void sign(String src, String dest, Certificate[] chain, PrivateKey pk, String digestAlgorithm, String provider,
			CryptoStandard cms, String reason, String location, Collection<CrlClient> crlList, OcspClient ocspClient,
			TSAClient tsaClient, int estimatedSize) throws Exception {
		//Creamos el lector de pdf y el sello
		PdfReader reader = new PdfReader(src);
		FileOutputStream os = new FileOutputStream(dest);
		
		PdfStamper sello = PdfStamper.createSignature(reader, os, '\0');
		
		//Creamos el sello y su localización
		PdfSignatureAppearance apariencia = sello.getSignatureAppearance();
		apariencia.setReason(reason);
		apariencia.setLocation(location);
		apariencia.setVisibleSignature(new Rectangle(36,748,144,786),1,"sig");
		
		//Creamos la firma
		ExternalSignature pks = new PrivateKeySignature(pk, digestAlgorithm, provider);
		
		ExternalDigest digest = new BouncyCastleDigest();
		MakeSignature.signDetached(apariencia, digest, pks,
				chain, crlList, ocspClient, tsaClient, estimatedSize, cms);
	}
	public static void mostrarAlmacen(KeyStore ks) throws Exception{
		Enumeration<String>enumeracion = ks.aliases();
		while (enumeracion.hasMoreElements()) {
			String alias = (String) enumeracion.nextElement();
			System.out.println("");
			Certificate certificate;
			System.out.println("");
		}
	}
}
