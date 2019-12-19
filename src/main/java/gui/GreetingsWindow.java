package gui;

import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class GreetingsWindow {
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
	 * Location of the window on the screen.
	 */
	private static Point windowLocation;

	public GreetingsWindow(JFrame parent, String title, String[] authors, String[] remainingText) {
		// Configure the window
		frame = new JDialog(parent, title);
		frame.setModal(true);
		frame.setSize(500, 500);
		if (windowLocation != null) {
			frame.setLocation(windowLocation);
		}
		itemPanel = new JPanel();

//		Border border = BorderFactory.createTitledBorder("Yes");
//		positiveAnswerPanel.setBorder(border);

		// Set the layout
		itemPanel.setLayout(new BoxLayout(itemPanel, BoxLayout.Y_AXIS));
		itemPanel.setBorder(new EmptyBorder(10, 10, 10, 10)); // Padding from edges

		frame.getContentPane().setLayout(new BorderLayout());
		frame.getContentPane().add(itemPanel);
		// Place all necessary elements on the screen
		addLabel(title);
		addLabel("Created by:");
		for (int i=0; i<authors.length-1; ++i) {
			addLabel(authors[i]);
			addLabel("&");
		}
		addLabel(authors[authors.length-1]);
		for (String text: remainingText) {
			addLabel(text);
		}
		createConfirmButton();
		frame.setVisible(true);
	}
	
	/**
	 * Adds a new label to the window.
	 * @param text Text to put on the label.
	 */
	private void addLabel(String text) {
		JLabel label = new JLabel(text);
		itemPanel.add(label);
	}

	/**
	 * Adds confirmation button to the dialog window.
	 */
	private void createConfirmButton() {
		JButton confirmButton = new JButton("OK");
		confirmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Save current location of the window
				windowLocation = frame.getLocation();
				frame.setVisible(false);
			}
		});
		itemPanel.add(confirmButton);
	}

}
