package core;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;


public class CreateDisplayFields 
{
	
	// Variables *-*-*-*
	
	int lineLength = 0, recLength = 0, numOfLines = 0, temp = 0, lastLine = 0, numOfTimes = 0, index = 0;
	int stateBeg = 0, stateLen = 0, dataIndex = 0;
	String line = " ";
	
	BufferedReader br;
	
	String[] listOfData = new String[64];
	String[] dataFile = new String[17];
	
	String[] testAll;
	int testIndex = 0;
	
	int recNum;
	
	ArrayList<String> tempArray = new ArrayList<String>();
	
	// Constructors *-*-*-*
	
	public CreateDisplayFields()
	{
		
	}
	
	public CreateDisplayFields(int numberOfFields)
	{
		
		
	}
	
	
	
	
	// Methods *-*-*-*-*-*-*-*
	// ---------------------------------- Methods --------------------------------------
	
	public void setRecordLength(String passedLine)
	{
		recLength = passedLine.length() + 2;
	}
	public int getRecordLength()
	{
		return recLength;
	}
	
	public void sortToGroups()
	{
		
	}
	
	public void setFileInputStream(String passedDirectory, String passedFileName)
	{
		try	{
				br = new BufferedReader(new FileReader(passedDirectory + passedFileName));
			} catch (Exception ex){
				ex.printStackTrace();
				}
	}
	
	public void addToPullFile(String passedLine)
	{
		dataFile[dataIndex] = passedLine;
		dataIndex++;
	}
	public String[] getPullData()
	{
		return dataFile;
	}
	public void clearPullData()
	{
		dataFile = new String[16]; 
		dataIndex = 0;
	}
		
	public void createPulledFile(int[] passedRecNums)
	{
		int recNum = 1;
		try{
			String tempLine = null;
			while( (tempLine = br.readLine()) != null){
				for(int ind = 0; ind < passedRecNums.length; ind++)
				{
					if(recNum == passedRecNums[ind])
					{
						dataFile[ind] = tempLine;
					}
				}
				recNum++;
				}
			}catch(Exception e){
				System.out.println("ERROR\n" + e);
				}
	}

	public String getNextLine()
	{
		try{
			while(br.readLine() != null){
				return (br.readLine());
				}
			}catch(Exception e){
				System.out.println(e);
				}
		return null;
	}
	
	/**
	 * Create Record Method
	 * Takes a string and parses it into an array of 100 bytes for each line
	 * @param passedline which is a long record length
	 * @return the array of the string split out
	 */
	public String[] createRecord(String passedline)
	{
		String[] ary = new String[40];
		numOfLines = passedline.length() / 100;
		lastLine = passedline.length() % 100;
		temp = 0;
		int i = 0;
		for(i = 0; i < numOfLines; i++)
		{
			ary[i] = passedline.substring(temp, temp+100);
			temp = temp + 100;
		}
		ary[i] = passedline.substring(temp, temp+lastLine);
		return ary;
	}
	
	public void addToList(String passedString)
	{
		listOfData[index] = passedString;
		index++;
	}
	
	public String getState(String passedString)
	{
		String tempLine = passedString.substring(stateBeg, stateLen);
		return (tempLine);
	}
	
	public void setStateLocation(int passedBeg, int passedLen)
	{
		stateBeg = passedBeg;
		stateLen = passedBeg+passedLen;
	}
		
	public boolean stateNotUsed(String passedState)
	{
		if (listOfData.length > 0)
		{
			for(int i = 0; i < listOfData.length; i++)
			{
				if (passedState.equalsIgnoreCase(listOfData[i]))
					return false;
			}
		}
		return true;
	}
	
	public void setRecNum(int passRecNum)
	{
		recNum = passRecNum;
	}
	public int getRecNum()
	{
		return recNum;
	}
	
	public String pullTarget(int beg, int add, String target, boolean stateMatters)
	{
		int recNumCount = 0;
		String tempLine = " ";
		try
	    {
			
			while( br.readLine() != null)
	        {
				line = br.readLine();
				
				if(line == null)									// Debug
					break;
				//System.out.println("line is null! : " + line); 	// Debug
		     	
				recNumCount++;
            	if(line.substring(beg, beg+add).equalsIgnoreCase(target))
	        	{
	        		if(stateMatters)
	        		{
	        			tempLine = getState(line);
		        		if(stateNotUsed(tempLine))
		        		{
		        			setRecNum(recNumCount);
		        			return line;
		        		}
	        		}else
		        		{
	        				setRecNum(recNumCount);
		        			return line;
		        		}
	        	}
	        }
			
			setRecNum(recNumCount);
			return "";
		} catch(Exception e)
	 	   {
			System.out.println("line: " + line);
	     	return "";
	 	   }
	}

