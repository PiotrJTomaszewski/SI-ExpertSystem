package gui;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.ButtonGroup;
import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;
import javax.swing.JButton;
import javax.swing.JDialog;
import gui.BetterRadioButton;
import org.json.simple.JSONObject;


/**
 * A dialog box to ask question and get the user's answer.
 */
public class QuestionAndAnswerWindow {
	/**
	 * Frame holding all of the dialog box content.
	 */
	private JDialog frame;

	/**
	 * A container holding all of the items, so we can place them nicely inside the
	 * dialog box.
	 */
	private JPanel itemPanel;
	
	/**
	 * A container holding all of the items that correspond to positive answers.
	 */
	private JPanel positiveAnswerPanel;

	/**
	 * A group holding all the radio buttons, so only one can be selected at any
	 * given moment.
	 */
	private ButtonGroup radioButtonGroup;
	
	/**
	 * A dictionary translating token names to strings.
	 */
	private static JSONObject shortNameDict;
	
	/**
	 * Location of the window on the screen.
	 */
	private static Point windowLocation;
	
	/**
	 * Create new GUI window (a dialog box) for questions and answers.
	 * 
	 * @param parent   A parent frame. Parent's execution will be hold until this
	 *                 dialog box is closed.
	 * @param question A question to ask the user.
	 * @param answers  An array of all possible answers.
	 */
	public QuestionAndAnswerWindow(String question, String[] answers) {
		// Configure the window
		frame = new JDialog((JWindow)null, (String)shortNameDict.get(question));
		frame.setModalityType(JDialog.ModalityType.TOOLKIT_MODAL);
		frame.setSize(500, 500);
		if (windowLocation != null) {
			frame.setLocation(windowLocation);
		}
		itemPanel = new JPanel();
		positiveAnswerPanel = new JPanel();
		
		if (answers[0] != "yes") {
			Border border = BorderFactory.createTitledBorder("Yes");
			positiveAnswerPanel.setBorder(border);
		}
		// Set the layout
		itemPanel.setLayout(new BoxLayout(itemPanel, BoxLayout.Y_AXIS));
		itemPanel.setBorder(new EmptyBorder(10, 10, 10, 10));  // Padding from edges
		positiveAnswerPanel.setLayout(new BoxLayout(positiveAnswerPanel, BoxLayout.Y_AXIS));

		frame.getContentPane().setLayout(new BorderLayout());
		frame.getContentPane().add(itemPanel);
		radioButtonGroup = new ButtonGroup();
		// Place all necessary elements on the screen
		addQuestionLabel(question);
		itemPanel.add(positiveAnswerPanel);

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
		//label.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		itemPanel.add(label);
	}

	/**
	 * Adds radio button with a possible answer to the dialog window.
	 * 
	 * @param text An answer variant.
	 */
	private void addRadioButton(String text) {
		BetterRadioButton radioButton = new BetterRadioButton(text, (String)shortNameDict.get(text));
		radioButtonGroup.add(radioButton);
		if (text == "no") {
			itemPanel.add(radioButton);
		}
		else {
			positiveAnswerPanel.add(radioButton);
		}
		radioButton.setSelected(true);
	}

	/**
	 * Adds confirmation button to the dialog window.
	 */
	private void createConfirmButton() {
		JButton confirmButton = new JButton("Confirm");
		confirmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Save current location of the window
				windowLocation = frame.getLocation();
				frame.setVisible(false);
			}
		});
		itemPanel.add(confirmButton);
	}
	
	/**
	 * Stores a dictionary into a local variable.
	 */
	public static void storeDictionary(JSONObject dictionary) {
		shortNameDict = dictionary;
	}
}