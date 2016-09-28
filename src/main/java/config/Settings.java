package config;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.kohsuke.args4j.Option;

public class Settings {

	@Option(name = "--testng", usage = "set path to testng xml", required = true)
	public String testNgPath;
	
	@Override
	public String toString(){
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SIMPLE_STYLE);
	}
}
