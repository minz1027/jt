package edu.msg.ro.persistence.common.entity;

import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

/**
 * Abstarct class for entities.
 * 
 * @author laszll
 *
 */
@MappedSuperclass
public abstract class AbstractEntity {

	@Version
	private Long lockVersion;

	/**
	 * Get for lockVersion.
	 * 
	 * @return
	 */
	public Long getLockVersion() {
		return lockVersion;
	}

	/**
	 * Set for lockVersion.
	 * 
	 * @param lockVersion
	 */
	public void setLockVersion(Long lockVersion) {
		this.lockVersion = lockVersion;
	}

	/**
	 * Get for Id.
	 * 
	 * @return
	 */
	public abstract Long getId();

}
