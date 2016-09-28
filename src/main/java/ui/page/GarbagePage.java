package ui.page;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import functions.IsPresent;

public class GarbagePage {
	
	private WebDriver driver;

	public GarbagePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
		
	@FindBy(xpath =".//span[text()='Черновики']")
	private WebElement garbage;
	
	@FindBy(xpath = ".//*[@id='b-letters']/div[1]/div[5]/div/div[2]/div[1]/div/a/div[4]/div[3]/div[1]/div")
	private WebElement theme;

	@FindBy(xpath = ".//*[@id='b-letters']/div/div[5]/div/div[2]/div[1]/div/a/div[4]/div[3]/div[2]")
	private WebElement whom;
	
	@FindBy(name = "Subject")
	private WebElement subject;
		
	@FindBy(xpath = "(.//*[@id='b-toolbar__right']//div[@class='b-checkbox__box'])[2]")
	private WebElement selectMessage;
		
	@FindBy(xpath = "(.//*[@id='b-toolbar__right']//span[text()='Удалить'])[2]")
	private WebElement deleteMessage;
	
	@FindBy(xpath = ".//*[@id='b-nav_folders']/div/div[3]/a")
	private WebElement garbageMesssage;
	
	@FindBy(xpath = ".//span[text()='Отправить']")
	private WebElement sendLetter;
	
	public void sendLetterClick(){
		sendLetter.click();
	}
	
	public void garbageClick(){
		garbage.click();
	}	
	
	public GarbagePage whomClick(){
		whom.click();
		return this;
	}

	public boolean isSelectPresent(){
		return IsPresent.isPresent(selectMessage);
	}
	
	public WebElement waitUtilElementToBeClicable(WebElement element) {
		  WebDriverWait wait = new WebDriverWait(driver, 15);
		  try{
			  WebElement myDynamicElement = wait.until(ExpectedConditions.elementToBeClickable(element));
			  return myDynamicElement;
		  }  catch (TimeoutException e){
			  return null;
		  }
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
	
	public String getGarbageMessageAttribute(){
		return garbageMesssage.getAttribute("data-title");
	}	
	
	public String whomText(){
		return whom.getText();
	}
	
	public String themeText(){
		return theme.getText();
	}
}
