
public class Tresor {

	private String nom ; 
	private int valor ; 
	private double pes ;
	
	public Tresor(String nom , int valor , double pes) {
		this.nom = nom ; 
		this.valor = valor ; 
		this.pes = pes ; 
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
	 * @return the valor
	 */
	public int getValor() {
		return valor;
	}
	/**
	 * @param valor the valor to set
	 */
	public void setValor(int valor) {
		this.valor = valor;
	}
	/**
	 * @return the pes
	 */
	public double getPes() {
		return pes;
	}
	/**
	 * @param pes the pes to set
	 */
	public void setPes(double pes) {
		this.pes = pes;
	} 
	
	public String toString() {
		return this.nom + " " + this.valor ; 
	}
	
	
	
}
