package com.qa.pages;

import org.openqa.selenium.By;

import com.qa.BaseTest;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.iOSBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class WifiPage extends BaseTest{
	
	@iOSXCUITFindBy (xpath = "//XCUIElementTypeSwitch[@name=\"Wi-Fi\"]") private MobileElement WifiSwitch;
	
	public WifiPage pressWifiSwitch() {
		click(WifiSwitch);
		return this;
	}
}