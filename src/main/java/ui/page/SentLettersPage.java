package ui.page;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import functions.IsPresent;

public class SentLettersPage{

	private WebDriver driver;

	public SentLettersPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = ".//*[@id='b-letters']/div/div[8]/div/div[2]/div[1]/div/a/div[4]/div[3]/div[1]/div")
	private WebElement themeSentLetter;

	@FindBy(xpath = ".//*[@id='b-letters']/div/div[8]/div/div[2]/div[1]/div/a/div[4]/div[3]/div[2]")
	private WebElement whomSentLetter;
	
	@FindBy(xpath=".//span[text()='Отправленные']")
	private WebElement sentLetters;
	
	@FindBy(xpath = "(.//*[@id='b-toolbar__right']//div[@class='b-checkbox__box'])[3]")
	private WebElement selectMessage;
		
	@FindBy(xpath = "(.//*[@id='b-toolbar__right']//span[text()='Удалить'])[3]")
	private WebElement deleteMessage;
	
	@FindBy(xpath = ".//*[@id='b-nav_folders']/div/div[2]/a")
	private WebElement sentMesssage;
	
	public WebElement waitUtilElementToBeClicable(WebElement element) {
		  WebDriverWait wait = new WebDriverWait(driver, 15);
		  try{
			  WebElement myDynamicElement = wait.until(ExpectedConditions.elementToBeClickable(element));
			  return myDynamicElement;
		  }
		  catch (TimeoutException e){
			  return null;
		  }
	}
	
	public boolean isSelectPresent(){
		return IsPresent.isPresent(selectMessage);
	}
	
	public void sentLettersClick(){
		sentLetters.click();
	}
	
	public String getSentMessageAttribute(){
		return sentMesssage.getAttribute("data-title");
	}
	
	public boolean selectMessageClick(){
		if (!(waitUtilElementToBeClicable(selectMessage) == null)){
			waitUtilElementToBeClicable(selectMessage).click();
			return true;
		}
		else {return false;}
	}
	
	public void deleteMessageClick(){
		deleteMessage.click();		
	}
	
	public String whomText(){
		return whomSentLetter.getText();
	}
	
	public String themeText(){
		return themeSentLetter.getText();
	}
}
