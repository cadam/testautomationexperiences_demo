This project is a demo which comes at the request of the users which read my posts from http://www.testautomationexperiences.com/ website.
For details about what this project does, please go to 
1. http://testautomationexperiences.com/detect-layout-issues-using-file-hashes-and-imagemagick-for-compare/
and
2. http://testautomationexperiences.com/detect-layout-issues-using-file-hashes-and-imagemagick-for-compare-coding-in-java/
and read the details.

To succesfully run this project you need to do the following steps:

1. Install Eclipse (to make your life easier): you just need to select a workspace and import the project there
2. Load all jars from lib directory
3. Install TestNG plugin (for seeing the TestNG options in Eclipse menu) for Eclipse. More information about the install here: http://testng.org/doc/download.html
4. Verify the config.properties file and insert your user / pass for mail.yahoo.com: this is required in the demo test case
5. Because the image comparation website is about comparing 2 images, you need am expected image for the home label element; to have it, 
just run the test case once; it will fail, but will generate the picture for you; modify the name from homeLabelLinkNew.png to homeLabelLink.png;
I commited the screenshots folder with these 2 files, but in case the screen is different and also the browser which you use to run against,
just follow the steps I indicated

Good luck!

PS: For questions please see my website: http://www.testautomationexperiences.com/ or mail me on: adam.claudiu86@gmail.com
