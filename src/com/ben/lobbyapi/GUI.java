package com.ben.lobbyapi;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class GUI
{
	private Inventory gui;
	
	/*
	 * Creates a new instance of a GUI.
	 * 
	 * @param   size   The number of slots in the inventory
	 * @param   title  The title of the GUI as it appears in-game
	 */
	public void create(int size, String title)
	{
		gui = Bukkit.createInventory(null, size, title);
	}
	
	/*
	 * Adds/overwrites an item in a specified slot in the inventory.
	 * 
	 * @param   slotID   The ID of the inventory slot
	 * @param   item     The actual item to add/overwrite to the inventory
	 */
	public void setItem(int slotID, ItemStack item)
	{
		if (slotID > gui.getSize() || slotID < 0)
		{
			return;
		}
		gui.setItem(slotID, item);
	}
	
	/*
	 * Returns thee current instance of the GUI (as an Inventory object).
	 * 
	 * @return      The current instance of the Inventory GUI object.
	 */
	public Inventory get()
	{
		return gui;
	}
}
