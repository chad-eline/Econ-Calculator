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
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

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
	
	/**Constructor: */
	public Main_GUI(){
		//set the layout for the jframe 
		setLayout(new BorderLayout());
		
		//add action listeners to the combo box
		modelList.setSelectedIndex(0);
		modelList.addActionListener(new ComboBoxListener());
		
		this.add(modelList, BorderLayout.NORTH);	//add the modeList jcombobox to the JFrame
	}
	
	class ComboBoxListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			JComboBox cb = (JComboBox) e.getSource();
			String modelName = (String) cb.getSelectedItem();
			switch(modelName){
			case "Bertrand": 
			case "Cournot":
			case "Monopoly":
			case "Stackleburg":
				//remove(graph);
				repaint();
				break;
			case "Perfect Competition":
				//remove(graph);
				PC_GUI pcgui = new PC_GUI();
				add(pcgui, BorderLayout.CENTER);
				revalidate();
				validate();
				break;
			}
		}
	}

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