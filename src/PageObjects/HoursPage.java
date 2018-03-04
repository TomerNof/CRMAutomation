package PageObjects;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import org.xml.sax.SAXException;

import Utilites.*;

public class HoursPage extends Base {

	private WebDriver driver;
	private CommonOps comOps;
//	@FindBy(how=How.NAME,using="slctMonth")
//	private WebElement monthCombo;
	
	@FindBy(how=How.XPATH,using="/html/body/table[2]/tbody/tr[1]/td[2]/table/tbody/tr/td/table/tbody/tr/td[1]/table/tbody/tr[1]/td[2]/table/tbody/tr/td/strong")
	private WebElement todayDate;
	
	public HoursPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
		comOps=new CommonOps();
	}
	public WebElement getTodayDate() throws IOException, ParserConfigurationException, SAXException
	{
		try {
			return this.todayDate;
		}
		catch (Exception e) {
			stepFail("Didnt find today date");
			failOfTestCase(e.getMessage());
		}
		return null;
	}
	public void clickOnNewMetting(String startHour) throws IOException, ParserConfigurationException, SAXException
	{
		try
		{
			By locatorHour=By.cssSelector("tbody tr td.journalrowtime");
			for(WebElement newMeet:driver.findElements(locatorHour))
			{
				if(newMeet.getText().equals(startHour))
				{
					
					for(WebElement y:newMeet.findElement(By.xpath("./../td[3]")).findElements(By.cssSelector("*")))
					{
						if(y.getText().equals("New Event"))
						{
							
							y.click();
							break;
						}
					}
					break;
				}
			}
		}
		catch(Exception e)
		{
			stepFail("didnt find start hour "+startHour);
			failOfTestCase(e.getMessage());
		}
	}
}
