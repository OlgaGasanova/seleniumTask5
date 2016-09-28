package ui.webdriver;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import ui.exceptions.UnknownDriverTypeException;

public class Driver {

	private static final String DEFAULT_WEB_DRIVER = "DEFAULT_WEB_DRIVER";
	private static DriverTypes defaultDriverType = DriverTypes.FF;
	private static HashMap<String, WebDriver> instances = new HashMap<String, WebDriver>();
	
	public static WebDriver getWebDriverInstance(String name, DriverTypes driverType){
		WebDriver driver;
		
		if (!instances.containsKey(name)){
			switch (driverType){
			case FF:
				driver = new FirefoxDriver();
				break;
			case IE:
				driver = new InternetExplorerDriver();
				break;
			default:
				throw new UnknownDriverTypeException("Unknown Driver Type " + driverType.getDriverName());
			}
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			instances.put(name,  driver);
		}
		else {
			driver = instances.get(name);
		}
		return driver;
	}
	
	public static WebDriver getWebDriverInstance(String name){
		return getWebDriverInstance(name, defaultDriverType);
	}
	
	public static WebDriver getWebDriverInstance(){
		return getWebDriverInstance(DEFAULT_WEB_DRIVER, defaultDriverType);
	}
}
