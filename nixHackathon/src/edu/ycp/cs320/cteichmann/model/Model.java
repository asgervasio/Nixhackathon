package edu.ycp.cs320.cteichmann.model;

import edu.ycp.cs320.cteichmann.model.Model;

public class Model {
	private int accountID;
	private String userName;
	private String password;
	private int favoriteID;

	public Model(){

	}
	// GET and SET accountID
	public int getAccountID() {
		return this.accountID;
	}
	public void setAccountID(int accountID) {
		this.accountID = accountID;
	}
	// GET and SET userName
	public String getUserName() {
		return this.userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	// GET and SET Password
	public String getPassword() {
		return this.password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	//GET and SET Favorite favorite
	public int getFavoriteID() {
		return this.favoriteID;
	}
	public void setFavoriteID(int favoriteID) {
		this.favoriteID = favoriteID;
	}
}
