import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;


public class memberList {
	private
		node first;
		int largest;

	//memberList constructor	
	public memberList() {
		first = null;
		largest = 111111;
	}
	
	//used to find the name of a member when given a member number, returns a string.
	public String findName(int number)
	{
		node current = first;
		
		if(current == null)
			return "";
		
		//search for the correct node
		while((current.getData().getNumber() != number) && (current.getNext() != null))
		{
			current = current.getNext();
		}
		
		//if it is the correct number
		if(current.getData().getNumber() == number)
			return current.getData().getName();
		
		else
			return "";
	}
	
	//Searches member list for the number passed in. returns the member if found, or null if not
	public member getMember(int number)
	{
		
		node current = first;
		
		while(current != null)
		{
			if(current.getData().getNumber() == number)
			{
				
				return current.getData();
			}
			current = current.getNext();
		}
		
		return null;
	}
	
	//writes member reports for all members in the list
	public void writeMemberReport()
	{
		node current = first;
		String toWrite = "";
		String path = "";
		Calendar date = Calendar.getInstance();
		
		if(current == null)
			return;
		
		while(current != null)
		{
			if(current.getData().serviceThisWeek())
			{
				path = current.getData().getName() + (date.get(Calendar.MONTH)+1) + date.get(Calendar.DAY_OF_MONTH) 
						+ date.get(Calendar.YEAR) + ".txt"; //file name is name<current date MM-DD-YYYY>.txt
				
				toWrite = current.getData().writeMemberReport();
				try {
					FileWriter writer = new FileWriter(path);
					writer.write(toWrite);
					writer.close();
					}
					catch(IOException e) {
					//something went wrong with accessing the file;
					}
			}
			
			current = current.getNext();
		}
	}
	
	//Adds member to list
	public void add(String name, String address, String city, String state, int zipCode, int number, boolean status)
	{
		node newNode = new node(name, address, city, state, zipCode, number, status);
		node current = first;
		node previous = current;
		
		if(number == 0)
		{
			number = generateNumber();
			newNode.getData().setNumber(number);
		}
		
		if(number > largest)
		{
			largest = number;
		}
		
		//if there is no list, then make the new node the first node
		if(first == null)
		{
			first = newNode;
			newNode.setNext(null);
			return;
		}
		
		//if they are adding a member with an already taken number
		if(newNode.getData().getNumber() == current.getData().getNumber())
		{
			return;
		}
		
		//if the new node's number is lower than the first node
		if(newNode.getData().getNumber() < first.getData().getNumber())
		{
			newNode.setNext(first);
			first = newNode;
			return;
		}
		//if there is a next
		//if(current.getNext() != null)
		{
		//	current = current.getNext();
			//iterate through the list until the newNode's number is less than the current node's number
			while(newNode.getData().getNumber() > current.getData().getNumber())
			{
				//if they are adding a member with an already taken number
				if(newNode.getData().getNumber() == current.getData().getNumber())
				{
					return;
				}
				
				previous = current;
				current = current.getNext();
				//if there is no next
				if(current == null)
					break;
			}
		
		}
		
		if(current != null)
		{
			//if they are adding a member with an already taken number
			if(newNode.getData().getNumber() == current.getData().getNumber())
			{
				return;
			}
		}
		//if there are nodes after the previous node
		
		//put the new Node in between the previous node and the current node
		previous.setNext(newNode);
		newNode.setNext(current);
		return;
	}
	
	//Deletes requested member from the list
	public void delete(int number)
	{
		node current = first;
		node previous = current;
		
		if(number == first.getData().getNumber())
		{
			first = first.getNext();
			return;
		}
		
		while(current != null)
		{
			if(number == current.getData().getNumber())
			{
				previous.setNext(current.getNext());
				return;
			}
			
			previous = current;
			current = current.getNext();
		}
		
		if(current == null)
		{
			return;
		}
	}	
	
