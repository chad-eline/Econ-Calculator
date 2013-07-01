/**This class represents the data model object for the a monopoly market.*/
package econ.model;

public class Monopoly_Model extends Model{

	//identify data fields for this model
	private double profit;
	private double revenue;
	private double price;
	private double quantity;
	private double marginalCost;
	private double marginalRevenue;
	private double demandSlope;
	private double demandIntercept;

	//default no arg constructor
	public Monopoly_Model(){
	}
	
	//constructor with params
	public Monopoly_Model(double demandSlope, double demandIntercept){
		this.demandSlope = demandSlope; 
		this.demandIntercept = demandIntercept;
	}

	//Methods
	
	//Calculates the monopoly price using data model parameters
	public double calcPrice(){
		return (this.demandIntercept + this.marginalCost)/2;
	}
	
	/**Calculates the monopoly prices given the input parameters.
	 * This method may be useful for populating a series for a
	 * dataset as you can loop through it and call I over and over
	 * again.*/
	public double calcPrice(double demandIntercept, double marginalCost){
		return (demandIntercept + marginalCost)/2;
	}
	
	/**Calculates the monopoly quantity*/
	public double calcQuantity(){
		return ((this.demandIntercept - this.marginalCost) / (2 * this.demandSlope));
	}
	
	/**Calculates the monopoly quantity given the input parameters.*/
	public double calcQuantity(double demandIntercept, double marginalCost, double demandSlope){
		return ((demandIntercept - marginalCost) / (2 * demandSlope));
	}

	public double calcMarginalRevenue(){
		return this.demandIntercept - (2 * this.demandSlope * this.quantity);
	}
	
	//TODO: possibly remove if there is no use case
	public double calcMarginalRevenue(double demandIntercept, double demandSlope, double quantity){
		return demandIntercept - (2 * demandSlope * quantity);
	}
	
	public static void main(String[] args){
		
	}
}
