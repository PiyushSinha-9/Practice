package com.qa.pages;

import org.openqa.selenium.By;

import com.qa.BaseTest;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.iOSBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class AddParticipants extends BaseTest{
	
	@iOSXCUITFindBy (xpath = "(//XCUIElementTypeOther[2])[2]") private MobileElement TxtField;
	@iOSXCUITFindBy (xpath = "(//XCUIElementTypeButton[@name=\"addParticipate\"])[1]") private MobileElement AddContact1;
	@iOSXCUITFindBy (xpath = "//XCUIElementTypeButton[@name=\"addParticipate\"]") private MobileElement AddContact2;
	@iOSXCUITFindBy (xpath = "(//XCUIElementTypeButton[@name=\"Done\"])[1]") private MobileElement DoneButton;
	
	public AddParticipants addContacts() {
		click(TxtField);
		sendKeys(TxtField,"testing");
		click(AddContact1);
		click(AddContact2);
		return this;
	}
	
	public CreatingCMSEventPage save() {
		click(DoneButton,"Participants Added.");
		return new CreatingCMSEventPage();
	}
}