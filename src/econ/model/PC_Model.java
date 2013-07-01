package econ.model;
import org.jfree.data.DomainOrder;
import org.jfree.data.general.DatasetChangeListener;
import org.jfree.data.general.DatasetGroup;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class PC_Model extends Model{
	
	//Axis and Chart Names
	private final String MODEL_TITLE = "Perfect Competition";
	private final String X_AXIS_TITLE = "Quantity";
	private final String Y_AXIS_TITLE = "Price";

	//private data fields for price and quantity
	private double demandSlope;
	private double demandIntercept;
	private double supplySlope;
	private double supplyIntercept;
	private double optimalPrice;
	private double optimalQuantity;
	private double consumerSurplus;		
	private double producerSurplus;
	private double wellfare;		
	private double deadWeighLoss;					

	//private data fields for supply series, demand series, and the dataset
	private XYSeries demandSeries = new XYSeries("Demand");
	private XYSeries supplySeries = new XYSeries("Supply");
	private XYDataset dataset;

	//default no arg constructor
	public PC_Model(){
	}

	/**This constructor takes the supply and demand intercept and slope,
	 * calculates the optimal quantity and price,
	 * creates a supply and demand series,
	 * and finally creates a dataset.*/
	public PC_Model(double supplySlope, double supplyIntercept, double demandSlope, double demandIntercept){
		
		//Pass the method args. to the object data fields
		this.supplySlope = supplySlope;
		this.supplyIntercept = supplyIntercept;
		this.demandSlope = demandSlope;
		this.demandIntercept = demandIntercept;

		//Call the calculate the price and qauntity methods and store the results in their respective datafields
		this.optimalPrice = calcOptimalPrice();
		this.optimalQuantity = calcOptimalQuantity();

		//Populate the Supply and demand series
		this.demandSeries = createDemandSeries();
		this.supplySeries = createSupplySeries();
		
		//Populate the dataset
		this.dataset = createDataset();
	}
	
	//Calculates optimal price 
	public double calcOptimalPrice(){
		return optimalPrice = ((demandIntercept * supplySlope) + (demandSlope * supplyIntercept)) / (demandSlope + supplySlope); 
	}

	//Calculates optimal quantity
	public double calcOptimalQuantity(){
		return optimalQuantity = ((demandIntercept - Math.abs(supplyIntercept)) / (demandSlope + supplySlope));
	}
	
	/**Calculates the consumer surplus for the Perfectly Competitive data model.*/
	public double calcConsumerSurplus(){
		return consumerSurplus = 0.5*(demandIntercept - optimalPrice) * optimalQuantity;
	}

	/**Calculates the producer surplus for the Perfectly Competitive data model*/
	public double calcProducerSurplus(){
		return producerSurplus = 0.5*(optimalPrice - supplyIntercept) * optimalQuantity;
	}	

	/**Calculates the wellfare for the Perfectly Competitive data model
	 * TODO: finish this method and debug*/
	public double calcWellfare(){
		return wellfare = consumerSurplus + producerSurplus;
	}	

	/**Calculates the dead weight loss for the Perfectly Competitive data model*/
	public double calcDdeadWeightLoss(){
		return deadWeighLoss = 0.5*(optimalPrice - supplyIntercept)*optimalQuantity;
	}	
	
	//createDemandSeries: Populates the Demand series for this model object
	public XYSeries createDemandSeries(){
		if(this.demandSeries.isEmpty() == false){
			System.out.println("DemandSeries is empty, it will be cleared");
			this.demandSeries.clear();
		}
		for(double p = this.demandIntercept; p >= 0; p--){
			double q = (this.demandIntercept - p)/this.demandSlope;
			this.demandSeries.add(q,p);
			//TODO: add a data point for q=0 that's dynamic
		}
		return this.demandSeries;
	}
	
	//createSupplySeries: Populates the supply series for this model object
	public XYSeries createSupplySeries(){

		//create temp vars
		double q = 0, p = 0;
		
		if(this.supplySeries.isEmpty() == false){
			System.out.println("SupplySeries is !empty, it will be cleared");	
		}
		while((q <= (1.2 * this.optimalQuantity)) || (p <= (1.2 * this.optimalPrice))){
			p = this.supplyIntercept + (this.supplySlope * q);
			this.supplySeries.add(q,p);
			q++;
		}
		return this.supplySeries;
	}
	
	/**createDataSet: Creates a dataset for the supply and demand series for this model object.
	 * TODO Consideration: perhaps remove this method later as you may want to create an ArrayList of
	 * PC_Model objects and add all their S & D curves to one data set, instead of trying to manage 
	 * multiple dataset's on same graph.*/
	public XYDataset createDataset(){
		XYSeriesCollection dataset = new XYSeriesCollection();

		//check if supplySeries is null
		if (this.supplySeries.isEmpty() == false){
			dataset.addSeries(this.supplySeries);
		}
		else{
			//TODO: Remove this from prod
			System.out.println("Supply Series is null");
		}
		
		if(this.demandSeries.isEmpty() == false){
			dataset.addSeries(this.demandSeries);			
		}
		else{
			//TODO: Remove this from prod
			System.out.println("Demand Series is null");
		}
		return dataset;
	}

	//	---	Getters and Setters	---
	public XYDataset getDataset() {
		return dataset;
	}

	public void setDataset(XYDataset dataset) {
		this.dataset = dataset;
	}	
	
	public XYSeries getDemandSeries() {
		return demandSeries;
	}

	public XYSeries getSupplySeries() {
		return supplySeries;
	}

	public void setDemandSeries(XYSeries demandSeries) {
		this.demandSeries = demandSeries;
	}

	public void setSupplySeries(XYSeries supplySeries) {
		this.supplySeries = supplySeries;
	}

	public double getSupplySlope() {
		return supplySlope;
	}

	public void setSupplySlope(double supplySlope) {
		this.supplySlope = supplySlope;
	}

	public double getSupplyIntercept() {
		return supplyIntercept;
	}

	public void setSupplyIntercept(double supplyIntercept) {
		this.supplyIntercept = supplyIntercept;
	}

	public double getDemandSlope() {
		return demandSlope;
	}

	public void setDemandSlope(double demandSlope) {
		this.demandSlope = demandSlope;
	}

	public double getDemandIntercept() {
		return demandIntercept;
	}

	public void setDemandIntercept(double demandIntercept) {
		this.demandIntercept = demandIntercept;
	}

	public double getOptimal_Price() {
		return optimalPrice;
	}

	public void setOptimal_Price(double optimal_Price) {
		this.optimalPrice = optimal_Price;
	}

	public double getOptimal_Quantity() {
		return optimalQuantity;
	}

	public void setOptimal_Quantity(double optimal_Quantity) {
		this.optimalQuantity = optimal_Quantity;
	}

	public String getModelName() {
		return MODEL_TITLE;
	}

	public String getXAxisName() {
		return X_AXIS_TITLE;
	}

	public String getYAxisName() {
		return Y_AXIS_TITLE;
	}
	
	
}