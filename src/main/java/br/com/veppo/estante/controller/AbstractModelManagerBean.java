package br.com.veppo.estante.controller;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.joda.time.LocalDateTime;
import org.primefaces.model.SortOrder;

import br.com.veppo.estante.model.AbstractEntity;


public abstract class AbstractModelManagerBean<T extends AbstractEntity> extends AbstractManagerBean<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	private Map<String, String> filters;
	protected int first, pageSize;
	protected String sortField;
	protected SortOrder sortOrder;
	protected Map<String, Object> objectFilters;

	/***************************************************************/
	/** ABSTRACT METHODS **/
	/***************************************************************/

	protected abstract List<T> dynamicQuery();

	protected abstract Long dynamicCount();

	/***************************************************************/
	/** BUSINESS **/
	/***************************************************************/

	public List<T> dynamicQuery(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters, Map<String, Object> objectFilter) {
		initSearchAttributes(first, pageSize, sortField, sortOrder, filters, objectFilter);
		return dynamicQuery();
	}

	protected void initSearchAttributes(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters, Map<String, Object> objectFilter) {
		setPageSize(pageSize);
		setSortField(sortField);
		setSortOrder(sortOrder);
		setFilters(filters);
		setFirst(first);
		setObjectFilters(objectFilter);
	}

	public Long dynamicCount(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters, Map<String, Object> objectFilter) {
		initSearchAttributes(first, pageSize, sortField, sortOrder, filters, objectFilter);
		return dynamicCount();
	}

	/**
	 * Localiza um join dado sua alias
	 * */
	protected Join<?, ?> findRootJoin(Root<? extends AbstractEntity> root, String alias) {
		if (root != null && root.getJoins() != null) {
			Iterator<?> itJoin = root.getJoins().iterator();
			while (itJoin.hasNext()) {
				Join<?, ?> join = (Join<?, ?>) itJoin.next();
				if (join.getAlias().equals(alias)) {
					return join;
				}
			}
			throw new IllegalArgumentException("Não encontrou nenhum join para a alias " + alias + " com root " + root.getModel());
		}
		throw new IllegalArgumentException("Root é nulo ou não possui joins. root: " + root);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void applyOrder(final CriteriaBuilder builder, final CriteriaQuery<?> dyQuery, final Root<?> root) {
		Path<Object> property;
		if (sortField != null && sortOrder != null) {
			if (sortField.contains(".")) {
				String[] properties = sortField.split("\\.");
				Join join = root.join(properties[0], JoinType.LEFT);
				property = join.get(properties[1]);
			} else {
				property = root.get(sortField);
			}
			if (sortOrder == SortOrder.ASCENDING) {
				dyQuery.orderBy(builder.asc(property));
			} else if (sortOrder == SortOrder.DESCENDING) {
				dyQuery.orderBy(builder.desc(property));
			}
		}
	}

	/***************************************************************/
	/** FILTER METHODS **/
	/***************************************************************/

	public String getStringFilter(final String filter) {
		if (filters != null && filters.get(filter) != null && !filters.get(filter).isEmpty()) {
			return filters.get(filter);
		}
		return null;
	}

	public String getStringBooleanCheckBoxFilter(final String filter) {
		if (filters != null && filters.get(filter) != null) {
			Object elemento = filters.get(filter);
			return elemento.toString();
		}
		return null;
	}

	public Integer getIntegerFilter(final String filter) {
		if (filters != null && filters.get(filter) != null && !filters.get(filter).isEmpty()) {
			try {
				return Integer.valueOf(filters.get(filter));
			} catch (Exception e) {
			}
		}
		return null;
	}

	public Integer getDoubleFilterAsInteger(final String filter) {
		if (filters != null && filters.get(filter) != null) {
			try {
				Object object = filters.get(filter);
				Double value = new Double(object.toString());
				return value.intValue();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public Long getLongFilter(final String filter) {
		if (filters != null && filters.get(filter) != null && !filters.get(filter).isEmpty()) {
			try {
				return Long.valueOf(filters.get(filter));
			} catch (Exception e) {
			}
		}
		return null;
	}

	public Boolean getBooleanFilter(final String filter) {
		if (filters != null && filters.get(filter) != null && !filters.get(filter).isEmpty()) {
			try {
				return Boolean.valueOf(filters.get(filter));
			} catch (Exception e) {
			}
		}
		return null;
	}

	public BigDecimal getBigDecimalFilter(final String filter) {
		if (filters != null && filters.get(filter) != null && !filters.get(filter).isEmpty()) {
			try {
				return new BigDecimal(filters.get(filter).replace(",", "."));
			} catch (Exception e) {
			}
		}
		return null;
	}

	public Date getDateFilter(final String filter) {
		return getDateFilter(filter, "dd/MM/yyyy");
	}

	public LocalDateTime getLocalDateTimeFilter(final String filter) {
		Map<String, Object> stringFilter = getObjectFilters();
		LocalDateTime filtro = null;
		if (stringFilter != null) {
			if (stringFilter.containsKey(filter)) {
				filtro = (LocalDateTime) stringFilter.get(filter);
			}
		}
		return filtro;
	}

	public void setDatePeriodFilter(final CriteriaBuilder builder, final String initFilter, final String endFilter, final List<Predicate> filterList,
			final Expression<Date> attribute) {
		Date dtInicio = getDateFilter(initFilter);
		if (dtInicio != null) {
			filterList.add(builder.greaterThanOrEqualTo(attribute, dtInicio));
		}
		Date dtFim = getDateFilter(endFilter);
		if (dtFim != null) {
			Calendar dataFim = new GregorianCalendar();
			dataFim.setTime(dtFim);
			dataFim.set(Calendar.HOUR, 23);
			dataFim.set(Calendar.MINUTE, 59);
			filterList.add(builder.lessThanOrEqualTo(attribute, dataFim.getTime()));
		}
	}

	public void setValueRange(final CriteriaBuilder builder, final String initFilter, final String endFilter, final List<Predicate> filterList,
			final Expression<BigDecimal> attribute) {
		BigDecimal valorInicial = getBigDecimalFilter(initFilter);
		if (valorInicial != null) {
			filterList.add(builder.greaterThanOrEqualTo(attribute, valorInicial));
		}

		BigDecimal valorFinal = getBigDecimalFilter(endFilter);
		if (valorFinal != null) {
			filterList.add(builder.lessThanOrEqualTo(attribute, valorFinal));
		}
	}

	public Date getDateFilter(final String filter, final String datePattern) {
		if (filters != null && filters.get(filter) != null && !filters.get(filter).isEmpty()) {
			SimpleDateFormat f = new SimpleDateFormat(datePattern);
			try {
				return f.parse(filters.get(filter));
			} catch (Exception e) {
			}
		}
		return null;
	}

	public LocalDateTime getDateTimeFilter(final String filter) {
		return getDateTimeFilter(filter, "dd/MM/yyyy");
	}

	public LocalDateTime getDateTimeFilter(final String filter, final String datePattern) {
		if (filters != null) {
			if (filters.get(filter) != null) {
				LocalDateTime date = new LocalDateTime(filters.get(filter));

				return date;
			}
		}

		return null;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Enum getEnumFilter(final String filter, final Class enumType) {
		if (filters != null) {
			Object value = filters.get(filter);
			if (value instanceof Enum) {
				return (Enum) value;
			}
			if (value != null && !((String) value).isEmpty()) {
				try {
					return Enum.valueOf(enumType, ((String) value));
				} catch (Exception e) {
				}
			}
		}
		return null;
	}

	/***************************************************************/
	/** ACCESSORS **/
	/***************************************************************/

	public Map<String, String> getFilters() {
		return filters;
	}

	public void setFilters(final Map<String, String> filters) {
		this.filters = filters;
	}

	public int getFirst() {
		return first;
	}

	public void setFirst(final int first) {
		this.first = first;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(final int pageSize) {
		this.pageSize = pageSize;
	}

	public String getSortField() {
		return sortField;
	}

	public void setSortField(final String sortField) {
		this.sortField = sortField;
	}

	public SortOrder getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(final SortOrder sortOrder) {
		this.sortOrder = sortOrder;
	}

	public Map<String, Object> getObjectFilters() {
		return objectFilters;
	}

	public void setObjectFilters(Map<String, Object> objectFilters) {
		this.objectFilters = objectFilters;
	}
}