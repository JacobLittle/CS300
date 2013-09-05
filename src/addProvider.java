import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class addProvider extends JFrame {
	private static final long serialVersionUID = 1L;
	private JLabel name, address, zipCode, state, city;
	private JTextField na, ad, zip, st, cit;
	private JButton cancel, enter;
	private providerList providers;
	private addProvider thisProv;
		
	public addProvider(providerList prov, final MainMenu handle) {
		super("Add Provider");
		providers = prov;
		thisProv = this;
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		
		JPanel p = new JPanel(new GridBagLayout());
		this.add(p);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5,5,5,5);
		this.setBounds(200, 150, 500, 500);
		
		name = new JLabel("Name (max of 25 characters):");
		address = new JLabel("Address (max of 25 characters):");
		zipCode = new JLabel("Zipcode (5 digits):");
		state = new JLabel("State (2 characters):");
		city = new JLabel("City (max of 14 characters):");
		
		na = new JTextField(10);
		ad = new JTextField(10);
		zip = new JTextField(10);
		st = new JTextField(10);
		cit = new JTextField(10);
		
		cancel = new JButton("Cancel");
		enter = new JButton("Finish");
		
		cancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				thisProv.setVisible(false);
			}
		});
		
		enter.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String pName, pAddress, pCity, pState;
				int pZip;
				boolean isCorrect;
				
				isCorrect = true;
				
				try {
					pName = na.getText();
					pAddress = ad.getText();
					pCity = cit.getText();
					pState = st.getText();
					pZip = Integer.parseInt(zip.getText());
					
					//checks all values for length to ensure correctness of inputs
					if(pName.length() > 25 || pAddress.length() > 25 || pCity.length() > 14 || pState.length() > 2 || pZip > 99999 || pZip < 10000)
						isCorrect = false;
				
					if(isCorrect == true) {
						providers.add(pName, pAddress, pCity, pState, pZip, 0);
						handle.updateProviders(providers);
						thisProv.setVisible(false);
					}
				}
				catch (RuntimeException e1) {
					isCorrect = false;
				}
				if (isCorrect == false) {
					JOptionPane.showMessageDialog(null, "One or More of the inputs was incorrect, Please check inputs and try again.");
				}	
			}
		});
		
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		p.add(name, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		p.add(na, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		p.add(address, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 1;
		p.add(ad, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 2;
		p.add(zipCode, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 2;
		p.add(zip, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 3;
		p.add(city, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 3;
		p.add(cit, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 4;
		p.add(state, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 4;
		p.add(st, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 6;
		p.add(cancel, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 6;
		p.add(enter, gbc);
		
	
	}
	
	public providerList getUpdated() {
		return providers;
	}
}
