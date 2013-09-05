import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;



public class GUI {

	/**
	 * @param args
	 */
	//1st read file to load members into list
	//2nd read file to load providers into list
	//3rd read file to load staff directory
	//4th read file to load provider directory
	//when closing the program
	//call write functions for : members and providers
	//we need to set a specific file for the file that we write and read from with an appropriate name
	// we need cancel buttons for adding updating and all that stuff to bring us back to the menu of what to do
	//
	
	public static void readBills(memberList members, providerList providers, providerDirectory pDirectory)
	{
		int pNumber = 0;
		int mNumber = 0;
		int serviceCode = 0;
		String comments = "";
		String mName = "";
		String pName = "";
		String sName ="";
		int fee = 0;
		Calendar dateReceived = Calendar.getInstance(); //Includes date and time
		int rMonth = 0;
		int rDay = 0;
		int rYear = 0;
		int rHour = 0;
		int rMinute = 0;
		int rSecond = 0;
		Calendar dateProvided = Calendar.getInstance(); //includes date
		int pMonth = 0;
		int pDay = 0;
		int pYear = 0;
		
		String path = "Bills.txt";
		try {
			BufferedReader reader = new BufferedReader(
			new FileReader(path));
			String line = reader.readLine();
			while(line != null) 
			{
				 rMonth = Integer.parseInt(line.substring(0,2 )) - 1;
				 rDay = Integer.parseInt(line.substring(3,5 ));
				 rYear = Integer.parseInt(line.substring(6,10 ));
				 rHour = Integer.parseInt(line.substring(11,13 ));
				 rMinute = Integer.parseInt(line.substring(14,16 ));
				 rSecond = Integer.parseInt(line.substring(17,19 ));
				 
				 line = reader.readLine();
				 pMonth = Integer.parseInt(line.substring(0,2 )) - 1;
				 pDay = Integer.parseInt(line.substring(3,5 ));
				 pYear = Integer.parseInt(line.substring(6,10 ));
				 
				 pNumber = Integer.parseInt(reader.readLine());
				 mNumber = Integer.parseInt(reader.readLine());
				 serviceCode = Integer.parseInt(reader.readLine());
				 comments = reader.readLine();
				 sName = pDirectory.findName(serviceCode);
				 pName = providers.findName(pNumber);
				 mName = members.findName(mNumber);
				 fee = pDirectory.findFee(serviceCode);
				
				 dateReceived = Calendar.getInstance();
				 dateProvided = Calendar.getInstance();
				 
				 dateProvided.set(pYear, pMonth, pDay);
				 dateReceived.set(rYear, rMonth, rDay, rHour, rMinute, rSecond);
				 
				 
				 members.addService(pNumber, mNumber, serviceCode, fee, comments, mName, pName, sName, dateReceived, dateProvided);
				 providers.addService(pNumber, mNumber, serviceCode, fee, comments, mName, pName, sName, dateReceived, dateProvided);
				//do something with line
				line = reader.readLine();
			}
			reader.close();
			}
			catch(FileNotFoundException e) {
			//the specified file could not be found
			}
			catch(IOException e) {
			//something went wrong with reading or closing
			}
		
		try {
			FileWriter writer = new FileWriter(path);
			writer.write("");
			writer.close();
			}
			catch(IOException e) {
			//something went wrong with accessing the file;
			}
		
		members.write("");
		providers.write("");
		
	}
	
	public static void readVerifyRequests(memberList members)
	{
		int number;
		String path = "VerifyRequests.txt";
		try {
			BufferedReader reader = new BufferedReader(
			new FileReader(path));
			String line = reader.readLine();
			while(line != null) 
			{
				number = Integer.parseInt(line);
				GUI.verifyMember(members, number);
				//do something with line
				line = reader.readLine();
			}
			reader.close();
			}
			catch(FileNotFoundException e) {
			//the specified file could not be found
			}
			catch(IOException e) {
			//something went wrong with reading or closing
			}
		
		
		
		try {
			FileWriter writer = new FileWriter(path);
			writer.write("");
			writer.close();
			}
			catch(IOException e) {
			//something went wrong with accessing the file;
			}
	}
	
