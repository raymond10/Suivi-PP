/**
 * 
 */
package fr.utt.sig.suivi;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import fr.utt.sig.suivi.beans.Recherche;
import fr.utt.sig.suivi.config.Config;
import fr.utt.sig.suivi.dao.Data;
import fr.utt.sig.suivi.dao.Liste;
import fr.utt.sig.suivi.editions.Edition;

/**
 * @author naneon
 * 
 */
@SuppressWarnings("all")

public class ReportController extends MultiActionController {
	protected final Log logger = LogFactory.getLog(getClass());
	private Data suivi;
	private Liste listes;
	private Recherche recherche;
	private Config config;
	private ModelAndView message;

	public void setSuivi(Data paramData) {
		this.suivi = paramData;
	}
	
	

	/**
	 * @param listes the listes to set
	 */
	public void setListes(Liste listes) {
		this.listes = listes;
	}



	public void setRecherche(Recherche paramRecherche) {
		this.recherche = paramRecherche;
	}

	public void setConfig(Config paramConfig) {
		this.config = paramConfig;
	}

	/**
	 * @return the message
	 */
	public ModelAndView getMessage() {
		return message;
	}



	/**
	 * @param message the message to set
	 */
	public void setMessage(ModelAndView message) {
		this.message = message;
	}



	public ModelAndView report(HttpServletRequest paramHttpServletRequest,
			HttpServletResponse paramHttpServletResponse)
			throws ServletException, IOException {
		String periode = paramHttpServletRequest.getParameter("periode");
		String diplome = paramHttpServletRequest.getParameter("diplome");
		String niveau = paramHttpServletRequest.getParameter("niveau");
		String mention = paramHttpServletRequest.getParameter("mention");
		String libniv = paramHttpServletRequest.getParameter("libniv");
		String libpv = paramHttpServletRequest.getParameter("libpv");
		String nom = paramHttpServletRequest.getParameter("nom");
		String num = paramHttpServletRequest.getParameter("num");
		String type = paramHttpServletRequest.getParameter("type");
		String reoriente = paramHttpServletRequest.getParameter("reoriente");
		if ((periode != null) && (diplome != null) && (niveau != null)
				&& (periode.length() > 0) && (diplome.length() > 0)) {
			this.recherche = new Recherche();
			this.recherche.setAction("edition");
			this.recherche.setType_action("edition");
			this.recherche.setDiplome(diplome);
			this.recherche.setPeriode(periode);
			this.recherche.setNiveau(niveau);
			this.recherche.setReoriente(reoriente);
			this.recherche.setMention(mention);
			if (nom.length() > 0) {
				nom = nom.replace("*", "%");
				this.recherche.setNom(nom);
				this.recherche.setNumero(null);
			}
			if (num.length() > 0) {
				this.recherche.setNumero(num);
				this.recherche.setNom(null);
			}
			if ((type.equals("1")) || (type.equals("6")))
				this.recherche.setConvoques(true);
			if (type.equals("4"))
				this.recherche.setDoubledipl(true);
			if ((type.equals("5")) || (type.equals("6")))
				this.recherche.setTrimaster(true);
			else
				this.recherche.setTrimaster(false);
			lanceEdition(paramHttpServletRequest);
		}
		return null;
	}

	private String lanceEdition(HttpServletRequest paramHttpServletRequest) {
		Edition edition = new Edition(paramHttpServletRequest);
		edition.setSuivi(this.suivi);
		edition.setCodeJury(this.listes);
		edition.setConfig(this.config);
		edition.setRecherche(this.recherche);
		int i = new Integer(paramHttpServletRequest.getParameter("type"))
				.intValue();
		edition.setTypeDocument(i);
		String libpv = paramHttpServletRequest.getParameter("libpv");
		edition.setLibpv(libpv);
		Object obj = paramHttpServletRequest.getParameter("libniv")
				.replaceAll(" ", "-");
		int j = ((String) obj).indexOf("-");
		if (j > 0)
			obj = ((String) obj).substring(j + 1,
					((String) obj).length());
		String nom = paramHttpServletRequest.getParameter("nom");
		String num = paramHttpServletRequest.getParameter("num");
		edition.setNumero(num);
		edition.setName(nom);
		if (nom.length() > 0) {
			obj = nom.replaceAll(" ", "-");
			obj = ((String) obj).replace("*", "");
		}
		if (num.length() > 0)
			obj = num;
		obj = ((String) obj).trim();
		if (((String) obj).length() > 0)
			obj = "-" + (String) obj;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
				"dd-MM-yyyy-HHmmss");
		String date = simpleDateFormat.format(new Date());
		if (date.length() > 0)
			date = "-" + date;
		String nomEdition = "PV-" + paramHttpServletRequest.getParameter("id") + date
				+ (String) obj + ".pdf";
		edition.setNom(nomEdition);
		String diplome = paramHttpServletRequest.getParameter("diplome");
		edition.setDiplome(diplome);
		if (diplome.equals("MST"))
			edition.setCompte(true);
		String email = paramHttpServletRequest.getParameter("mail");
		edition.setMail(email);
		Thread thread = new Thread(edition);
		thread.start();
		return edition.getInformation();
	}
}
