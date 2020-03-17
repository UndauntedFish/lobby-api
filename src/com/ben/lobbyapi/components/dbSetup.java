package com.ben.lobbyapi.components;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class dbSetup
{
	private Connection connection;
	private String host, database, username, password;
	private int port;
	
	/*
	 * Loads all preliminary database host information necessary to send queries and communicate with the DMBS.
	 * 
	 * @param  connection  The java.sql.Connection object as set by the user
	 * @param  host        Usually localhost, the hostname of the DBMS connection as set in user's config.yml
	 * @param  database    The name of the database to hook up to, as set in the user's config.yml
	 * @param  username    Username to sign into the DBMS, as set in the user's config.yml
	 * @param  password    Password to sign into the DBMS, as set in the user's config.yml
	 * @param  port        The port used by the DMBS to connect to the DB
	 */
	public void enterHostData(String host, String database, String username, String password, int port)
	{
		this.host = host;
		this.database = database;
		this.username = username;
		this.password = password;
		this.port = port;
	}
	
	// Opens a connection from the plugin to the database
	public void openConnection() throws SQLException
	{
		// If the connection is already open, then don't attempt to open it again
		if (connection != null && !connection.isClosed())
		{
			return;
		}
		
		connection = DriverManager.getConnection("jdbc:mysql://" + 
		this.host + ":" + this.port + "/" + this.database, this.username, this.password);
	}
	
	
	/* 
	 * If the player's uuid does not appear in the db, consider them to be new. This is their first login.
	 * If the player's uuid DOES appear in the db, then it's not their first login.
	 * 
	 * @param  UUID    uuid of the player to find in the database
	 * @param  table   Name of the table to search
	 * @param  column  Name of the column (in the specified table) to search
	 * @return         True if the player appears in the column of the specified table, false otherwise.
	 */
	public boolean isInDb(String UUID, String table, String column)
	{	
		int numberOfLogins = 0;
		
		/* 
		 * Query: 
		 * 
		 * SELECT COUNT(uuid) AS uuidcount 
		 * FROM player 
		 * WHERE uuid = <arg_uuid>;
		 */
		try
		{
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT COUNT(" + column + ") AS uuidcount FROM " + table + " WHERE " + column + " = '" + UUID + "';");
			numberOfLogins = rs.getInt("uuidcount");
		} catch (SQLException e)
		{
			e.printStackTrace();
			return false;
		}
		
		if (numberOfLogins > 0)
		{
			return true;
		}
		return false;
	}
	
	/*
	 * Returns the connection created here to the user, so that they may send queries to the db
	 */
	public Connection getConnection()
	{
		return this.connection;
	}
}
