
public class SalaTeranyina extends Sala {

	public SalaTeranyina(Tresor tresor , Monstre monstre , boolean explorada) {
		super(tresor , monstre , explorada);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	@Override
	public boolean intentarSortir() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * 
	 */
	@Override
	public boolean intentarSortir(int forca) {
		int aleatori = (int)Math.floor((Math.random()*12) + 1);
		boolean exit = false ; 

		if (aleatori <= forca) {
			exit = true ;
		}
		
		return exit ;
	}

	/**
	 * toString para mostrar les informacion de la Sala 
	 */
	public String toString() {
		return "Sala Teranyina " + super.toString() ; 
	}
	
}
