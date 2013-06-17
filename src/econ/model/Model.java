/**Top level generic class that will be super to all other model classes.
 * Allows for polymorphism between objects*/

package econ.model;

import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeriesCollection;

public class Model {
	
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
