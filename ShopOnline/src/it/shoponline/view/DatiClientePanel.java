package it.shoponline.view;

import it.shoponline.model.utility.Constants;
import it.shoponline.model.utility.Utility;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

public class DatiClientePanel extends AbstractPanel
{
	private static final long serialVersionUID = 1L;
	//
	// Puntatore al pannello dei dati di accesso
	private JPanel accessoPanel;
	// Radio
	private ButtonGroup radioButtons;
	// Dati di accesso
	private JTextField emailAccesso;
	private JTextField passwordAccesso;
	//
	private final static int LENGTH_INPUT_DATI_CLIENTE = 20;
	//
	private Map<String, JTextField> etichettaInput = new HashMap<String, JTextField>();

	@Override
	String getTitle()
	{
		return Constants.TAB_DATI_CLIENTE;
	}

	@Override
	String getToolTip()
	{
		return "Inserimento dati cliente";
	}

	@Override
	JPanel getPanel()
	{
		JPanel panel = new JPanel(new BorderLayout(0, 0));
		// Bottoni
		panel.add(getBottoniTabDatiCliente(), BorderLayout.NORTH);
		// Metodi di pagamento
		panel.add(getAutenticazionePanel(), BorderLayout.CENTER);
		return panel;
	}

	private JPanel getBottoniTabDatiCliente()
	{
		return getButtonPanel(Constants.TAB_DATI_CLIENTE, Constants.TAB_RIEPILOGO, Constants.TAB_PAGAMENTO);
	}

	private JPanel getAutenticazionePanel()
	{
		JPanel panel = new JPanel(new BorderLayout(0, 0));
		// Intestazione
		{
			JPanel p = new JPanel(new FlowLayout(FlowLayout.CENTER));
			JLabel label = new JLabel("Autenticazione");
			Font font = label.getFont();
			Font boldFont = new Font(font.getFontName(), Font.BOLD, 20);
			label.setFont(boldFont);
			p.add(label);
			panel.add(p, BorderLayout.NORTH);
		}
		{
			panel.add(getPanelRadioAutenticazione(), BorderLayout.CENTER);
		}

		return panel;
	}

	private JPanel getPanelRadioAutenticazione()
	{
		JPanel retPanel = new JPanel(new BorderLayout(0, 0));
		// Radio nuovo cliente
		JRadioButton radioNuovoCliente = new JRadioButton("Nuovo cliente");
		radioNuovoCliente.setActionCommand(Constants.TIPO_AUTENTICAZIONE_CLIENTE + Constants.SEPARATORE + Constants.CLIENTE_NUOVO);
		// Radio contrassegno
		JRadioButton radioClienteRegistrato = new JRadioButton("Gia' registrato");
		radioClienteRegistrato.setActionCommand(Constants.TIPO_AUTENTICAZIONE_CLIENTE + Constants.SEPARATORE + Constants.CLIENTE_REGISTRATO);
		// Associo i radio allo stesso gruppo
		{
			radioButtons = new ButtonGroup();
			radioButtons.add(radioNuovoCliente);
			radioButtons.add(radioClienteRegistrato);
			// Li metto in coda ai bottoni
			buttonList.add(radioNuovoCliente);
			buttonList.add(radioClienteRegistrato);
		}

		// Panel selezione
		{
			JPanel selezionePanel = new JPanel(new BorderLayout());
			// Radio
			{
				JPanel pPag = new JPanel(new FlowLayout(FlowLayout.CENTER));
				pPag.add(radioNuovoCliente);
				pPag.add(radioClienteRegistrato);
				selezionePanel.add(pPag, BorderLayout.NORTH);
			}
			// Dati accesso
			{
				JPanel intesta = new JPanel(new BorderLayout());
				{
					accessoPanel = new JPanel(new CardLayout());
					accessoPanel.add(new JPanel(), "");// Uno vuoto per la prima volta
					accessoPanel.add(getDatiNuovoClientePanel(), Constants.CLIENTE_NUOVO);
					accessoPanel.add(getClienteRegistratoPanel(), Constants.CLIENTE_REGISTRATO);
					intesta.add(accessoPanel, BorderLayout.NORTH);
				}
				intesta.add(getAnagraficaClientePanel(), BorderLayout.CENTER);
				
				selezionePanel.add(intesta, BorderLayout.CENTER);
			}
			retPanel.add(selezionePanel, BorderLayout.NORTH);
		}
		return retPanel;
	}

	void showDatiCliente(String etichettaRadio)
	{
		if (accessoPanel.getLayout() instanceof CardLayout)
		{
			CardLayout cl = (CardLayout) accessoPanel.getLayout();
			cl.show(accessoPanel, etichettaRadio);
			inputEditabile(Constants.CLIENTE_NUOVO.equals(etichettaRadio));
		}
		// Al cambio di radio, azzero gli input
		emailAccesso.setText("");
		passwordAccesso.setText("");
		clearDatiCliente();
	}
	
