import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Masmorra {

	public static Scanner sc = new Scanner(System.in);

	public static final int M = 5 ; // nombre de files de la masmorra 
	public static final int N = 5 ; // nombre de columnes de la masmorra 

	public static Sala[][] masmorra = new Sala[M][N]; // la masmorra on es mou el jugador
	public static Monstre[] monstresdelJoc = new Monstre[M*N]; // els monstres del joc 
	public static Tresor[] tresorsdelJoc = new Tresor[M*N]; // els tresors del joc 
	public static char[][] masmorraVista = new char[M][N]; // la vista de la masmorra on es veu el jugador, les sales explorades i l'inexplorat
	public static DecimalFormat dosDecimal = new DecimalFormat("#0.00"); // format decimal per controlar el nombre de xifres després de la coma perquè siguin només dues
	public static ArrayList<Personatge> jugadoressobreviscut = new ArrayList<Personatge>(); // llista dels jugadors que han guanyat 
	public static ArrayList<Personatge> jugadores = new ArrayList<Personatge>(); 
	public static ArrayList<Personatge> personatges = new ArrayList<Personatge>(5); 

	public static void main(String[] args) {

		// Jugadores vienen con el joc 
		personatges.add(new Personatge("Goliath", 20, 2, 4, 11));
		personatges.add(new Personatge("Shadow", 7, 4, 11, 5));
		personatges.add(new Personatge("Arthur", 14, 3, 7, 8));
		personatges.add(new Personatge("Vulkan", 5, 4, 5, 11));
		personatges.add(new Personatge("Zephyr", 12, 1, 11, 4));		

		// llista dels monstres 
		omplirMonstres(monstresdelJoc);

		// llista dels tresors del joc  
		omplirTresors(tresorsdelJoc);


		menu();

	}

	/**
	 * Mètode per omplir la masmorra amb les sales, monstres i tresors de manera 
	 * Sala comuna 50%
	 * Sala pont 20%
	 * Sala teranyina 20% 
	 * Sala trampa 10%
	 */
	public static void omplir() {
		int i = 0 ; 

		for(int fil = 0 ; fil < masmorra.length ; fil++) {
			for(int col = 0 ; col < masmorra.length ; col++) {
				int numAleatori = (int)(Math.floor((Math.random()*10 ) + 1));
				if(numAleatori > 4) {
					if(numAleatori == 5) {
						masmorra[fil][col] = new SalaTrampa(null , null , false);
					}
					masmorra[fil][col] = new SalaComuna(tresorsdelJoc[i] , monstresdelJoc[i] , false);
				}else if (numAleatori > 2) {
					masmorra[fil][col] = new SalaPont(tresorsdelJoc[i] , monstresdelJoc[i] , false);
				}else {                 
					masmorra[fil][col] = new SalaTeranyina(tresorsdelJoc[i] , monstresdelJoc[i] , false);
				}
				i++;
			}
		}
	}

	/**
	 * Mètode per calcular el percentatge de les sales explorades
	 * @return el percentatge de sales explorades
	 */
	public static double percentatgeDeSalasExploradas() {
		double contador = 0 ; // variable per comptar el nombre de sales explorades
		for(int fil = 0 ; fil < masmorra.length ; fil++) { // doble bucle per buscar la sala explorada
			for(int col = 0 ; col < masmorra.length ; col++) {
				if(masmorra[fil][col].isExplorada()) { // si la sala està explorada 
					contador++; // incrementar el valor del comptador 
				}
			}
		}
		return (contador / (M * N)) * 100 ; // calcular i retornar el valor del percentatge (M * N és la mida de la masmorra)
	}


	/**
	 * Llista de monstres del joc 
	 * @param monstresdelJoc la taula de monstres a omplir
	 */
	public static void omplirMonstres(Monstre[] monstresdelJoc) {

		monstresdelJoc[0] = new Monstre("Slime Blau", 4, 1);
		monstresdelJoc[1] = new Monstre("Ratolí de Bosc", 3, 2);
		monstresdelJoc[2] = new Monstre("Ratpenat Pudent", 5, 3);
		monstresdelJoc[3] = new Monstre("Esquelet Fràgil", 8, 1);
		monstresdelJoc[4] = new Monstre("Aranya Petita", 6, 2);
		monstresdelJoc[5] = new Monstre("Zombi Lent", 12, 0);
		monstresdelJoc[6] = new Monstre("Llop Afamat", 10, 2);
		//		monstresdelJoc[7] = new Monstre("Goblin Trapella", 7, 3);
		monstresdelJoc[8] = new Monstre("Gòlem de Fang", 18, 0);
		//		monstresdelJoc[9] = new Monstre("Serp Verinosa", 5, 3);
		monstresdelJoc[10] = new Monstre("Esperit Erràtic", 9, 2);
		monstresdelJoc[11] = new Monstre("Cullerot Cremat", 11, 1);
		monstresdelJoc[12] = new Monstre("Harpia Jove", 13, 2);
		monstresdelJoc[13] = new Monstre("Orc Guàrdia", 15, 1);
		monstresdelJoc[14] = new Monstre("Mimic Fals", 14, 3);
		monstresdelJoc[15] = new Monstre("Escarabat Blindat", 16, 0);
		//		monstresdelJoc[16] = new Monstre("Lladre Nocturn", 9, 3);
		monstresdelJoc[17] = new Monstre("Gargola de Pedra", 20, 0);
		monstresdelJoc[18] = new Monstre("Ombra Fugaç", 6, 3);
		//		monstresdelJoc[19] = new Monstre("Trol de Pont", 22, 0);
		monstresdelJoc[20] = new Monstre("Ciclop Jove", 21, 0);
		monstresdelJoc[21] = new Monstre("Bèstia del Pantà", 17, 1);
		//		monstresdelJoc[22] = new Monstre("Fada Corrupta", 8, 2);
		monstresdelJoc[23] = new Monstre("Elemental d'Aigua", 14, 1);
		monstresdelJoc[24] = new Monstre("Guerrer Maleït", 19, 1);

	}

	/**
	 * Llista dels tresors del joc 
	 * @param tresorsdelJoc la taula de tresors a omplir
	 */
	public static void omplirTresors(Tresor[] tresorsdelJoc) {
		tresorsdelJoc[0] = new Tresor("Moneda de Oro Antigua", 10, 0.05);
		tresorsdelJoc[1] = new Tresor("Poción de Hierbas", 25, 0.5);
		tresorsdelJoc[2] = new Tresor("Gema de Alma", 150, 0.2);
		tresorsdelJoc[3] = new Tresor("Llave de Esqueleto", 50, 0.1);
		tresorsdelJoc[4] = new Tresor("Anillo de Ojo de Gato", 300, 0.02);
		tresorsdelJoc[5] = new Tresor("Cáliz de Plata", 500, 1.5);
		//		tresorsdelJoc[6] = //new Tresor("Pluma de Fénix", 1000, 0.01);
		tresorsdelJoc[7] = new Tresor("Botas de Hermes", 850, 1.2);
		tresorsdelJoc[8] = new Tresor("Escudo de Espejo", 1200, 5.5);
		tresorsdelJoc[9] = new Tresor("Mapa de Cuero", 40, 0.3);
		//		tresorsdelJoc[10] = new Tresor("Daga de Veneno", 600, 0.8);
		tresorsdelJoc[11] = new Tresor("Amuleto de Suerte", 450, 0.15);
		tresorsdelJoc[12] = new Pocion("Heal Verde", 3 , 0.1);
		tresorsdelJoc[13] = new Tresor("Capa de Invisibilidad", 2000, 0.5);
		tresorsdelJoc[14] = new Tresor("Martillo de Guerra Rúnico", 1500, 8.0);
		//		tresorsdelJoc[15] = new Tresor("Vial de Maná Infinito", 3000, 0.4);
		tresorsdelJoc[16] = new Tresor("Cinturón de Gigante", 700, 1.0);
		tresorsdelJoc[17] = new Pocion("Heal Amarillo", 5 , 0.25);
		tresorsdelJoc[18] = new Tresor("Collar de Diente de Dragón", 1100, 0.2);
		tresorsdelJoc[19] = new Tresor("Orbe de Teletransporte", 2500, 0.6);
		tresorsdelJoc[20] = new Pocion("Heal Rojo", 7 , 0.7);
		//		tresorsdelJoc[21] = new Tresor("Libro de Hechizos Olvidados", 2200, 2.5);
		tresorsdelJoc[22] = new Tresor("Corona del Rey Loco", 5000, 3.0);
		tresorsdelJoc[23] = new Tresor("Reloj de Arena Roto", 3500, 1.1);
		tresorsdelJoc[24] = new Tresor("Espada Excalibur (Réplica)", 4500, 4.5);	
	}

	/**
	 * Mètode per mostrar la masmorra amb les sales explorades, inexplorades i on està el jugador 
	 * @param matriu la vista de la masmorra 
	 * @param jugador el personatge actual
	 */
	public static void mostrar(char[][] matriu , Personatge jugador) {

		for(int fil = 0 ; fil < masmorra.length ; fil++) {
			for(int col = 0 ;col < masmorra.length ; col++) {
				if( fil == jugador.getPosicio().y && col == jugador.getPosicio().x) {
					matriu[fil][col] = '&';
				}else if (masmorra[fil][col].isExplorada()) {
					matriu[fil][col] = '*';
				}else {
					matriu[fil][col] = '-';
				}
				System.out.print(matriu[fil][col] + " | ");
			}
			System.out.println();
		}

	}

	/**
	 * Mètode per començar a jugar 
	 * @param jugador el personatge que juga
	 */
	public static void jugar(Personatge jugador) {

		int opcion = 0 ; 
		omplir(); // omplim la masmorra 

		/**
		 * Mentre el jugador estigui viu i no hagi sortit de la masmorra, continua el joc
		 */
		while(jugador.estaViu() && (jugador.getPosicio().x >= 0 && jugador.getPosicio().y >= 0 && jugador.getPosicio().x < N && jugador.getPosicio().y < M)) {
			System.out.println(jugador); /* mostrar les dades del jugador */
			mostrar(masmorraVista , jugador); /* mostrar la masmorra */

			// les opcions del jugador
			System.out.println("1.Explorar. \n2.Atacar. \n3.Moure");

			try {
				opcion = sc.nextInt(); 
				switch (opcion) {
				case 1: // si l'opció és explorar

					// cridar al mètode explorar del personatge
					jugador.explorar(masmorra[jugador.getPosicio().y][jugador.getPosicio().x]);

					break;
				case 2: // si l'opció és atacar 
					if(masmorra[jugador.getPosicio().y][jugador.getPosicio().x].getMonstre() != null) { // si la sala té un monstre 
						jugador.atacar(masmorra[jugador.getPosicio().y][jugador.getPosicio().x].getMonstre()); // cridar al mètode atacar del personatge
					}else { // si no té cap monstre 
						System.out.println("No hi ha cap monstre"); // avisem l'usuari 
					}

					break;
				case 3: // si l'opció és moure 
					if(masmorra[jugador.getPosicio().y][jugador.getPosicio().x] instanceof SalaTeranyina) { // si la sala és una sala de teranyines 
						if(masmorra[jugador.getPosicio().y][jugador.getPosicio().x].intentarSortir(jugador.getAgilitat())) { // si el mètode intentarSortir retorna cert 

							// preguntem a l'usuari cap on es vol moure
							System.out.println("Cap on et vols moure (N/O/S/E)");
							char direccion = sc.next().toLowerCase().charAt(0);

							// Si l'usuari ha introduït una adreça que ja no apareix, tornar a preguntar-li per a què no torni a intentar sortir 
							while(direccion != 'n' && direccion != 's' && direccion != 'e' && direccion != 'o') {
								System.out.println("Aquest moviment no existeix!");
								System.out.println("Cap on et vols moure (N/O/S/E)");
								direccion = sc.next().toLowerCase().charAt(0);
							}

							// si la sala encara té el monstre
							if(masmorra[jugador.getPosicio().y][jugador.getPosicio().x].getMonstre() != null && masmorra[jugador.getPosicio().y][jugador.getPosicio().x].getMonstre().estaViu()) {
								jugador.rebreDany(masmorra[jugador.getPosicio().y][jugador.getPosicio().x].getMonstre().getPenalitzacio()); // el jugador rep el dany del monstre 
							}
							jugador.moure(direccion); // cridar al mètode moure del personatge amb la direcció 

						}else { // si no retorna cert, avisem l'usuari 
							System.out.println("No has pogut sortir, intenta-ho un altre cop.");
						}
					}else if (masmorra[jugador.getPosicio().y][jugador.getPosicio().x] instanceof SalaPont) { // si la sala és una sala de pont 
						if(masmorra[jugador.getPosicio().y][jugador.getPosicio().x].intentarSortir(jugador.getForsa())) { // si el mètode intentarSortir de la sala retorna cert 

							// preguntem a l'usuari cap on es vol moure
							System.out.println("Cap on et vols moure (N/O/S/E)"); 
							char direccion = sc.next().toLowerCase().charAt(0);

							// Si l'usuari ha introduït una adreça que ja no apareix, tornar a preguntar-li per a què no torni a intentar sortir, i no perd mes vida.
							while(direccion != 'n' && direccion != 's' && direccion != 'e' && direccion != 'o') {
								System.out.println("Aquest moviment no existeix!");
								System.out.println("Cap on et vols moure (N/O/S/E)");
								direccion = sc.next().toLowerCase().charAt(0);
							}

							// si la sala encara té el monstre
							if(masmorra[jugador.getPosicio().y][jugador.getPosicio().x].getMonstre() != null && masmorra[jugador.getPosicio().y][jugador.getPosicio().x].getMonstre().estaViu()) {
								jugador.rebreDany(masmorra[jugador.getPosicio().y][jugador.getPosicio().x].getMonstre().getPenalitzacio());
							}
							jugador.moure(direccion); // cridar al mètode moure del personatge amb la direcció 

						}else { // si no retorna cert, avisem l'usuari 
							System.out.println("No has pogut sortir, intenta-ho un altre cop. \nHas perdut 1 vida.");
							jugador.rebreDany(1);
						}

					}else { // si la sala és una sala trampa o sala comuna, ja que totes dues tenen el mètode intentarSortir sense paràmetres
						if(masmorra[jugador.getPosicio().y][jugador.getPosicio().x].intentarSortir()) { // si intentarSortir retorna cert 

							// preguntem a l'usuari cap on es vol moure
							System.out.println("Cap on et vols moure (N/O/S/E)");
							char direccion = sc.next().toLowerCase().charAt(0);

							// Si l'usuari ha introduït una adreça que ja no apareix, tornar a preguntar-li per a què no torni a intentar sortir
							while(direccion != 'n' && direccion != 's' && direccion != 'e' && direccion != 'o') {
								System.out.println("Aquest moviment no existeix!");
								System.out.println("Cap on et vols moure (N/O/S/E)");
								direccion = sc.next().toLowerCase().charAt(0);
							}

							// si la sala encara té el monstre
							if(masmorra[jugador.getPosicio().y][jugador.getPosicio().x].getMonstre() != null && masmorra[jugador.getPosicio().y][jugador.getPosicio().x].getMonstre().estaViu()) {
								jugador.rebreDany(masmorra[jugador.getPosicio().y][jugador.getPosicio().x].getMonstre().getPenalitzacio());
							}
							jugador.moure(direccion); // cridar al mètode moure del personatge amb la direcció
						}
					}
					break;

				default: // si no és una de les opcions 
					System.out.println("Aquesta opció no existeix.");
					break;
				}
			} catch (InputMismatchException e) {
				// Captura l'error si l'usuari no introdueix un nombre sencer
				System.out.println("Error InputMismatchException.");
				sc.next(); 
			}

		}


		if((jugador.getPosicio().y < 0 || jugador.getPosicio().x < 0) && jugador.estaViu()) { // si el jugador ha acabat el joc sobrevivint 

			// mostrem el missatge de victòria amb les seves dades 
			System.out.println("VICTORIA \nExperiència:" + jugador.getExperiencia() + " \nNombre de Tresors:" + jugador.getEquipament().size() + " \nNúmero total de monedes d'or:" + oroTotal(jugador) + " \nVida restant:" + jugador.getVida() + " \nPercentatge de la masmorra explorat:" + dosDecimal.format(percentatgeDeSalasExploradas()) + "%\n");
			if(!jugadoressobreviscut.contains(jugador)) { // mirem si el jugador no esta en la lista para que no agregara dos vezes
				jugadoressobreviscut.add(jugador); // afegir el jugador a la llista de jugadors que han guanyat 	
			}
		}else { // si el jugador ha acabat el joc mort
			if(opcion == 3) { // si la causa ha estat per caiguda del pont, que és la tercera opció del menú 

				// mostrem el missatge de derrota amb les seves dades i la causa de la mort 
				System.out.println("FALLIDA \nExperiència:" + jugador.getExperiencia() + " \nCausa de la mort: Caiguda del pont" + " \nPercentatge de sales explorades:" + dosDecimal.format(percentatgeDeSalasExploradas()) + "%\n");

			}else { // si la causa no ha estat per caiguda del pont, llavors la causa ha estat per un monstre

				// mostrem el missatge de derrota amb les seves dades i la causa de la mort
				System.out.println("FALLIDA \nExperiència:" + jugador.getExperiencia() + " \nCausa de la mort: Monstre " + masmorra[jugador.getPosicio().y][jugador.getPosicio().x].getMonstre().getNom() +" \nPercentatge de sales explorades:" + dosDecimal.format(percentatgeDeSalasExploradas()) + "%\n");
			}
		}

	}

	/**
	 * Mètode per calcular el nombre total de monedes d'or que el jugador porta al seu equipatge
	 * @param per el jugador 
	 * @return el total de monedes d'or
	 */
	public static int oroTotal(Personatge per) {
		int total = 0 ; 
		for(int i = 0 ; i < per.getEquipament().size() ; i++) {
			total += per.getEquipament().get(i).getValor(); 
		}
		return total ; 
	}

	/**
	 * Mètode per mostrar els jugadors que han guanyat 
	 */
	static void mostrarJugadoressobreviscut() {

		// ordenar la llista dels jugadors per experiència, si no, pel nom
		Collections.sort(jugadoressobreviscut);
		if(jugadoressobreviscut.size() > 0) { // si hi ha jugadors a la llista 
			System.out.println("\nJugadors que han guanyat.");
			for(int i = 0 ; i < jugadoressobreviscut.size() ; i++) {
				System.out.println(i+1 + " " + jugadoressobreviscut.get(i) + " EX:" + jugadoressobreviscut.get(i).getExperiencia() );
			}

		}else { // si no hi ha jugadors 
			System.out.println("No hi ha cap jugador. ");
		}
	}

	/**
	 * El menú del joc 
	 */
	public static void menu() {

		int num = -1 ; 

		/**
		 * Mentre l'usuari no hagi sortit del joc amb l'opció 0
		 */
		while(num != 0) {
			System.out.println("----- DungeonQuest -----"); // el títol del joc 
			/**
			 * Les opcions del joc 
			 */
			System.out.println("1.Crear Jugador \n2.Mostrar informació del Jugador \n3.Jugar \n4.Llista de Jugadors. \n5.Llista de Jugadors supervivents \n6.Guia del Joc \n0.Sortir ");

			try {

				num = sc.nextInt();
				switch (num) {
				case 1: // si la variable té el valor 1 
					crearJugador(); // cridar a la funció per crear el jugador 

					break;
				case 2: // si la variable té el valor 2
					buscarJugador(); // cridar al mètode que busca el jugador 

					break;
				case 3: // si la variable té el valor 3 
					int i = 0 ; 
					boolean trobat = false ; 
					sc.nextLine();

					// Es mostra el menú principal amb les dues opcions disponibles per a l'usuari
					System.out.println("1. Triar un personatge del joc. \n2. Triar un dels teus jugadors.");
					int opcion = sc.nextInt(); // Es llegeix l'opció seleccionada per l'usuari

					// Si l'usuari tria la primera opció
					if(opcion == 1) {
						i = 0; // S'inicialitza el comptador per recórrer la llista

						// Bucle per mostrar tots els personatges disponibles a l'ArrayList
						while(i < personatges.size()) {
							// Es mostra el número de llista (i+1) i les dades del personatge
							System.out.println((i + 1) + "." + personatges.get(i));
							i++; // S'incrementa el comptador
						}

						// Es demana a l'usuari que trii un personatge de la llista mostrada
						System.out.println("Introdueix el número del personatge:");
						// Es resta 1 a l'entrada de l'usuari per ajustar-ho a l'índex de l'ArrayList (que comença a 0)
						int personage = sc.nextInt() - 1;

						// Es crida la funció jugar passant-li el personatge seleccionat com a paràmetre
						jugar(personatges.get(personage));

					}else if (opcion == 2) { // Si l'usuari tria la segunda opció 
						i = 0 ; // S'inicialitza el comptador per recórrer la llista
						sc.nextLine();
						// preguntem a l'usuari amb qui vol jugar
						System.out.println("Introdueix el nom del Jugador amb qui vols jugar.");
						String nom = sc.nextLine(); // guardem el nom a la variable nom 

						// creem un bucle per veure si el jugador existeix o no 
						while(i < jugadores.size() && !trobat) { 
							if(jugadores.get(i).getNom().equalsIgnoreCase(nom)) { // si el jugador existeix
								trobat = true ; // canviem el valor de la variable trobat perquè el bucle s'acabi 
							}else { // si encara no ha trobat el jugador 
								i++; // incrementem el valor de la variable per mirar la següent posició
							}
						}

						// si el bucle ha trobat el jugador 
						if(trobat) {
							// comença el joc amb el jugador introduït 
							jugar(jugadores.get(i));
						}else { // si no ha trobat el jugador 
							System.out.println("El jugador no existeix.");
						}
					}
					break;
				case 4: // si la variable té el valor 4
					listaJugadores(); // cridar al mètode que mostra la llista de tots els jugadors
					break;
				case 5: // si la variable té el valor 5 
					mostrarJugadoressobreviscut(); // cridar al mètode que mostra la llista de jugadors supervivents 
					break; 
				case 6: // si la variable té el valor 6 
					guia(); // cridar al mètode que mostra la guia del joc 
					break;
				case 0:
					System.out.println("Adiu!");
					break;
				default: // si no és una de les opcions 
					System.out.println("Aquesta opció no existeix.");
					break;
				}

			} catch (InputMismatchException e) { // Captura l'error si l'usuari no introdueix un nombre sencer
				System.out.println("Error InputMismatchException.");
				sc.next(); // Neteja l'entrada incorrecta de l'scanner per evitar un bucle infinit
			} catch (IndexOutOfBoundsException e) {
				System.out.println("Error IndexOutOfBound."); // Captura l'error del index out of boud 
//				sc.next();
			}
		}

	}

	/**
	 * Mètode per crear un jugador 
	 */
	public static void crearJugador() {

		sc.nextLine(); // netejar buffer 
		int i ;
		boolean trobat ;

		/**
		 * Dues opcions per crear el jugador 
		 * Manual: permet al jugador donar els valors que vol al seu jugador 
		 * Aleatori: crea un jugador amb valors aleatoris per als seus atributs (opció per dificultar el joc)
		 */
		System.out.println("1.Manual \n2.Aleatori");
		int num = sc.nextInt();

		String nom ;
		switch (num) {
		case 1: // si l'opció és manual 

			sc.nextLine();

			System.out.println("Nom: "); // preguntem el nom del jugador
			nom = sc.nextLine();

			/**
			 * Creem un bucle per mirar si el jugador ja existeix 
			 */
			i = 0 ;
			trobat = false ; 
			while(i < jugadores.size() && !trobat) {
				if(jugadores.get(i).getNom().equalsIgnoreCase(nom)) {
					trobat= true ; 
				}else {
					i++;
				}
			}

			// si el jugador existeix, avisem l'usuari i no creem el jugador 
			if(trobat) {
				System.out.println("El jugador " + nom + " ja existeix.");
			}else { // si no existeix 

				/**
				 * Preguntem a l'usuari quins valors vol per al seu jugador 
				 */
				System.out.println("Vida (5-20): ");
				int vida = sc.nextInt();
				System.out.println("Atac (1-4): ");
				int atac = sc.nextInt();
				System.out.println("Agilitat (4-11): ");
				int agi = sc.nextInt();
				System.out.println("Força (4-11): ");
				int forsa = sc.nextInt();

				// creem el jugador amb les dades de l'usuari 
				jugadores.add(new Personatge(nom, vida, atac, agi, forsa));
				System.out.println("Jugador creat correctament."); // avisem l'usuari 

			}

			break;
		case 2: // si l'opció és aleatòria 
			sc.nextLine();

			System.out.println("Nom: "); // preguntem el nom del jugador 
			nom = sc.nextLine();
			/**
			 * Creem un bucle per mirar si el jugador ja existeix 
			 */
			i = 0 ;
			trobat = false ; 
			while(i < jugadores.size() && !trobat) {
				if(jugadores.get(i).getNom().equalsIgnoreCase(nom)) {
					trobat= true ; 
				}else {
					i++;
				}
			}

			// si el jugador existeix, avisem l'usuari i no creem el jugador 
			if(trobat) {
				System.out.println("El jugador " + nom + " ja existeix.");
			}else {
				// si el jugador no existeix, creem el jugador amb dades aleatòries entre el mínim i el màxim 
				jugadores.add(new Personatge(nom));
				System.out.println("Jugador creat correctament."); // avisem l'usuari 
			}
			break;
		default: // si no és una de les opcions 
			System.out.println("Aquesta opció no existeix.");
			break;
		}

	}

	/**
	 * Mètode per buscar un jugador i mostrar les seves dades 
	 */
	static void buscarJugador() {
		sc.nextLine();
		boolean existe = false ; 
		System.out.println("Introdueix el nom del jugador: "); // preguntem el nom del jugador 
		String nom = sc.nextLine();

		// bucle per buscar el jugador i mirem si existeix o no 
		for(int i = 0 ; i < jugadores.size() ; i++) {
			if(jugadores.get(i).getNom().equalsIgnoreCase(nom)) {
				System.out.println(jugadores.get(i) + " EX: " + jugadores.get(i).getExperiencia());
				existe = true ; 
			}
		}

		// si el jugador no existeix 
		if(!existe) { 
			System.out.println("No hi ha cap jugador amb aquest nom. "); // avisem el jugador 
		}
	}

	/**
	 * la guia del joc 
	 */
	static void guia() {
		System.out.println("=====================================================================================");
		System.out.println("                             DUNGEONQUEST: GUIA RAPIDA                             ");
		System.out.println("=====================================================================================");
		System.out.println("L'objectiu es: explorar sales, agafar tresors i sortir viu per la vora.");
		System.out.println("Comencaras sempre a la sala superior esquerra de la masmorra.");
		System.out.println();

		System.out.println("--- 1. LLEGENDA DEL MAPA ---");
		System.out.println("  &  -> Posicio actual del teu personatge.");
		System.out.println("  * -> Sala explorada.");
		System.out.println("  -  -> Sala SENSE explorar.");
		System.out.println();

		System.out.println("--- 2. REGLES D'EXPLORACIO ---");
		System.out.println("* NIEBLA DE GUERRA: No sabras si una sala te monstre o tresor");
		System.out.println("  fins que no la hagis EXPLORAT directament!");
		System.out.println("* LIMIT D'EQUIPAMENT: El limit de tresors que pots portar a la");
		System.out.println("  bossa es igual al teu valor de Forca.");
		System.out.println();

		System.out.println("--- 3. EL TEU PERSONATGE (Atributs) ---");
		System.out.println("* Vida (5-20): Si arriba a 0, mors i perds.");
		System.out.println("* Atac (1-4): Determina el dany maxim que pots fer.");
		System.out.println("* Forca (4-11): Limit de tresors i serveix per escapar de teranyines.");
		System.out.println("* Agilitat (4-11): Serveix per no caure dels ponts.");
		System.out.println("* Experiencia: Puja en derrotar monstres. Guanyaras el DOBLE de la");
		System.out.println("  vida inicial del monstre derrotat com a recompensa d'experiencia!");
		System.out.println();

		System.out.println("--- 4. TIPUS DE SALES ---");
		System.out.println("* Sala Comuna (50%): Segura, s'hi entra i se'n surt sense perill.");
		System.out.println("* Sala Teranyina (20%): Per sortir, dau (1-12) <= Forca. Si falles, quedes atrapat.");
		System.out.println("* Sala Pont (20%): Per sortir, dau (1-12) <= Agilitat. Si falles, -1 de vida i no avances.");
		System.out.println("* Sala Trampa (10%): Si l'explores, caus en una trampa i perds un tresor");
		System.out.println("  aleatori del teu equipament per atzar!");
		System.out.println();

		System.out.println("--- 5. OPCIONS DE CADA TORN ---");
		System.out.println("1. Explorar: Descobreix que hi ha a la sala (monstre i/o tresor) i agafa l'or.");
		System.out.println("2. Moure (N, S, E, O): Intentes anar a una sala veina.");
		System.out.println("   * Si fuges amb un monstre viu, et resta vida (penalitzacio de fugida).");
		System.out.println("3. Atacar: Combats contra el monstre de la sala.");
		System.out.println("   * Tu attaques primer. Si el monstre sobreviu, ell t'ataca a tu.");
		System.out.println("   * Si el vences, sumes la seva experiencia al teu personatge!");
		System.out.println();

		System.out.println("--- 6. FI DE LA PARTIDA ---");
		System.out.println("* Victoria: Surts de la masmorra traspassant qualsevol dels seus limits.");
		System.out.println("* Derrota: La teva vida grease a zero.");
		System.out.println("=====================================================================================");
	}

	/**
	 * Mètode que mostra la llista de tots els jugadors
	 */
	static void listaJugadores() {

		if(jugadores.size() > 0) { // si hi ha jugadors 
			System.out.println("------ Jugadors ------");
			for(int i = 0 ; i < jugadores.size() ; i++) {
				System.out.println(jugadores.get(i));
			}
		}else { // si no hi ha cap jugador 
			System.out.println("No hi ha cap jugador.");
		}


	}


}