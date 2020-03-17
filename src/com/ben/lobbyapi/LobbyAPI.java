package com.ben.lobbyapi;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import com.ben.lobbyapi.components.GUI;
import com.ben.lobbyapi.components.Timer;
import com.ben.lobbyapi.components.dbSetup;

public class LobbyAPI extends JavaPlugin implements Listener
{
	public Timer timer;
	public dbSetup dbs;
	public GUI gui;
	
	public void onEnable()
	{
		timer = new Timer();
		dbSetup = new dbSetup();
		gui = new GUI();
	}
}
