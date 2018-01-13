package PageObjects;

import org.openqa.selenium.support.PageFactory;

import Utilites.Base;

public class ManagePage extends Base{
	public static void init()
	{
		loginPage= PageFactory.initElements(driver, LoginPage.class);
		mainPageCrm = PageFactory.initElements(driver, MainPageCrm.class);
		
		//For meeting test case
		calendarPage=PageFactory.initElements(driver, CalendarPage.class);
		hoursPage=PageFactory.initElements(driver, HoursPage.class);
		eventPage=PageFactory.initElements(driver, EventPage.class);
		eventDetailsPage=PageFactory.initElements(driver, EventDetailsPage.class);
		
		//For company test case
		companyPage=PageFactory.initElements(driver, CompanyPage.class);
		createCompanyPage=PageFactory.initElements(driver,CreateCompanyPage.class);
		companyDetailsPage=PageFactory.initElements(driver, CompanyDetailsPage.class);
	
		//For contact test case
		contactsPage=PageFactory.initElements(driver, ContactsPage.class);
		contactInformationPage=PageFactory.initElements(driver, ContactInformationPage.class);
		contactDetailsPage=PageFactory.initElements(driver, ContactDetailsPage.class);

	}
}
