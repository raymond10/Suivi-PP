/**
 * 
 */
package fr.utt.sig.suivi.beans.profil;

import java.util.Iterator;
import java.util.List;

/**
 * @author oracle
 * 
 */
@SuppressWarnings("all")
public class Etudiant {
	private Long id;
	private String civ_nom;
	private String civ;
	private String nom;
	private String prenom;
	private String email;
	private String date_nais;
	private String com_nais;
	private String conseiller;
	private String der_dipl_obtenu;
	private String etab_der_dipl;
	private String der_inscription;
	private String der_periode;
	private String bac;
	private String an_bac;
	private String act_formation;
	private String reoriente;
	private List mineurs;
	private String cmineurs;
	private List diplomes;

	public String getAn_bac() {
		return this.an_bac;
	}

	public void setAn_bac(String paramString) {
		this.an_bac = paramString;
	}

	public String getBac() {
		return this.bac;
	}

	public void setBac(String paramString) {
		this.bac = paramString;
	}

	public String getCom_nais() {
		return this.com_nais;
	}

	public void setCom_nais(String paramString) {
		this.com_nais = paramString;
	}

	public String getConseiller() {
		return this.conseiller;
	}

	public void setConseiller(String paramString) {
		this.conseiller = paramString;
	}

	public String getDate_nais() {
		return this.date_nais;
	}

	public void setDate_nais(String paramString) {
		this.date_nais = paramString;
	}

	public String getDer_dipl_obtenu() {
		return this.der_dipl_obtenu;
	}

	public void setDer_dipl_obtenu(String paramString) {
		this.der_dipl_obtenu = paramString;
	}

	public String getDer_inscription() {
		return this.der_inscription;
	}

	public void setDer_inscription(String paramString) {
		this.der_inscription = paramString;
	}

	public String getDer_periode() {
		return this.der_periode;
	}

	public void setDer_periode(String paramString) {
		this.der_periode = paramString;
	}

	public List getDiplomes() {
		Iterator iterator = this.diplomes.iterator();
		while (iterator.hasNext()) {
			Diplome diplome = (Diplome) iterator.next();
			diplome.calcule_stotal();
			if ((diplome.getId().equals("TC"))
					|| (diplome.getId().equals("LB"))
					|| ((diplome.getId().equals("ING")) || (diplome.getId().equals("ING2")))
					|| (diplome.getId().equals("DOC")))
				diplome.calcule_totalg();
		}
		return this.diplomes;
	}

	public void setDiplomes(List paramList) {
		this.diplomes = paramList;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String paramString) {
		this.email = paramString;
	}

	public String getEtab_der_dipl() {
		return this.etab_der_dipl;
	}

	public void setEtab_der_dipl(String paramString) {
		this.etab_der_dipl = paramString;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long paramLong) {
		this.id = paramLong;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String paramString) {
		this.nom = paramString;
	}

	public String getPrenom() {
		return this.prenom;
	}

	public void setPrenom(String paramString) {
		this.prenom = paramString;
	}

	public String getCiv_nom() {
		return this.civ_nom;
	}

	public void setCiv_nom(String paramString) {
		this.civ_nom = paramString;
	}

	public String getCiv() {
		return this.civ;
	}

	public void setCiv(String paramString) {
		this.civ = paramString;
	}

	public String getReoriente() {
		return this.reoriente;
	}

	public void setReoriente(String paramString) {
		this.reoriente = paramString;
	}

	public String getAct_formation() {
		return this.act_formation;
	}

	public void setAct_formation(String paramString) {
		this.act_formation = paramString;
	}

	public String getCmineurs() {
		String mineur1 = "";
		Iterator lesMineurs = this.mineurs.iterator();
		while (lesMineurs.hasNext()) {
			String mineur2 = (String) lesMineurs.next();
			mineur1 = mineur1 + mineur2 + ", ";
		}
		if (mineur1.length() > 2)
			mineur1 = mineur1.substring(0, mineur1.length() - 2);
		this.cmineurs = mineur1;
		return this.cmineurs;
	}

	public void setCmineurs(String paramString) {
		this.cmineurs = paramString;
	}

	public List getMineurs() {
		return this.mineurs;
	}

	public void setMineurs(List paramList) {
		this.mineurs = paramList;
	}
}
