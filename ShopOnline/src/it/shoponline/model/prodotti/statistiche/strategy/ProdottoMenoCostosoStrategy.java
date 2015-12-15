package it.shoponline.model.prodotti.statistiche.strategy;

import it.shoponline.model.prodotti.astratti.Prodotto;
import it.shoponline.model.prodotti.statistiche.checker.ProdottoChecker;

public class ProdottoMenoCostosoStrategy extends ProdottoCostosoStrategy
{
	public ProdottoMenoCostosoStrategy(ProdottoChecker checker)
	{
		super(checker);
	}
	@Override
	boolean isNuovoPrezzoRiferimento(Prodotto p)
	{
		return p.getPrezzo() < getPrezzoRiferimento();
	}
	@Override
	public String toString()
	{
		return getCheckerName() + " meno costoso";
	}
}