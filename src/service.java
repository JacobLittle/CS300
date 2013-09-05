import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;

public class service 
{
	private
		int pNumber;
		int mNumber;
		int serviceCode;
		String comments;
		String mName;
		String pName;
		String sName;
		int fee;
		Calendar dateReceived; //Includes date and time
		Calendar dateProvided; //includes date
		
		//This constructor is given all the fields of the service to create the service.
		public service(int pNumber, int mNumber, int serviceCode, int fee,
				String comments, String mName, String pName, String sName, Calendar dateReceived, Calendar dateProvided) 
		{
			this.pNumber = pNumber;
			this.mNumber = mNumber;
			this.serviceCode = serviceCode;
			this.comments = comments;
			this.mName = mName;
			this.pName = pName;
			this.sName = sName;
			this.fee = fee;
			this.dateReceived = dateReceived;
			this.dateProvided = dateProvided;
		}
		
		//This will write the service information to the file that is given.
		//pNumber, mNumber, serviceCode, fee, comments, mName, pName, sName, dateReceived, dateProvided)
		public String write()
		{
			String total = "--SERVICE--" + System.lineSeparator();
			total += pNumber + System.lineSeparator();
			total += mNumber + System.lineSeparator(); //write mNumber
			total += serviceCode + System.lineSeparator(); //write serviceCode
			total += fee + System.lineSeparator(); //write fee
			total += comments + System.lineSeparator(); //write comments
			total += mName + System.lineSeparator(); //write mName
			total += pName + System.lineSeparator(); //write pName
			total += sName + System.lineSeparator(); //write sName
			total += dateProvided.get(Calendar.MONTH) + System.lineSeparator() + dateProvided.get(Calendar.DAY_OF_MONTH) + System.lineSeparator() + dateProvided.get(Calendar.YEAR) + System.lineSeparator()+ dateProvided.get(Calendar.HOUR) + System.lineSeparator() + dateProvided.get(Calendar.MINUTE) + System.lineSeparator() + dateProvided.get(Calendar.SECOND) + System.lineSeparator(); //write dateProvided MM-DD-YYYY
			total += dateReceived.get(Calendar.MONTH) + System.lineSeparator() + dateReceived.get(Calendar.DAY_OF_MONTH) + System.lineSeparator() + dateReceived.get(Calendar.YEAR) + System.lineSeparator() + dateReceived.get(Calendar.HOUR) + System.lineSeparator() + dateReceived.get(Calendar.MINUTE) + System.lineSeparator() + dateReceived.get(Calendar.SECOND) + System.lineSeparator(); //write dateReceived MM-DD-YYYY HH:MM:SS
			
			
			
			return total;
		}
		
		/*
		public void write(String path)
		{
			try {
				FileWriter writer = new FileWriter(path);
				writer.write(pNumber + System.lineSeparator());
				writer.write(mNumber + System.lineSeparator()); //write mNumber
				writer.write(serviceCode + System.lineSeparator()); //write serviceCode
				writer.write(fee + System.lineSeparator()); //write fee
				writer.write(comments + System.lineSeparator()); //write comments
				writer.write(mName + System.lineSeparator()); //write mName
				writer.write(sName + System.lineSeparator()); //write sName
				writer.write(dateProvided.get(Calendar.MONTH) + System.lineSeparator() + dateProvided.get(Calendar.DAY_OF_MONTH) + System.lineSeparator() + dateProvided.get(Calendar.YEAR) + System.lineSeparator()); //write dateProvided MM-DD-YYYY
				writer.write(dateReceived.get(Calendar.MONTH) + System.lineSeparator() + dateReceived.get(Calendar.DAY_OF_MONTH) + System.lineSeparator() + dateReceived.get(Calendar.YEAR) + System.lineSeparator() + dateReceived.get(Calendar.HOUR) + System.lineSeparator() + dateReceived.get(Calendar.MINUTE) + System.lineSeparator() + dateReceived.get(Calendar.SECOND) + System.lineSeparator()); //write dateReceived MM-DD-YYYY HH:MM:SS
				
				
				writer.close();
				}
				catch(IOException e) {
				//something went wrong with accessing the file;
				}
		}
		*/
		
		//writes the necessary info for a member report for this service to the file
		//set(int year, int month, int date, int hourOfDay, int minute, int second) 
		
