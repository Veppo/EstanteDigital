package br.com.veppo.estante.controller.autor;

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
import br.com.veppo.estante.model.autor.Autor;

@Stateless
public class AutorManagerBean extends AbstractModelManagerBean<Autor> implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected List<Autor> dynamicQuery() {
		log.debug("Consulta dinâmica de autores");

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Autor> dyQuery = builder.createQuery(Autor.class);
		dyQuery.distinct(true);

		Root<Autor> autor = dyQuery.from(Autor.class);
		autor.fetch("tags", JoinType.LEFT);

		List<Predicate> filters = getFilters(builder, autor);

		dyQuery.where(filters.toArray(new Predicate[filters.size()]));
		applyOrder(builder, dyQuery, autor);

		if (first < 0 || pageSize < 0) {
			return em.createQuery(dyQuery).getResultList();
		} else {
			return em.createQuery(dyQuery).setFirstResult(first).setMaxResults(pageSize).getResultList();
		}
	}

	@Override
	protected Long dynamicCount() {
		log.debug("Count dinâmico de autores");
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Long> dyQuery = builder.createQuery(Long.class);
		Root<Autor> autor = dyQuery.from(Autor.class);
		
		dyQuery.select(builder.countDistinct(autor));

		List<Predicate> filters = getFilters(builder, autor);
		dyQuery.where(filters.toArray(new Predicate[filters.size()]));

		return em.createQuery(dyQuery).getSingleResult();
	}
	
	private List<Predicate> getFilters(CriteriaBuilder builder, Root<Autor> autor) {
		List<Predicate> filters = new ArrayList<Predicate>();

		return filters;
	}

	
}