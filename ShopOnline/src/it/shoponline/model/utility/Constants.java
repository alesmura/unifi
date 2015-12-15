package it.shoponline.model.utility;

import java.util.LinkedList;
import java.util.List;

public abstract class Constants
{
	// Diciture
	public final static String DIC_TOTALE = "Totale";
	// Simboli
	public final static String EURO = "\u20ac";
	public final static String SEPARATORE = "|";
	public final static String NEW_LINE = "\n";
	public final static String BR = "<br>";
	// Operazioni
	public final static String ADD = "ADD";
	public final static String REMOVE = "REMOVE";
	public final static String UNDO = "UNDO";
	public final static String REDO = "REDO";
	public final static String STATISTICHE = "STATISTICHE_";
	public final static String STAMPA_FATTURA = "STAMPA_FATTURA";
	public final static String TIPO_STATISTICA = "TIPO_STATISTICA";
	public final static String TERMINA = "TERMINA";
	public final static String RESTART = "RESTART";
	// Dati cliente
	public final static String TIPO_AUTENTICAZIONE_CLIENTE = "TIPO_AUTENTICAZIONE_CLIENTE";
	public final static String CLIENTE_NUOVO = "NUOVO";
	public final static String CLIENTE_REGISTRATO = "REGISTRATO";
	public final static String ACCESSO_CLIENTE = "ACCESSO_CLIENTE";
	//
	public final static List<String> ETICHETTE_DATI_CLIENTE_LIST = getEtichetteDatiAnagraficiList();
	public final static String CLI_NOME = "Nome";
	public final static String CLI_COGNOME = "Cognome";
	public final static String CLI_INDIRIZZO = "Indirizzo";
	public final static String CLI_CITTA = "Citta";
	public final static String CLI_PROVINCIA = "Provincia";
	public final static String CLI_CAP = "Cap";
	public final static String CLI_NAZIONE = "Nazione";
	public final static String CLI_TELEFONO = "Telefono";
	public final static String CLI_EMAIL = "Email";
	public final static String CLI_PWD = "Password";
	public final static String CLI_PASSWORD = CLI_PWD;
	public final static String CLI_PASSWORD_CHECK = CLI_PWD + " check";
	// Pagamento
	public final static String PAGAMENTO = "PAGAMENTO";
	public final static String CONTRASSEGNO = "CONTRASSEGNO";
	public final static String CARTA_DI_CREDITO = "CARTA_DI_CREDITO";
	public final static String COMPLETA_ACQUISTO = "Completa acquisto";
	// Tab
	public final static String PREV_TAB = "PREV_TAB";
	public final static String NEXT_TAB = "NEXT_TAB";
	//
	public final static String TAB_PRODOTTI = "Prodotti";
	public final static String TAB_RIEPILOGO = "Riepilogo";
	public final static String TAB_DATI_CLIENTE = "Dati cliente";
	public final static String TAB_PAGAMENTO = "Pagamento";
	public final static String TAB_FATTURA = "Fattura";
	//
	private static List<String> getEtichetteDatiAnagraficiList()
	{
		List<String> etichetteList = new LinkedList<String>();
		etichetteList.add(CLI_NOME);
		etichetteList.add(CLI_COGNOME);
		etichetteList.add(CLI_INDIRIZZO);
		etichetteList.add(CLI_CITTA);
		etichetteList.add(CLI_PROVINCIA);
		etichetteList.add(CLI_CAP);
		etichetteList.add(CLI_NAZIONE);
		etichetteList.add(CLI_TELEFONO);
		etichetteList.add(CLI_EMAIL);
		etichetteList.add(CLI_PASSWORD);
		etichetteList.add(CLI_PASSWORD_CHECK);
		return etichetteList;
	}
}