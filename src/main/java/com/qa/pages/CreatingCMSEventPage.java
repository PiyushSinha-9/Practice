package com.qa.pages;

import java.time.Duration;

import com.qa.BaseTest;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.pagefactory.iOSBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class CreatingCMSEventPage extends BaseTest{
	
	@iOSXCUITFindBy (xpath = "//XCUIElementTypeStaticText[@name=\"Title*\"]//following-sibling::XCUIElementTypeTextField[1]" ) private MobileElement Title;
	@iOSXCUITFindBy (xpath = "//XCUIElementTypeStaticText[@name=\"Title*\"]//following-sibling::XCUIElementTypeTextField[2]" ) private MobileElement Date_and_Time;
	@iOSXCUITFindBy (xpath = "//XCUIElementTypeStaticText[@name=\"Title*\"]//following-sibling::XCUIElementTypeTextField[3]" ) private MobileElement Event_Type;
	@iOSXCUITFindBy (xpath = "//XCUIElementTypeStaticText[@name=\"Title*\"]//following-sibling::XCUIElementTypeTextField[4]" ) private MobileElement Participants;
	@iOSXCUITFindBy (xpath = "//XCUIElementTypeButton[@name=\" Topics / Tags \"]" ) private MobileElement Tags;
	@iOSXCUITFindBy (xpath = "(//XCUIElementTypeOther[@name=\"Rich Text Area. Press ALT-F9 for menu. Press ALT-F10 for toolbar. Press ALT-0 for help\"])[2]" ) private MobileElement Notes;
	@iOSXCUITFindBy (xpath = "//XCUIElementTypeOther[@name=\"Tiny MCE editor\"]" ) private MobileElement NotesActive;
	@iOSXCUITFindBy (xpath = "//XCUIElementTypeButton[@name=\"Cancel\"]" ) private MobileElement CancelButton;
	@iOSXCUITFindBy (xpath = "//XCUIElementTypeButton[@name=\"Yes\"]" ) private MobileElement CancelActive_Yes;
	@iOSXCUITFindBy (xpath = "//XCUIElementTypeButton[@name='Done']" ) private MobileElement KeyboardDone;
	@iOSXCUITFindBy (xpath = "//XCUIElementTypeButton[@name=\"Save\"]" ) private MobileElement SaveButton;
	@iOSXCUITFindBy (xpath= "//XCUIElementTypeButton[@name=\"Cancel\"]" ) private MobileElement CancelNoWifiButton;
	
	public TouchAction t = new TouchAction(getDriver());

	public CreatingCMSEventPage enterTestName(String username) {
		sendKeys(Title, username);
		return this;
	}
	
	public CreatingCMSEventPage setDateAndTime() {
		click(Date_and_Time);
		t.press(PointOption.point(124, 552)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).moveTo(PointOption.point(124,360)).release().perform();
		t.press(PointOption.point(204, 559)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).moveTo(PointOption.point(204,477)).release().perform();
		t.press(PointOption.point(262, 555)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).moveTo(PointOption.point(262,216)).release().perform();
		click(KeyboardDone);
		return this;
	}
	
	public CreatingCMSEventPage setDateAndTime2() {
		click(Date_and_Time);
		for(int i=0; i<1;i++) {
			t.press(PointOption.point(116, 464)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).moveTo(PointOption.point(116,657)).release().perform();
		}
		t.press(PointOption.point(204, 559)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).moveTo(PointOption.point(204,477)).release().perform();
		t.press(PointOption.point(262, 555)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).moveTo(PointOption.point(262,216)).release().perform();
		click(KeyboardDone);
		return this;
	}

	
	public CreatingCMSEventPage setEventType() {
		click(Event_Type);
		t.press(PointOption.point(204, 559)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).moveTo(PointOption.point(204,400)).release().perform();
		return this;
	}
	
	public AddParticipants addParticipant() {
		click(Participants);
		return new AddParticipants();
	}
	
	public TagClicked addTags() {
		click(Tags);
		return new TagClicked();
	}
	
	public CreatingCMSEventPage addNotes() {
		t.press(PointOption.point(124, 552)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).moveTo(PointOption.point(124,360)).release().perform();
		click(Notes);
		sendKeys(NotesActive,"Random Characters added for Testing","Notes Added");
		return this;
	}
	
	
	public OverviewPage cancelProcess() {
		click(CancelButton);
		click(CancelActive_Yes);
		return new OverviewPage();
	}
	
	public SelectedEventPage saveProcess() throws Exception {
		click(SaveButton,"Note Saved");
		Thread.sleep(5000);
		return new SelectedEventPage();
	}
	
	public CreatingCMSEventPage pressCancelNoWifi() {
		click(CancelNoWifiButton,"Pressing Cancel Button");
		return this;
	}
	
}