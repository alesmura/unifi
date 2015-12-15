package it.shoponline.controller;

import it.shoponline.model.core.carrello.Carrello;
import it.shoponline.model.core.carrello.CarrelloIVA;
import it.shoponline.model.core.carrello.CarrelloInterface;
import it.shoponline.model.core.carrello.CarrelloScontoImporto;
import it.shoponline.model.core.carrello.CarrelloSpeseSpedizione;
import it.shoponline.model.core.cliente.Cliente;
import it.shoponline.model.core.fattura.Fattura;
import it.shoponline.model.core.fattura.Fattura2Html;
import it.shoponline.model.core.fattura.FatturaBuilder;
import it.shoponline.model.data.ClienteXML;
import it.shoponline.model.prodotti.astratti.Prodotto;
import it.shoponline.model.utility.Constants;
import it.shoponline.model.utility.Utility;
import it.shoponline.view.ShopOnlineView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Classe che si occupa di gestire la comunicazione tra VIEW e MODEL
 * 
 * @author Alessio
 */
public class ShopOnlineController implements ActionListener, ChangeListener, Observer
{
	// Model
	private CarrelloInterface<Prodotto> carrello;
	// View
	private ShopOnlineView view;
	// Prodotti in vendita
	private ProdottiInVendita prodottiInVendita;
	//
	private CheckTabController checkTabController;
	private GestioneCarrelloController gestioneCarrelloController;
	private StatisticheController statisticheController;
	private DatiClienteController datiClienteController;
	private PagamentoController pagamentoController;
	// Costruttore
	public ShopOnlineController()
	{
		initialize();
	}
	
	private void initialize()
	{
		// Creo il model
		Carrello<Prodotto> carrello = new Carrello<Prodotto>();
		// Aggiungo il controller come osservatore del model (per disaccoppiare la view dal model)
		carrello.addObserver(this);
		// Creo la view
		ShopOnlineView view = new ShopOnlineView();
		
		// Inizializzo il MODEL, applicando le DECORAZIONI
		this.carrello = new CarrelloSpeseSpedizione<Prodotto>(new CarrelloScontoImporto<Prodotto>(carrello));
		// Inizializzo la VIEW
		// Se la view era gia' presente, la termino con il metodo dispose()
		if (this.view != null)
			this.view.dispose();
		this.view = view;
		// Inizializzo le liste e mappe interne alla classe
		prodottiInVendita = new ProdottiInVendita();
		// Inizializzo la VIEW
		initializeView();
		// Inizializzo i sotto controller
		initializeSubController();
	}
	
	private void initializeView()
	{
		// Imposto il dettaglio della vista dei prodotti in vendita
		view.setDettaglioProdotti(prodottiInVendita);
		// Metto il controller in ascolto della view
		view.addActionListener(this);
		view.addChangeListener(this);
	}
	
	private void initializeSubController()
	{
		this.checkTabController = new CheckTabController(view, carrello);
		this.gestioneCarrelloController = new GestioneCarrelloController(view, carrello, prodottiInVendita);
		this.statisticheController = new StatisticheController(view, this, carrello, prodottiInVendita);
		this.datiClienteController = new DatiClienteController(view);
		this.pagamentoController = new PagamentoController(view, this, carrello);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		System.out.println("Controller: interazione dalla view, aggiorno il model " + e.getActionCommand());
		String comandi[] = getComandi(e);
		String azione = comandi[0];
		String dettaglio = comandi[1];
		gestisciAzione(azione, dettaglio);
	}

	// Mi assicuro che gli ritorni un array di due stringhe NON nulle
	private String[] getComandi(ActionEvent e)
	{
		String actionCommand = e.getActionCommand();
		String[] ret = new String[] { "", "" };
		if (Utility.isBlank(actionCommand))
			return ret;
		String[] temp = actionCommand.split("\\" + Constants.SEPARATORE);
		if (temp.length == 0)
			return ret;
		if (temp.length > 0)
			ret[0] = temp[0];
		if (temp.length > 1)
			ret[1] = temp[1];
		return ret;
	}

	private void gestisciAzione(String azione, String dettaglio)
	{
		// Operazioni sulla finestra
		if (Constants.NEXT_TAB.equals(azione))
			nextTab(dettaglio);
		else if (Constants.PREV_TAB.equals(azione))
			prevTab();
		else if (Constants.TERMINA.equals(azione))
			terminate();
		else if (Constants.RESTART.equals(azione))
			restart();
		// Operazioni sul carrello
		else if (Constants.UNDO.equals(azione))
			carrelloUndo();
		else if (Constants.REDO.equals(azione))
			carrelloRedo();
		else if (Constants.ADD.equals(azione))
			aggiungiElementoCarrello(dettaglio);
		else if (Constants.REMOVE.equals(azione))
			rimuoviElementoCarrello(dettaglio);
		// Operazioni sulle statistiche
		else if (azione.startsWith(Constants.STATISTICHE))
			dialogStatistiche(azione);
		else if (Constants.TIPO_STATISTICA.equals(azione))
			eseguiStatistica();
		// Operazioni sul cliente
		else if (Constants.TIPO_AUTENTICAZIONE_CLIENTE.equals(azione))
			tipoAutenticazioneCliente(dettaglio);
		else if (Constants.ACCESSO_CLIENTE.equals(azione))
			accessoCliente();
		// Operazioni sul pagamento
		else if (Constants.PAGAMENTO.equals(azione))
			sceltaTipoPagamento(dettaglio);
		// Stampa fattura
		else if (Constants.STAMPA_FATTURA.equals(azione))
			stampaFattura();
	}

