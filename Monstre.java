public class Monstre implements Combatent {

	// Atributs de l'objecte Monstre 
	private String nom ; 
	private int vida ; 
	private int penalitzacio ; 
	private int valorExperiencia ; 
	
	public Monstre(String nom , int vida , int penalitzacio ) {
		this.nom = nom ; 
		this.vida = vida ; 
		this.penalitzacio = penalitzacio ; 
		this.valorExperiencia = this.vida * 2 ; 
	}
	
	/**
	 * @return the vida
	 */
	public int getVida() {
		return vida;
	}

	/**
	 * @param vida the vida to set
	 */
	public void setVida(int vida) {
		this.vida = vida;
	}


	/**
	 * @return the penalitzacio
	 */
	public int getPenalitzacio() {
		return penalitzacio;
	}

	/**
	 * @param penalitzacio the penalitzacio to set
	 */
	public void setPenalitzacio(int penalitzacio) {
		this.penalitzacio = penalitzacio;
	}

	/**
	 * @return the valorExperiencia
	 */
	public int getValorExperiencia() {
		return valorExperiencia;
	}

	/**
	 * @param valorExperiencia the valorExperiencia to set
	 */
	public void setValorExperiencia(int valorExperiencia) {
		this.valorExperiencia = valorExperiencia;
	}
	
	
	public String toString() {
		return this.nom + " " + this.vida;
	}

	/**
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	/**
	 * Mètode per calcular l'atac del monstre
	 */
	public int calcularAtac() {
		
		return (int)Math.floor((Math.random() * this.vida) + 1); 
	}

	@Override
	/**
	 * Mètode per restar la vida del monstre quan rep un dany 
	 */
	public void rebreDany(int quantitat) {
		this.vida -= quantitat ; 
		if(this.vida < 0) {
			this.vida = 0 ; 
		}
	}

	@Override
	/**
	 * Mètode que retorna cert si el monstre segueix viu i fals si està mort 
	 */
	public boolean estaViu() {
		boolean viu = false ; 
		if(this.vida > 0) {
			viu = true ;
		}
		
		return viu ; 
	}
}