package tests;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.uncommons.reportng.HTMLReporter;

import functions.DeleteLetters;
import functions.WriteLetter;
import ui.page.EnterInOurMail;
import ui.page.GarbagePage;
import ui.page.MailPage;
import ui.page.SentLettersPage;
import ui.page.WriteLetterPage;
import ui.webdriver.Driver;
import user.User;
import utils.ScreenShot;
import utils.ScreenShotListener;

//@Listeners({HTMLReporter.class, ScreenShotListener.class})
public class Tests {
	private static final String INCORRECT_EXIT = "Incorrect exit";
	private static final String THEME_TEXT_ON_SENT_LETTERS_PAGE_IS_INCORRECT = "themeText() on SentLettersPage is incorrect";
	private static final String WHOM_TEXT_ON_SENT_LETTERS_PAGE_IS_INCORRECT = "whomText() on SentLettersPage is incorrect";
	private static final String THEME_TEXT_ON_GARBAGE_PAGE_IS_INCORRECT = "themeText() on GarbagePage is incorrect";
	private static final String WHOM_TEXT_ON_GARBAGE_PAGE_IS_INCORRECT = "whomText() on GarbagePage is incorrect";
	private static final String INCORRECT_DELETE_FROM_SENT_LETTERS = "Incorrect delete from sent letters";
	private static final String INCORRECT_DELETE_FROM_GARBAGE = "Incorrect delete from garbage";
	private static final String INCORRECT_TITLE = "Incorrect title";
	private static final String MAIL_RU = "Mail.Ru";
	private static final String MY_PASSWORD = "20october1986";
	private static final String MY_LOGIN = "bolshova.olechka@mail.ru";
	private ResourceBundle rb;
	private String browserType = "chrome";
	WebDriver driver;
	private User person = new User(MY_LOGIN, MY_PASSWORD);
	private static final Logger LOG = Logger.getLogger(Tests.class);
	
	static {
		System.setProperty("org.uncommons.reportng.escape-output", "false");
		ScreenShot.deleteAll();
	}
	
	@DataProvider
	public Object[][] getWhom(){
		rb = ResourceBundle.getBundle("tests.adresses");
		return new Object[][]{
			new Object[] {rb.getString("whom1"), rb.getString("theme1"), rb.getString("text1")},
			new Object[] {rb.getString("whom2"), rb.getString("theme2"), rb.getString("text2")},
	    };
	}   
	
	private WebDriver initDriver() throws IOException {
		DesiredCapabilities capabilities = null; 
		switch (browserType) {
		case "chrome":
			System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
			capabilities = DesiredCapabilities.chrome();
			break;
		case "ie":
				System.setProperty("webdriver.ie.driver", "IEDriverServer.exe");
			capabilities = DesiredCapabilities.internetExplorer();
			capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			break;
		case "firefox":
		default:
			System.setProperty("webdriver.gecko.driver", "geckodriver.exe");
			capabilities = DesiredCapabilities.firefox();
			capabilities.setCapability(CapabilityType.TAKES_SCREENSHOT, false);
			break;
		}
		RemoteWebDriver driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
		return driver;
	}
	
	@Test(groups = "beforeWork")
	public void beforeWork() throws IOException{
		LOG.info("'beforeWork' start");
		driver = Driver.getWebDriverInstance();
		driver.get("http://mail.ru");
		Assert.assertTrue(driver.getTitle().contains(MAIL_RU), INCORRECT_TITLE);
		LOG.info("'beforeWork' finish");
	}
	
	@Test(groups = "performEnter", dependsOnGroups = "beforeWork")
	public void performEnter(){	
		LOG.info("'performEnter' start");
		MailPage page = new MailPage(driver);
		page.setSearchLoginFieldText(person.getLogin())
			.setSearchPasswordFieldText(person.getPassword())
			.clickEnterButton();
		//Assert.assertTrue(driver.findElement(By.xpath(".//i[text()='" + person.getLogin() + "']")).isDisplayed(),"assertEnter fail");
		new EnterInOurMail(driver).assertEnterInMail(person.getLogin());
		LOG.info("'performEnter' finish");
	}
	
	@Test(groups = "writeLetters", dependsOnGroups = "performEnter", dataProvider = "getWhom")
	public void writeAndSaveLetters(String whom, String theme, String text){
		LOG.info("'writeAndSaveLetters' start");
		WriteLetterPage pageW = new WriteLetterPage(driver);
		new WriteLetter(driver).performWriteLetter(whom, theme, text);
		pageW.garbageClick();
		GarbagePage page = new GarbagePage(driver);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();	
		}
		System.out.println("pageG whomText()=" + page.whomText());
		System.out.println("pageG themeText()=" + page.themeText());
		Assert.assertTrue(page.whomText().contains(whom), WHOM_TEXT_ON_GARBAGE_PAGE_IS_INCORRECT);
		Assert.assertTrue(page.themeText().contains(theme), THEME_TEXT_ON_GARBAGE_PAGE_IS_INCORRECT);
		LOG.info("'writeAndSaveLetters' finish");
		//ScreenShot.make(driver);
	}
	
	@Test(groups = "sendLetters", dependsOnGroups = "writeLetters", dataProvider = "getWhom")
	public void sendLetters(String whom, String theme, String text){
		LOG.info("'sendLetters' start");
		GarbagePage pageG = new GarbagePage(driver);
		pageG.garbageClick();
		pageG.whomClick();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();	
		}
		String whomText = pageG.whomText();
		String themeText = pageG.themeText();
		pageG.sendLetterClick();	
		SentLettersPage pageS = new SentLettersPage(driver);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();	
		}
		pageS.sentLettersClick();
		System.out.println("pageS whomText()=" + pageS.whomText());
		System.out.println("pageS themeText()=" + pageS.themeText());
		Assert.assertTrue(pageS.whomText().contains(whomText), WHOM_TEXT_ON_SENT_LETTERS_PAGE_IS_INCORRECT);
		Assert.assertTrue(pageS.themeText().contains(themeText), THEME_TEXT_ON_SENT_LETTERS_PAGE_IS_INCORRECT);
		LOG.info("'sendLetters' finish");
	}

	@Test(groups = "fail", dependsOnGroups = "sendLetters")
	public void failTest(){
		Assert.assertTrue(false);
	}
	
	@Test(groups = "delete", dependsOnGroups = "sendLetters")
	public void deleteLetters(){
		LOG.info("'deleteLetters' start");
		DeleteLetters delLet = new DeleteLetters(driver);
		Assert.assertTrue(delLet.deleteLettersGarbage(), INCORRECT_DELETE_FROM_GARBAGE);
		Assert.assertTrue(delLet.deleteLettersSent(), INCORRECT_DELETE_FROM_SENT_LETTERS);
		LOG.info("'deleteLetters' finish");
	}	
	
	@Test(groups = "exitMail", dependsOnGroups = "delete")
	public void exitMail(){
		LOG.info("'exitMail' start");
		MailPage page = new MailPage(driver);
		page.clickExitButton();
		Assert.assertTrue(page.searchLoginFieldIsDisplayed(), INCORRECT_EXIT);
		LOG.info("'exitMail' finish");
	}

	@AfterClass
	public void closeDriver() {
		driver.close();
	}
}
