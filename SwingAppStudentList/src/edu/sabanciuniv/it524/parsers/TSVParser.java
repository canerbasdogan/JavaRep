package edu.sabanciuniv.it524.parsers;

import java.io.BufferedReader;
import java.io.FileReader;

public class TSVParser extends TextFileParser{

	@Override
	public String readFile(String filePath) {
		
		try {
			
			FileReader reader = new FileReader("students.tsv");
			BufferedReader bfr = new BufferedReader(reader);
			String allText="";
			while(true) 
			{
				String line = bfr.readLine();
				if (line == null)
					break;
				allText += line+"\n";
			}
			return allText;
	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
}
