package ui.page;

import java.util.NoSuchElementException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MailPage {

	private WebDriver driver;
	
	public MailPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id = "mailbox__login")
	private WebElement searchLoginField;	
	
	@FindBy(xpath = ".//*[@id='mailbox__password']")
	private WebElement searchPasswordField;
	
	@FindBy(xpath = ".//*[@value='Войти']")
	private WebElement searchButtonEnter;
	
	@FindBy(id = "PH_logoutLink")
	private WebElement searchButtonExit;

    public boolean searchLoginFieldIsDisplayed(){
    	return searchLoginField.isDisplayed();
	}	
	
	public static boolean isPresent(WebElement webElement) {
		  boolean out = false;
		  try {
		   webElement.getSize();
		   out = true;
		  } catch (NoSuchElementException e) {
		   out = false;
		  }
		  return out;
		 }
	
	public WebElement waitUtilElementToBeClicable(WebElement element) {
		  WebDriverWait wait = new WebDriverWait(driver, 15);
		  WebElement myDynamicElement = wait.until(ExpectedConditions.elementToBeClickable(element));
		  return myDynamicElement;
	}
	
	public MailPage setSearchLoginFieldText(String text) {
		new Actions(driver).sendKeys(searchLoginField, text).build().perform();
		return this;
	}
	
	public MailPage setSearchPasswordFieldText(String text) {
		new Actions(driver).sendKeys(searchPasswordField, text).build().perform();
		return this;
	}
	
	public void clickEnterButton() {
		new Actions(driver).click(searchButtonEnter).build().perform();
	}
	
	public void clickExitButton() {
		new Actions(driver).click(searchButtonExit).build().perform();
	}
	
}
