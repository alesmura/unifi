package it.shoponline.view;

import it.shoponline.model.utility.Constants;
import it.shoponline.model.utility.Utility;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

/*
 * Package access
 */
abstract class AbstractPanel implements Serializable
{
	private static final long serialVersionUID = 1L;
	//
	// Border color
	protected Color borderColor = Color.decode("#C8D1D5");
	// Icone
	private static String PATH_IMG = "img/";
	// Elenco dei bottoni
	protected List<AbstractButton> buttonList = new LinkedList<AbstractButton>();
	
	// Metodi da ridefinire
	abstract String getTitle();
	abstract String getToolTip();
	abstract JPanel getPanel();
	public JPanel getBorderedPanel()
	{
		JPanel panel = getPanel();
		panel.setName(getTitle());
		panel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		return panel;
	}
	// Metodi comuni
	protected JPanel getButtonPanelStatistiche(final String curr, final String prev, final String next)
	{
		JPanel panel = new JPanel(new BorderLayout());
		JPanel statisticaButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		{
			JButton statisticheButton = new JButton("Statistiche");
			statisticheButton.setActionCommand(Constants.STATISTICHE + curr);
			buttonList.add(statisticheButton);
			statisticaButtonPanel.add(statisticheButton);
		}
		// Bottone di statistiche
		panel.add(statisticaButtonPanel);
		// Altri bottoni
		panel.add(getButtonPanel(curr, prev, next), BorderLayout.EAST);
		return panel;
	}
	protected JPanel getButtonPanel(final String curr, final String prev, final String next)
	{
		JPanel bottoniPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		// Gestione particolare per il tab dei PRODOTTI
		if (Constants.TAB_PRODOTTI.equals(curr) || Constants.TAB_RIEPILOGO.equals(curr))
		{
			// Bottone di Undo
			JButton undoButton = new JButton("Undo");
			undoButton.setActionCommand(Constants.UNDO);
			buttonList.add(undoButton);
			bottoniPanel.add(undoButton);
			// Bottone di Redo
			JButton redoButton = new JButton("Redo");
			redoButton.setActionCommand(Constants.REDO);
			buttonList.add(redoButton);
			bottoniPanel.add(redoButton);
		}
		// Bottone di prev
		if (Utility.isNotBlank(prev))
		{
			JButton prevButton = new JButton(prev);
			prevButton.setActionCommand(Constants.PREV_TAB + Constants.SEPARATORE + curr);
			buttonList.add(prevButton);
			bottoniPanel.add(prevButton);
		}
		// Bottone di next
		if (Utility.isNotBlank(next))
		{
			JButton nextButton = new JButton(next);
			nextButton.setActionCommand(Constants.NEXT_TAB + Constants.SEPARATORE + curr);
			buttonList.add(nextButton);
			bottoniPanel.add(nextButton);
		}
		return bottoniPanel;
	}
	
	protected void addActionListener(ActionListener controller)
	{
		for (AbstractButton button : buttonList)
			button.addActionListener(controller);
	}
	
	protected static ImageIcon getIcon(String s)
	{
		URL imageUrl = AbstractPanel.class.getResource(PATH_IMG + s);
		if (imageUrl != null)
			return new ImageIcon(imageUrl);
		System.err.println("Immagine non trovata " + s);
		return null;
	}
}