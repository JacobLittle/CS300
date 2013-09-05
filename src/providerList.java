import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;




public class providerList
{
	private
		node first;
		int largest;
		
	public providerList()
	{
		largest = 111111110;
		first = null;
	}
	
	public String findName(int number)
	{
		node current = first;
		
		if(current == null)
			return "";
		//search for the correct node
		while(current.getData().getNumber() != number && current.getNext() != null)
		{
			current = current.getNext();
		}
		//if it is the correct number
		if(current.getData().getNumber() == number)
		{
			return current.getData().getName();
		}
		
		return "";
	}
	
	public provider getProvider(/*String name, String address, String city, String state, int zipCode,*/ int number)
	{
		
		node current = first;
		
		while(current != null)
		{
			if(current.getData().getNumber() == number)
			{
				/*name = current.getData().getName();
				address = current.getData().getAddress();
				city = current.getData().getCity();
				state = current.getData().getState();
				zipCode = current.getData().getZipCode();
				number = current.getData().getNumber();*/
				
				return current.getData();
			}
			current = current.getNext();
		}
		
		return null;
		
		
	}
	
	public void read()
	{
		//provider variables
		String name = "";
		String address = "";
		String city = "";
		String state = "";
		int zipCode = 0;
		int number = 0;
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
			new FileReader("ProviderList.txt"));
			String line = reader.readLine();
			while(line != null) 
			{
				if(line.equals("--PROVIDER--"))
				{
					name = reader.readLine();
					number = Integer.parseInt(reader.readLine());
					address = reader.readLine();
					city = reader.readLine();
					state = reader.readLine();
					zipCode = Integer.parseInt(reader.readLine());
					this.add(name, address, city, state, zipCode, number);
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
			path = "ProviderList.txt"; //file name is EFT<current date MM-DD-YYYY>.txt
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
	
	public void writeEFT()
	{
		node current = first;
		String total = "";
		
		if(current == null)
			return;
		
		while(current != null)
		{
			if(current.getData().serviceThisWeek())
			{
				total += current.getData().writeEFT();
			}
			current = current.getNext();
		}
		
		Calendar date = Calendar.getInstance();
		
		String path = "EFT" + (date.get(Calendar.MONTH)+1) + date.get(Calendar.DAY_OF_MONTH) 
				+ date.get(Calendar.YEAR) + ".txt"; //file name is EFT<current date MM-DD-YYYY>.txt
		try {
			FileWriter writer = new FileWriter(path);
			writer.write(total);
			writer.close();
			}
			catch(IOException e) {
			//something went wrong with accessing the file;
			}
		
		
	}
	
	public void writeProviderReport()
	{
		node current = first;
		String toWrite = "";
		String path = "";
		Calendar date = Calendar.getInstance();
		
		if(current == null)
			return;
		
		while(current != null)
		{
			if(current.getData().serviceThisWeek() == true)
			{
				path = current.getData().getName() + (date.get(Calendar.MONTH)+1) + date.get(Calendar.DAY_OF_MONTH) 
						+ date.get(Calendar.YEAR) + ".txt"; //file name is name<current date MM-DD-YYYY>.txt
				
				toWrite = current.getData().writeProviderReport();
				try {
					FileWriter writer = new FileWriter(path);
					writer.write(toWrite);
					writer.write(current.getData().totalServices() + System.lineSeparator());
					writer.write((current.getData().totalFees()/100) + ".");
					if(current.getData().totalFees() % 100 < 10)
					{
							writer.write("0" + (current.getData().totalFees() % 100) + System.lineSeparator());
					}
					else
					{
							writer.write((current.getData().totalFees() % 100) + System.lineSeparator());
					}
					writer.close();
					}
					catch(IOException e) {
					//something went wrong with accessing the file;
					}
			}
			
			current = current.getNext();
		}
	}
	
	public void writeSummaryReport()
	{
		node current = first;
		String total = "";
		int totalProvidersProvided = 0;
		int totalConsultations = 0;
		int totalFees = 0;
		
		if(current == null)
			return ;
		
		while(current != null)
		{
			if(current.getData().serviceThisWeek() == true)
			{
				total += current.getData().writeSummaryReport();
				totalProvidersProvided++;
				totalConsultations += current.getData().totalServices();
				totalFees += current.getData().totalFees();
			}
			
			current = current.getNext();
		}
		
		total += totalProvidersProvided + System.lineSeparator();
		total += totalConsultations + System.lineSeparator();
		total += totalFees/100 + ".";
		if(totalFees % 100 < 10)
		{
			total += "0" + totalFees % 100 + System.lineSeparator();
		}
		else
		{
			total += totalFees % 100 + System.lineSeparator();
		}
		
		Calendar date = Calendar.getInstance();
		
		String path = "SummaryReport" + (date.get(Calendar.MONTH)+1) + date.get(Calendar.DAY_OF_MONTH) 
				+ date.get(Calendar.YEAR) + ".txt"; //file name is SummaryReport<current date MM-DD-YYYY>.txt
		try {
			FileWriter writer = new FileWriter(path);
			writer.write(total);
			writer.close();
			}
			catch(IOException e) {
			//something went wrong with accessing the file;
			}
	}
	
	
	public void add(String name, String address, String city, String state, int zipCode, int number)
	{
		node newNode = new node(name, address, city, state, zipCode, number);
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
	
	public void updateServicesWithmName(int number, String mName)
	{
		node current = first;
		
		while(current != null)
		{
			current.getData().getsList().updateMemberName(number, mName);
			current = current.getNext();
		}
	}
	
	//this function updates all the fields in the parameters if the strings are not "" and the zipCode is not 0
	public void update(String name, String address, String city, String state, int zipCode, int number)
	{
		node current = first;
		
		while(current != null)
		{
			if(current.getData().getNumber() == number)
			{
				if(name.equals("") == false)
				{
					current.getData().setName(name);
					current.getData().getsList().updateProviderName(number, name);
					//updateServicesWithpName(number, name);
				}
				if(address.equals("") == false)
					current.getData().setAddress(address);
				if(city.equals("") == false)
					current.getData().setCity(city);
				if(state.equals("") == false)
					current.getData().setState(state);
				if(zipCode != 0)
					current.getData().setZipCode(zipCode);
				
				return;
			}
			
			current = current.getNext();
		}
	}
	
	public void addService(int pNumber, int mNumber, int serviceCode, int fee,
			String comments, String mName, String pName, String sName, Calendar dateReceived, Calendar dateProvided)
	{
		node current = first;
		
		if(current == null)
			return;
		
		if(current.getData().getNumber() == pNumber)
		{
			current.getData().addService(pNumber, mNumber, serviceCode, fee, comments, mName, pName, sName, dateReceived, dateProvided);
			return;
		}
		
		
		while(current.getData().getNumber() != pNumber)
		{
			current = current.getNext();
			if(current == null)
				break;
		}
		
		if(current == null)
			return;
		
		if(current.getData().getNumber() == pNumber)
		{
			current.getData().addService(pNumber, mNumber, serviceCode, fee, comments, mName, pName, sName, dateReceived, dateProvided);
		}
	}
	
	public int generateNumber()
	{
		largest ++;
		return largest;
	}

	
	private class node
	{
		node next;
		provider data;
		

		// Node constructor
		public node(String name, String address, String city, String state, int zipCode, int number)
		{
			next = null;
			data = new provider(name, address, city, state, zipCode, number);
		}
		
		// these methods should be self-explanatory
		public provider getData()
		{
			return data;
		}
		
		/*
		public void setData(service data)
		{
			this.data = data;
		}
		*/
		
		public node getNext()
		{
			return next;
		}
		
		public void setNext(node next)
		{
			this.next = next;
		}
	}
	
	public static void testAdd(providerList p)
	{
		
		String name = "name";
		String address = "address";
		String city = "city";
		String state = "st";
		int zipCode = 44444;
		
		p.add(name, address, city, state, zipCode, 111111112);
		p.add(name, address, city, state, zipCode, 111111110);
		p.add(name, address, city, state, zipCode, 111111114);
		p.add(name, address, city, state, zipCode, 111111113);
		p.add(name, address, city, state, zipCode, 111111111);
		
		//attempt to add providers with already used provider numbers
		p.add(name, address, city, state, zipCode, 111111111);
		p.add(name, address, city, state, zipCode, 111111110);
		
		p.write("providerListTestAdd.txt");
		
		providerList.testUpdate(p);
		providerList.testDelete(p);
	
	}
	
	public static void testUpdate(providerList p)
	{
		String name = "name2";
		String address = "address2";
		String city = "city2";
		String state = "so";
		int zipCode = 22222;
		
		//attempting to update providers that do not exist
		p.update(name, address, city, state, zipCode, 111111101);
		p.update(name, address, city, state, zipCode, 111111115);
		
		//attempting to update second to last in list
		p.update(name, address, city, state, zipCode, 111111113);
		//attempting to update first in list
		p.update(name, address, city, state, zipCode, 111111110);
		//attempting to update second in list
		p.update(name, address, city, state, zipCode, 111111111);
		//attempting to update last in list
		p.update(name, address, city, state, zipCode, 111111114);
		//attempting to update middle of list
		p.update(name, address, city, state, zipCode, 111111112);
		
		p.write("providerListTestUpdate.txt");
	}
	
	
	public static void testDelete(providerList p)
	{
		//attempting to delete a node that does not exist
		p.delete(111111115);
		p.delete(111111101);
		
		String name = "name";
		String address = "address";
		String city = "city";
		String state = "st";
		int zipCode = 44444;
		
		p.add(name, address, city, state, zipCode, 111111116);
		p.add(name, address, city, state, zipCode, 111111115);
		
		//delete from middle of list
		p.delete(111111113);
		//delete second thing in list
		p.delete(111111111);
		//delete first thing in list
		p.delete(111111110);
		//delete second to last thing in list
		p.delete(111111115);
		//delete last thing in list
		p.delete(111111116);
		
		p.write("providerListTestDelete.txt");
	}
	
	public static void test()
	{
		providerList p = new providerList();
		providerList.testAdd(p);
		
		/*Calendar dateReceived; 
		Calendar dateProvided;
		Calendar dateProvided2 = Calendar.getInstance();
		Calendar dateProvided3 = Calendar.getInstance();
		Calendar dateProvided4 = Calendar.getInstance();
		Calendar dateProvided5 = Calendar.getInstance();
		Calendar dateProvided6 = Calendar.getInstance();
		
		p.add("Provizzle", "I provide NW", "Portland", "OR", 97346, 123456);
		p.add("Provider2" , "I provide SW", "Portland", "OR", 97445, 0);
		
		// Testing the Class
		//create service using fields int pNumber, int mNumber, int serviceCode, int fee, String comments, String mName, String pName, String sName, Calendar dateReceived, Calendar dateProvided
		dateReceived = Calendar.getInstance();
		//dateReceived.set(2010, 01, 14, 12, 30, 10);
		
		dateProvided = Calendar.getInstance();
		dateProvided.set(2012, 10, 4 );
		p.addService(123456,234567,333, 1, "This service was incredibly awesome", 
				"Wes Ris", "PSU", "Heaps of Chocolate", dateReceived, dateProvided);
		
		dateProvided = Calendar.getInstance();
		dateProvided.set(2012, 10, 4 );
		p.addService(111111,234567,333, 1, "This service was incredibly awesome", 
				"Wes Ris", "PSU", "Heaps of Chocolate", dateReceived, dateProvided);
		
		dateProvided2.set(2012, 10, 4);
		p.addService(123456,234567,333, 1, "This service was incredibly awesome", 
				"Wes Ris", "PSU", "Heaps of Chocolate", dateReceived, dateProvided2);
		//p.addService(111111,234567,333, 999, "This service was incredibly awesome", 
			//	"Wes Ris", "PSU", "Heaps of Chocolate", dateReceived, dateProvided2);
		
		dateProvided2.set(2012, 10, 6);
		p.addService(111111,234567,333, 1, "This service was incredibly awesome", 
				"Wes Ris", "PSU", "Heaps of Chocolate", dateReceived, dateProvided2);
		
		dateProvided3.set(2012, 10, 5);
		p.addService(123456,234567,333, 1, "This service was incredibly awesome", 
				"Wes Ris", "PSU", "Heaps of Chocolate", dateReceived, dateProvided3);
		
		dateProvided4.set(2012, 10, 4);
		p.addService(123456,234567,333, 1, "This service was incredibly awesome", 
				"Wes Ris", "PSU", "Heaps of Chocolate", dateReceived, dateProvided4);
		
		dateProvided5.set(2012, 10, 6);
		p.addService(123456,234567,333, 1, "This service was incredibly awesome", 
				"Wes Ris", "PSU", "Heaps of Chocolate", dateReceived, dateProvided5);
		
		dateProvided6.set(2012, 10, 3);
		p.addService(123456,234567,333, 1, "This service was incredibly awesome", 
				"Wes Ris", "PSU", "Heaps of Chocolate", dateReceived, dateProvided6); 
		
		p.add("name", "address", "city", "state", 97324, 0);
		p.add("name2", "address", "city", "state", 97324, 0);
		p.add("name3", "address", "city", "state", 97324, 0);
		p.add("name4", "address", "city", "state", 97324, 0);
		p.add("name5", "address", "city", "state", 97324, 0);
		
		p.delete(123462);
		p.delete(123457);
		p.delete(123456);
		
		p.update("nameUPDATED", "", "city", "state", 97326, 123456);
		p.update("nameUPDATED", "address2", "", "state", 97325, 123457);
		p.update("nameUPDATED", "address", "city3", "state4", 0, 123461);
		p.update("nameUPDATED", "address", "city", "", 0, 123462);
		
		p.addService(123458,234567,333, 1, "This service was incredibly awesome", 
				"Wes Ris", "PSU", "Heaps of Chocolate", dateReceived, dateProvided4);
		
		p.addService(123459,234567,333, 1, "This service was incredibly awesome", 
				"Wes Ris", "PSU", "Heaps of Chocolate", dateReceived, dateProvided4);
		
		p.addService(123460,234567,333, 1, "This service was incredibly awesome", 
				"Wes Ris", "PSU", "Heaps of Chocolate", dateReceived, dateProvided4);
		
		p.addService(123461,234567,333, 1, "This service was incredibly awesome", 
				"Wes Ris", "PSU", "Heaps of Chocolate", dateReceived, dateProvided4);
		
		System.out.println(p.findName(123458));
		System.out.println(p.findName(123456));
		System.out.println(p.getProvider("", "", "", "", 0, 123458));
		System.out.println(p.getProvider("", "", "", "", 0, 123459));
		System.out.println(p.getProvider("", "", "", "", 0, 123456));
		System.out.println(p.getProvider("", "", "", "", 0, 123461));
		*/
		/*p.read();
		p.writeSummaryReport();
		p.writeEFT();
		p.writeProviderReport();
		p.write();*/
		
		
		
	}
}
