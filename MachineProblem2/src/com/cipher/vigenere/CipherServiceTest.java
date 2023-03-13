package com.cipher.vigenere;

public class CipherServiceTest {

	public static void main(String args[]) {
		
		
		String keyFile = "keyFile.txt";
		String messageFile = "messageFile.txt";
		String encryptedMessageFile = "data.encrypt";
		
		String keySquare = System.getProperty("key.square") != null ? System.getProperty("key.square") : "alpha";
		
		switch(keySquare) {
			case "alpha":
				CipherService cs = new CipherServiceImpl();
				cs.encrypt(keyFile, messageFile, encryptedMessageFile);
				cs.decrypt(keyFile, encryptedMessageFile, messageFile);
				break;
			case "binary":
				CipherService binary = new BinaryCipherServiceImpl();
				binary.encrypt(keyFile, messageFile, encryptedMessageFile);
				binary.decrypt(keyFile, encryptedMessageFile, messageFile);
				break;
			default:	
		}
		
	}
}
