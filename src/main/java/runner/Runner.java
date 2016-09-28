package runner;

import org.apache.log4j.xml.DOMConfigurator;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.testng.TestNG;
import org.testng.xml.Parser;
import org.testng.xml.XmlSuite;
import org.uncommons.reportng.HTMLReporter;

import config.Settings;
import ui.exceptions.TestNgRunException;
import utils.ScreenShotListener;

public class Runner {

	protected TestNG testng = new TestNG();
	private String testNgConfig;
	
	public static void main(String[] args){
		new Runner(args).run();
	}
	
	public Runner(String[] args){
		Settings settings = new Settings();
		CmdLineParser parser = new CmdLineParser(settings);
		try{
			parser.parseArgument(args);
			testNgConfig = settings.testNgPath;
		}
		catch (CmdLineException e){
			System.err.println("error: " + e.toString());
			parser.printUsage(System.out);
		}
	}
	
	public void run(){
		DOMConfigurator.configure("src/test/resources/log4j.xml");
		this.testng.addListener(new HTMLReporter());
		this.testng.addListener(new ScreenShotListener());
		try{
			XmlSuite xmlSuite = new Parser(testNgConfig).parseToList().get(0);
			this.testng.setCommandLineSuite(xmlSuite);			
			this.testng.run();
		}
		catch(Exception e){
			throw new TestNgRunException("Error running testNG suite " + e.getMessage());
		}
	}
}
