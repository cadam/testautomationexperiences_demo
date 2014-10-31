/*
* This file is part of the LayoutIssuesDetectionUsingImageMagick project.
* (c) Adam Claudiu <adam.claudiu86@gmail.com>
*     http://www.testautomationexperiences.com/
* For the full copyright and license information, please view the LICENSE
* file that was distributed with this source code.
*/

package Utility;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class TakeScreenshot{

    public static void shootWebElement(WebDriver driver, WebElement element, 
    		String fileName) throws IOException  {

        File screen = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        Point p = element.getLocation();

        int width = element.getSize().getWidth();
        int height = element.getSize().getHeight();

        BufferedImage img = ImageIO.read(screen);

        BufferedImage dest = img.getSubimage(p.getX(), p.getY(), width,   
                                 height);

        ImageIO.write(dest, "png", screen);

        File f = new File(new General().LoadProperties().getProperty("screenshotDirectory") 
        		+ fileName + ".png");

        FileUtils.copyFile(screen, f);

    }
}