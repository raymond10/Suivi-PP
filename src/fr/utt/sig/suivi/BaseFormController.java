/**
 * 
 */
package fr.utt.sig.suivi;

import fr.utt.sig.suivi.beans.User;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

/**
 * @author naneon
 * 
 */
@SuppressWarnings("all")
public class BaseFormController extends SimpleFormController {
	public ModelAndView showForm(HttpServletRequest paramHttpServletRequest,
			HttpServletResponse paramHttpServletResponse,
			BindException paramBindException) throws Exception {
		User user = (User) paramHttpServletRequest.getSession()
				.getAttribute("user");
		if (user != null) {
			if ((user.isAdmin()) || (user.isEnseignant())
					|| (user.isPersonnel())
					|| (user.isResponsable_uv()))
				return super.showForm(paramHttpServletRequest,
						paramHttpServletResponse, paramBindException);
			erreur();
		}
		paramHttpServletResponse.sendRedirect("login.do");
		return null;
	}

	private ModelAndView erreur() {
		String str = "Vous n'êtes pas autorisé à accéder à cette fonctionnalité.";
		HashMap localHashMap = new HashMap();
		localHashMap.put("message", str);
		return new ModelAndView("erreur", localHashMap);
	}
}
