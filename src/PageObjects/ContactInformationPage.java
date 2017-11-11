package PageObjects;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import org.xml.sax.SAXException;

import Utilites.*;

public class ContactInformationPage extends Base {
	private WebDriver driver;
	private CommonOps comOps;
	@FindBy(how=How.ID,using="first_name")
	private WebElement firstName;

	@FindBy(how=How.ID,using="surname")
	private WebElement lastName;
	
	@FindBy(how=How.XPATH,using="//*[@id='contactForm']/table/tbody/tr[1]/td/input[2]")
	private WebElement saveBtn;
	
	public ContactInformationPage(WebDriver driver)
	{
		this.driver=driver;
		this.comOps=new CommonOps();
	}
	
	public void fillForm(String firstName,String lastName) throws IOException, ParserConfigurationException, SAXException
	{
		try
		{
			saveBtn.click();
			comOps.clickOnAlert();
			this.firstName.sendKeys(firstName);
			this.lastName.sendKeys(lastName);
			saveBtn.click();
			stepPass("Filled the new contact form");
		}
		catch(Exception e)
		{
			stepFail("Fill form doesnt ok");
			failOfTestCase(e.getMessage());
		}
		
	}
}
