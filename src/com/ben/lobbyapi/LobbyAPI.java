package com.ben.lobbyapi;

import org.bukkit.plugin.java.JavaPlugin;

import com.ben.lobbyapi.components.GUI;
import com.ben.lobbyapi.components.Timer;
import com.ben.lobbyapi.components.db;

public class LobbyAPI extends JavaPlugin
{
	public Timer timer;
	public db db;
	public GUI gui;
	
	public void onEnable()
	{
		timer = new Timer();
		db = new db();
		gui = new GUI();
	}
}
