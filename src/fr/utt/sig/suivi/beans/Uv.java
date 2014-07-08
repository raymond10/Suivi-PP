/**
 * 
 */
package fr.utt.sig.suivi.beans;

import java.util.List;

/**
 * @author oracle
 * 
 */
@SuppressWarnings("all")
public class Uv {
	private Long id;
	private String uv;
	private String anuniv;
	private String langue;
	private String titre;
	private String objectif;
	private String programme;
	private String creadate;
	private String modifdate;
	private String majauteur;
	private List chargeTrav;
	private List perEnseignement;
	private List antecedents;

	public List getChargeTrav() {
		return this.chargeTrav;
	}

	public void setChargeTrav(List paramList) {
		this.chargeTrav = paramList;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long paramLong) {
		this.id = paramLong;
	}

	public String getUv() {
		return this.uv;
	}

	public void setUv(String paramString) {
		this.uv = paramString;
	}

	public String getAnuniv() {
		return this.anuniv;
	}

	public void setAnuniv(String paramString) {
		this.anuniv = paramString;
	}

	public String getLangue() {
		return this.langue;
	}

	public void setLangue(String paramString) {
		this.langue = paramString;
	}

	public String getTitre() {
		return this.titre;
	}

	public void setTitre(String paramString) {
		this.titre = paramString;
	}

	public String getObjectif() {
		return this.objectif;
	}

	public void setObjectif(String paramString) {
		this.objectif = paramString;
	}

	public String getProgramme() {
		return this.programme;
	}

	public void setProgramme(String paramString) {
		this.programme = paramString;
	}

	public String getCredate() {
		return this.creadate;
	}

	public void setCredate(String paramString) {
		this.creadate = paramString;
	}

	public String getModifdate() {
		return this.modifdate;
	}

	public void setModifdate(String paramString) {
		this.modifdate = paramString;
	}

	public String getAuteur() {
		return this.majauteur;
	}

	public void setAuteur(String paramString) {
		this.majauteur = paramString;
	}

	public List getPerEnseignement() {
		return this.perEnseignement;
	}

	public void setPerEnseignement(List paramList) {
		this.perEnseignement = paramList;
	}

	public List getAntecedents() {
		return this.antecedents;
	}

	public void setAntecedents(List paramList) {
		this.antecedents = paramList;
	}
}
