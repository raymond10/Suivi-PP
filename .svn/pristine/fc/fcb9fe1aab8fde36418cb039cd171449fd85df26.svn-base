/**
 * 
 */
package fr.utt.sig.suivi;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

import fr.utt.sig.suivi.beans.Extraction;
import fr.utt.sig.suivi.dao.Data;
import fr.utt.sig.suivi.dao.Extractions;
import fr.utt.sig.suivi.dao.Liste;

/**
 * @author naneon
 * 
 */
@SuppressWarnings("all")
public class ExtractionsController extends BaseFormController {
	protected final Log logger = LogFactory.getLog(getClass());
	private Liste listes;
	private List lperiodes = null;
	private Extraction extraction;
	private Extractions extractions;
	private Data suivi;
	private String selectView = "extractionsForm";

	public void setExtraction(Extraction paramExtraction) {
		this.extraction = paramExtraction;
	}

	public void setListes(Liste paramListe) {
		this.listes = paramListe;
	}

	public void setSuivi(Data paramData) {
		this.suivi = paramData;
	}

	public ExtractionsController() {
		setBindOnNewForm(true);
		setSessionForm(true);
		setFormView(this.selectView);
		setSuccessView(this.selectView);
		setCommandName("command");
		setCommandClass(Extraction.class);
	}

	protected Object formBackingObject(
			HttpServletRequest paramHttpServletRequest) throws Exception {
		paramHttpServletRequest.getSession().setAttribute("rid", null);
		paramHttpServletRequest.getSession().setAttribute("precid", null);
		this.extraction.setId(null);
		return this.extraction;
	}

	protected Map referenceData(HttpServletRequest paramHttpServletRequest)
			throws Exception {
		HashMap localHashMap = new HashMap();
		this.lperiodes = this.listes.getListePeriodesAB();
		localHashMap.put("listePeriodes", this.lperiodes);
		List localList = this.listes.getListeDiplomesA(this.extraction);
		localHashMap.put("listeDiplomes", localList);
		localHashMap.put("listeNiveaux",
				this.listes.getListeNiveauxA(this.extraction));
		this.extraction.setAction("extraction");
		this.extraction.setType_action("extraction");
		String str = (String) paramHttpServletRequest.getSession()
				.getAttribute("message");
		this.extraction.setMessage(str);
		paramHttpServletRequest.getSession().removeAttribute("message");
		return localHashMap;
	}

	protected ModelAndView onSubmit(HttpServletRequest paramHttpServletRequest,
			HttpServletResponse paramHttpServletResponse, Object paramObject,
			BindException paramBindException) throws Exception {
		this.extraction = ((Extraction) paramObject);
		this.logger.info("periode = " + this.extraction.getPeriode());
		if (paramHttpServletRequest.getParameter("action").equals("extraction")) {
			paramHttpServletResponse.setContentType("application/csv");
			paramHttpServletResponse.setHeader("Content-Disposition",
					"attachment; filename=\"export.csv\"");
			HashMap localHashMap = new HashMap();
			localHashMap.put("dataSource", this.suivi.getSqlMapClientTemplate()
					.getDataSource());
			ModelAndView localModelAndView = new ModelAndView("CSVexport",
					localHashMap);
			return localModelAndView;
		}
		if (paramHttpServletRequest.getParameter("action").equals("filtre"))
			paramHttpServletRequest.getSession().removeAttribute("message");
		return showForm(paramHttpServletRequest, paramHttpServletResponse,
				paramBindException);
	}

	public void setExtractions(Extractions paramExtractions) {
		this.extractions = paramExtractions;
	}
}
