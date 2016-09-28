package functions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import ui.page.EnterInOurMail;
import ui.page.GarbagePage;
import ui.page.SentLettersPage;


public class DeleteLetters {

	private static final String ELEMENT_SELECT_MESSAGE_ON_SENT_LETTERS_PAGEIS_NOT_CLICKABLE = "Element 'selectMessage' on SentLettersPageis not clickable";
	private static final String ELEMENT_SELECT_MESSAGE_ON_GARBAGE_PAGE_IS_NOT_CLICKABLE = "Element 'selectMessage' on GarbagePage is not clickable";
	private static final String NO_LETTERS = "Нет писем";
	private WebDriver driver;
	
	public DeleteLetters(WebDriver driver){
		this.driver = driver;
	}	
	
	/*
	public void deleteLettersGarbage(){
		//new EnterInOurMail(driver).garbageClick();	
		GarbagePage pageG = new GarbagePage(driver);
		pageG.garbageClick();
		while (pageG.isWhomPresent()){
			pageG.chooseLetterClick();
			pageG.deleteMessageClick();
		}
	}*/
	
	
	public boolean deleteLettersGarbage(){
		new EnterInOurMail(driver).garbageClick();	
		GarbagePage pageG = new GarbagePage(driver);
		//pageG.garbageClick();
		boolean result = false;
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();	
		}
		String count = pageG.getGarbageMessageAttribute();	
		System.out.println("garbageMesssage.getAttribute(data-title)=" + count);
		if (count.length() > 0){
			if (!count.equals(NO_LETTERS)){
				if (pageG.isSelectPresent()){
					Assert.assertTrue(pageG.selectMessageClick(), ELEMENT_SELECT_MESSAGE_ON_GARBAGE_PAGE_IS_NOT_CLICKABLE);
					pageG.deleteMessageClick();
					result = true;
				}
				else {result = false;}
			}	
			else {result = true;}
		}
		return result;
	}
	
	public boolean deleteLettersSent(){
		new EnterInOurMail(driver).sentLettersClick();
		SentLettersPage pageS = new SentLettersPage(driver);
		boolean result = false;
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();	
		}
		String count = pageS.getSentMessageAttribute();
		System.out.println("sentMesssage.getAttribute(data-title)=" + count);
		if (count.length() > 0){
			if (!count.equals(NO_LETTERS)){
				if (pageS.isSelectPresent()){
					Assert.assertTrue(pageS.selectMessageClick(), ELEMENT_SELECT_MESSAGE_ON_SENT_LETTERS_PAGEIS_NOT_CLICKABLE);
					pageS.deleteMessageClick();
					result = true;
				}
				else {result = false;}
			}
			else {result = true;}
		}
		return result;
	}
}
