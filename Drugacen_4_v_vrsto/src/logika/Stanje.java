package logika;

public enum Stanje {
	NA_VRSTI_RED,
	NA_VRSTI_BLUE,
	ZMAGA_RED,
	ZMAGA_BLUE,
	NEODLOCENO;
	
	private Stirke zmagovalna;
	
	/* Nastavi zmagovalno �tirko na ni�, saj na za�etku igre nimamo �e zmagovalne �tirke. */
	
	private Stanje() {
		zmagovalna = null;
	}
	
	/* Nastavi zmagovalno �tirko. */
	
	public void setZmagovalna (Stirke s) {
		assert (this == ZMAGA_RED || this == ZMAGA_BLUE);
		zmagovalna = s;
	}
	
	/* getZmagovalna vrne zmagovalno �tirko. */
	
	public Stirke getZmagovalna() {
		return zmagovalna;
	}

}
