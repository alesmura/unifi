package it.shoponline.model.prodotti.astratti;

import it.shoponline.model.prodotti.statistiche.ProdottoVisitor;
import it.shoponline.model.utility.Constants;
import it.shoponline.model.utility.Utility;

public abstract class Prodotto implements Cloneable
{
	private String nome;
	public Prodotto(String nome)
	{
		this.nome = nome;
	}
	public String getNome()
	{
		return nome;
	}
	public abstract void accept(ProdottoVisitor pv);
	public String getDescrizione()
	{
		return getNome() + Constants.NEW_LINE + Utility.formattaDouble(getPrezzo()) + Constants.EURO;
	}
	public String getDescrizioneHtml()
	{
		return "<html>" + getDescrizione().replaceAll(Constants.NEW_LINE, Constants.BR) + "</html>";
	}
	public abstract double getPrezzo();
	public abstract void aggiungiProdotto(Prodotto p) throws UnsupportedOperationException;
	public abstract void rimuoviProdotto(Prodotto p) throws UnsupportedOperationException;
	@Override
	public abstract boolean equals(Object obj);
	@Override
	public abstract Object clone() throws CloneNotSupportedException;
}