package PageObjects;

import java.io.*;

import javax.xml.parsers.*;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.*;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.xml.sax.*;

import Utilites.*;

import com.relevantcodes.extentreports.LogStatus;

public class LoginPage extends Base
	{
		public WebDriver driver;
		
		@FindBy(how = How.NAME, using = "username")
		private WebElement userName;
		
		@FindBy(how = How.NAME, using = "password")
		private WebElement password;
		
		
		@FindBy(how = How.CSS, using ="input[class='btn btn-small']")
		private WebElement button;
		
		
		public LoginPage(WebDriver driver)
		{
			this.driver = driver;
		}
		
		public void loginUser(String userName,String password) throws IOException, ParserConfigurationException, SAXException
		{
			try
			{
				driver.switchTo().defaultContent();//for firefox;
				this.userName.clear();
				this.userName.sendKeys(userName);
				stepPass(userName+" inserted");
				this.password.clear();
				this.password.sendKeys(password);
				stepPass(password+" inserted");
				sleep();
				this.button.click();
				stepPass("button clicked");
				
			}
			catch(Exception e)
			{
				stepFail("Problem with login menu in login page");
				failOfTestCase(e.getMessage());
			}
			
		}
}
