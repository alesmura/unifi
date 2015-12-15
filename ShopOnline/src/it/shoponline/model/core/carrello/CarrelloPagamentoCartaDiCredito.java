package it.shoponline.model.core.carrello;

import it.shoponline.model.prodotti.astratti.Prodotto;

/*
 * Per il pagamento con carta di credito non faccio niente di strano
 */
public class CarrelloPagamentoCartaDiCredito<E extends Prodotto> extends CarrelloDecorator<E>
{
	public CarrelloPagamentoCartaDiCredito(CarrelloInterface<E> carrello)
	{
		super(carrello);
	}
}