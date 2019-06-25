package Client;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 * This class contains the methods that allow a limit of characters to be set on
 * a JTextField
 * 
 * @author aaron
 *
 */
public class JTextFieldLimit extends PlainDocument {
	
	private int limit;

	/**
	 * Constructor for the JTextFieldLimit object
	 * 
	 * @param limit The maximum amount of characters that is allowed
	 */
	public JTextFieldLimit(int limit) {
		super();
		this.limit = limit;
	}

	/**
	 * 
	 */
	public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
		if (str == null) {
			return;
		}

		if ((getLength() + str.length()) <= limit) {
			super.insertString(offset, str, attr);
		}
	}
}