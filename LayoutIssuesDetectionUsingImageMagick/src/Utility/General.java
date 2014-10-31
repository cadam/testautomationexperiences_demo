/*
* This file is part of the LayoutIssuesDetectionUsingImageMagick project.
* (c) Adam Claudiu <adam.claudiu86@gmail.com>
*     http://www.testautomationexperiences.com/
* For the full copyright and license information, please view the LICENSE
* file that was distributed with this source code.
*/

package Utility;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.apache.log4j.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.*;
import org.openqa.selenium.firefox.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.safari.*;

import Pages.LoginPage;

public class General {
	private static Logger Log =
		      Logger.getLogger(General.class.getName());
		
	public static WebDriver GetBrowser(String driverName) {
		
		WebDriver driver = null;
		
		switch(driverName) {
			case "IE":  
						System.setProperty("webdriver.ie.driver", new General().LoadProperties().getProperty("ieDriver"));	
						driver = new InternetExplorerDriver();
					    break;
			case "Firefox": driver = new FirefoxDriver();
						break;
			case "Chrome": 
						System.setProperty("webdriver.chrome.driver", new General().LoadProperties().getProperty("chromeDriver"));
						ChromeOptions options = new ChromeOptions();
						options.addArguments("test-type");
						driver = new ChromeDriver(options);
						break;
			case "Safari": driver = new SafariDriver();
						break;						
		}
		Log.info("Connnection opened using " + driverName + " browser.");
		
		return driver;
	}
	
	public static void GoTo(WebDriver driver, String url){
		
		try {
			URL address = new URL(url);
			driver.navigate().to(address);
		} catch(MalformedURLException ex) {
			Log.log(Level.ERROR, ex.getMessage());
		}
		
	}
	
	public void CloseConnection(WebDriver driver) {
		
		driver.close();
		Log.info("Connection closed.");
		
	}
	
	public static void Login(WebDriver driver, String _username, String _password) {
		
		LoginPage loginPage = new LoginPage(driver);
		loginPage.setUsername(_username);
		loginPage.setPassword(_password);
		loginPage.clickLogin();

	}

	public Properties LoadProperties()
	{
		Properties prop = new Properties();
		String propFileName = "config.properties";
		
		InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
		try {
			prop.load(inputStream);
		} catch (IOException e) {
			Log.info(e.getMessage());
		}
		
		return prop;
	}
	
	public static void DefaultWait()
	{	
		String waitTime = new General().LoadProperties().getProperty("defaultWait");
		
		try {
			Thread.sleep(Long.parseLong(waitTime));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
