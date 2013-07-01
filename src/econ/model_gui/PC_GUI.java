package econ.model_gui;
/**This class create the perfect competition gui that allows the user to interact
 * with the pc_model. This class should not control any of the data for the model.*/

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYAreaRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.general.Dataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.RectangleInsets;

import econ.model.PC_Model;

@SuppressWarnings("serial")
public class PC_GUI extends Model_GUI{

	private PC_Model pc_model;

	/**Keeps track of whether or not the panel has a model on it.
	 * TODO: implement a better way to do this. Possibly in the action event listener somehow*/
	private boolean isModelCreated = false;

	//----- GUI Buttons -----
	private JButton jbtSolve = new JButton("Solve");
	
	/*TODO: Add later
	private JButton jbtAddDemandSeries = new JButton("+");
	private JButton jbtAddSupplySeries = new JButton("+");
	private JButton jbtRemoveDemandSeries = new JButton("-");	
	private JButton jbtRemoveSupplySeries = new JButton("-");*/
	
	//----- GUI INPUTS COMPONENTS -----
	private JTextField jtfDemandSlope = new JTextField("");
	private JLabel jlDemandSlope = new JLabel("Demand Slope");
	private JTextField jtfDemandIntercept = new JTextField("");
	private JLabel jlDemandIntercept = new JLabel("Demand Intercept");
	private JTextField jtfSupplySlope = new JTextField("");
	private JLabel jlSupplySlope = new JLabel("Supply Slope");
	private JTextField jtfSupplyIntercept = new JTextField("");
	private JLabel jlSupplyIntercept = new JLabel("Supply Intercept");

	//----- GUI OUTPUTS COMPONENTS -----
	private JLabel jlPrice = new JLabel("Equilibrium Price");
	private JTextField jtfPrice = new JTextField();
	private JLabel jlQuantity = new JLabel("Equilibrium Quantity");	
	private JTextField jtfQuantity = new JTextField();
	private JLabel jlCS = new JLabel("Consumer Surplus");
	private JTextField jtfCS = new JTextField();
	private JLabel jlPS = new JLabel("Producer Surplus");
	private JTextField jtfPS = new JTextField();
	private JLabel jlW = new JLabel("Welfare");
	private JTextField jtfW = new JTextField();
	private JLabel jlDWL = new JLabel("Dead Weight Loss");
	private JTextField jtfDWL = new JTextField();

	// ----- CONSTRUCTORS -----
	public PC_GUI(){
		//create a new JPanel to place buttons into, and place the buttons in the panel 
		JPanel inputPanel = new JPanel();
		inputPanel.setLayout(new GridLayout(0,4,15,5));

		//add supply objects to inputpanel
		inputPanel.add(jlSupplySlope);
		inputPanel.add(jtfSupplySlope);
		inputPanel.add(jlSupplyIntercept);
		inputPanel.add(jtfSupplyIntercept);

		//add demand objects to inputpanel
		inputPanel.add(jlDemandSlope);
		inputPanel.add(jtfDemandSlope);
		inputPanel.add(jlDemandIntercept);
		inputPanel.add(jtfDemandIntercept);
		
		//add output
		inputPanel.add(jlQuantity);
		inputPanel.add(jtfQuantity);
		inputPanel.add(jlPrice);
		inputPanel.add(jtfPrice);
		inputPanel.add(jlCS);
		inputPanel.add(jtfCS);
		inputPanel.add(jlPS);
		inputPanel.add(jtfPS);
		inputPanel.add(jlW);
		inputPanel.add(jtfW);
		inputPanel.add(jlDWL);
		inputPanel.add(jtfDWL);

		//JPanel to arrange p1 and jbtSolve button into
		JPanel buttonPanel = new JPanel(new BorderLayout());
		buttonPanel.add(inputPanel, BorderLayout.CENTER);
		buttonPanel.add(jbtSolve, BorderLayout.SOUTH);

		//add buttonPanel and graph into the frame
		this.setLayout(new BorderLayout());
		this.add(buttonPanel, BorderLayout.SOUTH);
		validate();

		//create new solve button listener and register it
		SolveButtonListener sBListener = new SolveButtonListener(); 
		jbtSolve.addActionListener(sBListener);

		//add text fields to event listener so user can use enter key to submit values
		jtfDemandIntercept.addActionListener(sBListener);
		jtfDemandSlope.addActionListener(sBListener);
		jtfSupplyIntercept.addActionListener(sBListener);
		jtfSupplySlope.addActionListener(sBListener);
		jtfCS.addActionListener(sBListener);
		jtfPS.addActionListener(sBListener);
		jtfW.addActionListener(sBListener);
		jtfDWL.addActionListener(sBListener);
	}

