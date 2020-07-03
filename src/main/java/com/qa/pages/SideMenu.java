package com.qa.pages;

import org.openqa.selenium.By;

import com.qa.BaseTest;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.iOSBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class SideMenu extends BaseTest{
	
	@iOSXCUITFindBy (xpath = "//XCUIElementTypeButton[@name=\"LOG OUT\"]") private MobileElement LogoutButton;
	@iOSXCUITFindBy (xpath = "//XCUIElementTypeStaticText[@name=\"HOME\"]") private MobileElement HomeButton;
	
	public LoginPage logout() {
		click(LogoutButton,"Logging Out....");
		return new LoginPage();
	}
	
	public OverviewPage pressHomeButton() {
		click(HomeButton);
		return new OverviewPage();
	}
}