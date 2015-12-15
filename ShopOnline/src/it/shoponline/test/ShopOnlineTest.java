package it.shoponline.test;

import it.shoponline.test.carrello.CarrelloIVATest;
import it.shoponline.test.carrello.CarrelloPagamentoContrassegnoTest;
import it.shoponline.test.carrello.CarrelloScontoImportoTest;
import it.shoponline.test.carrello.CarrelloSpeseSpedizioneTest;
import it.shoponline.test.carrello.CarrelloTest;
import it.shoponline.test.creator.PaccoBuilderTest;
import it.shoponline.test.prodotti.AlimentoTest;
import it.shoponline.test.prodotti.PaccoTest;
import it.shoponline.test.statistiche.PrezzoMedioVisitorTest;
import it.shoponline.test.statistiche.ProdottoCheckerTest;
import it.shoponline.test.statistiche.ProdottoCostosoVisitorTest;
import it.shoponline.test.utility.UtilityTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
		AlimentoTest.class,
		CarrelloIVATest.class,
		CarrelloPagamentoContrassegnoTest.class,
		CarrelloScontoImportoTest.class,
		CarrelloSpeseSpedizioneTest.class,
		CarrelloTest.class,
		PaccoBuilderTest.class,
		PaccoTest.class,
		PrezzoMedioVisitorTest.class,
		ProdottoCheckerTest.class,
		ProdottoCostosoVisitorTest.class,
		UtilityTest.class
})
public class ShopOnlineTest
{
}