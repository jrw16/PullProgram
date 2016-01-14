import java.awt.Font;

import javax.swing.JOptionPane;
import javax.swing.plaf.FontUIResource;


public class TestPullNSIC
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
		// Set the directory of file locations																							-
    	String jobNum = "99999";												// Testing												-
        String dire = "C:/Work/" + jobNum + "/";								// Testing												-
    	String inpExt = ".DP";													// Testing												-
    	String outExt = ".DAT";													// Testing												-
    	int titleStartPos = 23;
    	int numOfPanels = 1;
		int numSets = 1;
    	int[] recNums = new int[24];
    	int recInd = 0;
// -                                                                                                                                    -
// -                                                                                                                                    -
// -                                                                                                                                    -
// Set the location of the state																										-
   		c1.setStateLocation( (358-1) , 2 );//																									-
// -                                                                                                                                    -
// --------------------------------------------------------------------------------------------------------------------------------------
		
   		
		
// Start loop for each panel
   		
for(int t = 1; t < (numOfPanels+1); t++)
{
	
	// Setting file names
		//file = "11190p0" + String.valueOf(t) + inpExt;					// Testing
		//outfile = "TestP0" + String.valueOf(t) + outExt;				// Testing
		file = "99999p03" + inpExt;					// Testing
		outfile = "TestP03" + outExt;				// Testing
		
	// Array info for pulling names
		int[] pos = {365,27,843,843,24,24,384,384};						// Array of starting positions to pull
		int[] add = {1,1,3,3,1,1,1,1};									// The number past the starting position
		String[] tar = {" ","&","005","500","a",".","4","5"};				// The target for the pull from the data
		
		int[] pos2 = {472,472,843,843,24,24,384,384};						// Array of starting positions to pull second target
		int[] add2 = {3,3,3,3,1,1,1,1};									// The number past the starting position for the second target
		String[] tar2 = {"271","311","005","500","a",".","4","5"};			// The second target to pull from the data
		
		boolean[] STcount = {true,true,false,false,true,true,true,true};	// true is the state matters, false means it doesn't

		
		for(int i = 0; i < pos.length; i++)
		{
			//if (i >= 7)
				//confirmPull = true;
			
			c1.setFileInputStream(dire, file);
			int reply = JOptionPane.YES_OPTION;
			
			do {
				
				// *****************************************************************************************************************************
				// Single Pull
				//pulledRecord = c1.pullTarget( (pos[i]-1) , add[i] , tar[i] , STcount[i] );
				
				// Dual Pull
				pulledRecord = c1.pullDualTarget((pos[i]-1) , add[i] , tar[i] , (pos2[i]-1) , add2[i] , tar2[i] , STcount[i]);
			    // *****************************************************************************************************************************
				
				arrayLine = c1.createRecord(pulledRecord);
				
				if(confirmPull)	{
					JOptionPane.showMessageDialog(null, c1.createDisplay(arrayLine));
					reply = JOptionPane.showConfirmDialog(null, "Do you want to keep this name?", "Pull Names", JOptionPane.YES_NO_OPTION);
				}
			}while(reply == JOptionPane.NO_OPTION && confirmPull);
			
			
			if(pulledRecord.equalsIgnoreCase("")) {	
				c1.setFileInputStream(dire, file);
				pulledRecord = c1.pullTarget((titleStartPos-1),2,"Mr",true);	// The 201 pos for Title will change per data file
			}else if(pulledRecord.equalsIgnoreCase("")) {	
					c1.setFileInputStream(dire, file);
					pulledRecord = c1.pullTarget((titleStartPos-1),2,"Ms",true);	// The 201 pos for Title will change per data file
					}
				
			c1.addToList(c1.getState(pulledRecord));	// Add State from pulled name to the state list
			c1.addToPullFile(pulledRecord);			// Add record to pulled list
			
			// Get record number of record pulled
				recNums[recInd] = c1.getRecNum();
				recInd++;
				
		} // End of loop for pulling names
		
		
	// Pull the names into pulledNames Array
		String[] pulledNames = c1.getPullData();
		c1.clearPullData();
	
	// Add a number of sets to the TestAll file
		//c1.addTestAll(pulledNames, 2);  // Error code??

	// Write the records to an output file
		c1.createTestFile(pulledNames, dire, outfile, numSets);
		
}	// End of for loop for panels
	
	for(int i = 0; i < recNums.length; i++){
		System.out.println(recNums[i]);
	}


// Create the testAll file with all the panels
	//c1.createTestFile(c1.getTestAll(), dire, "TestAll.DAT", 1); // Getting the error code with addTestAll method

	elapsedTime = System.nanoTime() - startTime;
	System.out.println("Elapsed Time is " + (elapsedTime / 1000000000.0) + " sec");

	
	
	
	
	}
}
