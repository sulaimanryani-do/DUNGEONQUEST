
public class SalaComuna extends Sala {

	public SalaComuna(Tresor tresor , Monstre monstre , boolean explorada) {
		super(tresor , monstre , explorada);
	}
	
	@Override
	public boolean intentarSortir() {
		
		return true;
	}

	public String toString() {
		return "Sala Comuna " + super.toString() ; 
	}

	@Override
	public boolean intentarSortir(int forcaAgilitat) {
		
		return false;
	}
}
