package it.shoponline.view;

import it.shoponline.model.core.carrello.CarrelloInterface;
import it.shoponline.model.utility.Constants;
import it.shoponline.model.utility.Utility;
import it.shoponline.view.component.input.DateInputChecker;
import it.shoponline.view.component.input.IntegerInputChecker;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.ParseException;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

//Package access
class PagamentoPanel extends AbstractPanel
{
	private static final long serialVersionUID = 1L;
	//
	// Puntatore al pannello dei dati di pagamento
	private JPanel datiPagamentoPanel;
	// Radio
	private ButtonGroup radioButtons;
	// Puntatori ai dati della carta
	private JTextField numeroCarta;
	private final static int COLUMNS_NUMERO_CARTA = 12;
	private final static int MAX_LENGTH_NUMERO_CARTA = 16;
	private JTextField scadenzaCarta;
	private final static int COLUMNS_SCADENZA_CARTA = 7;
	private final static int MAX_LENGTH_SCADENZA_CARTA = 10;
	// Puntatore all'etichetta del totale carrello al pagamento
	private JLabel importoTotaleCarrelloSenzaPagamento;
	private JLabel importoTotaleCarrelloConPagamento;

	// Package access
	@Override
	JPanel getPanel()
	{
		JPanel panel = new JPanel(new BorderLayout(0, 0));
		// Bottoni
		panel.add(getBottoniTabPagamento(), BorderLayout.NORTH);
		// Metodi di pagamento
		panel.add(getMetodiDiPagamentoPanel(), BorderLayout.CENTER);
		return panel;
	}

	@Override
	String getTitle()
	{
		return Constants.TAB_PAGAMENTO;
	}

	@Override
	String getToolTip()
	{
		return "Selezione tipo pagamento";
	}

	private JPanel getBottoniTabPagamento()
	{
		return getButtonPanel(Constants.TAB_PAGAMENTO, Constants.TAB_DATI_CLIENTE, Constants.COMPLETA_ACQUISTO);
	}

	private JPanel getMetodiDiPagamentoPanel()
	{
		JPanel panel = new JPanel(new BorderLayout(0, 0));
		// Intestazione
		{
			JPanel p = new JPanel(new FlowLayout(FlowLayout.CENTER));
			JLabel label = new JLabel("Selezione metodo di pagamento");
			Font font = label.getFont();
			Font boldFont = new Font(font.getFontName(), Font.BOLD, 20);
			label.setFont(boldFont);
			p.add(label);
			panel.add(p, BorderLayout.NORTH);
		}
		{
			panel.add(getPanelRadioPagamento(), BorderLayout.CENTER);
		}

		return panel;
	}

