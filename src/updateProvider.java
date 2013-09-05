import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class updateProvider extends JFrame{
	private static final long serialVersionUID = 1L;
	private JLabel pNumber, name, address, zipCode, state, city, warning;
	private JTextField pNum, na, ad, zip, st, cit;
	private JButton cancel, enter;
	private providerList providers;
	private memberList members;
	private updateProvider thisProv;
		
	public updateProvider(memberList mem, providerList prov, final MainMenu handle) {
		super("Update Provider");
		providers = prov;
		thisProv = this;
		members = mem;
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		
		JPanel p = new JPanel(new GridBagLayout());
		this.add(p);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5,5,5,5);
		this.setBounds(200, 150, 700, 500);
		
		pNumber = new JLabel("Provider Number  (9 digits):");
		name = new JLabel("Name (max of 25 characters):");
		address = new JLabel("Address (max of 25 characters):");
		zipCode = new JLabel("Zipcode (5 digits):");
		state = new JLabel("State (2 characters):");
		city = new JLabel("City (max of 14 characters):");
		warning = new JLabel("For word inputs leave blank if you want them unchanged, for numbers enter 0.");
		
		pNum = new JTextField(10);
		na = new JTextField(10);
		ad = new JTextField(10);
		zip = new JTextField(10);
		st = new JTextField(10);
		cit = new JTextField(10);
		
		cancel = new JButton("Cancel");
		enter = new JButton("Finish");
		
		warning.setForeground(Color.RED);
		
		cancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				thisProv.setVisible(false);
			}
		});
		
		enter.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String pNumb, pName, pAddress, pCity, pState;
				int pZip, pNumbInt;
				boolean isCorrect;
				
				isCorrect = true;
				
				try {
					pNumb = pNum.getText();
					pName = na.getText();
					pAddress = ad.getText();
					pCity = cit.getText();
					pState = st.getText();
					pNumbInt = Integer.parseInt(pNumb);
					pZip = Integer.parseInt(zip.getText());
					
					//checks all values for length to ensure correctness of inputs
					if(pNumb.compareTo("") != 0) {
						if (pNumb.length() != 9)
							isCorrect = false;
					}
					if (pNumbInt > 999999999 || pNumbInt < 100000000)
						isCorrect = false;
					
					if (pName.compareTo("") != 0) {
						if (pName.length() > 25) {
							isCorrect = false;	
						}
					}
					if (pAddress.compareTo("") != 0) {
						if (pAddress.length() > 25) {
							isCorrect = false;
						}
					}
					if (pCity.compareTo("") != 0) {
						if (pCity.length() > 14) {
							isCorrect = false;
						}
					}
					if (pState.compareTo("") != 0) {
						if (pState.length() > 2) {
							isCorrect = false;
						}
					}
					if (pZip != 0) {
						if (pZip > 99999 || pZip < 10000) {
							isCorrect = false;
						}
					}	
						
					if(isCorrect == true) {
						providers.update(pName, pAddress, pCity, pState, pZip, pNumbInt);
						if(pName.equals("") == false)
						{
							members.updateServicesWithpName(pNumbInt, pName);
						}
						//memberList data member is called "members"
						
						//do any list work before these. They update the master list in MainMenu
						handle.updateProviders(providers);
						handle.updateMembers(members);
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
		p.add(warning, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		p.add(pNumber, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 1;
		p.add(pNum, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 2;
		p.add(name, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 2;
		p.add(na, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 3;
		p.add(address, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 3;
		p.add(ad, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 4;
		p.add(zipCode, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 4;
		p.add(zip, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 5;
		p.add(city, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 5;
		p.add(cit, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 6;
		p.add(state, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 6;
		p.add(st, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 7;
		p.add(cancel, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 7;
		p.add(enter, gbc);
		
	}
}
