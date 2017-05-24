package EncryptionDecryption;

import java.io.File;
import java.io.FileInputStream;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import GUI.MainFrame;

public class DES_CTR {
	private MainFrame gui = null;	
	public DES_CTR(MainFrame gui){
		this.gui=gui;
	}
	
	public byte[] encryption(byte[] src){
		if(src!=null){
			try{
				Cipher cipher = Cipher.getInstance(gui.getSelectedAlgorithmMode());
				cipher.init(Cipher.ENCRYPT_MODE,getSecretKeySpec(),getIvParameterSpec());
				byte [] cipherText= new byte[cipher.getOutputSize(src.length)];
				int ctLength=cipher.update(src,0,src.length,cipherText,0);
				ctLength+=cipher.doFinal(cipherText,ctLength);
				return cipherText;
			}catch(Exception ex){
				System.out.println("Encryption FAILD");
				ex.printStackTrace();
				return null;
			}
		}
		return null;
	}


	public byte[] decryption(byte[] src){
		if(src!=null){
			try{
				Cipher cipher = Cipher.getInstance(gui.getSelectedAlgorithmMode());
				cipher.init(Cipher.DECRYPT_MODE,getSecretKeySpec(),getIvParameterSpec());
				byte []ctLength= new byte[cipher.getOutputSize(src.length)];
				int qtLength=ctLength.length;
				byte [] plainText = new byte[cipher.getOutputSize(qtLength)]; 
				int ptLength = cipher.update(src,0,qtLength,plainText);
				ptLength+=cipher.doFinal(plainText,ptLength);
				return plainText; 
			}catch(Exception ex){
				System.out.println("Decryption FAILD");
				ex.printStackTrace();
				return null;
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

	private SecretKeySpec getSecretKeySpec(){
		SecretKeySpec key=null;
		try{
			byte [] keySpec = gui.getKeySpec().getBytes();
			key = new SecretKeySpec(keySpec, "DES");
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return key;
	}
	
	private IvParameterSpec getIvParameterSpec(){
		IvParameterSpec ivSpec=null;
		try{
			byte [] ivBytes = gui.getIvKey().getBytes();
			ivSpec = new IvParameterSpec(ivBytes);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return ivSpec;
	}
}
