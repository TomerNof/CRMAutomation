package PageObjects;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import org.xml.sax.SAXException;

import Utilites.Base;

public class CompanyLookupPage extends Base {
	@FindBy(how=How.ID,using="search")
	private WebElement searchTextBox;
	
	@FindBy(how=How.CSS,using="input[value='Search']")
	private WebElement searchBtn;
	
	@FindBy(how=How.CSS,using="table tbody tr td table tbody tr td a")
	private WebElement companyToChoose;
	private WebDriver driver;
	public CompanyLookupPage(WebDriver driver)
	{
		this.driver=driver;
	}
	
	public void chooseCompany(String company) throws IOException, ParserConfigurationException, SAXException {
		try {
			searchTextBox.sendKeys(company);
			searchBtn.click();
			companyToChoose.click();
			stepPass("Choose company "+company);
		}
		catch (Exception e) {
			stepFail("Didnt choose company "+company);
			failOfTestCase(e.getMessage());
		}
	}
}
