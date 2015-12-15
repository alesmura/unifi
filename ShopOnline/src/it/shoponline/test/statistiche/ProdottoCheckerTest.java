package it.shoponline.test.statistiche;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import it.shoponline.model.prodotti.astratti.Alimento;
import it.shoponline.model.prodotti.astratti.Bevanda;
import it.shoponline.model.prodotti.astratti.Dolce;
import it.shoponline.model.prodotti.astratti.Pacco;
import it.shoponline.model.prodotti.astratti.Prodotto;
import it.shoponline.model.prodotti.astratti.Salume;
import it.shoponline.model.prodotti.creator.PaccoBuilder;
import it.shoponline.model.prodotti.creator.ProdottoFranciaFactory;
import it.shoponline.model.prodotti.creator.ProdottoGermaniaFactory;
import it.shoponline.model.prodotti.creator.ProdottoItaliaFactory;
import it.shoponline.model.prodotti.creator.ProdottoNazioneAbstractFactory;
import it.shoponline.model.prodotti.statistiche.checker.ProdottoChecker;
import java.util.LinkedList;
import org.junit.Before;
import org.junit.Test;

public class ProdottoCheckerTest extends StatisticheTest
{
	@Before
	public void initalize()
	{
		bevandaList = new LinkedList<Bevanda>();
		dolceList = new LinkedList<Dolce>();
		salumeList = new LinkedList<Salume>();
		paccoList = new LinkedList<Pacco>();
		// Factory
		ProdottoNazioneAbstractFactory prodottoFranciaFactory = new ProdottoFranciaFactory();
		ProdottoNazioneAbstractFactory prodottoGermaniaFactory = new ProdottoGermaniaFactory();
		ProdottoNazioneAbstractFactory prodottoItaliaFactory = new ProdottoItaliaFactory();
		// Bevande
		bevandaList.add(prodottoFranciaFactory.makeNewBevanda());
		bevandaList.add(prodottoGermaniaFactory.makeNewBevanda());
		bevandaList.add(prodottoItaliaFactory.makeNewBevanda());
		// Dolci
		dolceList.add(prodottoFranciaFactory.makeNewDolce());
		dolceList.add(prodottoGermaniaFactory.makeNewDolce());
		dolceList.add(prodottoItaliaFactory.makeNewDolce());
		// Salumi
		salumeList.add(prodottoFranciaFactory.makeNewSalume());
		salumeList.add(prodottoGermaniaFactory.makeNewSalume());
		salumeList.add(prodottoItaliaFactory.makeNewSalume());
		// Pacchi
		PaccoBuilder builder = new PaccoBuilder(prodottoFranciaFactory);
		paccoList.add(builder.getPacco());
		builder = new PaccoBuilder(prodottoGermaniaFactory);
		paccoList.add(builder.getPacco());
		builder = new PaccoBuilder(prodottoItaliaFactory);
		paccoList.add(builder.getPacco());
		// Lista totale
		prodottoList = new LinkedList<Prodotto>();
		prodottoList.addAll(bevandaList);
		prodottoList.addAll(dolceList);
		prodottoList.addAll(salumeList);
		prodottoList.addAll(paccoList);
	}
	
	//---------------------------------------------------------------------------------------------
	@Test
	public void testIsProdottoDaElaborare_1()
	{
		ProdottoChecker ck = new ProdottoChecker(Bevanda.class);
		assertTrue(ck.isProdottoDaElaborare(bevandaList.get(0)));
	}
	@Test
	public void testIsProdottoDaElaborare_2()
	{
		ProdottoChecker ck = new ProdottoChecker(Bevanda.class);
		assertFalse(ck.isProdottoDaElaborare(dolceList.get(0)));
	}
	@Test
	public void testIsProdottoDaElaborare_3()
	{
		ProdottoChecker ck = new ProdottoChecker(Prodotto.class);
		assertTrue(ck.isProdottoDaElaborare(salumeList.get(0)));
	}
	//---------------------------------------------------------------------------------------------
	
	@Test
	public void testGetProdottoFiltratoList_1()
	{
		ProdottoChecker ck = new ProdottoChecker(Bevanda.class);
		assertEquals(0, ck.getProdottoFiltratoList(salumeList).size());
	}
	@Test
	public void testGetProdottoFiltratoList_2()
	{
		ProdottoChecker ck = new ProdottoChecker(Bevanda.class);
		assertEquals(3, ck.getProdottoFiltratoList(bevandaList).size());
	}
	@Test
	public void testGetProdottoFiltratoList_3()
	{
		ProdottoChecker ck = new ProdottoChecker(Alimento.class);
		assertEquals(9, ck.getProdottoFiltratoList(prodottoList).size());
	}
	@Test
	public void testGetProdottoFiltratoList_4()
	{
		ProdottoChecker ck = new ProdottoChecker(Pacco.class);
		assertEquals(3, ck.getProdottoFiltratoList(prodottoList).size());
	}
	@Test
	public void testGetProdottoFiltratoList_5()
	{
		ProdottoChecker ck = new ProdottoChecker(Prodotto.class);
		assertEquals(12, ck.getProdottoFiltratoList(prodottoList).size());
	}
	//---------------------------------------------------------------------------------------------

	@Test
	public void testGetCheckClassName()
	{
		ProdottoChecker ck = new ProdottoChecker(Prodotto.class);
		assertEquals(Prodotto.class.getSimpleName(), ck.getCheckClassName());
	}	
}
