
public class SalaTrampa extends Sala{

	public SalaTrampa(Tresor tresor , Monstre monstre , boolean explorada) {
		super(tresor , monstre , explorada);
	}

	@Override
	public boolean intentarSortir() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean intentarSortir(int forcaAgilitat) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public String toString() {
		return "Sala Trampa"; 
	}


}
