package gui;

import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import org.json.simple.JSONObject;

public class BookInfoWindow {
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
	 * An array of containers, each holding a single book.
	 */
	private JPanel[] bookPanel;

	/**
	 * A dictionary translating token names to strings.
	 */
	private static JSONObject shortNameDict;

	/**
	 * Location of the window on the screen.
	 */
	private static Point windowLocation;

	public BookInfoWindow(String[] booksShort) {
		int booksNumber = booksShort.length;
		// Configure the window
		String windowTitle;
		if (booksNumber == 1) {
			windowTitle = "Book recommended for you:";
		} else {
			windowTitle = "Books recommended for you:";
		}
		frame = new JDialog((JWindow)null, windowTitle);
		frame.setModalityType(JDialog.ModalityType.TOOLKIT_MODAL);
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
		// Define new panels to hold books in
		JLabel windowTitleLabel = new JLabel(windowTitle);
		itemPanel.add(windowTitleLabel);
		bookPanel = new JPanel[booksNumber];
		for (int i = 0; i < booksNumber; ++i) {
			bookPanel[i] = new JPanel();
			bookPanel[i].setLayout(new BoxLayout(bookPanel[i], BoxLayout.Y_AXIS));
			String singleBookShort = booksShort[i];
			addBook(singleBookShort, i);
			itemPanel.add(bookPanel[i]);
		}
		createConfirmButton();
		frame.setVisible(true);
	}
	
	/**
	 * Adds a new book info to the window.
	 * @param bookShort A token representing a book
	 * @param index An index of the container holding this book
	 */
	private void addBook(String bookShort, int index) {
		JSONObject bookInfo = (JSONObject) shortNameDict.get(bookShort);
		String theme = (String) bookInfo.get("theme");
		String title = (String) bookInfo.get("title");
		String author = (String) bookInfo.get("author");
		Border border = BorderFactory.createTitledBorder("");
		bookPanel[index].setBorder(border);
		JLabel themeLabel = new JLabel("Book theme: " + theme);
		JLabel titleLabel = new JLabel("Title: " + title);
		JLabel authorLabel = new JLabel("Author: " + author);
		bookPanel[index].add(themeLabel);
		bookPanel[index].add(titleLabel);
		bookPanel[index].add(authorLabel);
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

	/**
	 * Stores a dictionary into a local variable.
	 */
	public static void storeDictionary(JSONObject dictionary) {
		shortNameDict = dictionary;
	}

}
