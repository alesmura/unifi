package it.shoponline.model.prodotti.concreti.pacchi;

import it.shoponline.model.prodotti.astratti.Pacco;

public class PaccoGermania extends Pacco
{
	public PaccoGermania(String nome)
	{
		super(nome);
	}
	@Override
	public String getTestoLettera()
	{
		return "Tanti saluti dalla Germania";
	}
}