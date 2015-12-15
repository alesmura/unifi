package it.shoponline.view;

import it.shoponline.model.utility.Constants;
import it.shoponline.view.component.table.JTableButtonMouseListener;
import it.shoponline.view.component.table.RiepilogoCarrelloJTable;
import it.shoponline.view.component.table.RiepilogoCarrelloTableModel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;

// Package access
class RiepilogoPanel extends AbstractPanel
{
	private static final long serialVersionUID = 1L;
	//
	// Table model del riepilogo del carrello
	private JTable tableRiepilogoCarrello;

	// Package access
	@Override
	JPanel getPanel()
	{
		JPanel panel = new JPanel(new BorderLayout(0, 0));
		// Bottoni
		panel.add(getBottoniTabRiepilogo(), BorderLayout.NORTH);
		// Riepilogo del carrello
		panel.add(getRiepilogoCarrelloPanel(), BorderLayout.CENTER);
		return panel;
	}

	@Override
	String getTitle()
	{
		return Constants.TAB_RIEPILOGO;
	}

	@Override
	String getToolTip()
	{
		return "Riepilogo prodotti selezionati";
	}

	private JPanel getBottoniTabRiepilogo()
	{
		return getButtonPanelStatistiche(Constants.TAB_RIEPILOGO, Constants.TAB_PRODOTTI, Constants.TAB_DATI_CLIENTE);
	}

	private JPanel getRiepilogoCarrelloPanel()
	{
		JPanel panel = new JPanel(new BorderLayout(0, 0));
		final int padding = 20;
		panel.setBorder(BorderFactory.createEmptyBorder(padding, padding, padding, padding));
		// Intestazione
		{
			JPanel p = new JPanel(new FlowLayout(FlowLayout.CENTER));
			JLabel label = new JLabel("Riepilogo carrello");
			Font font = label.getFont();
			Font boldFont = new Font(font.getFontName(), Font.BOLD, 20);
			label.setFont(boldFont);
			p.add(label);
			panel.add(p, BorderLayout.NORTH);
		}
		// Tabella
		{
			JPanel p = new JPanel(new FlowLayout(FlowLayout.CENTER));
			{
				JPanel p2 = new JPanel(new BorderLayout(0, 0));
				// Jtable
				tableRiepilogoCarrello = new RiepilogoCarrelloJTable();
				RiepilogoCarrelloTableModel tm = new RiepilogoCarrelloTableModel(null, null);
				setTableModelRiepilogoCarrello(tm);
				// Header
				p2.add(tableRiepilogoCarrello.getTableHeader(), BorderLayout.NORTH);
				//Applico i vari abbellimenti alla tabella
				tableRiepilogoCarrello.setColumnSelectionAllowed(false);
				tableRiepilogoCarrello.setRowSelectionAllowed(false);
				tableRiepilogoCarrello.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				tableRiepilogoCarrello.setBorder(BorderFactory.createLineBorder(Color.black));
				tableRiepilogoCarrello.addMouseListener(new JTableButtonMouseListener(tableRiepilogoCarrello));
				// Body
				p2.add(tableRiepilogoCarrello, BorderLayout.CENTER);
				p.add(p2);
			}
			panel.add(p, BorderLayout.CENTER);
		}
		return panel;
	}

	public void setTableModelRiepilogoCarrello(RiepilogoCarrelloTableModel tableModel)
	{
		tableRiepilogoCarrello.setModel(tableModel);
	}
}
