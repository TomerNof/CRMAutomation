package Utilites;

import java.io.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

import javax.xml.parsers.*;

import static org.junit.Assert.*;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.firefox.*;
import org.openqa.selenium.ie.*;
import org.openqa.selenium.remote.*;
import org.sikuli.script.*;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.relevantcodes.extentreports.*;

import PageObjects.CalendarPage;
import PageObjects.CompanyDetailsPage;
import PageObjects.CompanyPage;
import PageObjects.ContactDetailsPage;
import PageObjects.ContactInformationPage;
import PageObjects.ContactsPage;
import PageObjects.CreateCompanyPage;
import PageObjects.EventDetailsPage;
import PageObjects.EventPage;
import PageObjects.HoursPage;
import PageObjects.LoginPage;
import PageObjects.MainPageCrm;
import PageObjects.ManagePage;

public class Base
{
	public static WebDriver driver;
	protected WebDriver localDriver;
	protected static Screen screen;
	protected static ExtentReports extent;
	protected static ExtentTest test;
	protected static String timeStamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(Calendar.getInstance().getTime());	
	protected static LoginPage loginPage;
	protected static MainPageCrm mainPageCrm;
	
	protected static CalendarPage calendarPage;
	protected static HoursPage hoursPage;
	protected static EventPage eventPage;
	protected static EventDetailsPage eventDetailsPage;
	
	protected static CompanyPage companyPage;
	protected static CreateCompanyPage createCompanyPage; 
	protected static CompanyDetailsPage companyDetailsPage;
	
	protected static ContactsPage contactsPage;
	protected static ContactInformationPage contactInformationPage;
	protected static ContactDetailsPage contactDetailsPage;
	/*protected base(WebDriver localDriver) {
		this.localDriver=localDriver;
	}*/
	@BeforeClass
	public static void startSession() throws ParserConfigurationException, SAXException, IOException
	{		
		initBrowser(getData("BrowserType"));
		InstanceReport();
		ManagePage.init();
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
	@Rule
	public TestName testName=new TestName();
	@Before
	public void doBeforeTest() throws IOException, ParserConfigurationException, SAXException
	{
		String text = testName.getMethodName().split("_")[1];
		String cleanText = text.replaceAll("\\d+", "").replaceAll("(.)([A-Z])", "$1 $2");
		initReportTest(testName.getMethodName().split("_")[0],cleanText);
		loginPage.loginUser(getData("LoginUserName"), getData("LoginPassword"));
		
		//driver.navigate().refresh();//for firefox browser
	}
	@After
	public void doAfterTest() throws IOException, ParserConfigurationException, SAXException
	{
		mainPageCrm.clickLogout();
		finalizeReportTest();
	}
	public static String getData (String nodeName) throws ParserConfigurationException, SAXException, IOException
	{
		File fXmlFile =new File("c://test//CrmConfig2.xml");
		if(!fXmlFile.exists()) {//default data file
			fXmlFile =new File( System.getProperty("user.dir")+"//CrmConfig.xml");
		}
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(fXmlFile);		
		doc.getDocumentElement().normalize();
		return doc.getElementsByTagName(nodeName).item(0).getTextContent();
	}
	
	public static void initBrowser(String browserType) throws ParserConfigurationException, SAXException, IOException
	{
		switch (browserType.toLowerCase())
		{
		case "firefox":
			driver = initFFDriver();
			break;
		case "ie":
			driver = initIEDriver();
			break;
		case "chrome":
			driver = initChromeDriver();
			break;
		default:
			failOfTestCase("No valid browser");
		}
		
		driver.manage().window().maximize();
		driver.get(getData("URL"));
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		screen = new Screen();
	}

	public static WebDriver initFFDriver() throws ParserConfigurationException, SAXException, IOException
	{
		System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"\\drivers\\geckodriver.exe");
		WebDriver driverff = new FirefoxDriver();
		return driverff;
		
//		File pathBinary = new File(getData("FirefoxExe"));
//		FirefoxBinary firefoxBinary = new FirefoxBinary(pathBinary);
//		FirefoxProfile firefoxProfile = new FirefoxProfile();       
//		System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"\\drivers\\geckodriver.exe");
//		WebDriver driverff=new FirefoxDriver(firefoxBinary, firefoxProfile);
//		return driverff;

	}
	
