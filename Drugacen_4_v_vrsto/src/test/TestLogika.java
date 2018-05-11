package test;

import junit.framework.TestCase;

import logika.Igra;
import logika.Polje;
import logika.Stanje;
import logika.Poteza;

public class TestLogika extends TestCase {
	
	public void testIgra() {
		Igra igra = new Igra();
		// Preveri �e je res na za�etku na vrsti rde�i.
		assertEquals(Stanje.NA_VRSTI_RED, igra.stanje());
		// Preveri �e je plo��a res prazna.
		assertEquals(igra.praznaPlosca(), true);
		// Odigra potezo - NAPAKA
		igra.odigrajpotezo(igra.poteze().get(0));
		assertEquals(igra.praznaPlosca(), false);
//		assertEquals(Igra.N * Igra.N, igra.poteze().size());
//		 assertEquals(Stanje.NA_VRSTI_BLUE, igra.stanje());
		 
		
	}

}
