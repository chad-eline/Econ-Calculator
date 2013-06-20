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
	
	//Descript Variables
	private String nameOfFirm;
	private boolean longTerm;

	//Variables for supply line
	private double slope;
	private double intercept;

	//Model Data Fields
	private double averageCost;
	private double averageRevenue;
	private double cost;
	private double fixedCost;
	private double marginalCost;
	private double marginalRevenue;
	private double marginalProfit;
	private double price;
	private double profit;
	private double revenue;
	private double quantity;

	//Default no arg's constructor
	public firm(){
	}

	//methods
	public double calcAverageCost(){
		averageCost = this.cost / this.quantity;
		return this.averageCost;
	}
	
	public double calcAverageRevenue(){
		averageRevenue = this.revenue / this.quantity;
		return this.averageRevenue;
	}
	
	public double calcCost(){ 

		return this.cost;
	}
	
	public double calcFixedCost(){ 

		return this.fixedCost;
	}
	
	public double calcMarginalCost(){ 

		return this.marginalCost;
	}
	
	public double calcMarginalRevenue(){ 

		return this.marginalRevenue;
	}
	
	public double calcMarginalProfit(){ 

		return this.marginalProfit;
	}
	
	public double calcPrice(){ 
		
		return this.price;
	}
	
	public double calcProfit(){ 
		return this.profit;
	}
	
	public double calcRevenue(){
		this.revenue = this.price*this.quantity;
		return this.revenue;
	}
	
	public double calcQuantity(){ 
		return null;
	}

	//getters and settters
	public double getSlope(){ 
		return this.slope;
	}

	public double getIntercept(){ 
		return this.intercept;
	}

	public void setSlope(doube slope){ 
		this.slope = slope;
	}

	public void setIntercept(doube intercept){ 
		this.intercept = intercept;
	}
}