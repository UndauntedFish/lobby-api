package com.ben.lobbyapi;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class LobbyAPI extends JavaPlugin implements Listener
{
	public Timer timer;
	public QuerySender querySender;
	
	@Override
	public void onEnable()
	{
		timer = new Timer();
		querySender = new QuerySender();
	}
}
