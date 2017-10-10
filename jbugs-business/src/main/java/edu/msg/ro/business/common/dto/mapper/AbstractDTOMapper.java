package edu.msg.ro.business.common.dto.mapper;

import java.util.ArrayList;
import java.util.List;

import edu.msg.ro.business.common.dao.AbstractDao;
import edu.msg.ro.business.common.dto.AbstractDTO;
import edu.msg.ro.persistence.common.entity.AbstractEntity;

/**
 * Abstract class for mapping DTOs and Entityes.
 * 
 * @author laszll
 *
 * @param <E>
 * @param <DTO>
 */
public abstract class AbstractDTOMapper<E extends AbstractEntity, DTO extends AbstractDTO> {

	/**
	 * Method for getting instance.
	 * 
	 * @return
	 */
	public abstract DTO getDTOInstance();

	/**
	 * Method for mapping an Entity to DTO.
	 * 
	 * @param entity
	 * @return
	 */
	public DTO mapToDTO(E entity) {
		if (entity == null) {
			return null;
		} else {
			DTO dto = getDTOInstance();

			dto.setId(entity.getId());
			dto.setLockVersion(entity.getLockVersion());

			mapEntityToDTOFields(entity, dto);

			return dto;
		}

	}

	/**
	 * Method for mapping DTO to entity.
	 * 
	 * @param dto
	 * @param entity
	 */
	public void mapToEntity(DTO dto, E entity) {
		entity.setLockVersion(dto.getLockVersion());
		mapDTOToEntityFields(dto, entity);
	}

	/**
	 * Method for mapping multiple entities to DTOs.
	 * 
	 * @param entities
	 * @return
	 */
	public List<DTO> mapToDTOs(List<E> entities) {

		List<DTO> dtos = new ArrayList<>();
		for (E entity : entities) {
			dtos.add(mapToDTO(entity));
		}

		return dtos;
	}

	/**
	 * Method for mapping multiple DTOs to Entities.
	 * 
	 * @param listDTO
	 * @param dao
	 * @return
	 */
	public List<E> mapToEntities(List<DTO> listDTO, AbstractDao<E> dao) {
		List<E> listEntity = new ArrayList<>();
		for (DTO dto : listDTO) {
			E persistedE = dao.findEntity(dto.getId());
			mapToEntity(dto, persistedE);
			listEntity.add(persistedE);
		}
		return listEntity;
	}

	/**
	 * Method for resolving the mapping of fields.
	 * 
	 * @param entity
	 * @param dto
	 */
	protected abstract void mapEntityToDTOFields(E entity, DTO dto);

	/**
	 * Method for resolving the mapping of fields.
	 * 
	 * @param dto
	 * @param entity
	 */
	protected abstract void mapDTOToEntityFields(DTO dto, E entity);

}
