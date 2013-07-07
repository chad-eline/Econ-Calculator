/**Model_GUI: This class is the super type to all model_gui's.
 * TODO: Flesh out class.  
*/

package econ.model_gui;

import java.awt.Color;

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
import org.jfree.ui.RectangleInsets;

public class Model_GUI extends JPanel{
	
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
	
	//Constructors
	
	//methods
	
	/**
	 * Creates the chart from the data set
	 * @param dataset the data for the chart.
	 * @param modelName the name of the model being created
	 * @param xAxis X-Axis name
	 * @param yAxsis Y-Axis name
	 * @return a chart.
	 */
	@SuppressWarnings("deprecation")
	private static JFreeChart createChart(XYDataset dataset, String modelName, String xAxis, String yAxis){
		JFreeChart chart = ChartFactory.createXYLineChart(modelName, xAxis, yAxis, dataset, PlotOrientation.VERTICAL, true, true, false);
		chart.setBackgroundPaint(Color.white);
		XYPlot plot = (XYPlot) chart.getPlot();
		plot.setBackgroundPaint(Color.lightGray);
		plot.setAxisOffset(new RectangleInsets(5.0, 5.0, 5.0, 5.0));
		plot.setDomainGridlinePaint(Color.white);
		plot.setRangeGridlinePaint(Color.white);
		XYLineAndShapeRenderer renderer	= (XYLineAndShapeRenderer) plot.getRenderer();
		renderer.setShapesVisible(true);
		renderer.setShapesFilled(true);
		return chart;
	}

	/**Creates and returns a JPanel with a chart on it*/ 
	public static JPanel createPanel(XYDataset data, String modelName, String xAxis, String yAxis) {
		JFreeChart chart = createChart(data, modelName, xAxis, yAxis);
		return new ChartPanel(chart);
	}
		
	//Inner Classes

	//getters and setters 

}
