package it.shoponline.test.prodotti;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import it.shoponline.model.prodotti.astratti.Alimento;
import it.shoponline.model.prodotti.concreti.alimenti.Birra;
import it.shoponline.model.prodotti.concreti.alimenti.Champagne;
import org.junit.Before;
import org.junit.Test;

public class AlimentoTest
{
	private Alimento alimento1;
	private Alimento alimento2;
	private Alimento alimento3;
	@Before
	public void setUp() throws Exception
	{
		alimento1 = new Birra("Birra", 3);
		alimento2 = new Champagne("Champagne", 3);
		alimento3 = new Birra("Birra", 3);
	}
	//---------------------------------------------------------------------------------------------
	@Test
	public void testGetPrezzo()
	{
		assertEquals(3, alimento1.getPrezzo(), 0);
	}
	//---------------------------------------------------------------------------------------------
	@Test(expected = UnsupportedOperationException.class)
	public void testAggiungiProdotto()
	{
		alimento1.aggiungiProdotto(alimento2);
	}
	//---------------------------------------------------------------------------------------------
	@Test(expected = UnsupportedOperationException.class)
	public void testRimuoviProdotto()
	{
		alimento1.rimuoviProdotto(alimento2);
	}
	//---------------------------------------------------------------------------------------------
	@Test
	public void testEquals_Equal()
	{
		assertEquals(alimento1, alimento3);
	}
	@Test
	public void testEquals_NotEqual()
	{
		assertNotEquals(alimento1, alimento2);
	}
	//---------------------------------------------------------------------------------------------
	@Test
	public void testClone_Equal1() throws CloneNotSupportedException
	{
		Alimento clone = (Alimento) alimento1.clone();
		assertEquals(alimento1, clone);
		//
		assertFalse(alimento1 == clone);
	}
	@Test
	public void testClone_Equal2() throws CloneNotSupportedException
	{
		Alimento clone = (Alimento) alimento1.clone();
		assertEquals(alimento3, clone);
		//
		assertFalse(alimento3 == clone);
	}
	@Test
	public void testClone_NotEqual() throws CloneNotSupportedException
	{
		Alimento clone = (Alimento) alimento1.clone();
		assertNotEquals(alimento2, clone);
		//
		assertFalse(alimento2 == clone);
	}
	//---------------------------------------------------------------------------------------------
}