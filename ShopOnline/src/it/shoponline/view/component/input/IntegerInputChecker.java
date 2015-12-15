package it.shoponline.view.component.input;

import it.shoponline.model.utility.Utility;

public class IntegerInputChecker extends InputChecker
{
	private static final long serialVersionUID = 1L;

	public IntegerInputChecker(int maxLength)
	{
		super(maxLength);
	}
	@Override
	protected boolean checkType(String str)
	{
		// Se non e' numerico, non accetto
		if (!Utility.isNumeric(str))
			return false;
		return true;
	}
}