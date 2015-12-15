package it.shoponline.model.core.fattura;

import it.shoponline.model.core.cliente.Cliente;
import it.shoponline.model.utility.Constants;
import it.shoponline.model.utility.Utility;

public class Fattura2Html
{
	private final static String NEW_LINE = "<br/>";
	private Fattura fattura;

	public Fattura2Html(Fattura fattura)
	{
		this.fattura = fattura;
	}
	public String getHtml()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("<html>");
		sb.append(getHeadWithStyle());
		sb.append("<body>");
		sb.append(getIntestazione());
		sb.append(getCorpo());
		sb.append("</body>");
		sb.append("</html>");
		return sb.toString();
	}
	private String getHeadWithStyle()
	{
		final String BG_COLOR = "#C8D1D5";
		StringBuilder sb = new StringBuilder();
		sb.append("<head>");
		sb.append("<style>");
		
		sb.append("* { margin: 0; padding: 0; }");
		sb.append("body { width: 450px; margin: 25px; text-align: center; background-color: white; font: 10px Georgia, serif; }");
		sb.append("table { border-collapse: collapse; border-spacing: 0; }");
		sb.append("table td, table th { padding: 5px; }");
		sb.append("#header { height: 15px; width: 100%; margin: 20px 0; background: " + BG_COLOR + "; text-align: center; font: bold 15px Helvetica, Sans-Serif; text-decoration: uppercase; letter-spacing: 20px; padding: 8px 0px; }");
		sb.append("#tHead { width: 100%; }");
		sb.append("#customer-title { font-size: 12 px; font-weight: bold; float: left; }");
		sb.append("#meta { margin-top: 1px; float: right; }");
		sb.append("#meta td { border: 1px solid black; text-align: right;  }");
		sb.append("#meta td.meta-head { text-align: left; background: " + BG_COLOR + "; }");
		sb.append(".l { text-align: left; }");
		sb.append(".r { text-align: right; }");
		
		sb.append("#items { width: 100%; border: 1px solid black; }");
		sb.append("#items td {border: 1px solid black; white-space: nowrap;}");
		sb.append("#items td.nr { text-align: right; }");
		sb.append("#items th { background: " + BG_COLOR + "; }");
		sb.append("#items tr.item-row td { border: 0; vertical-align: top; }");
		sb.append("#items td.total-line { border-right: 0; text-align: right; }");
		sb.append("#items td.total-value { border-left: 0; padding: 10px; }");
		sb.append("#items td.balance { background: " + BG_COLOR + " }");
		sb.append("#items td.blank { border: 0; }");

		sb.append("</style>");
		sb.append("</head>");
		return sb.toString();
	}
	private String getIntestazione()
	{
		Cliente c = fattura.getCliente();
		StringBuilder sb = new StringBuilder();
		sb.append("<div id=\"header\">FATTURA</div>");
		sb.append("<table id=\"tHead\">");
		sb.append("<tr>");
		// Dati cliente
		{
			sb.append("<td class=\"l\">");
			sb.append(c.getNome()).append(" ").append(c.getCognome()).append(NEW_LINE);
			sb.append(c.getIndirizzo()).append(NEW_LINE);
			sb.append(c.getCitta()).append(", ").append(c.getProvincia()).append(" ").append(c.getCap()).append(NEW_LINE);
			sb.append("Tel.: ").append(c.getTelefono());
			sb.append("</td>");
		}
		// Dati fattura
		{
			sb.append("<td class=\"r\">");
			sb.append("<table id=\"meta\">");
			{
				sb.append("<tr>");
				sb.append("<th colspan=\"2\">");
				sb.append("<div id=\"customer-title\">");
				sb.append(Fattura.RAGIONE_SOCIALE);
				sb.append("</div>");
				sb.append("</th>");
				sb.append("</tr>");
				
				sb.append("<tr>");
				sb.append("<td class=\"meta-head\">Fattura #</td>");
				sb.append("<td><div>").append(fattura.getNumero()).append("</div></td>");
				sb.append("</tr>");
				
				sb.append("<tr>");
				sb.append("<td class=\"meta-head\">Data</td>");
				sb.append("<td>").append(Utility.formattaDate(fattura.getData())).append("</td>");
				sb.append("</tr>");
				
				sb.append("<tr>");
				sb.append("<td class=\"meta-head\">Importo totale</td>");
				sb.append("<td><div class=\"due\">").append(formattaValuta(fattura.getImportoTotale())).append("</div></td>");
				sb.append("</tr>");
			}
			sb.append("</table>");
			sb.append("</td>");
		}
		sb.append("</tr>");
		sb.append("</table>");
		return sb.toString();
	}
	private String getCorpo()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("<table id=\"items\">");
		sb.append("<tr>");
		sb.append("<th>Descrizione</th>");
		sb.append("<th>Quantita</th>");
		sb.append("<th>Prezzo</th>");
		sb.append("<th>Importo</th>");
		sb.append("</tr>");
		
		double totCalc = 0;
		for (RigaFattura rf : fattura.getRigaFatturaList())
		{
			if (rf.isRigaTotale())
				continue;
			totCalc = Utility.sommaDouble(totCalc, rf.getImporto());
			sb.append(getTr(rf));
		}
		
		sb.append("<tr>");
		sb.append("<td colspan=\"3\" class=\"blank\"> </td>");
		sb.append("<td class=\"nr\">Totale ").append(formattaValuta(totCalc)).append("</td>");
		sb.append("</tr>");
		sb.append("</table>");
		sb.append("</div>");
		return sb.toString();
	}
	
	private String getTr(RigaFattura rf)
	{
		StringBuilder sb = new StringBuilder();
		sb.append("<tr class=\"item-row\">");
		sb.append("<td class=\"item-name\"><div>").append(rf.getDescrizione()).append("</div></td>");
		
		String qta = "";
		String prezzo = "";
		if (rf.getQuantita() != 0)
		{
			qta = String.valueOf(rf.getQuantita());
			prezzo = formattaValuta(rf.getPrezzo());
		}
		
		sb.append("<td class=\"nr\">").append(qta).append("</td>");
		sb.append("<td class=\"nr\">").append(prezzo).append("</td>");
		sb.append("<td class=\"nr\">").append(formattaValuta(rf.getImporto())).append("</td>");
		sb.append("</tr>");
		return sb.toString();
	}
	
	private String formattaValuta(double d)
	{
		return Utility.formattaDouble(d) + " " + Constants.EURO;
	}
}