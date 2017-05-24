package EncryptionDecryption;

import java.io.File;
import java.io.FileOutputStream;

import javax.swing.JFileChooser;

import GUI.MainFrame;
import GUI.ValidationDialogGUI;
public class EncryptionDecryptionSelector {
	private MainFrame gui = null;
	private AES_CBC cbc=null;
	private AES_CTR ctr=null;
	private AES_ECB ecb =null;
	private AES_GCM gcm = null;
	private DES_CTR desCtr =null;
	private ValidationDialogGUI val=null;
	public EncryptionDecryptionSelector(MainFrame gui,ValidationDialogGUI val){
		this.gui=gui;
		this.val=val;
		this.cbc= new AES_CBC(gui);
		this.ctr= new AES_CTR(gui);
		this.ecb= new AES_ECB(gui);
		this.gcm= new AES_GCM(gui);
		this.desCtr=new DES_CTR(gui);
	}
	
	public byte[] encrypt(byte[] src){
		System.out.println("ggggrrrrrr");
		if(gui.getSelectedAlgorithmMode().contains("AES/CBC")){
			return cbc.encryption(src);
		}else if(gui.getSelectedAlgorithmMode().contains("AES/CTR")){
			return ctr.encryption(src);
		}else if(gui.getSelectedAlgorithmMode().contains("AES/ECB")){
			return ecb.encryption(src);
		}else if(gui.getSelectedAlgorithmMode().contains("AES/GCM")){
			return gcm.encryption(src);
		}else if(gui.getSelectedAlgorithmMode().contains("DES/CTR")){
			return desCtr.encryption(src);
		}
		System.out.println("(selector\nreturn null");
		return null;
	}
	
	public byte[] decrypt(byte[] src){
		if(gui.getSelectedAlgorithmMode().contains("AES/CBC")){
			return cbc.decryption(src);
		}else if(gui.getSelectedAlgorithmMode().contains("AES/CTR")){
			return ctr.decryption(src);
		}else if(gui.getSelectedAlgorithmMode().contains("AES/ECB")){
			return ecb.decryption(src);
		}else if(gui.getSelectedAlgorithmMode().contains("AES/GCM")){
			return gcm.decryption(src);
		}else if(gui.getSelectedAlgorithmMode().contains("DES/CTR")){
			return desCtr.decryption(src);
		}
		System.out.println("(selector\nreturn null");
		return null;
	}
	
	public void encryptFile(){
		if(gui.getSelectedAlgorithmMode().contains("AES/CBC")){
			File file=selectFileDialog();
			byte[] byteArr=cbc.encryptFile(file);
			val.dynamicWarningDialogWindow("File Encryption","The file:"+file.getName()+" has been encrypted,\npress OK to choose location to the file");
			saveFileDialog(byteArr, file.getName());
		}else if(gui.getSelectedAlgorithmMode().contains("AES/CTR")){
			File file=selectFileDialog();
			byte[] byteArr=ctr.encryptFile(file);
			val.dynamicWarningDialogWindow("File Encryption","The file:"+file.getName()+" has been encrypted,\npress OK to choose location to the file");
			saveFileDialog(byteArr, file.getName());
		}else if(gui.getSelectedAlgorithmMode().contains("AES/ECB")){
			File file=selectFileDialog();
			byte[] byteArr=ecb.encryptFile(file);
			val.dynamicWarningDialogWindow("File Encryption","The file:"+file.getName()+" has been encrypted,\npress OK to choose location to the file");
			saveFileDialog(byteArr, file.getName());
		}else if(gui.getSelectedAlgorithmMode().contains("AES/GCM")){
			File file=selectFileDialog();
			byte[] byteArr=gcm.encryptFile(file);
			val.dynamicWarningDialogWindow("File Encryption","The file:"+file.getName()+" has been encrypted,\npress OK to choose location to the file");
			saveFileDialog(byteArr, file.getName());
		}else if(gui.getSelectedAlgorithmMode().contains("DES/CTR")){
			File file=selectFileDialog();
			byte[] byteArr=desCtr.encryptFile(file);
			val.dynamicWarningDialogWindow("File Encryption","The file:"+file.getName()+" has been encrypted,\npress OK to choose location to the file");
			saveFileDialog(byteArr, file.getName());
		}
	}
	
	public void decryptFile(){
		if(gui.getSelectedAlgorithmMode().contains("AES/CBC")){
			File file=selectFileDialog();
			byte[] byteArr=cbc.decryptFile(file);
			val.dynamicWarningDialogWindow("File Encryption","The file:"+file.getName()+" has been decrypted,\npress OK to choose location to the file");
			saveFileDialog(byteArr, file.getName());
		}else if(gui.getSelectedAlgorithmMode().contains("AES/CTR")){
			File file=selectFileDialog();
			byte[] byteArr=ctr.decryptFile(file);
			val.dynamicWarningDialogWindow("File Encryption","The file:"+file.getName()+" has been decrypted,\npress OK to choose location to the file");
			saveFileDialog(byteArr, file.getName());
		}else if(gui.getSelectedAlgorithmMode().contains("AES/ECB")){
			File file=selectFileDialog();
			byte[] byteArr=ecb.decryptFile(file);
			val.dynamicWarningDialogWindow("File Encryption","The file:"+file.getName()+" has been decrypted,\npress OK to choose location to the file");
			saveFileDialog(byteArr, file.getName());
		}else if(gui.getSelectedAlgorithmMode().contains("AES/GCM")){
			File file=selectFileDialog();
			byte[] byteArr=gcm.decryptFile(file);
			val.dynamicWarningDialogWindow("File Encryption","The file:"+file.getName()+" has been decrypted,\npress OK to choose location to the file");
			saveFileDialog(byteArr, file.getName());
		}else if(gui.getSelectedAlgorithmMode().contains("DES/CTR")){
			File file=selectFileDialog();
			byte[] byteArr=desCtr.decryptFile(file);
			val.dynamicWarningDialogWindow("File Encryption","The file:"+file.getName()+" has been decrypted,\npress OK to choose location to the file");
			saveFileDialog(byteArr, file.getName());
		}
	}


private File selectFileDialog(){
	File file=null;
	try{
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Select file");
		if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
		    file = fileChooser.getSelectedFile();
		}
	}catch(Exception ex){
		ex.printStackTrace();
	}
	return file;
}

private void saveFileDialog(byte[] byteArr, String fileName){
	try{
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Save file");
		if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
		    File file = fileChooser.getSelectedFile();
		    FileOutputStream outputStream = new FileOutputStream(file.getAbsolutePath());
			outputStream.write(byteArr);
			outputStream.close();
		}
	}catch(Exception ex){
		ex.printStackTrace();
	}
}
	
}
	

