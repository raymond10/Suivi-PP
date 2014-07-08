/**
 * 
 */
package fr.utt.sig.suivi.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import fr.utt.sig.suivi.beans.Extraction;
import fr.utt.sig.suivi.beans.ListeOV;
import fr.utt.sig.suivi.beans.Recherche;

/**
 * @author oracle
 * 
 */
@SuppressWarnings("all")
public abstract interface ListeService {
	public abstract List<ListeOV> getListeDiplomes() throws DataAccessException;

	public abstract List<ListeOV> getListePeriodes(Recherche paramRecherche)
			throws DataAccessException;

	public abstract List<ListeOV> getListeSituations(Recherche paramRecherche)
			throws DataAccessException;

	public abstract String getPeriodeDefaut(String paramString)
			throws DataAccessException;

	public abstract List<ListeOV> getListeNiveaux(Recherche paramRecherche)
			throws DataAccessException;

	public abstract List<ListeOV> getListeUVs(Recherche paramRecherche)
			throws DataAccessException;

	public abstract List<ListeOV> getListeSpecialites(String paramString)
			throws DataAccessException;

	public abstract List<ListeOV> getListeCategories(String paramString)
			throws DataAccessException;

	public abstract List<ListeOV> getListePeriodes(String paramString)
			throws DataAccessException;

	public abstract List<ListeOV> getListeDiplomesA(Extraction paramExtraction)
			throws DataAccessException;

	public abstract List<ListeOV> getListeNiveaux(Extraction paramExtraction)
			throws DataAccessException;

	public abstract List<ListeOV> getListeNiveauxA(Extraction paramExtraction)
			throws DataAccessException;

	public abstract List<ListeOV> getListePeriodes(Extraction paramExtraction)
			throws DataAccessException;

	public abstract List<ListeOV> getListePeriodesA(Extraction paramExtraction)
			throws DataAccessException;

	public abstract List<ListeOV> getListePeriodesAB()
			throws DataAccessException;

	public abstract List getListeCodeJury()	throws DataAccessException;
	
	public abstract List getListeCodeJuryING2()	throws DataAccessException;
	
	public abstract List getListeCodeJuryMST()	throws DataAccessException;
}
