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


public class TestCreateDisplayFields 
{
	public static void main(String[] args)
	{
	// Start Timer
		long startTime, elapsedTime;  // for speed benchmarking
        startTime = System.nanoTime();

		String display = "";
		String[] arrayLine1,arrayLine2,arrayLine3,arrayLine4,arrayLine5,arrayLine6,arrayLine7,arrayLine8,arrayLine9,arrayLine10,arrayLine11,arrayLine12,arrayLine13,arrayLine14,arrayLine15,arrayLine16;
		javax.swing.UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("FangSong", Font.PLAIN, 13)));
		
		CreateDisplayFields c1 = new CreateDisplayFields();
		
		
		//String dire = JOptionPane.showInputDialog("Enter the directory");
		//String file = JOptionPane.showInputDialog("Enter the file name");
		String dire;
		String file;
		
// Testing purposes
		dire = "C:/Work/99999/";		// Testing
		file = "11073PC1.DP";			// Testing
		
// Set the file input stream
		c1.setFileInputStream(dire, file);

// Set the location of the state
		c1.setStateLocation(620, 2);
	
		
//		SAMOESD
		c1.setFileInputStream(dire,file);
		String SAMOESD = c1.pullTarget((106-1),7,"SAMOESD",false);
			if(SAMOESD.equalsIgnoreCase(""))
			{
				c1.setFileInputStream(dire,file);
				SAMOESD = c1.pullTarget((645-1),1,"4",false);
			}
		arrayLine13 = c1.createRecord(SAMOESD);
		c1.addToPullFile(SAMOESD);
		
//		display = "SCA Seed\n";
		c1.setFileInputStream(dire,file);
		String SCAseed = c1.pullTarget((531-1),17,"11200 Waples Mill",false);
			if(SCAseed.equalsIgnoreCase(""))
			{
				c1.setFileInputStream(dire,file);
				SCAseed = c1.pullTarget((645-1),1,"4",false);
			}
		arrayLine11 = c1.createRecord(SCAseed);
		c1.addToPullFile(SCAseed);
		
//		display = "DOW Seed\n";
		c1.setFileInputStream(dire,file);
		String DOWseed = c1.pullTarget((741-1),3,"XXX",false);
			c1.addToList(c1.getState(DOWseed));
			arrayLine12 = c1.createRecord(DOWseed);
		c1.addToPullFile(DOWseed);
			
//		4 Lines
		c1.setFileInputStream(dire,file);
		String FourLines = c1.pullTarget((645-1),1,"4",true);
			if(FourLines.equalsIgnoreCase(""))
			{
				c1.setFileInputStream(dire,file);
				FourLines = c1.pullTarget((645-1),1,"3",false);
			}
		//c1.addToList(c1.getState(FourLines));
		arrayLine14 = c1.createRecord(FourLines);
		c1.addToPullFile(FourLines);
		
//		5 Lines
		c1.setFileInputStream(dire,file);
		String FiveLines = c1.pullTarget((645-1),1,"5",true);
			if(FiveLines.equalsIgnoreCase(""))
			{
				c1.setFileInputStream(dire,file);
				FiveLines = c1.pullTarget((645-1),1,"3",false);
			}
		//c1.addToList(c1.getState(FiveLines));
		arrayLine15 = c1.createRecord(FiveLines);
		c1.addToPullFile(FiveLines);	

//		5 Lines Second Time
		c1.setFileInputStream(dire,file);
		String LastLines = c1.pullTarget((645-1),1,"5",true);
			if(LastLines.equalsIgnoreCase(""))
			{
				c1.setFileInputStream(dire,file);
				LastLines = c1.pullTarget((645-1),1,"4",false);
					if(LastLines.equalsIgnoreCase(""))
					{
						c1.setFileInputStream(dire,file);
						LastLines = c1.pullTarget((645-1),1,"3",false);
					}
			}
		//c1.addToList(c1.getState(FiveLines));
		arrayLine16 = c1.createRecord(LastLines);
		c1.addToPullFile(LastLines);	
		
//		display = "Min $15\n";
		c1.setFileInputStream(dire,file);
		String min = c1.pullTarget((1116-1),2,"15",true);
			c1.addToList(c1.getState(min));
			arrayLine9 = c1.createRecord(min);
			c1.addToPullFile(min);		
		
//		display = "Max $100\n";
		c1.setFileInputStream(dire,file);
		String max = c1.pullTarget((1116-1),3,"100",true);
			c1.addToList(c1.getState(max));
			arrayLine10 = c1.createRecord(max);
			c1.addToPullFile(max);
			
//		display = "code1\n";
		String code1 = c1.pullTarget((642-1),1,"1",true);
			c1.addToList(c1.getState(code1));
			arrayLine1 = c1.createRecord(code1);
			c1.addToPullFile(code1);
			
//		display = "code2\n";
		c1.setFileInputStream(dire,file);
		String code2 = c1.pullTarget((642-1),1,"2",true);
			c1.addToList(c1.getState(code2));
			arrayLine2 = c1.createRecord(code2);
			c1.addToPullFile(code2);
			
//		display = "code3\n";
		c1.setFileInputStream(dire,file);
		String code3 = c1.pullTarget((642-1),1,"3",true);
			c1.addToList(c1.getState(code3));
			arrayLine3 = c1.createRecord(code3);
			c1.addToPullFile(code3);

