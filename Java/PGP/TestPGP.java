package com.pe.ge.pe;

import java.io.IOException;

public class TestPGP {
	
	public static void main(String[] args) throws IOException {
		UncalPGPEnDec endec = new UncalPGPEnDec();
		endec.setPlainMessage("ini adalah text yang akan di encrypt");
		String hasil = endec.getEncryptedMessage();
		System.out.println(hasil);
		endec = new UncalPGPEnDec();
		endec.setPGPMessage(hasil);
		hasil = endec.getDecryptedMessage();
		System.out.println(hasil);
	}

}
