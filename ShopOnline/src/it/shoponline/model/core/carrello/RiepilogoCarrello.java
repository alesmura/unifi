package it.shoponline.model.core.carrello;

import it.shoponline.model.utility.Constants;
import it.shoponline.model.utility.Utility;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class RiepilogoCarrello implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private List<RigaRiepilogoCarrello> rigaList;
	private double importoTotale;
	RiepilogoCarrello(List<RigaRiepilogoCarrello> rigaList, double importoTotale)
	{
		this.rigaList = rigaList;
		this.importoTotale = importoTotale;
	}
	public void aggiungiRiga(RigaRiepilogoCarrello riga)
	{
		rigaList.add(riga);
		importoTotale = Utility.sommaDouble(importoTotale, riga.getImporto());
	}
	public double getImportoTotale()
	{
		return importoTotale;
	}
	public List<RigaRiepilogoCarrello> getRigaList()
	{
		List<RigaRiepilogoCarrello> retList = new LinkedList<RigaRiepilogoCarrello>();
		retList.addAll(rigaList);
		retList.add(new RigaRiepilogoCarrello(Constants.DIC_TOTALE, 0, importoTotale));
		return retList;
	}
}
