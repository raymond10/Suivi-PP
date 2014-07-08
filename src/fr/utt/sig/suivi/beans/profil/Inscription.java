/**
 * 
 */
package fr.utt.sig.suivi.beans.profil;

/**
 * @author naneon
 * 
 */
public class Inscription {
	private String id;
	private Long etu_id;
	private String diplome;
	private String an_univ;
	private String prd_univ;
	private String libelle;
	private String formation;
	private String stage;
	private String observation;
	private String soutenance;
	private String specialite;
	private String niveau;
	private String decision;
	private String situation;
	private String session;
	private String diplome_num;

	public String getDiplome_num() {
		return this.diplome_num;
	}

	public void setDiplome_num(String paramString) {
		this.diplome_num = paramString;
	}

	public String getAn_univ() {
		return this.an_univ;
	}

	public void setAn_univ(String paramString) {
		this.an_univ = paramString;
	}

	public String getDecision() {
		return this.decision;
	}

	public void setDecision(String paramString) {
		this.decision = paramString;
	}

	public String getDiplome() {
		return this.diplome;
	}

	public void setDiplome(String paramString) {
		this.diplome = paramString;
	}

	public Long getEtu_id() {
		return this.etu_id;
	}

	public void setEtu_id(Long paramLong) {
		this.etu_id = paramLong;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String paramString) {
		this.id = paramString;
	}

	public String getNiveau() {
		return this.niveau;
	}

	public void setNiveau(String paramString) {
		this.niveau = paramString;
	}

	public String getObservation() {
		return this.observation;
	}

	public void setObservation(String paramString) {
		this.observation = paramString;
	}

	public String getPrd_univ() {
		return this.prd_univ;
	}

	public void setPrd_univ(String paramString) {
		this.prd_univ = paramString;
	}

	public String getSituation() {
		return this.situation;
	}

	public void setSituation(String paramString) {
		if (paramString == null)
			paramString = "";
		this.situation = paramString;
	}

	public String getSpecialite() {
		return this.specialite;
	}

	public void setSpecialite(String paramString) {
		this.specialite = paramString;
	}

	public String getLibelle() {
		return this.libelle;
	}

	public void setLibelle(String paramString) {
		this.libelle = paramString;
	}

	public String getFormation() {
		return this.formation;
	}

	public void setFormation(String paramString) {
		this.formation = paramString;
	}

	public String getSession() {
		return this.session;
	}

	public void setSession(String paramString) {
		this.session = paramString;
	}

	public String getStage() {
		return this.stage;
	}

	public void setStage(String paramString) {
		this.stage = paramString;
	}

	public String getSoutenance() {
		return this.soutenance;
	}

	public void setSoutenance(String paramString) {
		this.soutenance = paramString;
	}
}
