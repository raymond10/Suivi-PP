/**
 * 
 */
package fr.utt.sig.suivi.beans.profil;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author naneon
 * 
 */
@SuppressWarnings("all")
public class Diplome {
	protected final Log logger = LogFactory.getLog(getClass());
	private String etu_id;
	private String id;
	private String libelle;
	private String prd_debut;
	private String prd_fin;
	private String debut;
	private String fin;
	private String reoriente;
	private String niveau_entree;
	private String specialite;
	private String num_diplome;
	private String situation;
	private String demissionaire;
	private String transfert;
	private List inscriptions_adm;
	private List inscriptions_uv;
	private List categories;
	private List legende;
	private LinkedHashMap stotal = new LinkedHashMap();
	private LinkedHashMap totalg = new LinkedHashMap();
	private List<Totaux> totaux;

	public void calcule_stotal() {
		if ((this.inscriptions_adm != null) && (this.categories != null)) {
			Iterator localIterator1 = this.inscriptions_adm.iterator();
			while (localIterator1.hasNext()) {
				int i = 0;
				Inscription localInscription = (Inscription) localIterator1
						.next();
				String str1 = localInscription.getAn_univ()
						+ localInscription.getPrd_univ();
				int j = 0;
				Iterator localIterator2 = this.categories.iterator();
				while (localIterator2.hasNext()) {
					LinkedHashMap localLinkedHashMap1 = (LinkedHashMap) localIterator2
							.next();
					int k = 0;
					int m = 0;
					String str2 = (String) localLinkedHashMap1.get("LIB_ABR");
					Iterator localIterator3 = this.inscriptions_uv.iterator();
					while (localIterator3.hasNext()) {
						LinkedHashMap localLinkedHashMap2 = (LinkedHashMap) localIterator3
								.next();
						if (localLinkedHashMap2 != null) {
							String str3 = (String) localLinkedHashMap2
									.get("PERIODE");
							String str4 = (String) localLinkedHashMap2
									.get("CATEG_ABR");
							if ((str1.equals(str3)) && (str2.equals(str4))) {
								String str5 = (String) localLinkedHashMap2
										.get("CREDIT");
								if (str5 != null) {
									k += new Integer(str5).intValue();
									m = 1;
								}
							}
						}
					}
					if (m != 0) {
						if (k != -1)
							this.stotal.put(str1 + str2, Integer.valueOf(k));
						i += k;
						j = 1;
					}
				}
				if (j != 0)
					this.stotal.put(str1 + "TS", Integer.valueOf(i));
			}
		}
	}

	public void calcule_totalg() {
		if (!this.stotal.isEmpty()) {
			int i = 6;
			if (getId().equals("DOC"))
				i = 5;
			int j = 0;
			int k = 0;
			Iterator localIterator1 = this.categories.iterator();
			while (localIterator1.hasNext()) {
				int m = 0;
				int n = 0;
				LinkedHashMap localLinkedHashMap = (LinkedHashMap) localIterator1
						.next();
				String str1 = (String) localLinkedHashMap.get("LIB_ABR");
				Iterator localIterator2 = this.stotal.keySet().iterator();
				while (localIterator2.hasNext()) {
					String str2 = (String) localIterator2.next();
					if (str2.substring(i).equals(str1)) {
						Integer localInteger = (Integer) this.stotal.get(str2);
						if (localInteger != null) {
							int i1 = localInteger.intValue();
							m += i1;
							n = 1;
						}
					}
				}
				if (n != 0) {
					this.totalg.put(str1, Integer.valueOf(m));
					j += m;
					k = 1;
				}
				if (k != 0)
					this.totalg.put("TG", Integer.valueOf(j));
			}
		}
	}

	public List getCategories() {
		return this.categories;
	}

	public void setCategories(List paramList) {
		this.categories = paramList;
	}

	public String getDebut() {
		return this.debut;
	}

	public void setDebut(String paramString) {
		this.debut = paramString;
	}

	public String getFin() {
		return this.fin;
	}

	public void setFin(String paramString) {
		this.fin = paramString;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String paramString) {
		this.id = paramString;
	}

	public List getInscriptions_adm() {
		return this.inscriptions_adm;
	}

	public void setInscriptions_adm(List paramList) {
		this.inscriptions_adm = paramList;
	}

	public List getInscriptions_uv() {
		return this.inscriptions_uv;
	}

	public void setInscriptions_uv(List paramList) {
		this.inscriptions_uv = paramList;
	}

	public String getLibelle() {
		return this.libelle;
	}

	public void setLibelle(String paramString) {
		this.libelle = paramString;
	}

	public LinkedHashMap getStotal() {
		return this.stotal;
	}

	public void setStotal(LinkedHashMap paramLinkedHashMap) {
		this.stotal = paramLinkedHashMap;
	}

	public List<Totaux> getTotaux() {
		return this.totaux;
	}

	public void setTotaux(List<Totaux> paramList) {
		this.totaux = paramList;
	}

	public String getEtu_id() {
		return this.etu_id;
	}

	public void setEtu_id(String paramString) {
		this.etu_id = paramString;
	}

	public List getLegende() {
		return this.legende;
	}

	public void setLegende(List paramList) {
		this.legende = paramList;
	}

	public String getPrd_debut() {
		return this.prd_debut;
	}

	public void setPrd_debut(String paramString) {
		this.prd_debut = paramString;
	}

	public String getPrd_fin() {
		return this.prd_fin;
	}

	public void setPrd_fin(String paramString) {
		this.prd_fin = paramString;
	}

	public String getReoriente() {
		return this.reoriente;
	}

	public void setReoriente(String paramString) {
		this.reoriente = paramString;
	}

	public String getNiveau_entree() {
		return this.niveau_entree;
	}

	public void setNiveau_entree(String paramString) {
		this.niveau_entree = paramString;
	}

	public String getSpecialite() {
		return this.specialite;
	}

	public void setSpecialite(String paramString) {
		this.specialite = paramString;
	}

	public String getSituation() {
		return this.situation;
	}

	public void setSituation(String paramString) {
		this.situation = paramString;
	}

	public String getNum_diplome() {
		return this.num_diplome;
	}

	public void setNum_diplome(String paramString) {
		this.num_diplome = paramString;
	}

	/**
	 * @return the demissionaire
	 */
	public String getDemissionaire() {
		return demissionaire;
	}

	/**
	 * @param demissionaire the demissionaire to set
	 */
	public void setDemissionaire(String demissionaire) {
		this.demissionaire = demissionaire;
	}

	/**
	 * @return the transfert
	 */
	public String getTransfert() {
		return transfert;
	}

	/**
	 * @param transfert the transfert to set
	 */
	public void setTransfert(String transfert) {
		this.transfert = transfert;
	}

	public LinkedHashMap getTotalg() {
		return this.totalg;
	}

	public void setTotalg(LinkedHashMap paramLinkedHashMap) {
		this.totalg = paramLinkedHashMap;
	}
}
