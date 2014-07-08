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
public class User {
	public static int ADMIN = 5;
	public static int ENSEIGNANT = 10;
	public static int ETUDIANT = 20;
	public static int SECRETAIRE = 30;
	public static int PERSONNEL = 40;
	public static int CONSEILLER = 50;
	public static int RESPONSABLE_UV = 60;
	private long id;
	private String nom;
	private String prenom;
	private String login;
	private String nconseiller;
	private String email;
	private int itype;
	private Integer isPersonnel;
	private Integer isAdmin;
	private Integer isEtudiant;
	private Integer isEnseignant;
	private Integer isResponsable_uv;
	private Integer isConseiller;
	private boolean personnel;
	private boolean admin;
	private boolean etudiant;
	private boolean enseignant;
	private boolean responsable_uv;
	private boolean conseiller;
	private List uvs;
	private List etu_conseilles;
	private String periode;
	private String lib_periode;

	public long getId() {
		return this.id;
	}

	public void setId(long paramLong) {
		this.id = paramLong;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String paramString) {
		this.email = paramString;
	}

	public boolean isType(int paramInt) {
		return this.itype == paramInt;
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

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String paramString) {
		this.login = paramString;
	}

	public String getNconseiller() {
		return this.nconseiller;
	}

	public void setNconseiller(String paramString) {
		this.nconseiller = paramString;
	}

	public List getUvs() {
		return this.uvs;
	}

	public void setUvs(List paramList) {
		this.uvs = paramList;
	}

	public List getEtu_conseilles() {
		return this.etu_conseilles;
	}

	public void setEtu_conseilles(List paramList) {
		this.etu_conseilles = paramList;
	}

	public String getPeriode() {
		return this.periode;
	}

	public void setPeriode(String paramString) {
		this.periode = paramString;
	}

	public boolean isPersonnel() {
		this.personnel = (getIsPersonnel().intValue() > 0);
		return this.personnel;
	}

	public boolean isAdmin() {
		this.admin = (getIsAdmin().intValue() > 0);
		return this.admin;
	}

	public boolean isEtudiant() {
		this.etudiant = false;
		if (getIsEtudiant() != null)
			this.etudiant = (getIsEtudiant().intValue() > 0);
		return this.etudiant;
	}

	public boolean isEnseignant() {
		this.enseignant = false;
		if (getIsEnseignant() != null)
			this.enseignant = (getIsEnseignant().intValue() > 0);
		return this.enseignant;
	}

	public boolean isResponsable_uv() {
		this.responsable_uv = false;
		if (getIsResponsable_uv() != null)
			this.responsable_uv = (getIsResponsable_uv().intValue() > 0);
		return this.responsable_uv;
	}

	public boolean isConseiller() {
		this.conseiller = false;
		if (getIsConseiller() != null)
			this.conseiller = (getIsConseiller().intValue() > 0);
		return this.conseiller;
	}

	public Integer getIsPersonnel() {
		return this.isPersonnel;
	}

	public void setIsPersonnel(Integer paramInteger) {
		this.isPersonnel = paramInteger;
	}

	public Integer getIsAdmin() {
		return this.isAdmin;
	}

	public void setIsAdmin(Integer paramInteger) {
		this.isAdmin = paramInteger;
	}

	public Integer getIsEtudiant() {
		return this.isEtudiant;
	}

	public void setIsEtudiant(Integer paramInteger) {
		this.isEtudiant = paramInteger;
	}

	public Integer getIsEnseignant() {
		return this.isEnseignant;
	}

	public void setIsEnseignant(Integer paramInteger) {
		this.isEnseignant = paramInteger;
	}

	public Integer getIsResponsable_uv() {
		return this.isResponsable_uv;
	}

	public void setIsResponsable_uv(Integer paramInteger) {
		this.isResponsable_uv = paramInteger;
	}

	public Integer getIsConseiller() {
		return this.isConseiller;
	}

	public void setIsConseiller(Integer paramInteger) {
		this.isConseiller = paramInteger;
	}

	public String getLib_periode() {
		return this.lib_periode;
	}

	public void setLib_periode(String paramString) {
		this.lib_periode = paramString;
	}
}
