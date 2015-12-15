package it.shoponline.model.core.carrello;

import it.shoponline.model.prodotti.astratti.Prodotto;
import it.shoponline.model.utility.Utility;

public class CarrelloPagamentoContrassegno<E extends Prodotto> extends CarrelloDecorator<E>
{
	public CarrelloPagamentoContrassegno(CarrelloInterface<E> carrello)
	{
		super(carrello);
	}

	@Override
	public RiepilogoCarrello getRiepilogo()
	{
		RiepilogoCarrello riepilogoCarrello = super.getRiepilogo();
		double maggiorazione = Utility.moltiplicaDouble(riepilogoCarrello.getImportoTotale(), PERCENTUALE_MAGGIORAZIONE_CONTRASSEGNO);
		maggiorazione = Utility.dividiDouble(maggiorazione, 100);
		String descr = "Maggiorazione per pagamento con contrassegno del " + Utility.formattaDouble(PERCENTUALE_MAGGIORAZIONE_CONTRASSEGNO) + "%";
		riepilogoCarrello.aggiungiRiga(new RigaRiepilogoCarrello(descr, 0, maggiorazione));
		return riepilogoCarrello;
	}
}