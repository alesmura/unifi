package it.shoponline.controller;

import it.shoponline.model.core.carrello.CarrelloInterface;
import it.shoponline.model.prodotti.astratti.Prodotto;
import it.shoponline.view.ShopOnlineView;
import javax.swing.JOptionPane;

class GestioneCarrelloController
{
	// Riferimento Model
	private CarrelloInterface<Prodotto> carrello;
	// Riferimento alla View
	private ShopOnlineView view;
	//
	private ProdottiInVendita prodottiInVendita;
	//
	GestioneCarrelloController(ShopOnlineView view, CarrelloInterface<Prodotto> carrello, ProdottiInVendita prodottiInVendita)
	{
		this.view = view;
		this.carrello = carrello;
		this.prodottiInVendita = prodottiInVendita;
	}
	void carrelloUndo()
	{
		carrello.undo();
	}
	
	void carrelloRedo()
	{
		carrello.redo();
	}
	
	void aggiungiElementoCarrello(String dettaglio)
	{
		try
		{
			carrello.aggiungiElemento(getProdottoClonato(dettaglio));
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(view, e.getMessage());
		}
	}

	void rimuoviElementoCarrello(String dettaglio)
	{
		try
		{
			carrello.rimuoviElemento(getProdottoClonato(dettaglio));
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(view, e.getMessage());
		}
	}
	
	private Prodotto getProdottoClonato(String className) throws Exception
	{
		return prodottiInVendita.getProdottoClonato(className);
	}
}