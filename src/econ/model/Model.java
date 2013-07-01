/**Top level generic class that will be super to all other model classes.
 * Allows for polymorphism between objects*/

package econ.model;

import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeriesCollection;

public class Model {
	
	//Axis and Chart Names
	private final String MODEL_TITLE = "Model";
	private final String X_AXIS_TITLE = "Quantity";
	private final String Y_AXIS_TITLE = "Price";
	
	
	//	---	COnstructors	---
	
	/*Default no arg constructor*/
	public Model(){
	}

	//	---	Methods	---
	
	//creates a data set
	 public XYDataset createDataset(){
		 
		 //create a dataset
		 XYSeriesCollection dataset = new XYSeriesCollection();
		 
		 //check if any of the series are null
		 
		 return null; //change later
	 }
	
	//	---	Getters and setters	--
	
}
