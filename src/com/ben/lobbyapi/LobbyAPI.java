package com.ben.lobbyapi;

import org.bukkit.plugin.java.JavaPlugin;

import com.ben.lobbyapi.components.GUI;
import com.ben.lobbyapi.components.Timer;
import com.ben.lobbyapi.components.dbSetup;

public class LobbyAPI extends JavaPlugin
{
	public Timer timer;
	public dbSetup dbSetup;
	public GUI gui;
	
	public void onEnable()
	{
		timer = new Timer();
		dbSetup = new dbSetup();
		gui = new GUI();
	}
}
