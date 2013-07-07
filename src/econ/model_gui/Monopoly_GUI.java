//This class will be the graphical representation of the monopoly model

package econ.model_gui;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import econ.model.Monopoly_Model;

public class Monopoly_GUI extends Model_GUI{
	
	private Monopoly_Model monopoly_Model;
	
	
	//buttons
	private JButton jbtSolve = new JButton("Solve");
	
	//inputs components
	
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
	
}
