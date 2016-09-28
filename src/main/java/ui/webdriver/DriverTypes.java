package ui.webdriver;

public enum DriverTypes {

	FF("firefox"), IE("internet explorer");
	
	private String driverName;
	
	public String getDriverName(){
		return this.driverName;
	}
	
	private DriverTypes(String driverName){
		this.driverName = driverName;
	}
}
