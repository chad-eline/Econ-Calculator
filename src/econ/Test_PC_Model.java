package econ;


/**
 * This class is used to test the PC_Model class to see if it works.
*/
public class Test_PC_Model{
	public static PC_Model model;
	public static void main(String[] args){
		
		model = new PC_Model(2, 50, 8, 100);
		System.out.println("Supply Intercept = " + model.getSupplyIntercept());
		System.out.println("Supply Slope = " + model.getSupplySlope());
		System.out.println("Demand Intercept = "+ model.getDemandIntercept());
		System.out.println("Demand Slope = "+ model.getDemandSlope());
		System.out.println("P* = " + model.getOptimal_Price());
		System.out.println("Q* = " + model.getOptimal_Quantity());
		
	}
}
