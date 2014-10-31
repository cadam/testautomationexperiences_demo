/*
* This file is part of the LayoutIssuesDetectionUsingImageMagick project.
* (c) Adam Claudiu <adam.claudiu86@gmail.com>
*     http://www.testautomationexperiences.com/
* For the full copyright and license information, please view the LICENSE
* file that was distributed with this source code.
*/

package Utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.codec.digest.DigestUtils;


public class Digest{

    public static String getHash(String fileName) throws IOException  {
        return DigestUtils.md5Hex(new FileInputStream(new File(new General().LoadProperties()
    					.getProperty("screenshotDirectory") + fileName + ".png")));
    }
}