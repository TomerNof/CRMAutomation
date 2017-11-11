package PageObjects;


import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import org.xml.sax.SAXException;

import Utilites.*;

public class ContactDetailsPage extends Base {

	private WebDriver driver;
	private CommonOps comOps;
	@FindBy(how=How.XPATH,using="//*[@id='vSummary']/table/tbody/tr[1]/td/table/tbody/tr/td[1]")
	private WebElement contactTitle;
	
	@FindBy(how=How.XPATH,using="//*[@id='vSummary']/table/tbody/tr[2]/td[1]/table/tbody/tr[2]/td[2]")
	private WebElement contactName;
	
	public ContactDetailsPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
		comOps=new CommonOps();
	}
	public void validateContact(String firstName,String lastName) throws IOException, ParserConfigurationException, SAXException
	{
		try
		{
			String fullName=firstName+" "+lastName;
			comOps.verifyValueExists(this.contactTitle, fullName);
			comOps.verifyValueExists(contactName, fullName);
			stepPass(fullName+" is a new contact");
		}
		catch(Exception e)
		{
			stepFail("Wrong validate");
			failOfTestCase(e.getMessage());
		}
	}
}
