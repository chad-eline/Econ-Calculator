//This class will be the graphical representation of the monopoly model
package econ.model_gui;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import econ.model.Monopoly_Model;

public class Monopoly_GUI extends Model_GUI{
	
	private Monopoly_Model monopoly_Model;
	
	//----- BUTTONS -----
	private JButton jbtSolve = new JButton("Solve");
	
	//----- INPUT COMPONENTS ----- 
	
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
	public Monopoly_GUI(){
		JPanel inputPanel = new JPanel();
		inputPanel.setLayout(new GridLayout(0,4,15,5));

		//Add components to canvas
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
		
		//create new solve button listener
		SolveButtonListener sBListener = new SolveButtonListener(); 

		//register listener
		jbtSolve.addActionListener(sBListener);
		jtfPrice.addActionListener(sBListener);
		jtfQuantity.addActionListener(sBListener);
		jtfCS.addActionListener(sBListener);
		jtfPS.addActionListener(sBListener);
		jtfW.addActionListener(sBListener);
		jtfDWL.addActionListener(sBListener);
	}
	
	//Inner class listeners
	class SolveButtonListener implements ActionListener{
			
		
		public void actionPerformed(ActionEvent e){
			/**TODO add logic for monopoly
			 * determine a way to calculate for the missing fields
			 * grab text out of fields
			 * calculate values
			 * place new numbers back in fields*/
		}
		
		/*Converts a text field to a usable double and check if the value is blank.*/
		public double convertInput(JTextField j){
			//get text from JTF
			String str = j.getText();	
			//Loop until string is not null
			while(str == null || str.length() == 0){
				str = JOptionPane.showInputDialog("Whoops! Looks like you left" + j.getName() + " blank. " +  "Why don't you give that another shot?");
			}
			j.setText(str);					//set the text to the new value
			return Double.parseDouble(str);	//return valid double value
		}
	}
}