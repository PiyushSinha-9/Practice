package com.qa.pages;

import org.openqa.selenium.By;

import com.qa.BaseTest;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.iOSBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class SettingPage extends BaseTest{
	
	@iOSXCUITFindBy (xpath = "//XCUIElementTypeCell[@name=\"Wi-Fi\"]") private MobileElement WifiOption;
	
	public WifiPage selectWifi() {
		click(WifiOption);
		return new WifiPage();
	}
	
}