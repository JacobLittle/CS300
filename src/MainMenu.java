import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.*;

public class MainMenu extends JFrame{
	private static final long serialVersionUID = 1L;
	private static memberList members;
	private static providerList providers;
	private
		addMember addM;
		addProvider addP;
		deleteMember delM;
		deleteProvider delP;
		updateMember upM;
		updateProvider upP;
		MainMenu menu;
		verifyMember verMem;
		boolean manager;
	public MainMenu(memberList mem, providerList prov, boolean isManager) {
		super("Main Menu");//frame title
		members = mem;
		providers = prov;
		menu = this;
		manager = isManager;
		this.setBounds(500, 150, 400, 400);
		GUI.readVerifyRequests(members);
		
		if (manager == true)
			setSize(700, 300);//setting size and closing settings
		else
			setSize(400,400);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		
		//grid for constraints on panel
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10,10,5,5);
		
		//panel for buttons
		JPanel p = new JPanel(new GridBagLayout());
		this.add(p);
		
		//buttons
		JButton addMember = new JButton("Add Member");
		JButton updateMember = new JButton("Update Member Info");
		JButton deleteMember = new JButton("Delete Member");
		
		JButton addProvider = new JButton("Add Provider");
		JButton updateProvider = new JButton("Update Provider Info");
		JButton deleteProvider = new JButton("Delete Provider");
		JButton printSummaryReport = new JButton("Print Summary Report");
		JButton printMemberReports = new JButton("Print Member Reports");
		JButton printProviderReports = new JButton("Print Provider Reports");
		JButton printEFT = new JButton("Print EFT");
		
		JButton quit = new JButton("Quit");
		
