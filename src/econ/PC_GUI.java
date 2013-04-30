package econ;
/**This class create the perfect competition gui that allows the user to interact
 * with the pc_model. This class should not control any of the data for the model.
 * It's only role is to:
 		-take user inputs
 		-create pc_model objects
 		-keep track of these objects
 		-and delete them as necessary
 * MAJOR TODOS!
 * TODO: Move the action event listener into its one class.
 * TODO: Clean up this class! Redo application logic and ensure that 
 * the application logic makes sense. 
 * TODO: Implement an ArrayList or Vector into this class to manage multiple
 * PC_Model classes.
 */

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
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.general.Dataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.RectangleInsets;


/**TODO: Devise way of updating chart once it painted to the canvas instead of deleting and creating a new one. */
@SuppressWarnings("serial")
public class PC_GUI extends JPanel{

	/*Creates a null instance of pc_model for now.
	 * TODO: Determine an appropriate data structure and location of said data strucutre to store PC_Models
	 */
	private PC_Model pc_model;

	/**Keeps track of whether or not the panel has a model on it.
	 * TODO: implement a better way to do this. Possibly in the action event listener somehow*/
	private boolean isModelCreated = false;

	//----- GUI INPUTS COMPONENTS -----
	//create the buttons and text fields
	private JButton jbtSolve = new JButton("Solve");

	//demand slope button and label
	private JTextField jtfDemandSlope = new JTextField("");
	private JLabel jlDemandSlope = new JLabel("Demand Slope");

	//demand intercept button and label
	private JTextField jtfDemandIntercept = new JTextField("");
	private JLabel jlDemandIntercept = new JLabel("Demand Intercept");

	//supply slope button and label
	private JTextField jtfSupplySlope = new JTextField("");
	private JLabel jlSupplySlope = new JLabel("Supply Slope");

	//SUPPLY intercept button and label
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
		inputPanel.setLayout(new GridLayout(0,4,5,5));

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
		inputPanel.add(jlPrice);
		inputPanel.add(jtfPrice);
		inputPanel.add(jlQuantity);
		inputPanel.add(jtfQuantity);
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
		public void actionPerformed(ActionEvent e) {

			/*TODO: Add a try/catch block around these doubles to check if they're null*/ 

			//Parse the double values from the text boxes
			double SSlope = Double.parseDouble(jtfSupplySlope.getText());
			double SIntercept = Double.parseDouble(jtfSupplyIntercept.getText());
			double DSlope = Double.parseDouble(jtfDemandSlope.getText());
			double DIntercept = Double.parseDouble(jtfDemandIntercept.getText());


			/**Want to update the series within the pc_model if TRUE, and just run the constructor if false
			 * TODO: fix this if condition, which should check for null values
			 * if((SSlope = null) && (SIntercept = null) && (DSlope = null) && (DIntercept = null)){ 
			*/

			if(isModelCreated){
				//update the slope and intercept for the model
				pc_model.setSupplySlope(SSlope);
				pc_model.setSupplyIntercept(SIntercept);
				pc_model.setDemandSlope(DSlope);
				pc_model.setDemandIntercept(DIntercept);

				/**Call the calc methods*/
				pc_model.calcPrice();
				pc_model.calcQuantity();
				pc_model.calcCS();
				pc_model.calcPS();
				pc_model.calcW();
				pc_model.calcDWL();

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
				pc_model.createDataset();
				//add the model to the panel
				add(createPanel(pc_model.getDataset()), BorderLayout.NORTH);
				isModelCreated = true;
			}
			jtfPrice.setText(Double.toString(pc_model.calcPrice()));
			jtfQuantity.setText(Double.toString(pc_model.calcQuantity()));
			jtfCS.setText(Double.toString(pc_model.calcCS()));
			jtfPS.setText(Double.toString(pc_model.calcPS()));
			
//			Uncomment when a second set of curves is added
//			jtfDWL.setText(Double.toString(pc_model.calcW()));
//			jtfDWL.setText(Double.toString(pc_model.calcDWL()));
			
			//validates the panel object
			validate();
		}
	}

	/**
	 * Creates the chart from the data set
	 * @param dataset the data for the chart.
	 * @return a chart.
	 */
	@SuppressWarnings("deprecation")
	private static JFreeChart createChart(XYDataset dataset) {
		// create the chart...
		JFreeChart chart = ChartFactory.createXYLineChart(
				"Perfect Competition", // chart title
				"X", // x axis label
				"Y", // y axis label
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
		plot.setAxisOffset(new RectangleInsets(5.0, 5.0, 5.0, 5.0));		//HERE
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


	//Creates and returns a JPanel with a chart on it 
	public static JPanel createPanel(XYDataset data) {
		JFreeChart chart = createChart(data);
		return new ChartPanel(chart);
	}
	// ----- METHODS END -----

	//Main method: start the program
	public static void main (String [] args){

		//create frame and pc object
		PC_GUI test = new PC_GUI();
		JFrame frame = new JFrame();
		frame.setTitle("Testing PC_GUI and PC_Model");
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(500,600));

		//add pc to frame, pack, and set visible
		frame.add(test);
		frame.pack();
		frame.setVisible(true);
	}
}