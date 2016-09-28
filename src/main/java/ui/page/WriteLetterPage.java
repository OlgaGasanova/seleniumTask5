package ui.page;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class WriteLetterPage {

	private WebDriver driver;

	public WriteLetterPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
		
	@FindBy(id = "PH_logoutLink")
	private WebElement searchButtonExit;
	
	
	@FindBy(xpath = ".//*[text()='Написать письмо']")
	private WebElement writeLetter;
	
	@FindBy(xpath = ".//*[@id='tinymce']")
	private WebElement textBody;
		
	@FindBy(xpath = ".//*[@id='compose__header__content']/div[2]/div[2]/div[1]/textarea[2]")
	private WebElement whom;
	
	@FindBy(name = "Subject")
	private WebElement subject;
	
	@FindBy(xpath = ".//iframe[@title='{#aria.rich_text_area}']")
	private WebElement textFrame;
	
	@FindBy(xpath = ".//*[@id='tinymce']")
	private WebElement textArea;
	
	@FindBy(xpath = ".//span[text()='Сохранить']")
	private WebElement save;
	
	@FindBy(xpath = ".//span[text()='Отправить']")
	private WebElement sendLetter;
	
	@FindBy(xpath=".//span[text()='Отправленные']")
	private WebElement sentLetters;
	
	@FindBy(xpath = ".//*[@id='b-toolbar__right']/div/div/div[2]/div[6]/div/a")
	private WebElement garbage;

	public void clickSentLetters(){
		new Actions(driver).click(sentLetters).build().perform();
	}
	
	public void clickSendLetter(){
		new Actions(driver).click(sendLetter).build().perform();
	}
	
	public WriteLetterPage setWhom(String addressee){ 
		whom.clear();
		new Actions(driver).sendKeys(addressee).build().perform();
		return this;
	}
	
	public WriteLetterPage setSubject(String theme){
		subject.clear();
		new Actions(driver).sendKeys(theme).build().perform();
		return this;
		
	}
	
	public WriteLetterPage setText(String text){
		 driver.switchTo().frame(textFrame);
		 textArea.clear();
		 textArea.sendKeys(text);
		 driver.switchTo().defaultContent();
		 return this;
	}
	
	public void clickSave(){ 
		new Actions(driver).click(save).build().perform();
	}
		
	public void clickWriteLetter(){
		new Actions(driver).click(writeLetter).build().perform();
	}
	
	public void assertEnterInMail(){
		WebElement searchPerson = (WebElement) ((JavascriptExecutor) driver).
				executeScript("return document.getElementByXPath('.//i[text()='bolshova.olechka@mail.ru']');");
		Assert.assertTrue(searchPerson.isDisplayed());
	}
	
	public void garbageClick(){
		new Actions(driver).click(garbage).build().perform();
	}
		
	public void clickExitButton() {
		new Actions(driver).click(searchButtonExit).build().perform();
	}
}
