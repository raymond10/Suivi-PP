/**
 * 
 */
package fr.utt.sig.suivi.beans;

import fr.utt.sig.suivi.dao.Liste;

/**
 * @author oracle
 * 
 */
public class Extraction {

	private String nom;
	private String numero;
	private String diplome;
	private String niveau;
	private String periode;
	private String action;
	private String typedoc;
	private String id;
	private Liste listes;
	private String url_action;
	private String lib_action;
	private String type_action;
	private String message;

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

	public Extraction() {
		setAction("extraction");
		setType_action("extraction");
	}

	public Extraction(String paramString1, String paramString2) {
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
		this.niveau = paramString;
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
		this.lib_action = (this.type_action.equals("extraction") ? "Exporter"
				: "Export");
		return this.lib_action;
	}

	public void setLib_action(String paramString) {
		this.lib_action = paramString;
	}

	public String getUrl_action() {
		this.url_action = (this.type_action.equals("extraction") ? "extraction.csv"
				: "extractions.do");
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

}
