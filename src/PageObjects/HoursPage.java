package PageObjects;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import org.xml.sax.SAXException;

import Utilites.*;

public class HoursPage extends Base {

	private WebDriver driver;
	
//	@FindBy(how=How.NAME,using="slctMonth")
//	private WebElement monthCombo;
	
	
	
	public HoursPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
	}
	
	public void clickOnNewMetting(String startHour) throws IOException, ParserConfigurationException, SAXException
	{
		try
		{
			By locatorHour=By.className("journalrowtime");
			for(WebElement newMeet:driver.findElements(locatorHour))
			{
				if(newMeet.getText().equals(startHour))
				{
					newMeet.findElement(By.xpath("./../td[3]")).click();
					//newMeet.findElement(By.xpath("./../*[contains(text(),'New Event')]")).click();
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
