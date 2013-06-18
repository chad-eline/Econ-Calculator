/**Class: Firm
 * Description: This class is a data structure meant to 
 * represent a firm in the classical sense of economics.
 * Since these classical economic models attempt to 
 * explain the relationships, interactions, and competitions 
 * between firms\players, I thought it would be best 
 * to create a 'firm' data model object to emulate 
 * these interactions.
 *
 * NOTE: This aproach may prove to be cumbersome and this class may become deprecated.
*/
package econ.model;

public class firm{
	
	private String nameOfFirm;

	//Main variables
	private double profit;
	private double price;
	private double quantity;
	private double slope;
	private double intercept;

	//Revenues
	private double revenue;
	private double averageRevenue;
	private double marginalRevenue;

	//Costs
	private double cost;
	private double averageCost;
	private double fixedCost;
	private double marginalCost;
	
	//Default no arg's constructor
	public firm(){
	}
	

}
