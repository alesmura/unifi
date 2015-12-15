package it.shoponline.test.utility;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import it.shoponline.model.utility.Utility;
import java.text.ParseException;
import java.util.Calendar;
import org.junit.Test;

public class UtilityTest
{
	@Test
	public void testIsBlank_String()
	{
		assertFalse(Utility.isBlank("aaa"));
	}

	@Test
	public void testIsBlank_Null()
	{
		assertTrue(Utility.isBlank(null));
	}

	@Test
	public void testIsBlank_Blank()
	{
		assertTrue(Utility.isBlank(""));
	}

	@Test
	public void testIsBlank_Space()
	{
		assertTrue(Utility.isBlank(" "));
	}

	//---------------------------------------------------------------------------------------------
	@Test
	public void testIsNotBlank_String()
	{
		assertTrue(Utility.isNotBlank("aaa"));
	}

	@Test
	public void testIsNotBlank_Null()
	{
		assertFalse(Utility.isNotBlank(null));
	}

	@Test
	public void testIsNotBlank_Blank()
	{
		assertFalse(Utility.isNotBlank(""));
	}

	@Test
	public void testIsNotBlank_Space()
	{
		assertFalse(Utility.isNotBlank(" "));
	}

	//---------------------------------------------------------------------------------------------
	@Test
	public void testIsNumeric_String()
	{
		assertFalse(Utility.isNumeric("aaaa"));
	}

	@Test
	public void testIsNumeric_Double()
	{
		assertTrue(Utility.isNumeric("2.21"));
	}

	@Test
	public void testIsNumeric_Integer()
	{
		assertTrue(Utility.isNumeric("21"));
	}

	@Test
	public void testIsNumeric_Null()
	{
		assertFalse(Utility.isNumeric(null));
	}

	@Test
	public void testIsNumeric_Blank()
	{
		assertFalse(Utility.isNumeric(""));
	}

	@Test
	public void testIsNumeric_Space()
	{
		assertFalse(Utility.isNumeric(" "));
	}

	//---------------------------------------------------------------------------------------------
	@Test
	public void testFormattaDate_1()
	{
		Calendar c = Calendar.getInstance();
		c.set(Calendar.DAY_OF_MONTH, 1);
		c.set(Calendar.MONTH, Calendar.JANUARY);
		c.set(Calendar.YEAR, 2001);
		assertEquals("01/01/2001", Utility.formattaDate(c.getTime()));
	}

	@Test
	public void testFormattaDate_2()
	{
		Calendar c = Calendar.getInstance();
		c.set(Calendar.DAY_OF_MONTH, 1);
		c.set(Calendar.MONTH, Calendar.MARCH);
		c.set(Calendar.YEAR, 1890);
		assertEquals("01/03/1890", Utility.formattaDate(c.getTime()));
	}

	@Test
	public void testFormattaDate_Null()
	{
		assertEquals("", Utility.formattaDate(null));
	}

	//---------------------------------------------------------------------------------------------
	@Test
	public void testParseDate_1() throws ParseException
	{
		Calendar c = Calendar.getInstance();
		c.clear();
		c.set(Calendar.DAY_OF_MONTH, 1);
		c.set(Calendar.MONTH, Calendar.JUNE);
		c.set(Calendar.YEAR, 1988);
		assertEquals(c.getTime(), Utility.parseDate("01/06/1988"));
	}

	@Test
	public void testParseDate_2() throws ParseException
	{
		Calendar c = Calendar.getInstance();
		c.clear();
		c.set(Calendar.DAY_OF_MONTH, 21);
		c.set(Calendar.MONTH, Calendar.APRIL);
		c.set(Calendar.YEAR, 2015);
		assertEquals(c.getTime(), Utility.parseDate("21/04/2015"));
	}

	@Test(expected = ParseException.class)
	public void testParseDate_Exception() throws ParseException
	{
		Utility.parseDate("30/0a/2015");
	}

	@Test
	public void testParseDate_Null() throws ParseException
	{
		assertEquals(null, Utility.parseDate(null));
	}

	@Test
	public void testParseDate_Blank() throws ParseException
	{
		assertEquals(null, Utility.parseDate(""));
	}

