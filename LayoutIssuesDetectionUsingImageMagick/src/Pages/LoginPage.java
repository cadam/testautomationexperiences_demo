/*
* This file is part of the LayoutIssuesDetectionUsingImageMagick project.
* (c) Adam Claudiu <adam.claudiu86@gmail.com>
*     http://www.testautomationexperiences.com/
* For the full copyright and license information, please view the LICENSE
* file that was distributed with this source code.
*/

package Pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.log4j.*;
import org.testng.Assert;

import Utility.Reflection;

public class LoginPage {
	
	@FindBy(id="username")
	private WebElement usernameTxtBox;
	
	@FindBy(id="passwd")
	private WebElement passwordTxtBox;
	
	@FindBy(id=".save")
	private WebElement signInBtn;
	
	WebDriverWait wait;
	
	private static Logger Log = Logger.getLogger(LoginPage.class.getName());
	
	public LoginPage(WebDriver driver)
	{
		Log.info(Reflection.getCurrentMethod());
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 10);
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
	}
	
	
	//region Actions
	public void setUsername(String _username)
	{
		Log.info(Reflection.getCurrentMethod());
		usernameTxtBox.sendKeys(_username);
	}
	
	public void setPassword(String _password)
	{
		Log.info(Reflection.getCurrentMethod());
		passwordTxtBox.sendKeys(_password);
	}
	
	public void clickLogin()
	{
		Log.info(Reflection.getCurrentMethod());
		wait.until(ExpectedConditions.elementToBeClickable(signInBtn));
		signInBtn.click();
	}
	//endregion Actions
	
	//region Asserts
	public void assertLoginPageIsDisplayed(WebDriver driver)
	{
		Log.info(Reflection.getCurrentMethod());
		wait.until(ExpectedConditions.elementToBeClickable(signInBtn));
		Assert.assertEquals(driver.getTitle().trim(), "Sign in to Yahoo");
	}
	//endregion Asserts

}
