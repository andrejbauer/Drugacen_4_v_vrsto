package logika;

import java.util.LinkedList;
import java.util.List;

public class Igra {
	
	/*Velikost plosce je nxn*/
	
	public static final int N = 6;
	
	/* Atributi razreda Igra. */
	
	private Polje[][] plosca;
	private Igralec naPotezi;
	
	private static final List<Stirke> stirke = new LinkedList<Stirke>();
	
	/* Na za�etku igre (ko se prvi� po�ene igra oziroma program) se ustvari seznam vseh mo�nih �tirk.*/
	
	{ /*Dolo�imo mo�ne smeri �tirk*/
		int[][] smeri = {{1,0}, {0,1}, {1,1}, {1,-1}};
		for (int x = 0; x < N; x++) {
			for (int y = 0; y < N; y++) {
				for (int[] smer : smeri){
					int dx = smer[0];
					int dy = smer[1];
					if ((0 <= x + (N - 1)*dx) && (0 <= y + (N -1)*dy) && (x + (N - 1)*dx < N) && (y + (N -1)*dy < N)) {
						/* Ustvari novi tabeli, ena s 4 x in druga s 4 y. */
						int[] stirka_x = new int[4];
						int[] stirka_y = new int[4];
						for (int k = 0; k < 4; k++) {
							/* Doda elemente v tabeli. */
							stirka_x[k] = x + dx*k;
							stirka_y[k] = y + dy*k;
						}
						/* V seznam �tirk, ki se ustvari na za�etku, doda nove �tirke.*/
						stirke.add(new Stirke(stirka_x, stirka_y));
						
					}
				}
			}
		}
	}
	
	/* Ustvari novo igro s praznimi polji in dolo�i, da igro za�ne rde�i. */
	
	public Igra() {
		plosca = new Polje[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				plosca[i][j] = Polje.PRAZNO;
			}
		}
		naPotezi = Igralec.RED;
	}

	/*
	 * Metoda, ki preverja ali ima prazno polje nepraznega soseda. �e polje ima nepraznega soseda funkcija osamljeni vrne true, sicer pa false.
	 */
	
	public boolean sosedi(int x, int y) {
		if (plosca[x][y] == Polje.PRAZNO){
			if (plosca[x+1][y] != Polje.PRAZNO || plosca[x-1][y] != Polje.PRAZNO || plosca[x][y+1] != Polje.PRAZNO || plosca[x+1][y-1] != Polje.PRAZNO){
				return true;
			}
		}
		return false;
	}

	/* Metoda, ki izra�una mo�ne poteze in vrne seznam teh. */

	public List<Poteza> poteze() {
		LinkedList<Poteza> seznampotez = new LinkedList<Poteza>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++){
				if (sosedi(i,j) == true){
				seznampotez.add(new Poteza(i,j));
				}
			}
		}
	return seznampotez;
	}
	
	/* Metoda, ki preverja, �e so vsa polja �tirke enaka. Torej ali so vsa rde�a oziroma modra. 
	 * �e niso enaka vrne false, �e pa so vsa 4 polja enaka pa true. */
	
	private boolean vse_enake(Stirke s, Polje p) {
		for (int k = 0; k < 4; k++) {
			if (plosca[s.x[k]][s.y[k]] != p) {
				return false;
			}
		}
		return true;
	}
	
	/* 
	 * Stanje igre - preveri ali je zmagal rde�i, modri, je neodlo�eno ali pa igre �e ni konec.
	 */
	
	public Stanje stanje() {
		for (Stirke s : stirke) {
			/* Preveri, �e je zmagal rde�i in �e je nastavi zmagovalno �tirko. */
			if (vse_enake(s, Polje.RED)) {
				Stanje stanje = Stanje.ZMAGA_RED;
				stanje.setZmagovalna(s);
				return stanje;
			} 
			/* Preveri;
			 *Preveri, �e je zmagal modri in �e je nastavi zmagovalno �tirko. */
			else if (vse_enake(s, Polje.BLUE)) {
				Stanje stanje = Stanje.ZMAGA_BLUE;
				stanje.setZmagovalna(s);
				return stanje;
				
			}
		}
		/* Preverimo, �e je kak�no polje prazno, vkolikor je igre �e ni konec. */
		for (int i = 0; i < N; i++ ) {
			for (int j = 0; j < N; j++) {
				if (plosca[i][j] == Polje.PRAZNO) {
					if (naPotezi == Igralec.RED) {
						return Stanje.NA_VRSTI_RED;
					} else {
						return Stanje.NA_VRSTI_BLUE;
					}
				}
			}
		}
		/* �e ni zmagal noben igralec in so vsa polja zasedena, vrne neodlo�en rezultat. */
		return Stanje.NEODLOCENO;
	}
	
	/*
	 * Odigraj novo potezo pogleda, �e je odigrana poteza ustrezna. 
	 * �e je vrne true in shrani polje ustreznega igralca in nastavi, da je na potezi nasprotni igralec.
	 * �e poteza ni ustrezna, vrne false.
	 */
	
	public boolean odigrajpotezo (Poteza p) {
		List<Poteza> k = poteze();
		if ( k.contains(plosca[p.getX()][p.getY()])) {
			plosca[p.getX()][p.getY()] = naPotezi.getPolje();
			naPotezi = naPotezi.nasprotnik();
			return true;
		} else {
			return false;
		}
	}
}
