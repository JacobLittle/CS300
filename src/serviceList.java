import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;


public class serviceList 
{
	private
		node first;
		
	//default constructor sets first to null
	public serviceList()
	{
		first = null;
	}
	
	public void updateMemberName(int number, String name)
	{
		node current = first;
		
		while(current != null)
		{
			if(current.getData().getmNumber() == number)
				current.getData().setmName(name);
			
			current = current.getNext();
		}
	}
	
	public void updateProviderName(int number, String name)
	{
		node current = first;
		
		while(current != null)
		{
			if(current.getData().getpNumber() == number)
				current.getData().setpName(name);
			
			current = current.getNext();
		}
	}
	
	//this adds a new node created using the parameters. services are ordered by dateProvided with the most recent date at the beginning
	public void add(int pNumber, int mNumber, int serviceCode, int fee,
			String comments, String mName, String pName, String sName, Calendar dateReceived, Calendar dateProvided)
	{
		node current = first;
		node previous = current;
		//make a new node using parameters
		node newNode = new node(pNumber, mNumber, serviceCode, fee,
				comments, mName, pName, sName, dateReceived, dateProvided);
		
		//if there is no list
		if(first == null)
		{
			//make a new node using parameters
			first = newNode;
		//	current = newNode;
			newNode.setNext(null);
			return;
		}
		
		//if the first node's dateProvided is before the new Node
		if(newNode.getData().getDateProvided().after(first.getData().getDateProvided()) || newNode.getData().getDateProvided().equals(first.getData().getDateProvided()))
		{
			//make the new node's next the currently first node
			newNode.setNext(first);
			//make the new node the first node
			first = newNode;
			return;
		}
		
		//if the first node does have a next
		//if(current.getNext() != null)
		{
			//current = current.getNext();
			//while the current node's dateProvided is before the next node's date provided
			while(newNode.getData().getDateProvided().before(current.getData().getDateProvided()))
			{
				if(newNode.getData().getDateProvided().equals(current.getData().getDateProvided()))
				{
					previous.setNext(newNode);
					newNode.setNext(current);
					return;
				}
				/*
				//if the current node doesn't have a next
				if(current.getNext() == null)
					break;*/
				previous = current;
				current = current.getNext();
				if(current == null)
				{
					previous.setNext(newNode);
					newNode.setNext(null);
					return;
				}
				
			}
		}
		
		
		//put the new Node in between the previous node and the current node
		previous.setNext(newNode);
		newNode.setNext(current);
		return;
				
	}
	
	//this returns a string with the information to write to the default storage file
	public String write()
	{
		String total = "";
		node current = first;
		
		if(first == null)
		{
			return total;
		}
		
		total += current.getData().write();
		
		while(current != null)
		{
			
			current = current.getNext();
			if(current == null)
				break;
			total += current.getData().write();
		}
		
		return total;
	}
	
	//returns the total of all the fees for the services provied within the last week
	public int totalFees()
	{
		node current = first;
		int total = 0;
		
		//if there is no list
				if(current == null)
				{
					return 0;
				}
				//while there are more nodes
				while(current.getNext() != null)
				{
					//if the service was provided this week
					if(current.getData().thisWeek() == true)
					{
						total += current.getData().getFee();
						current = current.getNext();
					}
					else
					{
						return total;
					}
					
				}
				
				if(current.getData().thisWeek() == true)
				{
					total += current.getData().getFee();
					current = current.getNext();
				}
				
				return total;
		
	}
	
	
	//Returns true if the provider has had 1 service this week, aka the first service
	public boolean serviceThisWeek()
	{
		if(first == null)
			return false;
		
		return first.getData().thisWeek();
	}
	
	public String writeProviderReportInfo()
	{
		node current = first;
		String total = "";
		
		//if there is no list
		if(current == null)
		{
			return total;
		}
		//while there are more nodes
		while(current.getNext() != null)
		{
			//if the service was provided this week
			if(current.getData().thisWeek() == true)
			{
				total += current.getData().writeProviderReportInfo();
				current = current.getNext();
			}
			else
			{
				return total;
			}
			
		}
		
		if(current.getData().thisWeek() == true)
		{
			total += current.getData().writeProviderReportInfo();
			current = current.getNext();
		}
		
		return total;
		
	}
	
	public String writeMemberReportInfo()
	{
		node current = first;
		String total = "";
		
		//if there is no list
		if(current == null)
		{
			return total;
		}
		//while there are more nodes
		while(current.getNext() != null)
		{
			//if the service was provided this week
			if(current.getData().thisWeek() == true)
			{
				total += current.getData().writeMemberReportInfo();
				current = current.getNext();
			}
			else
			{
				return total;
			}
			
		}
		
		
		if(current.getData().thisWeek() == true)
		{
			total += current.getData().writeMemberReportInfo();
			current = current.getNext();
		}
		
		
		return total;
		
	}
	
	//this function returns the total number of services provided for the week
	public int totalServicesThisWeek()
	{
		node current = first;
		int total = 0;
		
		//if there is no list
		if(current == null)
		{
			return 0;
		}
		//while there are more nodes
		while(current.getNext() != null)
		{
			//if the service was provided this week
			if(current.getData().thisWeek() == true)
			{
				total++;
				current = current.getNext();
			}
			else
			{
				return total;
			}
			
		}
		
		if(current.getData().thisWeek() == true)
		{
			total++;
			current = current.getNext();
		}
		
		return total;
	}
	
	private class node
	{
		node next;
		service data;
		

		// Node constructor
		public node(int pNumber, int mNumber, int serviceCode, int fee,
				String comments, String mName, String pName, String sName, Calendar dateReceived, Calendar dateProvided)
		{
			next = null;
			data = new service(pNumber, mNumber, serviceCode, fee,
					comments, mName, pName, sName, dateReceived, dateProvided);
		}
		
