package it.shoponline.view;

import it.shoponline.model.prodotti.astratti.Prodotto;
import it.shoponline.model.prodotti.statistiche.checker.ProdottoChecker;
import it.shoponline.model.utility.Constants;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

// Package access
class ProdottiPanel extends AbstractPanel
{
	private static final long serialVersionUID = 1L;
	//
	private static String FOLDER_PRODOTTO = "prodotto/";
	private static String IMG_EXTENSION = ".gif";
	//
	private static Icon ICONA_ADD_BIG = getIcon("addToCart_30.gif");
	//
	private JPanel dettaglioProdottiPanel  = new JPanel();
	private Map<String, JLabel> pezziProdottoLabel = new HashMap<String, JLabel>();

	// Package access
	@Override
	JPanel getPanel()
	{
		JPanel panel = new JPanel(new BorderLayout(0, 0));
		// Bottoni
		panel.add(getBottoniTabProdotto(), BorderLayout.NORTH);
		// Prodotti, non vengono inizialmente rappresentati, ci pensera' il controller a disegnarli
		panel.add(dettaglioProdottiPanel, BorderLayout.CENTER);
		return panel;
	}

	@Override
	String getTitle()
	{
		return Constants.TAB_PRODOTTI;
	}

	String getToolTip()
	{
		return "Elenco prodotti";
	}

	private JPanel getBottoniTabProdotto()
	{
		return getButtonPanelStatistiche(Constants.TAB_PRODOTTI, null, Constants.TAB_RIEPILOGO);
	}

	void setDettaglioProdotti(List<Class<? extends Prodotto>> elementiInVenditaList, List<Prodotto> prodottiList)
	{
		// Layout su righe
		dettaglioProdottiPanel.setLayout(new GridLayout(elementiInVenditaList.size(), 0, 0, 0));
		for (Class<? extends Prodotto> clazz : elementiInVenditaList)
			dettaglioProdottiPanel.add(getTipiProdotto(clazz, new ProdottoChecker(clazz).getProdottoFiltratoList(prodottiList)));
	}

	private JPanel getTipiProdotto(Class<? extends Prodotto> clazz, List<Prodotto> list)
	{
		JPanel panel = new JPanel(new BorderLayout());
		{
			JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT));
			// Bottone
			{
				JButton statisticaButton = new JButton(getIcon("statistiche.gif"));
				statisticaButton.setToolTipText("Statistiche " + clazz.getSimpleName());
				statisticaButton.setBorder(BorderFactory.createEmptyBorder());
				statisticaButton.setActionCommand(Constants.STATISTICHE + clazz.getName());
				buttonList.add(statisticaButton);
				p.add(statisticaButton);
			}
			// Intestazione
			p.add(new JLabel(clazz.getSimpleName()));
			panel.add(p, BorderLayout.NORTH);
		}
		
		JPanel rigaPanel = new JPanel(new GridLayout(0, list.size(), 0, 0));
		rigaPanel.setBorder(BorderFactory.createLineBorder(borderColor));
		// Colonne dei prodotti
		for (Prodotto p : list)
			rigaPanel.add(getColonnaProdotto(p));
		//
		panel.add(rigaPanel, BorderLayout.CENTER);
		return panel;
	}


	private JPanel getColonnaProdotto(Prodotto p)
	{
		JPanel panel = new JPanel(new GridBagLayout());
		panel.setBorder(BorderFactory.createLineBorder(borderColor));

		GridBagConstraints c = new GridBagConstraints();
		// Immagine
		{
			JPanel jp = new JPanel(new FlowLayout(FlowLayout.LEFT));
			jp.add(new JLabel(getIcon(getSrcImgProdotto(p))));
			c.gridx = 0; // Colonna 0
			c.gridy = 0; // Riga 0
			c.gridwidth = 1; // Colspan 1
			c.gridheight = 2; // Rowspan 2
			c.weightx = 0.5;
			c.fill = GridBagConstraints.HORIZONTAL;
			panel.add(jp, c);
		}
		// Descrizione
		{
			JPanel jp = new JPanel(new FlowLayout(FlowLayout.CENTER));
			jp.add(new JLabel(p.getDescrizioneHtml()));
			c.gridx = 1; // Colonna 1
			c.gridy = 0; // Riga 0
			c.gridwidth = 1; // Colspan 1
			c.gridheight = 2; // Rowspan 2
			c.weightx = 0.5;
			c.fill = GridBagConstraints.HORIZONTAL;
			panel.add(jp, c);
		}
		// Bottone aggiunta
		{
			JPanel jp = new JPanel();
			JButton button = new JButton(ICONA_ADD_BIG);
			button.setActionCommand(Constants.ADD + Constants.SEPARATORE + p.getClass().getName());
			final int dim = 35;
			button.setPreferredSize(new Dimension(dim, dim));
			buttonList.add(button);
			jp.add(button);
			c.gridx = 2; // Colonna 2
			c.gridy = 0; // Riga 0
			c.gridwidth = 1; // Colspan 1
			c.gridheight = 1; // Rowspan 1
			c.weightx = 0.5;
			c.fill = GridBagConstraints.NONE;
			c.anchor = GridBagConstraints.EAST;
			panel.add(jp, c);
		}
		// Pezzi
		{
			JPanel jp = new JPanel();
			JLabel label = new JLabel(" ");
			pezziProdottoLabel.put(p.getClass().getName(), label);
			jp.add(label);
			c.gridx = 2; // Colonna 2
			c.gridy = 1; // Riga 1
			c.gridwidth = 1; // Colspan 1
			c.gridheight = 1; // Rowspan 1
			c.weightx = 0.5;
			c.fill = GridBagConstraints.NONE;
			c.anchor = GridBagConstraints.EAST;
			c.insets = new Insets(0, 0, 0, 6);
			panel.add(jp, c);
		}
		return panel;
	}

	private String getSrcImgProdotto(Prodotto p)
	{
		return FOLDER_PRODOTTO + p.getClass().getSimpleName() + IMG_EXTENSION;
	}

	void updatePezziProdottoLabel(String labelName, String labelContent)
	{
		JLabel label = pezziProdottoLabel.get(labelName);
		if (label != null)
			label.setText(labelContent);
	}
}