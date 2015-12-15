package it.shoponline.model.prodotti.creator;

import it.shoponline.model.prodotti.astratti.Bevanda;
import it.shoponline.model.prodotti.astratti.Dolce;
import it.shoponline.model.prodotti.astratti.Pacco;
import it.shoponline.model.prodotti.astratti.Salume;
import it.shoponline.model.prodotti.concreti.alimenti.Birra;
import it.shoponline.model.prodotti.concreti.alimenti.Bretzel;
import it.shoponline.model.prodotti.concreti.alimenti.Wurstel;
import it.shoponline.model.prodotti.concreti.pacchi.PaccoGermania;

public class ProdottoGermaniaFactory implements ProdottoNazioneAbstractFactory
{
	public Pacco makeNewPacco()
	{
		return new PaccoGermania("Pacco Germania");
	}
	public Salume makeNewSalume()
	{
		return new Wurstel("Wurstel speziato", 3.00);
	}
	public Dolce makeNewDolce()
	{
		return new Bretzel("Bretzel", 3.15);
	}
	public Bevanda makeNewBevanda()
	{
		return new Birra("Weissbier", 2.45);
	}
}