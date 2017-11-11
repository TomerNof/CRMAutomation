package PageObjects;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.xml.sax.SAXException;

import Utilites.*;

public class MainPageCrm  extends Base
{
	public WebDriver driver;
	
	@FindBy(how = How.NAME, using = "mainpanel")
	private WebElement frame;
	
	@FindBy(how=How.XPATH,using="//*[contains(text(),'User:')]")
	private WebElement userNameTitle;
	
	@FindBy(how=How.XPATH,using="//a[contains(text(),'Logout')]")
	private WebElement logout;
	
	@FindBy(how=How.XPATH,using="//*[@id=\"navmenu\"]/ul/li[2]/a")
	private WebElement calendar;
	
	@FindBy(how=How.XPATH,using="//*[@id='navmenu']/ul/li[3]/a")
	private WebElement company;
	
	@FindBy(how=How.XPATH,using="//*[@id='navmenu']/ul/li[4]/a")
	private WebElement contact;
	
	public MainPageCrm(WebDriver driver)
	{
		this.driver = driver;
	}
	private void moveToMainFrame()
	{
		try
		{
			driver.switchTo().defaultContent();
			for(WebElement f:driver.findElements(By.tagName("frame")))
			{
				
				if(f.getAttribute("name").equals(this.frame.getAttribute("name")))
				{
					driver.switchTo().frame(f);
					break;
				}
			}
			//stepPass("moved to right frame in Main page crm");
		}
		catch(Exception e)
		{
			failOfTestCase(e.getMessage());
		}
		
	}
	public WebElement getUserName() throws IOException, ParserConfigurationException, SAXException
	{
		WebElement x=null;
		try
		{
			moveToMainFrame();
			x=this.userNameTitle;
			stepPass("User name in main page="+this.userNameTitle.getText());
		}
		catch(Exception e)
		{
			stepFail("Didn't found user name "+getData("UserName"));
			failOfTestCase(e.getMessage());
		}
		return x;
		
	}
	public void clickLogout() throws IOException, ParserConfigurationException, SAXException
	{
		
		try{
			moveToMainFrame();
			logout.click();
			stepPass("click on logout");
		}
		catch(Exception e)
		{
			stepFail(e.getMessage());
			failOfTestCase(e.getMessage());
		}
		
	}
	public void clickCalenderTab() throws IOException, ParserConfigurationException, SAXException
	{
		
		try{
			moveToMainFrame();
			calendar.click();
			stepPass("click on calander");
		}
		catch(Exception e)
		{
			stepFail("Didnt click on calendar");
			failOfTestCase(e.getMessage());
		}
		
	}
	public void clickCompanyTab() throws IOException, ParserConfigurationException, SAXException
	{
		
		try{
			moveToMainFrame();
			company.click();
			stepPass("click on company");
		}
		catch(Exception e)
		{
			stepFail("Didnt click on company");
			failOfTestCase(e.getMessage());
		}
		
	}
	
	public void clickContactTab() throws IOException, ParserConfigurationException, SAXException
	{
		
		try{
			moveToMainFrame();
			contact.click();
			stepPass("click on contact");
		}
		catch(Exception e)
		{
			stepFail("Didnt click on company");
			failOfTestCase(e.getMessage());
		}
		
	}
}
