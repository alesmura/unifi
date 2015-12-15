package it.shoponline.model.prodotti.astratti;

import it.shoponline.model.prodotti.statistiche.ProdottoVisitor;
import it.shoponline.model.utility.Utility;
import java.lang.reflect.Constructor;
import java.util.LinkedList;
import java.util.List;

public abstract class Pacco extends Prodotto
{
	private final static double PERC_SCONTO = 0.25;
	private List<Prodotto> prodottoList = new LinkedList<Prodotto>();
	public Pacco(String nome)
	{
		super(nome);
	}
	@Override
	public double getPrezzo()
	{
		double ret = 0;
		for (Prodotto prodotto : prodottoList)
			ret = Utility.sommaDouble(ret, prodotto.getPrezzo());
		
		double sconto = Utility.moltiplicaDouble(ret, PERC_SCONTO);
		// Sconto del 25%
		ret = Utility.sommaDouble(ret, -sconto);
		return ret;
	}
	@Override
	public void aggiungiProdotto(Prodotto p) throws UnsupportedOperationException
	{
		prodottoList.add(p);
	}
	@Override
	public void rimuoviProdotto(Prodotto p) throws UnsupportedOperationException
	{
		prodottoList.remove(p);
	}
	public abstract String getTestoLettera();
	@Override
	public Pacco clone() throws CloneNotSupportedException
	{
		try
		{
			Constructor<?> constructor = this.getClass().getConstructor(String.class);
			Pacco p = (Pacco)constructor.newInstance(getNome());
			for (Prodotto prodotto : prodottoList)
				p.aggiungiProdotto((Prodotto) prodotto.clone());
			return p;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new CloneNotSupportedException(e.getMessage());
		}
	}
	@Override
	public boolean equals(Object obj)
	{
		if (obj == null || !(obj instanceof Pacco))
			return false;
		if (!this.getClass().equals(obj.getClass()))
			return false;
		Pacco oth = (Pacco)obj;
		return this.getNome().equals(oth.getNome()) && this.prodottoList.equals(oth.prodottoList);
	}
	
	@Override
	public int hashCode()
	{
		int hashCode = getClass().hashCode() * getNome().hashCode();
		for (Prodotto p : prodottoList)
			hashCode = 31*hashCode + (p==null ? 0 : p.hashCode());
		return hashCode;
	}
	
	@Override
	public void accept(ProdottoVisitor pv)
	{
		pv.analizza(this);
	}
}