	public static WebDriver initIEDriver() throws ParserConfigurationException, SAXException, IOException
	{
		//System.setProperty("webdriver.ie.driver", getData("IEDriverPath"));
		System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+"\\drivers\\MicrosoftWebDriver.exe");
		WebDriver driverie = new InternetExplorerDriver();
		return driverie;
	}
	
	public static WebDriver initChromeDriver() throws ParserConfigurationException, SAXException, IOException
	{
		//System.setProperty("webdriver.chrome.driver", getData("ChromeDriverPath"));
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\drivers\\chromedriver.exe");
		
		WebDriver driver = new ChromeDriver();
		return driver;
	}
	
	public static void InstanceReport() throws ParserConfigurationException, SAXException, IOException
	{
		try
		{
			extent = new ExtentReports(getData("ReportFilePath")+timeStamp+"//" +getData("BrowserType").toLowerCase()+getData("ReportFileName") + timeStamp + ".html", true);		
		}
		catch(Exception e)
		{
			fail("Exception InstanceReport()="+e.getMessage());
		}
		catch (AssertionError e) {
			fail("AssertinoError InstanceReport()="+e.getMessage());
		}
	}
	
	public static void initReportTest(String testName, String testDescription)
	{
		try
		{
			test = extent.startTest(testName, testDescription);
		}
		catch(Exception e)
		{
			fail("Exception initReportTest()="+e.getMessage());
		}
		catch (AssertionError e) {
			fail("AssertinoError initReportTest()="+e.getMessage());
		}
	}
	
	public static void finalizeReportTest()
	{
		try
		{
			extent.endTest(test);
		}
		catch(Exception e)
		{
			fail("Exception finalizeReportTest()="+e.getMessage());
		}
		catch (AssertionError e) {
			fail("AssertinoError finalizeReportTest()="+e.getMessage());
		}
	}
	public static void stepPass(String description)
	{
		try
		{
			test.log(LogStatus.PASS, description);
		
		}
		catch(Exception e)
		{
			fail("Exception stepPass("+description+")="+e.getMessage());
		}
		catch (AssertionError e) {
			fail("AssertinoError stepPass("+description+")="+e.getMessage());
		}
	}
	public static void stepFail(String description) throws IOException, ParserConfigurationException, SAXException
	{
		try
		{
			test.log(LogStatus.FAIL, description+", see screenshut: " + test.addScreenCapture(takeSS()));
			
		}
		catch(Exception e)
		{
			fail("Exception stepFail("+description+")="+e.getMessage());
		}
		catch (AssertionError e) {
			fail("AssertinoError stepFail("+description+")="+e.getMessage());
		}
	}
	public static void finalizeExtentReport()
	{
		try{
			if(extent!=null){
				extent.flush();
				extent.close();
			}
		}
		catch(Exception e)
		{
			fail("Exception finalizeExtentReport()="+e.getMessage());
		}
		catch (AssertionError e) {
			fail("AssertinoError finalizeExtentReport()="+e.getMessage());
		}
		
	}
	public static void failOfTestCase(String description)
	{
		fail(description);
	}
	public static String takeSS() throws IOException, ParserConfigurationException, SAXException
	{
		String SSpath = getData("ReportFilePath")+timeStamp+"//" + "screenshot_" + getRandomNumber() + ".png";
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File(SSpath));
		return SSpath;
	}
	
	public static int getRandomNumber()
	{
		Random rand = new Random();
		//int  n = rand.nextInt(999999) + 1000;
		return rand.nextInt(999999) + 1000;
	}
	public void sleep() throws InterruptedException, NumberFormatException, ParserConfigurationException, SAXException, IOException
	{
		Thread.sleep(Long.parseLong(getData("milis")));
	}
}