	// ----- LISTENERS -----
	class SolveButtonListener implements ActionListener{
		
		/*TODO: Add logic to figure out which field is missing a value, 
		and calculate it's values give the other input variables.*/		
		public void actionPerformed(ActionEvent e) {

			//Convert strings to doubles and check for null strings
			double SSlope = convertInput(jtfSupplySlope);
			double SIntercept = convertInput(jtfSupplyIntercept);
			double DSlope = convertInput(jtfDemandSlope);
			double DIntercept = convertInput(jtfDemandIntercept);
			
			if(isModelCreated){
				//update the slope and intercept for the model
				//only update the model if the input is different than the original
				if((SSlope != pc_model.getSupplySlope()) && (SSlope != 0)) pc_model.setSupplySlope(SSlope);
				if(SIntercept != pc_model.getSupplyIntercept()) pc_model.setSupplyIntercept(SIntercept);
				if(DSlope != pc_model.getDemandSlope()) pc_model.setDemandSlope(DSlope); 
				if(DIntercept != pc_model.getDemandIntercept()) pc_model.setDemandIntercept(DIntercept);

				/**Call the calc methods*/
				pc_model.calcOptimalPrice();
				pc_model.calcOptimalQuantity();
				pc_model.calcConsumerSurplus();
				pc_model.calcProducerSurplus();
				pc_model.calcWellfare();
				pc_model.calcDdeadWeightLoss();

				//Clear the series'
				pc_model.getSupplySeries().clear();
				pc_model.getDemandSeries().clear();

				//then recalculate the series'
				pc_model.setSupplySeries(pc_model.createSupplySeries());
				pc_model.setDemandSeries(pc_model.createDemandSeries());

				revalidate();
			}
			else{
				pc_model = new PC_Model(SSlope, SIntercept, DSlope, DIntercept);
				//add the model to the panel
				add(createChart(pc_model.getDataset(), pc_model.getModelName(), pc_model.getXAxisName(), pc_model.getYAxisName()), BorderLayout.NORTH);
				isModelCreated = true;
			}
			jtfPrice.setText(Double.toString(pc_model.calcOptimalPrice()));
			jtfQuantity.setText(Double.toString(pc_model.calcOptimalQuantity()));
			jtfCS.setText(Double.toString(pc_model.calcConsumerSurplus()));
			jtfPS.setText(Double.toString(pc_model.calcProducerSurplus()));
			jtfW.setText(Double.toString(pc_model.calcWellfare()));
			
//			Uncomment when a second set of curves is added
//			jtfDWL.setText(Double.toString(pc_model.calcW()));
//			jtfDWL.setText(Double.toString(pc_model.calcDWL()));
			
			//validates the panel object
			validate();
		}
		
		/*Converts a text field to a usable double and check if the value is blank.*/
		public double convertInput(JTextField j){
			//get text from JTF
			String str = j.getText();	

			//Loop until string is not null
			while(str == null || str.length() == 0){
				str = JOptionPane.showInputDialog("Whoops! Looks like you left" + j.getName() + " blank. " +
						"Why don't you give that another shot?");
				
			}
			j.setText(str);					//set the text to the new value
			return Double.parseDouble(str);	//return valid double value
		}
	}

	/**
	 * Creates the chart from the data set
	 * @param dataset - the data for the chart.
	 * @param chartTitle - the title of the chart. 
	 * @param xAxisLable - x axis label.
	 * @param yAxisLable - y axis label.
	 * @return a JPanel with a chart on it.
	 */
	@SuppressWarnings("deprecation")
	private static JPanel createChart(XYDataset dataset, String chartTitle, String xAxisLable, String yAxisLable){
		// create the chart...
		JFreeChart chart = ChartFactory.createXYLineChart(
				chartTitle,
				xAxisLable,
				yAxisLable,
				dataset, 
				PlotOrientation.VERTICAL,
				true, // include legend
				true, // tooltips
				false // urls
				);
		//NOW DO SOME OPTIONAL CUSTOMISATION OF THE CHART
		chart.setBackgroundPaint(Color.white);

		//Get a reference to the plot for further customization
		XYPlot plot = (XYPlot) chart.getPlot();
		plot.setBackgroundPaint(Color.lightGray);
		plot.setAxisOffset(new RectangleInsets(5.0, 5.0, 5.0, 5.0));		//HERE
		plot.setDomainGridlinePaint(Color.white);
		plot.setRangeGridlinePaint(Color.white);
		XYLineAndShapeRenderer renderer	= (XYLineAndShapeRenderer) plot.getRenderer();
		renderer.setShapesVisible(true);
		renderer.setShapesFilled(true);

		// change the auto tick unit selection to integer units only...
		NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
		rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
				
		return new ChartPanel(chart);
	}

}