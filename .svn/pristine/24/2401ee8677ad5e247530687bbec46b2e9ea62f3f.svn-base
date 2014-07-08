/**
 * 
 */
package fr.utt.sig.suivi.beans;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import fr.utt.sig.suivi.dao.Liste;

/**
 * @author oracle
 * 
 */
public class Recherche {
	protected final Log logger = LogFactory.getLog(getClass());
	private String nom;
	private String numero;
	private String diplome;
	private String niveau;
	private String mention;
	private String periode;
	private String situation;
	private String uv;
	private String etu_conseilles;
	private String action;
	private String typedoc;
	private String id;
	private Liste listes;
	private String url_action;
	private String lib_action;
	private String type_action;
	private String message;
	private String periode_conseiller;
	private String reoriente;
	private boolean doubledipl;
	private boolean trimaster;
	private String spec;
	private int niv = 0;
	private String lib_pr_conseiller;
	private boolean conseiller;
	private long conseiller_id;
	private boolean convoques;

	public String getMention() {
		return this.mention;
	}

	public void setMention(String paramString) {
		this.mention = paramString;
	}

	public boolean isTrimaster() {
		return this.trimaster;
	}

	public void setTrimaster(boolean paramBoolean) {
		this.trimaster = paramBoolean;
	}

	public void setMessage(String paramString) {
		this.message = paramString;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String paramString) {
		this.id = paramString;
	}

	public void setListes(Liste paramListe) {
		this.listes = paramListe;
	}

	public Recherche() {
		setAction("recherche");
		setType_action("recherche");
	}

	public Recherche(String paramString1, String paramString2) {
		setDiplome(paramString1);
		setPeriode(this.listes.getPeriodeDefaut(paramString1));
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String paramString) {
		this.nom = paramString;
	}

	public String getDiplome() {
		return this.diplome;
	}

	public void setDiplome(String paramString) {
		this.diplome = paramString;
	}

	public String getNiveau() {
		return this.niveau;
	}

	public void setNiveau(String paramString) {
		int i = 0;
		this.niveau = paramString;
		if (this.niveau != null) {
			if (this.niveau.indexOf("-M1") > 0) {
				setSpec(this.niveau.substring(0, 4).trim());
				setNiv(1);
				i = 1;
			}
			if (this.niveau.indexOf("-M2") > 0) {
				setSpec(this.niveau.substring(0, 4).trim());
				setNiv(2);
				i = 1;
			}
			if (i != 0)
				this.niveau = null;
		}
	}

	public String getPeriode() {
		return this.periode;
	}

	public void setPeriode(String paramString) {
		this.periode = paramString;
	}

	public String getAction() {
		return this.action;
	}

	public void setAction(String paramString) {
		this.action = paramString;
	}

	public String getNumero() {
		return this.numero;
	}

	public void setNumero(String paramString) {
		this.numero = paramString;
	}

	public String getTypedoc() {
		return this.typedoc;
	}

	public void setTypedoc(String paramString) {
		this.typedoc = paramString;
	}

	public String getLib_action() {
		this.lib_action = (this.type_action.equals("recherche") ? "Rechercher"
				: "Lancer l'Ždition");
		return this.lib_action;
	}

	public void setLib_action(String paramString) {
		this.lib_action = paramString;
	}

	public String getUrl_action() {
		this.url_action = (this.type_action.equals("recherche") ? "rechercheEtudiant.do"
				: "editions.do");
		return this.url_action;
	}

	public void setUrl_action(String paramString) {
		this.url_action = paramString;
	}

	public String getType_action() {
		return this.type_action;
	}

	public void setType_action(String paramString) {
		this.type_action = paramString;
	}

	public String getMessage() {
		return this.message;
	}

	public String getSituation() {
		return this.situation;
	}

	public void setSituation(String paramString) {
		this.situation = paramString;
	}

	public String getUv() {
		return this.uv;
	}

	public void setUv(String paramString) {
		this.uv = paramString;
	}

	public void setConseiller(boolean paramBoolean) {
		this.conseiller = paramBoolean;
	}

	public boolean isConseiller() {
		return this.conseiller;
	}

	public long getConseiller_id() {
		return this.conseiller_id;
	}

	public void setConseiller_id(long paramLong) {
		this.conseiller_id = paramLong;
	}

	public String getPeriode_conseiller() {
		return this.periode_conseiller;
	}

	public void setPeriode_conseiller(String paramString) {
		this.periode_conseiller = paramString;
	}

	public String getEtu_conseilles() {
		return this.etu_conseilles;
	}

	public void setEtu_conseilles(String paramString) {
		this.etu_conseilles = paramString;
	}

	public String getLib_pr_conseiller() {
		return this.lib_pr_conseiller;
	}

	public void setLib_pr_conseiller(String paramString) {
		this.lib_pr_conseiller = paramString;
	}

	public boolean isConvoques() {
		return this.convoques;
	}

	public void setConvoques(boolean paramBoolean) {
		this.convoques = paramBoolean;
	}

	public String getReoriente() {
		return this.reoriente;
	}

	public void setReoriente(String paramString) {
		this.reoriente = paramString;
	}

	public boolean isDoubledipl() {
		return this.doubledipl;
	}

	public void setDoubledipl(boolean paramBoolean) {
		this.doubledipl = paramBoolean;
	}

	public String getSpec() {
		return this.spec;
	}

	public void setSpec(String paramString) {
		this.spec = paramString;
	}

	public int getNiv() {
		return this.niv;
	}

	public void setNiv(int paramInt) {
		this.niv = paramInt;
	}

}
