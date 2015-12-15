package it.shoponline.test.prodotti;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import it.shoponline.model.prodotti.astratti.Alimento;
import it.shoponline.model.prodotti.astratti.Pacco;
import it.shoponline.model.prodotti.concreti.alimenti.Champagne;
import it.shoponline.model.prodotti.concreti.alimenti.Macarons;
import it.shoponline.model.prodotti.concreti.alimenti.Panettone;
import it.shoponline.model.prodotti.concreti.alimenti.Salame;
import it.shoponline.model.prodotti.concreti.alimenti.Vinsanto;
import it.shoponline.model.prodotti.concreti.pacchi.PaccoFrancia;
import it.shoponline.model.prodotti.concreti.pacchi.PaccoGermania;
import it.shoponline.model.prodotti.concreti.pacchi.PaccoItalia;
import org.junit.Before;
import org.junit.Test;

public class PaccoTest
{
	private Pacco pacco = new PaccoItalia("Pacco Italia");
	private Alimento alimento = new Salame("Salame", 4.89);
	
	private Pacco paccoItalia1 = new PaccoItalia("Pacco Italia 1");
	private Pacco paccoItalia2 = new PaccoItalia("Pacco Italia 2");
	private Pacco paccoItalia3 = new PaccoItalia("Pacco Italia 3");
	private Pacco paccoFrancia = new PaccoItalia("Pacco Francia");
	private Pacco paccoGermania = new PaccoGermania("Pacco Germania");
	private Pacco paccoFranciaVero = new PaccoFrancia("Pacco Francia");
	@Before
	public void setUp() throws Exception
	{
		paccoItalia1.aggiungiProdotto(new Vinsanto("Vinsanto", 10.5));
		paccoItalia1.aggiungiProdotto(new Panettone("Panettone", 8.6));
		
		paccoItalia2.aggiungiProdotto(new Vinsanto("Vinsanto", 10.5));
		paccoItalia2.aggiungiProdotto(new Panettone("Panettone", 8.6));
		
		paccoItalia3.aggiungiProdotto(new Vinsanto("Vinsanto", 10));
		paccoItalia3.aggiungiProdotto(new Panettone("Panettone", 8.6));
		
		paccoFrancia.aggiungiProdotto(new Champagne("Champagne", 22.5));
		paccoFrancia.aggiungiProdotto(new Macarons("Macarons", 3.3));
	}
	//---------------------------------------------------------------------------------------------
	@Test
	public void testgetPrezzo()
	{
		assertEquals(14.32, paccoItalia1.getPrezzo(), 0);
	}
	//---------------------------------------------------------------------------------------------
	@Test
	public void testAggiungiProdotto()
	{
		pacco.aggiungiProdotto(alimento);
		assertTrue(true);
	}
	//---------------------------------------------------------------------------------------------
	public void testRimuoviProdotto()
	{
		pacco.rimuoviProdotto(alimento);
		assertTrue(true);
	}
	//---------------------------------------------------------------------------------------------
	@Test
	public void testEquals_Equal()
	{
		assertNotEquals(paccoItalia1, paccoItalia2);
	}
	@Test
	public void testEquals_NotEqual_1()
	{
		assertNotEquals(paccoItalia1, paccoItalia3);
	}
	@Test
	public void testEquals_NotEqual_2()
	{
		assertNotEquals(paccoItalia1, paccoFrancia);
	}
	//---------------------------------------------------------------------------------------------
	@Test
	public void testClone_Equal1() throws CloneNotSupportedException
	{
		Pacco clone = paccoItalia1.clone();
		assertEquals(paccoItalia1, clone);
		//
		assertFalse(paccoItalia1 == clone);
	}
	@Test
	public void testClone_Equal2() throws CloneNotSupportedException
	{
		Pacco clone = paccoItalia1.clone();
		assertNotEquals(paccoItalia2, clone);
		//
		assertFalse(paccoItalia2 == clone);
	}
	@Test
	public void testClone_NotEqual_1() throws CloneNotSupportedException
	{
		Pacco clone = paccoItalia1.clone();
		assertNotEquals(paccoItalia3, clone);
		//
		assertFalse(paccoItalia3	 == clone);
	}
	@Test
	public void testClone_NotEqual_2() throws CloneNotSupportedException
	{
		Pacco clone = paccoItalia1.clone();
		assertNotEquals(paccoFrancia, clone);
		//
		assertFalse(paccoFrancia == clone);
	}
	//---------------------------------------------------------------------------------------------
	@Test
	public void testGetTestoLettera1() throws CloneNotSupportedException
	{
		assertEquals("Tanti saluti dalla Francia", paccoFranciaVero.getTestoLettera());
	}
	@Test
	public void testGetTestoLettera2() throws CloneNotSupportedException
	{
		assertEquals("Tanti saluti dalla Germania", paccoGermania.getTestoLettera());
	}
	@Test
	public void testGetTestoLettera3() throws CloneNotSupportedException
	{
		assertEquals("Tanti saluti dall'Italia", paccoItalia1.getTestoLettera());
	}
}