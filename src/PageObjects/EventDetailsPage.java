package PageObjects;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.xml.parsers.ParserConfigurationException;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import org.xml.sax.SAXException;

import Utilites.*;

public class EventDetailsPage extends Base {
	private CommonOps comOps;
	private WebDriver driver;

	//@FindBy(how=How.XPATH,using="/html/body/table[2]/tbody/tr[1]/td[2]/table/tbody/tr/td/table[2]/tbody/tr[2]/td[1]/table/tbody/tr[1]/td[2]")
	@FindBy(how=How.XPATH,using="//strong[text()='Title']/../../td[2]")
	private WebElement titleDetails;
	
	//@FindBy(how=How.XPATH,using="/html/body/table[2]/tbody/tr[1]/td[2]/table/tbody/tr/td/table[2]/tbody/tr[2]/td[1]/table/tbody/tr[2]/td[2]")
	@FindBy(how=How.XPATH,using="//strong[text()='Starts']/../../td[2]")
	private WebElement startMeet;
	
	//@FindBy(how=How.XPATH,using="/html/body/table[2]/tbody/tr[1]/td[2]/table/tbody/tr/td/table[2]/tbody/tr[2]/td[1]/table/tbody/tr[3]/td[2]")
	@FindBy(how=How.XPATH,using="//strong[text()='Ends']/../../td[2]")
	private WebElement endMeet;
	
	//@FindBy(how=How.XPATH,using="/html/body/table[2]/tbody/tr[1]/td[2]/table/tbody/tr/td/table[2]/tbody/tr[2]/td[2]/table/tbody/tr[4]/td[2]")
	@FindBy(how=How.XPATH,using="//strong[text()='Assigned to']/../../td[2]")
	private WebElement assignTo;
	
	public EventDetailsPage(WebDriver driver) {
		this.driver=driver;
		comOps=new CommonOps();
	}
	
	public void validateNewMeeting(String titleDescription,String start,String end,String assignTo) throws IOException, ParserConfigurationException, SAXException
	{
		try
		{
			/////////////

			String stratMeeting=CommonOps.getMeetingDate(getData("DateNewMeeting"),getData("StartHour"));
			////////////////
			comOps.verifyValueExists(titleDetails, titleDescription);
			comOps.verifyValueExists(startMeet, start);
			comOps.verifyValueExists(endMeet, end);
			comOps.verifyValueExists(this.assignTo, assignTo);
			stepPass("Validated that "+titleDescription+", "+start+", "+end+", "+assignTo+" is in the validate form");
		}
		catch(Exception e)
		{
			stepFail("Wrong validate of meeting details");
			failOfTestCase(e.getMessage());
		}
	}
	private String shortMonthName()
	{
		String res="";
		String format2 = "MMM";
	    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	    Calendar cal = Calendar.getInstance();
		for(int i=0;i<12;i++)
		{
			cal = Calendar.getInstance();
				   cal.add(Calendar.MONTH, i);
			    Date date = cal.getTime();
			    SimpleDateFormat sdf =  new SimpleDateFormat(format2, Locale.ENGLISH);
			    System.out.println(sdf.format(date));

		}
		return res;
	}
}
