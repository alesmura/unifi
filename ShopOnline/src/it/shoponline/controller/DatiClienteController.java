package it.shoponline.controller;

import it.shoponline.model.core.cliente.Cliente;
import it.shoponline.model.data.ClienteXML;
import it.shoponline.model.utility.Constants;
import it.shoponline.view.ShopOnlineView;
import javax.swing.JOptionPane;

class DatiClienteController
{
	// Riferimento alla View
	private ShopOnlineView view;
	//
	DatiClienteController(ShopOnlineView view)
	{
		this.view = view;
	}
	
	void tipoAutenticazioneCliente(String dettaglio)
	{
		view.showDatiCliente(dettaglio);
	}
	
	void accessoCliente()
	{
		try
		{
			String emailAccesso = view.getEmailAccesso();
			String passwordAccesso = view.getPasswordAccesso();
			Cliente c = new ClienteXML().getCliente(emailAccesso);
			if (c == null)
				throw new RuntimeException("Utente non registrato");
			if (!c.getPassword().equals(passwordAccesso))
				throw new RuntimeException("Password errata");
			notifyViewDatiCliente(c);
		}
		catch (Exception e)
		{
			// Azzero i dati in maschera
			view.clearDatiCliente();
			// Segnalo l'errore
			JOptionPane.showMessageDialog(view, "Errore accesso: " + e.getMessage());
		}
	}
	
	private void notifyViewDatiCliente(Cliente c)
	{
		view.setDatoCliente(Constants.CLI_NOME, c.getNome());
		view.setDatoCliente(Constants.CLI_COGNOME, c.getCognome());
		view.setDatoCliente(Constants.CLI_INDIRIZZO, c.getIndirizzo());
		view.setDatoCliente(Constants.CLI_CITTA, c.getCitta());
		view.setDatoCliente(Constants.CLI_PROVINCIA, c.getProvincia());
		view.setDatoCliente(Constants.CLI_CAP, c.getCap());
		view.setDatoCliente(Constants.CLI_NAZIONE, c.getNazione());
		view.setDatoCliente(Constants.CLI_TELEFONO, c.getTelefono());
		view.setDatoCliente(Constants.CLI_EMAIL, c.getEmail());
	}
	
	Cliente getClienteByView()
	{
		String nome = view.getDatoCliente(Constants.CLI_NOME);
		String cognome = view.getDatoCliente(Constants.CLI_COGNOME);
		String indirizzo = view.getDatoCliente(Constants.CLI_INDIRIZZO);
		String citta = view.getDatoCliente(Constants.CLI_CITTA);
		String provincia = view.getDatoCliente(Constants.CLI_PROVINCIA);
		String cap = view.getDatoCliente(Constants.CLI_CAP);
		String nazione = view.getDatoCliente(Constants.CLI_NAZIONE);
		String telefono = view.getDatoCliente(Constants.CLI_TELEFONO);
		String email = view.getDatoCliente(Constants.CLI_EMAIL);
		String password = view.getDatoCliente(Constants.CLI_PASSWORD);
		return new Cliente(nome, cognome, indirizzo, citta, provincia, cap, nazione, telefono, email, password);
	}
}