package hardCodedScripts;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class WriteToProperties {

	public static void main(String[] args) throws IOException {
		FileInputStream fis = new FileInputStream("./src/test/resources/new.properties");
		Properties property = new Properties();
		property.load(fis);
		
		property.put("Subject", "Selenium");
		property.put("class", "Benz");
		property.put("roll", "123");
		property.put("value", "null");
		FileOutputStream fos = new FileOutputStream("./src/test/resources/new.properties");
		property.store(fos, "Data updated successfully.");                                     
	}
}