package it.shoponline.test.carrello;

import static org.junit.Assert.assertEquals;
import it.shoponline.model.core.carrello.Carrello;
import it.shoponline.model.core.carrello.CarrelloInterface;
import it.shoponline.model.core.carrello.CarrelloSpeseSpedizione;
import it.shoponline.model.prodotti.astratti.Prodotto;
import it.shoponline.model.prodotti.concreti.alimenti.Birra;
import it.shoponline.model.utility.Utility;
import it.shoponline.test.statistiche.StatisticheTest;
import org.junit.Test;

public class CarrelloSpeseSpedizioneTest extends StatisticheTest
{
	@Test
	public void testGetRiepilogo_SenzaSpese()
	{
		CarrelloSpeseSpedizione<Prodotto> c = new CarrelloSpeseSpedizione<Prodotto>(new Carrello<Prodotto>());
		c.aggiungiElemento(new Birra("", CarrelloInterface.IMPORTO_MINIMO_SPESE_SPEDIZIONE));
		assertEquals(CarrelloInterface.IMPORTO_MINIMO_SPESE_SPEDIZIONE, c.getRiepilogo().getImportoTotale(), 0);
	}
	@Test
	public void testGetRiepilogo_ConSpese()
	{

		CarrelloSpeseSpedizione<Prodotto> c = new CarrelloSpeseSpedizione<Prodotto>(new Carrello<Prodotto>());
		double importo = Utility.sommaDouble(CarrelloInterface.IMPORTO_MINIMO_SPESE_SPEDIZIONE, -1);
		c.aggiungiElemento(new Birra("", importo));
		double importoAtteso = Utility.sommaDouble(importo, CarrelloInterface.IMPORTO_SPESE_SPEDIZIONE);
		assertEquals(importoAtteso, c.getRiepilogo().getImportoTotale(), 0);
	}
}