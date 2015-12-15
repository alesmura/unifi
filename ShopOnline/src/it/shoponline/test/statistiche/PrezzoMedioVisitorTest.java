package it.shoponline.test.statistiche;

import static org.junit.Assert.assertEquals;
import it.shoponline.model.prodotti.astratti.Alimento;
import it.shoponline.model.prodotti.astratti.Prodotto;
import it.shoponline.model.prodotti.statistiche.VisitorUtility;
import it.shoponline.model.prodotti.statistiche.checker.ProdottoChecker;
import it.shoponline.model.prodotti.statistiche.concrete.PrezzoMedioVisitor;
import org.junit.Test;

public class PrezzoMedioVisitorTest extends StatisticheTest
{
	@Test
	public void testGetPrezzoMedio_1()
	{
		ProdottoChecker ck = new ProdottoChecker(Prodotto.class);
		PrezzoMedioVisitor pmv = new PrezzoMedioVisitor(ck);
		VisitorUtility.analizzaProdotti(prodottoList, pmv);
		assertEquals(11.75, pmv.getPrezzoMedio(), 0);
	}
	@Test
	public void testGetPrezzoMedio_2()
	{
		ProdottoChecker ck = new ProdottoChecker(Alimento.class);
		PrezzoMedioVisitor pmv = new PrezzoMedioVisitor(ck);
		VisitorUtility.analizzaProdotti(prodottoList, pmv);
		assertEquals(8.95, pmv.getPrezzoMedio(), 0);
	}
	@Test
	public void testGetPrezzoMedio_3()
	{
		ProdottoChecker ck = new ProdottoChecker(Alimento.class);
		PrezzoMedioVisitor pmv = new PrezzoMedioVisitor(ck);
		VisitorUtility.analizzaProdotti(paccoList, pmv);
		assertEquals(0, pmv.getPrezzoMedio(), 0);
	}
}