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
		System.out.println("Starting execution");
		JUnitCore.main("bussnessLogic.CRM");
		System.out.println("Ending execution");
	}
}
