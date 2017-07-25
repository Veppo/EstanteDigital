package br.com.veppo.estante.controller.editora;

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
import br.com.veppo.estante.model.editora.Editora;

@Stateless
public class EditoraManagerBean extends AbstractModelManagerBean<Editora> implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected List<Editora> dynamicQuery() {
		log.debug("Consulta dinâmica de editoras");

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Editora> dyQuery = builder.createQuery(Editora.class);
		dyQuery.distinct(true);

		Root<Editora> editora = dyQuery.from(Editora.class);
		editora.fetch("tags", JoinType.LEFT);

		List<Predicate> filters = getFilters(builder, editora);

		dyQuery.where(filters.toArray(new Predicate[filters.size()]));
		applyOrder(builder, dyQuery, editora);

		if (first < 0 || pageSize < 0) {
			return em.createQuery(dyQuery).getResultList();
		} else {
			return em.createQuery(dyQuery).setFirstResult(first).setMaxResults(pageSize).getResultList();
		}
	}

	@Override
	protected Long dynamicCount() {
		log.debug("Count dinâmico de editoras");
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Long> dyQuery = builder.createQuery(Long.class);
		Root<Editora> editora = dyQuery.from(Editora.class);
		
		dyQuery.select(builder.countDistinct(editora));

		List<Predicate> filters = getFilters(builder, editora);
		dyQuery.where(filters.toArray(new Predicate[filters.size()]));

		return em.createQuery(dyQuery).getSingleResult();
	}
	
	private List<Predicate> getFilters(CriteriaBuilder builder, Root<Editora> editora) {
		List<Predicate> filters = new ArrayList<Predicate>();

		return filters;
	}

	
}