package EncryptionDecryption;
import java.io.File;
import java.io.FileInputStream;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import GUI.MainFrame;

public class AES_GCM {
	private MainFrame gui = null;	
	public AES_GCM(MainFrame gui){
		this.gui=gui;
	}
	
	public byte[] encryption(byte[] src){
		if(src!=null){
			try{
				Cipher cipher = Cipher.getInstance(gui.getSelectedAlgorithmMode());
				cipher.init(Cipher.ENCRYPT_MODE, getSecretKey(), getGCMParameterSpec());
				return cipher.doFinal(src);
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}	
		return null;
	}
	
	public byte[] decryption(byte[] src){
		if(src!=null){
			try{
				Cipher cipher = Cipher.getInstance(gui.getSelectedAlgorithmMode());
				cipher.init(Cipher.DECRYPT_MODE, getSecretKey(), getGCMParameterSpec());
				return cipher.doFinal(src);
			}catch(Exception ex){
				ex.printStackTrace();
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

	private SecretKey getSecretKey(){
		SecretKey secretKey=null;
		try{
			byte[] keySpec = gui.getKeySpec().getBytes();
			secretKey = new SecretKeySpec(keySpec, "AES");
		}catch(Exception ex){
			System.out.println("\n\nFelet uppstår i metod: getSecretKey()\n\n");
			ex.printStackTrace();
		}
		return secretKey;
	} 
	
	private GCMParameterSpec getGCMParameterSpec(){
		GCMParameterSpec gmcIvSpec=null;
		try{
			byte[] ivKey = gui.getIvKey().getBytes();
			gmcIvSpec = new GCMParameterSpec(16 * Byte.SIZE,ivKey);     
		}catch(Exception ex){
			System.out.println("\n\nFelet uppstår i metod: getSecretIvSpec()\n\n");
			ex.printStackTrace();
		}
		return gmcIvSpec;
	} 
	
}
