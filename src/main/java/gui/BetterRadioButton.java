package gui;
import javax.swing.JRadioButton;


public class BetterRadioButton extends JRadioButton {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String shortText;
	public BetterRadioButton(String shortText, String longText) {
		super(longText);
		this.shortText = shortText;
	}
	
	public String getShortText() {
		return this.shortText;
	}
}
