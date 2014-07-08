/**
 * 
 */
package fr.utt.sig.suivi;

import fr.utt.sig.suivi.beans.Recherche;
import fr.utt.sig.suivi.config.Config;
import fr.utt.sig.suivi.dao.Data;
import fr.utt.sig.suivi.dao.Liste;
import fr.utt.sig.suivi.editions.Edition;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author naneon
 * 
 */
@SuppressWarnings("all")
public class EditionController extends BaseFormController {
	protected final Log logger = LogFactory.getLog(getClass());
	private Config config;
	private Liste listes;
	private List lperiodes = null;
	private Recherche recherche;
	private Data suivi;
	private List etudiants;
	private String selectView = "editionsForm";

	public void setRecherche(Recherche paramRecherche) {
		this.recherche = paramRecherche;
	}

	public void setListes(Liste paramListe) {
		this.listes = paramListe;
	}

	public void setSuivi(Data paramData) {
		this.suivi = paramData;
	}

	public EditionController() {
		setBindOnNewForm(true);
		setSessionForm(true);
		setFormView(this.selectView);
		setSuccessView("vueProfil");
		setCommandName("command");
		setCommandClass(Recherche.class);
	}

	protected Object formBackingObject(
			HttpServletRequest paramHttpServletRequest) throws Exception {
		paramHttpServletRequest.getSession().setAttribute("rid", null);
		paramHttpServletRequest.getSession().setAttribute("precid", null);
		this.recherche.setId(null);
		return this.recherche;
	}

	protected Map referenceData(HttpServletRequest paramHttpServletRequest)
			throws Exception {
		HashMap localHashMap = new HashMap();
		List localList = this.listes.getListeDiplomes();
		localHashMap.put("listeDiplomes", localList);
		this.lperiodes = this.listes.getListePeriodes(this.recherche);
		localHashMap.put("listePeriodes", this.lperiodes);
		localHashMap.put("listeNiveaux",
				this.listes.getListeNiveaux(this.recherche));
		this.recherche.setAction("edition");
		this.recherche.setType_action("edition");
		String str = (String) paramHttpServletRequest.getSession()
				.getAttribute("message");
		this.recherche.setMessage(str);
		paramHttpServletRequest.getSession().removeAttribute("message");
		return localHashMap;
	}

	protected ModelAndView onSubmit(HttpServletRequest paramHttpServletRequest,
			HttpServletResponse paramHttpServletResponse, Object paramObject,
			BindException paramBindException) throws Exception {
		this.recherche = ((Recherche) paramObject);
		if (paramHttpServletRequest.getParameter("action").equals("edition")) {
			lanceEdition(paramHttpServletRequest);
			paramHttpServletRequest.getSession().setAttribute("message", "1");
			paramHttpServletResponse.sendRedirect("editions.do");
			return null;
		}
		if (paramHttpServletRequest.getParameter("action").equals("filtre"))
			paramHttpServletRequest.getSession().removeAttribute("message");
		return showForm(paramHttpServletRequest, paramHttpServletResponse,
				paramBindException);
	}

	private void lanceEdition(HttpServletRequest paramHttpServletRequest) {
		Edition localEdition = new Edition(paramHttpServletRequest);
		this.recherche.setAction("edition");
		this.recherche.setType_action("edition");
		localEdition.setSuivi(this.suivi);
		localEdition.setCodeJury(this.listes);
		localEdition.setConfig(this.config);
		localEdition.setRecherche(this.recherche);
		Thread localThread = new Thread(localEdition);
		localThread.start();
	}

	public Recherche getRecherche() {
		return this.recherche;
	}

	public void setConfig(Config paramConfig) {
		this.config = paramConfig;
	}
}
