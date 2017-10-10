package edu.msg.ro.business.notification.dto.mapper;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import edu.msg.ro.business.common.dto.mapper.AbstractDTOMapper;
import edu.msg.ro.business.notification.dto.NotificationDTO;
import edu.msg.ro.business.notification.util.NotificationType;
import edu.msg.ro.business.user.dao.UserDAO;
import edu.msg.ro.business.user.dto.mapper.UserDTOMapper;
import edu.msg.ro.persistence.notification.entity.Notification;

/**
 * Mapper for {@link Notification} and {@link NotificationDTO}
 * 
 * @author balinc
 *
 */
@Stateless
public class NotificationDTOMapper extends AbstractDTOMapper<Notification, NotificationDTO> {

	@EJB
	UserDTOMapper udm;

	@EJB
	private UserDAO userDAO;

	@Override
	public NotificationDTO getDTOInstance() {
		return new NotificationDTO();
	}

	@Override
	protected void mapEntityToDTOFields(Notification entity, NotificationDTO dto) {
		dto.setVariables(entity.getVariables());
		dto.setTarget(udm.mapToDTO(entity.getTarget()));
		dto.setType(NotificationType.values()[entity.getType()]);
		dto.setActive(entity.isActive());
		dto.setCreated(entity.getCreated());
	}

	@Override
	protected void mapDTOToEntityFields(NotificationDTO dto, Notification entity) {
		entity.setVariables(dto.getVariables());
		entity.setTarget(userDAO.findEntity(dto.getTarget().getId()));
		entity.setType(dto.getType().key);
		entity.setActive(dto.isActive());
	}
}