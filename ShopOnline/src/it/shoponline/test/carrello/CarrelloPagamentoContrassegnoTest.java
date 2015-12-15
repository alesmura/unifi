package it.shoponline.test.carrello;

import static org.junit.Assert.assertEquals;
import it.shoponline.model.core.carrello.Carrello;
import it.shoponline.model.core.carrello.CarrelloInterface;
import it.shoponline.model.core.carrello.CarrelloPagamentoContrassegno;
import it.shoponline.model.prodotti.astratti.Prodotto;
import it.shoponline.model.prodotti.concreti.alimenti.Birra;
import it.shoponline.model.utility.Utility;
import org.junit.Test;

public class CarrelloPagamentoContrassegnoTest
{
	@Test
	public void testGetRiepilogo_ConMaggiorazione()
	{
		CarrelloPagamentoContrassegno<Prodotto> c = new CarrelloPagamentoContrassegno<Prodotto>(new Carrello<Prodotto>());
		double prezzo = 10;
		c.aggiungiElemento(new Birra("", prezzo));
		
		double maggiorazione = Utility.moltiplicaDouble(prezzo, CarrelloInterface.PERCENTUALE_MAGGIORAZIONE_CONTRASSEGNO);
		maggiorazione = Utility.dividiDouble(maggiorazione, 100);
		double importoAtteso = Utility.sommaDouble(prezzo, maggiorazione);
		assertEquals(importoAtteso, c.getRiepilogo().getImportoTotale(), 0);
	}
}