package bussnessLogic;

import java.io.*;
import java.nio.file.*;

import org.junit.runner.*;
import org.junit.runners.*;

@RunWith(Suite.class)
public class testRunner
{
	public static void main(String[] args)
	{
		//This is my second comment
		//This will be shown after commit
		System.out.println("Starting execution");
		JUnitCore.main("bussnessLogic.CRM");
		System.out.println("Ending execution");
//		File x=new File(System.getProperty("user.dir")+"\\drivers\\chromedriver.exe");
//		System.out.println(x.exists());
//		File dir=new File(System.getProperty("user.dir")+"\\drivers");
//		for(File f:dir.listFiles())
//		{
//			System.out.println(f.getName());
//		}
		
	}
}
