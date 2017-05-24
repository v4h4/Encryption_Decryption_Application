package GUI;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import EncryptionDecryption.EncryptionDecryptionSelector;

public class MainFrame {
	private JFrame mainFrame = null;
	private PanelForModeAndKeySelection modeAndKeySelectionPanelpanelForComboBox=null;
	private PanelForCyrptoControl cryptoPanel=null;
	private EncryptionDecryptionSelector sec = null;
	private ValidationDialogGUI val=null;
	
	public MainFrame(){
		startClientWindowGUI("Encryption & Decryption Application");
		this.val= new ValidationDialogGUI(mainFrame);
		mainFrameCloseListener();
		this.sec= new EncryptionDecryptionSelector(this,val);
		addPanelForComboBoxToFrame();
		addPanelForCryptoControlToFrame();
		
	}
	
	public void startClientWindowGUI(String title){
		try{
			this.mainFrame = new JFrame();
			mainFrame.setTitle(title);
			mainFrame.setVisible(false);
			mainFrame.setSize(505, 690);
			mainFrame.setLayout(null);
			mainFrame.setLocationRelativeTo(null);
			mainFrame.setResizable(false);
			mainFrame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
			mainFrame.setLocation(800, 10);
			mainFrameCloseListener();
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	
	private void addPanelForComboBoxToFrame(){
		modeAndKeySelectionPanelpanelForComboBox = new PanelForModeAndKeySelection();
		mainFrame.add(modeAndKeySelectionPanelpanelForComboBox.getJPanel()).setBounds(10, 10, 480, 175);
	}
	
	private void addPanelForCryptoControlToFrame(){
		this.cryptoPanel = new PanelForCyrptoControl(this,sec);
		mainFrame.add(cryptoPanel.getJPanel()).setBounds(10, 190, 480, 470);
	}
	
	private void mainFrameCloseListener(){
		mainFrame.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				boolean close=true;//val.dynamicConfirmationDialog("Closing down Admin Bank Chatt", "Are you sure that you want to close this Admin Bank Chatt?");
				if(close == true){
					mainFrame.dispose();
					System.exit(0);
					//mainFrameServerSelection.showMainFrame();
				}	
			}
		});
	}
	
	public String getSelectedAlgorithmMode(){
		return this.modeAndKeySelectionPanelpanelForComboBox.getSelectedAlgorithmMode();		
	}
	
	public boolean isKeyandIvKeyStatusInactivated(){
		return this.modeAndKeySelectionPanelpanelForComboBox.isKeyAndIvKeyInactivaten();
	}
	
	public String getKeySpec(){
		return this.modeAndKeySelectionPanelpanelForComboBox.getKeySpec();
	}
	
	public String getIvKey(){
		return this.modeAndKeySelectionPanelpanelForComboBox.getIvKey();
	}
	
	public EncryptionDecryptionSelector getEncryptionDecryptionSec(){
		return this.sec;
	}
	
	public void showGUI(){
		this.mainFrame.setVisible(true);
	}
}
