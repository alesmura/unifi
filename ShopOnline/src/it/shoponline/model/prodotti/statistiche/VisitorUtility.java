package it.shoponline.model.prodotti.statistiche;

import it.shoponline.model.prodotti.astratti.Prodotto;
import java.util.List;

public class VisitorUtility
{
	public static void analizzaProdotti(List<? extends Prodotto> list, ProdottoVisitor pv)
	{
		for (Prodotto p : list)
			p.accept(pv);
	}
}