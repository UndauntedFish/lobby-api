package com.ben.lobbyapi;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import com.ben.lobbyapi.components.GUI;
import com.ben.lobbyapi.components.QuerySender;
import com.ben.lobbyapi.components.Timer;

public class LobbyAPI extends JavaPlugin implements Listener
{
	public Timer timer;
	public QuerySender querySender;
	public GUI gui;
	
	@Override
	public void onEnable()
	{
		timer = new Timer();
		querySender = new QuerySender();
		gui = new GUI();
	}
}
