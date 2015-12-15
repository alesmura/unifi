package it.shoponline.controller;

import it.shoponline.model.core.carrello.CarrelloInterface;
import it.shoponline.model.data.ClienteXML;
import it.shoponline.model.utility.Constants;
import it.shoponline.model.utility.Utility;
import it.shoponline.view.ShopOnlineView;
import java.text.ParseException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JOptionPane;

/*
 * Package access
 */
class CheckTabController
{
	// Riferimento Model
	private CarrelloInterface<?> carrello;
	// Riferimento alla View
	private ShopOnlineView view;
	CheckTabController(ShopOnlineView view, CarrelloInterface<?> carrello)
	{
		this.view = view;
		this.carrello = carrello;
	}
	boolean checkTab(String tab)
	{
		String mex = getMessageCheckTab(tab);
		if (Utility.isNotBlank(mex))
		{
			JOptionPane.showMessageDialog(view, mex);
			return false;
		}
		return true;
	}
	private String getMessageCheckTab(String tab)
	{
		List<String> mexList = new LinkedList<String>();
		if (Constants.TAB_PRODOTTI.equals(tab))
			addMessage(mexList, checkTabProdotti());
		else if (Constants.TAB_RIEPILOGO.equals(tab))
			addMessage(mexList, checkTabRiepilogo());
		else if (Constants.TAB_DATI_CLIENTE.equals(tab))
			mexList.addAll(checkTabDatiCliente());
		else if (Constants.TAB_PAGAMENTO.equals(tab))
			mexList.addAll(checkTabPagamento());
		// Formatto i messaggi
		return formatMessagges(mexList);
	}
	private String checkTabProdotti()
	{
		return checkCarrello();
	}
	private String checkTabRiepilogo()
	{
		return checkCarrello();
	}
	private String checkCarrello()
	{
		String ret = "";
		// Se non ho nessun elemento nel carrello, non faccio procedere
		if (carrello.isEmpty())
			ret = "Inserire almeno un prodotto nel carrello.";
		return ret;
	}
	private List<String> checkTabDatiCliente()
	{
		List<String> mexList = new LinkedList<String>();
		// Se non ho selezionato nessun tipo di autenticazione, non faccio procedere
		if (!view.isAutenticazioneSelezionata())
		{
			mexList.add("Selezionare un tipo di autenticazione");
			return mexList;
		}
		// Controllo i dati di input
		mexList.addAll(checkInputDatiCliente());
		// Se non ho errori faccio altri controlli sul nuovo cliente
		if (mexList.isEmpty())
			addMessage(mexList, checkNuovoCliente());
		//
		return mexList;
	}
	/*
	 * Controllo i campi di input del cliente
	 */
	private List<String> checkInputDatiCliente()
	{
		boolean clienteRegistrato = view.isClienteRegistrato();
		List<String> mexList = new LinkedList<String>();
		for (String etichetta : Constants.ETICHETTE_DATI_CLIENTE_LIST)
		{
			// In modifica non controllo le password
			if (clienteRegistrato && etichetta.startsWith(Constants.CLI_PWD))
				continue;
			if (Utility.isBlank(view.getDatoCliente(etichetta)))
				mexList.add("Inserire " + etichetta);
		}
		return mexList;
	}
	/*
	 * Controlli sul nuovo cliente
	 */
	private String checkNuovoCliente()
	{
		// Se non sono in creazione non devo controllare niente
		if (!view.isCreazioneNuovoCliente())
			return "";
		// Controllo se l'utente e' registrato
		{
			String mex = checkUtenteRegistrato();
			if (Utility.isNotBlank(mex))
				return mex;
		}
		// Controllo le password
		{
			String mex = checkPasswordImmesse();
			if (Utility.isNotBlank(mex))
				return mex;
		}
		return "";
	}
	private String checkPasswordImmesse()
	{
		String ret = "";
		String pwd = view.getDatoCliente(Constants.CLI_PASSWORD);
		String pwdCheck = view.getDatoCliente(Constants.CLI_PASSWORD_CHECK);
		if (!pwd.equals(pwdCheck))
			ret = "Le due password devono essere identiche";
		return ret;
	}
	private String checkUtenteRegistrato()
	{
		// Se non esiste lo creo, altrimenti segnalo errore
		if (new ClienteXML().isClientePresente(view.getDatoCliente(Constants.CLI_EMAIL)))
			return "Utente gia' registrato";
		return "";
	}
	private List<String> checkTabPagamento()
	{
		List<String> mexList = new LinkedList<String>();
		// Se non ho selezionato un tipo di pagamento, non faccio procedere
		if (!view.isPagamentoSelezionato())
		{
			mexList.add("Selezionare un pagamento.");
			return mexList;
		}
		// Pagamento con carta
		if (view.isPagamentoCartaDiCredito())
			mexList.addAll(checkDatiCartaCredito());
		return mexList;
	}
	private List<String> checkDatiCartaCredito()
	{
		List<String> mexList = new LinkedList<String>();
		// Numerodella carta
		addMessage(mexList, checkNumeroCartaCredito());
		// Data di scadenza della carta
		addMessage(mexList, checkDataScadenzaCartaCredito());
		return mexList;
	}
	private String checkNumeroCartaCredito()
	{
		try
		{
			long numeroCarta = view.getNumeroCarta();
			if (String.valueOf(numeroCarta).length() != 16)
				throw new Exception();
		}
		catch (Exception e)
		{
			return "Numero carta errato. Inserire un numero di 16 cifre.";
		}
		return "";
	}
	private String checkDataScadenzaCartaCredito()
	{
		try
		{
			Date scadenzaCarta = view.getScadenzaCarta();
			if (scadenzaCarta.before(new Date()))
				return "Carta scaduta il " + Utility.formattaDate(scadenzaCarta);
		}
		catch (ParseException e)
		{
			return "Data di scadenza della carta errata. Inserire una data nel formato " + Utility.DATE_FORMAT;
		}
		return "";
	}
	private void addMessage(List<String> list, String mex)
	{
		if (Utility.isNotBlank(mex))
			list.add(mex);
	}
	private String formatMessagges(List<String> mexList)
	{
		if (mexList.size() == 0)
			return "";
		StringBuilder sb = new StringBuilder();
		for (String mex : mexList)
			sb.append(Constants.NEW_LINE).append(mex);
		return sb.substring(Constants.NEW_LINE.length());
	}
}