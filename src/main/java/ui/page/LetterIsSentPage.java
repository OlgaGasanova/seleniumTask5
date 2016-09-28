package ui.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LetterIsSentPage {

	private WebDriver driver;

	public LetterIsSentPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = ".//span[text()='Отправленные']")
	private WebElement sentLetters;
	
	@FindBy(xpath = ".//span[text()='Черновики']")
	private WebElement garbage;
	
	public void garbageClick(){
		new Actions(driver).click(garbage).build().perform();
	}
	
	public void sentLettersClick(){
		new Actions(driver).click(sentLetters).build().perform();
	}
}
