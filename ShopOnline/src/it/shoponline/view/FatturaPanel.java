package it.shoponline.view;

import it.shoponline.model.utility.Constants;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class FatturaPanel extends AbstractPanel
{
	private static final long serialVersionUID = 1L;
	//
	private JLabel fattura;

	@Override
	String getTitle()
	{
		return Constants.TAB_FATTURA;
	}

	@Override
	String getToolTip()
	{
		return Constants.TAB_FATTURA;
	}

	@Override
	JPanel getPanel()
	{
		JPanel panel = new JPanel(new BorderLayout(0, 0));
		// Bottoni
		panel.add(getButtonPanel(Constants.TAB_FATTURA, null, null), BorderLayout.NORTH);
		// Metodi di pagamento
		panel.add(getFatturaPanel(), BorderLayout.CENTER);
		return panel;
	}

	@Override
	protected JPanel getButtonPanel(final String curr, final String prev, final String next)
	{
		JPanel bottoniPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		{
			// Bottone di stampa
			JButton nextButton = new JButton("Stampa fattura");
			nextButton.setActionCommand(Constants.STAMPA_FATTURA);
			buttonList.add(nextButton);
			bottoniPanel.add(nextButton);
		}
		{
			JButton terminaButton = new JButton("Termina");
			terminaButton.setActionCommand(Constants.TERMINA);
			buttonList.add(terminaButton);
			bottoniPanel.add(terminaButton);	
		}
		{
			JButton restartButton = new JButton("Nuovo acquisto");
			restartButton.setActionCommand(Constants.RESTART);
			buttonList.add(restartButton);
			bottoniPanel.add(restartButton);
		}
		return bottoniPanel;
	}

	private JPanel getFatturaPanel()
	{
		JPanel panel = new JPanel(new BorderLayout(0, 0));
		JPanel pf = new JPanel(new FlowLayout(FlowLayout.CENTER));
		{
			fattura = new JLabel();
			fattura.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			fattura.setHorizontalAlignment(JLabel.CENTER);
			pf.add(fattura);
		}
		panel.add(new JScrollPane(pf), BorderLayout.CENTER);
		return panel;
	}

	public void updateFattura(String fatturaHtml)
	{
		fattura.setText(fatturaHtml);
	}

	public JLabel getFattura()
	{
		return fattura;
	}
}