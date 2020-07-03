package com.qa.tests;

import org.testng.annotations.Test;

import com.qa.BaseTest;
import com.qa.pages.EventsPage;
import com.qa.pages.LoginPage;
import com.qa.pages.OverviewPage;

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

public class LoginTests extends BaseTest{
	
	LoginPage loginPage;
	OverviewPage overviewPage;
	EventsPage eventsPage;
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
	  }
	  
	  @BeforeMethod
	  public void beforeMethod(Method m) {
		  loginPage = new LoginPage();
		  System.out.println("\n" + "******  Starting Test: "+ m.getName() + "\n");
	  }

	  @AfterMethod
	  public void afterMethod() {
	  }
	  
		@Test
		public void test1() throws Exception{
			loginPage.enterUserName(loginUsers.getJSONObject("validUser").getString("username"));
			loginPage.enterPassword(loginUsers.getJSONObject("validUser").getString("password"));
			overviewPage=loginPage.pressLoginButton();
			eventsPage = overviewPage.pressEventButton();
			
			
			
		}
		
}
