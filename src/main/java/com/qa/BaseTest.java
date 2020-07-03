package com.qa;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.qa.pages.CreatingCMSEventPage;
import com.qa.pages.OverviewPage;
import com.qa.pages.SettingPage;
import com.qa.pages.WifiPage;
import com.qa.reports.ExtentReport;
import com.qa.utils.TestUtils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.InteractsWithApps;
import io.appium.java_client.MobileElement;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.commons.codec.binary.Base64;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.ThreadContext;
import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;

import io.appium.java_client.screenrecording.CanRecordScreen;

public class BaseTest {
	
	//Thread Safe
	
//	protected static AppiumDriver<MobileElement> driver;
//	protected static Properties props;
//	protected static HashMap<String, String> strings = new  HashMap<String, String>();
//	protected static String dateTime;
//	protected static String platform;
	
	protected static ThreadLocal <AppiumDriver> driver= new ThreadLocal<AppiumDriver>();
	protected static ThreadLocal <Properties> props= new ThreadLocal<Properties>();
	protected static ThreadLocal <HashMap<String, String>> strings = new  ThreadLocal<HashMap<String, String>>();
	protected static ThreadLocal <String> dateTime= new ThreadLocal<String>();
	protected static ThreadLocal <String> platform= new ThreadLocal<String>();
	
	protected static ThreadLocal <String> deviceName= new ThreadLocal<String>();
	

	
	TestUtils utils= new TestUtils();
	
	public AppiumDriver getDriver() {
		return driver.get();
	}
	
	public void setDriver(AppiumDriver driver2) {
		driver.set(driver2);
	}
	
	public Properties getProps() {
		return props.get();
	}
	
	public void setProps(Properties props2) {
		props.set(props2);
	}
	
	public HashMap<String, String> getStrings() {
		return strings.get();
	}
	
	public void setStrings(HashMap<String,String> strings2) {
		strings.set(strings2);
	}
	
	public String getPlatform() {
		return platform.get();
	}
	
	public void setPlatform(String platform2) {
		platform.set(platform2);
	}
	
	public String getDateTime() {
		return dateTime.get();
	}
	
	public void setDateTime(String dateTime2) {
		dateTime.set(dateTime2);
	}
	
	

	public String getDeviceName() {
		return deviceName.get();
	}
	
	public void setDeviceName(String deviceName2) {
		deviceName.set(deviceName2);
	}
	
	
	
	// constructor
	
	
	// Page factory Elements
	public BaseTest() {
		PageFactory.initElements(new AppiumFieldDecorator(getDriver()), this);
	}
	
	
	
	@BeforeMethod
	public void beforeMethod() {
		((CanRecordScreen) getDriver()).startRecordingScreen();
	}
	
	
	//stop video capturing and create *.mp4 file
		@AfterMethod
		public synchronized void  afterMethod(ITestResult result) {
			String media = ((CanRecordScreen) getDriver()).stopRecordingScreen();
			
			
			
			if(result.getStatus()==2) {
				Map <String, String> params = result.getTestContext().getCurrentXmlTest().getAllParameters();		
				String dirPath = "videos" + File.separator + params.get("platformName") + "_" + params.get("deviceName") 
				+ File.separator + getDateTime() + File.separator + result.getTestClass().getRealClass().getSimpleName();
				
				File videoDir = new File(dirPath);
				
				synchronized(videoDir){					// object Synchronised
					if(!videoDir.exists()) {
						videoDir.mkdirs();
					}	
				}
				
				FileOutputStream stream = null;
				try {
					stream = new FileOutputStream(videoDir + File.separator + result.getName() + ".mp4");
					stream.write(Base64.decodeBase64(media));
//					stream.close();
//					utils.log().info("video path: " + videoDir + File.separator + result.getName() + ".mp4");
				} catch (Exception e) {
//					utils.log().error("error during video capture" + e.toString());
					e.printStackTrace();
				}
			}
			
				
		}
	
//	//stop video capturing and create *.mp4 file
//	@AfterMethod
//	public synchronized void afterMethod(ITestResult result) throws Exception {
//		String media = ((CanRecordScreen) getDriver()).stopRecordingScreen();
//		
//		
//		
//		if(result.getStatus()==2) {
//			
//		}
//		
//		
//		Map <String, String> params = result.getTestContext().getCurrentXmlTest().getAllParameters();		
//		String dirPath = "videos" + File.separator + params.get("platformName") + "_" + params.get("deviceName") 
//		+ File.separator + getDateTime() + File.separator + result.getTestClass().getRealClass().getSimpleName();
//		
//		File videoDir = new File(dirPath);
//		
//		synchronized(videoDir){
//			if(!videoDir.exists()) {
//				videoDir.mkdirs();
//			}	
//		}
//		FileOutputStream stream = null;
//		try {
//			stream = new FileOutputStream(videoDir + File.separator + result.getName() + ".mp4");
//			stream.write(Base64.decodeBase64(media));
////			stream.close();
////			utils.log().info("video path: " + videoDir + File.separator + result.getName() + ".mp4");
//		} catch (Exception e) {
////			utils.log().error("error during video capture" + e.toString());
//			e.printStackTrace();
//		}
////		finally {
////			if(stream != null) {
////				stream.close();
////			}
////		}		
//	}


