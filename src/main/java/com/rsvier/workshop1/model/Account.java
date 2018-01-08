package model;

public class Account {
	
	private long accountId;
	private boolean active;
	private String ownerType;
	
	public Account() {
	}
	
	public Account(long accoundId,
				   boolean active,
				   String accountOwner) {
	}
	
	public long getAccountId() {
		return accountId;
	}

	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getOwnerType() {
		return ownerType;
	}
	
	public void setOwnerType(String ownerType) {
		this.ownerType = ownerType;
	}

}
