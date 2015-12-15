package it.shoponline.model.prodotti.creator;

import it.shoponline.model.prodotti.astratti.Bevanda;
import it.shoponline.model.prodotti.astratti.Dolce;
import it.shoponline.model.prodotti.astratti.Pacco;
import it.shoponline.model.prodotti.astratti.Salume;

public interface ProdottoNazioneAbstractFactory
{
	public Pacco makeNewPacco();
	public Bevanda makeNewBevanda();
	public Dolce makeNewDolce();
	public Salume makeNewSalume();
}