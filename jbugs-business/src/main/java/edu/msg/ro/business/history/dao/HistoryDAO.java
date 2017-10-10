package edu.msg.ro.business.history.dao;

import javax.ejb.Stateless;

import edu.msg.ro.business.common.dao.AbstractDao;
import edu.msg.ro.persistence.bug.entity.Bug;
import edu.msg.ro.persistence.history.entity.History;

/**
 * DAO for {@link Bug} entity.
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

}
