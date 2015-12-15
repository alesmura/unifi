package it.shoponline.view.component.table;

import java.awt.Component;
import java.util.Enumeration;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

public class JTableAutoWidth extends JTable
{
	private static final long serialVersionUID = 1L;
	@Override
	public void setModel(TableModel dataModel)
	{
		super.setModel(dataModel);
		impostaCellRenderer();
	}
	protected void impostaCellRenderer()
	{
		Enumeration<TableColumn> en = getColumnModel().getColumns();
		while (en.hasMoreElements())
			en.nextElement().setCellRenderer(new PaddingCellRenderer());
	}
	@Override
	public Component prepareRenderer(TableCellRenderer renderer, int row, int column)
	{
		Component component = super.prepareRenderer(renderer, row, column);
		int rendererWidth = component.getPreferredSize().width;
		TableColumn tableColumn = getColumnModel().getColumn(column);
		tableColumn.setPreferredWidth(Math.max(rendererWidth + getIntercellSpacing().width, tableColumn.getPreferredWidth()));
		return component;
	}
}