package com.qa.pages;

import org.openqa.selenium.By;

import com.qa.BaseTest;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.iOSBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class OverviewPage extends BaseTest{
	
	@iOSXCUITFindBy (xpath = "//XCUIElementTypeStaticText[@name=\"CMS event\"]") private MobileElement CMS_event;
	@iOSXCUITFindBy (xpath = "//XCUIElementTypeStaticText[@name=\"events\"]") private MobileElement events;
	@iOSXCUITFindBy (xpath = "//XCUIElementTypeButton[@name=\"nav icon\"]") private MobileElement MenuButton;

	
	
	public CreatingCMSEventPage pressCMSEventButton() {
		click(CMS_event,"Event Creation process started");
		return new CreatingCMSEventPage(); 
	}
	
	public EventsPage pressEventButton() throws Exception{
		click(events);
		Thread.sleep(2000);
		return new EventsPage(); 
	}
	
	public SideMenu pressMenuButton() {
		click(MenuButton);
		return new SideMenu();
	}
	
}