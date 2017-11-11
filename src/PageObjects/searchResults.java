package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import Utilites.*;

public class searchResults extends Base
{
	public WebDriver driver;
	
	@FindBy(how = How.CLASS_NAME, using = "mw-search-nonefound")
	public WebElement searchResult;
	
	public searchResults(WebDriver driver)
	{
		this.driver = driver;
	}
	
	
}
