/**
 * 
 */
package fr.utt.sig.suivi.dao;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.SqlMapClientTemplate;

import fr.utt.sig.suivi.beans.Extraction;

/**
 * @author oracle
 * 
 */
@SuppressWarnings("all")
public class Extractions implements ExtractionsService {

	private SqlMapClientTemplate sqlMapClientTemplate;

	public void setSqlMapClientTemplate(
			SqlMapClientTemplate paramSqlMapClientTemplate) {
		this.sqlMapClientTemplate = paramSqlMapClientTemplate;
	}

	public List<LinkedHashMap> getExportEtudiant(Extraction paramExtraction)
			throws DataAccessException {
		return this.sqlMapClientTemplate.queryForList("getExportEtudiant",
				paramExtraction);
	}

}
