/**This class represents the data model object for the a monopoly market.
 * The monopoly model is driven off of 2 basic curves. The total cost curve 
 * and the inverse demand curve (see formulas below). For the initial versions 
 * of this class we will use those as the inputs collects from monopolot_gui class.
 * This class allows for both linear and quadratic cost curves.
 * 
 * Inverse Demand curve: P = a-b*Q 
 * Total Cost Curve: TC = f + c*Q + dQ^2
 **/
package econ.model;

public class Monopoly_Model extends Model{
	//Axis and Chart Names
	private final String MODEL_TITLE = "Monopoly";
	private final String X_AXIS_TITLE = "Quantity";
	private final String Y_AXIS_TITLE = "Price";
	
	//identify data fields for this model
	private double profit;
	private double revenue;
	private double price;
	private double quantity;
	private double marginalCost;
	private double marginalRevenue;
	
	//inverse demand components, i.e. P = a - b * Q
	private double demandSlope;			//b
	private double demandIntercept;		//a
	
	//Total Cost componenets, i.e. TC = f + c*Q + dQ^2
	private double fixedCost;		//f, fixed cost, not relevant for the short run
	private double straightCost;	//c, straight line component for the Total Cost curve
	private double quadraticCost;	//d, quadratic cost component for the Total Cost curve

	//default no arg constructor
	public Monopoly_Model(){
	}
	
	//constructor with params
	public Monopoly_Model(double demandSlope, double demandIntercept){
		this.demandSlope = demandSlope; 
		this.demandIntercept = demandIntercept;
	}

	//Methods below this line
	
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

	/**Calculates the marginal revenue of the object*/
	public double calcMarginalRevenue(){
		return this.demandIntercept - (2 * this.demandSlope * this.quantity);
	}
	
	/**Returns the marginal reveneue given */
	public double calcMarginalRevenue(double demandIntercept, double demandSlope, double quantity){
		return demandIntercept - (2 * demandSlope * quantity);
	}
	
	/**Calculates the marginal cost of the model, given the constructor inputs*/
	public double calcMarginalCost(){
		return this.marginalCost = (this.straightCost + ((2 * this.quadraticCost) * this.quantity));
	}
	
	/**Calculates the marginal cost of the model, given the constructor inputs*/
	public double calcMarginalCost(double straightCost, double quadraticCost, double quantity){
		double MC = (straightCost + ((2 * quadraticCost) * quantity)); 		//MC=c+2*d*Q
		return MC;
	}
}