package it.shoponline.view.component.table;

import java.util.Enumeration;
import javax.swing.table.TableColumn;

public class RiepilogoCarrelloJTable extends JTableAutoWidth
{
	private static final long serialVersionUID = 1L;
	@Override
	protected void impostaCellRenderer()
	{
		Enumeration<TableColumn> en = getColumnModel().getColumns();
		while (en.hasMoreElements())
		{
			TableColumn tc = en.nextElement();
			String hv = (String) tc.getHeaderValue();
			if (RiepilogoCarrelloTableModel.COL_DESCRIZIONE.equals(hv))
				tc.setPreferredWidth(400);
			else if (RiepilogoCarrelloTableModel.COL_ADD.equals(hv) || RiepilogoCarrelloTableModel.COL_REMOVE.equals(hv))
				tc.setPreferredWidth(30);
			//
			tc.setCellRenderer(new PaddingCellRenderer());
		}
	}
}
