package contracts;

import decorators.HotelVilleDecorator;
import decorators.MineDecorator;
import services.IHotelVilleService;
import services.IMineService;
import enums.ERace;
import exceptions.InvariantError;
import exceptions.PostconditionError;
import exceptions.PreconditionError;

public class HotelVilleContract extends HotelVilleDecorator{
	public HotelVilleContract(IHotelVilleService delegate) {
		super(delegate);
	}

	private void checkInvariants() {
		/* estAbandonnee(H) =(min) abandonCompteur(H) = 51 && =(min) appartenance(H) = RIEN */
		if(!(estAbandonnee() && abandonCompteur() == 51 && appartenance() == ERace.RIEN)){
			throw new InvariantError("estAbandonnee(H) =(min) abandonCompteur(H) = 51 && =(min) appartenance(H) = RIEN incorrecte");
		}

		/* 0 <= abandonCompteur(H) <= 51 */
		if(!(abandonCompteur() >= 0 && abandonCompteur() <= 51 )){
			throw new InvariantError("0 <= abandonCompteur(H) <= 51 incorrecte");
		}	
	}

	public int getLargeur() {
		// aucun pre 

		// run
		return super.getLargeur();
	}


	public int getHauteur() {
		// aucun pre 

		// run
		return super.getHauteur();
	}

	public int orRestant(){
		// aucun pre 

		// run
		return super.orRestant();
	}

	public boolean estAbandonnee(){
		// aucun pre 

		// run
		return super.estAbandonnee();
	}
	
	public int abandonCompteur(){
		// aucun pre 

		return super.abandonCompteur();
	}
	
	public ERace appartenance() {
		// TODO Auto-generated method stub
		return super.appartenance();
	}
	
	public IHotelVilleService init(int largeur, int hauteur){

		// pre init(l, h) require largeur %2 = 1
		if(!( (largeur %2) == 1)){
			throw new PreconditionError(" init(l, h) require largeur % 2 = 1.");
		}

		// pre init(l, h) require largeur %2 = 1
		if(!( (hauteur %2) == 1)){
			throw new PreconditionError("init(l, h) require hauteur % 2 = 1.");
		}


		// inv avant 
		checkInvariants();

		// run
		super.init(largeur, hauteur);

		// inv apres
		checkInvariants();

		/* post getLargeur(init(l, h)) = l */
		if(!(getLargeur() == largeur)){
			throw new PostconditionError("getLargeur(init(l, h)) = l incorrecte.");
		}

		/* post getHauteur(init(l, h)) = h */
		if(!(getHauteur() == hauteur)){
			throw new PostconditionError("getHauteur(init(l, h)) = h incorrecte.");
		}

		/* post orRestant(init(l, h)) = 51 */
		if(!(orRestant() == 51)){
			throw new PostconditionError("orRestant(init(l, h)) = 51 incorrecte.");
		}

		return this;
	}

	public  IHotelVilleService depot(int s){

		// inv avant 
		checkInvariants();

		// capture 
		int oldOrRest = orRestant();

		// run
		super.depot(s);

		// inv apres
		checkInvariants();

		/* post orRestant(depot(H,s)) == orRestant(H) + s */
		if(!(orRestant() == (oldOrRest + s))){
			throw new PostconditionError("orRestant(depot(M,s)) == orRestant(M) + s incorrecte");
		}

		return this;

	}
	
	@Override
	public IHotelVilleService setOrRestant(int s) {
		// pre setOrRestant(H, s) require s > 0
		if (!(s > 0)) {
			throw new PreconditionError("setOrRestant(H, s) require s > 0 incorrecte");
		}
		
		// capture 
		int oldOrRestant = orRestant();
		
		// inv avant 
		checkInvariants();
		
		// run
		super.setOrRestant(s);
		
		// inv apres
		checkInvariants();
		
		//post orRestant(setOrRestant(H, s)) = orRestant(H)
		if (!(orRestant() == oldOrRestant)) {
			throw new PostconditionError("orRestant(setOrRestant(H, s)) = orRestant(H) incorrecte");
		}	
		
		//post orRestant(setOrRestant(H, s)) = s
		if (!(orRestant() == s)) {
			throw new PostconditionError("orRestant(setOrRestant(H, s)) = s incorrecte");
		}	
		
		return this;
	}
	
	public IHotelVilleService accueil(ERace r){

		/* pre accueil(H) require !estAbandonnee(H) */
		if((!(estAbandonnee()))){
			throw new PreconditionError("accueil(H) require !estAbandonnee(H) incorrecte");
		}

		// inv avant 
		checkInvariants();

		// capture 
		int oldOrRest = orRestant();

		// run
		super.accueil(r);

		// inv apres
		checkInvariants();

		/*post orRestant(accueil(H,r)) == orRestant(H) */
		if(!(orRestant() == oldOrRest)){
			throw new PostconditionError("orRestant(accueil(H,r)) == orRestant(H) incorrecte");
		}

		/*post abandonCompteur(accueil(H,r)) = 0 */
		if(!(abandonCompteur() == 0)){
			throw new PostconditionError("abandonCompteur(accueil(H,r)) = 0 incorrecte");
		}

		/*post 	appartenance(accueil(M,r)) = r */
		if(!(appartenance() == r)){
			throw new PostconditionError("appartenance(accueil(M,r)) = r incorrecte");
		}
		return this;
	}
	
	public IHotelVilleService abandoned(){

		/* pre abandoned(M) require !estAbandonee(M) */
		if(((estAbandonnee()))){
			throw new PreconditionError("abandoned(H) require estAbandonee(H) incorrecte");
		}

		// inv avant 
		checkInvariants();

		// capture 
		int oldOrRest = orRestant();
		int oldabanComp = abandonCompteur();

		// run
		super.abandoned();

		// inv apres
		checkInvariants();

		/*post orRestant(abandoned(M,s)) == orRestant(M) */
		if(!(orRestant() == oldOrRest)){
			throw new PostconditionError("orRestant(abandoned(M,s)) == orRestant(M) incorrecte");
		}

		/*post abandonCompteur(abandoned(M,s)) = abandonCompteur(M) + 1 */
		if(!(abandonCompteur() == oldabanComp + 1)){
			throw new PostconditionError("abandonCompteur(abandoned(M,s)) = abandonCompteur(M) + 1 incorrecte");
		}
		
		/*post appartenance(accueil(M,r)) = RIEN */
		if(!(appartenance() == ERace.RIEN)){
			throw new PostconditionError("appartenance(accueil(M,r)) = RIEN incorrecte");
		}

		return this;
	}
}
