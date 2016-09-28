package utils;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenShot {

	private static final String PATH_TO_REPORT = "test-output/html/";
	private static final String SCREENSHOTS_FOLDER = "screenshots";
	
	private static final Logger LOG = Logger.getLogger(ScreenShot.class);
	
	public static void deleteAll(){
		File directory = new File(PATH_TO_REPORT + SCREENSHOTS_FOLDER);
		LOG.info("Delete all files from folder " + directory.getAbsolutePath());
		File[] files = directory.listFiles();
		if (files != null && files.length > 0){
			for (File f: files){
				if (! f.delete()){
					LOG.info("Cannot delete file " + f);
				}
			}
		}
	}
	
	public static void make(WebDriver driver){
		if (driver == null){
			return;
		}
		try{
			File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFileToDirectory(screenshot, new File (PATH_TO_REPORT + SCREENSHOTS_FOLDER));
			String logMessage = "<a href='" + SCREENSHOTS_FOLDER + "/" + screenshot.getName() + "'>See ScreenShot</a>";
			LOG.info(logMessage);
		} catch (Exception e){
			LOG.error("Error with making screenshot: " + e);
			throw new RuntimeException(e);
		}
	}
}
