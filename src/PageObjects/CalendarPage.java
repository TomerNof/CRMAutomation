package PageObjects;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.xml.sax.SAXException;

import Utilites.*;

public class CalendarPage extends Base {

	private WebDriver driver;
	private CommonOps comOps;
	//@FindBy(how=How.NAME,using="slctMonth")
	@FindBy(how=How.CSS,using="select[name='slctMonth']")
	private WebElement monthCombo;
	
	
	@FindBy(how=How.NAME,using="slctYear")
	private WebElement yearCombo;
	
	public CalendarPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
		comOps=new CommonOps();
	}
	
	public void moveToDay(String day) throws IOException, ParserConfigurationException, SAXException
	{
		//5/12/2018
		try
		{
			String dayArr[]=day.split("/");
			String year=dayArr[2];
			String month=dayArr[1];
			String dayInCalendar=dayArr[0];
			comOps.selectDropDownByValue(yearCombo, year);
			//new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(monthCombo));//for IE
			new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(monthCombo));//for IE
			
			comOps.selectDropDownByValue(monthCombo, month);
			By dayLocator=By.xpath("//*[@id='crmcalendar']/table/tbody/tr[2]/td/table/tbody/tr/td");
			new WebDriverWait(driver, 5).until(ExpectedConditions.presenceOfElementLocated(dayLocator));//for IE
			for(WebElement dayToClick:driver.findElements(dayLocator))
			{
				if(dayToClick.getText().startsWith(dayInCalendar))
				{
					dayToClick.click();
					stepPass("Moved to hours of the day");
					break;
				}
			}
		}
		catch(Exception e)
		{
			stepFail("didnt moved to day "+day);
			failOfTestCase(e.getMessage());
		}
	}
}
