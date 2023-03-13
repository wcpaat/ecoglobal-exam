package com.cipher.vigenere;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public abstract class CipherService {
	public static String RESOURCE_DIR="/resource/";
	
	public abstract void encrypt(String keyFile, String messageFile, String encryptedMessageFile);

	public abstract void decrypt(String keyFile, String encryptedMessageFile, String messageFile);
	
	
	public File readFile(String fileName) throws URISyntaxException {
		String path = new File("").getAbsolutePath()+RESOURCE_DIR+fileName;
        return new File(path);
	}
	
	public void writeFile(String fileName, String data) {
		String path = new File("").getAbsolutePath()+RESOURCE_DIR+fileName;
		BufferedWriter writer = null;
		try {
			Files.write( Paths.get(path), data.getBytes());
			writer = new BufferedWriter( new FileWriter(path,StandardCharsets.UTF_8));
			writer.write(data);
		}catch(IOException e) {
			//
		}finally {
			  try
			    {
			        if ( writer != null)
			        writer.close( );
			    }
			    catch ( IOException e)
			    {
			    }
		}
	     
	}
	
	protected String readFromFile(String filename) {
		File file;
		String strCurrentLine="";
		String result="";
		try {
			file = readFile(filename);
			BufferedReader br = new BufferedReader(new FileReader(file,StandardCharsets.UTF_8));
			
			while ((strCurrentLine = br.readLine()) != null) {
				result +=strCurrentLine;
			}
		} catch (URISyntaxException | IOException  e) {
			System.out.println(e.getMessage());
		}
		
		return result;
	}
	
	public static void main(String[] args) {
		CipherService s = new CipherServiceImpl();
		
		s.readFromFile("keyFile.txt");
		
	}
}