	void clearDatiCliente()
	{
		for (JTextField input : etichettaInput.values())
			input.setText("");
	}

	private JPanel getClienteRegistratoPanel()
	{
		// Intestazione
		JPanel px = new JPanel(new BorderLayout());
		{
			JPanel p = new JPanel(new FlowLayout(FlowLayout.CENTER));
			JLabel label = new JLabel("Accesso");
			Font font = label.getFont();
			Font boldFont = new Font(font.getFontName(), Font.BOLD, 20);
			label.setFont(boldFont);
			p.add(label);
			px.add(p, BorderLayout.NORTH);
		}
		{
			JPanel fittizio = new JPanel(new FlowLayout(FlowLayout.CENTER));
			{
				JPanel p = new JPanel(new SpringLayout());
				p.add(new JLabel("Email"));
				emailAccesso = new JTextField(LENGTH_INPUT_DATI_CLIENTE);
				p.add(emailAccesso);
				p.add(new JLabel());
				//
				p.add(new JLabel("Password"));
				passwordAccesso = new JPasswordField(LENGTH_INPUT_DATI_CLIENTE);
				p.add(passwordAccesso);
				JButton buttonAccedi = new JButton("Accedi");
				buttonAccedi.setPreferredSize(new Dimension(80, 10));
				buttonAccedi.setActionCommand(Constants.ACCESSO_CLIENTE);
				buttonList.add(buttonAccedi);
				p.add(buttonAccedi);
				//
				SpringUtilities.makeCompactGrid(p, 2, 3, //rows, cols
						6, 6, //initX, initY
						6, 6); //xPad, yPad
				fittizio.add(p);
			}
			px.add(fittizio, BorderLayout.CENTER);
		}
		return px;
	}

	private void inputEditabile(boolean editable)
	{
		for (JTextField tx : etichettaInput.values())
			tx.setEditable(editable);
	}

	private JPanel getDatiNuovoClientePanel()
	{
		// Intestazione
		JPanel p = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JLabel label = new JLabel("Inserimento dati anagrafici");
		Font font = label.getFont();
		Font boldFont = new Font(font.getFontName(), Font.BOLD, 20);
		label.setFont(boldFont);
		p.add(label);

		return p;
	}

	private JPanel getAnagraficaClientePanel()
	{
		JPanel px = new JPanel(new FlowLayout(FlowLayout.CENTER));
		{
			JPanel p = new JPanel(new SpringLayout());
			List<String> etichetteList = Constants.ETICHETTE_DATI_CLIENTE_LIST;

			NextInputEnterKeyPress n = new NextInputEnterKeyPress();
			//Create and populate the panel.
			for (String etichetta : etichetteList)
			{
				JLabel l = new JLabel(etichetta, JLabel.TRAILING);
				p.add(l);
				JTextField textField = new JTextField(LENGTH_INPUT_DATI_CLIENTE);
				textField.addKeyListener(n);
				l.setLabelFor(textField);
				p.add(textField);
				etichettaInput.put(etichetta, textField);
			}

			//Lay out the panel.
			SpringUtilities.makeCompactGrid(p, etichetteList.size(), 2, //rows, cols
					6, 6, //initX, initY
					6, 6); //xPad, yPad
			px.add(p);
		}
		return px;
	}

	String getInputByEtichetta(String key)
	{
		JTextField jtf = etichettaInput.get(key);
		if (jtf == null)
			return "";
		String s = jtf.getText();
		if (Utility.isBlank(s))
			return "";
		return s;
	}
	void setInputByEtichetta(String key, String value)
	{
		JTextField jtf = etichettaInput.get(key);
		if (jtf != null)
			jtf.setText(value);
	}

	String getEmailAccesso()
	{
		return emailAccesso.getText();
	}

	String getPasswordAccesso()
	{
		return passwordAccesso.getText();
	}
	
	boolean isAutenticazioneSelezionata()
	{
		return radioButtons.getSelection() != null;
	}
	
	boolean isCreazioneNuovoCliente()
	{
		if (!isAutenticazioneSelezionata())
			return false;
		ButtonModel bm = radioButtons.getSelection();
		return bm.getActionCommand().contains(Constants.CLIENTE_NUOVO);
	}
	
	boolean isClienteRegistrato()
	{
		if (!isAutenticazioneSelezionata())
			return false;
		ButtonModel bm = radioButtons.getSelection();
		return bm.getActionCommand().contains(Constants.CLIENTE_REGISTRATO);
	}
	
	private static final class NextInputEnterKeyPress extends KeyAdapter
	{
		@Override
		public void keyPressed(KeyEvent e)
		{
			if (e.getKeyCode() == KeyEvent.VK_ENTER)
			{
				if (e.getSource() instanceof JTextField)
					((JTextField)e.getSource()).transferFocus();
			}
		}
	}
}