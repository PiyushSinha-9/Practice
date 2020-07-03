package com.qa.pages;

import org.openqa.selenium.By;

import com.qa.BaseTest;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.iOSBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class AddTopicAndTags extends BaseTest{
	@iOSXCUITFindBy (xpath = "//XCUIElementTypeSearchField/parent::*[1]") private MobileElement TxtField;
	@iOSXCUITFindBy (xpath = "//XCUIElementTypeStaticText[@name=\"TESTING TOPIC001\"]") private MobileElement InbuildTag;
	@iOSXCUITFindBy (xpath = "//XCUIElementTypeButton[@name=\"Create New\"]") private MobileElement CustomTagButton;
	@iOSXCUITFindBy (xpath = "(//XCUIElementTypeButton[@name=\"Done\"])[1]") private MobileElement DoneButton;
	@iOSXCUITFindBy (xpath = "//XCUIElementTypeButton[@name=\"addParticipate\"]") private MobileElement AddTagButton;
	
						public AddTopicAndTags selectInBuildTag() {
							click(InbuildTag,"Selecting InBuilt Tags" );
							return this;
						}
						
						public AddTopicAndTags selectInBuildTag_ByUser(String txt) {
							click(TxtField);
							sendKeys(TxtField,txt);
							click(AddTagButton);
							return this;
						}
						
						public AddTopicAndTags selectCustomBuildTag_ByUser(String txt) {
							click(TxtField);
							sendKeys(TxtField,txt);
							click(CustomTagButton,txt+" Has been added as new custom Tag");
							return this;
						}
						
						
						public CreatingCMSEventPage clickDoneButton() {
							click(DoneButton);
							return new CreatingCMSEventPage();
						}
}