package it.shoponline.view.component.table;

import it.shoponline.model.utility.Utility;
import java.awt.Component;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class PaddingCellRenderer extends DefaultTableCellRenderer
{
	private static final long serialVersionUID = 1L;
	private static final int PADDING = 5;
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
	{
		super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		if (value instanceof JButton)
			return (JButton) value;
		if (value instanceof Number)
			setHorizontalAlignment(RIGHT);
		if (value instanceof Double)
			setText(Utility.formattaDouble((Double)value));
		setBorder(BorderFactory.createEmptyBorder(PADDING, PADDING, PADDING, PADDING));
		return this;
	}
}