/*
* This file is part of the LayoutIssuesDetectionUsingImageMagick project.
* (c) Adam Claudiu <adam.claudiu86@gmail.com>
*     http://www.testautomationexperiences.com/
* For the full copyright and license information, please view the LICENSE
* file that was distributed with this source code.
*/

package TestCases;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import Pages.HomePage;
import Pages.LoginPage;
import Utility.*;

public class LoginTestCases {
	
	private WebDriver driver;
	private static Properties props;
	
	@DataProvider(name = "loginData")
	public static Object[][] loginData() {
		props = new General().LoadProperties();
	
		String username = props.getProperty("username");
		String password = props.getProperty("password");
		
        return new Object[][] { { username, password } };
    }
	
	@BeforeMethod()
	public void Setup()
	{			
		driver = General.GetBrowser(new General().LoadProperties().getProperty("browser"));
		driver.manage().window().maximize();
		General.GoTo(driver, new General().LoadProperties().getProperty("address"));
		LoginPage loginPage = new LoginPage(driver);
		
		loginPage.assertLoginPageIsDisplayed(driver);
	}
		
	@Test(dataProvider="loginData")
	public void LoginWithCorrectUsernameAndPassword(String username, String password) throws InterruptedException
	{
		General.Login(driver, username, password);
		HomePage homePage = new HomePage(driver);
		homePage.assertHomePageIsDisplayed(driver);		
	}
	
	@AfterMethod
	public void Teardown()
	{	
		try {
		   // you can insert here a call to a logout method
		} catch(Exception ex)
		{			
			System.out.println(ex.getMessage());
		}
		finally {
		driver.quit();
		}
	}

}
