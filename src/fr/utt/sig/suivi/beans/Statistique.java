/**
 * 
 */
package fr.utt.sig.suivi.beans;

/**
 * @author naneon
 * 
 */
public class Statistique {
	private String id;
	private String libelle;
	private String valeur;

	public String getId() {
		return this.id;
	}

	public void setId(String paramString) {
		this.id = paramString;
	}

	public String getLibelle() {
		return this.libelle;
	}

	public void setLibelle(String paramString) {
		this.libelle = paramString;
	}

	public String getValeur() {
		return this.valeur;
	}

	public void setValeur(String paramString) {
		this.valeur = paramString;
	}
}
