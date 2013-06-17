/**This class will handle the logic for creating and gathering series into dataset's and then charts*/
package econ;

import java.util.ArrayList;

import org.jfree.data.general.Dataset;
import org.jfree.data.general.Series;

/**TODO: Migrate chart creation logic out of the model classes and centralize here*/
public class ChartSpawner{
	
	private ArrayList <Series> SeriesList;		//data structure for the series
	private Dataset dataset;					//

	//Default no arg constructor
	public ChartSpawner(){
	}
	
	//Methods
	
	
	/*main method, for testing remove later
	 * Want to remove all chart creation logic and functionality out of the pc_model obj and
	 * move to here so we can reuse this class for all the other models.
	 * TODO: Decide how to pass data to this class
	 * TODO:
	 */
	public static void main(String [] args){
		
		
		
	}

}
