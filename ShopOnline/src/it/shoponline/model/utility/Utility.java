package it.shoponline.model.utility;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public abstract class Utility
{
	public static final String DATE_FORMAT = "dd/MM/yyyy";
	// ---------------------------
	private static NumberFormat doubleFormat = new DecimalFormat("#,##0.00", DecimalFormatSymbols.getInstance(Locale.ITALY));
	private static SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
	// ---------------------------
	public final static boolean isBlank(String s)
	{
		return !isNotBlank(s);
	}
	public final static boolean isNotBlank(String s)
	{
		return s != null && s.trim().length() > 0;
	}
	public final static boolean isNumeric(String s)
	{
		if (isBlank(s))
			return false;
		try
		{
			Long.parseLong(s);
		}
		catch (NumberFormatException e)
		{
			try
			{
				Double.parseDouble(s);
			}
			catch (NumberFormatException e1)
			{
				return false;
			}
		}
		return true;
	}
	public final static synchronized String formattaDate(Date d)
	{
		if (d == null)
			return "";
		return sdf.format(d);
	}
	public final static synchronized Date parseDate(String s) throws ParseException
	{
		if (isBlank(s))
			return null;
		return sdf.parse(s);
	}
	public final static String formattaDouble(double p)
	{
		return doubleFormat.format(p);
	}
	public final static boolean equalsDouble(double d1, double d2)
	{
		return Math.abs(d1 - d2) < .0000001 ;
	}
	public final static double sommaDouble(double d1, double d2)
	{
		return sommaDouble(d1, d2, 2);
	}
	public final static double sommaDouble(double d1, double d2, int numeroDecimali)
	{
		BigDecimal bd1 = new BigDecimal(Double.toString(d1));
		BigDecimal bd2 = new BigDecimal(Double.toString(d2));
		bd1 = bd1.add(bd2);
		return format(bd1, numeroDecimali).doubleValue();
	}
	public final static double moltiplicaDouble(double d1, double d2)
	{
		return moltiplicaDouble(d1, d2, 2);
	}
	public final static double moltiplicaDouble(double d1, double d2, int numeroDecimali)
	{
		BigDecimal bd1 = new BigDecimal(Double.toString(d1));
		BigDecimal bd2 = new BigDecimal(Double.toString(d2));
		bd1 = bd1.multiply(bd2);
		return format(bd1, numeroDecimali).doubleValue();
	}
	public final static double dividiDouble(double d1, double d2)
	{
		return dividiDouble(d1, d2, 2);
	}
	public final static double dividiDouble(double d1, double d2, int numeroDecimali)
	{
		BigDecimal bd1 = new BigDecimal(Double.toString(d1));
		BigDecimal bd2 = new BigDecimal(Double.toString(d2));
		bd1 = bd1.divide(bd2, numeroDecimali, RoundingMode.HALF_UP);
		return format(bd1, numeroDecimali).doubleValue();
	}
	// ---------------------------
	private final static BigDecimal format(BigDecimal n, int prec)
	{
		return n.setScale(prec, BigDecimal.ROUND_HALF_UP);
	}
}