package bussnessLogic;

import java.io.*;
import java.text.*;
import java.util.*;

import org.junit.runner.*;
import org.junit.runners.*;

@RunWith(Suite.class)
public class testRunner
{

	public static void main(String[] args) throws ParseException
	{
//		System.out.println("Starting execution");
//		JUnitCore.main("bussnessLogic.CRM");
//		System.out.println("Ending execution");
		
		Calendar calendar = Calendar.getInstance();
		Date date = calendar.getTime();
		// 3 letter name form of the day
		System.out.println(new SimpleDateFormat("EE", Locale.ENGLISH).format(date.getTime()));
		// full name form of the day
		System.out.println(new SimpleDateFormat("EEEE", Locale.ENGLISH).format(date.getTime()));
		
		DateFormat dateFormat = new SimpleDateFormat("EEEE MMM dd, yyyy");
		Date date2 = new Date();
		System.out.println(dateFormat.format(date2));
		

	}
}
