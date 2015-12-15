package it.shoponline.controller;

import it.shoponline.model.core.carrello.CarrelloInterface;
import it.shoponline.model.prodotti.astratti.Prodotto;
import it.shoponline.model.prodotti.statistiche.ProdottoVisitor;
import it.shoponline.model.prodotti.statistiche.VisitorUtility;
import it.shoponline.model.prodotti.statistiche.checker.ProdottoChecker;
import it.shoponline.model.prodotti.statistiche.concrete.PrezzoMedioVisitor;
import it.shoponline.model.prodotti.statistiche.concrete.ProdottoCostosoVisitor;
import it.shoponline.model.prodotti.statistiche.strategy.ProdottoMenoCostosoStrategy;
import it.shoponline.model.prodotti.statistiche.strategy.ProdottoPiuCostosoStrategy;
import it.shoponline.model.utility.Constants;
import it.shoponline.view.ShopOnlineView;
import it.shoponline.view.dialog.StatisticheDialog;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

class StatisticheController
{
	// Riferimento Model
	private CarrelloInterface<Prodotto> carrello;
	// Riferimento alla View
	private ShopOnlineView view;
	//
	private ProdottiInVendita prodottiInVendita;
	//
	private ActionListener actionListener;
	//
	StatisticheController(ShopOnlineView view, ActionListener actionListener, CarrelloInterface<Prodotto> carrello, ProdottiInVendita prodottiInVendita)
	{
		this.view = view;
		this.carrello = carrello;
		this.prodottiInVendita = prodottiInVendita;
		this.actionListener = actionListener;
	}
	
	void dialogStatistiche(String azione)
	{
		String tipo = azione.replace(Constants.STATISTICHE, "");
		Class<? extends Prodotto> clazz = getClasseProdotto(tipo);
		List<ProdottoVisitor> visitorList = getElementiStatistica(clazz);
		if (!Constants.TAB_RIEPILOGO.equals(tipo))
			tipo = clazz.getSimpleName();
		view.setDialogStatistiche(new StatisticheDialog(view, tipo, actionListener, visitorList.toArray(new ProdottoVisitor[0])));
	}
	
	@SuppressWarnings("unchecked")
	private Class<? extends Prodotto> getClasseProdotto(String className)
	{
		Class<? extends Prodotto> clazz;
		try
		{
			clazz = (Class<? extends Prodotto>) Class.forName(className);
		}
		catch (Exception e)
		{
			clazz = Prodotto.class;
		}
		return clazz;
	}
	
	private List<ProdottoVisitor> getElementiStatistica(Class<? extends Prodotto> clazz)
	{
		List<ProdottoVisitor> statisticaList = new ArrayList<ProdottoVisitor>();
		ProdottoChecker checker = new ProdottoChecker(clazz);
		statisticaList.add(null);
		// Prodotto piu costoso
		statisticaList.add(new ProdottoCostosoVisitor(new ProdottoPiuCostosoStrategy(checker)));
		// Prodotto meno costoso
		statisticaList.add(new ProdottoCostosoVisitor(new ProdottoMenoCostosoStrategy(checker)));
		// Prezzo medio
		statisticaList.add(new PrezzoMedioVisitor(checker));
		//
		return statisticaList;
	}
	
	void eseguiStatistica()
	{
		String resultStatistiche = "";
		List<Prodotto> pList = getListToAnalize();
		//
		Object statistica = view.getStatisticaSelezionata();
		if (statistica instanceof ProdottoVisitor)
		{
			ProdottoVisitor pv = (ProdottoVisitor) statistica;
			VisitorUtility.analizzaProdotti(pList, pv);
			resultStatistiche = pv.getResult();
		}
		view.setResultStatistiche(resultStatistiche);
	}

	private List<Prodotto> getListToAnalize()
	{
		List<Prodotto> pList = null;
		if (Constants.TAB_RIEPILOGO.equals(view.getTipoStatistica()))
			pList = getProdottiNelCarrelloList();
		else
			pList = getProdottiInVenditaList();
		return pList;
	}

	private List<Prodotto> getProdottiNelCarrelloList()
	{
		return carrello.getElementiNelCarrello();
	}
	
	private List<Prodotto> getProdottiInVenditaList()
	{
		return prodottiInVendita.getProdottiInVenditaList();
	}
}