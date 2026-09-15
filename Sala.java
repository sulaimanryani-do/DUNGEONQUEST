
public abstract class Sala {

	// Atributus del Objecto Sala
	private Tresor tresor ; 
	private Monstre monstre ; 
	private boolean explorada ; 
	
	public Sala(Tresor tresor , Monstre monstre , boolean explorada) {
		this.tresor = tresor ;
		this.monstre = monstre ; 
		this.explorada = explorada ; 
	}
	
	public Sala() {
		tresor = null ; 
		monstre = null ; 
		explorada = true ; 
	}
	
	/**
	 * @return the monstre
	 */
	public Monstre getMonstre() {
		return monstre;
	}

	/**
	 * @param monstre the monstre to set
	 */
	public void setMonstre(Monstre monstre) {
		this.monstre = monstre;
	}

	/**
	 * @return the explorada
	 */
	public boolean isExplorada() {
		return explorada;
	}

	/**
	 * @param explorada the explorada to set
	 */
	public void setExplorada(boolean explorada) {
		this.explorada = explorada;
	}

	// methodes Abstract para les classes hijas de la Sala
	public abstract boolean intentarSortir();
	public abstract boolean intentarSortir(int forcaAgilitat);

	/**
	 * @return the tresor
	 */
	public Tresor getTresor() {
		return tresor;
	}

	/**
	 * @param tresor the tresor to set
	 */
	public void setTresor(Tresor tresor) {
		this.tresor = tresor;
	}
	
	public String toString() {
		// si la sala te un Tresor mostrem el Tresor , si la Sala te un monstre mostrem el monstre 
		return (this.tresor != null ? "Tresor: " +  this.tresor : "") + (this.monstre != null ? " Monstre: " + this.monstre : "") ; 
	}
	
	
}
