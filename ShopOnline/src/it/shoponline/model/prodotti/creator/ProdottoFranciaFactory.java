package it.shoponline.model.prodotti.creator;

import it.shoponline.model.prodotti.astratti.Bevanda;
import it.shoponline.model.prodotti.astratti.Dolce;
import it.shoponline.model.prodotti.astratti.Pacco;
import it.shoponline.model.prodotti.astratti.Salume;
import it.shoponline.model.prodotti.concreti.alimenti.Champagne;
import it.shoponline.model.prodotti.concreti.alimenti.Macarons;
import it.shoponline.model.prodotti.concreti.alimenti.PateFegatoOca;
import it.shoponline.model.prodotti.concreti.pacchi.PaccoFrancia;

public class ProdottoFranciaFactory implements ProdottoNazioneAbstractFactory
{
	public Pacco makeNewPacco()
	{
		return new PaccoFrancia("Pacco Francia");
	}
	public Salume makeNewSalume()
	{
		return new PateFegatoOca("Pate' de fois gras", 8.60);
	}
	public Dolce makeNewDolce()
	{
		return new Macarons("Macarons", 5.80);
	}
	public Bevanda makeNewBevanda()
	{
		return new Champagne("Champagne", 30.60);
	}
}