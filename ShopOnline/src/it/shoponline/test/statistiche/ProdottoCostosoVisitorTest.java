package it.shoponline.test.statistiche;

import static org.junit.Assert.assertEquals;
import it.shoponline.model.prodotti.astratti.Alimento;
import it.shoponline.model.prodotti.astratti.Dolce;
import it.shoponline.model.prodotti.astratti.Prodotto;
import it.shoponline.model.prodotti.statistiche.VisitorUtility;
import it.shoponline.model.prodotti.statistiche.checker.ProdottoChecker;
import it.shoponline.model.prodotti.statistiche.concrete.ProdottoCostosoVisitor;
import it.shoponline.model.prodotti.statistiche.strategy.ProdottoMenoCostosoStrategy;
import it.shoponline.model.prodotti.statistiche.strategy.ProdottoPiuCostosoStrategy;
import org.junit.Before;
import org.junit.Test;

public class ProdottoCostosoVisitorTest extends StatisticheTest
{
	
	@Before
	public void setUp() throws Exception
	{
	}
	@Test
	public void testGetResult_PiuCostoso_1()
	{
		ProdottoChecker ck = new ProdottoChecker(Prodotto.class);
		ProdottoCostosoVisitor pcv = new ProdottoCostosoVisitor(new ProdottoPiuCostosoStrategy(ck));
		VisitorUtility.analizzaProdotti(prodottoList, pcv);
		assertEquals(1, pcv.getProdottoList().size(), 0);
		assertEquals(paccoPiuCostoso, pcv.getProdottoList().get(0));
	}
	@Test
	public void testGetResult_PiuCostoso_2()
	{
		ProdottoChecker ck = new ProdottoChecker(Alimento.class);
		ProdottoCostosoVisitor pcv = new ProdottoCostosoVisitor(new ProdottoPiuCostosoStrategy(ck));
		VisitorUtility.analizzaProdotti(prodottoList, pcv);
		assertEquals(1, pcv.getProdottoList().size(), 0);
		assertEquals(bevandaPiuCostosa, pcv.getProdottoList().get(0));
	}
	@Test
	public void testGetResult_PiuCostoso_3()
	{
		ProdottoChecker ck = new ProdottoChecker(Dolce.class);
		ProdottoCostosoVisitor pcv = new ProdottoCostosoVisitor(new ProdottoPiuCostosoStrategy(ck));
		VisitorUtility.analizzaProdotti(prodottoList, pcv);
		assertEquals(1, pcv.getProdottoList().size(), 0);
		assertEquals(dolcePiuCostoso, pcv.getProdottoList().get(0));
	}
	@Test
	public void testGetResult_PiuCostoso_4()
	{
		ProdottoChecker ck = new ProdottoChecker(Dolce.class);
		ProdottoCostosoVisitor pcv = new ProdottoCostosoVisitor(new ProdottoPiuCostosoStrategy(ck));
		VisitorUtility.analizzaProdotti(paccoList, pcv);
		assertEquals(0, pcv.getProdottoList().size(), 0);
	}
	//---------------------------------------------------------------------------------------------
	@Test
	public void testGetResult_MenoCostoso_1()
	{
		ProdottoChecker ck = new ProdottoChecker(Prodotto.class);
		ProdottoCostosoVisitor pcv = new ProdottoCostosoVisitor(new ProdottoMenoCostosoStrategy(ck));
		VisitorUtility.analizzaProdotti(prodottoList, pcv);
		assertEquals(1, pcv.getProdottoList().size(), 0);
		assertEquals(bevandaMenoCostosa, pcv.getProdottoList().get(0));
	}
	@Test
	public void testGetResult_MenoCostoso_2()
	{
		ProdottoChecker ck = new ProdottoChecker(Alimento.class);
		ProdottoCostosoVisitor pcv = new ProdottoCostosoVisitor(new ProdottoMenoCostosoStrategy(ck));
		VisitorUtility.analizzaProdotti(prodottoList, pcv);
		assertEquals(1, pcv.getProdottoList().size(), 0);
		assertEquals(bevandaMenoCostosa, pcv.getProdottoList().get(0));
	}
	@Test
	public void testGetResult_MenoCostoso_3()
	{
		ProdottoChecker ck = new ProdottoChecker(Dolce.class);
		ProdottoCostosoVisitor pcv = new ProdottoCostosoVisitor(new ProdottoMenoCostosoStrategy(ck));
		VisitorUtility.analizzaProdotti(prodottoList, pcv);
		assertEquals(1, pcv.getProdottoList().size(), 0);
		assertEquals(dolceMenoCostoso, pcv.getProdottoList().get(0));
	}
	@Test
	public void testGetResult_MenoCostoso_4()
	{
		ProdottoChecker ck = new ProdottoChecker(Dolce.class);
		ProdottoCostosoVisitor pcv = new ProdottoCostosoVisitor(new ProdottoMenoCostosoStrategy(ck));
		VisitorUtility.analizzaProdotti(paccoList, pcv);
		assertEquals(0, pcv.getProdottoList().size(), 0);
	}
}