/**
 * 
 */
package fr.utt.sig.suivi;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import fr.utt.sig.suivi.config.Config;
import fr.utt.sig.suivi.beans.User;
import fr.utt.sig.suivi.dao.Data;
import fr.utt.sig.suivi.dao.Liste;

/**
 * @author oracle
 * 
 */
@SuppressWarnings("all")
public class loginController extends MultiActionController {
	protected final Log logger = LogFactory.getLog(getClass());
	private Config config;
	private Data data;
	private Liste listes;

	public void setConfig(Config paramConfig) {
		this.config = paramConfig;
	}

	public void setData(Data paramData) {
		this.data = paramData;
	}

	/**
	 * @param listes the listes to set
	 */
	public void setListes(Liste listes) {
		this.listes = listes;
	}

	public ModelAndView login(HttpServletRequest paramHttpServletRequest,
			HttpServletResponse paramHttpServletResponse)
			throws ServletException, IOException {
		long l = 23694;
		User user = this.data.getUser(l);
		paramHttpServletRequest.getSession().setAttribute("user", user);
		if ((user.isAdmin()) || (user.isEnseignant())
				|| (user.isResponsable_uv()) || (user.isPersonnel()))
			paramHttpServletResponse.sendRedirect("rechercheEtudiant.do");
		if (user.isEtudiant()) {
			HashMap hashMap = new HashMap();
			hashMap.put("etudiant", this.data.getEtudiantp(user.getId()));
			return new ModelAndView("vueProfil", hashMap);
		}
		return null;
	}
}
