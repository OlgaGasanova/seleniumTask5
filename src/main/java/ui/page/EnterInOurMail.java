package ui.page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class EnterInOurMail {


	private WebDriver driver;
	
	public EnterInOurMail(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(xpath = ".//i[text()='bolshova.olechka@mail.ru']")
	private WebElement enterPerson;
	
	@FindBy(id = "PH_logoutLink")
	private WebElement searchButtonExit;
	
	@FindBy(xpath = ".//*[text()='Написать письмо']")
	private WebElement writeLetter;
		
	@FindBy(xpath=".//span[text()='Отправленные']")
	private WebElement sentLetters;
		
	@FindBy(xpath =".//span[text()='Черновики']")
	private WebElement garbage;
	
	@FindBy(css = ".b-nav__link.js-shortcut")
	private WebElement garbageMesssage;
	
	public String getGarbageMessageAttribute(){
		return garbageMesssage.getAttribute("data-title");
	}	
	
	public WebElement getPerson(){
		return enterPerson;
	}
	
	public void garbageClick(){
		new Actions(driver).click(garbage).build().perform();
	}
	
	public void clickWriteLetter(){
		new Actions(driver).click(writeLetter).build().perform();
	}
	
	public void sentLettersClick(){
		new Actions(driver).click(sentLetters).build().perform();
	}
	
	public void assertEnterInMail(String s){
		String path = ".//i[text()='" + s + "']";
		try
		{
			((JavascriptExecutor) driver).
			executeScript("arguments[0].click()", driver.findElement(By.xpath(path)));
		} catch (Exception e){
			System.out.println("Enter in mail is incorrect");
		}	
	//	WebElement searchPerson = (WebElement) ((JavascriptExecutor) driver).
	//			executeScript("return document.getElementByXPath('.//i[text()='bolshova.olechka@mail.ru']');");	
	//	Assert.assertTrue(searchPerson.isDisplayed(), "Enter in mail is incorrect");
	}
			
	public void clickExitButton() {
		new Actions(driver).click(searchButtonExit).build().perform();
	}

}
