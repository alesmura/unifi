package it.shoponline.model.prodotti.statistiche;

import it.shoponline.model.prodotti.astratti.Alimento;
import it.shoponline.model.prodotti.astratti.Pacco;

public interface ProdottoVisitor
{
	public abstract void analizza(Alimento a);
	public abstract void analizza(Pacco p);
	@Override
	public abstract String toString();
	public abstract String getResult();
}