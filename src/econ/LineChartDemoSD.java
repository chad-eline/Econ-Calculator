package econ;
/* -------------------
 * LineChartDemo2.java
 * -------------------
 * (C) Copyright 2002-2005, by Object Refinery Limited.
 *
 */
import java.awt.Color;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RectangleInsets;
import org.jfree.ui.RefineryUtilities;
/**
 * A simple demonstration application showing how to create a line chart using
 * data from an {@link XYDataset}.
 * <p>
 * IMPORTANT NOTE: THIS DEMO IS DOCUMENTED IN THE JFREECHART DEVELOPER GUIDE.
 * DO NOT MAKE CHANGES WITHOUT UPDATING THE GUIDE ALSO!!
 */
public class LineChartDemoSD extends ApplicationFrame{
	
	static double supplySlope = 3;
	static double supplyIntercept = 20;
	
	static double demandSlope = 8;			//b
	static double demandIntercept = 100;  	//a
	
	/**TODO: Fix the optimal_price and optimal_quantity vairable dependency. Consider making them equivalent
	 * 	to the funciton call. 
	 * @param optimal_Price: calcPirce must be called before calling this variable.
	 * @param optimal_Quantity: calcQuantity must be called before calling this variable.
	 */
	static double optimal_Price = 0;
	static double optimal_Quantity = 0;
	
	
	/**Creates a new demo.
	 * @param title the frame title.
	 */
	public LineChartDemoSD(String title) {
		super(title);
		XYDataset dataset = createDataset();
		JFreeChart chart = createChart(dataset);
		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
		setContentPane(chartPanel);
	}
	
	public static double calcPrice(){
		double optimal_Price = ((demandIntercept * supplySlope) + (demandSlope * supplyIntercept)) / (demandSlope + supplySlope);
		return optimal_Price ;
	}

	public static double calcQuantity(){
		double optimal_Quantity = ((demandIntercept - Math.abs(supplyIntercept)) / (demandSlope + supplySlope));
		return optimal_Quantity;
	}
	
	/**
	 * Creates a sample dataset.
	 * @return a sample dataset.
	 */
	private static XYDataset createDataset() {

		//P and Q are local vars used to populate the data set
		double p = 0, q = 0;


		//TODO: remove this and make methods nonstatic
		optimal_Price = calcPrice();
		optimal_Quantity = calcQuantity();

		//Create and populate demand series for this dataset
		/**TODO: Clean up / redo this for loop. */
		XYSeries demand_series = new XYSeries("Demand");
		for(p = demandIntercept; p > 0; p = p - 5){
			q = (demandIntercept - p)/demandSlope;
			demand_series.add(q,p);
		}
		
		//Reset p and q
		p = 0;
		q = 0;		

		/**create and populate supply series for this dataset. 
		 * Demand curve must be calculated first and then supply
		 * just like irl :)*/
		XYSeries supply_series = new XYSeries("Supply");
		while((q <= (1.02*optimal_Quantity)) || (p<=(1.02*optimal_Price))){
			p = supplyIntercept + (supplySlope * q);
			supply_series.add(q,p);
			q++;
		}
		
		XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(supply_series);
		dataset.addSeries(demand_series);
		return dataset;
	}

	/**
	 * Creates a chart.
	 * @param dataset the data for the chart.
	 * @return a chart.
	 */
	@SuppressWarnings("deprecation")
	private static JFreeChart createChart(XYDataset dataset) {
		// create the chart...
		JFreeChart chart = ChartFactory.createXYLineChart(
				"Supply and Demand", // chart title
				"Quantity", // x axis label
				"Price", // y axis label
				dataset, // data
				PlotOrientation.VERTICAL,
				true, // include legend
				true, // tooltips
				false // urls
				);
		// NOW DO SOME OPTIONAL CUSTOMISATION OF THE CHART...
		chart.setBackgroundPaint(Color.white);
		
		// get a reference to the plot for further customisation...
		XYPlot plot = (XYPlot) chart.getPlot();
		plot.setBackgroundPaint(Color.lightGray);
		plot.setAxisOffset(new RectangleInsets(5.0, 5.0, 5.0, 5.0));
		plot.setDomainGridlinePaint(Color.white);
		plot.setRangeGridlinePaint(Color.white);
		XYLineAndShapeRenderer renderer	= (XYLineAndShapeRenderer) plot.getRenderer();
		renderer.setShapesVisible(true);
		renderer.setShapesFilled(true);
		
		// change the auto tick unit selection to integer units only...
		NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
		rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		
		// OPTIONAL CUSTOMIZATION COMPLETED.
		return chart;
	}

	/**
	 * Creates a panel for the demo (used by SuperDemo.java).
	 *
	 * @return A panel.
	 */
	public static JPanel createDemoPanel() {
		JFreeChart chart = createChart(createDataset());
		return new ChartPanel(chart);
	}
	/**
	 * Starting point for the demonstration application.
	 *
	 * @param args ignored.
	 */
	public static void main(String[] args) {
		LineChartDemoSD demo = new LineChartDemoSD("Simply S&D Chart");
		demo.pack();
		RefineryUtilities.centerFrameOnScreen(demo);
		demo.setVisible(true);
	}
}