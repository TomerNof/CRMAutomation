package PageObjects;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.xml.sax.SAXException;

import Utilites.Base;
import Utilites.CommonOps;

import com.relevantcodes.extentreports.LogStatus;

public class mainPage extends Base
{
	public WebDriver driver;
	CommonOps comOps = new CommonOps();
	
	@FindBy(how = How.ID, using = "searchInput")
	public WebElement searchBox;
	
	@FindBy(how = How.ID, using = "searchLanguage")
	public WebElement searchLanguage;
	
	@FindBy(how = How.CSS, using = "button[type='submit']")
	public WebElement searchButton;
	
	
	public mainPage(WebDriver driver)
	{
		this.driver = driver;
	}
	
	public void searchAction(String searchValue) throws IOException, ParserConfigurationException, SAXException
	{
		comOps.selectDropDownByValue(searchLanguage, "simple");
		searchBox.sendKeys(searchValue);
		test.log(LogStatus.PASS, searchValue + "Inserted");
		searchButton.click();
		test.log(LogStatus.PASS, "Element Clicked");
	}
}