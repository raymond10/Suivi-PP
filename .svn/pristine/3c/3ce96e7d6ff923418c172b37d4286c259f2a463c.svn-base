/**
 * 
 */
package fr.utt.sig.suivi.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import fr.utt.sig.suivi.beans.Recherche;
import fr.utt.sig.suivi.beans.User;
import fr.utt.sig.suivi.beans.Uv;
import fr.utt.sig.suivi.beans.profil.Etudiant;

/**
 * @author oracle
 * 
 */
@SuppressWarnings("all")
public abstract interface DataService {

	public abstract List<Uv> getUVsByCode(String paramString)
			throws DataAccessException;

	public abstract Uv getUV(String paramString);

	public abstract Uv getUV(String paramString1, String paramString2);

	public abstract List<Etudiant> getEtudiants() throws DataAccessException;

	public abstract List getListeInsUV(Long paramLong, String paramString)
			throws DataAccessException;

	public abstract List getParcours(long paramLong, String paramString)
			throws DataAccessException;

	public abstract List getDiplomes(long paramLong) throws DataAccessException;

	public abstract String getMineurs(long paramLong)
			throws DataAccessException;

	public abstract List getEtudiantsR(Recherche paramRecherche)
			throws DataAccessException;
	
	public abstract List getEtudiantsDouble(Recherche paramRecherche)
			throws DataAccessException;

	public abstract List getEtudiantsP(Recherche paramRecherche)
			throws DataAccessException;

	public abstract List getEtudiantsPT(Recherche paramRecherche)
			throws DataAccessException;

	public abstract User getUser(long paramLong) throws DataAccessException;

	public abstract User getUser(String paramString) throws DataAccessException;
	
	public abstract List getListeCodeJuri()	throws DataAccessException;
	
	public abstract List getListeCodeJuryING()	throws DataAccessException;
	
	public abstract List getListeCodeJuryMAST()	throws DataAccessException;

}
