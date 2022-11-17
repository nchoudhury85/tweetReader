package com.tweet.tweetReader.data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class UserLabel {

	@Id
	private String userName;
	private String password;
	
	private String labels;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLabels() {
		return labels;
	}

	public void setLabels(String labels) {
		this.labels = labels;
	}
	
	
}
