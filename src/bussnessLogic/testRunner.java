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
		Format formatter = new SimpleDateFormat("MMM dd YYYY");
		
		String s = formatter.format(new Date());
		
		System.out.println(s);
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
		String dateInString = "31-08-1982 10:20:56";
		
		Date date = sdf.parse(dateInString);
		System.out.println(date); //Tue Aug 31 10:20:56 SGT 1982

//		System.out.println("Starting execution");
//		JUnitCore.main("bussnessLogic.CRM");
//		System.out.println("Ending execution");
		
//		File x=new File(System.getProperty("user.dir")+"\\drivers\\chromedriver.exe");
//		System.out.println(x.exists());
//		File dir=new File(System.getProperty("user.dir")+"\\drivers");
//		for(File f:dir.listFiles())
//		{
//			System.out.println(f.getName());
//		}
		
	}
}
