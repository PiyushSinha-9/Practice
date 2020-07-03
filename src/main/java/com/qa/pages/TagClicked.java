package com.qa.pages;

import org.openqa.selenium.By;

import com.qa.BaseTest;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.iOSBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class TagClicked extends BaseTest{
	
	@iOSXCUITFindBy (xpath = "//XCUIElementTypeTable/XCUIElementTypeCell") private MobileElement Option;
	
	public AddTopicAndTags selectOption() {
		click(Option);
		return new AddTopicAndTags();
	}
	
	public AddTopicAndTags selectOptionCreated() {
		click(Option);
		return new AddTopicAndTags();
	}
	
}