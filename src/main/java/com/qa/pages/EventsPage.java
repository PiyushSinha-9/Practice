package com.qa.pages;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;

import com.qa.BaseTest;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.iOSBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class EventsPage extends BaseTest{
	
	
	@iOSXCUITFindBy (xpath = "//XCUIElementTypeButton[@name=\"nav icon\"]") private MobileElement MenuButton;
	
	public SelectedEventPage scrollAndClick(String eventName) throws Exception {
		
		Thread.sleep(3000);
		MobileElement check123= (MobileElement) getDriver().findElementByAccessibilityId(eventName);
		Thread.sleep(5000);
		check123.click();
		Thread.sleep(5000);
		MobileElement check12345= (MobileElement) getDriver().findElementByAccessibilityId(eventName);
		Thread.sleep(4000);
		check12345.click();
		return new SelectedEventPage();
		
	}
	
	
	public SideMenu pressMenuButton() {
		click(MenuButton, "Upper Side button pressed");
		return new SideMenu();
	}
	
}