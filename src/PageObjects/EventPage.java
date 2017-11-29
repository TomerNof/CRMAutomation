package PageObjects;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import org.xml.sax.SAXException;

import Utilites.*;

public class EventPage extends Base {

	private WebDriver driver;
	private CommonOps comOps;
	@FindBy(how=How.ID,using="title")
	private WebElement title;
	
	@FindBy(how=How.CSS,using="input[class='button'][value='Save']")
	private WebElement btnSave;
	
	@FindBy(how=How.CSS,using="select[name='assigned_to_user_id_src'][class='select']")
	private WebElement assignToUserSrc;

	@FindBy(how=How.CSS,using="input[class='button'][value='==ADD==>']")
	private WebElement addBtn;
	
	@FindBy(how=How.CSS,using="select[name='assigned_to_user_id'][class='select']")
	private WebElement assignToUser;
	
	public EventPage(WebDriver driver) {
		this.driver=driver;
		comOps=new CommonOps();
	}
	
	public void fillForm(String meetingDescription,String assignToUser) throws IOException, ParserConfigurationException, SAXException
	{
		try
		{
			btnSave.click();
			stepPass("click on save in event form");
			comOps.clickOnAlert();
			title.sendKeys(meetingDescription);
			stepPass("insert "+meetingDescription+" to textbox");
			comOps.selectDropDownByVisibleText(assignToUserSrc, assignToUser);
			addBtn.click();
			comOps.selectDropDownByVisibleText(this.assignToUser, assignToUser);
			//comOps.waitForElementToBeVisible(btnSave, "Save button");
			btnSave.click();
			stepPass("click on save in event form");
		}
		catch(Exception e)
		{
			stepFail("Not finish event fill form");
			failOfTestCase(e.getMessage());
		}
	}
}
