package com.qa.pages;

import com.qa.BaseTest;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class LoginPage extends BaseTest{
	
	@iOSXCUITFindBy (xpath = "(//XCUIElementTypeOther[@name=\"IRO on the GO\"])[1]/XCUIElementTypeTextField" ) 
	@AndroidFindBy  (xpath = "<<!----- Mention Android App xpath using Appium -----!>>")
	private MobileElement id;
	
	@iOSXCUITFindBy (xpath = "(//XCUIElementTypeOther[@name=\"IRO on the GO\"])[1]/XCUIElementTypeSecureTextField" ) 
	@AndroidFindBy  (xpath = "<<!----- Mention Android App xpath using Appium -----!>>")
	private MobileElement psd;
	
	@iOSXCUITFindBy (xpath = "//XCUIElementTypeButton[@name=\"Sign In\"]" ) 
	@AndroidFindBy  (xpath = "<<!----- Mention Android App xpath using Appium -----!>>")
	private MobileElement button;
	
	

	
	public LoginPage enterUserName(String username) {
		click(id);
		sendKeys(id, username,username+" "+"added in userfield");
		return this;
	}
	
	public LoginPage enterPassword(String password) {
		click(psd);
		sendKeys(psd, password,"Password Added");
		return this;
	}
	
	public OverviewPage pressLoginButton() {
		click(button,"Starting Login");
		return new OverviewPage(); 
	}
	
}