package it.shoponline.model.prodotti.statistiche.strategy;

import it.shoponline.model.prodotti.astratti.Prodotto;
import it.shoponline.model.prodotti.statistiche.checker.ProdottoChecker;
import it.shoponline.model.utility.Utility;

public final class PrezzoMedioCalculator extends StatisticheStrategy
{
	private int elementiVisitati = 0;
	private double sommaPrezzi = 0;
	public PrezzoMedioCalculator(ProdottoChecker checker)
	{
		super(checker);
	}
	@Override
	void analizza(Prodotto p)
	{
		elementiVisitati++;
		sommaPrezzi = Utility.sommaDouble(sommaPrezzi, p.getPrezzo());
	}
	public double getPrezzoMedio()
	{
		if (elementiVisitati == 0)
			return 0;
		return Utility.dividiDouble(sommaPrezzi, elementiVisitati);
	}
	@Override
	public String toString()
	{
		return "Prezzo medio " + getCheckerName();
	}
}