	private JPanel getPanelRadioPagamento()
	{
		JPanel retPanel = new JPanel(new BorderLayout(0, 0));
		// Radio carta
		JRadioButton radioCartaDiCredito = new JRadioButton("Carta di credito");
		radioCartaDiCredito.setActionCommand(Constants.PAGAMENTO + Constants.SEPARATORE + Constants.CARTA_DI_CREDITO);
		// Radio contrassegno
		String m = "Contrassegno (Comporta una maggiorazione del " + Utility.formattaDouble(CarrelloInterface.PERCENTUALE_MAGGIORAZIONE_CONTRASSEGNO) + "%)";
		JRadioButton radioContrassegno = new JRadioButton(m);
		radioContrassegno.setActionCommand(Constants.PAGAMENTO + Constants.SEPARATORE + Constants.CONTRASSEGNO);
		// Associo i radio allo stesso gruppo
		{
			radioButtons = new ButtonGroup();
			radioButtons.add(radioCartaDiCredito);
			radioButtons.add(radioContrassegno);
			// Li metto in coda ai bottoni
			buttonList.add(radioCartaDiCredito);
			buttonList.add(radioContrassegno);
		}
		// Panel pagamento
		{
			JPanel pagamentoPanel = new JPanel(new BorderLayout());
			// Radio
			{
				JPanel pPag = new JPanel(new FlowLayout(FlowLayout.CENTER));
				pPag.add(radioCartaDiCredito);
				pPag.add(radioContrassegno);
				pagamentoPanel.add(pPag, BorderLayout.NORTH);
			}
			// Dati pagamento
			{
				JPanel panelDatiCarta = new JPanel(new FlowLayout(FlowLayout.CENTER));
				// Numero della carta
				panelDatiCarta.add(new JLabel("Numero della carta"));
				numeroCarta = new JTextField(COLUMNS_NUMERO_CARTA);
				numeroCarta.setDocument(new IntegerInputChecker(MAX_LENGTH_NUMERO_CARTA));
				panelDatiCarta.add(numeroCarta);
				// Data di scadenza della carta
				panelDatiCarta.add(new JLabel("Data scadenza della carta"));
				scadenzaCarta = new JTextField(COLUMNS_SCADENZA_CARTA);
				scadenzaCarta.setHorizontalAlignment(JTextField.CENTER);
				scadenzaCarta.setDocument(new DateInputChecker(MAX_LENGTH_SCADENZA_CARTA));
				scadenzaCarta.setText(Utility.DATE_FORMAT);
				// Al primo click cancello il testo di guida
				scadenzaCarta.addFocusListener(new FocusDataScadenza());
				panelDatiCarta.add(scadenzaCarta);
				//
				datiPagamentoPanel = new JPanel(new CardLayout());
				datiPagamentoPanel.add(new JPanel(), ""); // Uno vuoto per la prima volta
				datiPagamentoPanel.add(panelDatiCarta, Constants.CARTA_DI_CREDITO);
				datiPagamentoPanel.add(new JPanel(), Constants.CONTRASSEGNO);
				pagamentoPanel.add(datiPagamentoPanel, BorderLayout.CENTER);
			}
			retPanel.add(pagamentoPanel, BorderLayout.NORTH);
		}
		// Panel totale carrello
		{
			JPanel totPanel = new JPanel(new BorderLayout());
			final int PADDING = 10;
			totPanel.setBorder(BorderFactory.createEmptyBorder(PADDING, PADDING, PADDING, PADDING));

			JLabel labTotSp, labTotCp;
			{
				JPanel p = new JPanel(new FlowLayout(FlowLayout.CENTER));
				labTotSp = new JLabel("Totale carrello");
				p.add(labTotSp);
				importoTotaleCarrelloSenzaPagamento = new JLabel();
				p.add(importoTotaleCarrelloSenzaPagamento);
				totPanel.add(p, BorderLayout.NORTH);
			}
			{
				JPanel p = new JPanel(new FlowLayout(FlowLayout.CENTER));
				labTotCp = new JLabel("Totale carrello con pagamento");
				p.add(labTotCp);
				importoTotaleCarrelloConPagamento = new JLabel();
				p.add(importoTotaleCarrelloConPagamento);
				totPanel.add(p, BorderLayout.CENTER);
			}
			{
				// Imposto la dimensione max
				Dimension maxDim = getResizedDimension(labTotSp.getPreferredSize(), labTotCp.getPreferredSize(), 10);
				labTotSp.setPreferredSize(maxDim);
				labTotCp.setPreferredSize(maxDim);
			}
			retPanel.add(totPanel, BorderLayout.CENTER);
		}
		return retPanel;
	}

	private Dimension getResizedDimension(Dimension dim1, Dimension dim2, int padding)
	{
		int maxW = (int) Math.max(dim1.getWidth(), dim2.getWidth());
		int maxH = (int) Math.max(dim1.getHeight(), dim2.getHeight());
		return new Dimension(maxW + 10, maxH + 10);
	}

	private String getImportoFormat(double importo)
	{
		return Utility.formattaDouble(importo) + " " + Constants.EURO;
	}

	void setImportoTotaleCarrelloSenzaPagamento(double importo)
	{
		importoTotaleCarrelloSenzaPagamento.setText(getImportoFormat(importo));
	}

	void setImportoTotaleCarrelloConPagamento(double importo)
	{
		importoTotaleCarrelloConPagamento.setText(getImportoFormat(importo));
	}

	void showDatiPagamento(String etichettaRadio)
	{
		if (datiPagamentoPanel.getLayout() instanceof CardLayout)
		{
			CardLayout cl = (CardLayout) datiPagamentoPanel.getLayout();
			cl.show(datiPagamentoPanel, etichettaRadio);
		}
	}

	long getNumeroCarta()
	{
		return Long.parseLong(numeroCarta.getText());
	}

	Date getScadenzaCarta() throws ParseException
	{
		return Utility.parseDate(scadenzaCarta.getText());
	}
	
	boolean isPagamentoSelezionato()
	{
		return radioButtons.getSelection() != null;
	}
	
	boolean isPagamentoCartaDiCredito()
	{
		if (!isPagamentoSelezionato())
			return false;
		ButtonModel bm = radioButtons.getSelection();
		return bm.getActionCommand().contains(Constants.CARTA_DI_CREDITO);
	}
	
	private static final class FocusDataScadenza implements FocusListener
	{
		@Override
		public void focusGained(FocusEvent e)
		{
			JTextField jtf = (JTextField) e.getComponent();
			if (Utility.DATE_FORMAT.equals(jtf.getText()))
				jtf.setText("");

		}
		@Override
		public void focusLost(FocusEvent e)
		{
			JTextField jtf = (JTextField) e.getComponent();
			if (Utility.isBlank(jtf.getText()))
				jtf.setText(Utility.DATE_FORMAT);
		}
	}
}