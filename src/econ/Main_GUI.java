package econ;
/**This class is responsible for creating the main GUI window
 * and handling all of the events for that JPanel. It contains
 * A JComboBox with the names of the various models. 
 * Once the model is selected this class:
 * 		-removes the temporary Graph object
 * 		-creates and loads the selected graph object
 * 		-returns control to user 
 * */

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

import econ.model_gui.Cournot_GUI;
import econ.model_gui.Model_GUI;
import econ.model_gui.Monopoly_GUI;
import econ.model_gui.PC_GUI;

/**TODO: Change super of Main_GUI from JFrame to JPanel. I want to use 
 * lightweight swing ONLY and not heavy awt*/
public class Main_GUI extends JFrame{
	
	/**Default X and Y frame sizes for the application frame window*/
	public static final int DEFAULT_MAIN_WINDOW_SIZE_X = 600;
	public static final int DEFAULT_MAIN_WINDOW_SIZE_Y = 475;
	
	/**Default frame title*/
	public static final String DEFAULT_FRAME_TITLE = "My Econ Tutor";
	
	private String[] modelNames = {"Please Select a Model", "Bertrand", "Cournot", "Monopoly", "Perfect Competition", "Stackelburg"};
	private JComboBox modelList = new JComboBox(modelNames);
	private Model_GUI gui;
	List <Model_GUI> guiList = new ArrayList<Model_GUI>();
	private static int numGUIS = 0;
	
	/**Constructor: */
	public Main_GUI(){
		setLayout(new BorderLayout());						//set the layout for the jframe
		modelList.setSelectedIndex(0);						//add action listeners to the combo box
		modelList.addActionListener(new ComboBoxListener());
		this.add(modelList, BorderLayout.NORTH);			//add the modeList jcombobox to the JFrame
	}
	
	//need a way to remove the current model from the canvas
	
	class ComboBoxListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			JComboBox cb = (JComboBox) e.getSource();
			String modelName = (String) cb.getSelectedItem();
			
			/*Check if a model GUI has been add to the frame. If true, clear the frame and 
			 * remove the gui from the guiList*/ 
			if(numGUIS > 0){
				numGUIS--;
				gui = guiList.get(numGUIS);
				remove(gui);
				guiList.remove(gui);
				validate();
			}
			
			switch(modelName){
			case "Bertrand":
				gui = new Bertrand_GUI();
				guiList.add(gui);
				add(gui, BorderLayout.CENTER);
				revalidate();
				validate();
				numGUIS++;
				break;
			case "Cournot":
				gui = new Cournot_GUI();
				guiList.add(gui);
				add(gui, BorderLayout.CENTER);
				revalidate();
				validate();
				numGUIS++;
				break;
			case "Monopoly":
				gui = new Monopoly_GUI();
				guiList.add(gui);
				add(gui, BorderLayout.CENTER);
				revalidate();
				validate();
				numGUIS++;
			case "Stackleburg":
				gui = new Stackleburg_GUI();
				guiList.add(gui);
				add(gui, BorderLayout.CENTER);
				revalidate();
				validate();
				numGUIS++;
			case "Perfect Competition":
				gui = new PC_GUI();
				guiList.add(gui);
				add(gui, BorderLayout.CENTER);
				revalidate();
				validate();
				numGUIS++;
				break;
			}
		}
	}

	//main method, starts app
	public static void main(String[] args){
		/**For Testing. 
		 * TODO: Remove and finalize this code.*/
		
		Main_GUI mPanel = new Main_GUI();	//creates a main GUI panel
		mPanel.setTitle(DEFAULT_FRAME_TITLE);									//sets the frames title
		mPanel.setSize(DEFAULT_MAIN_WINDOW_SIZE_X, DEFAULT_MAIN_WINDOW_SIZE_Y);	//set default size of the frame
		mPanel.setLocationRelativeTo(null);										//sets the relative location of the frame to the center of the screen
		mPanel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mPanel.setVisible(true);
	}
}