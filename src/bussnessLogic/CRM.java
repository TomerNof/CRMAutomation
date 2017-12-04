package bussnessLogic;

import java.io.*;
import java.text.*;
import java.time.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

import javax.xml.parsers.*;

import org.junit.*;
import org.junit.runners.*;
import org.openqa.selenium.support.*;
import org.xml.sax.*;

import PageObjects.*;
import Utilites.*;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CRM extends Base{
	CommonOps comOps = new CommonOps();	
	static LoginPage loginPage;
	static MainPageCrm mainPageCrm;
	
	static CalendarPage calendarPage;
	static HoursPage hoursPage;
	static EventPage eventPage;
	static EventDetailsPage eventDetailsPage;
	
	static CompanyPage companyPage;
	static CreateCompanyPage createCompanyPage; 
	static CompanyDetailsPage companyDetailsPage;
	
	static ContactsPage contactsPage;
	static ContactInformationPage contactInformationPage;
	static ContactDetailsPage contactDetailsPage;
	@BeforeClass
	public static void startSession() throws ParserConfigurationException, SAXException, IOException
	{		
		initBrowser(getData("BrowserType"));
		InstanceReport();
		loginPage= PageFactory.initElements(driver, LoginPage.class);
		mainPageCrm = PageFactory.initElements(driver, MainPageCrm.class);
		
		//For meeting test case
		calendarPage=PageFactory.initElements(driver, CalendarPage.class);
		hoursPage=PageFactory.initElements(driver, HoursPage.class);
		eventPage=PageFactory.initElements(driver, EventPage.class);
		eventDetailsPage=PageFactory.initElements(driver, EventDetailsPage.class);
		
		//For company test case
		companyPage=PageFactory.initElements(driver, CompanyPage.class);
		createCompanyPage=PageFactory.initElements(driver,CreateCompanyPage.class);
		companyDetailsPage=PageFactory.initElements(driver, CompanyDetailsPage.class);
	
		//For contact test case
		contactsPage=PageFactory.initElements(driver, ContactsPage.class);
		contactInformationPage=PageFactory.initElements(driver, ContactInformationPage.class);
		contactDetailsPage=PageFactory.initElements(driver, ContactDetailsPage.class);
	}
	
	@AfterClass
	public static void closeSession()
	{
		finalizeExtentReport();
		if(driver!=null)
		{
			driver.quit();
		}
	}

	@Before
	public void doBeforeTest()
	{
		//driver.navigate().refresh();//for firefox browser
	}
	@After
	public void doAfterTest() throws IOException, ParserConfigurationException, SAXException
	{
		mainPageCrm.clickLogout();
		finalizeReportTest();
	}
	@Test
	public void newContact() throws IOException, ParserConfigurationException, SAXException, InterruptedException
	{
		initReportTest("newContact", "verify: newContact");
		loginPage.loginUser(getData("LoginUserName"), getData("LoginPassword"));
		mainPageCrm.clickContactTab();
		contactsPage.clickOnNewContact();
		String lastName=getData("NewContactLastName")+getRandomNumber();
		contactInformationPage.fillForm(getData("NewContactFirstName"), lastName);
		contactDetailsPage.validateContact(getData("NewContactFirstName"), lastName);
	}
	@Test
	public void newCompany() throws IOException, ParserConfigurationException, SAXException, InterruptedException
	{
		initReportTest("newCompany", "verify: newCompany");
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
	public void newMeeting() throws IOException, ParserConfigurationException, SAXException, InterruptedException
	{
		initReportTest("newMeeting", "verify: create new meeting");
		loginPage.loginUser(getData("LoginUserName"), getData("LoginPassword"));
		mainPageCrm.clickCalenderTab();
		calendarPage.moveToDay(getData("DateNewMeeting"));
		hoursPage.clickOnNewMetting(getData("StartHour"));
		eventPage.fillForm(getData("MeetDesc")+" "+getData("UserName")+" "+getData("StartHour"), getData("UserName"));
		eventDetailsPage.validateNewMeeting(getData("MeetDesc")+" "+getData("UserName")+" "+getData("StartHour"),getData("UserName"));
		
	}

	

	@Test
	public void verifyUserName() throws IOException, ParserConfigurationException, SAXException, InterruptedException
	{
		initReportTest("verifyUserName", "verify: user name is valid");
		comOps.verifyImageExists("freeCRM.PNG");
		loginPage.loginUser(getData("LoginUserName"), getData("LoginPassword"));
		comOps.verifyElementExists(mainPageCrm.getUserName());
		//comOps.verifyValueExists(mainPageCrm.getUserName(), getData("ExpectedUserTitle")+getData("UserName"));
		comOps.verifyValueExists(mainPageCrm.getUserName(), "User: "+getData("UserName"));
		
	}
}