	public static void verifyMember(memberList members, int number)
	{
		String message = "";
		
		int status = members.verifyMember(number);
		if(status == 0)
		{
			message = "Member Suspended.";
		}
		else if(status == 1)
		{
			message = "Validated.";
		}
		else if(status == 3)
		{
			message = "Invalid Number.";
		}
		
		String path = "Verify" + number + ".txt";
		
		try {
			FileWriter writer = new FileWriter(path);
			writer.write(message);
			writer.close();
			}
			catch(IOException e) {
			//something went wrong with accessing the file;
			}
	}
	
	
	public static Calendar read()
	{
		Calendar procedure = Calendar.getInstance();
		int day;
		int month;
		int year;
		int second;
		int minute;
		int hour;
		try {
			BufferedReader reader = new BufferedReader(
			new FileReader("ProcedureDate.txt"));
			String line = reader.readLine();
			if(line != null) 
			{
				month = Integer.parseInt(line);
				day = Integer.parseInt(reader.readLine());
				year = Integer.parseInt(reader.readLine());
				hour = Integer.parseInt(reader.readLine());
				minute = Integer.parseInt(reader.readLine());
				second = Integer.parseInt(reader.readLine());
				procedure.set(year, month, day, hour, minute, second);
				//do something with line
				//line = reader.readLine();
			}
			reader.close();
			}
			catch(FileNotFoundException e) {
			//the specified file could not be found
			}
			catch(IOException e) {
			//something went wrong with reading or closing
			}
		
		return procedure;
	}
	
	
	public static void write(Calendar procedure)
	{
		String total = "";
		String path = "ProcedureDate.txt";
		total += procedure.get(Calendar.MONTH) 
				+ System.lineSeparator() 
				+ procedure.get(Calendar.DAY_OF_MONTH) 
				+ System.lineSeparator() 
				+ procedure.get(Calendar.YEAR) 
				+ System.lineSeparator() 
				+ procedure.get(Calendar.HOUR)
				+ System.lineSeparator() 
				+ procedure.get(Calendar.MINUTE) 
				+ System.lineSeparator() 
				+ procedure.get(Calendar.SECOND) 
				+ System.lineSeparator(); //write Info
		
		try {
			FileWriter writer = new FileWriter(path);
			writer.write(total);
			writer.close();
			}
			catch(IOException e) {
			//something went wrong with accessing the file;
			}
	}
	
	
	
	public static boolean thisWeek(Calendar procedure)
	{
		Calendar newDate = Calendar.getInstance(); //creates a new date with the current time
		newDate.add(Calendar.DATE, -7); //Subtracts 7 days from the newDate
		//newDate.setTimeInMillis(0);
		
		if(procedure.after(newDate) == true) //date provided is within the last week
		{
			return true;
		}
		
		return false;
	}
	
	public static void main(String[] args) 
	{
		
		
		memberList members = new memberList();
		providerList providers = new providerList();
		providerDirectory providerDir = new providerDirectory();
		staffList staff = new staffList();
		
		staff.read();
		providerDir.read();
		members.read();
		providers.read();
		//ADDED STUFF HERE FOR READBILLS STATEMENT SHOULD NOT MOVE, BILLS ONLY READ WHEN PROGRAM IS OPENED
		GUI.readBills(members, providers, providerDir);
		providers = new providerList();
		members = new memberList();
		members.read();
		providers.read();
		
		//added stuff here jacob. This is the automatic account procedure call
		
	/*//	TEST CODE
	// TODO Auto-generated method stub
		//Call test function for service
		//service.test();
		serviceList.test();
	//	provider.test();
		providerList.test();
		memberList.test();
		//providerDirectory.test();
	//	staffList.test();
	*/
	
		
		login login = new login();
		boolean isStaff = false;
		boolean isManager = false;
		while (isStaff == false) {//while invalid user/pass is entered
			login.setVisible(true);//show login window
			if (login.getClosePlease() == true)//check to see if cancel has been pressed
				System.exit(0);//exit program
			isStaff = login.getIsStaff();//check if valid input is given
			isManager = login.getIsManager();
		}
		login.setVisible(false);//hide login window
		
		MainMenu main = new MainMenu(members, providers, isManager);
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
		main.setVisible(true);//show main menu
		
		//Add these when appropriate
		//members.writeMemberReport();
		//providers.writeProviderReport();
		//providers.writeSummaryReport();
		//providers.writeEFT();
		
	}
	
}
