package it.shoponline.controller;

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
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public final class ProdottiInVendita
{
	private List<Class<? extends Prodotto>> elementiInVenditaList;
	private Map<Class<? extends Prodotto>, Prodotto> prodottiInVenditaMap;

	public ProdottiInVendita()
	{
		impostaElementiInVenditaList();
		impostaProdottiInVenditaList();
	}
	
	private void impostaProdottiInVenditaList()
	{
		prodottiInVenditaMap = new LinkedHashMap<Class<? extends Prodotto>, Prodotto>();
		// Factory
		ProdottoNazioneAbstractFactory prodottoFranciaFactory = new ProdottoFranciaFactory();
		ProdottoNazioneAbstractFactory prodottoGermaniaFactory = new ProdottoGermaniaFactory();
		ProdottoNazioneAbstractFactory prodottoItaliaFactory = new ProdottoItaliaFactory();
		// Bevande
		inserisciProdottoInMap(prodottoFranciaFactory.makeNewBevanda());
		inserisciProdottoInMap(prodottoGermaniaFactory.makeNewBevanda());
		inserisciProdottoInMap(prodottoItaliaFactory.makeNewBevanda());
		// Dolci
		inserisciProdottoInMap(prodottoFranciaFactory.makeNewDolce());
		inserisciProdottoInMap(prodottoGermaniaFactory.makeNewDolce());
		inserisciProdottoInMap(prodottoItaliaFactory.makeNewDolce());
		// Salumi
		inserisciProdottoInMap(prodottoFranciaFactory.makeNewSalume());
		inserisciProdottoInMap(prodottoGermaniaFactory.makeNewSalume());
		inserisciProdottoInMap(prodottoItaliaFactory.makeNewSalume());
		// Pacchi
		PaccoBuilder builder = new PaccoBuilder(prodottoFranciaFactory);
		inserisciProdottoInMap(builder.getPacco());
		builder = new PaccoBuilder(prodottoGermaniaFactory);
		inserisciProdottoInMap(builder.getPacco());
		builder = new PaccoBuilder(prodottoItaliaFactory);
		inserisciProdottoInMap(builder.getPacco());
	}

	private void impostaElementiInVenditaList()
	{
		elementiInVenditaList = new LinkedList<Class<? extends Prodotto>>();
		elementiInVenditaList.add(Bevanda.class);
		elementiInVenditaList.add(Dolce.class);
		elementiInVenditaList.add(Salume.class);
		elementiInVenditaList.add(Pacco.class);
	}

	private void inserisciProdottoInMap(Prodotto p)
	{
		prodottiInVenditaMap.put(p.getClass(), p);
	}

	public List<Class<? extends Prodotto>> getElementiInVenditaList()
	{
		return elementiInVenditaList;
	}
	
	public List<Prodotto> getProdottiInVenditaList()
	{
		return new LinkedList<Prodotto>(prodottiInVenditaMap.values());
	}
	
	public Prodotto getProdottoClonato(String className) throws Exception
	{
		Class<?> clazz = Class.forName(className);
		Prodotto prodottoOriginale = prodottiInVenditaMap.get(clazz);
		return (Prodotto) prodottoOriginale.clone();
	}
}