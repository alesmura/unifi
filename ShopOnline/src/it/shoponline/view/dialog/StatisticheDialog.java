package it.shoponline.view.dialog;

import it.shoponline.model.prodotti.statistiche.ProdottoVisitor;
import it.shoponline.model.utility.Constants;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class StatisticheDialog extends JDialog
{
	private static final long serialVersionUID = 1L;
	private String tipo;
	private JComboBox<ProdottoVisitor> comboBox;
	private JLabel resultLabel;

	public StatisticheDialog(Component parent, String tipo, ActionListener controller, ProdottoVisitor[] elementiStatistiche)
	{
		this.tipo = tipo;
		setTitle("Statistiche " + tipo);
		setContentPane(getPanelContent(controller, elementiStatistiche));
		setPreferredSize(new Dimension(400, 200));
		
		//
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		pack();
		setLocationRelativeTo(parent);
		setVisible(true);
	}

	private JPanel getPanelContent(ActionListener controller, ProdottoVisitor[] elementiStatistiche)
	{
		JPanel parent = new JPanel(new BorderLayout());
		// Intestazione
		{
			JPanel p = new JPanel(new FlowLayout(FlowLayout.CENTER));
			p.add(new JLabel(getTitle()));
			parent.add(p, BorderLayout.NORTH);
		}
		{
			JPanel pCont = new JPanel(new BorderLayout());
			// Combo
			{
				JPanel p = new JPanel(new FlowLayout(FlowLayout.CENTER));
				comboBox = new JComboBox<ProdottoVisitor>(elementiStatistiche);
				comboBox.addActionListener(controller);
				comboBox.setActionCommand(Constants.TIPO_STATISTICA);
				p.add(comboBox);
				pCont.add(p, BorderLayout.NORTH);
			}
			// Result
			{
				JPanel p = new JPanel(new FlowLayout(FlowLayout.CENTER));
				resultLabel = new JLabel();
				p.add(resultLabel);
				pCont.add(p, BorderLayout.CENTER);
			}
			parent.add(pCont, BorderLayout.CENTER);
		}
		return parent;
	}

	public void setResultStatistiche(String result)
	{
		resultLabel.setText(result);
	}

	public Object getStatisticaSelezionata()
	{
		return comboBox.getSelectedItem();
	}

	public String getTipo()
	{
		return tipo;
	}
}