package utils;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.spi.LoggingEvent;
import org.testng.Reporter;

public class ReportingAppender extends AppenderSkeleton {

	@Override
	public void close() {
	}

	@Override
	public boolean requiresLayout() {
		return true;
	}

	@Override
	protected void append(LoggingEvent event) {
		String message = this.layout.format(event);
		message = message.replaceAll("\n", "<br>");
		Reporter.log(message);
	}

}
