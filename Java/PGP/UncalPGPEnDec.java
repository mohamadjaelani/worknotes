package com.pe.ge.pe;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.Iterator;
import java.util.Properties;
import org.bouncycastle.bcpg.ArmoredInputStream;
import org.bouncycastle.bcpg.ArmoredOutputStream;
import org.bouncycastle.bcpg.BCPGOutputStream;
import org.bouncycastle.bcpg.HashAlgorithmTags;
import org.bouncycastle.openpgp.PGPCompressedData;
import org.bouncycastle.openpgp.PGPCompressedDataGenerator;
import org.bouncycastle.openpgp.PGPEncryptedData;
import org.bouncycastle.openpgp.PGPEncryptedDataGenerator;
import org.bouncycastle.openpgp.PGPEncryptedDataList;
import org.bouncycastle.openpgp.PGPException;
import org.bouncycastle.openpgp.PGPLiteralData;
import org.bouncycastle.openpgp.PGPLiteralDataGenerator;
import org.bouncycastle.openpgp.PGPObjectFactory;
import org.bouncycastle.openpgp.PGPOnePassSignature;
import org.bouncycastle.openpgp.PGPOnePassSignatureList;
import org.bouncycastle.openpgp.PGPPrivateKey;
import org.bouncycastle.openpgp.PGPPublicKey;
import org.bouncycastle.openpgp.PGPPublicKeyEncryptedData;
import org.bouncycastle.openpgp.PGPPublicKeyRingCollection;
import org.bouncycastle.openpgp.PGPSecretKey;
import org.bouncycastle.openpgp.PGPSignature;
import org.bouncycastle.openpgp.PGPSignatureGenerator;
import org.bouncycastle.openpgp.PGPSignatureList;
import org.bouncycastle.openpgp.PGPUtil;
import org.bouncycastle.openpgp.operator.PublicKeyDataDecryptorFactory;
import org.bouncycastle.openpgp.operator.bc.BcKeyFingerprintCalculator;
import org.bouncycastle.openpgp.operator.bc.BcPGPContentSignerBuilder;
import org.bouncycastle.openpgp.operator.bc.BcPGPContentVerifierBuilderProvider;
import org.bouncycastle.openpgp.operator.bc.BcPGPDataEncryptorBuilder;
import org.bouncycastle.openpgp.operator.bc.BcPublicKeyDataDecryptorFactory;
import org.bouncycastle.openpgp.operator.bc.BcPublicKeyKeyEncryptionMethodGenerator;

/**
 * 
 * @author j43lani
 *</br>
 *this class is for CIC Mulesoft consultant only
 */
public class UncalPGPEnDec extends BasePGPCommon{
	private String hasilDecrypt;
	private String passwordKey;
	private InputStream publicKeyPartner;
	private InputStream privateKeySendiri;
	private Boolean ttd;
	
