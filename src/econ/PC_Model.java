package econ;
import org.jfree.data.DomainOrder;
import org.jfree.data.general.DatasetChangeListener;
import org.jfree.data.general.DatasetGroup;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**This is the clean version of the Perfect Competition data model.
TODO: add methods to handle the creation of the datasets and the series*/

class PC_Model extends Model{

	//private data fields for price and quantity
	private double demandSlope;
	private double demandIntercept;
	private double supplySlope;
	private double supplyIntercept;
	private double optimal_Price;
	private double optimal_Quantity;

	//private data fields for supply and demand series
	private XYSeries demandSeries = new XYSeries("Demand");
	private XYSeries supplySeries = new XYSeries("Supply");
	
	//private data field for dataset
	private XYDataset dataset;

	//default no arg constructor
	public PC_Model(){
	}

	//constructor that takes demand and supply slope and intercept
	public PC_Model(double supplySlope, double supplyIntercept, double demandSlope, double demandIntercept){
		
		//Pass the method args. to the object data fields
		this.supplySlope = supplySlope;
		this.supplyIntercept = supplyIntercept;
		this.demandSlope = demandSlope;
		this.demandIntercept = demandIntercept;

		//Call the calculate the price and qauntity methods and store the results in their respective datafields
		this.optimal_Price = calcPrice();
		this.optimal_Quantity = calcQuantity();

		//Populate the Supply and demand series
		this.demandSeries = createDemandSeries();
		this.supplySeries = createSupplySeries();
		
		//Populate the dataset
		this.dataset = createDataset();
	}
	
	//Calculates optimal price 
	public double calcPrice(){
		return optimal_Price = ((demandIntercept * supplySlope) + (demandSlope * supplyIntercept)) / (demandSlope + supplySlope); 
	}

	//Calculates optimal quantity
	public double calcQuantity(){
		return optimal_Quantity = ((demandIntercept - Math.abs(supplyIntercept)) / (demandSlope + supplySlope));
	}
	
	//createDemandSeries: Populates the Demand series for this model object
	public XYSeries createDemandSeries(){
		XYSeries demandSeries = new XYSeries("Demand");
		for(double p = demandIntercept; p > 0; p--){
			double q = (demandIntercept - p)/demandSlope;
			demandSeries.add(q,p);
		}
		return demandSeries;
	}

	//createSupplySeries: Populates the supply series for this model object
	public XYSeries createSupplySeries(){
		
		//create temp vars
		XYSeries supplySeries = new XYSeries("Supply");
		double q = 0, p = 0;
		
		while((q <= (1.02*optimal_Quantity)) || (p<=(1.02*optimal_Price))){
			p = supplyIntercept + (supplySlope * q);
			supplySeries.add(q,p);
			q++;
		}
		return supplySeries;
	}
	
	/**createDataSet: creates a dataset for the supply and demand series for this model object.
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
			System.out.println("Supply Series is null");
		}
		
		if(this.demandSeries.isEmpty() == false){
			dataset.addSeries(this.demandSeries);			
		}
		else{
			System.out.println("Demand Series is null");
		}
		return dataset;
	}

	//Getters and Setters
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
		return optimal_Price;
	}

	public void setOptimal_Price(double optimal_Price) {
		this.optimal_Price = optimal_Price;
	}

	public double getOptimal_Quantity() {
		return optimal_Quantity;
	}

	public void setOptimal_Quantity(double optimal_Quantity) {
		this.optimal_Quantity = optimal_Quantity;
	}
}