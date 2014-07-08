/**
 * 
 */
package fr.utt.sig.suivi.beans.profil;

/**
 * @author oracle
 * 
 */
@SuppressWarnings("all")
public class Total {
	private int total_id;
	private int param_id;
	private String total;
	private String min;
	private String sacquis;
	private int ordre_col;
	private int longueur;
	private int ordre_lg;
	private boolean vide;
	private boolean acquis;
	private int span;

	public boolean isAcquis() {
		return this.sacquis.equals("O");
	}

	public void setAcquis(boolean paramBoolean) {
		this.acquis = paramBoolean;
	}

	public int getTotal_id() {
		return this.total_id;
	}

	public void setTotal_id(int paramInt) {
		this.total_id = paramInt;
	}

	public int getParam_id() {
		return this.param_id;
	}

	public void setParam_id(int paramInt) {
		this.param_id = paramInt;
	}

	public String getTotal() {
		return this.total;
	}

	public void setTotal(String paramString) {
		this.total = paramString;
	}

	public String getMin() {
		return this.min;
	}

	public void setMin(String paramString) {
		this.min = paramString;
	}

	public String getSacquis() {
		return this.sacquis;
	}

	public void setSacquis(String paramString) {
		this.sacquis = paramString;
	}

	public int getOrdre_col() {
		return this.ordre_col;
	}

	public void setOrdre_col(int paramInt) {
		this.ordre_col = paramInt;
	}

	public int getLongueur() {
		return this.longueur;
	}

	public void setLongueur(int paramInt) {
		this.longueur = paramInt;
	}

	public int getOrdre_lg() {
		return this.ordre_lg;
	}

	public void setOrdre_lg(int paramInt) {
		this.ordre_lg = paramInt;
	}

	public boolean isVide() {
		return this.vide;
	}

	public void setVide(boolean paramBoolean) {
		this.vide = paramBoolean;
	}

	public int getSpan() {
		return this.span;
	}

	public void setSpan(int paramInt) {
		this.span = paramInt;
	}
}
