import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class providerDirectory 
{
	private
		int size;
		info [] list;
		
	public providerDirectory()
	{
		size = 0;
		//size = initialSize();
		//list = new info[size];
		read();
		
	}
	
	/*private int initialSize()
	{
		int newSize = 0;
		
		try {
			BufferedReader reader = new BufferedReader(
			new FileReader("ProviderDirectory.txt"));
			String line = reader.readLine();
			
			if(line != null)
			{
				newSize = Integer.parseInt(line);
			}
			reader.close();
			}
			catch(FileNotFoundException e) {
			//the specified file could not be found
			}
			catch(IOException e) {
			//something went wrong with reading or closing
			}
		
		return newSize;
	}*/
	
	public int findFee(int serviceCode)
	{
		for(int i=0; i < size; i++)
		{
			if(serviceCode == list[i].getServiceCode())
				return list[i].getFee();
		}
		
		return 0; //if it is nowhere to be found return -1 
				   //signifying that it does not exist
	}
	
	
	public String findName(int serviceCode)
	{
		for(int i=0; i < size; i++)
		{
			if(serviceCode == list[i].getServiceCode())
				return list[i].getName();
		}
		
		return ""; //if it is nowhere to be found, return no name
	}
	
	
	public void write()
	{
		String total = "" + size + System.lineSeparator();
		if(size == 0)
			return;
		
		for(int i = 0; i < size; i++)
		{
			total += list[i].getName() + System.lineSeparator();
			total += list[i].getServiceCode() + System.lineSeparator();
			total += list[i].getFee()/100 + ".";
			if(list[i].getFee() % 100 < 10)
			{
				total += "0" + (list[i].getFee() % 100) + System.lineSeparator();
			}
			else
			{
				total += list[i].getFee() % 100 + System.lineSeparator();
			}
		}
		
		String path = "ProviderDirectory.txt";
		try {
			FileWriter writer = new FileWriter(path);
			writer.write(total);
			writer.close();
			}
			catch(IOException e) {
			//something went wrong with accessing the file;
			}
	}
	
	
	public void read()
	{	
		double temp = 0.0;
		
		//int counter = 0;
		try {
			BufferedReader reader = new BufferedReader(
			new FileReader("ProviderDirectory.txt"));
			String line = reader.readLine();
			
			if(line != null)
			{
				size = Integer.parseInt(line);
				list = new info[size];
				
				for(int i = 0; i < size; i++)
				{
					list[i] = new info();
				}
				
				line = reader.readLine();
				//while(line != null && counter < size)
				//{
				for(int i = 0; i < size; i++)
				{
					list[i].setName(line);
					line = reader.readLine();
					list[i].setServiceCode(Integer.parseInt(line));
					line = reader.readLine();
					temp = Double.parseDouble(line);
					//list[i].setFee(Integer.parseInt(line));
					list[i].setFee((int) (temp * 100));
					line = reader.readLine();
					//if(line == null)
						//break;
					//counter++;
				}
				//}
				
				
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
	
	private class info
	{
		private
			int serviceCode;
			int fee;
			String name;
		
		public info()
		{
			this.name = "";
			this.fee = 0;
			this.serviceCode = 0;
		}

		public int getServiceCode() {
			return serviceCode;
		}

		public void setServiceCode(int serviceCode) {
			this.serviceCode = serviceCode;
		}

		public int getFee() {
			return fee;
		}

		public void setFee(int fee) {
			this.fee = fee;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
		
		
	}
	
	public static void test()
	{
		providerDirectory d = new providerDirectory();
		
		//d.read();
		d.write();
		System.out.println(d.findName(123456));
		System.out.println(d.findFee(123456));
		
		System.out.println(d.findName(123452));
		System.out.println(d.findFee(123452));
		
	}


}
