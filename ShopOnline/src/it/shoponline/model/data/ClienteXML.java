package it.shoponline.model.data;

import it.shoponline.model.core.cliente.Cliente;

public class ClienteXML extends BaseXML
{
	private final static String ROOT_TAG_NAME = "Clienti";
	private final static String ELEMENT_TAG_NAME = "Cliente";
	private final static String NOME_FILE_XML = "Clienti_SHOPONLINE.xml";
	
	@Override
	public String getNomeFileXML()
	{
		return NOME_FILE_XML;
	}

	@Override
	String getRootTagName()
	{
		return ROOT_TAG_NAME;
	}

	@Override
	String getElementTagName()
	{
		return ELEMENT_TAG_NAME;
	}

	public boolean isClientePresente(String email)
	{
		return isElementoPresente(email);
	}

	public Cliente getCliente(String email)
	{
		return getObjectFromElement(Cliente.class, email);
	}

	public void storeCliente(Cliente c)
	{
		storeElement(c, c.getEmail());
	}
}