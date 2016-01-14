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


public class TestPullHSP 
{
	public static void main(String[] args)
	{
	// Start Timer
		long startTime, elapsedTime;  // for speed benchmarking
        startTime = System.nanoTime();

for(int t = 1; t < 6; t++)
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
		dire = "C:/Work/11202/";										// Testing
		file = "11202p0" + String.valueOf(t) + ".DD4";					// Testing
		outfile = "TestP0" + String.valueOf(t) + ".DAT";				// Testing
		
		
// Set the file input stream
		c1.setFileInputStream(dire, file);

// Set the location of the state
		c1.setStateLocation(420, 2);

		
//		Invalid Zip Code ---------------------------------------------------------------------------------------------------------------------------------------

		int reply = JOptionPane.YES_OPTION;
		do
		{
			code1 = c1.pullTarget((205-1),1,"&",true);
			arrayLine1 = c1.createRecord(code1);
			JOptionPane.showMessageDialog(null, c1.createDisplay(arrayLine1));
			reply = JOptionPane.showConfirmDialog(null, "Do you want to keep this name?", "Pull ATA Names", JOptionPane.YES_NO_OPTION);
		}while(reply == JOptionPane.NO_OPTION);
		c1.addToList(c1.getState(code1));	// Add State from pulled name to the state list
		c1.addToPullFile(code1);			// Add record to pulled list
		
		
//		Longest Name Mr. & Mrs. ---------------------------------------------------------------------------------------------------------------------------------
		c1.setFileInputStream(dire,file);
		String code2 = c1.pullTarget((201-1),2,"Mr",true);
			if(code2.equalsIgnoreCase(""))
			{
				c1.setFileInputStream(dire,file);
				code2 = c1.pullTarget((201-1),2,"Ms",true);
			}
			c1.addToList(c1.getState(code2));
			arrayLine2 = c1.createRecord(code2);
			c1.addToPullFile(code2);
			
			
//		Title Mr. -----------------------------------------------------------------------------------------------------------------------------------------------
		c1.setFileInputStream(dire,file);
		String code3 = c1.pullTarget((202-1),1,"a",true);
			c1.addToList(c1.getState(code3));
			arrayLine3 = c1.createRecord(code3);
			c1.addToPullFile(code3);

			
//		display = "code5\n";
		c1.setFileInputStream(dire,file);
		String code4 = c1.pullTarget((202-1),1,".",true);
			c1.addToList(c1.getState(code4));
			arrayLine5 = c1.createRecord(code4);
			c1.addToPullFile(code4);

			
// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

			
	//Create Display Records
		//c1.createDisplay(arrayLine1);
		//c1.addToPullFile(display);		

		
		
	String[] pulledNames = c1.getPullData();
	
	c1.createTestFile(pulledNames, dire, outfile,1);

	
} // End of for loop for two panels
	
	elapsedTime = System.nanoTime() - startTime;
	System.out.println("Elapsed Time is " + (elapsedTime / 1000000000.0) + " sec");

		
	}
}
