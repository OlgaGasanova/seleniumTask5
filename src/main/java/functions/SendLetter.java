package functions;

import org.openqa.selenium.WebDriver;

import ui.page.WriteLetterPage;

public class SendLetter {

	private WebDriver driver;
	
	public SendLetter(WebDriver driver){
		this.driver = driver;
	}
	
	public void sendOneLetter(){
		WriteLetterPage page = new WriteLetterPage(driver);
		page.clickSendLetter();	
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}	
	}

}
