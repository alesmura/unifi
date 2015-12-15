package it.shoponline.test.statistiche;

import it.shoponline.model.prodotti.astratti.Bevanda;
import it.shoponline.model.prodotti.astratti.Dolce;
import it.shoponline.model.prodotti.astratti.Pacco;
import it.shoponline.model.prodotti.astratti.Prodotto;
import it.shoponline.model.prodotti.astratti.Salume;
import it.shoponline.model.prodotti.creator.PaccoBuilder;
import it.shoponline.model.prodotti.creator.ProdottoFranciaFactory;
import it.shoponline.model.prodotti.creator.ProdottoGermaniaFactory;
import it.shoponline.model.prodotti.creator.ProdottoItaliaFactory;
import it.shoponline.model.prodotti.creator.ProdottoNazioneAbstractFactory;
import java.util.LinkedList;
import java.util.List;

public class StatisticheTest
{
	protected Pacco paccoPiuCostoso;
	protected Bevanda bevandaPiuCostosa;
	protected Bevanda bevandaMenoCostosa;
	protected Dolce dolcePiuCostoso;
	protected Dolce dolceMenoCostoso;
	protected List<Prodotto> prodottoList;
	protected List<Bevanda> bevandaList;
	protected List<Dolce> dolceList;
	protected List<Salume> salumeList;
	protected List<Pacco> paccoList;
	
	public StatisticheTest()
	{
		bevandaList = new LinkedList<Bevanda>();
		dolceList = new LinkedList<Dolce>();
		salumeList = new LinkedList<Salume>();
		paccoList = new LinkedList<Pacco>();
		// Factory
		ProdottoNazioneAbstractFactory prodottoFranciaFactory = new ProdottoFranciaFactory();
		ProdottoNazioneAbstractFactory prodottoGermaniaFactory = new ProdottoGermaniaFactory();
		ProdottoNazioneAbstractFactory prodottoItaliaFactory = new ProdottoItaliaFactory();
		// Bevande
		bevandaPiuCostosa = prodottoFranciaFactory.makeNewBevanda();
		bevandaList.add(bevandaPiuCostosa);
		bevandaMenoCostosa = prodottoGermaniaFactory.makeNewBevanda();
		bevandaList.add(bevandaMenoCostosa);
		bevandaList.add(prodottoItaliaFactory.makeNewBevanda());
		// Dolci
		dolceList.add(prodottoFranciaFactory.makeNewDolce());
		dolceMenoCostoso = prodottoGermaniaFactory.makeNewDolce();
		dolceList.add(dolceMenoCostoso);
		dolcePiuCostoso = prodottoItaliaFactory.makeNewDolce();
		dolceList.add(dolcePiuCostoso);
		// Salumi
		salumeList.add(prodottoFranciaFactory.makeNewSalume());
		salumeList.add(prodottoGermaniaFactory.makeNewSalume());
		salumeList.add(prodottoItaliaFactory.makeNewSalume());
		// Pacchi
		PaccoBuilder builder = new PaccoBuilder(prodottoFranciaFactory);
		paccoPiuCostoso = builder.getPacco();
		paccoList.add(paccoPiuCostoso);
		builder = new PaccoBuilder(prodottoGermaniaFactory);
		paccoList.add(builder.getPacco());
		builder = new PaccoBuilder(prodottoItaliaFactory);
		paccoList.add(builder.getPacco());
		// Lista totale
		prodottoList = new LinkedList<Prodotto>();
		prodottoList.addAll(bevandaList);
		prodottoList.addAll(dolceList);
		prodottoList.addAll(salumeList);
		prodottoList.addAll(paccoList);
	}
}