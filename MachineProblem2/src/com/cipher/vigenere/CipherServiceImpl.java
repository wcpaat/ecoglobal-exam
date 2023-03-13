package com.cipher.vigenere;

public class CipherServiceImpl extends CipherService {

	@Override
	//Function for encrytion using algebraic function of vigenere cipher
	public void encrypt(String keyFile, String messageFile, String encryptedMessageFile) {
		String res = "";
		
		String key = super.readFromFile(keyFile);
		String message = super.readFromFile(messageFile);
		
		message = message.toUpperCase();
		for (int i = 0, j = 0; i < message.length(); i++) {
			
			char c = message.charAt(i);
			if (c < 'A' || c > 'Z') {
				continue;
			}
			res += (char) ((c + key.charAt(j) - 2 * 'A') % 26 + 'A');
			j = ++j % key.length();
			
		}
		System.out.println("encrypt: " + res);
		super.writeFile(encryptedMessageFile, res);
	}

	@Override
	//Function for decryption using algebraic function of vigenere cipher
	public void decrypt(String keyFile, String encryptedMessageFile, String messageFile) {
		String res = "";
		
		String key = super.readFromFile(keyFile);
		String message = super.readFromFile(encryptedMessageFile);
		
		message = message.toUpperCase();
        for (int i = 0, j = 0; i < message.length(); i++)
        {
            char c = message.charAt(i);
            if (c < 'A' || c > 'Z') {
                continue;
            }
            res += (char) ((c - key.charAt(j) + 26) % 26 + 'A');
            j = ++j % key.length();
        }
        
    	String fileStr = "data.decrypt";
    	System.out.println("decrypt: " + res);
        super.writeFile(fileStr, res);
        
	}

}
