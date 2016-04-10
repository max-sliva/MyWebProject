package ru.my.project.db;

public class UserGroup {
	public UserGroup() {
		super();
	}
	private long id;
	private String groupName;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	
}
