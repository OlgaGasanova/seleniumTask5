package functions;

import org.openqa.selenium.WebDriver;

import ui.page.WriteLetterPage;

public class SaveLetter {
	
	private WebDriver driver;	
	public SaveLetter(WebDriver driver) {
		this.driver = driver;
	}
	
	public SaveLetter performSaveLetter() {
		WriteLetterPage page = new WriteLetterPage(driver);
		page.clickSave();
		return this;
	}
	
	public SaveLetter sendOneLetter(){
		WriteLetterPage page = new WriteLetterPage(driver);
		page.clickSendLetter();	
		return this;
	}
}
