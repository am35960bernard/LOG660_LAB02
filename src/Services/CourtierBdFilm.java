package Services;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import Controllers.StaticVariables;
import Model.Film;
import Services.Filters.FilterCriteria;
import Services.Filters.Parameters;

public class CourtierBdFilm {

	public List<Film> chercherFilm(FilterCriteria criterias)
	{
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transactionFilm = null;
		try
		{
			transactionFilm = session.beginTransaction();
			List<Parameters> myCriterias = criterias.getCriterias();
			String hql = "FROM Film f JOIN f.ActeurFilm af JOIN af.acteur a";
			String hqlWHERE = " WHERE 1 = 1"; // This wouldn't cut it if we used OR statements though.
			List<String> title = new ArrayList<String>();
			List<String> actors = new ArrayList<String>();

			for(Parameters element : myCriterias)
			{
				boolean singleValue = element.getValues().size() == 1;
				switch(element.getName())
				{

				case StaticVariables.ACTEUR_NOM :
					actors = element.getValues();
					hqlWHERE += singleValue ?
							" AND af.acteur.nomComplet = :nomActeur"
							+ " AND af.film.idFilm = f.idFilm"
							:
								" AND f.titre IN (SELECT af.film.titre FROM ActeurFilm af"
								+ " WHERE af.acteur.nomComplet IN (:nomActeur)"
								+ " GROUP BY af.film.titre"
								+ " HAVING COUNT(*) >= :nbActeurs)";

					break;

				case StaticVariables.ANNEE_FILM :
					hqlWHERE += singleValue ? 
							" AND f.anneeSortie = :anneeSortie"
							:
								" AND f.anneeSortie BETWEEN(:min, :max)";
					break;

				case StaticVariables.LANGUE_FILM :
					hqlWHERE += singleValue ? 
							" AND f.langueOriginale = :langueOriginale"
							:
								" AND f.langueOriginale IN(:langueOriginale)";					
					break;

				case StaticVariables.NOM_GENRE :
					hqlWHERE += singleValue ? 
							" AND f.genre.nom = :genreNom"
							:
								" AND f.genre.nom IN(:genreNom)";	
					break;

				case StaticVariables.NOM_PAYS :
					hqlWHERE += singleValue ? 
							" AND pays.nom  = :paysNom"
							:
								" AND pays.nom IN(:paysNom)";
					break;

				case StaticVariables.REALISATEUR_NOM :
					hqlWHERE += singleValue ? 
							" AND realisateurCinema.nomComplet  = :nomRealisateur"
							:
								" AND realisateurCinema.nomComplet IN(:nomRealisateur)";
					break;

				case StaticVariables.TITRE_FILM :
					hqlWHERE += singleValue ? 
							" AND titre = :titre"
							:
								" AND titre IN(:titre)";
					title = element.getValues();
					break;
				}

			}
			String request = hql.concat(hqlWHERE);

			Query query = session.createQuery(request);
			query.setParameterList("titre", title);
			query.setParameter("nbActeurs", actors.size());
			query.setParameterList("nomActeur", actors);

			List<Film> results = query.list();
			for (Film f : results) {
				System.out.println(f.getTitre());
			}
			return results;

		}
		catch(HibernateException e)
		{

		}
		finally 
		{
			session.close();
		}
		return null;

	}
}
