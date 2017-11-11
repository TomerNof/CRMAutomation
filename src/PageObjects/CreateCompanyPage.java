package PageObjects;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import org.xml.sax.SAXException;

import Utilites.*;

public class CreateCompanyPage extends Base {
	private WebDriver driver;
	private CommonOps comOps;
	@FindBy(how=How.CSS,using="input[class='button'][value='Save']")
	private WebElement btnSave;

	@FindBy(how=How.ID,using="company_name")
	private WebElement companyName;

	@FindBy(how=How.NAME,using="industry")
	private WebElement industry;
	
	@FindBy(how=How.ID,using="num_of_employees")
	private WebElement employees;

	@FindBy(how=How.ID,using="phone")
	private WebElement phone;

	@FindBy(how=How.ID,using="website")
	private WebElement website;
	
	public CreateCompanyPage(WebDriver driver)
	{
		this.driver=driver;
		comOps=new CommonOps();
	}
	public void newCompany(String nameOfCompny,
			String nameOfIndustry,
			String numberOfEmployees,
			String phoneNumber,
			String url) throws IOException, ParserConfigurationException, SAXException
	{
		try
		{
			btnSave.click();
			stepPass("clicked on save before alert");
			comOps.clickOnAlert();
			companyName.sendKeys(nameOfCompny);
			industry.sendKeys(nameOfIndustry);
			employees.sendKeys(numberOfEmployees);
			phone.sendKeys(phoneNumber);
			website.sendKeys(url);
			btnSave.click();
			stepPass("Fill the form of new company");
			
		}
		catch(Exception e)
		{
			stepFail("Can't create new company");
			failOfTestCase("Error"+e.getMessage());
		}
		
	}
}
