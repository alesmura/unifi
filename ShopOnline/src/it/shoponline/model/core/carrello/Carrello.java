package it.shoponline.model.core.carrello;

import it.shoponline.model.prodotti.astratti.Prodotto;
import it.shoponline.model.utility.Utility;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.TreeMap;

public class Carrello<E extends Prodotto> extends Observable implements CarrelloInterface<E>
{
	// Faccio accessi indicizzati, meglio ArrayList di LinkedList
	private List<E> elementoList = new ArrayList<E>();
	private CarrelloCaretaker carrelloCaretaker = new CarrelloCaretaker();
	@Override
	public void aggiungiElemento(E e)
	{
		int indice = elementoList.size();
		add(indice, e);
		saveToMemento(OPERAZIONE_CARRELLO.AGGIUNGI, e, indice);
	}
	@Override
	public void rimuoviElemento(E e)
	{
		int indice = elementoList.indexOf(e);
		remove(indice);
		saveToMemento(OPERAZIONE_CARRELLO.RIMUOVI, e, indice);
	}
	@Override
	public boolean isEmpty()
	{
		return elementoList.isEmpty();
	}
	@Override
	public void undo()
	{
		this.carrelloCaretaker.undo();
	}
	@Override
	public void redo()
	{
		this.carrelloCaretaker.redo();
	}
	@Override
	public RiepilogoCarrello getRiepilogo()
	{
		double importoTotale = 0;
		Map<String, RigaRiepilogoCarrello> righeMap = new TreeMap<String, RigaRiepilogoCarrello>();
		for (Prodotto p : elementoList)
		{
			String key = p.getNome();
			RigaRiepilogoCarrello r = righeMap.get(key);
			if (r == null)
				r = new RigaRiepilogoCarrello(p.getClass().getName(), p.getNome(), 0, p.getPrezzo());
			r.incrementaQuantita();
			righeMap.put(key, r);
			importoTotale = Utility.sommaDouble(importoTotale, p.getPrezzo());
		}
		return new RiepilogoCarrello(new LinkedList<RigaRiepilogoCarrello>(righeMap.values()), importoTotale);
	}
	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		for (E e : elementoList)
			sb.append(e).append(",");
		return sb.toString();
	}
	@Override
	public List<E> getElementiNelCarrello()
	{
		return elementoList;
	}
	public int countElement(Object obj)
	{
		int conta = 0;
		for (E e : elementoList)
			if (e.equals(obj))
				conta++;
		return conta;
	}
	private void saveToMemento(OPERAZIONE_CARRELLO t, E e, int indice)
	{
		carrelloCaretaker.addMemento(new OperazioneCarrelloMemento<E>(t, e, indice));
	}
	private void remove(int indice)
	{
		E e = elementoList.get(indice);
		elementoList.remove(indice);
		updateObservers(e);
	}
	private void add(int indice, E e)
	{
		elementoList.add(indice, e);
		updateObservers(e);
	}
	private void updateObservers(E e)
	{
		System.out.println("Model: modifica dall'esterno, aggiorno gli observers");
		setChanged();
		notifyObservers(e);
	}
	private class CarrelloCaretaker
	{
		// Utilizzo due stack per elencare le operazioni undo/redo
		private Deque<OperazioneCarrelloMemento<E>> operazioniUndo = new LinkedList<OperazioneCarrelloMemento<E>>();
		private Deque<OperazioneCarrelloMemento<E>> operazioniRedo = new LinkedList<OperazioneCarrelloMemento<E>>();
		private void addMemento(OperazioneCarrelloMemento<E> op)
		{
			// Eseguo una operazione
			operazioniUndo.push(op);
			// Pulisco le operazioni di REDO
			operazioniRedo.clear();
		}
		private void undo()
		{
			if (operazioniUndo.isEmpty())
				return;
			OperazioneCarrelloMemento<E> op = operazioniUndo.pop();
			if (op.getOperazione() == OPERAZIONE_CARRELLO.AGGIUNGI)
				remove(op.getIndice());
			else if (op.getOperazione() == OPERAZIONE_CARRELLO.RIMUOVI)
				add(op.getIndice(), op.getElemento());
			operazioniRedo.push(op);
		}
		private void redo()
		{
			if (operazioniRedo.isEmpty())
				return;
			OperazioneCarrelloMemento<E> op = operazioniRedo.pop();
			if (op.getOperazione() == OPERAZIONE_CARRELLO.AGGIUNGI)
				add(op.getIndice(), op.getElemento());
			else if (op.getOperazione() == OPERAZIONE_CARRELLO.RIMUOVI)
				remove(op.getIndice());
			operazioniUndo.push(op);
		}
	}
}