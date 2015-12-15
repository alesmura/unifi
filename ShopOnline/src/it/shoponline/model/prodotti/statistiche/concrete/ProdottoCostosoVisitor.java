package it.shoponline.model.prodotti.statistiche.concrete;

import it.shoponline.model.prodotti.astratti.Alimento;
import it.shoponline.model.prodotti.astratti.Pacco;
import it.shoponline.model.prodotti.astratti.Prodotto;
import it.shoponline.model.prodotti.statistiche.ProdottoVisitor;
import it.shoponline.model.prodotti.statistiche.strategy.ProdottoCostosoStrategy;
import it.shoponline.model.utility.Constants;
import it.shoponline.model.utility.Utility;
import java.util.List;

public final class ProdottoCostosoVisitor implements ProdottoVisitor
{
	private ProdottoCostosoStrategy strategy = null;
	public ProdottoCostosoVisitor(ProdottoCostosoStrategy strategy)
	{
		this.strategy = strategy;
	}
	@Override
	public void analizza(Alimento a)
	{
		strategy.analizzaProdotto(a);
	}
	@Override
	public void analizza(Pacco p)
	{
		strategy.analizzaProdotto(p);
	}
	public List<Prodotto> getProdottoList()
	{
		return strategy.getProdottoList();
	}
	@Override
	public String toString()
	{
		return strategy.toString();
	}
	@Override
	public String getResult()
	{
		StringBuilder sb = new StringBuilder();
		sb.append(toString());
		if (getProdottoList().size() != 0)
		{
			sb.append(" ");
			sb.append(Utility.formattaDouble(strategy.getPrezzoRiferimento())).append(Constants.EURO);
			sb.append(" ");
			for (Prodotto p : getProdottoList())
				sb.append(p.getNome()).append(" ");
		}
		return sb.toString();
	}
}