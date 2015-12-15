package it.shoponline.model.core.fattura;

import it.shoponline.model.core.cliente.Cliente;
import java.util.Date;
import java.util.List;

public class Fattura
{
	public final static String RAGIONE_SOCIALE = "Shop on line Alimenti";
	
	private Cliente cliente;
	private static int numero;
	private Date data;
	private double importoTotale;
	private List<? extends RigaFattura> rigaFatturaList;
	// Package acces del costruttore
	Fattura(Cliente cliente, Date data, double importoTotale, List<? extends RigaFattura> rigaFatturaList)
	{
		// Incremento automatico del numero
		incrementaNumero();
		this.cliente = cliente;
		this.data = data;
		this.importoTotale = importoTotale;
		this.rigaFatturaList = rigaFatturaList;
	}
	private synchronized void incrementaNumero()
	{
		++numero;
	}
	public Cliente getCliente()
	{
		return cliente;
	}
	public int getNumero()
	{
		return numero;
	}
	public Date getData()
	{
		if (data == null)
			return null;
		return (Date) data.clone();
	}
	public double getImportoTotale()
	{
		return importoTotale;
	}
	public List<? extends RigaFattura> getRigaFatturaList()
	{
		return rigaFatturaList;
	}
}