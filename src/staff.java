
public class staff 
{

	private
	String logInName;
	String password;
	boolean manager;
	
	public staff()
	{
		logInName = "";
		password = "";
		manager = false;
	}

	public String getLogInName() {
		return logInName;
	}

	public void setLogInName(String logInName) {
		this.logInName = logInName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isManager() {
		return manager;
	}

	public void setManager(boolean manager) {
		this.manager = manager;
	}
	
	public String write()
	{
		String total = "";
		
		total += logInName + System.lineSeparator();
		total += password + System.lineSeparator();
		if(manager == true)
			total += "true" + System.lineSeparator();
		else
			total += "false" + System.lineSeparator();
		
		return total;
	}

}
