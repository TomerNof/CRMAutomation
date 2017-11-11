package PageObjects;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import org.xml.sax.SAXException;

import Utilites.*;

public class ContactsPage extends Base {
	
	private WebDriver driver;

	@FindBy(how=How.CSS,using="input[class='button'][value='New Contact']")
	private WebElement newContact;
	
	public ContactsPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
	}
	public void clickOnNewContact() throws IOException, ParserConfigurationException, SAXException
	{
		try
		{
			newContact.click();
			stepPass("Clicked on new contact");
		}
		catch(Exception e)
		{
			stepFail("Didnt clicked on new contact");
			failOfTestCase(e.getMessage());
		}
	}
}