	public UncalPGPEnDec() throws IOException {
//		init();
	}
	private String encryptedMessage;
	void initEnc() throws IOException{
//		Properties prop = new Properties();
//		prop.load(UncalPGPEnDec.class.getClassLoader().getResourceAsStream("resource.properties"));
//		publicKeyPartner = UncalPGPEnDec.class.getClassLoader().getResourceAsStream(prop.getProperty("publickey"));
		publicKeyPartner = new FileInputStream("E:\\JAVA\\UncalESB.2.0\\UE_2.1\\library\\pgp_public_key.asc");
	}
	void initDec() throws IOException{
		/*Properties prop = new Properties();
		prop.load(UncalPGPEnDec.class.getClassLoader().getResourceAsStream("resource.properties"));
		privateKeySendiri = UncalPGPEnDec.class.getClassLoader().getResourceAsStream(prop.getProperty("privatekey"));*/
		privateKeySendiri = new FileInputStream("E:\\JAVA\\UncalESB.2.0\\UE_2.1\\library\\pgp_private_key.asc");
//		ini untuk keperluan decrypt dengan sign
		/*try{
			publicKeyPartner = UncalPGPEnDec.class.getClassLoader().getResourceAsStream(prop.getProperty("publickey"));
		}catch(Exception e){}*/
		passwordKey = "eximbank";//prop.getProperty("passwordkey");
	}
	public void setPlainMessage(String plainText) {
		try {
			initEnc();
			InputStream isPlainText = new ByteArrayInputStream(plainText.getBytes());
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			encrypt(publicKeyPartner, null, null, null, "", isPlainText, baos);
			encryptedMessage = baos.toString();
		} catch (IOException e) {
			encryptedMessage = e.getMessage();
		}
	}
	public String getEncryptedMessage(){
		return encryptedMessage;
	}
	void encrypt(InputStream publicKeyOfRecipient, InputStream privateKeyOfSender, String userIdOfSender, String passwordOfSendersPrivateKey, String inputDataName, InputStream plainInputData, OutputStream target) {
		PGPPublicKey pgpPublicKey = findPublicKey(publicKeyOfRecipient, new KeyFilter<PGPPublicKey>() {
			@Override
			public boolean accept(PGPPublicKey pgpKey) {
				return pgpKey.isEncryptionKey() && !pgpKey.isMasterKey();
			}
		});
		if( pgpPublicKey != null ) {
			try( OutputStream wrappedTargetStream = new ArmoredOutputStream(target) ) {
				BcPGPDataEncryptorBuilder encryptorBuilder = new BcPGPDataEncryptorBuilder(getEncryptionAlgorithm());
				encryptorBuilder.setWithIntegrityPacket(true);
				PGPEncryptedDataGenerator encryptedDataGenerator = new PGPEncryptedDataGenerator(encryptorBuilder);
				encryptedDataGenerator.addMethod(new BcPublicKeyKeyEncryptionMethodGenerator(pgpPublicKey));
				PGPSecretKey pgpSecretKey = null;
				if( privateKeyOfSender != null )
					pgpSecretKey = findSecretKey(privateKeyOfSender, userIdOfSender);
				try( OutputStream encryptedDataStream = encryptedDataGenerator.open(wrappedTargetStream, new byte[4096]) ) {
					encryptAndSign(pgpSecretKey, passwordOfSendersPrivateKey, inputDataName, plainInputData, encryptedDataStream);
				}
				wrappedTargetStream.flush();
				wrappedTargetStream.close();
			} catch (IOException | PGPException e) {
				e.printStackTrace();
			}
		}else{
			System.out.println("tak ada public key");
		}
	}
	void encryptAndSign(PGPSecretKey pgpSecretKey, String password, String inputDataName, InputStream inputData, OutputStream encryptedDataStream) throws PGPException, IOException {
		PGPSignatureGenerator pgpSignatureGenerator = null;

		PGPPrivateKey signingKey = null;
		if (pgpSecretKey != null) {
			signingKey = findPrivateKey(pgpSecretKey, password);
		}

		PGPCompressedDataGenerator compressedDataGenerator = new PGPCompressedDataGenerator(getCompressionAlgorithm());
		try ( OutputStream compressedDataStream = new BCPGOutputStream(compressedDataGenerator.open(encryptedDataStream)) ) {
			if (signingKey != null) {
				pgpSignatureGenerator = new PGPSignatureGenerator(new BcPGPContentSignerBuilder(signingKey.getPublicKeyPacket().getAlgorithm(), HashAlgorithmTags.SHA256));
				pgpSignatureGenerator.init(PGPSignature.BINARY_DOCUMENT, signingKey);
				pgpSignatureGenerator.generateOnePassVersion(false).encode(compressedDataStream);
			} else {
				System.out.println("encrypt tidak di sign");
			}
			PGPLiteralDataGenerator literalDataGenerator = new PGPLiteralDataGenerator(false);
			try (OutputStream literalDataOutputStream = literalDataGenerator.open(compressedDataStream, PGPLiteralDataGenerator.BINARY, inputDataName, new Date(), new byte[4096])) {
				IOUtils.StreamHandler streamHandler = null;
				if( signingKey != null ) {
					final PGPSignatureGenerator callbackGenerator = pgpSignatureGenerator;
					streamHandler = new IOUtils.StreamHandler() {
						@Override
						public void handleStreamBuffer(byte[] buffer, int offset, int length) throws IOException {
							callbackGenerator.update(buffer, offset, length);
						}
					};
				}
				IOUtils.copy(inputData, literalDataOutputStream, new byte[4096], streamHandler);
				literalDataGenerator.close();
			}
			if (signingKey != null) {
				pgpSignatureGenerator.generate().encode(compressedDataStream);
			}
			compressedDataGenerator.close();
		}
	}
	protected  int getEncryptionAlgorithm() {
		return (isUnlimitedEncryptionStrength()) ? PGPEncryptedData.AES_256 : PGPEncryptedData.AES_128;
	}
	public String getDecryptedMessage(){
		return hasilDecrypt;
	}
	public void setPGPMessage(String encryptedData){
		try {
			initDec();
			PGPPublicKeyRingCollection publicKeyRingCollection = null;
			if( publicKeyPartner != null ) {
				try( InputStream armoredInputStream = new ArmoredInputStream(publicKeyPartner)) {
					publicKeyRingCollection = new PGPPublicKeyRingCollection(armoredInputStream, new BcKeyFingerprintCalculator());
				}
			}
			try( InputStream in = PGPUtil.getDecoderStream(new ByteArrayInputStream(encryptedData.getBytes()))) {
				PGPObjectFactory objectFactory = new PGPObjectFactory(in, new BcKeyFingerprintCalculator());
				PGPEncryptedDataList dataList;
				Object firstObject = objectFactory.nextObject();
				if( firstObject instanceof PGPEncryptedDataList ) {
					dataList = (PGPEncryptedDataList)firstObject;
				} else {
					dataList = (PGPEncryptedDataList)objectFactory.nextObject();
				}
				PGPPrivateKey pgpPrivateKey = null;
				PGPEncryptedData pgpEncryptedData = null;
				Iterator<PGPEncryptedData> iterator = dataList.getEncryptedDataObjects();
				while( pgpPrivateKey == null && iterator.hasNext() ) {
					pgpEncryptedData = iterator.next();
					pgpPrivateKey = findPrivateKey(privateKeySendiri, ((PGPPublicKeyEncryptedData) pgpEncryptedData).getKeyID(), passwordKey);
				}
				PublicKeyDataDecryptorFactory publicKeyDataDecryptorFactory = new BcPublicKeyDataDecryptorFactory(pgpPrivateKey);
				try( InputStream clearText = ((PGPPublicKeyEncryptedData)pgpEncryptedData).getDataStream(publicKeyDataDecryptorFactory)) {
					PGPObjectFactory pgpObjectFactory = new PGPObjectFactory(clearText, new BcKeyFingerprintCalculator());
					Object message;
					PGPCompressedData compressedData;
					PGPOnePassSignatureList onePassSignatureList = null;
					PGPOnePassSignature onePassSignature = null;
					PGPLiteralData literalData;
					PGPSignatureList signatures = null;
					PGPPublicKey pgpPublicKey = null;

					while( (message = pgpObjectFactory.nextObject()) != null ) {
						if( message instanceof PGPCompressedData ) {
							compressedData = (PGPCompressedData) message;
							pgpObjectFactory = new PGPObjectFactory(compressedData.getDataStream(), new BcKeyFingerprintCalculator());
						}
						if( message instanceof PGPLiteralData ) {
							literalData = (PGPLiteralData) message;
							try( InputStream literalDataStream = literalData.getInputStream() ) {
								if( onePassSignature != null ) {
									final PGPOnePassSignature callbackSignature = onePassSignature;
									new IOUtils.StreamHandler() {
										@Override
										public void handleStreamBuffer(byte[] buffer, int offset, int length) throws IOException {
											callbackSignature.update(buffer, offset, length);
										}
									};
								}
								ByteArrayOutputStream baos = new ByteArrayOutputStream();
								byte[] buffer = new byte[1024];
								for (int length; (length = literalDataStream.read(buffer)) != -1; ) {
									baos.write(buffer, 0, length);
								}
								hasilDecrypt = baos.toString();
							}
						} else if( message instanceof PGPOnePassSignatureList ) {
							onePassSignatureList = (PGPOnePassSignatureList)message;
							if( publicKeyPartner != null ) {
								onePassSignature = onePassSignatureList.get(0);
								pgpPublicKey = publicKeyRingCollection.getPublicKey(onePassSignature.getKeyID());
								onePassSignature.init(new BcPGPContentVerifierBuilderProvider(), pgpPublicKey);
							}
						} else if( message instanceof  PGPSignatureList){
							signatures = (PGPSignatureList)message;
						}
					}
					for( int i = 0; onePassSignatureList != null && i < onePassSignatureList.size(); i++ ) {
						if( pgpPublicKey != null && signatures != null ) {
							PGPSignature signature = signatures.get(i);
							if( onePassSignature.verify(signature) ) {
								Iterator<String> it = pgpPublicKey.getUserIDs();
								while (it.hasNext()) {
									it.next();
								}
							} else {
							}
						}
					}
					if( pgpEncryptedData.isIntegrityProtected() ) {
						if( pgpEncryptedData.verify() ) {
						} else {
						}
					}
				}
			}
		} catch (IOException | PGPException e) {
			e.printStackTrace();
		}
	}
}
