package edu.ycp.cs320.cteichmann.persist;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.ycp.cs320.cteichmann.model.Model;
import edu.ycp.cs320.cteichmann.model.LoginModel;
import edu.ycp.cs320.cteichmann.model.Overwatch;
import edu.ycp.cs320.cteichmann.model.Schedule;

public class DerbyDatabase{
	static {
		try{
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
		} catch (Exception e) {
			throw new IllegalStateException("Could not load Derby driver");
		}
	}

	private interface Transaction<ResultType> {
		public ResultType execute(Connection conn) throws SQLException;
	}

	private static final int MAX_ATTEMPTS = 10;
	public Overwatch findTeamInfoByTeamID(final int teamID) throws SQLException{
		return doExecuteTransaction(new Transaction<Overwatch>() {
			public Overwatch execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				Overwatch result = new Overwatch();
				try{ conn.setAutoCommit(true);
				stmt = conn.prepareStatement(
						"select teamInfo.*" +
								" from teamInfo " +
								"where teamInfo.teamID = ?"				
						);
				stmt.setInt(1, teamID);
				

				resultSet = stmt.executeQuery();
				// for testing that a result was returned
				Boolean found = false;

				while (resultSet.next()) {
					found = true;

					Overwatch overwatch = new Overwatch();
					loadOverwatch(overwatch, resultSet, 1);


					result = overwatch;
				}

				// check if the title was found
				if (!found) {
					System.out.println("<" + teamID + "> (Team ID) was not found in the TeamInfo table");
				}

				
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
				return result;
			}
		});
	}
	public List<Overwatch>findTeamInfoByTeamName(final String teamName) throws SQLException{
		return doExecuteTransaction(new Transaction<List<Overwatch>>() {
			public List<Overwatch> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;

				try{ conn.setAutoCommit(true);
				stmt = conn.prepareStatement(
						"select teamInfo.*"+
								" from teamInfo " +
								"where teamInfo.teamName = ?"						
						);
				stmt.setString(1, teamName);
				List<Overwatch> result = new ArrayList<Overwatch>();

				resultSet = stmt.executeQuery();
				// for testing that a result was returned
				Boolean found = false;

				while (resultSet.next()) {
					found = true;
					Overwatch overwatch = new Overwatch();
					loadOverwatch(overwatch, resultSet, 1);
					result.add(overwatch);
				}

				// check if the title was found
				if (!found) {
					System.out.println("<" + teamName + "> (Team Name) was not found in the TeamInfo table");
				}

				return result;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}
	public List<Overwatch>findTeamInfoByTeamStanding(final int teamStanding) throws SQLException{
		return doExecuteTransaction(new Transaction<List<Overwatch>>() {
			public List<Overwatch> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;

				try{ conn.setAutoCommit(true);
				stmt = conn.prepareStatement(
						"select teamInfo.*"+
								" from teamInfo " +
								"where teamInfo.teamStanding = ?"						
						);
				stmt.setInt(1, teamStanding);
				List<Overwatch> result = new ArrayList<Overwatch>();

				resultSet = stmt.executeQuery();
				// for testing that a result was returned
				Boolean found = false;

				while (resultSet.next()) {
					found = true;
					Overwatch overwatch = new Overwatch();
					loadOverwatch(overwatch, resultSet, 1);
					result.add(overwatch);
				}

				// check if the title was found
				if (!found) {
					System.out.println("<" + teamStanding + "> (Team Name) was not found in the TeamInfo table");
				}

				return result;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}
	public Model findAccountByUserName(final String userName) throws SQLException{
		return doExecuteTransaction(new Transaction<Model>() {
			public Model execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				Model finalResult = new Model();
				Boolean found = false;
				try{ conn.setAutoCommit(true);
				stmt = conn.prepareStatement(
						"select Account.*"+
								" from Account " +
								"where userName = ?"
						);
				stmt.setString(1, userName);
				resultSet = stmt.executeQuery();
				// for testing that a result was returned
				while (resultSet.next()) {
					found = true;
					loadAccount(finalResult, resultSet, 1);
					
				}
				if (!found) {
					System.out.println("<" + userName +"> is not valid");
				}

				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
				return finalResult;
			}
		});
	}
	public Boolean ValidateAccountByUserNameAndPassword(final String userName, final String password) throws SQLException{
		return executeTransaction(new Transaction<Boolean>() {
			public Boolean execute(Connection conn) throws SQLException {
				Boolean found = false;
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				try {
					conn.setAutoCommit(true);

					// a canned query to find book information (including author name) from title
					stmt = conn.prepareStatement(
							"select account.accountID " +
									"from Account " +
									"	   where userName = ? " +
									"	   AND password = ? "	
							);

					// substitute the last name and first name of the existing author entered by the user for the placeholder in the query
					stmt.setString(1, userName);
					stmt.setString(2, password);

					// execute the query
					resultSet = stmt.executeQuery();

					// for testing that a result was returned


					while (resultSet.next()) {
						found = true;
					}

					// check if the item was found
					if (!found) {
						System.out.println("Either <" + userName + "or" + password +"> is not valid");
					}

				} finally {
					// close result set, statement, connection
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
				return found;
			}});
	}
	public int findAccountIDbyUsernameAndPassword(final String userName, final String password) throws SQLException {
		return executeTransaction(new Transaction<Integer>() {
			public Integer execute(Connection conn) throws SQLException {
				int finalResult = -1;
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				try {
					conn.setAutoCommit(true);
					stmt = conn.prepareStatement(
							"select accountID " +
									"from Account " +
									"	   where userName = ? " +
									"	   AND password = ? "	
							);
					stmt.setString(1, userName);
					stmt.setString(2, password);
					// execute the query
					resultSet = stmt.executeQuery();
					// for testing that a result was returned
					Boolean found = false;
					while (resultSet.next()) {
						found = true;
						// create new item object
						// retrieve attributes from resultSet starting at index
						//Account account = new Account();
						//loadAccount(account, resultSet, 1);
						//System.out.println(account.getAccountID());
						//finalResult = account.getAccountID();
						finalResult=Integer.parseInt(resultSet.getObject(1).toString());
					}

					// check if the item was found
					if (!found) {
						System.out.println("Either <" + userName + "or" + password +"> is not valid");
					}

				} finally {
					// close result set, statement, connection
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
				return finalResult;
			}});
	}
	public Model findAccountByAccountID(final int accountID) throws SQLException{
		return doExecuteTransaction(new Transaction<Model>() {
			public Model execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				try{ conn.setAutoCommit(true);
				stmt = conn.prepareStatement(
						"select Account.*"+
								" from Account " +
								"where Account.AccountID = ?"							
						);
				stmt.setInt(1, accountID);
				Model result = new Model();

				resultSet = stmt.executeQuery();
				// for testing that a result was returned
				Boolean found = false;

				while (resultSet.next()) {
					found = true;
					loadAccount(result, resultSet, 1);
				}

				// check if the title was found
				if (!found) {
					System.out.println("<" + accountID + "> (FavoriteID) was not found in the Account table");
				}

				return result;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}
	public Boolean verifyAccount(final String userName, final String password) throws SQLException{
		return doExecuteTransaction(new Transaction<Boolean>() {
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				Boolean found = false;
				System.out.println(""+userName+" "+password);
				try{ conn.setAutoCommit(true);
				System.out.println("A");
				stmt = conn.prepareStatement(
						"select Account.*"+
								" from Account " +
								"where Account.userName = ?" +	
								" AND Account.password = ?"
						);
				System.out.println("B");
				stmt.setString(1, userName);
				stmt.setString(2, password);
				resultSet = stmt.executeQuery();
				// for testing that a result was returned
				while (resultSet.next()) {
					found = true;
				}
				if(!found) {
					System.out.println("No result set for account");
				}
				return found;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}
	public Boolean AddAccountIntoAccountTable(final String userName, final String password, final int favoriteID) throws SQLException {
		return executeTransaction(new Transaction<Boolean>() {
			public Boolean execute(Connection conn) throws SQLException {
				Boolean finalResult = false;
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				System.out.println("1");
				try {
					System.out.println("2");
					conn.setAutoCommit(true);
					stmt = conn.prepareStatement(
							"insert into Account(userName, password, favoriteID) "
									+ "  values (?, ?, ?) "
							);
					stmt.setString(1, userName);
					stmt.setString(2, password);
					stmt.setInt(3, favoriteID);
					// execute the query
					stmt.executeUpdate();
					System.out.println("3");
					if (verifyAccount(userName, password) == true) {
						System.out.println("4");
						finalResult = true;
					}
					System.out.println("5");
				} finally {
					// close result set, statement, connection
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
				return finalResult;
			}});
	}
	public List<Schedule>findMatchByWeek(final int scheduleWeek) throws SQLException{
		return doExecuteTransaction(new Transaction<List<Schedule>>() {
			public List<Schedule> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				try{ conn.setAutoCommit(true);
				stmt = conn.prepareStatement(
						"select schedule.*"+
								" from schedule " +
								"where schedule.scheduleWeek = ?"				
						);
				stmt.setInt(1, scheduleWeek);
				List<Schedule> result = new ArrayList<Schedule>();

				resultSet = stmt.executeQuery();
				// for testing that a result was returned
				Boolean found = false;

				while (resultSet.next()) {
					found = true;
					Schedule schedule = new Schedule();
					loadSchedule(schedule, resultSet, 1);
					result.add(schedule);
				}
				// check if the schedule was found
				if (!found) {
					System.out.println("<" + scheduleWeek + "> (ScheduleWeek) was not found in the Schedule table");
				}
				return result;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}
	public String findTeamColorByFavoriteID(final int favoriteID) throws SQLException{
		return doExecuteTransaction(new Transaction<String>(){
			public String execute(Connection conn) throws SQLException {
				PreparedStatement StatementGetTeamID = null;
				ResultSet resultSet = null;
				String result = null;
				Overwatch overwatch = new Overwatch();
				try{ conn.setAutoCommit(true);
				StatementGetTeamID = conn.prepareStatement(
						"select teamInfo.*" +
								"from teamInfo " +
								"where teamInfo.teamID = ?"	
						);
				StatementGetTeamID.setInt(1, favoriteID);
				resultSet = StatementGetTeamID.executeQuery();
				// for testing that a result was returned
				Boolean found = false;
				while (resultSet.next()){
					found = true;						
					loadOverwatch(overwatch, resultSet, 1);
					result = overwatch.getTeamColor();
				}
				// check if the title was found
				if (!found) {
					System.out.println("<" + favoriteID + "> (favoriteID) was not found in the TeamInfo table");
				}
				return result;
				} 
				finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(StatementGetTeamID);
				}
			}
		});
	}
	// wrapper SQL transaction function that calls actual transaction function (which has retries)
	public<ResultType> ResultType executeTransaction(Transaction<ResultType> txn) {
		try{
			return doExecuteTransaction(txn);
		} catch (SQLException e) {
			throw new PersistenceException("Transaction failed", e);
		}
	}
	// SQL transaction function which retries the transaction MAX_ATTEMPTS times before failing
	public<ResultType> ResultType doExecuteTransaction(Transaction<ResultType> txn) throws SQLException {
		Connection conn = connect();

		try{ conn.setAutoCommit(true);
		int numAttempts = 0;
		boolean success = false;
		ResultType result = null;

		while (!success && numAttempts < MAX_ATTEMPTS) {
			try{ conn.setAutoCommit(true);
			result = txn.execute(conn);
			conn.commit();
			success = true;
			} catch (SQLException e) {
				if (e.getSQLState() != null && e.getSQLState().equals("41000")) {
					// Deadlock: retry (unless max retry count has been reached)
					numAttempts++;
				} else {
					// Some other kind of SQLException
					throw e;
				}
			}
		}

		if (!success) {
			throw new SQLException("Transaction failed (too many retries)");
		}

		// Success!
		return result;
		} finally {
			DBUtil.closeQuietly(conn);
		}
	}
	//SQL statement to clear table data on runs
	public void dropTables() throws SQLException {
		doExecuteTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement dropAccount = null;
				PreparedStatement dropTeamInfo = null;
				PreparedStatement dropSchedule= null;
				try{ conn.setAutoCommit(true); 
				dropAccount = conn.prepareStatement 
						( "drop table Account" ); 
				dropAccount.execute(); 
				dropAccount.close(); 

				dropTeamInfo = conn.prepareStatement 
						( "drop table TeamInfo" ); 
				dropTeamInfo.execute(); 
				dropTeamInfo.close();

				dropSchedule = conn.prepareStatement 
						( "drop table Schedule" ); 
				dropSchedule.execute(); 
				dropSchedule.close();

				return true;
				} catch (Exception ex) {
					return true;
				} finally {
					DBUtil.closeQuietly(dropAccount);
				}				

			};
		});
	}
	private Connection connect() throws SQLException {
		Connection conn = DriverManager.getConnection("jdbc:derby:C:/CS320-2018/library.db;create=true");		

		// Set autocommit() to false to allow the execution of
		// multiple queries/statements as part of the same transaction.
		conn.setAutoCommit(false);

		return conn;
	}
	// retrieves Account information from query result set
	private void loadAccount(Model model, ResultSet resultSet, int index) throws SQLException {
		model.setAccountID(resultSet.getInt(index++));
		model.setUserName(resultSet.getString(index++));
		model.setPassword(resultSet.getString(index++));
		model.setFavoriteID(resultSet.getInt(index++));
	}
	// retrieves TeamInfo information from query result set
	private void loadOverwatch(Overwatch overwatch, ResultSet resultSet, int index) throws SQLException {
		overwatch.setTeamID(resultSet.getInt(index++));
		overwatch.setTeamName(resultSet.getString(index++));
		overwatch.setTeamColor(resultSet.getString(index++));
		overwatch.setTeamWin(resultSet.getInt(index++));
		overwatch.setTeamLoss(resultSet.getInt(index++));
		overwatch.setTeamMP(resultSet.getInt(index++));
		overwatch.setTeamStanding(resultSet.getInt(index++));
	}
	// retrieves ScheduleInfo information from query result set
	private void loadSchedule(Schedule schedule, ResultSet resultSet, int index) throws SQLException {
		schedule.setScheduleWeek(resultSet.getInt(index++));
		schedule.setScheduleWeekday(resultSet.getString(index++));
		schedule.setScheduleDate(resultSet.getString(index++));
		schedule.setScheduleTime(resultSet.getString(index++));
		schedule.setScheduleTeam1ID(resultSet.getInt(index++));
		schedule.setScheduleTeam1Score(resultSet.getInt(index++));
		schedule.setScheduleTeam2ID(resultSet.getInt(index++));
		schedule.setScheduleTeam2Score(resultSet.getInt(index++));
	}
	//  creates the Authors and Books tables
	public void createTables() {
		executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement accountCreate = null;
				PreparedStatement teamCreate = null;	
				PreparedStatement scheduleCreate = null;
				try{ conn.setAutoCommit(true);
				//Create Account
				accountCreate = conn.prepareStatement(
						"create table Account(" +	
								"	accountID integer primary key " +
								"		generated always as identity (start with 1, increment by 1), " +
								"	userName varchar(40)," +
								"	password varchar(40)," +
								"	favoriteID integer" +
								")"
						);	
				accountCreate.executeUpdate();
				System.out.println("Account Information table created");

				//Create Team Info
				teamCreate = conn.prepareStatement(
						"create table TeamInfo (" +
								"	teamID integer," +
								"	teamName varchar(40)," +
								"	teamColor varchar(10)," +
								"	teamWin integer," +
								"	teamLoss integer," +
								"	teamMP integer," +
								"   teamStanding integer" +
								")"
						);
				teamCreate.executeUpdate();
				System.out.println("Team Information table created");


				//Create Team Info
				scheduleCreate = conn.prepareStatement(
						"create table Schedule (" +
								"	scheduleWeek integer," +
								"	scheduleWeekday varchar(10)," +
								"	scheduleDate varchar(10)," +
								"	scheduleTime varchar(10)," +
								"	scheduleTeam1ID integer," +
								"	scheduleTeam1Score integer," +
								"	scheduleTeam2ID integer," +
								"   scheduleTeam2Score integer" +
								")"
						);
				scheduleCreate.executeUpdate();
				System.out.println("Schedule Information table created");
				return true;	
				}

				finally {
					DBUtil.closeQuietly(accountCreate);
					DBUtil.closeQuietly(teamCreate);
					DBUtil.closeQuietly(scheduleCreate);
				}
			}
		});
	}
	// loads data retrieved from CSV files into DB tables in batch mode
	public void loadInitialData() {
		executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				List<Model> accountList;
				List<Overwatch> overwatchList;
				List<Schedule> scheduleList;

				try{ conn.setAutoCommit(true);
				accountList = InitialData.getAccount();
				overwatchList = InitialData.getOverwatch();
				scheduleList = InitialData.getSchedule();

				} catch (IOException e) {
					throw new SQLException("Couldn't read initial data", e);
				}

				PreparedStatement insertAccount = null;
				PreparedStatement insertOverwatch = null;
				PreparedStatement insertSchedule = null;

				try{ conn.setAutoCommit(true);
				// Account table
				insertAccount = conn.prepareStatement(
						"insert into Account (userName, password, favoriteID)" +
						" values (?, ?, ?)" );
				for (Model model : accountList) {
					insertAccount.setString(1, model.getUserName());
					insertAccount.setString(2, model.getPassword());
					insertAccount.setInt(3, model.getFavoriteID());
					insertAccount.addBatch();
				}
				insertAccount.executeBatch();
				System.out.println("Account table populated");


				//populate TeamInformation table
				insertOverwatch = conn.prepareStatement(
						"insert into TeamInfo (teamID, teamName, teamColor, teamWin, teamLoss, teamMP, teamStanding)" +
						"values (?, ?, ?, ?, ?, ?, ?)");
				for (Overwatch overwatch : overwatchList) {
					insertOverwatch.setInt(1, overwatch.getTeamID());
					insertOverwatch.setString(2, overwatch.getTeamName());
					insertOverwatch.setString(3, overwatch.getTeamColor());
					insertOverwatch.setInt(4, overwatch.getTeamWin());
					insertOverwatch.setInt(5, overwatch.getTeamLoss());
					insertOverwatch.setInt(6, overwatch.getTeamMP());
					insertOverwatch.setInt(7, overwatch.getTeamStanding());
					insertOverwatch.addBatch();
				}
				insertOverwatch.executeBatch();
				System.out.println("TeamInfo table populated");	


				//populate Schedule table
				insertSchedule = conn.prepareStatement(
						"insert into Schedule (scheduleWeek, scheduleWeekday, scheduleDate, scheduleTime, scheduleTeam1ID, scheduleTeam1Score, scheduleTeam2ID, scheduleTeam2Score)" +
						"values (?, ?, ?, ?, ?, ?, ?, ?)");
				for (Schedule schedule : scheduleList) {
					insertSchedule.setInt(1, schedule.getScheduleWeek());
					insertSchedule.setString(2, schedule.getScheduleWeekday());
					insertSchedule.setString(3, schedule.getScheduleDate());
					insertSchedule.setString(4, schedule.getScheduleTime());
					insertSchedule.setInt(5, schedule.getScheduleTeam1ID());
					insertSchedule.setInt(6, schedule.getScheduleTeam1Score());
					insertSchedule.setInt(7, schedule.getScheduleTeam2ID());
					insertSchedule.setInt(8, schedule.getScheduleTeam2Score());
					insertSchedule.addBatch();
				}
				insertSchedule.executeBatch();
				System.out.println("Schedule table populated");		
				return true;
				} finally {
					DBUtil.closeQuietly(insertAccount);
					DBUtil.closeQuietly(insertOverwatch);		
					DBUtil.closeQuietly(insertSchedule);	
				}
			}
		});
	}
	// The main method creates the database tables and loads the initial data.
	public static void main(String[] args) throws IOException, SQLException {
		System.out.println("Creating tables...");
		DerbyDatabase db = new DerbyDatabase();
		db.dropTables();
		db.createTables();
		System.out.println("Loading initial data...");
		db.loadInitialData();
		System.out.println("Library DB successfully initialized!");
	}
}