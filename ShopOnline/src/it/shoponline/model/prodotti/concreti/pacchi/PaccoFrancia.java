package it.shoponline.model.prodotti.concreti.pacchi;

import it.shoponline.model.prodotti.astratti.Pacco;

public class PaccoFrancia extends Pacco
{
	public PaccoFrancia(String nome)
	{
		super(nome);
	}
	@Override
	public String getTestoLettera()
	{
		return "Tanti saluti dalla Francia";
	}
}