package edu.ycp.cs320.cteichmann.persist;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import edu.ycp.cs320.cteichmann.model.Model;
import edu.ycp.cs320.cteichmann.model.Overwatch;
import edu.ycp.cs320.cteichmann.model.Schedule;

public class InitialData {

	// reads initial account data from CSV file and returns a List of Account
	public static List<Model> getAccount() throws IOException {
		List<Model> accountList = new ArrayList<Model>();
		ReadCSV readAccount = new ReadCSV("Account.csv");
		try {
			// auto-generated primary key for books table
			Integer accountID = 1;
			while (true) {
				List<String> tuple = readAccount.next();
				if (tuple == null) {
					break;
				}
				Iterator<String> i = tuple.iterator();
				Model model = new Model();
				model.setAccountID(accountID++);
				model.setUserName(i.next());
				model.setPassword(i.next());
				model.setFavoriteID(Integer.parseInt(i.next()));
				accountList.add(model);
			}
			return accountList;
		} finally {
			readAccount.close();
		}
	}
}