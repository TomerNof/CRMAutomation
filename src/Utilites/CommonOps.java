package Utilites;
import static org.junit.Assert.*;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.xpath.patterns.StepPattern;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import org.xml.sax.SAXException;

import com.relevantcodes.extentreports.LogStatus;

public class CommonOps extends Base
{
	public void verifyElementExists(WebElement elem) throws IOException, ParserConfigurationException, SAXException
	{
		try
		{
			elem.isDisplayed();
			drawElemet(elem);
			stepPass("Element "+elem+" exists !");			
		}
		catch (Exception e)
		{
			stepFail("Element "+elem+" not exists !");
			fail(e.getMessage());
		}
	}
	
	public void verifyValueExists(WebElement elem, String expectedValue) throws IOException, ParserConfigurationException, SAXException
	{
		try
		{
			String actual = elem.getText().trim();
			drawElemet(elem);
			assertEquals(expectedValue, actual);
			stepPass(expectedValue+" exists");
		}
		catch (Exception e)
		{
			stepFail("Problem with "+elem);
			fail(e.getMessage());
		}
		catch (AssertionError ea)
		{
			stepFail("assert failed of "+expectedValue);
			fail(ea.getMessage());
		}
	}
	
	
	public void selectDropDownByValue(WebElement elem, String valueLanguage) throws IOException, ParserConfigurationException, SAXException
	{
		try
		{
			Select myValue = new Select(elem);
			myValue.selectByValue(valueLanguage);
			stepPass("combobox with value "+valueLanguage+" selected");
		}
		catch (Exception e)
		{
			stepFail(elem+" didnt selected");
			fail(e.getMessage());
		}
	}

	public void selectDropDownByVisibleText(WebElement elem, String valueLanguage) throws IOException, ParserConfigurationException, SAXException
	{
		try
		{
			Select myValue = new Select(elem);
			myValue.selectByVisibleText(valueLanguage);
			stepPass("combobox with visible text "+valueLanguage+" selected");
		}
		catch (Exception e)
		{
			stepFail(elem+" didnt selected");
			fail(e.getMessage());
		}
	}

	public void verifyImageExists(String imageName) throws IOException, ParserConfigurationException, SAXException
	{
		try
		{
			//screen.click(getData("ImagePath") + imageName);
			screen.click(System.getProperty("user.dir")+"\\images\\" + imageName);
			stepPass("Image "+imageName+" found");
		}
		catch (Exception e)
		{
			stepFail("Image "+imageName+" not found");
			fail(e.getMessage());
		}
	}
	public void clickOnAlert() throws IOException, ParserConfigurationException, SAXException {
		try
		{
			Alert alertWin=driver.switchTo().alert();
			alertWin.accept();
			stepPass("Clicked on alert");
		}
		catch(Exception e)
		{
			stepFail("Didnt found alert");
			failOfTestCase(e.getMessage());
		}
	}
	
	public static String getMeetingDate(String dateNewMeeting,String StartHour)
	{
//		<DateNewMeeting>13/8/2019</DateNewMeeting>
//
//		<StartHour>03:00 PM</StartHour>
//
//		<EndHour>04:00 PM</EndHour>
//
//		<MeetDesc>New meeting with</MeetDesc>
//
//		<NewMeetingStart>Aug 13, 2019, 3:00:00 PM</NewMeetingStart>
		String res="";
		String newDateNewMeeting[]=dateNewMeeting.split("/");
		res=
		res+=" "+newDateNewMeeting[0]+", "+newDateNewMeeting[2]+", ";
		String newStartHour[]=StartHour.split(" ");
		newStartHour[0]=newStartHour[0]+":00";
		res+=newStartHour[0]+" "+newStartHour[1];
		return res;
	}
	private void drawElemet(WebElement element)
	{
		((JavascriptExecutor)driver).executeScript("arguments[0].style.border='2px solid blue'",element);
	}
}
