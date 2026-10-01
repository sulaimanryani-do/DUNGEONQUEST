
public class SalaPont extends Sala {

	public SalaPont(Tresor tresor , Monstre monstre , boolean explorada) {
		super(tresor , monstre , explorada);
		// TODO Auto-generated constructor stub
	}

	public boolean intentarSortir(int agilitat) {

		int aleatori = (int)Math.floor((Math.random()*12) + 1); 
		boolean exit = false ; 
		if(aleatori <= agilitat) {
			exit = true ; 
		}
		
		return exit ; 
	}

	public String toString() {
		return "Sala Pont " + super.toString() ; 
	}

	@Override
	public boolean intentarSortir() {
		return false;
	}

}