		addMember.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//boolean quit, added;
			//	quit = false;
			//	added = false;
				addM = new addMember(members, menu);
				addM.setVisible(true);
				GUI.readVerifyRequests(members);
				Calendar procedure = GUI.read();
				if(GUI.thisWeek(procedure) != true)
				{
					members.writeMemberReport();
					providers.writeProviderReport();
					providers.writeSummaryReport();
					providers.writeEFT();
					
					procedure = Calendar.getInstance(); //set the last time the procedure was run to today
					GUI.write(procedure);
				}
			}
		});
		
		addProvider.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				addP = new addProvider(providers, menu);
				addP.setVisible(true);
				GUI.readVerifyRequests(members);
				Calendar procedure = GUI.read();
				if(GUI.thisWeek(procedure) != true)
				{
					members.writeMemberReport();
					providers.writeProviderReport();
					providers.writeSummaryReport();
					providers.writeEFT();
					
					procedure = Calendar.getInstance(); //set the last time the procedure was run to today
					GUI.write(procedure);
				}
			}
		});
		
		updateMember.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				upM = new updateMember(members, providers, menu);
				upM.setVisible(true);
				GUI.readVerifyRequests(members);
				Calendar procedure = GUI.read();
				if(GUI.thisWeek(procedure) != true)
				{
					members.writeMemberReport();
					providers.writeProviderReport();
					providers.writeSummaryReport();
					providers.writeEFT();
					
					procedure = Calendar.getInstance(); //set the last time the procedure was run to today
					GUI.write(procedure);
				}
			}
		});
		
		updateProvider.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				upP = new updateProvider(members, providers, menu);
				upP.setVisible(true);
				GUI.readVerifyRequests(members);
				Calendar procedure = GUI.read();
				if(GUI.thisWeek(procedure) != true)
				{
					members.writeMemberReport();
					providers.writeProviderReport();
					providers.writeSummaryReport();
					providers.writeEFT();
					
					procedure = Calendar.getInstance(); //set the last time the procedure was run to today
					GUI.write(procedure);
				}
			}
		});
		
		deleteMember.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				delM = new deleteMember(members, menu);
				delM.setVisible(true);
				GUI.readVerifyRequests(members);
				Calendar procedure = GUI.read();
				if(GUI.thisWeek(procedure) != true)
				{
					members.writeMemberReport();
					providers.writeProviderReport();
					providers.writeSummaryReport();
					providers.writeEFT();
					
					procedure = Calendar.getInstance(); //set the last time the procedure was run to today
					GUI.write(procedure);
				}
			}
		});
		
		deleteProvider.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				delP = new deleteProvider(providers, menu);
				delP.setVisible(true);
				GUI.readVerifyRequests(members);
				Calendar procedure = GUI.read();
				if(GUI.thisWeek(procedure) != true)
				{
					members.writeMemberReport();
					providers.writeProviderReport();
					providers.writeSummaryReport();
					providers.writeEFT();
					
					procedure = Calendar.getInstance(); //set the last time the procedure was run to today
					GUI.write(procedure);
				}
			}
		});
		
		printSummaryReport.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				providers.writeSummaryReport();
				GUI.readVerifyRequests(members);
				Calendar procedure = GUI.read();
				if(GUI.thisWeek(procedure) != true)
				{
					members.writeMemberReport();
					providers.writeProviderReport();
					providers.writeSummaryReport();
					providers.writeEFT();
					
					procedure = Calendar.getInstance(); //set the last time the procedure was run to today
					GUI.write(procedure);
				}
			}
		});
		
		printMemberReports.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				members.writeMemberReport();
				GUI.readVerifyRequests(members);
				Calendar procedure = GUI.read();
				if(GUI.thisWeek(procedure) != true)
				{
					members.writeMemberReport();
					providers.writeProviderReport();
					providers.writeSummaryReport();
					providers.writeEFT();
					
					procedure = Calendar.getInstance(); //set the last time the procedure was run to today
					GUI.write(procedure);
				}
			}
		});
		
		printProviderReports.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				providers.writeProviderReport();
				GUI.readVerifyRequests(members);
				Calendar procedure = GUI.read();
				if(GUI.thisWeek(procedure) != true)
				{
					members.writeMemberReport();
					providers.writeProviderReport();
					providers.writeSummaryReport();
					providers.writeEFT();
					
					procedure = Calendar.getInstance(); //set the last time the procedure was run to today
					GUI.write(procedure);
				}
			}
		});
		
		printEFT.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				providers.writeEFT();
				GUI.readVerifyRequests(members);
				Calendar procedure = GUI.read();
				if(GUI.thisWeek(procedure) != true)
				{
					members.writeMemberReport();
					providers.writeProviderReport();
					providers.writeSummaryReport();
					providers.writeEFT();
					
					procedure = Calendar.getInstance(); //set the last time the procedure was run to today
					GUI.write(procedure);
				}
			}
		});
		
		quit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				members.write("");
				providers.write("");
				GUI.readVerifyRequests(members);
				Calendar procedure = GUI.read();
				if(GUI.thisWeek(procedure) != true)
				{
					members.writeMemberReport();
					providers.writeProviderReport();
					providers.writeSummaryReport();
					providers.writeEFT();
					
					procedure = Calendar.getInstance(); //set the last time the procedure was run to today
					GUI.write(procedure);
				}
				menu.setVisible(false);
				System.exit(0);
			}
		});
		
		//non manager display
		if (manager == false) {
		//Add Components to the window
			gbc.gridx = 0;
			gbc.gridy = 1;
			p.add(addMember, gbc);
			gbc.gridx = 0;
			gbc.gridy = 4;
			p.add(addProvider, gbc);
			gbc.gridx = 0;
			gbc.gridy = 2;
			p.add(updateMember, gbc);
			gbc.gridx = 0;
			gbc.gridy = 5;
			p.add(updateProvider, gbc);
			gbc.gridx = 0;
			gbc.gridy = 3;
			p.add(deleteMember, gbc);
			gbc.gridx = 0;
			gbc.gridy = 6;
			p.add(deleteProvider, gbc);
			gbc.gridx = 0;
			gbc.gridy = 7;
			p.add(quit, gbc);
		}
		
		//manager display
		else if (manager == true) {
			gbc.gridx = 0;
			gbc.gridy = 0;
			p.add(addMember, gbc);
			gbc.gridx = 0;
			gbc.gridy = 1;
			p.add(addProvider, gbc);
			gbc.gridx = 1;
			gbc.gridy = 0;
			p.add(updateMember, gbc);
			gbc.gridx = 1;
			gbc.gridy = 1;
			p.add(updateProvider, gbc);
			gbc.gridx = 2;
			gbc.gridy = 0;
			p.add(deleteMember, gbc);
			gbc.gridx = 2;
			gbc.gridy = 1;
			p.add(deleteProvider, gbc);
			gbc.gridx = 0;
			gbc.gridy = 2;
			p.add(printMemberReports, gbc);
			gbc.gridx = 1;
			gbc.gridy = 2;
			p.add(printProviderReports, gbc);
			gbc.gridx = 2;
			gbc.gridy = 2;
			p.add(printSummaryReport, gbc);
			gbc.gridx = 3;
			gbc.gridy = 2;
			p.add(printEFT, gbc);
			gbc.gridx = 1;
			gbc.gridy = 3;
			p.add(quit, gbc);
		}
		
	}
	
	public void updateMembers(memberList mem) {
		MainMenu.members = mem;
		members.write("");
		GUI.readVerifyRequests(members);
		Calendar procedure = GUI.read();
		if(GUI.thisWeek(procedure) != true)
		{
			members.writeMemberReport();
			providers.writeProviderReport();
			providers.writeSummaryReport();
			providers.writeEFT();
			
			procedure = Calendar.getInstance(); //set the last time the procedure was run to today
			GUI.write(procedure);
		}
	}
	
	public void updateProviders(providerList prov) {
		MainMenu.providers = prov;
		providers.write("");
		GUI.readVerifyRequests(members);
		Calendar procedure = GUI.read();
		if(GUI.thisWeek(procedure) != true)
		{
			members.writeMemberReport();
			providers.writeProviderReport();
			providers.writeSummaryReport();
			providers.writeEFT();
			
			procedure = Calendar.getInstance(); //set the last time the procedure was run to today
			GUI.write(procedure);
		}
	}
}
