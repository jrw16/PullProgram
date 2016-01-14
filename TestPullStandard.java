import java.awt.Font;
import javax.swing.JOptionPane;
import javax.swing.plaf.FontUIResource;


public class TestPullStandard 
{
	public static void main(String[] args)
	{
// Start Timer
		long startTime, elapsedTime;  // for speed benchmarking
        startTime = System.nanoTime();
        
// Instance variables
        String[] arrayLine;
        String pulledRecord, file, outfile;
		boolean confirmPull = false;

// Set the font for JOptionPane to FangSong 13 point
		javax.swing.UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("FangSong", Font.PLAIN, 13)));

// Create the "CreateDisplayFields" object
		CreateDisplayFields c1 = new CreateDisplayFields();
		

// --------------------------------------------------------------------------------------------------------------------------------------
// - Change this part for each job                                                                                                      -
// -                                                                                                                                    -
// Set the directory of file locations																									-
    	String jobNum = "99999";												// Testing												-
        String dire = "C:/Work/" + jobNum + "/";								// Testing												-
    	String inpExt = ".DP";													// Testing												-
    	String outExt = ".DAT";													// Testing												-
    	int numOfPanels = 2;
		int numSets = 2;
    	int[] recNums = null;
    	int recInd = 0;
// -                                                                                                                                    -
// -                                                                                                                                    -
// -                                                                                                                                    -
// Set the location of the state																										-
   		c1.setStateLocation(169, 2);//																									-
// -                                                                                                                                    -
// --------------------------------------------------------------------------------------------------------------------------------------
		
		
// Start loop
for(int t = 1; t < (numOfPanels+1); t++)
{
	
	// Setting file names
		file = "99999p" + String.valueOf(t) + inpExt;					// Testing
		outfile = "TestP" + String.valueOf(t) + outExt;				// Testing
		//file = "99999PB2" + inpExt;					// Testing
		//outfile = "TestPB2" + outExt;				// Testing
		
	// Array info for pulling names
		int[] pos = {176,27,23,23,24,24,206,206};						// Array of starting positions to pull
		int[] add = {1,1,2,2,1,1,1,1};									// The number past the starting position
		String[] tar = {" ","&","Ms","Mr","a",".","4","5"};				// The target for the pull from the data
		
		int[] pos2 = {176,27,23,307,24,24,206,206};						// Array of starting positions to pull second target
		int[] add2 = {1,1,2,2,1,1,1,1};									// The number past the starting position for the second target
		String[] tar2 = {" ","&","Ms","AA","a",".","4","5"};			// The second target to pull from the data
		
		boolean[] STcount = {true,true,true,true,true,true,true,true};	// true is the state matters, false means it doesn't

		
		for(int i = 0; i < pos.length; i++)
		{
			//if (i >= 7)
				//confirmPull = true;
			
			c1.setFileInputStream(dire, file);
			int reply = JOptionPane.YES_OPTION;
			
			do {
				//pulledRecord = c1.pullTarget((pos[i]-1),add[i],tar[i],STcount[i]);
				pulledRecord = c1.pullDualTarget((pos[i]-1) , add[i] , tar[i] , (pos2[i]-1) , add2[i] , tar2[i] , STcount[i]); // Testing out
			    
					//System.out.println(i + ": " + (pos[i]-1) + add[i]  + tar[i] + STcount[i]); // Debug
				arrayLine = c1.createRecord(pulledRecord);
				
				if(confirmPull)	{
					JOptionPane.showMessageDialog(null, c1.createDisplay(arrayLine));
					reply = JOptionPane.showConfirmDialog(null, "Do you want to keep this name?", "Pull Names", JOptionPane.YES_NO_OPTION);
				}
			}while(reply == JOptionPane.NO_OPTION && confirmPull);
			
			
			if(pulledRecord.equalsIgnoreCase("")) {	
				c1.setFileInputStream(dire, file);
				pulledRecord = c1.pullTarget((23-1),2,"Mr",false);	// The 201 pos for Title will change per data file
			}else if(pulledRecord.equalsIgnoreCase("")) {	
					c1.setFileInputStream(dire, file);
					pulledRecord = c1.pullTarget((23-1),2,"Ms",false);	// The 201 pos for Title will change per data file
					}
				
			c1.addToList(c1.getState(pulledRecord));	// Add State from pulled name to the state list
			c1.addToPullFile(pulledRecord);			// Add record to pulled list
			
			c1.getRecNum();
		}

	// Get record number of record pulled
		//recNums[recInd] = c1.getRecNum();
		//recInd++;
		
	// Pull the names into pulledNames Array
		String[] pulledNames = c1.getPullData();
		c1.clearPullData();
	
	// Add a number of sets to the TestAll file
		//c1.addTestAll(pulledNames, 2);  // Error code??

	// Write the records to an output file
		c1.createTestFile(pulledNames, dire, outfile, numSets);
		
} 

// End of for loop for panels
	

// Create the testAll file with all the panels
	//c1.createTestFile(c1.getTestAll(), dire, "TestAll.DAT", 1); // Getting the error code with addTestAll method

	elapsedTime = System.nanoTime() - startTime;
	System.out.println("Elapsed Time is " + (elapsedTime / 1000000000.0) + " sec");

	
	
	
	
	}
}
