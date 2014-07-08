/**
 * 
 */
package fr.utt.sig.suivi.editions;

import fr.utt.sig.suivi.beans.Recherche;
import fr.utt.sig.suivi.beans.User;
import fr.utt.sig.suivi.beans.profil.Etudiant;
import fr.utt.sig.suivi.config.Config;
import fr.utt.sig.suivi.dao.Data;
import fr.utt.sig.suivi.dao.Liste;

import java.io.File;
import java.util.List;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

/**
 * @author naneon
 * 
 */
@SuppressWarnings("all")
public class Edition implements Runnable {

	private HttpServletRequest req;
	private List listEtudiants;
	private List listPvING2;
	private List listPvMST;
	private List listPv;
	private int typeDocument;
	private Config config;
	private Data suivi;
	private Liste codeJury;
	private Recherche recherche;
	private Etudiant etudiant;
	private int pourcentage;
	private String nom;
	private String libpv;
	private String diplome;
	private String numero;
	private String name;
	private String mail;
	private User user;
	private boolean compte = false;
	private String information;
	protected final Log logger = LogFactory.getLog(getClass());

	public void setEtudiant(Etudiant paramEtudiant) {
		this.etudiant = paramEtudiant;
	}

	public Edition(HttpServletRequest paramHttpServletRequest) {
		this.req = paramHttpServletRequest;
	}

	public void run() {
		Moniteur moniteur = null;
		if (moniteur == null)
			moniteur = new Moniteur();	
		List list = null;
		if ((this.typeDocument == 5) || (this.typeDocument == 6))
			list = this.suivi.getEtudiantsPT(this.recherche);
		//Correction edition double dipl™me
		else if(this.typeDocument == 4)
			list = this.suivi.getEtudiantsDouble(this.recherche);
		else
			list = this.suivi.getEtudiantsP(this.recherche);
		this.listEtudiants = list;
		this.listPv = this.codeJury.getListeCodeJury();
		this.listPvING2 = this.codeJury.getListeCodeJuryING2();
		this.listPvMST = this.codeJury.getListeCodeJuryMST();
		//nom = user.getNom();
		//mail = user.getEmail();
		String dir_export = this.config.getProperties("dir_export");
		String dir_fnt = this.config.getProperties("dir_fnt");
		String dir_img = this.config.getProperties("dir_img");
		String url_photo = this.config.getProperties("url_photo");
		DocumentP document = new DocumentP(this.listEtudiants, this.listPv, this.listPvING2, this.listPvMST, this.typeDocument,
				this.etudiant, moniteur, dir_export, dir_fnt, dir_img,
				url_photo, this.nom, this.libpv, this.diplome, this.numero, this.name, this.compte);
		information = document.getBeConnect();
		String chemin = moniteur.getFchemin();
		this.logger.info(" Chemin : " + chemin);
		try {
			JavaMailSenderImpl javaMailSenderImpl = new JavaMailSenderImpl();
			String smtp_host = this.config.getProperties("smtp_host");
			javaMailSenderImpl.setHost(smtp_host);
			MimeMessage mimeMsg = javaMailSenderImpl.createMimeMessage();
			MimeMessageHelper mimeMsgHelper = new MimeMessageHelper(mimeMsg,
					true);
			mimeMsgHelper.setTo(getMail());
			mimeMsgHelper.setFrom(getMail());
			mimeMsgHelper.setSubject("PV de jury de suivi ");
			mimeMsgHelper.setText("PV de jury de suivi ");
			File file = new File(chemin);
			FileSystemResource fileSystemResource = new FileSystemResource(file);
			String fileName = file.getName();
			mimeMsgHelper.addAttachment(fileName, fileSystemResource);
			javaMailSenderImpl.send(mimeMsg);
		} catch (Exception exception) {
			this.logger.info("Erreur envoi de mail  " + exception.toString());
		}
	}

	/**
	 * @return the listEtudiants
	 */
	public List getListEtudiants() {
		return listEtudiants;
	}

	/**
	 * @param listEtudiants the listEtudiants to set
	 */
	public void setListEtudiants(List listEtudiants) {
		this.listEtudiants = listEtudiants;
	}

	public int getTypeDocument() {
		return this.typeDocument;
	}

	public void setTypeDocument(int paramInt) {
		this.typeDocument = paramInt;
	}

	public int getPourcentage() {
		return this.pourcentage;
	}

	public void setPourcentage(int paramInt) {
		this.pourcentage = paramInt;
	}

	public void setSuivi(Data paramData) {
		this.suivi = paramData;
	}

	public Recherche getRecherche() {
		return this.recherche;
	}

	public void setRecherche(Recherche paramRecherche) {
		this.recherche = paramRecherche;
	}

	public void setConfig(Config paramConfig) {
		this.config = paramConfig;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String paramString) {
		this.nom = paramString;
	}

	public String getLibpv() {
		return this.libpv;
	}

	public void setLibpv(String paramString) {
		this.libpv = paramString;
	}

	/**
	 * @return the diplome
	 */
	public String getDiplome() {
		return diplome;
	}

	/**
	 * @param diplome the diplome to set
	 */
	public void setDiplome(String diplome) {
		this.diplome = diplome;
	}

	public String getMail() {
		return this.mail;
	}

	public void setMail(String paramString) {
		this.mail = paramString;
	}

	public boolean isCompte() {
		return this.compte;
	}

	public void setCompte(boolean paramBoolean) {
		this.compte = paramBoolean;
	}

	/**
	 * @return the numero
	 */
	public String getNumero() {
		return numero;
	}

	/**
	 * @param numero the numero to set
	 */
	public void setNumero(String numero) {
		this.numero = numero;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return the config
	 */
	public Config getConfig() {
		return config;
	}

	/**
	 * @return the suivi
	 */
	public Data getSuivi() {
		return suivi;
	}

	/**
	 * @return the etudiant
	 */
	public Etudiant getEtudiant() {
		return etudiant;
	}

	/**
	 * @param ids the ids to set
	 */
	public void setIds(List ids) {
		this.listEtudiants = ids;
	}

	/**
	 * @return the listPvING2
	 */
	public List getListPvING2() {
		return listPvING2;
	}

	/**
	 * @param listPvING2 the listPvING2 to set
	 */
	public void setListPvING2(List listPvING2) {
		this.listPvING2 = listPvING2;
	}

	/**
	 * @return the listPvMST
	 */
	public List getListPvMST() {
		return listPvMST;
	}

	/**
	 * @param listPvMST the listPvMST to set
	 */
	public void setListPvMST(List listPvMST) {
		this.listPvMST = listPvMST;
	}

	/**
	 * @return the listPv
	 */
	public List getListPv() {
		return listPv;
	}

	/**
	 * @param listPv the listPv to set
	 */
	public void setListPv(List listPv) {
		this.listPv = listPv;
	}

	/**
	 * @return the codeJury
	 */
	public Liste getCodeJury() {
		return codeJury;
	}

	/**
	 * @param codeJury the codeJury to set
	 */
	public void setCodeJury(Liste codeJury) {
		this.codeJury = codeJury;
	}

	/**
	 * @return the information
	 */
	public String getInformation() {
		return information;
	}

	/**
	 * @param information the information to set
	 */
	public void setInformation(String information) {
		this.information = information;
	}

}
