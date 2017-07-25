package br.com.veppo.estante.controller.livro;

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
import br.com.veppo.estante.model.livro.Livro;

@Stateless
public class LivroManagerBean extends AbstractModelManagerBean<Livro> implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected List<Livro> dynamicQuery() {
		log.debug("Consulta dinâmica de livros");

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Livro> dyQuery = builder.createQuery(Livro.class);
		dyQuery.distinct(true);

		Root<Livro> livro = dyQuery.from(Livro.class);
		livro.fetch("tags", JoinType.LEFT);

		List<Predicate> filters = getFilters(builder, livro);

		dyQuery.where(filters.toArray(new Predicate[filters.size()]));
		applyOrder(builder, dyQuery, livro);

		if (first < 0 || pageSize < 0) {
			return em.createQuery(dyQuery).getResultList();
		} else {
			return em.createQuery(dyQuery).setFirstResult(first).setMaxResults(pageSize).getResultList();
		}
	}

	@Override
	protected Long dynamicCount() {
		log.debug("Count dinâmico de livros");
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Long> dyQuery = builder.createQuery(Long.class);
		Root<Livro> livro = dyQuery.from(Livro.class);
		
		dyQuery.select(builder.countDistinct(livro));

		List<Predicate> filters = getFilters(builder, livro);
		dyQuery.where(filters.toArray(new Predicate[filters.size()]));

		return em.createQuery(dyQuery).getSingleResult();
	}
	
	private List<Predicate> getFilters(CriteriaBuilder builder, Root<Livro> livro) {
		List<Predicate> filters = new ArrayList<Predicate>();

		return filters;
	}

	
}