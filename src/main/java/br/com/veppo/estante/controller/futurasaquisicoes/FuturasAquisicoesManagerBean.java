package br.com.veppo.estante.controller.futurasaquisicoes;

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
import br.com.veppo.estante.model.futurasaquisicoes.FuturasAquisicoes;

@Stateless
public class FuturasAquisicoesManagerBean extends AbstractModelManagerBean<FuturasAquisicoes> implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected List<FuturasAquisicoes> dynamicQuery() {
		log.debug("Consulta dinâmica de futuras aquisições");

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<FuturasAquisicoes> dyQuery = builder.createQuery(FuturasAquisicoes.class);
		dyQuery.distinct(true);

		Root<FuturasAquisicoes> aquisicoes = dyQuery.from(FuturasAquisicoes.class);
		aquisicoes.fetch("tags", JoinType.LEFT);

		List<Predicate> filters = getFilters(builder, aquisicoes);

		dyQuery.where(filters.toArray(new Predicate[filters.size()]));
		applyOrder(builder, dyQuery, aquisicoes);

		if (first < 0 || pageSize < 0) {
			return em.createQuery(dyQuery).getResultList();
		} else {
			return em.createQuery(dyQuery).setFirstResult(first).setMaxResults(pageSize).getResultList();
		}
	}

	@Override
	protected Long dynamicCount() {
		log.debug("Count dinâmico de futuras aquisições");
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Long> dyQuery = builder.createQuery(Long.class);
		Root<FuturasAquisicoes> aquisicoes = dyQuery.from(FuturasAquisicoes.class);
		
		dyQuery.select(builder.countDistinct(aquisicoes));

		List<Predicate> filters = getFilters(builder, aquisicoes);
		dyQuery.where(filters.toArray(new Predicate[filters.size()]));

		return em.createQuery(dyQuery).getSingleResult();
	}
	
	private List<Predicate> getFilters(CriteriaBuilder builder, Root<FuturasAquisicoes> aquisicoes) {
		List<Predicate> filters = new ArrayList<Predicate>();

		return filters;
	}

	
}