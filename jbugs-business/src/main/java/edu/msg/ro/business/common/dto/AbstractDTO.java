package edu.msg.ro.business.common.dto;

/**
 * Abstract common class for DTO objects.
 * 
 * @author balinc
 *
 */
public abstract class AbstractDTO {
	private Long id;

	private Long lockVersion;

	/**
	 * Get for id.
	 * 
	 * @return
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Set for id.
	 * 
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Get for lockversion.
	 * 
	 * @return
	 */
	public Long getLockVersion() {
		return lockVersion;
	}

	/**
	 * Set for lockversion.
	 * 
	 * @param lockVersion
	 */
	public void setLockVersion(Long lockVersion) {
		this.lockVersion = lockVersion;
	}

}
