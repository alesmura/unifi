package it.shoponline.model.prodotti.statistiche.strategy;

import it.shoponline.model.prodotti.astratti.Prodotto;
import it.shoponline.model.prodotti.statistiche.checker.ProdottoChecker;

public abstract class StatisticheStrategy
{
	private ProdottoChecker checker = null;
	public StatisticheStrategy(ProdottoChecker checker)
	{
		this.checker = checker;
	}
	public void analizzaProdotto(Prodotto p)
	{
		if (isProdottoDaElaborare(p))
			analizza(p);
	}
	abstract void analizza(Prodotto p);
	@Override
	public abstract String toString();
	protected String getCheckerName()
	{
		return checker.getCheckClassName();
	}
	private boolean isProdottoDaElaborare(Prodotto p)
	{
		return checker.isProdottoDaElaborare(p);
	}
}