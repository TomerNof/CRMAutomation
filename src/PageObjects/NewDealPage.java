package PageObjects;

import java.io.*;
import java.util.*;

import javax.xml.parsers.ParserConfigurationException;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import org.xml.sax.SAXException;

import Utilites.*;

public class NewDealPage extends Base{

	@FindBy(how=How.ID,using="title")
	private WebElement title;
	
	@FindBy(how=How.CSS,using="*[id='prospectForm'] table tbody tr td table tbody tr td input[value='Lookup']")
	private WebElement companyLookup;
	
	private WebDriver driver;
	public NewDealPage(WebDriver driver)
	{
		this.driver=driver;
	}
	
	public void setDealDetails(String title,String company) throws IOException, ParserConfigurationException, SAXException
	{
		try {
			this.title.sendKeys(title);
			chooseCompanyLookupWindow(company);
			stepPass("typed "+title);
		}
		catch (Exception e) {
			stepFail("didnt typed "+title);
			failOfTestCase(e.getMessage());
		}
	}
	private void chooseCompanyLookupWindow(String company) throws IOException, ParserConfigurationException, SAXException {
		try {
			String currentHandle=driver.getWindowHandle();
			this.companyLookup.click();
			CommonOps.moveToOpenWindow(this.driver);
			companyLookupPage.chooseCompany(company);
			Set<String> handles=driver.getWindowHandles();
			for(String handle:handles) {
				if(handle.equals(currentHandle)) {
					driver.switchTo().window(currentHandle);
					break;
				}
			}
			stepPass("Inserted company "+company+" to the form");
		}
		catch (Exception e) {
			stepFail("Didnt inserted company "+company+" to the form");
			failOfTestCase(e.getMessage());
		}
	}
}
