package it.shoponline.model.core.carrello;

import it.shoponline.model.prodotti.astratti.Prodotto;
import java.util.List;


public abstract class CarrelloDecorator<E extends Prodotto> implements CarrelloInterface<E>
{
	private CarrelloInterface<E> carrello;
	public CarrelloDecorator(CarrelloInterface<E> carrello)
	{
		this.carrello = carrello;
	}
	@Override
	public void aggiungiElemento(E p)
	{
		carrello.aggiungiElemento(p);
	}
	@Override
	public void rimuoviElemento(E p)
	{
		carrello.rimuoviElemento(p);
	}
	@Override
	public boolean isEmpty()
	{
		return carrello.isEmpty();
	}
	@Override
	public void undo()
	{
		carrello.undo();
	}
	@Override
	public void redo()
	{
		carrello.redo();
	}
	@Override
	public RiepilogoCarrello getRiepilogo()
	{
		return carrello.getRiepilogo();
	}
	@Override
	public List<E> getElementiNelCarrello()
	{
		return carrello.getElementiNelCarrello();
	}
}