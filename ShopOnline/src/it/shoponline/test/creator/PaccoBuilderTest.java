package it.shoponline.test.creator;

import static org.junit.Assert.assertEquals;
import it.shoponline.model.prodotti.astratti.Pacco;
import it.shoponline.model.prodotti.concreti.pacchi.PaccoFrancia;
import it.shoponline.model.prodotti.concreti.pacchi.PaccoGermania;
import it.shoponline.model.prodotti.concreti.pacchi.PaccoItalia;
import it.shoponline.model.prodotti.creator.PaccoBuilder;
import it.shoponline.model.prodotti.creator.ProdottoFranciaFactory;
import it.shoponline.model.prodotti.creator.ProdottoGermaniaFactory;
import it.shoponline.model.prodotti.creator.ProdottoItaliaFactory;
import it.shoponline.model.prodotti.creator.ProdottoNazioneAbstractFactory;
import org.junit.Before;
import org.junit.Test;

public class PaccoBuilderTest
{
	private Pacco paccoFrancia;
	private Pacco paccoGermania;
	private Pacco paccoItalia;
	
	@Before
	public void setUp() throws Exception
	{
		ProdottoNazioneAbstractFactory factory;
		factory = new ProdottoFranciaFactory();
		paccoFrancia = new PaccoFrancia("Pacco Francia");
		paccoFrancia.aggiungiProdotto(factory.makeNewBevanda());
		paccoFrancia.aggiungiProdotto(factory.makeNewDolce());
		paccoFrancia.aggiungiProdotto(factory.makeNewSalume());
		//
		factory = new ProdottoGermaniaFactory();
		paccoGermania = new PaccoGermania("Pacco Germania");
		paccoGermania.aggiungiProdotto(factory.makeNewBevanda());
		paccoGermania.aggiungiProdotto(factory.makeNewDolce());
		paccoGermania.aggiungiProdotto(factory.makeNewSalume());
		//
		factory = new ProdottoItaliaFactory();
		paccoItalia = new PaccoItalia("Pacco Italia");
		paccoItalia.aggiungiProdotto(factory.makeNewBevanda());
		paccoItalia.aggiungiProdotto(factory.makeNewDolce());
		paccoItalia.aggiungiProdotto(factory.makeNewSalume());
		
	}
	@Test
	public void testGetPaccoFrancia()
	{
		PaccoBuilder builder = new PaccoBuilder(new ProdottoFranciaFactory());
		assertEquals(builder.getPacco(), paccoFrancia);
	}
	@Test
	public void testGetPaccoGermania()
	{
		PaccoBuilder builder = new PaccoBuilder(new ProdottoGermaniaFactory());
		assertEquals(builder.getPacco(), paccoGermania);
	}
	@Test
	public void testGetPaccoItalia()
	{
		PaccoBuilder builder = new PaccoBuilder(new ProdottoItaliaFactory());
		assertEquals(builder.getPacco(), paccoItalia);
	}
}
