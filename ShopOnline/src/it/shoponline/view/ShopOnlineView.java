package it.shoponline.view;

import it.shoponline.controller.ProdottiInVendita;
import it.shoponline.view.component.table.RiepilogoCarrelloTableModel;
import it.shoponline.view.dialog.StatisticheDialog;
import it.shoponline.view.print.StampaComponente;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.text.ParseException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeListener;

/**
 * Classe che si occupa SOLAMENTE del rendering grafico
 * 
 * @author Alessio
 */
public class ShopOnlineView extends JFrame
{
	private static final long serialVersionUID = 1L;
	//
	private static int WIDTH_VIEW = 800;
	private static int HEIGHT_VIEW = 600;
	private final static String TITOLO = "Shop Online";
	// PUntatore al dialog delle statistiche
	private StatisticheDialog dialogStatistiche;
	// Puntatore al pannello con i tab
	private JTabbedPane pannelloTabs;
	// Puntatore all'oggetto del pannello dei prodotti
	private ProdottiPanel prodottiPanel;
	// Puntatore all'oggetto del pannello di riepilogo
	private RiepilogoPanel riepilogoPanel;
	// Puntatore all'oggetto del pannello dei dati anagrafici del cliente
	private DatiClientePanel datiClientePanel;
	// Puntatore all'oggetto del pannello di pagamento
	private PagamentoPanel pagamentoPanel;
	// Puntatore all'oggetto del pannello della fattura
	private FatturaPanel fatturaPanel;
	//
	private List<AbstractPanel> panelList = new LinkedList<AbstractPanel>();

	/**
	 * Create the application.
	 */
	public ShopOnlineView()
	{
		super(TITOLO);
		initialize();
		setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize()
	{
		setBounds(0, 0, WIDTH_VIEW, HEIGHT_VIEW);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		// Pannello con tab
		pannelloTabs = new JTabbedPane(JTabbedPane.TOP);
		// Prodotti
		prodottiPanel = new ProdottiPanel();
		addAbstractPanel(prodottiPanel);
		// Riepilogo
		riepilogoPanel = new RiepilogoPanel();
		addAbstractPanel(riepilogoPanel);
		// Pagamento
		datiClientePanel = new DatiClientePanel();
		addAbstractPanel(datiClientePanel);
		// Pagamento
		pagamentoPanel = new PagamentoPanel();
		addAbstractPanel(pagamentoPanel);
		// Fattura
		fatturaPanel = new FatturaPanel();
		addAbstractPanel(fatturaPanel);
		// Abilito solo la prima scheda
		disabledNextTabs(1);
		// Aggiungo il pannello al contenuto
		getContentPane().add(pannelloTabs);
	}

	private void addAbstractPanel(AbstractPanel abstractPanel)
	{
		pannelloTabs.addTab(abstractPanel.getTitle(), null, abstractPanel.getBorderedPanel(), abstractPanel.getToolTip());
		panelList.add(abstractPanel);
	}

	// Metodi pubblici
	public void setDettaglioProdotti(ProdottiInVendita prodottiInVendita)
	{
		prodottiPanel.setDettaglioProdotti(prodottiInVendita.getElementiInVenditaList(), prodottiInVendita.getProdottiInVenditaList());
	}

	public void addActionListener(ActionListener controller)
	{
		for (AbstractPanel ap : panelList)
			ap.addActionListener(controller);
	}

	public void addChangeListener(ChangeListener controller)
	{
		pannelloTabs.addChangeListener(controller);
	}

	public void updatePezziProdottoLabel(String labelName, String labelContent)
	{
		prodottiPanel.updatePezziProdottoLabel(labelName, labelContent);
	}

	public void nextTab()
	{
		int nextIndex = pannelloTabs.getSelectedIndex() + 1;
		// Abilito l'etichetta
		pannelloTabs.setEnabledAt(nextIndex, true);
		// La metto attiva
		pannelloTabs.setSelectedIndex(nextIndex);
	}

	public void prevTab()
	{
		int currentIndex = pannelloTabs.getSelectedIndex();
		// La metto attiva
		pannelloTabs.setSelectedIndex(currentIndex - 1);
	}

	// Disabilito i tab successivi
	public void disabledNextTabs(int index)
	{
		for (int i = index; i < pannelloTabs.getComponentCount(); i++)
			pannelloTabs.setEnabledAt(i, false);
	}
	
	public void disableAllTabs()
	{
		disabledNextTabs(0);
	}

	public void setTableModelRiepilogoCarrello(RiepilogoCarrelloTableModel tableModel)
	{
		riepilogoPanel.setTableModelRiepilogoCarrello(tableModel);
	}

	public void setImportoTotaleCarrelloSenzaPagamento(double importo)
	{
		pagamentoPanel.setImportoTotaleCarrelloSenzaPagamento(importo);
	}

	public void setImportoTotaleCarrelloConPagamento(double importo)
	{
		pagamentoPanel.setImportoTotaleCarrelloConPagamento(importo);
	}

	public void showDatiPagamento(String etichettaRadio)
	{
		pagamentoPanel.showDatiPagamento(etichettaRadio);
	}

	public long getNumeroCarta()
	{
		return pagamentoPanel.getNumeroCarta();
	}

	public Date getScadenzaCarta() throws ParseException
	{
		return pagamentoPanel.getScadenzaCarta();
	}

	public void setDialogStatistiche(StatisticheDialog dialogStatistiche)
	{
		this.dialogStatistiche = dialogStatistiche;
	}

	public Object getTipoStatistica()
	{
		return dialogStatistiche.getTipo();
	}

	public Object getStatisticaSelezionata()
	{
		return dialogStatistiche.getStatisticaSelezionata();
	}

	public void setResultStatistiche(String result)
	{
		dialogStatistiche.setResultStatistiche(result);
	}

	public void updateFattura(String fatturaHtml)
	{
		fatturaPanel.updateFattura(fatturaHtml);
	}

	public StampaComponente getStampaFattura()
	{
		return new StampaComponente(fatturaPanel.getFattura());
	}

	public String getDatoCliente(String etichetta)
	{
		return datiClientePanel.getInputByEtichetta(etichetta);
	}

	public void setDatoCliente(String etichetta, String value)
	{
		datiClientePanel.setInputByEtichetta(etichetta, value);
	}

	public void showDatiCliente(String etichettaRadio)
	{
		datiClientePanel.showDatiCliente(etichettaRadio);
	}

	public String getEmailAccesso()
	{
		return datiClientePanel.getEmailAccesso();
	}

	public String getPasswordAccesso()
	{
		return datiClientePanel.getPasswordAccesso();
	}
	
	public boolean isAutenticazioneSelezionata()
	{
		return datiClientePanel.isAutenticazioneSelezionata();
	}
	
	public boolean isCreazioneNuovoCliente()
	{
		return datiClientePanel.isCreazioneNuovoCliente();
	}
	
	public boolean isClienteRegistrato()
	{
		return datiClientePanel.isClienteRegistrato();
	}
	
	public void clearDatiCliente()
	{
		datiClientePanel.clearDatiCliente();
	}
	
	public void close()
	{
		this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
	}
	
	public boolean isPagamentoSelezionato()
	{
		return pagamentoPanel.isPagamentoSelezionato();
	}
	
	public boolean isPagamentoCartaDiCredito()
	{
		return pagamentoPanel.isPagamentoCartaDiCredito();
	}
}