package it.shoponline.controller;

import it.shoponline.model.core.carrello.CarrelloInterface;
import it.shoponline.model.core.carrello.CarrelloPagamentoCartaDiCredito;
import it.shoponline.model.core.carrello.CarrelloPagamentoContrassegno;
import it.shoponline.model.core.carrello.RiepilogoCarrello;
import it.shoponline.model.prodotti.astratti.Prodotto;
import it.shoponline.model.utility.Constants;
import it.shoponline.view.ShopOnlineView;
import it.shoponline.view.component.table.RiepilogoCarrelloTableModel;
import java.awt.event.ActionListener;

class PagamentoController
{
	// Riferimento Model
	private CarrelloInterface<Prodotto> carrelloSenzaPagamento;
	private CarrelloInterface<Prodotto> carrello;
	// Riferimento alla View
	private ShopOnlineView view;
	//
	private ActionListener actionListener;
	//
	PagamentoController(ShopOnlineView view, ActionListener actionListener, CarrelloInterface<Prodotto> carrello)
	{
		this.view = view;
		this.carrello = carrello;
		this.carrelloSenzaPagamento = this.carrello;
		this.actionListener = actionListener;
	}

	void sceltaTipoPagamento(String dettaglio)
	{
		// Devo applicare al carrello una decorazione differente
		if (Constants.CARTA_DI_CREDITO.equals(dettaglio))
			this.carrello = new CarrelloPagamentoCartaDiCredito<Prodotto>(this.carrelloSenzaPagamento);
		else if (Constants.CONTRASSEGNO.equals(dettaglio))
			this.carrello = new CarrelloPagamentoContrassegno<Prodotto>(this.carrelloSenzaPagamento);
		// Notifico la view
		notifyViewRiepilogoCarrello();
		view.showDatiPagamento(dettaglio);
	}
	
	void notifyViewRiepilogoCarrello()
	{
		// Modifico il RIEPILOGO DEL CARRELLO della VIEW
		RiepilogoCarrello r = carrello.getRiepilogo();
		view.setTableModelRiepilogoCarrello(new RiepilogoCarrelloTableModel(r, actionListener));
		// Mostro l'importo totale (Etichetta pagamento)
		view.setImportoTotaleCarrelloSenzaPagamento(carrelloSenzaPagamento.getRiepilogo().getImportoTotale());
		view.setImportoTotaleCarrelloConPagamento(r.getImportoTotale());
	}
}