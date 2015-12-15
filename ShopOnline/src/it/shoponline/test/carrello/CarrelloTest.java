package it.shoponline.test.carrello;

import static org.junit.Assert.*;
import it.shoponline.model.core.carrello.Carrello;
import it.shoponline.model.core.carrello.RiepilogoCarrello;
import it.shoponline.model.prodotti.astratti.Bevanda;
import it.shoponline.model.prodotti.astratti.Prodotto;
import it.shoponline.model.prodotti.concreti.alimenti.Birra;
import it.shoponline.model.utility.Utility;
import it.shoponline.test.statistiche.StatisticheTest;
import org.junit.Test;

public class CarrelloTest extends StatisticheTest
{
	@Test
	public void testAggiungiElemento()
	{
		Carrello<Prodotto> c = new Carrello<Prodotto>();
		for (Bevanda b : bevandaList)
			c.aggiungiElemento(b);
		assertEquals(3, c.getElementiNelCarrello().size());
	}
	//---------------------------------------------------------------------------------------------
	@Test
	public void testRimuoviElemento()
	{
		Carrello<Prodotto> c = new Carrello<Prodotto>();
		for (Bevanda b : bevandaList)
			c.aggiungiElemento(b);
		c.rimuoviElemento(bevandaMenoCostosa);
		assertEquals(2, c.getElementiNelCarrello().size());
	}
	//---------------------------------------------------------------------------------------------
	@Test
	public void testUndo_1()
	{
		Carrello<Prodotto> c = new Carrello<Prodotto>();
		for (Bevanda b : bevandaList)
			c.aggiungiElemento(b);
		c.aggiungiElemento(new Birra("", 0));
		c.undo();
		assertEquals(bevandaList, c.getElementiNelCarrello());
	}
	@Test
	public void testUndo_2()
	{
		Carrello<Prodotto> c = new Carrello<Prodotto>();
		for (Bevanda b : bevandaList)
			c.aggiungiElemento(b);
		c.undo();
		assertNotEquals(bevandaList, c.getElementiNelCarrello());
	}
	//---------------------------------------------------------------------------------------------
	@Test
	public void testRedo_1()
	{
		Carrello<Prodotto> c = new Carrello<Prodotto>();
		for (Bevanda b : bevandaList)
			c.aggiungiElemento(b);
		c.undo();
		c.redo();
		assertEquals(bevandaList, c.getElementiNelCarrello());
	}
	@Test
	public void testRedo_2()
	{
		Carrello<Prodotto> c = new Carrello<Prodotto>();
		for (Bevanda b : bevandaList)
			c.aggiungiElemento(b);
		c.aggiungiElemento(new Birra("", 0));
		c.undo();
		c.redo();
		assertNotEquals(bevandaList, c.getElementiNelCarrello());
	}
	//---------------------------------------------------------------------------------------------
	@Test
	public void testUndoRedo()
	{
		Carrello<Prodotto> c = new Carrello<Prodotto>();
		c.undo();
		c.redo();
		for (Bevanda b : bevandaList)
			c.aggiungiElemento(b);
		c.undo();
		assertNotEquals(bevandaList, c.getElementiNelCarrello());
		c.redo();
		assertEquals(bevandaList, c.getElementiNelCarrello());
		c.undo();
		c.undo();
		c.undo();
		assertEquals(0, c.getElementiNelCarrello().size());
		c.redo();
		c.redo();
		assertNotEquals(bevandaList, c.getElementiNelCarrello());
		c.undo();
		c.redo();
		c.redo();
		assertEquals(bevandaList, c.getElementiNelCarrello());
	}
	//---------------------------------------------------------------------------------------------
	@Test
	public void testGetRiepilogo()
	{
		double tot = 0;
		Carrello<Prodotto> c = new Carrello<Prodotto>();
		for (Bevanda b : bevandaList)
		{
			c.aggiungiElemento(b);
			tot = Utility.sommaDouble(tot, b.getPrezzo());
		}
		RiepilogoCarrello r = c.getRiepilogo();
		assertEquals(tot, r.getImportoTotale(), 0);
	}
	//---------------------------------------------------------------------------------------------
	@Test
	public void testGetElementiNelCarrello()
	{
		Carrello<Prodotto> c = new Carrello<Prodotto>();
		for (Bevanda b : bevandaList)
			c.aggiungiElemento(b);
		assertEquals(bevandaList, c.getElementiNelCarrello());
	}
	//---------------------------------------------------------------------------------------------
	@Test
	public void testCountElement_1()
	{
		Carrello<Prodotto> c = new Carrello<Prodotto>();
		Birra bx = new Birra("Prova", 10);
		c.aggiungiElemento(bx);
		c.aggiungiElemento(bx);
		c.aggiungiElemento(new Birra("Prova", 10));
		for (Bevanda b : bevandaList)
			c.aggiungiElemento(b);
		assertEquals(3, c.countElement(bx));
	}
	@Test
	public void testCountElement_2()
	{
		Carrello<Prodotto> c = new Carrello<Prodotto>();
		Birra bx = new Birra("Prova", 10);
		c.aggiungiElemento(bx);
		c.aggiungiElemento(bx);
		c.aggiungiElemento(new Birra("Prova", 10));
		for (Bevanda b : bevandaList)
			c.aggiungiElemento(b);
		assertEquals(0, c.countElement(new Birra("Prova 2", 10)));
		assertEquals(0, c.countElement(new Birra("Prova", 11)));
	}
	//---------------------------------------------------------------------------------------------
	@Test
	public void testIsEmpty()
	{
		Carrello<Prodotto> c = new Carrello<Prodotto>();
		Birra bx = new Birra("Prova", 10);
		c.aggiungiElemento(bx);
		assertFalse(c.isEmpty());
	}
	@Test
	public void testIsEmpty2()
	{
		Carrello<Prodotto> c = new Carrello<Prodotto>();
		Birra bx = new Birra("Prova", 10);
		c.aggiungiElemento(bx);
		c.rimuoviElemento(bx);
		assertTrue(c.isEmpty());
	}
}