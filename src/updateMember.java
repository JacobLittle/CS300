import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class updateMember extends JFrame {
	private static final long serialVersionUID = 1L;
	private JLabel mNumber, name, address, zipCode, state, city, status, warning;
	private JTextField mNum, na, ad, zip, st, cit, stat;
	private JButton cancel, enter;
	private memberList members;
	private providerList providers;
	private updateMember thisMem;
	
	public updateMember(memberList mem, providerList prov, final MainMenu handle) {
		super("Update Member");
		members = mem;
		thisMem = this;
		providers = prov;
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		
		JPanel p = new JPanel(new GridBagLayout());
		this.add(p);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5,5,5,5);
		this.setBounds(200, 150, 700, 500);
		
		mNumber = new JLabel("Member Number(9 digits):");
		name = new JLabel("Name (max of 25 characters):");
		address = new JLabel("Address (max of 25 characters):");
		zipCode = new JLabel("Zipcode (5 digits):");
		state = new JLabel("State (2 characters):");
		city = new JLabel("City (max of 14 characters):");
		status = new JLabel("Member Status (y/n):");
		warning = new JLabel("For word inputs leave blank if you want them unchanged, for numbers enter 0.");
		
		mNum = new JTextField(10);
		na = new JTextField(10);
		ad = new JTextField(10);
		zip = new JTextField(10);
		st = new JTextField(10);
		cit = new JTextField(10);
		stat = new JTextField(10);
		
		cancel = new JButton("Cancel");
		enter = new JButton("Finish");
		
		warning.setForeground(Color.RED);
		
		cancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				thisMem.setVisible(false);
			}
		});
		
		enter.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String mName, mAddress, mCity, mState, mNumb, tempStatus;
				int mZip, mNumbInt, mStatus;
				boolean  isCorrect;
				
				isCorrect = true;
				try {
					mNumb = mNum.getText();
					mName = na.getText();
					mAddress = ad.getText();
					mCity = cit.getText();
					mState = st.getText();
					mNumbInt = Integer.parseInt(mNumb);
					mZip = Integer.parseInt(zip.getText());
					tempStatus = stat.getText();
					/*if (mNumb.length() != 9 || mName != "" || mAddress != "" || mCity != "" || mState != "" || tempStatus != "" || mZip != 0) {
						if(mNumb.length() != 9 || mName.length() > 25 || mAddress.length() > 25 || mCity.length() > 14 || mState.length() > 2 || tempStatus.length() > 1 || mZip > 99999 || mZip < 10000)
							isCorrect = false;*/
				
					if (mNumbInt > 999999999 || mNumbInt < 100000000)
						isCorrect = false;
					
					if (mName.compareTo("") != 0) {
						if (mName.length() > 25) {
							isCorrect = false;	
						}
					}
					if (mAddress.compareTo("") != 0) {
						if (mAddress.length() > 25) {
							isCorrect = false;
						}
					}
					if (mCity.compareTo("") != 0) {
						if (mCity.length() > 14) {
							isCorrect = false;
						}
					}
					if (mState.compareTo("") != 0) {
						if (mState.length() > 2) {
							isCorrect = false;
						}
					}
					if (mZip != 0) {
						if (mZip > 99999 || mZip < 10000) {
							isCorrect = false;
						}
					}	
					if (tempStatus.compareTo("") != 0) {
						if (tempStatus.compareTo("y") != 0 || tempStatus.compareTo("n") != 0 || tempStatus.compareTo("Y") != 0 || tempStatus.compareTo("N") != 0 ) {
							isCorrect = false;
						}
					}
					if (tempStatus.compareTo("y") == 0 || tempStatus.compareTo("Y") == 0)	
						mStatus = 1;
					else
						mStatus = 2;
					
					if(isCorrect == true) {
						members.update(mName, mAddress, mCity, mState, mZip, mNumbInt, mStatus);
						if(mName.equals("") == false)
						{
							providers.updateServicesWithmName(mNumbInt, mName);
						}
						
						//providerList data member is called "providers"
						
						//Do any list work before this. These update the master lists in MainMenu.
						handle.updateMembers(members);
						handle.updateProviders(providers);
						thisMem.setVisible(false);
					}
				}
				catch(RuntimeException e1) {
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
		p.add(mNumber, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 1;
		p.add(mNum, gbc);
		
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
		p.add(status, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 7;
		p.add(stat, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 8;
		p.add(cancel, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 8;
		p.add(enter, gbc);
		
	
	}
	
	public memberList getUpdated() {
		return members;
	}
}
