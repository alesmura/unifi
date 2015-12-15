package it.shoponline.model.prodotti.statistiche.checker;

import it.shoponline.model.prodotti.astratti.Prodotto;
import java.util.LinkedList;
import java.util.List;

public class ProdottoChecker
{
	private Class<? extends Prodotto> classCheck;
	public ProdottoChecker(Class<? extends Prodotto> clazz)
	{
		this.classCheck = clazz;
	}
	public boolean isProdottoDaElaborare(Prodotto p)
	{
		return classCheck.isInstance(p);
	}
	public List<Prodotto> getProdottoFiltratoList(List<? extends Prodotto> inputList)
	{
		List<Prodotto> outputList = new LinkedList<Prodotto>();
		for (Prodotto p : inputList)
			if (isProdottoDaElaborare(p))
				outputList.add(p);
		return outputList;
	}
	public String getCheckClassName()
	{
		return classCheck.getSimpleName();
	}
}