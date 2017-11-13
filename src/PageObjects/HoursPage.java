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
			//By locatorHour=By.className("journalrowtime");
			By locatorHour=By.cssSelector("tbody tr td.journalrowtime");
			for(WebElement newMeet:driver.findElements(locatorHour))
			{
				//System.out.println("newMeet="+newMeet.getText() );
				if(newMeet.getText().equals(startHour))
				{
					
					for(WebElement y:newMeet.findElement(By.xpath("./../td[3]")).findElements(By.cssSelector("*")))
					{
						//System.out.println("y="+y.getText());
						if(y.getText().equals("New Event"))
						{
							y.click();
							break;
						}
					}
					//x.click();
					
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
