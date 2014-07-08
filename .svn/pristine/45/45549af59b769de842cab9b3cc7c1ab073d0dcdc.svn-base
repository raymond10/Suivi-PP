/**
 * 
 */
package fr.utt.sig.suivi;

import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

/**
 * @author naneon
 * 
 */
@SuppressWarnings("all")
public class ErrorsController extends MultiActionController {
	
	public ModelAndView handleErrors(
			HttpServletRequest paramHttpServletRequest,
			HttpServletResponse paramHttpServletResponse)
			throws ServletException {
		String str = "Il y a des erreurs";
		HashMap hashMap = new HashMap();
		hashMap.put("message", str);
		return new ModelAndView("erreur", hashMap);
	}

	public ModelAndView handle404(HttpServletRequest paramHttpServletRequest,
			HttpServletResponse paramHttpServletResponse)
			throws ServletException {
		String str = "Page introuvable";
		HashMap localHashMap = new HashMap();
		localHashMap.put("message", str);
		return new ModelAndView("erreur", localHashMap);
	}
}
