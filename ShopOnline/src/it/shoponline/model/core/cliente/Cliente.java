package it.shoponline.model.core.cliente;

public class Cliente
{
	private String nome;
	private String cognome;
	private String indirizzo;
	private String citta;
	private String provincia;
	private String cap;
	private String nazione;
	private String telefono;
	private String email;
	private String password;

	public Cliente()
	{
	}
	public Cliente(String nome, String cognome, String indirizzo, String citta, String provincia, String cap, String nazione, String telefono, String email, String password)
	{
		this.nome = nome;
		this.cognome = cognome;
		this.indirizzo = indirizzo;
		this.citta = citta;
		this.provincia = provincia;
		this.cap = cap;
		this.nazione = nazione;
		this.telefono = telefono;
		this.email = email;
		this.password = password;
	}

	public String getNome()
	{
		return nome;
	}

	public void setNome(String nome)
	{
		this.nome = nome;
	}

	public String getCognome()
	{
		return cognome;
	}

	public void setCognome(String cognome)
	{
		this.cognome = cognome;
	}

	public String getIndirizzo()
	{
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo)
	{
		this.indirizzo = indirizzo;
	}

	public String getCitta()
	{
		return citta;
	}

	public void setCitta(String citta)
	{
		this.citta = citta;
	}

	public String getProvincia()
	{
		return provincia;
	}

	public void setProvincia(String provincia)
	{
		this.provincia = provincia;
	}

	public String getCap()
	{
		return cap;
	}

	public void setCap(String cap)
	{
		this.cap = cap;
	}

	public String getNazione()
	{
		return nazione;
	}

	public void setNazione(String nazione)
	{
		this.nazione = nazione;
	}

	public String getTelefono()
	{
		return telefono;
	}

	public void setTelefono(String telefono)
	{
		this.telefono = telefono;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}
	public String getPassword()
	{
		return password;
	}
	public void setPassword(String password)
	{
		this.password = password;
	}
}