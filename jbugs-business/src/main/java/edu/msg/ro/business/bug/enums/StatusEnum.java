package edu.msg.ro.business.bug.enums;

import java.util.ArrayList;

/**
 * Enum for bug status
 * 
 * @author fulops
 *
 */
public enum StatusEnum {

	/**
	 * The bug possible statuses.
	 */
	OPEN, REJECTED, INPROGRESS, INFONEEDED, FIXED, CLOSE;

	public int key;

	public ArrayList<StatusEnum> neighbors;

	/**
	 * Every state every state has next state.
	 */
	static {
		OPEN.neighbors = new ArrayList<>();
		OPEN.neighbors.add(REJECTED);
		OPEN.neighbors.add(INPROGRESS);

		REJECTED.neighbors = new ArrayList<>();
		REJECTED.neighbors.add(CLOSE);

		INPROGRESS.neighbors = new ArrayList<>();
		INPROGRESS.neighbors.add(REJECTED);
		INPROGRESS.neighbors.add(INFONEEDED);
		INPROGRESS.neighbors.add(FIXED);

		INFONEEDED.neighbors = new ArrayList<>();
		INFONEEDED.neighbors.add(INPROGRESS);

		FIXED.neighbors = new ArrayList<>();
		FIXED.neighbors.add(OPEN);
		FIXED.neighbors.add(CLOSE);

		CLOSE.neighbors = new ArrayList<>();

	}

	/**
	 * Constructor.
	 * 
	 * @param
	 */
	private StatusEnum() {
		this.key = ordinal();
	}
}
