package core;

import java.awt.Font;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.JOptionPane;
import javax.swing.plaf.FontUIResource;


public class TestPullATA 
{
	public static void main(String[] args)
	{
	// Start Timer
		long startTime, elapsedTime;  // for speed benchmarking
        startTime = System.nanoTime();

for(int t = 1; t < 9; t++)
{
        
		String display = "";
		String[] arrayLine1,arrayLine2,arrayLine3,arrayLine4,arrayLine5,arrayLine6,arrayLine7,arrayLine8,arrayLine9,arrayLine10,arrayLine11,arrayLine12,arrayLine13,arrayLine14,arrayLine15,arrayLine16;
		String code1;
		javax.swing.UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("FangSong", Font.PLAIN, 13)));
		
		CreateDisplayFields c1 = new CreateDisplayFields();
		
		
		//String dire = JOptionPane.showInputDialog("Enter the directory");
		//String file = JOptionPane.showInputDialog("Enter the file name");
		String dire;
		String file;
		String outfile;
	

// Testing purposes
		dire = "C:/Work/99999/";						// Testing
		file = "11098P" + String.valueOf(t) + ".DP";						// Testing
		outfile = "TestP" + String.valueOf(t) + ".DAT";						// Testing
		
		
// Set the file input stream
		c1.setFileInputStream(dire, file);

// Set the location of the state
		c1.setStateLocation(169, 2);

		
//		Invalid Zip Code ---------------------------------------------------------------------------------------------------------------------------------------
		int reply = JOptionPane.YES_OPTION;
		do
		{
			code1 = c1.pullTarget((176-1),1," ",true);
			arrayLine1 = c1.createRecord(code1);
			JOptionPane.showMessageDialog(null, c1.createDisplay(arrayLine1));
			reply = JOptionPane.showConfirmDialog(null, "Do you want to keep this name?", "Pull ATA Names", JOptionPane.YES_NO_OPTION);
		}while(reply == JOptionPane.NO_OPTION);
		c1.addToList(c1.getState(code1));	// Add State from pulled name to the state list
		c1.addToPullFile(code1);			// Add record to pulled list
		
//		Longest Name Mr. & Mrs. ---------------------------------------------------------------------------------------------------------------------------------
		c1.setFileInputStream(dire,file);
		String code2 = c1.pullTarget((27-1),1,"&",true);
			c1.addToList(c1.getState(code2));
			arrayLine2 = c1.createRecord(code2);
			c1.addToPullFile(code2);
			
//		Title Mr. -----------------------------------------------------------------------------------------------------------------------------------------------
		c1.setFileInputStream(dire,file);
		String code3 = c1.pullTarget((23-1),2,"Mr",true);
			c1.addToList(c1.getState(code3));
			arrayLine3 = c1.createRecord(code3);
			c1.addToPullFile(code3);

//		display = "code4\n";
		c1.setFileInputStream(dire,file);
		String code4 = c1.pullTarget((23-1),2,"Ms",true);
			c1.addToList(c1.getState(code4));
			arrayLine4 = c1.createRecord(code4);
			c1.addToPullFile(code4);

//		display = "code5\n";
		c1.setFileInputStream(dire,file);
		String code5 = c1.pullTarget((24-1),1,"a",true);
			c1.addToList(c1.getState(code5));
			arrayLine5 = c1.createRecord(code5);
			c1.addToPullFile(code5);

//		display = "code6\n";
		c1.setFileInputStream(dire,file);
		String code6 = c1.pullTarget((24-1),1,".",true);
			c1.addToList(c1.getState(code6));
			arrayLine6 = c1.createRecord(code6);
			c1.addToPullFile(code6);

//		4 Lines or 3 Lines
		c1.setFileInputStream(dire,file);
		String FourLines = c1.pullTarget((386-1),1,"4",true);
			if(FourLines.equalsIgnoreCase(""))
			{
				c1.setFileInputStream(dire,file);
				FourLines = c1.pullTarget((386-1),1,"3",false);
			}
		c1.addToList(c1.getState(FourLines));
		arrayLine7 = c1.createRecord(FourLines);
		c1.addToPullFile(FourLines);
			
//		4 Lines or 3 Lines 2nd One
		c1.setFileInputStream(dire,file);
		String FiveLines = c1.pullTarget((386-1),1,"4",true);
			if(FiveLines.equalsIgnoreCase(""))
			{
				c1.setFileInputStream(dire,file);
				FiveLines = c1.pullTarget((386-1),1,"3",false);
			}
		c1.addToList(c1.getState(FiveLines));
		arrayLine8 = c1.createRecord(FiveLines);
		c1.addToPullFile(FiveLines);	

			
// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

			
	//Create Display Records
		c1.createDisplay(arrayLine1);
		//c1.addToPullFile(display);		
	
		
	String[] pulledNames = c1.getPullData();
	
	
	//System.out.println("Length = " + pulledNames.length);		// For testing out purposes
	
// Show the user all the pulled names							// For testing out printing out names
	//for(int i = 0; pulledNames[i] != null; i++)
		//JOptionPane.showMessageDialog(null, pulledNames[i]);
	
	
	c1.createTestFile(pulledNames, dire, outfile,1);

	
} // End of for loop for two panels
	
	elapsedTime = System.nanoTime() - startTime;
	System.out.println("Elapsed Time is " + (elapsedTime / 1000000000.0) + " sec");

		
	}
}
