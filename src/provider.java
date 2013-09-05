import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;


public class provider 
{
	private
		serviceList sList;
		String name;
		String address;
		String city;
		String state;
		int zipCode;
		int number;
		
	
	public provider(String name, String address, String city, String state, int zipCode, int number)
	{
		sList = new serviceList();
		this.name = name;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
		this.number = number;
	}
	
	public int totalFees()
	{
		return sList.totalFees();
	}
	
	public int totalServices()
	{
		return sList.totalServicesThisWeek();
	}
	
	public String writeSummaryReport()
	{
		String total = "";
		
		total += name + System.lineSeparator();
		total += totalServices() + System.lineSeparator();
		total += totalFees()/100 + ".";
		
		if(totalFees() % 100 < 10)
		{
			total += "0" + (totalFees() % 100) + System.lineSeparator();
		}
		else
		{
			total += (totalFees() % 100) + System.lineSeparator();
		}
		
		return total;
	}
	
	public String writeEFT()
	{
		String total = "";
		
		total += name + System.lineSeparator();
		total += number + System.lineSeparator();
		total += (sList.totalFees()/100) + ".";
		if(sList.totalFees() % 100 < 10)
		{
			total += "0" + (sList.totalFees() % 100) + System.lineSeparator();
		}
		else
		{
			total += (sList.totalFees() % 100) + System.lineSeparator();
		}
		
		return total;
	}
	
	public String writeProviderReport()
	{
		String total = "";
		
		total += name + System.lineSeparator();
		total += number + System.lineSeparator();
		total += address + System.lineSeparator();
		total += city + System.lineSeparator();
		total += state + System.lineSeparator();
		total += zipCode + System.lineSeparator();
		total += sList.writeProviderReportInfo();
		
		return total;
	}
	
	public boolean serviceThisWeek()
	{
		return sList.serviceThisWeek();
	}
	
	public String write()
	{
		String total = "--PROVIDER--" + System.lineSeparator();
		
		total += name + System.lineSeparator();
		total += number + System.lineSeparator();
		total += address + System.lineSeparator();
		total += city + System.lineSeparator();
		total += state + System.lineSeparator();
		total += zipCode + System.lineSeparator();
		total += sList.write();
		
		return total;
	}
		
	public serviceList getsList() {
		return sList;
	}

	public void setsList(serviceList sList) {
		this.sList = sList;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getZipCode() {
		return zipCode;
	}

	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public void addService(int pNumber, int mNumber, int serviceCode, int fee,
			String comments, String mName, String pName, String sName, Calendar dateReceived, Calendar dateProvided)
	{
		sList.add(pNumber, mNumber, serviceCode, fee, comments, mName, pName, sName, dateReceived, dateProvided);
	}

	
	public static void test()
	{
		provider p = new provider("Wes Ris", "2945 CS300 WY NE", "Portland", "OR", 97335, 111111);
		Calendar dateReceived; 
		Calendar dateProvided;
		Calendar dateProvided2 = Calendar.getInstance();
		Calendar dateProvided3 = Calendar.getInstance();
		Calendar dateProvided4 = Calendar.getInstance();
		Calendar dateProvided5 = Calendar.getInstance();
		Calendar dateProvided6 = Calendar.getInstance();
		
		// Testing the Class
		//create service using fields int pNumber, int mNumber, int serviceCode, int fee, String comments, String mName, String pName, String sName, Calendar dateReceived, Calendar dateProvided
		dateReceived = Calendar.getInstance();
		//dateReceived.set(2010, 01, 14, 12, 30, 10);
		dateProvided = Calendar.getInstance();
		dateProvided.set(2012, 10, 1 );
		p.addService(123456,234567,333, 999, "This service was incredibly awesome", 
				"Wes Ris", "PSU", "Heaps of Chocolate", dateReceived, dateProvided);
		dateProvided2.set(2012, 10, 4);
		p.addService(123456,234567,333, 999, "This service was incredibly awesome", 
				"Wes Ris", "PSU", "Heaps of Chocolate", dateReceived, dateProvided2);
		
		dateProvided3.set(2012, 10, 5);
		p.addService(123456,234567,333, 999, "This service was incredibly awesome", 
				"Wes Ris", "PSU", "Heaps of Chocolate", dateReceived, dateProvided3);
		dateProvided4.set(2012, 10, 4);
		p.addService(123456,234567,333, 999, "This service was incredibly awesome", 
				"Wes Ris", "PSU", "Heaps of Chocolate", dateReceived, dateProvided4);
		dateProvided5.set(2012, 10, 2);
		p.addService(123456,234567,333, 999, "This service was incredibly awesome", 
				"Wes Ris", "PSU", "Heaps of Chocolate", dateReceived, dateProvided5);
		dateProvided6.set(2012, 10, 3);
		p.addService(123456,234567,333, 999, "This service was incredibly awesome", 
				"Wes Ris", "PSU", "Heaps of Chocolate", dateReceived, dateProvided6); 
		
		
		String path = "testProviderWrite.txt";
		//if()
		{
			//testing the write function
			try {
				FileWriter writer = new FileWriter(path);
				writer.write(p.write());
				writer.close();
				}
				catch(IOException e) {
				//something went wrong with accessing the file;
				}
			//testing the writeEFT function
			path = "testProviderWriteEFT.txt";
			try {
				FileWriter writer = new FileWriter(path);
				writer.write(p.writeEFT());
				writer.close();
				}
				catch(IOException e) {
				//something went wrong with accessing the file;
				}
			//testing the writeProviderReport function
			path = "testProviderWriteProviderReport.txt";
			try {
				FileWriter writer = new FileWriter(path);
				writer.write(p.writeProviderReport());
				writer.close();
				}
				catch(IOException e) {
				//something went wrong with accessing the file;
				}
			
			
		}
	}
}
