package it.shoponline.test.carrello;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import it.shoponline.model.core.carrello.Carrello;
import it.shoponline.model.core.carrello.CarrelloIVA;
import it.shoponline.model.core.carrello.CarrelloInterface;
import it.shoponline.model.prodotti.astratti.Prodotto;
import it.shoponline.model.prodotti.concreti.alimenti.Birra;
import it.shoponline.model.utility.Utility;

public class CarrelloIVATest
{
	@Test
	public void testGetRiepilogo()
	{
		CarrelloIVA<Prodotto> c = new CarrelloIVA<Prodotto>(new Carrello<Prodotto>());
		double prezzo = 10;
		c.aggiungiElemento(new Birra("", prezzo));
		double importo = Utility.moltiplicaDouble(prezzo, CarrelloInterface.PERCENTUALE_IVA);
		importo = Utility.dividiDouble(importo, 100);
		importo = Utility.sommaDouble(importo, prezzo);
		assertEquals(importo, c.getRiepilogo().getImportoTotale(), 0);
	}
}