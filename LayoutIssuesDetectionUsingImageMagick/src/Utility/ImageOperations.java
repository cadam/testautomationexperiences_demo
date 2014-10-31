/*
* This file is part of the LayoutIssuesDetectionUsingImageMagick project.
* (c) Adam Claudiu <adam.claudiu86@gmail.com>
*     http://www.testautomationexperiences.com/
* For the full copyright and license information, please view the LICENSE
* file that was distributed with this source code.
*/

package Utility;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.im4java.core.CompareCmd;
import org.im4java.core.ConvertCmd;
import org.im4java.core.IM4JavaException;
import org.im4java.core.IMOperation;
import org.im4java.process.ProcessStarter;
import org.apache.log4j.*;

public class ImageOperations {

	private static Logger Log = Logger.getLogger(ImageOperations.class.getName());
	
	public static void setPath() {
		//here will be the installation path for image magick app
		String myPath="C:\\Program Files\\ImageMagick-6.8.9-Q16"; 
		ProcessStarter.setGlobalSearchPath(myPath);
	}
	
	public static void compareImages(String image1, String image2, String outputImage) {
		CompareCmd cmd = new CompareCmd();
		
		List<String> args = new ArrayList<String>();
		args.add("-compose");
		args.add("Src");
		args.add("-highlight-color");
		args.add("White");
		args.add("-lowlight-color");
		args.add("Black");

		IMOperation im = new IMOperation();
		im.addRawArgs(args);
		im.addImage(image1);
		im.addImage(image2);
		im.addImage(outputImage);
		
		try {
			cmd.run(im);
		} catch (IOException | InterruptedException | IM4JavaException e) {
			if(e.getMessage().contains("return code: 2")) {
				Assert.fail("The compare did not gone well.");
				e.printStackTrace();
			}
			else if(e.getMessage().contains("return code: 0")) {
				Log.info("The images are the same. Something gone wrong with the hash calculations.");
			}
			else if(e.getMessage().contains("return code: 1")) {
				Log.info("The images are different indeed. The differences can be seen in the output image.");
			}
			else
				e.printStackTrace();
		}
	}
	
	public static void convertImages(String image1, String image2, String outputImage) {
		ConvertCmd cmd = new ConvertCmd();
		
		List<String> args = new ArrayList<String>();
		args.add("-delay");
		args.add("50");
		args.add("-loop");
		args.add("0");
		
		IMOperation im = new IMOperation();
		im.addRawArgs(args);
		im.addImage(image1);
		im.addImage(image2);
		im.addImage(outputImage);
		
		try {
			cmd.run(im);
		} catch (IOException | InterruptedException | IM4JavaException e) {
			if(e.getMessage().contains("return code: 2")) {
				Assert.fail("The compare did not gone well.");
				e.printStackTrace();
			}
			else if(e.getMessage().contains("return code: 0")) {
				Log.info("The images are the same. Something gone wrong with the hash calculations.");
			}
			else if(e.getMessage().contains("return code: 1")) {
				Log.info("The images are different indeed. The differences can be seen in the output image.");
			}
			else
				e.printStackTrace();
		}
	}
	
}
