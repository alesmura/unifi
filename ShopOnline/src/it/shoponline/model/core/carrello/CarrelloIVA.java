package it.shoponline.model.core.carrello;

import it.shoponline.model.prodotti.astratti.Prodotto;
import it.shoponline.model.utility.Utility;

public class CarrelloIVA<E extends Prodotto> extends CarrelloDecorator<E>
{
	public CarrelloIVA(CarrelloInterface<E> carrello)
	{
		super(carrello);
	}
	@Override
	public RiepilogoCarrello getRiepilogo()
	{
		RiepilogoCarrello riepilogoCarrello = super.getRiepilogo();
		double iva = Utility.moltiplicaDouble(riepilogoCarrello.getImportoTotale(), PERCENTUALE_IVA);
		iva = Utility.dividiDouble(iva, 100);
		String descr = "Iva del " + Utility.formattaDouble(PERCENTUALE_IVA) + "%";
		riepilogoCarrello.aggiungiRiga(new RigaRiepilogoCarrello(descr, 0, iva));
		return riepilogoCarrello;
	}
}