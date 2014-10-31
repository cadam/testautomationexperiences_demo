/*
* This file is part of the LayoutIssuesDetectionUsingImageMagick project.
* (c) Adam Claudiu <adam.claudiu86@gmail.com>
*     http://www.testautomationexperiences.com/
* For the full copyright and license information, please view the LICENSE
* file that was distributed with this source code.
*/

package Pages;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.log4j.*;
import org.testng.Assert;

import Utility.Digest;
import Utility.General;
import Utility.ImageOperations;
import Utility.Reflection;
import Utility.TakeScreenshot;

public class HomePage {

	@FindBy(linkText="Home")
	private WebElement homeLbl;
	
	WebDriverWait wait;
	
	private static Logger Log = Logger.getLogger(HomePage.class.getName());
	
	public HomePage(WebDriver driver)
	{
		Log.info(Reflection.getCurrentMethod());
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 10);
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
	}
	
	
	//region Actions

	//endregion Actions
	
	//region Asserts
	public void assertHomePageIsDisplayed(WebDriver driver) throws InterruptedException
	{
		Log.info(Reflection.getCurrentMethod());
		wait.until(ExpectedConditions.elementToBeClickable(homeLbl));
		Thread.sleep(5000);
		// this is only an example .. should be modified after understanding how it works
		try {
			TakeScreenshot.shootWebElement(driver, homeLbl, "homeLabelLinkNew");
			String expectedHash = Digest.getHash("homeLabelLink");
			String actualHash = Digest.getHash("homeLabelLinkNew");
			Assert.assertEquals(actualHash, expectedHash);
		} catch (IOException | AssertionError ae) {
			// if the assert failed, we reach this point and we want to compare those 2 images
			ae.printStackTrace();
			
			// set the path for ImageMagick tool which will be called through Im4Java librarie classes
			ImageOperations.setPath();
			
			// load the first image (existing one)
			String pathImage1 = new General().LoadProperties()
					.getProperty("screenshotDirectory") + "homeLabelLink" + ".png";
			
			// load the second image (current test run image)
			String pathImage2 = new General().LoadProperties()
					.getProperty("screenshotDirectory") + "homeLabelLinkNew" + ".png";
			
			// one way of comparing is by using compareImages method and you get one picture
			// with the difference between the 2 compared pictured
			// The 3rd parameter is the output image; can be changed based on the needs, or
			// moved into the properties file as the other parameters
			ImageOperations.compareImages(pathImage1, pathImage2, "outputImage.png");
			
			// the other way of comparing the images is to overlap the 2 images and generate
			// a gif image
			ImageOperations.convertImages(pathImage1, pathImage2, "outputImage.gif");
		}
		
		Assert.assertEquals(homeLbl.isDisplayed(), true, "Assert -- HomePage is displayed.");
	}
	//endregion Asserts

}
