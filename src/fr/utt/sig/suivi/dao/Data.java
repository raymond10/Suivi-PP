/**
 * 
 */
package fr.utt.sig.suivi.dao;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.SqlMapClientTemplate;

import com.ibatis.common.logging.Log;
import com.ibatis.common.logging.LogFactory;

import fr.utt.sig.suivi.beans.Recherche;
import fr.utt.sig.suivi.beans.User;
import fr.utt.sig.suivi.beans.Uv;
import fr.utt.sig.suivi.beans.profil.Diplome;
import fr.utt.sig.suivi.beans.profil.Etudiant;

/**
 * @author oracle
 * 
 */
@SuppressWarnings("all")
public class Data implements DataService {

	protected final Log logger = LogFactory.getLog(getClass());
	private SqlMapClientTemplate sqlMapClientTemplate;
	
	public Data() {
		LogFactory.selectLog4JLogging();
	}

	public void setSqlMapClientTemplate(
			SqlMapClientTemplate paramSqlMapClientTemplate) {
		this.sqlMapClientTemplate = paramSqlMapClientTemplate;
	}

	public SqlMapClientTemplate getSqlMapClientTemplate() {
		return this.sqlMapClientTemplate;
	}

	public List getUVsByCode(String paramString) throws DataAccessException {
		return this.sqlMapClientTemplate.queryForList("getUvsByCode",
				paramString.toUpperCase() + "%");
	}

	public List getUVs() throws DataAccessException {
		return this.sqlMapClientTemplate.queryForList("getUvs", null);
	}

	public Uv getUV(String paramString) {
		HashMap localHashMap = new HashMap(2);
		localHashMap.put("uv", paramString);
		localHashMap.put("an_univ", "2006");
		return (Uv) this.sqlMapClientTemplate.queryForObject("getUv",
				localHashMap);
	}

	public Uv getUV(String paramString1, String paramString2) {
		HashMap localHashMap = new HashMap(2);
		localHashMap.put("uv", paramString1);
		localHashMap.put("an_univ", paramString2);
		return (Uv) this.sqlMapClientTemplate.queryForObject("getUv",
				localHashMap);
	}

	public List getEtudiants() throws DataAccessException {
		return this.sqlMapClientTemplate.queryForList("getEtudiants", null);
	}

	public List getListeInsUV(Long paramLong, String paramString)
			throws DataAccessException {
		HashMap localHashMap = new HashMap(2);
		localHashMap.put("etu_id", paramLong);
		localHashMap.put("diplome", paramString);
		//getListeInsUV
		return this.sqlMapClientTemplate.queryForList("getInsUV",
				localHashMap);
	}

	public List getParcours(long paramLong, String paramString)
			throws DataAccessException {
		HashMap localHashMap = new HashMap(2);
		localHashMap.put("etu_id", new Long(paramLong));
		return this.sqlMapClientTemplate.queryForList("getDiplomes",
				localHashMap);
	}

	public List getDiplomes(long paramLong) throws DataAccessException {
		HashMap localHashMap = new HashMap();
		localHashMap.put("etu_id", new Long(paramLong));
		List localList = this.sqlMapClientTemplate.queryForList("getDiplomes",
				localHashMap);
		Iterator localIterator = localList.iterator();
		while (localIterator.hasNext()) {
			Diplome localDiplome = (Diplome) localIterator.next();
			localDiplome.calcule_stotal();
		}
		return localList;
	}

	public String getMineurs(long paramLong) throws DataAccessException {
		HashMap localHashMap = new HashMap(1);
		localHashMap.put("etu_id", new Long(paramLong));
		List localList = this.sqlMapClientTemplate.queryForList("getMineurs",
				localHashMap);
		String str1 = "";
		Iterator localIterator = localList.iterator();
		while (localIterator.hasNext()) {
			String str2 = (String) localIterator.next();
			str1 = str1 + str2 + ", ";
		}
		return str1;
	}

	public Etudiant getEtudiantp(long paramLong) {
		Etudiant localEtudiant = (Etudiant) this.sqlMapClientTemplate
				.queryForObject("getEtudiantp", Long.valueOf(paramLong));
		if (localEtudiant != null) {
			Iterator localIterator = localEtudiant.getDiplomes().iterator();
			while (localIterator.hasNext()) {
				Diplome localDiplome = (Diplome) localIterator.next();
				localDiplome.calcule_stotal();
			}
		}
		return localEtudiant;
	}

	public List getEtudiantsR(Recherche paramRecherche)
			throws DataAccessException {
		return this.sqlMapClientTemplate.queryForList("getEtudiantR",
				paramRecherche);
	}

	//Correction edition double dipl™me
	public List getEtudiantsDouble(Recherche paramRecherche)
			throws DataAccessException {
		return this.sqlMapClientTemplate.queryForList("getEtudiantsD",
				paramRecherche);
	}

	public List getEtudiantsP(Recherche paramRecherche)
			throws DataAccessException {
		return this.sqlMapClientTemplate.queryForList("getEtudiantsP",
				paramRecherche);
	}

	public List getEtudiantsPT(Recherche paramRecherche)
			throws DataAccessException {
		return this.sqlMapClientTemplate.queryForList("getEtudiantsPT",
				paramRecherche);
	}

	public User getUser(long paramLong) {
		return (User) this.sqlMapClientTemplate.queryForObject("getUserById",
				Long.valueOf(paramLong));
	}

	public User getUser(String paramString) {
		return (User) this.sqlMapClientTemplate.queryForObject(
				"getUserByLogin", paramString);
	}
	
	@Override
	public List getListeCodeJuri()
			throws DataAccessException {
		// TODO Auto-generated method stub
		List localList = this.sqlMapClientTemplate.queryForList(
				"getListeCodeJuri", null);
		return localList;
	}

	@Override
	public List getListeCodeJuryING() throws DataAccessException {
		// TODO Auto-generated method stub
		List localList = this.sqlMapClientTemplate.queryForList(
				"getListeCodeJuryING", null);
		return localList;
	}

	@Override
	public List getListeCodeJuryMAST() throws DataAccessException {
		// TODO Auto-generated method stub
		List localList = this.sqlMapClientTemplate.queryForList(
				"getListeCodeJuryMAST", null);
		return localList;
	}
}
