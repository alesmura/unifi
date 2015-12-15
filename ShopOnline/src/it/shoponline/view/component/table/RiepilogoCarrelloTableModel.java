package it.shoponline.view.component.table;

import it.shoponline.model.core.carrello.RiepilogoCarrello;
import it.shoponline.model.core.carrello.RigaRiepilogoCarrello;
import it.shoponline.model.utility.Constants;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.table.AbstractTableModel;

public class RiepilogoCarrelloTableModel extends AbstractTableModel
{
	private static final long serialVersionUID = 1L;
	//
	public static final String COL_DESCRIZIONE = "DESCRIZIONE";
	public static final String COL_QTA = "QTA";
	public static final String COL_PREZZO = "PREZZO";
	public static final String COL_IMPORTO = "IMPORTO";
	public static final String COL_ADD = "ADD";
	public static final String COL_REMOVE = "REM";
	private String[] defOrder = new String[]{COL_DESCRIZIONE, COL_QTA, COL_REMOVE, COL_ADD, COL_PREZZO, COL_IMPORTO};
	//
	private RiepilogoCarrello riepilogoCarrello;
	private ActionListener buttonListener;
	public RiepilogoCarrelloTableModel(RiepilogoCarrello riepilogoCarrello, ActionListener buttonListener)
	{
		this.riepilogoCarrello = riepilogoCarrello;
		this.buttonListener = buttonListener;
	}
	@Override
	public int getRowCount()
	{
		if (riepilogoCarrello == null)
			return 0;
		return riepilogoCarrello.getRigaList().size();
	}
	@Override
	public int getColumnCount()
	{
		return defOrder.length;
	}
	@Override
	public String getColumnName(int columnIndex)
	{
		return defOrder[columnIndex];
	}
	@Override
	public Class<?> getColumnClass(int columnIndex)
	{
		String colName = getColumnName(columnIndex);
		if (COL_DESCRIZIONE.equals(colName))
			return String.class;
		if (COL_QTA.equals(colName))
			return Integer.class;
		if (COL_PREZZO.equals(colName) || COL_IMPORTO.equals(colName))
			return Double.class;
		if (COL_ADD.equals(colName) || COL_REMOVE.equals(colName))
			return JButton.class;
		return null;
	}
	@Override
	public Object getValueAt(int rowIndex, int columnIndex)
	{
		if (riepilogoCarrello == null)
			return null;
		RigaRiepilogoCarrello riga = riepilogoCarrello.getRigaList().get(rowIndex);
		String colName = getColumnName(columnIndex);
		if (COL_DESCRIZIONE.equals(colName))
			return riga.getDescrizione();
		if (COL_QTA.equals(colName))
		{
			if (riga.getProdottoClassName() == null)
				return null;
			return riga.getQuantita();
		}
		if (COL_PREZZO.equals(colName))
		{
			if (riga.getProdottoClassName() == null)
				return null;
			return riga.getPrezzo();
		}
		if (COL_IMPORTO.equals(colName))
			return riga.getImporto();
		if (COL_ADD.equals(colName))
		{
			if (riga.getProdottoClassName() == null)
				return null;
			JButton button = new JButton("+");
			button.setActionCommand(Constants.ADD + "|" + riga.getProdottoClassName());
			button.addActionListener(buttonListener);
			return button;
		}
		if (COL_REMOVE.equals(colName))
		{
			if (riga.getProdottoClassName() == null)
				return null;
			JButton button = new JButton("-");
			button.setActionCommand(Constants.REMOVE + "|" + riga.getProdottoClassName());
			button.addActionListener(buttonListener);
			return button;
		}
		return null;
	}
}