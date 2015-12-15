package it.shoponline.test.carrello;

import static org.junit.Assert.assertEquals;
import it.shoponline.model.core.carrello.Carrello;
import it.shoponline.model.core.carrello.CarrelloInterface;
import it.shoponline.model.core.carrello.CarrelloScontoImporto;
import it.shoponline.model.prodotti.astratti.Prodotto;
import it.shoponline.model.prodotti.concreti.alimenti.Birra;
import it.shoponline.model.utility.Utility;
import org.junit.Test;

public class CarrelloScontoImportoTest
{
	@Test
	public void testGetRiepilogo_SenzaSconto()
	{
		CarrelloScontoImporto<Prodotto> c = new CarrelloScontoImporto<Prodotto>(new Carrello<Prodotto>());
		c.aggiungiElemento(new Birra("", CarrelloInterface.IMPORTO_MINIMO_SCONTO));
		assertEquals(CarrelloInterface.IMPORTO_MINIMO_SCONTO, c.getRiepilogo().getImportoTotale(), 0);
	}
	@Test
	public void testGetRiepilogo_ConSconto()
	{

		CarrelloScontoImporto<Prodotto> c = new CarrelloScontoImporto<Prodotto>(new Carrello<Prodotto>());
		double importo = Utility.sommaDouble(CarrelloInterface.IMPORTO_MINIMO_SCONTO, 1);
		c.aggiungiElemento(new Birra("", importo));
		
		double sconto = Utility.moltiplicaDouble(importo, CarrelloInterface.PERCENTUALE_SCONTO_IMPORTO);
		sconto = Utility.dividiDouble(sconto, 100);
		double importoAtteso = Utility.sommaDouble(importo, -sconto);		
		
		assertEquals(importoAtteso, c.getRiepilogo().getImportoTotale(), 0);
	}
}