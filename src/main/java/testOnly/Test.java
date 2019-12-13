package testOnly;

import gui.GuiWindow;
import javax.swing.JFrame;

/**
 * A playground to test various parts of the code.
 *
 */
public class Test {
	public static void main(String[] args) {
		// Needed to hold execution of the main thread until the dialog (question) box
		// is closed
		JFrame mainFrame = new JFrame();

		// Create window and get selected answer
		GuiWindow window;
		String[] answers1 = { "test1", "test2", "test3" };
		window = new GuiWindow(mainFrame, "test", answers1);
		System.out.println(window.getSelectedOption());

		String[] answers2 = { "jeden", "dwa", "trzy", "cztery", "pięć" };
		window = new GuiWindow(mainFrame, "test2", answers2);
		System.out.println(window.getSelectedOption());
	}
}
