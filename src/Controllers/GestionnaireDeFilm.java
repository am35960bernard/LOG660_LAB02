package Controllers;

import java.util.List;
import java.util.Observable;

import Services.CourtierBdFilm;
import Services.Filters.Criteria;
import Services.Filters.FilterCriteria;
import Services.Filters.Parameters;

public class GestionnaireDeFilm extends Observable {
	
	public void chercher(List<String> titles, List<String> intervals, List<String> pays,List<String> langues, List<String> genres,List<String> realisators,List<String> actors )
	{
		FilterCriteria myFilters = new FilterCriteria();
		if(!titles.isEmpty())
			myFilters.addCriteria(new Parameters(StaticVariables.TITRE_FILM, titles));
		
		if(!intervals.isEmpty())
			myFilters.addCriteria(new Parameters(StaticVariables.ANNEE_FILM, intervals));
		
		if(!pays.isEmpty())
			myFilters.addCriteria(new Parameters(StaticVariables.NOM_PAYS, pays));
		
		if(!langues.isEmpty())
			myFilters.addCriteria(new Parameters(StaticVariables.LANGUE_FILM, langues));
		
		if(!genres.isEmpty())
			myFilters.addCriteria(new Parameters(StaticVariables.NOM_GENRE, genres));
		
		if(!realisators.isEmpty())
			myFilters.addCriteria(new Parameters(StaticVariables.REALISATEUR_NOM, realisators));
		
		if(!actors.isEmpty())
			myFilters.addCriteria(new Parameters(StaticVariables.ACTEUR_NOM, actors));
		
		CourtierBdFilm courtierFilm = new CourtierBdFilm();
		courtierFilm.chercherFilm(myFilters);
	}

}
