package obj.enums;

/**
 * Created by Tic-Tac on 08/04/2017.
 */
public enum TurretType {

	//MODELE: (à mettre avant le ;)
	//NomDeTypeDeTourelle(paramètres)

	;
	private String type="";

	TurretType(){} //TODO:Ajouter le constructeur qui prendrait en compte chaque paramètre des tourelles

	@Override
	public String toString(){
		return this.type;
	}
}
