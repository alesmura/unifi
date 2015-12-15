package it.shoponline.view.component.input;

import it.shoponline.model.utility.Utility;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public abstract class InputChecker extends PlainDocument
{
	private static final long serialVersionUID = 1L;
	private int maxLength;
	//
	public InputChecker(int maxLength)
	{
		this.maxLength = maxLength;
	}
	@Override
	public void insertString(int offs, String str, AttributeSet a) throws BadLocationException
	{
		if (Utility.isNotBlank(str))
		{
			if (checkType(str))
				if ((getLength() + str.length()) <= maxLength)
					super.insertString(offs, str, a);
		}
	}
	protected abstract boolean checkType(String str);
}