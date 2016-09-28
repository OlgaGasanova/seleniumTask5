package functions;

import org.openqa.selenium.WebDriver;
import ui.page.EnterInOurMail;
import ui.page.WriteLetterPage;


public class WriteLetter {
	private WebDriver driver;
	
	public WriteLetter(WebDriver driver) {
		this.driver = driver;
	}
	
	public void performWriteLetter(String whom, String theme, String text) {
		EnterInOurMail pageMail = new EnterInOurMail(driver);
		pageMail.clickWriteLetter();
		
		WriteLetterPage pageWrite = new WriteLetterPage(driver);
		pageWrite.setWhom(whom)
			.setSubject(theme)
			.setText(text)
			.clickSave();
	}
}
