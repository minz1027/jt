package edu.msg.ro.business.history.enums;

public enum BugFields {
	TITLE(((byte) 1)), DESCRIPTION(((byte) 2)), TARGETDATE(((byte) 3)), SEVERITY(((byte) 4)), AUTHOR(
			((byte) 5)), STATUS(((byte) 6)), ASSIGNED(((byte) 7)), VERSION(
					((byte) 8)), FIXEDIN(((byte) 9)), ATTACHMENT(((byte) 10)), ATTACHMENTNAME(((byte) 11));

	private Byte id;

	BugFields(Byte b) {
		this.id = b;
	}

	public Byte getId() {
		return id;
	}
}