		// these methods should be self-explanatory
		public service getData()
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
	
	public static void testAdd(serviceList s)
	{
		int pNumber = 111111;
		int mNumber = 111111;
		int serviceCode = 111111;
		String comments = "comments";
		String mName = "mName";
		String pName = "pName";
		String sName = "sName";
		int fee = 111;
		Calendar dateReceived = Calendar.getInstance();
		Calendar dateProvided = Calendar.getInstance();
		Calendar dateProvided2 = Calendar.getInstance();
		Calendar dateProvided3 = Calendar.getInstance();
		Calendar dateProvided4 = Calendar.getInstance();
		Calendar dateProvided5 = Calendar.getInstance();
		
		//no list
		//dateProvided.set(2012, 9, 22);
		dateProvided.add(Calendar.DATE, -7);
		s.add(pNumber, mNumber, serviceCode, fee, comments, mName, pName, sName, dateReceived, dateProvided);
		s.add(pNumber, mNumber, serviceCode, fee, comments, mName, pName, sName, dateReceived, dateProvided);
		//beginning
		//dateProvided2.set(2012, 9, 20);
		dateProvided2.add(Calendar.DATE, -9);
		s.add(pNumber, mNumber, serviceCode, fee, comments, mName, pName, sName, dateReceived, dateProvided2);
		s.add(pNumber, mNumber, serviceCode, fee, comments, mName, pName, sName, dateReceived, dateProvided2);
		//last
		//dateProvided3.set(2012, 9, 24);
		dateProvided3.add(Calendar.DATE, -5);
		s.add(pNumber, mNumber, serviceCode, fee, comments, mName, pName, sName, dateReceived, dateProvided3);
		s.add(pNumber, mNumber, serviceCode, fee, comments, mName, pName, sName, dateReceived, dateProvided3);
		//second to last
		//dateProvided4.set(2012, 9, 23);
		dateProvided4.add(Calendar.DATE, -6);
		s.add(pNumber, mNumber, serviceCode, fee, comments, mName, pName, sName, dateReceived, dateProvided4);
		//equal date
		s.add(pNumber, mNumber, serviceCode, fee, comments, mName, pName, sName, dateReceived, dateProvided4);
		//second
		//dateProvided5.set(2012, 9, 21);
		dateProvided5.add(Calendar.DATE, -8);
		s.add(pNumber, mNumber, serviceCode, fee, comments, mName, pName, sName, dateReceived, dateProvided5);
		//equal date
		s.add(pNumber, mNumber, serviceCode, fee, comments, mName, pName, sName, dateReceived, dateProvided5);
		
		String path = "serviceListTestAdd.txt";
		try {
			FileWriter writer = new FileWriter(path);
			//writing service information
			writer.write(s.write());
			//writing to test the totalFees() and totalServicesThisWeek() functions
			writer.write(s.totalFees() + System.lineSeparator() + s.totalServicesThisWeek() + System.lineSeparator());
			writer.close();
			}
			catch(IOException e) {
			//something went wrong with accessing the file;
			}
		
	}
	
	
	public static void test()
	{
		serviceList s = new serviceList();
		serviceList.testAdd(s);
		
		/*Calendar dateReceived; 
		Calendar dateProvided;
		Calendar dateProvided2 = Calendar.getInstance();
		Calendar dateProvided3 = Calendar.getInstance();
		Calendar dateProvided4 = Calendar.getInstance();
		Calendar dateProvided5 = Calendar.getInstance();
		Calendar dateProvided6 = Calendar.getInstance();
		
		serviceList sList = new serviceList();
		// Testing the Class
		//create service using fields int pNumber, int mNumber, int serviceCode, int fee, String comments, String mName, String pName, String sName, Calendar dateReceived, Calendar dateProvided
		dateReceived = Calendar.getInstance();
		//dateReceived.set(2010, 01, 14, 12, 30, 10);
		dateProvided = Calendar.getInstance();
		dateProvided.set(2012, 9, 27);
		sList.add(123456,234567,333, 999, "This service was incredibly awesome", 
				"Wes Ris", "PSU", "Heaps of Chocolate", dateReceived, dateProvided);
		dateProvided2.set(2012, 9, 28);
		sList.add(123456,234567,333, 999, "This service was incredibly awesome", 
				"Wes Ris", "PSU", "Heaps of Chocolate", dateReceived, dateProvided2);
		
		dateProvided3.set(2012, 9, 29);
		sList.add(123456,234567,333, 999, "This service was incredibly awesome", 
				"Wes Ris", "PSU", "Heaps of Chocolate", dateReceived, dateProvided3);
		dateProvided4.set(2012, 9, 28);
		sList.add(123456,234567,333, 999, "This service was incredibly awesome", 
				"Wes Ris", "PSU", "Heaps of Chocolate", dateReceived, dateProvided4);
		dateProvided5.set(2012, 9, 26);
		sList.add(123456,234567,333, 999, "This service was incredibly awesome", 
				"Wes Ris", "PSU", "Heaps of Chocolate", dateReceived, dateProvided5);
		dateProvided6.set(2012, 9, 27);
		sList.add(123456,234567,333, 999, "This service was incredibly awesome", 
				"Wes Ris", "PSU", "Heaps of Chocolate", dateReceived, dateProvided6);
		
		String path = "serviceListTest.txt";
		try {
			FileWriter writer = new FileWriter(path);
			writer.write(sList.write());
			writer.write(sList.totalFees() + System.lineSeparator() + sList.totalServicesThisWeek() + System.lineSeparator());
			writer.close();
			}
			catch(IOException e) {
			//something went wrong with accessing the file;
			}
		*/
	}
}
