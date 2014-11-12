package org.soulspace.base.domain.test;

import org.soulspace.annotation.domain.Entity;

@Entity
public interface User {
	String getLogin();
	void setLogin(String login);
	
	String getPassword();
	void setPassword(String password);
	
	String getEMail();
	void setEMail(String eMail);
}
