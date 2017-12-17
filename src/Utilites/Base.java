package Utilites;

import java.io.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

import javax.xml.parsers.*;

import static org.junit.Assert.*;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.firefox.*;
import org.openqa.selenium.ie.*;
import org.openqa.selenium.remote.*;
import org.sikuli.script.*;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.relevantcodes.extentreports.*;

public class Base
{
	public static WebDriver driver;
	protected WebDriver localDriver;
	public static Screen screen;
	public static ExtentReports extent;
	public static ExtentTest test;
	public static String timeStamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(Calendar.getInstance().getTime());	
	
	/*protected base(WebDriver localDriver) {
		this.localDriver=localDriver;
	}*/
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
	public void stepPass(String description)
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
	public void stepFail(String description) throws IOException, ParserConfigurationException, SAXException
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
