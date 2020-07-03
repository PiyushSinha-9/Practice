package com.qa.pages;

import org.openqa.selenium.By;

import com.qa.BaseTest;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.iOSBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class SelectedEventPage extends BaseTest{
	
	@iOSXCUITFindBy (xpath = "//XCUIElementTypeNavigationBar[@name=\"Event Overview\"]/XCUIElementTypeButton[1]") private MobileElement BackButton;
	@iOSXCUITFindBy (xpath = "//XCUIElementTypeStaticText[@name=\" Notes \"]") private MobileElement NotesButton;
	@iOSXCUITFindBy (xpath = "//XCUIElementTypeStaticText[@name=\"Event\"]") private MobileElement NoteActive_Event;
	@iOSXCUITFindBy (xpath = "//XCUIElementTypeStaticText[@name=\"Test12\"]") private MobileElement EventName;

	public EventsPage pressBackButton() {
		click(BackButton);
		return new EventsPage();
	}
	
	public NoteActivePage openNotes() throws Exception {
		Thread.sleep(3000);
		click(NotesButton);
		click(NoteActive_Event,"Notes section added");
		return new NoteActivePage();
	}

}