package com.qa.tests;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.BaseTest;
import com.qa.pages.AddParticipants;
import com.qa.pages.AddTopicAndTags;
import com.qa.pages.CreatingCMSEventPage;
import com.qa.pages.EventsPage;
import com.qa.pages.LoginPage;
import com.qa.pages.NoteActivePage;
import com.qa.pages.OverviewPage;
import com.qa.pages.SelectedEventPage;
import com.qa.pages.SettingPage;
import com.qa.pages.SideMenu;
import com.qa.pages.TagClicked;
import com.qa.pages.WifiPage;
import com.qa.utils.TestUtils;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.time.Duration;
import java.util.HashMap;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Scenario1 extends BaseTest{
	
	LoginPage loginPage;
	OverviewPage overviewPage;
	EventsPage eventsPage;
	AddParticipants addParticipants;
	AddTopicAndTags addTopicAndTags;
	CreatingCMSEventPage creatingCMSEventPage;
	SettingPage settingPage;
	SideMenu sideMenu;
	TagClicked tagClicked;
	WifiPage wifiPage;
	NoteActivePage noteActivePage;
	SelectedEventPage selectedEventPage;
	
	JSONObject loginUsers;
	
	  @BeforeClass
	  public void beforeClass() throws Exception {
		InputStream datais= null;
		  try {
			  
			  String dataFileName = "data/loginUsers.json";
			  datais = getClass().getClassLoader().getResourceAsStream(dataFileName);
			  JSONTokener tokener = new JSONTokener(datais);
			  loginUsers = new JSONObject(tokener);
			  
		  }catch(Exception e) {
			  
			  e.printStackTrace();
			  throw e;
			  
		  } finally {
			  if(datais != null) {
				  datais.close();
			  }
		  }
		  
	  }

	  @AfterClass
	  public void afterClass() {
		  
//		  CloseApp();
//		  LaunchApp();
		  
	  }
	  
	  @BeforeMethod
	  public void beforeMethod(Method m) {
		  loginPage = new LoginPage();
		  System.out.println("\n" + "******  Starting Test: "+ m.getName() + "\n");
		  
		  
//		  loginPage.enterUserName(loginUsers.getJSONObject("validUser").getString("username"));
//			loginPage.enterPassword(loginUsers.getJSONObject("validUser").getString("password"));
//			overviewPage=loginPage.pressLoginButton();
	  
	  
		  
	  
	  }

	  @AfterMethod
	  public void afterMethod() {
		  
//		  sideMenu=overviewPage.pressMenuButton();
//			loginPage=sideMenu.logout();
		  
	  }
	  
	  public TestUtils utils = new TestUtils();
		@Test
		public void test1() throws Exception{
			
			String eventName= "Testing-"+randomNumber();
			utils.log(eventName);
			
			String exceptedNotes = getStrings().get("Notes_Saved_NoWifi");
			utils.log(exceptedNotes);
			
			
			
			loginPage.enterUserName(loginUsers.getJSONObject("validUser").getString("username"));
			loginPage.enterPassword(loginUsers.getJSONObject("validUser").getString("password"));
			overviewPage=loginPage.pressLoginButton();
			creatingCMSEventPage = overviewPage.pressCMSEventButton();
			creatingCMSEventPage.enterTestName("Appium_Testing_ps");
			creatingCMSEventPage.setDateAndTime();
			creatingCMSEventPage.setEventType();
			addParticipants = creatingCMSEventPage.addParticipant();
			addParticipants.addContacts();
			creatingCMSEventPage = addParticipants.save();
			tagClicked = creatingCMSEventPage.addTags();
			addTopicAndTags= tagClicked.selectOption();
			addTopicAndTags.selectInBuildTag();
			addTopicAndTags.selectInBuildTag_ByUser("okay");
			creatingCMSEventPage=addTopicAndTags.clickDoneButton();
			creatingCMSEventPage.addNotes();
			overviewPage=creatingCMSEventPage.cancelProcess();
			
			eventsPage=overviewPage.pressEventButton();
			
			selectedEventPage= eventsPage.scrollAndClick("Auto_Test");
			eventsPage=selectedEventPage.pressBackButton();
			sideMenu = eventsPage.pressMenuButton();
			overviewPage=sideMenu.pressHomeButton();
			sideMenu=overviewPage.pressMenuButton();
			loginPage=sideMenu.logout();
		}
	  
	  
	  
	  
		@Test
		public void test2() throws Exception{
			
			SoftAssert sa= new SoftAssert();
			
			String eventName= "Testing-"+randomNumber();
			String exceptedNotes = getStrings().get("Notes_Saved_NoWifi");
			
			utils.log().info(eventName);
			loginPage.enterUserName(loginUsers.getJSONObject("validUser").getString("username"));
			loginPage.enterPassword(loginUsers.getJSONObject("validUser").getString("password"));
			overviewPage=loginPage.pressLoginButton();
			creatingCMSEventPage = overviewPage.pressCMSEventButton();
			creatingCMSEventPage.enterTestName(eventName);
			creatingCMSEventPage.setDateAndTime2();
			creatingCMSEventPage.setEventType();
			addParticipants = creatingCMSEventPage.addParticipant();
			addParticipants.addContacts();
			creatingCMSEventPage = addParticipants.save();
			tagClicked = creatingCMSEventPage.addTags();
			addTopicAndTags= tagClicked.selectOption();
			addTopicAndTags.selectInBuildTag();
			//addTopicAndTags.selectInBuildTag_ByUser("okay");
			
			String customTagName="CustomTag"+randomNumber()+randomNumber();
			
			addTopicAndTags.selectCustomBuildTag_ByUser(customTagName);
			creatingCMSEventPage=addTopicAndTags.clickDoneButton();
			
			settingPage=OpenSetting();
			wifiPage=settingPage.selectWifi();
			wifiPage.pressWifiSwitch();
			creatingCMSEventPage=OpenIRO();
			
			
			creatingCMSEventPage.pressCancelNoWifi();
			
			
			creatingCMSEventPage.addNotes();
			//overviewPage=creatingCMSEventPage.cancelProcess();
			wifiPage=OpenSetting2();
			wifiPage.pressWifiSwitch();
			
			Thread.sleep(13000);
			
			creatingCMSEventPage=OpenIRO();
			selectedEventPage=creatingCMSEventPage.saveProcess();
			eventsPage=selectedEventPage.pressBackButton();	
			selectedEventPage= eventsPage.scrollAndClick(eventName);
			
			noteActivePage=selectedEventPage.openNotes();
			String StoredNotes= noteActivePage.getNotes();
			
//			sa.assertEquals(StoredNotes, exceptedNotes);
			
			selectedEventPage=noteActivePage.pressBackButton();
			
			eventsPage=selectedEventPage.pressBackButton();
			sideMenu = eventsPage.pressMenuButton();
			overviewPage=sideMenu.pressHomeButton();
			sideMenu=overviewPage.pressMenuButton();
			loginPage=sideMenu.logout();
			utils.log().info("completed");
			
			Assert.assertEquals(StoredNotes, exceptedNotes);

		}
		

}