	private void nextTab(String dettaglio)
	{
		// Devo controllare se posso andare al tab successivo
		if (checkTabController.checkTab(dettaglio))
			view.nextTab();
	}
	
	private void prevTab()
	{
		view.prevTab();
	}

	private void terminate()
	{
		view.close();
	}
	
	private void restart()
	{
		initialize();
	}
	
	private void carrelloUndo()
	{
		gestioneCarrelloController.carrelloUndo();
	}
	
	private void carrelloRedo()
	{
		gestioneCarrelloController.carrelloRedo();
	}
	
	private void aggiungiElementoCarrello(String dettaglio)
	{
		gestioneCarrelloController.aggiungiElementoCarrello(dettaglio);
	}

	private void rimuoviElementoCarrello(String dettaglio)
	{
		gestioneCarrelloController.rimuoviElementoCarrello(dettaglio);
	}
	
	private void dialogStatistiche(String azione)
	{
		statisticheController.dialogStatistiche(azione);
	}
	
	private void eseguiStatistica()
	{
		statisticheController.eseguiStatistica();
	}
	
	private void tipoAutenticazioneCliente(String dettaglio)
	{
		datiClienteController.tipoAutenticazioneCliente(dettaglio);
	}
	
	private void accessoCliente()
	{
		datiClienteController.accessoCliente();
	}
	
	private void sceltaTipoPagamento(String dettaglio)
	{
		pagamentoController.sceltaTipoPagamento(dettaglio);
	}
	
	private void stampaFattura()
	{
		long inizio = System.currentTimeMillis();
		PrinterJob job = PrinterJob.getPrinterJob();
		job.setPrintable(view.getStampaFattura());
		boolean ok = job.printDialog();
		if (ok)
		{
			try
			{
				job.print();
			}
			catch (PrinterException ex)
			{
			}
		}
		System.out.println("Tempo totale stampa in --> " + (System.currentTimeMillis() - inizio));
	}

	@Override
	public void update(Observable obs, Object obj)
	{
		System.out.println("Controller: notifica dal model, update della view");
		if (obs instanceof Carrello<?> && obj instanceof Prodotto)
		{
			// Recupero l'oggetto da osservare
			Carrello<?> c = (Carrello<?>) obs;
			// Conto le occorrenze
			int pz = c.countElement(obj);
			// Recupero il nome del label
			String prodottoClass = obj.getClass().getName();
			// Genero il contenuto del label
			String labelContent = " ";
			if (pz != 0)
				labelContent = "Pz. " + pz;
			// Modifico la VIEW
			// Prima l'etichetta dei prezzi
			view.updatePezziProdottoLabel(prodottoClass, labelContent);
			// Poi la tabella del carrello e il totale al pagamento
			notifyViewRiepilogoCarrello();
		}
	}

	private void notifyViewRiepilogoCarrello()
	{
		pagamentoController.notifyViewRiepilogoCarrello();
	}
	
	@Override
	public void stateChanged(ChangeEvent e)
	{
		if (e.getSource() instanceof JTabbedPane)
		{
			JTabbedPane tabbedPane = (JTabbedPane) e.getSource();
			int currIndex = tabbedPane.getSelectedIndex();

			// Disabilito i tab successivi
			view.disabledNextTabs(currIndex + 1);

			// Se sono sulla tab della fattura, aggiorno l'eticetta della fattura
			if (Constants.TAB_FATTURA.equals(tabbedPane.getComponentAt(currIndex).getName()))
			{
				// Recupero ed eventualmente salvo il cliente
				Cliente c = storeNuovoCliente();
				// Recupero l'html della fattura
				String html = getHtmlFattura(c);
				view.updateFattura(html);
				// Disabilito tutti i tab
				view.disableAllTabs();
			}
		}
	}

	private Cliente storeNuovoCliente()
	{
		Cliente c = getClienteByView();
		// Se ero in fase di creazione di un nuovo cliente, lo registro
		if (view.isCreazioneNuovoCliente())
			new ClienteXML().storeCliente(c);
		return c;
	}
	
	private Cliente getClienteByView()
	{
		return datiClienteController.getClienteByView();
	}
	
	private String getHtmlFattura(Cliente c)
	{
		CarrelloIVA<Prodotto> carrelloFattura = new CarrelloIVA<Prodotto>(carrello);
		Fattura f = new FatturaBuilder().makeFattura(c, carrelloFattura.getRiepilogo());
		String html = new Fattura2Html(f).getHtml();
		return html;
	}
}