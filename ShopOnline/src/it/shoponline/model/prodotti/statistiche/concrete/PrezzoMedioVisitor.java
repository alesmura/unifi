package it.shoponline.model.prodotti.statistiche.concrete;

import it.shoponline.model.prodotti.astratti.Alimento;
import it.shoponline.model.prodotti.astratti.Pacco;
import it.shoponline.model.prodotti.statistiche.ProdottoVisitor;
import it.shoponline.model.prodotti.statistiche.checker.ProdottoChecker;
import it.shoponline.model.prodotti.statistiche.strategy.PrezzoMedioCalculator;
import it.shoponline.model.utility.Constants;
import it.shoponline.model.utility.Utility;

public final class PrezzoMedioVisitor implements ProdottoVisitor
{
	private PrezzoMedioCalculator calculator;
	public PrezzoMedioVisitor(ProdottoChecker checker)
	{
		this.calculator = new PrezzoMedioCalculator(checker);
	}
	@Override
	public void analizza(Alimento a)
	{
		calculator.analizzaProdotto(a);
	}
	@Override
	public void analizza(Pacco p)
	{
		calculator.analizzaProdotto(p);
	}
	public double getPrezzoMedio()
	{
		return calculator.getPrezzoMedio();
	}
	@Override
	public String toString()
	{
		return calculator.toString();
	}
	@Override
	public String getResult()
	{
		return toString() + " " + Utility.formattaDouble(getPrezzoMedio()) + Constants.EURO;
	}
}