/**
 * 
 */
package fr.utt.sig.suivi;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

import fr.utt.sig.suivi.beans.Recherche;
import fr.utt.sig.suivi.beans.User;
import fr.utt.sig.suivi.dao.Data;
import fr.utt.sig.suivi.dao.Liste;
import fr.utt.sig.suivi.beans.profil.Etudiant;

/**
 * @author naneon
 * 
 */
@SuppressWarnings("all")

public class RechercheEtudiantController extends BaseFormController {
	protected final Log logger = LogFactory.getLog(getClass());
	private Liste listes;
	private List lperiodes = null;
	private Recherche recherche;
	private Data suivi;
	private List etudiants;
	private String selectView = "rechercheEtudiantForm";

	public void setRecherche(Recherche paramRecherche) {
		this.recherche = paramRecherche;
	}

	public void setListes(Liste paramListe) {
		this.listes = paramListe;
	}

	public void setSuivi(Data paramData) {
		this.suivi = paramData;
	}

	public RechercheEtudiantController() {
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
		paramHttpServletRequest.getSession().setAttribute("idselected", null);
		this.recherche.setId(null);
		return this.recherche;
	}

	protected Map referenceData(HttpServletRequest paramHttpServletRequest)
			throws Exception {
		HashMap hashMap = new HashMap();
		List diplomeList = this.listes.getListeDiplomes();
		hashMap.put("listeDiplomes", diplomeList);
		this.lperiodes = this.listes.getListePeriodes(this.recherche);
		hashMap.put("listePeriodes", this.lperiodes);
		List niveauxList = this.listes.getListeNiveaux(this.recherche);
		hashMap.put("listeNiveaux",niveauxList	);
		List situationList = this.listes.getListeSituations(this.recherche);
		hashMap.put("listeSituations",situationList);
		List uvList = this.listes.getListeUVs(this.recherche);
		hashMap.put("listeUVs", uvList);
		User user = (User) paramHttpServletRequest.getSession()
				.getAttribute("user");
		if (user != null)
			if (user.isConseiller()) {
				this.recherche.setConseiller(user.isConseiller());
				this.recherche.setConseiller_id(user.getId());
				this.recherche.setPeriode_conseiller(user.getPeriode());
				this.recherche.setLib_pr_conseiller(user.getLib_periode());
				this.recherche.setEtu_conseilles("true");
			} else {
				this.recherche.setConseiller(false);
				this.recherche.setPeriode_conseiller(null);
				this.recherche.setLib_pr_conseiller(null);
				this.recherche.setEtu_conseilles(null);
			}
		this.recherche.setAction("recherche");
		this.recherche.setType_action("recherche");
		this.recherche.setMessage(null);
		paramHttpServletRequest.getSession().removeAttribute("message");
		return hashMap;
	}

	protected ModelAndView onSubmit(HttpServletRequest paramHttpServletRequest,
			HttpServletResponse paramHttpServletResponse, Object paramObject,
			BindException paramBindException) throws Exception {
		this.recherche = ((Recherche) paramObject);
		if (paramHttpServletRequest.getParameter("action").equals("recherche")) {
			List list = null;
			list = this.suivi.getEtudiantsR(this.recherche);
			if (list.size() < 1) {
				paramBindException
						.rejectValue("diplome", "notFound",
								"Il n'y a pas d'Žtudiants correspondants ˆ ces critres");
				return showForm(paramHttpServletRequest,
						paramHttpServletResponse, paramBindException);
			}
			if (list.size() > 1) {
				PagedListHolder localPagedListHolder = new PagedListHolder(
						list);
				localPagedListHolder.setPageSize(10);
				paramHttpServletRequest.getSession().setAttribute("etudiants",
						localPagedListHolder);
				paramHttpServletRequest.setAttribute("etudiants",
						localPagedListHolder);
				return showForm(paramHttpServletRequest,
						paramHttpServletResponse, paramBindException);
			}
			long l = ((Etudiant) list.iterator().next()).getId()
					.longValue();
			paramHttpServletRequest.getSession().setAttribute("idselected",
					new Long(l).toString());
			Etudiant localEtudiant = this.suivi.getEtudiantp(l);
			return new ModelAndView(getSuccessView(), "etudiant", localEtudiant);
		}
		if (paramHttpServletRequest.getParameter("action").equals("filtre")) {
			paramHttpServletRequest.getSession().removeAttribute("etudiants");
			paramHttpServletRequest.getSession().removeAttribute("message");
		}
		return showForm(paramHttpServletRequest, paramHttpServletResponse,
				paramBindException);
	}

	protected ModelAndView handleRequestInternal(
			HttpServletRequest paramHttpServletRequest,
			HttpServletResponse paramHttpServletResponse) throws Exception {
		String str = paramHttpServletRequest.getParameter("page");
		if (str != null) {
			PagedListHolder pagedListHolder = (PagedListHolder) paramHttpServletRequest
					.getSession().getAttribute("etudiants");
			if (pagedListHolder != null) {
				if ("suiv".equals(str))
					pagedListHolder.nextPage();
				if ("prec".equals(str))
					pagedListHolder.previousPage();
				paramHttpServletRequest.setAttribute("etudiants",
						pagedListHolder);
			}
		} else {
			paramHttpServletRequest.getSession().removeAttribute("etudiants");
		}
		return super.handleRequestInternal(paramHttpServletRequest,
				paramHttpServletResponse);
	}

	protected ModelAndView handleInvalidSubmit(
			HttpServletRequest paramHttpServletRequest,
			HttpServletResponse paramHttpServletResponse) throws Exception {
		this.logger.info("handleInvalidSubmit");
		BindException bindException = new BindException(
				formBackingObject(paramHttpServletRequest), getCommandName());
		bindException.reject("duplicateFormSubmission",
				"Duplicate form submission");
		return showForm(paramHttpServletRequest, paramHttpServletResponse,
				bindException);
	}
}
