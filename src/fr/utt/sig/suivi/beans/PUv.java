/**
 * 
 */
package fr.utt.sig.suivi.beans;

/**
 * @author naneon
 * 
 */
public class PUv {
	private String code;
	private String resuv;
	private Integer credit;
	private String profil;
	private String legende;
	private String legendeU;

	public String getCode() {
		return this.code;
	}

	public void setCode(String paramString) {
		this.code = paramString;
	}

	public Integer getCredit() {
		return this.credit;
	}

	public void setCredit(Integer paramInteger) {
		this.credit = paramInteger;
	}

	public String getProfil() {
		return this.profil;
	}

	public void setProfil(String paramString) {
		this.profil = paramString;
	}

	public String getResuv() {
		return this.resuv;
	}

	public void setResuv(String paramString) {
		this.resuv = paramString;
	}

	public String getLegende() {
		return this.legende;
	}

	public void setLegende(String paramString) {
		this.legende = paramString;
	}

	public String getLegendeU() {
		return this.legendeU;
	}

	public void setLegendeU(String paramString) {
		this.legendeU = paramString;
	}
}