	@Test
	public void testParseDate_Space() throws ParseException
	{
		assertEquals(null, Utility.parseDate(" "));
	}

	//---------------------------------------------------------------------------------------------
	@Test
	public void testFormattaDouble_Zero()
	{
		assertEquals("0,00", Utility.formattaDouble(0));
	}

	@Test
	public void testFormattaDouble_1()
	{
		assertEquals("10,21", Utility.formattaDouble(10.211));
	}

	@Test
	public void testFormattaDouble_2()
	{
		assertEquals("0,46", Utility.formattaDouble(0.456));
	}

	@Test
	public void testFormattaDouble_3()
	{
		assertEquals("0,45", Utility.formattaDouble(0.454));
	}

	//---------------------------------------------------------------------------------------------
	@Test
	public void testEqualsDouble_1()
	{
		assertTrue(Utility.equalsDouble(0.1, 0.1));
	}

	@Test
	public void testEqualsDouble_2()
	{
		assertFalse(Utility.equalsDouble(0.2, 0.1));
	}

	@Test
	public void testEqualsDouble_3()
	{
		assertTrue(Utility.equalsDouble(3.156, 3.156));
	}

	//---------------------------------------------------------------------------------------------
	@Test
	public void testSommaDouble_Zero()
	{
		assertEquals(1, Utility.sommaDouble(1, 0), 0);
	}

	@Test
	public void testSommaDouble_1()
	{
		assertEquals(7.62, Utility.sommaDouble(3.4612, 4.1554), 0);
	}

	@Test
	public void testSommaDouble_2()
	{
		assertEquals(4.43, Utility.sommaDouble(1.6486, 2.7854), 0);
	}

	//---------------------------------------------------------------------------------------------
	@Test
	public void testSommaDoubleRound_1()
	{
		assertEquals(7.6166, Utility.sommaDouble(3.4612, 4.1554, 4), 0);
	}

	@Test
	public void testSommaDoubleRound_2()
	{
		assertEquals(4.434, Utility.sommaDouble(1.6486, 2.7854, 4), 0);
	}

	//---------------------------------------------------------------------------------------------
	@Test
	public void testMoltiplicaDouble_Zero()
	{
		assertEquals(0, Utility.moltiplicaDouble(1, 0), 0);
	}

	@Test
	public void testMoltiplicaDouble_1()
	{
		assertEquals(7.34, Utility.moltiplicaDouble(3.312, 2.215), 0);
	}

	@Test
	public void testMoltiplicaDouble_2()
	{
		assertEquals(42.66, Utility.moltiplicaDouble(4.4186, 9.654), 0);
	}

	//---------------------------------------------------------------------------------------------
	@Test
	public void testMoltiplicaDouble_Round1()
	{
		assertEquals(7.336, Utility.moltiplicaDouble(3.312, 2.215, 3), 0);
	}

	@Test
	public void testMoltiplicaDouble_Round2()
	{
		assertEquals(42.657, Utility.moltiplicaDouble(4.4186, 9.654, 3), 0);
	}

	//---------------------------------------------------------------------------------------------
	@Test(expected = ArithmeticException.class)
	public void testDividiDouble_Zero()
	{
		Utility.dividiDouble(3, 0);
	}
	
	@Test
	public void testDividiDouble_Uno()
	{
		assertEquals(3, Utility.dividiDouble(3, 1), 0);
	}

	@Test
	public void testDividiDouble_1()
	{
		assertEquals(4.2, Utility.dividiDouble(10.4523, 2.4897), 0);
	}

	@Test
	public void testDividiDouble_2()
	{
		assertEquals(15.95, Utility.dividiDouble(50.25, 3.15), 0);
	}

	//---------------------------------------------------------------------------------------------
	@Test
	public void testDividiDouble_Round1()
	{
		assertEquals(4.1982, Utility.dividiDouble(10.4523, 2.4897, 4), 0);
	}

	@Test
	public void testDividiDouble_Round2()
	{
		assertEquals(15.9524, Utility.dividiDouble(50.25, 3.15, 4), 0);
	}
	//---------------------------------------------------------------------------------------------
}
