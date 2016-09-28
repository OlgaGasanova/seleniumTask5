package functions;

import java.util.NoSuchElementException;

import org.openqa.selenium.WebElement;

public class IsPresent {

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
}
