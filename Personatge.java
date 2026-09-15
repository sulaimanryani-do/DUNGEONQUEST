import java.util.ArrayList;
import java.util.Scanner;

public class Personatge implements Combatent , Comparable<Personatge> {

	public static Scanner sc = new Scanner(System.in);

	// Atributs de l'objecte Personatge 
	private String nom ; 
	private int vida ; // valor entre 5 - 20
	private int atac ; // valor 1 - 4 
	private int experiencia ; 
	private int agilitat ; // valor entre 4 - 11
	private int forsa ;  // valor entre 4 - 11
	private Mapa posicio ; // comença a 0 0 
	private ArrayList<Tresor> equipament ; 

	/**
	 * constructor con valors manuals
	 * @param nom
	 * @param vida
	 * @param atac
	 * @param agilitat
	 * @param forsa
	 */
	public Personatge(String nom , int vida , int atac ,  int agilitat , int forsa ) {
		this.nom = nom ; 
		setVida(vida);
		setAtac(atac);
		setAgilitat(agilitat);
		setForsa(forsa);
		posicio = new Mapa(0 , 0); 
		equipament = new ArrayList<Tresor>(this.forsa);
	}

	/**
	 * constructor con valors aleatoris
	 * @param nom
	 */
	public Personatge(String nom) {
		this.nom = nom ; 
		this.vida = (int)Math.floor((Math.random()*16) + 5);
		this.atac = (int)Math.floor((Math.random()*4) + 1);
		this.agilitat = (int)Math.floor((Math.random()*8) + 4);
		this.forsa = (int)Math.floor((Math.random()*8) + 4);
		posicio = new Mapa(0 , 0); 
		equipament = new ArrayList<Tresor>(this.forsa);
	}

	/**
	 * Mètode del jugador per atacar el monstre 
	 * @param m el monstre de la sala 
	 */
	public void atacar(Monstre m) {

		System.out.println(m.getNom() + " Vida: " + m.getVida()); // mostrar la informació del monstre
		int atac = calcularAtac(); // calcular l'atac del jugador 
		int monstreAtac = m.calcularAtac(); // calcular l'atac del monstre 
		System.out.println("Atac: " + atac); // mostrar l'atac del jugador 
		m.rebreDany(atac); // el monstre rep un dany igual a l'atac del jugador 
		if(m.estaViu()) { // si el monstre segueix viu 
			System.out.println("Vida del Monstre: " + m.getVida()); // mostrar la vida del monstre 
			rebreDany(monstreAtac); // el jugador rep un dany igual a l'atac del monstre 
			System.out.println("Dany: " + monstreAtac); // mostrar el dany que ha rebut el jugador 

		}else { // si el monstre ha mort 
			System.out.println("Has matat el Monstre: " + m.getNom()); // mostrar un missatge al jugador 
			System.out.println("Experiència: " + m.getValorExperiencia()); // mostrar l'experiència que ha rebut el jugador 
			this.experiencia += m.getValorExperiencia(); // sumar l'experiència al jugador 
		}

	}

	/**
	 * Mètode del jugador per explorar la sala on es troba 
	 * @param sala la sala actual
	 */
	public void explorar(Sala sala) {

		System.out.println(sala);

			sala.setExplorada(true); // canviar el valor (explorada) de la sala perquè ha estat explorada 
			if (sala instanceof SalaTrampa) {
				System.out.println("Has entrat a una Sala Trampa!!");
				System.out.println("Perdràs un Tresor aleatori del teu equipatge.");
				int num = (int) Math.floor(Math.random() * this.equipament.size());
				if(this.equipament.size() == 0) {
					System.out.println("Te n'has escapat perquè no tens equipatge. Però no t'escaparàs la segona vegada.");
				}else {
					System.out.println("Has perdut el Tresor " + this.equipament.get(num));
					this.equipament.remove(num);
				}
			}else{
				
				if(sala.getMonstre() != null) {
					System.out.println("La Sala conté el Monstre :" + sala.getMonstre());
				}
				if(sala.getTresor() != null) { // si la sala conté un Tresor 
					System.out.println("Has trobat el Tresor " + sala.getTresor() + " \nEl vols agafar? (Y/N):"); // preguntar al jugador si el vol agafar 
					char coger = sc.next().toLowerCase().charAt(0);
					if(coger == 'y') { // si el jugador vol agafar el Tresor 
						if(sala.getTresor() instanceof Pocion) { // si el tresor és una poció
							setVida(this.vida + sala.getTresor().getValor()); // la vida del jugador s'incrementa amb el valor de la poció 
							System.out.println("Has guanyat " + sala.getTresor().getValor() + " punts de vida."); 
						}else {
							if(this.equipament.size() < forsa) { // si hi ha espai a l'equipatge del jugador 
								this.equipament.add(sala.getTresor()); // guardem el Tresor a l'equipatge del jugador 
								sala.setTresor(null); // el Tresor de la sala passa a ser null 
							}else { // si el jugador no té lloc a l'equipatge 
								System.out.println("No tens espai al teu equipatge!"); // avisar amb un missatge 
							}
						}
					}
				}else { // si no hi ha cap tresor 
					System.out.println("No hi ha cap Tresor a la Sala.");
				}

			}
		
	}


