package it.shoponline.model.core.carrello;


class OperazioneCarrelloMemento<E>
{
	private OPERAZIONE_CARRELLO operazione;
	private E elemento;
	private int indice;
	public OperazioneCarrelloMemento(OPERAZIONE_CARRELLO operazione, E elemento, int indice)
	{
		this.operazione = operazione;
		this.elemento = elemento;
		this.indice = indice;
	}
	public OPERAZIONE_CARRELLO getOperazione()
	{
		return operazione;
	}
	public E getElemento()
	{
		return elemento;
	}
	public int getIndice()
	{
		return indice;
	}
}