import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class staffList 
{
	private
		int size;
		staff [] list;
		
	public staffList()
	{
		size = 0;
		read();
	}
	
	public boolean isManager(String logInName)
	{
		for(int i=0; i < size; i++)
		{
			if(logInName.equals(list[i].getLogInName()))
				return list[i].isManager();
		}
		
		return false;
	}
	
	public boolean verify(String logInName, String password)
	{
		for(int i=0; i < size; i++)
		{
			if(logInName.equals(list[i].getLogInName()))
				if(password.equals(list[i].getPassword()))
					return true;
		}
		
		return false;
		
	}
	
	public void read()
	{
			try {
				BufferedReader reader = new BufferedReader(
				new FileReader("StaffList.txt"));
				String line = reader.readLine();
					
				if(line != null)
				{
					size = Integer.parseInt(line);
					list = new staff[size];
					
					for(int i = 0; i < size; i++)
					{
						list[i] = new staff();
					}
						
					line = reader.readLine();
		
					for(int i = 0; i < size; i++)
					{
						list[i].setLogInName(line);
						line = reader.readLine();
						list[i].setPassword(line);
						line = reader.readLine();
						if(line.equals("false"))
						{
							list[i].setManager(false);
						}
						else
							list[i].setManager(true);
						
						line = reader.readLine();
						
					}		
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
	
	public void write()
	{
		String total = "" + size + System.lineSeparator();
		if(size == 0)
			return;
		
		for(int i = 0; i < size; i++)
		{
			total += list[i].write();
			
		}
		
		String path = "staffListWriteTest.txt";
		try {
			FileWriter writer = new FileWriter(path);
			writer.write(total);
			writer.close();
			}
			catch(IOException e) {
			//something went wrong with accessing the file;
			}
	}
	

	
	
	public static void test()
	{
		staffList d = new staffList();
		
		System.out.println(d.verify("Wes Ris", "passwordforwes"));
		System.out.println(d.verify("Wes Rise", "passwordforwes"));
		System.out.println(d.verify("Wes Ris", "passwordforjacob"));
		
		System.out.println(d.isManager("Wes Ris"));
		System.out.println(d.isManager("Wes Rise"));
		System.out.println(d.isManager("Jacob Little"));
		System.out.println(d.isManager("Random Dude"));
		System.out.println(d.isManager("Random Other Dude"));
		
		d.write();
	}
}
