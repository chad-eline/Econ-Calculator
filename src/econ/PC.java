package econ;
/**This class was used to play with JFreeChart and figure out a way to implement it
 * into my app. It is essentially a test bed for programming with the JFreeChart
 * library.
 * MAJOR TODOS!
 * Move the action event listener into its one class.
 * Move the create series and create datasets methods and vars 
 * into the PC_Model class and seperate that class out from this class.*/

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.RectangleInsets;


public class PC extends JPanel{
	
	//keeps track of if there is a chart or not
	private boolean there_is_a_chart = false;
	
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
	//----- GUI INPUTS COMPONENTS END -----

	//----- GUI OUTPUTS COMPONENTS -----
	private JLabel jlPrice = new JLabel("Equilibrium Price");
	private JTextField jtfPrice = new JTextField();
	private JLabel jlQuantity = new JLabel("Equilibrium Quantity");	
	private JTextField jtfQuantity = new JTextField();
	//----- GUI OUTPUTS COMPONENTS END -----
	
	
	//----- jFreeChart Components -----

	//----- jFreeChart Components End-----
	
	// ----- CONSTRUCTORS -----
	public PC(){
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

		//JPanel to arrange p1 and jbtSolve button into
		JPanel buttonPanel = new JPanel(new BorderLayout());
		buttonPanel.add(inputPanel, BorderLayout.CENTER);
		buttonPanel.add(jbtSolve, BorderLayout.SOUTH);

		//add buttonPanel and graph into the frame
		this.setLayout(new BorderLayout());
/*		this.add(graph, BorderLayout.CENTER);	TODO: integrate JFreeChart here*/  						
		this.add(buttonPanel, BorderLayout.SOUTH);
		validate();

		//Register anonymous action listener class for the solve button
		jbtSolve.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e){
						/**Checks if all the necessary fields are filled and then calculates the outcomes for
						 * perfect competition and then displays them!!!!*/
						if((jtfDemandSlope.getText()!= "")
						  && (jtfDemandSlope.getText()!= "") &&
						  (jtfSupplySlope.getText()!= "")&&
						  (jtfSupplyIntercept.getText()!= "")){

							/*Pass text from boxes in fields to constructor, then call 
							 * calc methods inside the text setter function*/
							PC_Model model = new PC_Model(
								Double.parseDouble(jtfSupplySlope.getText()),
								Double.parseDouble(jtfSupplyIntercept.getText()),
								Double.parseDouble(jtfDemandSlope.getText()),
								Double.parseDouble(jtfDemandIntercept.getText())
							);
							//sets text on gui
							jtfPrice.setText(Double.toString(model.calcPrice()));
							jtfQuantity.setText(Double.toString(model.calcQuantity()));

							
							/**TODO: update logic here to match logic from switching 
							to the pc_model object*/

							//call createchart and createDataset  
							add(createPanel(model), BorderLayout.NORTH);
							validate();

						}
					}
				}
			);
		}
	// ----- CONSTRUCTORS END -----

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
	
	public static JPanel createPanel(PC_Model model) {
		XYDataset data = createDataset(model);
		JFreeChart chart = createChart(data);
		return new ChartPanel(chart);
	}
	// ----- METHODS END -----

	//----- INNER CLASSES ----
	//----- INNER CLASSES END----

	//Main method: start the program
	public static void main (String [] args){
		
		//create frame and pc object
		PC test = new PC();
		JFrame frame = new JFrame();
		frame.setTitle("Simple PC");
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//add pc to frame, pack, and set visible
		frame.add(test);
		frame.pack();
		frame.setVisible(true);
	}
}