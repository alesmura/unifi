package it.shoponline.model.prodotti.statistiche.strategy;

import it.shoponline.model.prodotti.astratti.Prodotto;
import it.shoponline.model.prodotti.statistiche.checker.ProdottoChecker;
import it.shoponline.model.utility.Utility;
import java.util.LinkedList;
import java.util.List;

public abstract class ProdottoCostosoStrategy extends StatisticheStrategy
{
	private Double prezzoRiferimento = null;
	private List<Prodotto> prodottoList = new LinkedList<Prodotto>();

	public ProdottoCostosoStrategy(ProdottoChecker checker)
	{
		super(checker);
	}

	@Override
	void analizza(Prodotto p)
	{
		if (isNuovoPrezzoRiferimentoOrNull(p))
		{
			prezzoRiferimento = p.getPrezzo();
			prodottoList.clear();
			prodottoList.add(p);
		}
		else if (Utility.equalsDouble(p.getPrezzo(), prezzoRiferimento))
		{
			if (!prodottoList.contains(p))
				prodottoList.add(p);
		}
	}

	private boolean isNuovoPrezzoRiferimentoOrNull(Prodotto p)
	{
		if (getPrezzoRiferimento() == null)
			return true;
		return isNuovoPrezzoRiferimento(p);
	}

	abstract boolean isNuovoPrezzoRiferimento(Prodotto p);

	public List<Prodotto> getProdottoList()
	{
		return prodottoList;
	}

	public Double getPrezzoRiferimento()
	{
		return prezzoRiferimento;
	}
}