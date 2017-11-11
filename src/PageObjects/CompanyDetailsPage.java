package PageObjects;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.xml.sax.SAXException;

import Utilites.*;

public class CompanyDetailsPage extends Base {

	private WebDriver driver;
	private CommonOps comOps;
	//@FindBy(how=How.XPATH,using="//tbody/tr/td[@class='datacardtitle'][starts-with(text(),'Company: ')]")
	@FindBy(how=How.XPATH,using="//td[@class='tabs_header']")
	private WebElement company;
	
	@FindBy(how=How.XPATH,using="//strong[text()='Employees']/../../td[2]")
	private WebElement employees;
	
	@FindBy(how=How.XPATH,using="//strong[text()='Industry']/../../td[2]")
	private WebElement industry;
	
	@FindBy(how=How.XPATH,using="//strong[text()='Website']/../../td[2]")
	private WebElement website;
	
	
	@FindBy(how=How.XPATH,using="//strong[text()='Phone']/../../td[2]")
	private WebElement phone;
	
	public CompanyDetailsPage(WebDriver driver) {
		this.driver=driver;
		comOps=new CommonOps();
	}
	public void valideCompany(String companyName,
			String employees,
			String industry,
			String website,
			String phone) throws IOException, ParserConfigurationException, SAXException
	{
		try
		{
			comOps.verifyValueExists(company, companyName);
			comOps.verifyValueExists(this.employees, employees);
			comOps.verifyValueExists(this.industry, industry);
			comOps.verifyValueExists(this.website, website);
			comOps.verifyValueExists(this.phone, phone);
			stepPass("Validated that "+companyName+", "+employees+", "+industry+", "+website+" and "+phone+" exists");
		}
		catch(Exception e)
		{
			stepFail("Failed to validate company details");
			failOfTestCase(e.getMessage());
		}
	}
}