	@Parameters({"platformName","udid","deviceName","platformVersion"})  
	@BeforeTest
	public void beforeTest(String platformName,String udid, String deviceName,String platformVersion) throws Exception {
		utils = new TestUtils();
		setDateTime(utils.dateTime());
		setPlatform(platformName);
		setDeviceName(deviceName);
		URL url;
		InputStream stringsis = null;
		InputStream inputStream = null;
		Properties props= new Properties();
		AppiumDriver driver;
		
		String strFile = "logs" + File.separator + platformName + "_" + deviceName;
		File logFile = new File(strFile);
		if (!logFile.exists()) {
			logFile.mkdirs();
		}
		//route logs to separate file for each thread
		ThreadContext.put("ROUTINGKEY", strFile);
		utils.log().info("log path: " + strFile);
		
		
		try {

			props = new Properties();
			String propFileName= "config.properties";
			String xmlFileName="strings/strings.xml";
			
			
			inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);		 
			props.load(inputStream);
			setProps(props);
			
			DesiredCapabilities cap= new DesiredCapabilities();
			
			
			stringsis =getClass().getClassLoader().getResourceAsStream(xmlFileName);
			setStrings(utils.parseStringXML(stringsis));
			
			
			
			
			cap.setCapability("deviceName", deviceName);
			cap.setCapability("platformVersion", platformVersion);
			cap.setCapability("platformName", platformName);
			
			
			switch(platformName) {
			case "iOS":
				cap.setCapability("udid",udid);
				cap.setCapability("automationName",props.getProperty("IOSAutomationName"));
				cap.setCapability("bundleId", props.getProperty("IOSBundleId"));
				
				
//				Using it while working on .app, .apk file.
//				URL appURL= getClass().getClassLoader().getResource(props.getProperty("IOSAppLocation"));
//				String appURL= getClass().getResource(props.getProperty("IOSAppLocation")).getFile();
//				cap.setCapability("app",appURL);
				
				
				url = new URL(props.getProperty("appiumURL") + "4723/wd/hub");
				driver= new IOSDriver<MobileElement>(url, cap);
				break;
				
			// For Future Expansion
			case "Android":
				cap.setCapability("udid",udid);
				cap.setCapability("automationName",props.getProperty("AndroidAutomationName"));
				cap.setCapability("bundleId", props.getProperty("AndroidBundleId"));
				
//				Using it while working on .app, .apk file.
//				String androidAppURL= getClass().getResource(props.getProperty("AndroidAppLocation")).getFile();
//				cap.setCapability("app",androidAppURL);

				url = new URL(props.getProperty("appiumURL") + "4723/wd/hub");
				driver= new AndroidDriver<MobileElement>(url, cap);
				break;
			default:
				throw new Exception("Platform Name is invalid!! - "+ platformName);
			}
			setDriver(driver);
		}

