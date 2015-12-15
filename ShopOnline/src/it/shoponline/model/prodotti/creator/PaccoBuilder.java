package it.shoponline.model.prodotti.creator;

import it.shoponline.model.prodotti.astratti.Pacco;

public class PaccoBuilder
{
	private ProdottoNazioneAbstractFactory factory;
	public PaccoBuilder(ProdottoNazioneAbstractFactory factory)
	{
		this.factory = factory;
	}
	public Pacco getPacco()
	{
		Pacco pacco = factory.makeNewPacco();
		pacco.aggiungiProdotto(factory.makeNewBevanda());
		pacco.aggiungiProdotto(factory.makeNewDolce());
		pacco.aggiungiProdotto(factory.makeNewSalume());		
		return pacco;
	}
}