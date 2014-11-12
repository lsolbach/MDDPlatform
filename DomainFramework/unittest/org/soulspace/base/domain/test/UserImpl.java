package org.soulspace.base.domain.test;

import org.soulspace.annotation.domain.Entity;

@Entity
public class UserImpl implements User {

	String login;
	String password;
	String eMail;
	
	public String getEMail() {
		return eMail;
	}

	public String getLogin() {
		return login;
	}

	public String getPassword() {
		return password;
	}

	public void setEMail(String eMail) {
		this.eMail = eMail;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