		catch(Exception exp) {
			TestUtils utils = new TestUtils();
			utils.log().info("Cause is : "+exp.getCause());
			utils.log().info("Message is : "+exp.getMessage());
			//			exp.printStackTrace();
		}
		finally {
			if(inputStream != null) {
				inputStream.close();
			}
			if(stringsis != null) {
				stringsis.close();
			}
		}



	}
	

	
	
	public void waitForVisibility(MobileElement e) {
		WebDriverWait wait= new WebDriverWait(getDriver(), TestUtils.WAIT);
		wait.until(ExpectedConditions.visibilityOf(e));
	}

	public void clear(MobileElement e) {
		waitForVisibility(e);
		e.clear();
	}
	
	public void clear(MobileElement e,String msg) {
		waitForVisibility(e);
		utils.log().info(msg);
		
		ExtentReport.getTest().log(Status.INFO, msg);
		
		e.clear();
	}
	
	public void click(MobileElement e) {
		waitForVisibility(e);
		e.click();
	}
	
	public void click(MobileElement e,String msg) {
		waitForVisibility(e);
		e.click();
		utils.log().info(msg);
		ExtentReport.getTest().log(Status.INFO, msg);
		
	}
	
	
	
	public void sendKeys(MobileElement e,String txt)
	{
		waitForVisibility(e);
		e.sendKeys(txt);
	}
	
	public void sendKeys(MobileElement e,String txt,String msg)
	{
		waitForVisibility(e);
		utils.log().info(msg);
		//ExtentReport.getTest().log(Status.INFO, msg);
		e.sendKeys(txt);
	}


	
	public String getAttribute(MobileElement e, String attribute) {
		waitForVisibility(e);
		return e.getAttribute(attribute);
	}
	
	public String getAttribute(MobileElement e, String attribute,String msg) {
		waitForVisibility(e);
		utils.log().info(msg);
		return e.getAttribute(attribute);
	}
	
	public String randomNumber() {
		Random r = new Random();
		int low = 1000;
		int high = 9999;
		int result = r.nextInt(high-low) + low;
		return Integer.toString(result); 
	}
	
	public void CloseApp() {
		((InteractsWithApps)driver).closeApp();
	}
	
	public void LaunchApp() {
		((InteractsWithApps)driver).launchApp();
	}
	
	
	
//	public static void scrollup() {
//		Dimension dim = getDriver().manage().window().getSize();
//		int scrollStart = (int)(dim.getHeight()*0.5);
//		int scrollEnd = (int)(dim.getHeight()*0.8);
//		
//		new TouchAction((PerformsTouchActions)driver).press(PointOption.point(0,scrollStart))
//		.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(2))).moveTo(PointOption
//				.point(0, scrollEnd)).release().perform();
//		
//		
//	}
	
	public HashMap<String, Object> args = new HashMap<String, Object>();
	public  SettingPage OpenSetting() {
		
		utils.log().info("Switching to Setting app");
		
		args.put("bundleId", "com.apple.Preferences");
		getDriver().executeScript("mobile: launchApp", args);
		
//		CloseApp();
//		LaunchApp();
//		
		return new SettingPage();
	}
	
	public  WifiPage OpenSetting2() {
		
		utils.log().info("Switching to Wifi Setting app");
		
		args.put("bundleId", "com.apple.Preferences");
		getDriver().executeScript("mobile: launchApp", args);
		return new WifiPage();
	}
	
	
	
	public CreatingCMSEventPage OpenIRO() {
		
		utils.log().info("Switching to IRO on the Go app");
		
		args.put("bundleId", "com.nasdaq.VA");
		getDriver().executeScript("mobile: activateApp", args);
		
		return new CreatingCMSEventPage();
	}
	
	
	public OverviewPage OpenIRO2() {
		
		utils.log().info("Switching to IRO on the Go app");
		
		args.put("bundleId", "com.nasdaq.VA");
		getDriver().executeScript("mobile: activateApp", args);
		return new OverviewPage();
	}
	
	
	
	
//	public static void scrolling1(WebElement el) throws Exception {
//		
//		int retry =0 ;
//		
//			while(retry <= 7) {
//				try {
//					el.click();
//					System.out.println("bhaiya bhiya");
//					break;
//					
//				}catch (org.openqa.selenium.NoSuchElementException e) {
//					
//					System.out.println("deekte");
//					scrollup();
//					Thread.sleep(5000);
//					retry++;
//				}
//			}
//			
//		}
//	
	
	public void Example_How_to_Tackle_Multi_Platform() {
		
		switch(getPlatform()) {
		case "Android":
			// Add Android specific Features
			utils.log().info("Perform Android features");
			break;
		
		case "iOS":
			// Add IOS specific Features
			utils.log().info ("Perform IOS featues");
			break;
		}
	
	}
	

	@AfterTest
	public void afterTest() throws Exception {
		Thread.sleep(5000);
		getDriver().quit();
	}

}
