package com.ben.lobbyapi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class QuerySender
{
	private Connection connection;
	private String host, database, username, password;
	private int port;
	
	/*
	 * Loads all preliminary database host information necessary to send queries and communicate with the DMBS.
	 * 
	 * @param   connection   The java.sql.Connection object as set by the user.
	 * @param   host         Usually localhost, the hostname of the DBMS connection as set in user's config.yml.
	 * @param   database     
	 * 
	 */
	public void enterHostData(Connection connection, String host, String database, String username, String password, int port)
	{
		this.connection = connection;
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
	
	
	
	// If the player's uuid does not appear in the db, consider them to be new. This is their first login.
	// If the player's uuid DOES appear in the db, then it's not their first login.
	public boolean isInDb(String UUID, String table)
	{	
		int numberOfLogins = 0;
		
		// Query: SELECT COUNT(UUID) AS uuidcount FROM player;
		try
		{
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT COUNT(UUID) AS uuidcount FROM " + table + " WHERE UUID = '" + UUID + "';");
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
}
