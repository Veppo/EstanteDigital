package br.com.veppo.estante.controller.genero;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.veppo.estante.controller.AbstractModelManagerBean;
import br.com.veppo.estante.model.genero.Genero;

@Stateless
public class GeneroManagerBean extends AbstractModelManagerBean<Genero> implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected List<Genero> dynamicQuery() {
		log.debug("Consulta dinâmica de generos");

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Genero> dyQuery = builder.createQuery(Genero.class);
		dyQuery.distinct(true);

		Root<Genero> genero = dyQuery.from(Genero.class);
		genero.fetch("tags", JoinType.LEFT);

		List<Predicate> filters = getFilters(builder, genero);

		dyQuery.where(filters.toArray(new Predicate[filters.size()]));
		applyOrder(builder, dyQuery, genero);

		if (first < 0 || pageSize < 0) {
			return em.createQuery(dyQuery).getResultList();
		} else {
			return em.createQuery(dyQuery).setFirstResult(first).setMaxResults(pageSize).getResultList();
		}
	}

	@Override
	protected Long dynamicCount() {
		log.debug("Count dinâmico de generos");
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Long> dyQuery = builder.createQuery(Long.class);
		Root<Genero> genero = dyQuery.from(Genero.class);
		
		dyQuery.select(builder.countDistinct(genero));

		List<Predicate> filters = getFilters(builder, genero);
		dyQuery.where(filters.toArray(new Predicate[filters.size()]));

		return em.createQuery(dyQuery).getSingleResult();
	}
	
	private List<Predicate> getFilters(CriteriaBuilder builder, Root<Genero> genero) {
		List<Predicate> filters = new ArrayList<Predicate>();

		return filters;
	}

	
}