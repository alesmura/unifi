package it.shoponline.model.prodotti.astratti;

import it.shoponline.model.prodotti.statistiche.ProdottoVisitor;
import it.shoponline.model.utility.Utility;
import java.lang.reflect.Constructor;

public abstract class Alimento extends Prodotto
{
	private double prezzo;
	public Alimento(String nome, double prezzo)
	{
		super(nome);
		this.prezzo = prezzo;
	}
	@Override
	public double getPrezzo()
	{
		return prezzo;
	}
	@Override
	public void aggiungiProdotto(Prodotto p) throws UnsupportedOperationException
	{
		throw new UnsupportedOperationException("Operazione non permessa");
	}
	@Override
	public void rimuoviProdotto(Prodotto p) throws UnsupportedOperationException
	{
		throw new UnsupportedOperationException("Operazione non permessa");		
	}
	@Override
	public Object clone() throws CloneNotSupportedException
	{
		try
		{
			Constructor<?> constructor = this.getClass().getConstructor(String.class, double.class);
			return (Alimento)constructor.newInstance(getNome(), getPrezzo());
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
		if (obj == null || !(obj instanceof Alimento))
			return false;
		if (!this.getClass().equals(obj.getClass()))
			return false;
		Alimento oth = (Alimento)obj;
		return this.getNome().equals(oth.getNome()) && Utility.equalsDouble(this.getPrezzo(), oth.getPrezzo());
	}
	@Override
	public int hashCode()
	{
		return (int) (getClass().hashCode() * getNome().hashCode() * getPrezzo());
	}
	@Override
	public void accept(ProdottoVisitor pv)
	{
		pv.analizza(this);
	}
}