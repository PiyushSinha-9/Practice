package com.qa.pages;

import org.openqa.selenium.By;

import com.qa.BaseTest;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.iOSBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class NoteActivePage extends BaseTest{
	
	@iOSXCUITFindBy (xpath = "//XCUIElementTypeNavigationBar[@name=\"Notes\"]/XCUIElementTypeButton[1]") private MobileElement BackButton;
	@iOSXCUITFindBy (xpath = "(//XCUIElementTypeTextView/XCUIElementTypeOther)[2]/child::*[1]") private MobileElement Notes;
	
	public String getNotes() {
//		String store= Notes.getAttribute("value","Notes extracted after event creation");
		String store= getAttribute(Notes,"value","Notes extracted after event creation");
		return store;
	}
	
	public SelectedEventPage pressBackButton() {
		click(BackButton);
		
		return new SelectedEventPage();
	}
	
}