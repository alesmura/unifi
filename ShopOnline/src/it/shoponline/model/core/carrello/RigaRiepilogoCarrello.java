package it.shoponline.model.core.carrello;

import it.shoponline.model.core.fattura.RigaFattura;
import it.shoponline.model.utility.Constants;
import it.shoponline.model.utility.Utility;
import java.io.Serializable;

public class RigaRiepilogoCarrello implements RigaFattura, Serializable
{
	private static final long serialVersionUID = 1L;
	//
	private String prodottoClassName = null;
	private String descrizione;
	private int quantita;
	private double prezzo;
	public RigaRiepilogoCarrello(String prodottoClassName, String descrizione, int quantita, double prezzo)
	{
		this.prodottoClassName = prodottoClassName;
		this.descrizione = descrizione;
		this.quantita = quantita;
		this.prezzo = prezzo;
	}
	public RigaRiepilogoCarrello(String descrizione, int quantita, double prezzo)
	{
		this.descrizione = descrizione;
		this.quantita = quantita;
		this.prezzo = prezzo;
	}
	public String getProdottoClassName()
	{
		return prodottoClassName;
	}
	public String getDescrizione()
	{
		return descrizione;
	}
	public int getQuantita()
	{
		return quantita;
	}
	public double getPrezzo()
	{
		return prezzo;
	}
	public double getImporto()
	{
		if (quantita == 0)
			return prezzo;
		return Utility.moltiplicaDouble(quantita, prezzo);
	}
	protected void incrementaQuantita()
	{
		quantita++;
	}
	@Override
	public boolean isRigaTotale()
	{
		return Constants.DIC_TOTALE.equals(getDescrizione());
	}
}