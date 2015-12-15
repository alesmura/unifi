package it.shoponline.model.core.fattura;

import it.shoponline.model.core.carrello.RiepilogoCarrello;
import it.shoponline.model.core.cliente.Cliente;
import java.util.Date;

public class FatturaBuilder
{
	public Fattura makeFattura(Cliente c, RiepilogoCarrello r)
	{
		return new Fattura(c, new Date(), r.getImportoTotale(), r.getRigaList());
	}
}