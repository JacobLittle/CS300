import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class login extends JFrame {
	private static final long serialVersionUID = 1L;
	private
		staffList slist;
		JPasswordField pa;
		JTextField na;
		String user, password;
		boolean isStaff, closePlease, isManager;
	
	public login() {
		super("Login");
		this.setBounds(500, 200, 200, 200);
		slist = new staffList();//read in staff for login verification
		
		setSize(200, 200);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		JPanel p = new JPanel(new GridBagLayout());
		add(p);
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5,5,5,5);
		
		JLabel name = new JLabel("Username:");
		JLabel pass = new JLabel("Password:");
		na = new JTextField(5);
		pa = new JPasswordField(5);
		JButton enter = new JButton("Enter");
		JButton cancel = new JButton("Cancel");
		
		enter.addActionListener(new ActionListener() {
			
			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent arg0) {
				user = na.getText();
				password = pa.getText();
				isStaff = slist.verify(user, password);
				isManager = slist.isManager(user);
			}
		});
		
		cancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				closePlease = true;
			}
		});
		
		gbc.gridx = -1;
		gbc.gridy = 0;
		p.add(name, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		p.add(na, gbc);
		
		gbc.gridx = -1;
		gbc.gridy = 1;
		p.add(pass, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 1;
		p.add(pa, gbc);
		
		gbc.gridx = -1;
		gbc.gridy = 2;
		p.add(enter, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 2;
		p.add(cancel, gbc);
	}
	
	public boolean getIsStaff() {
		return isStaff;
	}
	
	public boolean getIsManager() {
		return isManager;
	}
	
	public boolean getClosePlease() {
		return closePlease;
	}
}
