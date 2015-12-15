package it.shoponline.view.print;

import it.shoponline.model.utility.Utility;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import javax.swing.JLabel;

public class StampaComponente implements Printable
{
	private JLabel componente;

	public StampaComponente(JLabel componente)
	{
		super();
		this.componente = componente;
	}

	@Override
	public int print(Graphics g, PageFormat pf, int page) throws PrinterException
	{
		if (page > 0)
			return Printable.NO_SUCH_PAGE;

		// Recupero le dimensioni del componente da stampare
		Dimension dim = componente.getSize();
		double cHeight = dim.getHeight();
		double cWidth = dim.getWidth();

		// Recupero le dimensioni stampabili
		double pHeight = pf.getImageableHeight();
		double pWidth = pf.getImageableWidth();

		// Ottengo il rapporto delle dimensioni
		double xRatio = Utility.dividiDouble(pWidth, cWidth);
		double yRatio = Utility.dividiDouble(pHeight, cHeight);
		// Recupero il rapporto minore
		double ratio = Math.min(xRatio, yRatio);
		ratio = Utility.sommaDouble(ratio, -0.01);

		if (g instanceof Graphics2D)
		{
			Graphics2D g2 = (Graphics2D) g;
			double pXStart = pf.getImageableX();
			double pYStart = pf.getImageableY();
			g2.translate(pXStart, pYStart);
			// Scalo con il rapporto minore
			g2.scale(ratio, ratio);
			componente.paint(g2);
		}
		
		return Printable.PAGE_EXISTS;
	}
}