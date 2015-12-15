package it.shoponline.model.core.fattura;

public interface RigaFattura
{
	public String getDescrizione();
	public int getQuantita();
	public double getPrezzo();
	public double getImporto();
	public boolean isRigaTotale();
}