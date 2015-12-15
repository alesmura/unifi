package it.shoponline.model.core.carrello;

import it.shoponline.model.prodotti.astratti.Prodotto;
import it.shoponline.model.utility.Constants;
import it.shoponline.model.utility.Utility;

public class CarrelloSpeseSpedizione<E extends Prodotto> extends CarrelloDecorator<E>
{
	public CarrelloSpeseSpedizione(CarrelloInterface<E> carrello)
	{
		super(carrello);
	}

	@Override
	public RiepilogoCarrello getRiepilogo()
	{
		RiepilogoCarrello riepilogoCarrello = super.getRiepilogo();
		String descr = "Spese di spedizione gratuite per importi superiori a " + Utility.formattaDouble(IMPORTO_MINIMO_SPESE_SPEDIZIONE) + Constants.EURO;
		double importo = 0;
		if (riepilogoCarrello.getImportoTotale() < IMPORTO_MINIMO_SPESE_SPEDIZIONE)
		{
			descr = "Spese di spedizione";
			importo = IMPORTO_SPESE_SPEDIZIONE;
		}
		riepilogoCarrello.aggiungiRiga(new RigaRiepilogoCarrello(descr, 0, importo));
		return riepilogoCarrello;
	}
}