	public void updateServicesWithpName(int number, String pName)
	{
		node current = first;
		
		while(current != null)
		{
			current.getData().getsList().updateProviderName(number, pName);
			current = current.getNext();
		}
	}
	
	
	//updates all fields in a current member
	public void update(String name, String address, String city, String state, int zipCode, int number, int status)
	{
		node current = first;
		
		while(current != null)
		{
			if(current.getData().getNumber() == number)
			{
				boolean check;
				check = current.getData().getStatus();
				if(name.equals("") == false)
				{
					current.getData().setName(name);
					current.getData().getsList().updateMemberName(number, name);
					//updateServicesWithmName(number, name); WE NEED SOMETHING AT THIS STAGE TO UPDATE THE PROVIDERS AS WELL
				}
				if(address.equals("") == false)
					current.getData().setAddress(address);
				if(city.equals("") == false)
					current.getData().setCity(city);
				if(state.equals("") == false)
					current.getData().setState(state);
				if(zipCode != 0)
					current.getData().setZipCode(zipCode);
				if(status == 1 && check == false)
					current.getData().setStatus(true);
				if(status == 2 && check == true)
					current.getData().setStatus(false);
				
				return;
			}
			current = current.getNext();
		}
	}
	
	//adds a service to the member requested.
	public void addService(int pNumber, int mNumber, int serviceCode, int fee,
			String comments, String mName, String pName, String sName, Calendar dateReceived, Calendar dateProvided)
	{
		node current = first;
		
		if(current == null)
			return;
		
		if(current.getData().getNumber() == mNumber)
		{
			current.getData().addService(pNumber, mNumber, serviceCode, fee, comments, mName, pName, sName, dateReceived, dateProvided);
			return;
		}
		
		
		while(current.getData().getNumber() != mNumber)
		{
			current = current.getNext();
			if(current == null)
				break;
		}
		
		if(current == null)
			return;
		
		if(current.getData().getNumber() == mNumber)
		{
			current.getData().addService(pNumber, mNumber, serviceCode, fee, comments, mName, pName, sName, dateReceived, dateProvided);
		}
	}
	
	
	
	//write information to file
	public void write(String path)
	{
		node current = first;
		String total = "";
		
		if(current == null)
			return;
		
		while(current != null)
		{
			total += current.getData().write();
			current = current.getNext();
		}
		if(path.equals(""))
		{
			path = "MemberList.txt"; 
		}
		try {
			FileWriter writer = new FileWriter(path);
			writer.write(total);
			writer.close();
			}
			catch(IOException e) {
			//something went wrong with accessing the file;
			}
	}
	
	//generates the next available member number
	public int generateNumber()
	{
		largest ++;
		return largest;
	}

