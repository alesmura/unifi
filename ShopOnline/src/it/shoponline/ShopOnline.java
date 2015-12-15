package it.shoponline;

import it.shoponline.controller.ShopOnlineController;
import javax.swing.SwingUtilities;

/**
 *	View  		-- button -->	Controller
 * 	Controller  -- modify -->	Model
 *	Model		-- notify -->	Controller
 *	Controller  -- update -->	View
 * @author Alessio
 */
public class ShopOnline
{
	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		execute();
	}
	
	public static void execute()
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					// Creo il controller, con il riferimento al model e alla view
					new ShopOnlineController();
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}
}