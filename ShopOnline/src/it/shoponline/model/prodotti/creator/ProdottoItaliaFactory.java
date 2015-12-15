package it.shoponline.model.prodotti.creator;

import it.shoponline.model.prodotti.astratti.Bevanda;
import it.shoponline.model.prodotti.astratti.Dolce;
import it.shoponline.model.prodotti.astratti.Pacco;
import it.shoponline.model.prodotti.astratti.Salume;
import it.shoponline.model.prodotti.concreti.alimenti.Panettone;
import it.shoponline.model.prodotti.concreti.alimenti.Salame;
import it.shoponline.model.prodotti.concreti.alimenti.Vinsanto;
import it.shoponline.model.prodotti.concreti.pacchi.PaccoItalia;

public class ProdottoItaliaFactory implements ProdottoNazioneAbstractFactory
{
	public Pacco makeNewPacco()
	{
		return new PaccoItalia("Pacco Italia");
	}
	public Salume makeNewSalume()
	{
		return new Salame("Salame", 4.50);
	}
	public Dolce makeNewDolce()
	{
		return new Panettone("Panettone", 12.50);
	}
	public Bevanda makeNewBevanda()
	{
		return new Vinsanto("Vinsanto", 9.99);
	}
}