		public String writeMemberReportInfo()
		{
			String total = "";
			total += (dateProvided.get(Calendar.MONTH)+1) + "-" + dateProvided.get(Calendar.DAY_OF_MONTH) + "-" + dateProvided.get(Calendar.YEAR) + System.lineSeparator(); //write dateProvided MM-DD-YYYY
		//	total += dateReceived.get(Calendar.MONTH) + "-" + dateReceived.get(Calendar.DAY_OF_MONTH) + "-" + dateReceived.get(Calendar.YEAR) + " " + dateReceived.get(Calendar.HOUR) + ":" + dateReceived.get(Calendar.MINUTE) + ":" + dateReceived.get(Calendar.SECOND) + System.lineSeparator(); //write dateReceived MM-DD-YYYY HH:MM:SS
		//	total += mName + System.lineSeparator(); //write mName
		//	total += mNumber + System.lineSeparator(); //write mNumber
		//	total += serviceCode + System.lineSeparator(); //write serviceCode
		//	total += fee + System.lineSeparator();
			total += pName + System.lineSeparator();
			total += sName + System.lineSeparator();
			
			return total;
		}
		/*
		public void writeMemberReportInfo(String path)
		{
			try {
				FileWriter writer = new FileWriter(path);
				writer.write(dateProvided.get(Calendar.MONTH) + "-" + dateProvided.get(Calendar.DAY_OF_MONTH) + "-" + dateProvided.get(Calendar.YEAR) + System.lineSeparator()); //write dateProvided MM-DD-YYYY
				writer.write(pName + System.lineSeparator()); //write pName
				writer.write(sName + System.lineSeparator()); //write sName
				writer.close();
				}
				catch(IOException e) {
				//something went wrong with accessing the file;
				}
		}
		*/
		
		
		public String writeProviderReportInfo()
		{
			String total = "";
			
			total += (dateProvided.get(Calendar.MONTH)+1) + "-" + dateProvided.get(Calendar.DAY_OF_MONTH) + "-" + dateProvided.get(Calendar.YEAR) + System.lineSeparator(); //write dateProvided MM-DD-YYYY
			total += (dateReceived.get(Calendar.MONTH)+1) + "-" + dateReceived.get(Calendar.DAY_OF_MONTH) + "-" + dateReceived.get(Calendar.YEAR) + " " + dateReceived.get(Calendar.HOUR) + ":" + dateReceived.get(Calendar.MINUTE) + ":" + dateReceived.get(Calendar.SECOND) + System.lineSeparator(); //write dateReceived MM-DD-YYYY HH:MM:SS
			total += mName + System.lineSeparator();//write mName
			total += mNumber + System.lineSeparator(); //write mNumber
			total += serviceCode + System.lineSeparator(); //write serviceCode
			total += fee/100 + ".";
			if(fee % 100 < 10)
			{
				total += "0" + fee % 100 + System.lineSeparator();
			}
			else
			{
				total += fee % 100 + System.lineSeparator();
			}
			
			return total;
		}
		
		/*
		public void writeProviderReportInfo(String path)
		{
			try {
				FileWriter writer = new FileWriter(path);
				writer.write(dateProvided.get(Calendar.MONTH) + "-" + dateProvided.get(Calendar.DAY_OF_MONTH) + "-" + dateProvided.get(Calendar.YEAR) + System.lineSeparator()); //write dateProvided MM-DD-YYYY
				writer.write(dateReceived.get(Calendar.MONTH) + "-" + dateReceived.get(Calendar.DAY_OF_MONTH) + "-" + dateReceived.get(Calendar.YEAR) + " " + dateReceived.get(Calendar.HOUR) + ":" + dateReceived.get(Calendar.MINUTE) + ":" + dateReceived.get(Calendar.SECOND) + System.lineSeparator()); //write dateReceived MM-DD-YYYY HH:MM:SS
				writer.write(mName + System.lineSeparator()); //write mName
				writer.write(mNumber + System.lineSeparator()); //write mNumber
				writer.write(serviceCode + System.lineSeparator()); //write serviceCode
				writer.write(fee + System.lineSeparator());
				writer.close();
				}
				catch(IOException e) {
				//something went wrong with accessing the file;
				}
			
		}
		*/
		
		
		//Checks whether the dateProvided is within the last 7 days
		public boolean thisWeek()
		{
			Calendar newDate = Calendar.getInstance(); //creates a new date with the current time
			newDate.add(Calendar.DATE, -7); //Subtracts 7 days from the newDate
			//newDate.setTimeInMillis(0);
			
			if(dateProvided.after(newDate) == true) //date provided is within the last week
			{
				return true;
			}
			
			return false;
		}

		
		public Calendar getDateReceived() 
		{
			return dateReceived;
		}

		public void setDateReceived(Calendar dateReceived) 
		{
			this.dateReceived = dateReceived;
		}

		public Calendar getDateProvided() {
			return dateProvided;
		}

		public void setDateProvided(Calendar dateProvided) {
			this.dateProvided = dateProvided;
		}
		
		public int getmNumber() 
		{
			return mNumber;
		}
		
		public void setmNumber(int mNumber) 
		{
			this.mNumber = mNumber;
		}
		
		public int getServiceCode() 
		{
			return serviceCode;
		}
		
		public void setServiceCode(int serviceCode) 
		{
			this.serviceCode = serviceCode;
		}
		
		public String getComments() 
		{
			return comments;
		}

		public void setComments(String comments) 
		{
			this.comments = comments;
		}

		public String getmName()
		{
			return mName;
		}

		public void setmName(String mName) 
		{
			this.mName = mName;
		}

		public String getpName() 
		{
			return pName;
		}

		public void setpName(String pName) 
		{
			this.pName = pName;
		}

		public int getFee() 
		{
			return fee;
		}
		
		public void setFee(int fee) 
		{
			this.fee = fee;
		}
		
		public int getpNumber() 
		{
			return pNumber;
		}
		
		public void setpNumber(int pNumber) 
		{
			this.pNumber = pNumber;
		}
		
		public String getsName()
		{
			return sName;
		}

		public void setsName(String sName)
		{
			this.sName = sName;
		}
		
		
		//this is used to test the functionality of the class
		public static void test()
		{
			// Testing the Class
			//create service using fields int pNumber, int mNumber, int serviceCode, int fee, String comments, String mName, String pName, String sName, Calendar dateReceived, Calendar dateProvided
			Calendar dateReceived = Calendar.getInstance();
			//dateReceived.set(2010, 01, 14, 12, 30, 10);
			Calendar dateProvided = Calendar.getInstance();
			dateProvided.set(2012, 9, 23);
			service d = new service(123456,234567,333, 999, "This service was incredibly awesome", "Wes Ris", "PSU", "Heaps of Chocolate", dateReceived, dateProvided); 
						
			//testing the write function, as well as the testService function
			String path = "testService.txt";
			if(d.thisWeek())
			{
				try {
					FileWriter writer = new FileWriter(path);
					writer.write(d.write());
					writer.close();
					}
					catch(IOException e) {
					//something went wrong with accessing the file;
					}
			}
		}
		

}
