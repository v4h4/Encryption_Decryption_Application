package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

public class PanelForModeAndKeySelection {
	private JPanel modeAndKeySelectionPanel=null;
	private JComboBox<String> algorithmComboBox = null;
	private JPasswordField cryptoKeyField =null;
	private JPanel cryptoPanel=null;
	private JPanel initalVectorpanel=null;
	private JPasswordField initialVectorField =null;
	private JLabel keyStatusbLabel=null;
	private JLabel ivKeyStatusbLabel=null;
	private boolean inactivated=false;
	public PanelForModeAndKeySelection(){
		createModeAndKeySelectionPanel();
		addComboBoxToPanel();
		addCryptopKeyFieldToPanel();
		addInitialVectorFieldToPanel();
		addKeyStatusLabelToPanel();
		addInitalVectorKeyStatusLabelToPanel();
	} 
	
	private void createModeAndKeySelectionPanel(){
		this.modeAndKeySelectionPanel = new JPanel();
		this.modeAndKeySelectionPanel.setBorder(BorderFactory.createTitledBorder("Select AES or DES Encryption/Decryption Mode"));
		this.modeAndKeySelectionPanel.setVisible(true);
		this.modeAndKeySelectionPanel.setLayout(null);
	}
	
	private void addComboBoxToPanel(){
		String algorithms[]={"AES/CBC/PKCS5Padding","AES/CTR/PKCS5Padding","AES/ECB/PKCS5Padding","AES/GCM/PKCS5Padding","DES/CTR/PKCS5Padding"};
		algorithmComboBox = new JComboBox<String>(algorithms);
		Font font = new Font("Consolas", Font.BOLD, 20);
		algorithmComboBox.setFont(font);
		modeAndKeySelectionPanel.add(algorithmComboBox).setBounds(10, 25, 460, 30);
		algorithmComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				isIvKeyStatusInactivated();
				isKeyStatusInactivated();
				if(getSelectedAlgorithmMode().contains("DES")){
					cryptoPanel.setBorder(BorderFactory.createTitledBorder("Enter key (8 caharacters)"));
					initalVectorpanel.setBorder(BorderFactory.createTitledBorder("Enter iv-key (8 caharacters)"));
					
				}else{
					cryptoPanel.setBorder(BorderFactory.createTitledBorder("Enter key (16 caharacters)"));
					initalVectorpanel.setBorder(BorderFactory.createTitledBorder("Enter iv-key (16 caharacters)"));
				}
			}
		});
	}
	
	private void addKeyStatusLabelToPanel(){
		this.keyStatusbLabel = new JLabel();
		Font font = new Font("Ariel", Font.BOLD, 25);
		keyStatusbLabel.setFont(font);
		keyStatusbLabel.setHorizontalAlignment(JLabel.CENTER);
		keyStatusbLabel.setBorder(BorderFactory.createTitledBorder("Key Status"));
		modeAndKeySelectionPanel.add(keyStatusbLabel).setBounds(10, 55, 225, 50);
		isKeyStatusInactivated();
	}	
	
	private void addInitalVectorKeyStatusLabelToPanel(){
		this.ivKeyStatusbLabel = new JLabel();
		Font font = new Font("Ariel", Font.BOLD, 21);
		ivKeyStatusbLabel.setFont(font);
		ivKeyStatusbLabel.setHorizontalAlignment(JLabel.CENTER);
		ivKeyStatusbLabel.setBorder(BorderFactory.createTitledBorder("IV-Key Status"));
		modeAndKeySelectionPanel.add(ivKeyStatusbLabel).setBounds(245, 55, 225, 50);
		isIvKeyStatusInactivated();
	}	
	
	private void addCryptopKeyFieldToPanel(){
		this.cryptoKeyField= new JPasswordField();
		this.cryptoPanel = new JPanel();
		cryptoPanel.setBorder(BorderFactory.createTitledBorder("Enter key (16 caharacters)"));
		cryptoPanel.setVisible(true);
		cryptoPanel.setLayout(null);
		Font font = new Font("Arial", Font.BOLD, 35);
		cryptoKeyField.setFont(font);
		cryptoKeyField.setText("");
		cryptoPanel.add(cryptoKeyField).setBounds(10, 20, 205, 30);
		modeAndKeySelectionPanel.add(cryptoPanel).setBounds(10, 105, 225, 60);
		cryptoKeyField.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				isKeyStatusInactivated();
			}
			@Override
			public void keyReleased(KeyEvent arg0) {
				isKeyStatusInactivated();
			}
			@Override
			public void keyPressed(KeyEvent arg0) {
				isKeyStatusInactivated();	
			}
		});
	}
	
	private void addInitialVectorFieldToPanel(){
		this.initialVectorField = new JPasswordField();
		this.initalVectorpanel = new JPanel();
		initalVectorpanel.setBorder(BorderFactory.createTitledBorder("Enter iv-key (16 caharacters)"));
		initalVectorpanel.setVisible(true);
		initalVectorpanel.setLayout(null);
		Font font = new Font("Arial", Font.BOLD, 35);
		initialVectorField.setFont(font);
		initialVectorField.setText("");
		initalVectorpanel.add(initialVectorField).setBounds(10, 20, 205, 30);
		modeAndKeySelectionPanel.add(initalVectorpanel).setBounds(245, 105, 225, 60);
		initialVectorField.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				isIvKeyStatusInactivated();
			}
			@Override
			public void keyReleased(KeyEvent arg0) {
				isIvKeyStatusInactivated();
			}
			@Override
			public void keyPressed(KeyEvent arg0) {
				isIvKeyStatusInactivated();	
			}
		});
	}
	
	private boolean isKeyStatusInactivated(){
		if(getSelectedAlgorithmMode().contains("DES") && cryptoKeyField.getPassword().length==8 ){
			keyStatusbLabel.setForeground(new Color(0,150,0));
			keyStatusbLabel.setText("KEY CORRECT");
			inactivated= false;
		}
		else if(!getSelectedAlgorithmMode().contains("DES") && cryptoKeyField.getPassword().length==16){
			keyStatusbLabel.setForeground(new Color(0,150,0));
			keyStatusbLabel.setText("KEY CORRECT");
			inactivated= false;
		}else{
			keyStatusbLabel.setForeground(Color.RED);
			keyStatusbLabel.setText("KEY INCORRECT");
			inactivated= true;
		}
		return inactivated;
	}
	
	private boolean isIvKeyStatusInactivated(){
		if(getSelectedAlgorithmMode().contains("AES/ECB")){
			ivKeyStatusbLabel.setForeground(new Color(0,150,0));
			ivKeyStatusbLabel.setText("NOT NEEDED");
			initialVectorField.setEnabled(false);
			inactivated= true;
		}else if(!getSelectedAlgorithmMode().contains("DES") && initialVectorField.getPassword().length==16){
			ivKeyStatusbLabel.setForeground(new Color(0,150,0));
			ivKeyStatusbLabel.setText("IV-KEY CORRECT");
			inactivated= false;
			initialVectorField.setEnabled(true);
		}else if(getSelectedAlgorithmMode().contains("DES") && initialVectorField.getPassword().length==8){
			ivKeyStatusbLabel.setForeground(new Color(0,150,0));
			ivKeyStatusbLabel.setText("IV-KEY CORRECT");
			inactivated= false;
			initialVectorField.setEnabled(true);
		}else{
			ivKeyStatusbLabel.setForeground(Color.RED);
			ivKeyStatusbLabel.setText("IV-KEY INCORRECT");
			initialVectorField.setEnabled(true);
			inactivated= true;
		}
		return inactivated;
	}
	
	public boolean isKeyAndIvKeyInactivaten(){
		if(isIvKeyStatusInactivated()==true && isKeyStatusInactivated()==true){
			return true;
		}else{
			return false;
		}
	}
	
	public String getSelectedAlgorithmMode(){
		return this.algorithmComboBox.getSelectedItem().toString();
	}
	
	public JPanel getJPanel(){
		return this.modeAndKeySelectionPanel;
	}
	
	public String getKeySpec(){
		return new String(this.cryptoKeyField.getPassword());
	}
	
	public String getIvKey(){
		return new String(this.initialVectorField.getPassword());
	}
	
}