	/**
	 * Mètode del jugador per moure's entre les sales 
	 * @param direccio N:nord S:sud E:est O:oest 
	 */
	public void moure(char direccio) {
		if(direccio == 'N' || direccio == 'n') { // si la direcció és cap al nord 

			this.posicio.y--; // moure el jugador a la sala del nord 	

		}else if (direccio == 'S' || direccio == 's') { // si la direcció és cap al sud

			this.posicio.y++;

		}else if (direccio == 'E' || direccio == 'e') {

			this.posicio.x--; 

		}else if (direccio == 'O' || direccio == 'o') {

			this.posicio.x++;

		}
	}

	/**
	 * Mètode per calcular l'atac del jugador
	 */
	public int calcularAtac() {
		return (int)Math.floor((Math.random() * this.atac) + 1);
	}



	/**
	 * @return the posicio
	 */
	public Mapa getPosicio() {
		return posicio;
	}

	/**
	 * @param posicio the posicio to set
	 */
	public void setPosicio(Mapa posicio) {
		this.posicio = posicio;
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
	 * @return the vida
	 */
	public int getVida() {
		return vida;
	}
	/**
	 * @param vida the vida to set
	 */
	public void setVida(int vida) {
		if(vida < 5) {
			vida = 5 ;
		}else if (vida > 20) {
			vida = 20 ; 
		}
		this.vida = vida;
	}
	/**
	 * @return the atac
	 */
	public int getAtac() {
		return atac;
	}
	/**
	 * @param atac the atac to set
	 */
	public void setAtac(int atac) {

		if(atac < 1) {
			atac = 1 ; 
		}else if (atac > 4) {
			atac = 4 ; 
		}

		this.atac = atac;
	}
	/**
	 * @return the experiencia
	 */
	public int getExperiencia() {
		return experiencia;
	}
	/**
	 * @param experiencia the experiencia to set
	 */
	public void setExperiencia(int experiencia) {
		this.experiencia = experiencia;
	}
	/**
	 * @return the agilitat
	 */
	public int getAgilitat() {
		return agilitat;
	}
	/**
	 * @param agilitat the agilitat to set
	 */
	public void setAgilitat(int agilitat) {
		if(agilitat < 4) {
			agilitat = 4; 
		}else if (agilitat  > 11) {
			agilitat = 11 ; 
		}

		this.agilitat = agilitat;
	}
	/**
	 * @return the forsa
	 */
	public int getForsa() {
		return forsa;
	}
	/**
	 * @param forsa the forsa to set
	 */
	public void setForsa(int forsa) {
		if(forsa < 4) {
			forsa = 4 ; 
		}else if (forsa > 11) {
			forsa = 11 ; 
		}

		this.forsa = forsa;
	}

	/**
	 * @return the equipament
	 */
	public ArrayList<Tresor> getEquipament() {
		return equipament;
	}

	/**
	 * @param equipament the equipament to set
	 */
	public void setEquipament(ArrayList<Tresor> equipament) {
		this.equipament = equipament;
	}

	@Override
	/**
	 * Mètode per restar la vida del jugador quan rep un dany 
	 */
	public void rebreDany(int quantitat) {
		this.vida -= quantitat ; 
		if(this.vida < 0) {
			this.vida = 0 ; 
		}
	}

	@Override
	/**
	 * Mètode que retorna cert si el jugador segueix viu i fals si està mort 
	 */
	public boolean estaViu() {

		boolean viu = false ; 
		if(this.vida > 0) {
			viu = true ;
		}

		return viu ; 

	}


	@Override
	/**
	 * Mètode toString per mostrar la informació del jugador
	 */
	public String toString() {
		String info = this.nom + " PV:" + this.vida + " AG:" + this.agilitat + 
				" FS:" + this.forsa + " X:" + this.posicio.x + " Y:" + this.posicio.y  ;
		String equipamentes = " " ; 
		for(int y = 0 ; y < this.equipament.size() ; y++) {
			equipamentes += this.equipament.get(y) + " ";
		}
		return info + equipamentes ;
	}

	@Override
	public int compareTo(Personatge o) {

		if(this.experiencia > o.getExperiencia()) {
			return 1 ; 
		}else if(this.experiencia < o.getExperiencia()) {
			return -1 ; 
		}else {
			return this.nom.compareTo(o.getNom());
		}

		
	}

}