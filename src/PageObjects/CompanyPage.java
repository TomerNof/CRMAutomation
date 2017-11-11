package PageObjects;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import org.xml.sax.SAXException;

import Utilites.*;

public class CompanyPage extends Base {
	private WebDriver driver;

	@FindBy(how=How.CSS,using="input[class='button'][value='New Company']")
	private WebElement newCompanyButton;
	
	public CompanyPage(WebDriver driver)
	{
		this.driver=driver;
	}
	public void clickOnNewCompantBtn() throws IOException, ParserConfigurationException, SAXException {
		try
		{
			newCompanyButton.click();
			stepPass("click on New company button");
		}
		catch(Exception e)
		{
			stepFail("Didnt find the New company button");
			failOfTestCase(e.getMessage());
		}
	}
}