//		display = "code4\n";
		c1.setFileInputStream(dire,file);
		String code4 = c1.pullTarget((642-1),1,"4",true);
			c1.addToList(c1.getState(code4));
			arrayLine4 = c1.createRecord(code4);
			c1.addToPullFile(code4);

//		display = "code5\n";
		c1.setFileInputStream(dire,file);
		String code5 = c1.pullTarget((642-1),1,"5",true);
			c1.addToList(c1.getState(code5));
			arrayLine5 = c1.createRecord(code5);
			c1.addToPullFile(code5);

//		display = "code6\n";
		c1.setFileInputStream(dire,file);
		String code6 = c1.pullTarget((642-1),1,"6",true);
			c1.addToList(c1.getState(code6));
			arrayLine6 = c1.createRecord(code6);
			c1.addToPullFile(code6);

//		display = "code7\n";
		c1.setFileInputStream(dire,file);
		String code7 = c1.pullTarget((642-1),1,"7",true);
			c1.addToList(c1.getState(code7));
			arrayLine7 = c1.createRecord(code7);
			c1.addToPullFile(code7);

//		display = "code8\n";
		c1.setFileInputStream(dire,file);
		String code8 = c1.pullTarget((642-1),1,"8",true);
			c1.addToList(c1.getState(code8));
			arrayLine8 = c1.createRecord(code8);
			c1.addToPullFile(code8);
			
			
			
// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

	
			
	//Code 1
		display = "";
		for(int i = 0; arrayLine1[i] != null; i++)
			display = display + arrayLine1[i] + "\n";
		//c1.addToPullFile(display);		
			
	//Code 2
		display = "";
		for(int i = 0; arrayLine2[i] != null; i++)
			display = display + arrayLine2[i] + "\n";
		//c1.addToPullFile(display);
		
	//Code 3
		display = "";
		for(int i = 0; arrayLine3[i] != null; i++)
			display = display + arrayLine3[i] + "\n";
		//c1.addToPullFile(display);		

	//Code 4
		display = "";
		for(int i = 0; arrayLine4[i] != null; i++)
			display = display + arrayLine4[i] + "\n";
		//c1.addToPullFile(display);		

	//Code 5
		display = "";
		for(int i = 0; arrayLine5[i] != null; i++)
			display = display + arrayLine5[i] + "\n";
		//c1.addToPullFile(display);		

	//Code 6
		display = "";
		for(int i = 0; arrayLine6[i] != null; i++)
			display = display + arrayLine6[i] + "\n";
		//c1.addToPullFile(display);		

	//Code 7
		display = "";
		for(int i = 0; arrayLine7[i] != null; i++)
			display = display + arrayLine7[i] + "\n";
		//c1.addToPullFile(display);		

	//Code 8
		display = "";
		for(int i = 0; arrayLine8[i] != null; i++)
			display = display + arrayLine8[i] + "\n";
		//c1.addToPullFile(display);		

	//Minnimum Dollar
		display = "";
		for(int i = 0; arrayLine9[i] != null; i++)
			display = display + arrayLine9[i] + "\n";
		//c1.addToPullFile(display);		

	//Maximum Dollar
		display = "";
		for(int i = 0; arrayLine10[i] != null; i++)
			display = display + arrayLine10[i] + "\n";
		//c1.addToPullFile(display);		

	//Seed 1
		display = "";
		for(int i = 0; arrayLine11[i] != null; i++)
			display = display + arrayLine11[i] + "\n";
		//c1.addToPullFile(display);		

	//Seed 2
		display = "";
		for(int i = 0; arrayLine12[i] != null; i++)
			display = display + arrayLine12[i] + "\n";
		//c1.addToPullFile(display);		

	//SAMOESD Seed or Random
		display = "";
		for(int i = 0; arrayLine13[i] != null; i++)
			display = display + arrayLine13[i] + "\n";
		//c1.addToPullFile(display);		

	// 4 Liner or 3 Liner
		display = "";
		for(int i = 0; arrayLine14[i] != null; i++)
			display = display + arrayLine14[i] + "\n";
		//c1.addToPullFile(display);		
		
	// 5 Liner or 3 Liner
		display = "";
		for(int i = 0; arrayLine15[i] != null; i++)
			display = display + arrayLine15[i] + "\n";
		//c1.addToPullFile(display);		

	// 5 Liner or 3 Liner
		display = "";
		for(int i = 0; arrayLine16[i] != null; i++)
			display = display + arrayLine16[i] + "\n";
	//c1.addToPullFile(display);		

		
	String[] pulledNames = c1.getPullData();
	
	System.out.println("Length = " + pulledNames.length);

	
// Show the user all the pulled names
	//for(int i = 0; pulledNames[i] != null; i++)
		//JOptionPane.showMessageDialog(null, pulledNames[i]);
	
	dire = "C:/Work/99999/";		// Testing
	file = "TestPC1.DAT";			// Testing
	c1.createTestFile(pulledNames, dire, file,1);

	
	
	elapsedTime = System.nanoTime() - startTime;
	System.out.println("Elapsed Time is " + (elapsedTime / 1000000000.0) + " sec");

		
	}
}
