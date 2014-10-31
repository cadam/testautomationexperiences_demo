/*
* This file is part of the LayoutIssuesDetectionUsingImageMagick project.
* (c) Adam Claudiu <adam.claudiu86@gmail.com>
*     http://www.testautomationexperiences.com/
* For the full copyright and license information, please view the LICENSE
* file that was distributed with this source code.
*/

package Utility;

public class Reflection {
	
	public static String getCurrentMethod(){
	    try{
	        throw new Exception("");
	    }catch(Exception e){
	        return e.getStackTrace()[1].toString();
	    }
	}
	
}