	//reads in members from a text file.
	public void read()
	{
		//provider variables
		String name = "";
		String address = "";
		String city = "";
		String state = "";
		int zipCode = 0;
		int number = 0;
		int statusInt;
		boolean status = false;
		//service variables
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
		int pHour = 0;
		int pMinute = 0;
		int pSecond = 0;
		
		try {
			BufferedReader reader = new BufferedReader(
			new FileReader("MemberList.txt"));
			String line = reader.readLine();
			while(line != null) 
			{
				if(line.equals("--MEMBER--"))
				{
					name = reader.readLine();
					number = Integer.parseInt(reader.readLine());
					address = reader.readLine();
					city = reader.readLine();
					state = reader.readLine();
					zipCode = Integer.parseInt(reader.readLine());
					statusInt = Integer.parseInt(reader.readLine());
					if (statusInt == 0)
						status = false;
					else if (statusInt == 1)
						status = true;
					this.add(name, address, city, state, zipCode, number, status);
				}
				else if(line.equals("--SERVICE--"))
				{
					dateProvided = Calendar.getInstance();
					dateReceived = Calendar.getInstance();
					pNumber = Integer.parseInt(reader.readLine());
					mNumber = Integer.parseInt(reader.readLine());
					serviceCode = Integer.parseInt(reader.readLine());
					fee = Integer.parseInt(reader.readLine());
					comments = reader.readLine();
					mName = reader.readLine();
					pName = reader.readLine();
					sName = reader.readLine();
					//make variables for date fields and read in and create the dates
					pMonth = Integer.parseInt(reader.readLine());
					pDay = Integer.parseInt(reader.readLine());
					pYear = Integer.parseInt(reader.readLine());
					pHour = Integer.parseInt(reader.readLine());
					pMinute = Integer.parseInt(reader.readLine());
					pSecond = Integer.parseInt(reader.readLine());
					dateProvided.set(pYear, pMonth, pDay, pHour, pMinute, pSecond);
					
					rMonth = Integer.parseInt(reader.readLine());
					rDay = Integer.parseInt(reader.readLine());
					rYear = Integer.parseInt(reader.readLine());
					rHour = Integer.parseInt(reader.readLine());
					rMinute = Integer.parseInt(reader.readLine());
					rSecond = Integer.parseInt(reader.readLine());
					dateReceived.set(rYear, rMonth, rDay, rHour, rMinute, rSecond);
					this.addService(pNumber, mNumber, serviceCode, fee, comments, mName, pName, sName, dateReceived, dateProvided);
				}
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
		
	}
	
	//used to verify a member's status. If it returns 0, then the member is there, but their account is frozen. return of 1 is 
	//they are a current active member, and 3 means the member was not found.
	public int verifyMember(int number) {
		node current = first;
		boolean mStatus;
		while (current != null) {
			if(current.getData().getNumber() == number) {
				mStatus = current.getData().getStatus();
				if (mStatus == false)
					return 0;
				else if (mStatus == true)
					return 1;
			}
			current = current.getNext();
		}
		return 3;
	}
	
	
	//node class used to construct the member list
	private class node
	{
		node next;
		member data;
	

		// Node constructor
		public node(String name, String address, String city, String state, int zipCode, int number, boolean status) {
			next = null;
			data = new member(name, address, city, state, zipCode, number, status);
		}
	
		//getters and setters
		public member getData() {
			return data;
		}
	
		public node getNext() {
			return next;
		}
	
		public void setNext(node next) {
			this.next = next;
		}
	}	
	
	/*--------------------------------------------------TESTS------------------------------------------------------------*/
	public static void testAdd(memberList m)
	{
		String name = "name";
		String address = "address";
		String city = "city";
		String state = "st";
		int zipCode = 44444;
		
		m.add(name, address, city, state, zipCode, 111111112, true);
		m.add(name, address, city, state, zipCode, 111111110, true);
		m.add(name, address, city, state, zipCode, 111111114, true);
		m.add(name, address, city, state, zipCode, 111111113, true);
		m.add(name, address, city, state, zipCode, 111111111, true);
		
		//attempt to add members with already used member numbers
		m.add(name, address, city, state, zipCode, 111111113, true);
		m.add(name, address, city, state, zipCode, 111111111, true);
		
		m.write("memberListTestAdd.txt");
		
		memberList.testUpdate(m);
		memberList.testDelete(m);
	}
	
	public static void testDelete(memberList m)
	{
		//attempting to delete a node that does not exist
				m.delete(111111115);
				m.delete(111111101);
				
				String name = "name";
				String address = "address";
				String city = "city";
				String state = "st";
				int zipCode = 44444;
				
				m.add(name, address, city, state, zipCode, 111111116, true);
				m.add(name, address, city, state, zipCode, 111111115, true);
				
				//delete from middle of list
				m.delete(111111113);
				//delete second thing in list
				m.delete(111111111);
				//delete first thing in list
				m.delete(111111110);
				//delete second to last thing in list
				m.delete(111111115);
				//delete last thing in list
				m.delete(111111116);
				
				m.write("memberListTestDelete.txt");
	}
	
	public static void testUpdate(memberList m)
	{
		String name = "name2";
		String address = "address2";
		String city = "city2";
		String state = "so";
		int zipCode = 22222;
		
		//attempting to update providers that do not exist
		m.update(name, address, city, state, zipCode, 111111101, 1);
		m.update(name, address, city, state, zipCode, 111111115, 1);
		
		//attempting to update second to last in list
		m.update(name, address, city, state, zipCode, 111111113, 1);
		//attempting to update first in list
		m.update(name, address, city, state, zipCode, 111111110, 1);
		//attempting to update second in list
		m.update(name, address, city, state, zipCode, 111111111, 1);
		//attempting to update last in list
		m.update(name, address, city, state, zipCode, 111111114, 1);
		//attempting to update middle of list
		m.update(name, address, city, state, zipCode, 111111112, 1);
		
		m.write("memberListTestUpdate.txt");
	}
	
	
	public static void test()
	{
		memberList m = new memberList();
		memberList.testAdd(m);
	}
}