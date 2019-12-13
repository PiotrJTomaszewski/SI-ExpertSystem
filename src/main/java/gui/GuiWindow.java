package gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ButtonGroup;
import javax.swing.AbstractButton;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Enumeration;
import javax.swing.JButton;
import javax.swing.JDialog;
import gui.BetterRadioButton;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.google.protobuf.TextFormat.ParseException;


/**
 * A dialog box to ask question and get the user's answer.
 */
public class GuiWindow {
	/**
	 * Frame holding all of the dialog box content.
	 */
	private JDialog frame;

	/**
	 * A container holding all of the items, so we can place them nicely inside the
	 * dialog box.
	 */
	private JPanel itemPane;

	/**
	 * A group holding all the radio buttons, so only one can be selected at any
	 * given moment.
	 */
	private ButtonGroup radioButtonGroup;
	
	/**
	 * A dictionary translating token names to strings
	 */
	static JSONObject shortNameDict; 
	
	/**
	 * Create new GUI window (a dialog box).
	 * 
	 * @param parent   A parent frame. Parent's execution will be hold until this
	 *                 dialog box is closed.
	 * @param question A question to ask the user.
	 * @param answers  An array of all possible answers.
	 */
	public GuiWindow(JFrame parent, String question, String[] answers) {
		// Configure the window
		frame = new JDialog(parent, question);
		frame.setModal(true);
		frame.setSize(300, 300); // TODO: Choose window size
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // TODO: Set close operation
		itemPane = new JPanel();
		// Set the layout
		itemPane.setLayout(new BoxLayout(itemPane, BoxLayout.Y_AXIS));
		frame.getContentPane().setLayout(new BorderLayout());
		frame.getContentPane().add(itemPane, BorderLayout.CENTER); // TODO: It seems centering doesn't work
		radioButtonGroup = new ButtonGroup();
		// Place all necessary elements on the screen
		addQuestionLabel(question);
		for (String answer : answers) {
			addRadioButton(answer);
		}
		createConfirmButton();
		frame.setVisible(true);
	}

	/**
	 * Get user's answer.
	 * 
	 * @return User's answer. Returns null if no answer is selected.
	 */
	public String getSelectedOption() {
		for (Enumeration<AbstractButton> buttons = radioButtonGroup.getElements(); buttons.hasMoreElements();) {
			AbstractButton button = buttons.nextElement();
			if (button.isSelected()) {
				BetterRadioButton selectedButton = (BetterRadioButton)button;
				return selectedButton.getShortText();
			}
		}
		return null;
	}

	/**
	 * Adds question label to the dialog window.
	 * 
	 * @param question A question to be displayed.
	 */
	private void addQuestionLabel(String question) {
		JLabel label = new JLabel((String)shortNameDict.get(question));
		itemPane.add(label);
	}

	/**
	 * Adds radio button with a possible answer to the dialog window.
	 * 
	 * @param text An answer variant.
	 */
	private void addRadioButton(String text) {
		BetterRadioButton radioButton = new BetterRadioButton(text, (String)shortNameDict.get(text));
		radioButtonGroup.add(radioButton);
		itemPane.add(radioButton);
	}

	/**
	 * Adds confirmation button to the dialog window.
	 */
	private void createConfirmButton() {
		JButton confirmButton = new JButton("Confirm");
		confirmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
			}
		});
		itemPane.add(confirmButton);
	}
	
	public static void readJson(String path) {
		JSONParser jsonParser = new JSONParser();
		try {
			FileReader reader = new FileReader(path);
			Object obj;
			try {
				obj = jsonParser.parse(reader);
				// JSONArray textList = (JSONArray) obj;
				shortNameDict = (JSONObject) obj;
			} catch (org.json.simple.parser.ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
