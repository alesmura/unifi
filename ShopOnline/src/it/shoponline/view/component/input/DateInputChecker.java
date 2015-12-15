package it.shoponline.view.component.input;

import it.shoponline.model.utility.Utility;

public class DateInputChecker extends InputChecker
{
	private static final long serialVersionUID = 1L;

	public DateInputChecker(int maxLength)
	{
		super(maxLength);
	}
	@Override
	protected boolean checkType(String str)
	{
		// Serve per l'inizializzazione
		if (str.equals(Utility.DATE_FORMAT))
			return true;
		// Se non e' / e non e' numerico, non accetto
		if (!str.equals("/") && !Utility.isNumeric(str))
			return false;
		return true;
	}
}