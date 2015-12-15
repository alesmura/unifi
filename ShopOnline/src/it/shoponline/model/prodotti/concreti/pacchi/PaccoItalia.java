package it.shoponline.model.prodotti.concreti.pacchi;

import it.shoponline.model.prodotti.astratti.Pacco;

public class PaccoItalia extends Pacco
{
	public PaccoItalia(String nome)
	{
		super(nome);
	}
	@Override
	public String getTestoLettera()
	{
		return "Tanti saluti dall'Italia";
	}
}