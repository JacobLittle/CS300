import java.util.Calendar;

public class member {
	
	private
		serviceList sList;
		String name;
		String address;
		String city;
		String state;
		int zipCode;
		int number;
		int statusInt;
		boolean status;
		
		//constructor for member class
		public member(String name, String address, String city, String state, int zipCode, int number, boolean status)
		{
			sList = new serviceList();
			this.name = name;
			this.address = address;
			this.city = city;
			this.state = state;
			this.zipCode = zipCode;
			this.number = number;
			this.status = status;
			if (this.status == true)
				this.statusInt = 1;
			else if (this.status == false)
				this.statusInt = 0;
		}
		
		//Returns true if this member has services within the last week, else returns false
		public boolean serviceThisWeek() {
			if(sList.serviceThisWeek() == true)
				return true;
			else
				return false;
		}
		
		//creates one large formatted string and returns it to the calling routine.
		public String writeMemberReport()
		{
			String total = "";
			
			total += name + System.lineSeparator();
			total += number + System.lineSeparator();
			total += address + System.lineSeparator();
			total += city + System.lineSeparator();
			total += state + System.lineSeparator();
			total += zipCode + System.lineSeparator();
			total += sList.writeMemberReportInfo();
			
			return total;
		}
		
		//creates a large formatted string and returns it to the calling routine (uses the sList write function)
		public String write()
		{
			String total = "";
			total += "--MEMBER--" + System.lineSeparator();
			total += name + System.lineSeparator();
			total += number + System.lineSeparator();
			total += address + System.lineSeparator();
			total += city + System.lineSeparator();
			total += state + System.lineSeparator();
			total += zipCode + System.lineSeparator();
			total += statusInt + System.lineSeparator();
			total += sList.write();
			
			return total;
		}
		
		//Service Add function. Adds a service to the service list with passed in arguments
		public void addService(int pNumber, int mNumber, int serviceCode, int fee, String comments,
				String mName, String pName, String sName, Calendar dateReceived, Calendar dateProvided)
		{
			sList.add(pNumber, mNumber, serviceCode, fee, comments, mName, pName, sName, dateReceived, dateProvided);
		}
		
		//Getters and Setters for all data members ----------------------------------------------------------------------------
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
		
		public boolean getStatus() {
			return this.status;
		}
		
		public void setStatus(boolean status) {
			this.status = status;
		}
		//---------------------------------------------------------------------------------------------------------------------
}
