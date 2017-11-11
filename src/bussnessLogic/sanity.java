package bussnessLogic;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.xml.sax.SAXException;

import Utilites.*;

public class sanity extends Base
{
	static PageObjects.mainPage mp;
	static PageObjects.searchResults sr;
	CommonOps comOps = new CommonOps();	
	
	@BeforeClass
	public static void startSession() throws ParserConfigurationException, SAXException, IOException
	{		
		initBrowser(getData("BrowserType"));
		InstanceReport();
		mp = PageFactory.initElements(driver, PageObjects.mainPage.class);
		sr = PageFactory.initElements(driver, PageObjects.searchResults.class);
		
	}
	
	@AfterClass
	public static void closeSession()
	{
		finalizeExtentReport();
		driver.quit();
	}
	
	@After
	public void doAfterTest()
	{
		finalizeReportTest();
	}
	
	@Test
	public void test1() throws IOException, ParserConfigurationException, SAXException
	{
		initReportTest("test1", "verify: Selenium WebDriver not exists in Wikipedia");
		comOps.verifyImageExists("wikiHeader.png");
		mp.searchAction("Selenium WebDriver");
		comOps.verifyElementExists(sr.searchResult);
		comOps.verifyValueExists(sr.searchResult , "There were no results matching the query.");
	}
}
