package Utilites;
import static org.junit.Assert.*;

import java.io.IOException;
import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Set;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.xpath.patterns.StepPattern;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.xml.sax.SAXException;

import com.relevantcodes.extentreports.LogStatus;

public class CommonOps extends Base
{
	public static void verifyElementExists(WebElement elem) throws IOException, ParserConfigurationException, SAXException
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
	
	public static void verifyValueExists(WebElement elem, String expectedValue) throws IOException, ParserConfigurationException, SAXException
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
	
	
	public static void selectDropDownByValue(WebElement elem, String valueLanguage) throws IOException, ParserConfigurationException, SAXException
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

	public static void selectDropDownByVisibleText(WebElement elem, String valueLanguage) throws IOException, ParserConfigurationException, SAXException
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

	public static void verifyImageExists(String imageName) throws IOException, ParserConfigurationException, SAXException
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
	public static void clickOnAlert() throws IOException, ParserConfigurationException, SAXException {
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
	public static String monthNameIn3Letters(int month)
	{
		DateFormat formatter = new SimpleDateFormat("MMM", Locale.US);
	    GregorianCalendar calendar = new GregorianCalendar();
	    calendar.set(Calendar.DAY_OF_MONTH, 1);
	    calendar.set(Calendar.MONTH, month-1);
	    return formatter.format(calendar.getTime());
	    
	}
	public static String getMeetingDatetHour(String dateNewMeeting,String hour) throws ParseException
	{
		//<StartHour>02:00 PM</StartHour>
		String res="";
		String newDateNewMeeting[]=dateNewMeeting.split("/");
		res+=monthNameIn3Letters(Integer.parseInt(newDateNewMeeting[1]));//res="Nov"
		res+=" "+newDateNewMeeting[0]+", "+newDateNewMeeting[2]+", ";//res="Nov 11, 2018, "
		String hourSplit[]=hour.split(" ");//"02:00 PM"
		String hourNumber=hourSplit[0];//"02:00"
		String hourSpiltMinute[]=hourNumber.split(":");//"02","00"
		String hourAfterSplit=hourSpiltMinute[0];//"02"
		String minuteAfterSplit=hourSpiltMinute[1];//"00"
		int hourInt=Integer.parseInt(hourAfterSplit);//2
		String convertedHour=String.valueOf(hourInt)+":"+minuteAfterSplit+":00";//"2:00:00"
		res+=convertedHour+" "+hourSplit[1];//res="Nov 11, 2018, 2:00:00 PM"
		return res;
	}
	public static String getTodayString()
	{
		//Monday Jan 22, 2018
		DateFormat dateFormat = new SimpleDateFormat("EEEE MMM dd, yyyy");
		Date date = new Date();
		String day=dateFormat.format(date);
		return day;
	}
	private static void drawElemet(WebElement element)
	{
		((JavascriptExecutor)driver).executeScript("arguments[0].style.border='2px solid blue'",element);
	}
	public static void waitForElementToBeVisible(WebElement element,String elementName) throws IOException, ParserConfigurationException, SAXException
	{
		try
		{
			WebDriverWait wait=new WebDriverWait(driver, 5);
			wait.until(ExpectedConditions.visibilityOf(element));
			stepPass("element "+elementName+" is visible");
		}
		catch (Exception e) {
			stepFail("element "+elementName+" is not visible");
			failOfTestCase(e.getMessage());
		}
	}
	public static void waitForElementToBeClickable(WebElement element,String elementName) throws IOException, ParserConfigurationException, SAXException
	{
		try
		{
			WebDriverWait wait=new WebDriverWait(driver, 5);
			wait.until(ExpectedConditions.elementToBeClickable(element));
			stepPass("element "+elementName+" is clickable");
		}
		catch (Exception e) {
			stepFail("element "+elementName+" is not clickable");
			failOfTestCase(e.getMessage());
		}
	}
	public static void moveToElement(WebElement element,String elementName) throws IOException, ParserConfigurationException, SAXException
	{
		try {
			Actions act = new Actions(driver);
			act.moveToElement(element).build().perform();
			stepPass("Moved to element "+elementName);
		}
		catch (Exception e) {
			stepFail("didnt Moved to element "+elementName);
			failOfTestCase(e.getMessage());
			// TODO: handle exception
		}
	}
	public static void moveToOpenWindow(WebDriver driver) throws IOException, ParserConfigurationException, SAXException {
		try {
			Set<String> handles= driver.getWindowHandles();
			for(String handle:handles) {
				driver.switchTo().window(handle);
			}
			stepPass("Moved to opened window");
		}
		catch (Exception e) {
			stepFail("Didnt moved to opened window");
			failOfTestCase(e.getMessage());
		}
	}
}