	public String pullDualTarget(int beg, int add, String target, int beg2, int add2, String target2, boolean stateMatters)
	{
		int recNumCount = 0;
		String tempLine = " ";
		try
	    {
			while( br.readLine() != null)
	        {
				line = br.readLine();
				if(line == null)
					break;
				recNumCount++;
            	if( (line.substring(beg, beg+add).equalsIgnoreCase(target)) && (line.substring(beg2, beg2+add2).equalsIgnoreCase(target2)) )
	        	{
	        		if(stateMatters)
	        		{
	        			tempLine = getState(line);
		        		if(stateNotUsed(tempLine))
		        		{
		        			setRecNum(recNumCount);
		        			return line;
		        		}
	        		}else
		        		{
	        				setRecNum(recNumCount);
		        			return line;
		        		}
	        	}
	        }
			setRecNum(recNumCount);
			return "";
		} catch(Exception e)
	 	   {
			System.out.println("line: " + line);
	     	return "";
	 	   }
	}
	
	public String createDisplay(String[] passedArray)
	{
		String _d = "";
		for(int i = 0; passedArray[i] != null; i++)
			_d = _d + passedArray[i] + "\n";
		return _d;
	}
	
	public boolean createTestFile(String[] passedRecords, String passedDirectory, String passedFileName, int sets)
	{
		int i = 0;
		
		try 
		{
			File file = new File(passedDirectory + passedFileName);
			
 			if (!(file.exists())) 
			{
				file.createNewFile();
			}
 			
 			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			
			for(int s = 0; s < sets; s++)
			{
				for(i = 0; passedRecords[i] != null; i++)
				{
					bw.append(passedRecords[i]);
					bw.newLine();
				}
			}
			bw.close();
			return true;
		} catch (IOException e) 
			{
				System.out.println("passedRecords[i] = " + passedRecords[i]);
				e.printStackTrace();
				return false;
			}
	}
	
	public void addTestAll(String[] passNames, int numSets)
	{
		for(int i = 0; i < numSets; i++)
		{
			for(int j = 0; passNames[j] != null; j++)
			{
				testAll[testIndex] = passNames[j];
				testIndex++;
			}
		}
	}
	
	public String[] getTestAll()
	{
		return testAll;
	}
	
    /**
     *  Read from the Excel file sent in from DMS
     */
	public void readExcelFile(String passedfileName)
	{
		String directory = "J:\\99999\\RAW\\"; // Testing only, remove when finished
		passedfileName = "test_11105.DAT";
		boolean checkColumnTwo = true;
		try
	    {
			Scanner inputStream = new Scanner(new File(directory+passedfileName));
	        String line = inputStream.nextLine();
			line = inputStream.nextLine();
		    int i = 0, k = 0;
	        while (i < 1)
	        {
	        	line = inputStream.nextLine();
	        	lineLength = line.length() + 2;
	        	System.out.println("The Line Length is: " + lineLength);
	        	//ary = line.split(",");
	     		//excelComName[k] = ary[0];
	     		//excelCodeCount[k] = Integer.parseInt(ary[1]);
	     		/*
	     		if(checkColumnTwo)
	     		{
	     			if(ary[5].equalsIgnoreCase("ZZZ"))
	     				checkColumnTwo = false;
	     			excelComName[k+29] = ary[5];
	     			excelCodeCount[k+29] = Integer.parseInt(ary[6]);
	         	}
	     		k++;
	     		*/
	     		i++;
	         }
	         inputStream.close( );
	  	 } catch(Exception e)
	 	   {
	     	 System.out.println(e);
	 	   }
	}
	
	public void copyFiles()
	{
	String inFileStr = "C:/Users/Joshua/workspace/Practice/src/BestOfHood.jpg";
	String outFileStr = "C:/Users/Joshua/workspace/Practice/src/BestOfHood_out1.jpg";
	// Print file length
	Path path1 = Paths.get(inFileStr);
	Path path2 = Paths.get(outFileStr);
	try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(inFileStr));)
	//BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(outFileStr))) 
	{
	System.out.println("File size is " + Files.size(path1));
	//int byteRead;
	//while ((byteRead = in.read()) != -1) 
	//{
	//out.write(byteRead);
	//}
	} catch (IOException ex) 
	{
	ex.printStackTrace();
	}
	}
	
	
	
}
