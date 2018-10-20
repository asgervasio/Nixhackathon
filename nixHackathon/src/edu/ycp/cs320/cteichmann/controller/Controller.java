package edu.ycp.cs320.cteichmann.controller;

import java.util.ArrayList;
import java.util.List;



import edu.ycp.cs320.cteichmann.model.Model;

public class Controller {
	List<Model> accountList;
	
	public Controller() {
		accountList = new ArrayList<Model>();
	}
	// adds account to the list
	public void addAccount(Model model) {
		accountList.add(model);
	}
	// returns the account with given username
	public Model getAccount(String username) {
		for (Model model: accountList) {
			if (model.getUserName().equals(username)) {
				System.out.println("getAcountName() = username");
				return model;
			}
		}
		return null;
	}
	public boolean doesAccountExist(String username) {
		for (Model model: accountList) {
			if (model.getUserName().equals(username)) {
				return true;
			}
		}
		return false;
	}
}