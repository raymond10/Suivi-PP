/**
 * 
 */
package fr.utt.sig.suivi.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.SqlMapClientTemplate;

import com.ibatis.common.logging.Log;
import com.ibatis.common.logging.LogFactory;

import fr.utt.sig.suivi.beans.Extraction;
import fr.utt.sig.suivi.beans.ListeOV;
import fr.utt.sig.suivi.beans.Recherche;

/**
 * @author oracle
 * 
 */
@SuppressWarnings("all")
public class Liste implements ListeService {
	
	protected final Log logger = LogFactory.getLog(getClass());
	private SqlMapClientTemplate sqlMapClientTemplate;
	
	public Liste() {
		LogFactory.selectLog4JLogging();
	}

	public void setSqlMapClientTemplate(
			SqlMapClientTemplate paramSqlMapClientTemplate) {
		this.sqlMapClientTemplate = paramSqlMapClientTemplate;
	}

	public List<ListeOV> getListeDiplomes() throws DataAccessException {
		List localList = this.sqlMapClientTemplate.queryForList(
				"getListeDiplomes", null);
		return localList;
	}

	public List<ListeOV> getListePeriodes(Recherche paramRecherche)
			throws DataAccessException {
		List localList = this.sqlMapClientTemplate.queryForList("getListePeriodes",
				paramRecherche);
		return localList;
	}

	public List<ListeOV> getListeNiveaux(Recherche paramRecherche)
			throws DataAccessException {
		List localList = this.sqlMapClientTemplate.queryForList("getListeNiveaux",
				paramRecherche);
		return localList;
	}

	public List<ListeOV> getListeSituations(Recherche paramRecherche)
			throws DataAccessException {
		List localList = this.sqlMapClientTemplate.queryForList("getListeSituations",
				paramRecherche);
		return localList;
	}

	public List<ListeOV> getListeUVs(Recherche paramRecherche)
			throws DataAccessException {
		List localList = this.sqlMapClientTemplate.queryForList("getListeUVs",
				paramRecherche);
		return localList;
	}

	public List<ListeOV> getListePeriodes(String paramString)
			throws DataAccessException {
		List localList = this.sqlMapClientTemplate.queryForList("getListePeriodes",
				paramString);
		return localList;
	}

	public String getPeriodeDefaut(String paramString)
			throws DataAccessException {
		String localList = (String) this.sqlMapClientTemplate.queryForObject(
				"getListePeriodeDefaut", paramString);
		return localList;
	}

	public List<ListeOV> getListeSpecialites(String paramString)
			throws DataAccessException {
		List localList = this.sqlMapClientTemplate.queryForList(
				"getListeSpecialites", paramString);
		return localList;
	}

	public List<ListeOV> getListeCategories(String paramString)
			throws DataAccessException {
		List localList = this.sqlMapClientTemplate.queryForList(
				"getListeCategories", paramString);
		return localList;
	}

	public List<ListeOV> getListeDiplomesA(Extraction paramExtraction)
			throws DataAccessException {
		List localList = this.sqlMapClientTemplate.queryForList(
				"getListeDiplomesA", paramExtraction);
		return localList;
	}

	public List<ListeOV> getListeNiveaux(Extraction paramExtraction)
			throws DataAccessException {
		List localList = this.sqlMapClientTemplate.queryForList("getListeNiveaux",
				paramExtraction);
		return localList;
	}

	public List<ListeOV> getListeNiveauxA(Extraction paramExtraction)
			throws DataAccessException {
		List localList = this.sqlMapClientTemplate.queryForList("getListeNiveauxA",
				paramExtraction);
		return localList;
	}

	public List<ListeOV> getListePeriodes(Extraction paramExtraction)
			throws DataAccessException {
		List localList = this.sqlMapClientTemplate.queryForList("getListePeriodes",
				paramExtraction);
		return localList;
	}

	public List<ListeOV> getListePeriodesA(Extraction paramExtraction)
			throws DataAccessException {
		List localList = this.sqlMapClientTemplate.queryForList("getListePeriodesA",
				paramExtraction);
		return localList;
	}

	public List<ListeOV> getListePeriodesAB() throws DataAccessException {
		List localList = this.sqlMapClientTemplate.queryForList("getListePeriodesAB",
				null);
		return localList;
	}

	@Override
	public List getListeCodeJury()
			throws DataAccessException {
		// TODO Auto-generated method stub
		List localList = this.sqlMapClientTemplate.queryForList(
				"getListeCodeJury", null);
		return localList;
	}

	@Override
	public List getListeCodeJuryING2() throws DataAccessException {
		// TODO Auto-generated method stub
		List localList = this.sqlMapClientTemplate.queryForList(
				"getListeCodeJuryING2", null);
		return localList;
	}

	@Override
	public List getListeCodeJuryMST() throws DataAccessException {
		// TODO Auto-generated method stub
		List localList = this.sqlMapClientTemplate.queryForList(
				"getListeCodeJuryMST", null);
		return localList;
	}
}
