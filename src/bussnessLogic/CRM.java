package bussnessLogic;

import java.io.*;
import java.text.*;
import java.time.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

import javax.xml.parsers.*;

import org.junit.*;
import org.junit.rules.TestName;
import org.junit.runners.*;
import org.openqa.selenium.support.*;
import org.xml.sax.*;

import PageObjects.*;
import Utilites.*;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CRM extends Base{
	
	
	@Test
	public void newContact_VerifyNewContact() throws IOException, ParserConfigurationException, SAXException, InterruptedException
	{
		loginPage.loginUser(getData("LoginUserName"), getData("LoginPassword"));
		mainPageCrm.clickContactTab();
		contactsPage.clickOnNewContact();
		String lastName=getData("NewContactLastName")+getRandomNumber();
		contactInformationPage.fillForm(getData("NewContactFirstName"), lastName);
		contactDetailsPage.validateContact(getData("NewContactFirstName"), lastName);
	}
	@Test
	public void newCompany_VerifyNewCompany() throws IOException, ParserConfigurationException, SAXException, InterruptedException
	{

		loginPage.loginUser(getData("LoginUserName"), getData("LoginPassword"));
		mainPageCrm.clickCompanyTab();
		companyPage.clickOnNewCompantBtn();
		String companyName=getData("NewCompanyTitle")+getRandomNumber();
		createCompanyPage.newCompany(companyName,
				getData("IndustryName"),
				getData("EmployeeNumber"),
				getData("CompanyPhone"),
				getData("CompanyWebsite"));
		companyDetailsPage.valideCompany(companyName,
				getData("EmployeeNumber"),
				getData("IndustryName"),
				getData("CompanyWebsite"),
				getData("CompanyPhone"));
		
	}	
	
	@Test
	public void newMeeting_VerifyCreateNewMeeting() throws IOException, ParserConfigurationException, SAXException, InterruptedException
	{
		loginPage.loginUser(getData("LoginUserName"), getData("LoginPassword"));
		mainPageCrm.clickCalenderTab();
		calendarPage.moveToDay(getData("DateNewMeeting"));
		hoursPage.clickOnNewMetting(getData("StartHour"));
		eventPage.fillForm(getData("MeetDesc")+" "+getData("UserName")+" "+getData("StartHour"), getData("UserName"));
		eventDetailsPage.validateNewMeeting(getData("MeetDesc")+" "+getData("UserName")+" "+getData("StartHour"),getData("UserName"));
		
	}
	@Test
	public void verifyUserName_VerifyUserNameIsValid() throws IOException, ParserConfigurationException, SAXException, InterruptedException
	{
		
		//CommonOps.verifyImageExists("freeCRM.PNG");
		loginPage.loginUser(getData("LoginUserName"), getData("LoginPassword"));
		CommonOps.verifyElementExists(mainPageCrm.getUserName());
		CommonOps.verifyValueExists(mainPageCrm.getUserName(), "User: "+getData("UserName"));
		
	}
	@Test
	public void verifySeeViewToday_LookForTheEventsOfToday() throws IOException, ParserConfigurationException, SAXException, NumberFormatException, InterruptedException
	{
		loginPage.loginUser(getData("LoginUserName"), getData("LoginPassword"));
		mainPageCrm.moveToCalenderTab();
		mainPageCrm.clickOnNewEvent();
		CommonOps.verifyElementExists(eventDetailsPage.getEventInformation());
	
	}
}
