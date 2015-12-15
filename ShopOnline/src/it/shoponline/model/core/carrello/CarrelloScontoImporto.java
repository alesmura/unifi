package it.shoponline.model.core.carrello;

import it.shoponline.model.prodotti.astratti.Prodotto;
import it.shoponline.model.utility.Constants;
import it.shoponline.model.utility.Utility;

public class CarrelloScontoImporto<E extends Prodotto> extends CarrelloDecorator<E>
{
	public CarrelloScontoImporto(CarrelloInterface<E> carrello)
	{
		super(carrello);
	}

	@Override
	public RiepilogoCarrello getRiepilogo()
	{
		RiepilogoCarrello riepilogoCarrello = super.getRiepilogo();
		if (riepilogoCarrello.getImportoTotale() > IMPORTO_MINIMO_SCONTO)
		{
			double sconto = Utility.moltiplicaDouble(riepilogoCarrello.getImportoTotale(), PERCENTUALE_SCONTO_IMPORTO);
			sconto = Utility.dividiDouble(sconto, 100);
			sconto = -sconto;
			String descr = "Sconto del " + Utility.formattaDouble(PERCENTUALE_SCONTO_IMPORTO) + "% per importo superiore a " + Utility.formattaDouble(IMPORTO_MINIMO_SCONTO) + Constants.EURO;
			riepilogoCarrello.aggiungiRiga(new RigaRiepilogoCarrello(descr, 0, sconto));
		}
		return riepilogoCarrello;
	}
}