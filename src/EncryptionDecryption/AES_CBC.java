package EncryptionDecryption;

import java.io.File;
import java.io.FileInputStream;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import GUI.MainFrame;

public class AES_CBC {
	private MainFrame gui = null;	
	public AES_CBC(MainFrame gui){
		this.gui=gui;
	}
	
	public byte[] encryption(byte[] src) {
	    if(src!=null){
	    	try {
				Cipher cipher = Cipher.getInstance(gui.getSelectedAlgorithmMode());
			    cipher.init(Cipher.ENCRYPT_MODE, getSecretKey(), getSeretIvSpec());
				return cipher.doFinal(src);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	    return null;
	}
   
	public byte[] decryption(byte[] src) {
		if(src!=null){
			try {
				Cipher cipher = Cipher.getInstance(gui.getSelectedAlgorithmMode());
				cipher.init(Cipher.DECRYPT_MODE, getSecretKey(), getSeretIvSpec());
				return cipher.doFinal(src);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public byte[] encryptFile(File file){
		byte[] outputBytes=null;
		try{
			FileInputStream inputStream = new FileInputStream(file);
			byte[] inputBytes = new byte[(int) file.length()];
			inputStream.read(inputBytes);
			outputBytes = encryption(inputBytes);
			inputStream.close();
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return outputBytes;
	}

	public byte[] decryptFile(File file){
		byte[] outputBytes=null;
		try{
			FileInputStream inputStream = new FileInputStream(file);
			byte[] inputBytes = new byte[(int) file.length()];
			inputStream.read(inputBytes);
			outputBytes = decryption(inputBytes);
			inputStream.close();
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return outputBytes;
	}
	
	private SecretKeySpec getSecretKey(){
		SecretKeySpec secretKey=null;
		try{
			byte[] keySpec = gui.getKeySpec().getBytes();
			secretKey= new  SecretKeySpec(keySpec, "AES");
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return secretKey;
	}
	
	private IvParameterSpec getSeretIvSpec(){
		IvParameterSpec secretIvSepc=null;
		try{
			byte[] ivKey = gui.getIvKey().getBytes();
			secretIvSepc = new IvParameterSpec(ivKey); 
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return secretIvSepc;
	}
}
