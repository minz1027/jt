package edu.msg.ro.business.history.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import edu.msg.ro.business.common.dao.AbstractDao;
import edu.msg.ro.persistence.bug.entity.Bug;
import edu.msg.ro.persistence.history.entity.History;

/**
 * DAO for {@link History} entity.
 *
 * @author balinc
 *
 */
@Stateless
public class HistoryDAO extends AbstractDao<History> {

	@Override
	public Class<History> getEntityClass() {
		return History.class;
	}

	/**
	 * Method for getting back all {@link History}s.
	 * 
	 * @param bug
	 * @return
	 */
	public List<History> getAll(Bug bug) {
		TypedQuery<History> query = this.em.createNamedQuery(History.FIND_ALL, History.class);
		query.setParameter("bug", bug);
		return query.getResultList();
	}

}
