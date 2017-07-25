package br.com.veppo.estante.controller.leitura;

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
import br.com.veppo.estante.model.leitura.Leitura;

@Stateless
public class LeituraManagerBean extends AbstractModelManagerBean<Leitura> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected List<Leitura> dynamicQuery() {
		log.debug("Consulta dinâmica de leituras");

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Leitura> dyQuery = builder.createQuery(Leitura.class);
		dyQuery.distinct(true);

		Root<Leitura> leitura = dyQuery.from(Leitura.class);
		leitura.fetch("tags", JoinType.LEFT);

		List<Predicate> filters = getFilters(builder, leitura);

		dyQuery.where(filters.toArray(new Predicate[filters.size()]));
		applyOrder(builder, dyQuery, leitura);

		if (first < 0 || pageSize < 0) {
			return em.createQuery(dyQuery).getResultList();
		} else {
			return em.createQuery(dyQuery).setFirstResult(first).setMaxResults(pageSize).getResultList();
		}
	}

	@Override
	protected Long dynamicCount() {
		log.debug("Count dinâmico de leituras");
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Long> dyQuery = builder.createQuery(Long.class);
		Root<Leitura> leitura = dyQuery.from(Leitura.class);

		dyQuery.select(builder.countDistinct(leitura));

		List<Predicate> filters = getFilters(builder, leitura);
		dyQuery.where(filters.toArray(new Predicate[filters.size()]));

		return em.createQuery(dyQuery).getSingleResult();
	}

	private List<Predicate> getFilters(CriteriaBuilder builder, Root<Leitura> leitura) {
		List<Predicate> filters = new ArrayList<Predicate>();

		return filters;
	}

}