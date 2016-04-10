package ru.my.project.db;

public class Account {
	private Long id; 
	private String login;
	private String pass;
	
	public Account() {
		super();
	}

	public Account(String login, String pass) {
		super();
		this.login = login;
		this.pass = pass;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}

}