package it.shoponline.model.core.carrello;

import it.shoponline.model.prodotti.astratti.Prodotto;
import java.util.List;

public interface CarrelloInterface<E extends Prodotto>
{
	final static double IMPORTO_MINIMO_SPESE_SPEDIZIONE = 150;
	final static double IMPORTO_SPESE_SPEDIZIONE = 20;
	final static double IMPORTO_MINIMO_SCONTO = 200;
	final static double PERCENTUALE_SCONTO_IMPORTO = 10;
	final static double PERCENTUALE_MAGGIORAZIONE_CONTRASSEGNO = 5;
	final static double PERCENTUALE_IVA = 4;
	public void aggiungiElemento(E p);
	public void rimuoviElemento(E p);
	public boolean isEmpty();
	public void undo();
	public void redo();
	public RiepilogoCarrello getRiepilogo();
	public List<E> getElementiNelCarrello();
}