package com.mrockey28.bukkit.QuickOptions;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.enchantment.PrepareItemEnchantEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;


/**
 * @author mrockey28
 */
public class QuickOptionsListener implements Listener {

	public QuickOptionsListener(final QuickOptionsPlugin plugin) {}	

/////
//EVENT HANDLERS
/////
	
	@EventHandler
	public void onPlayerPickupItem(PlayerPickupItemEvent event) {
		event.setCancelled(true);
		event.getItem().remove();
	}
	
	
	@EventHandler
	public void onPlayerPrepareToEnchant(PrepareItemEnchantEvent event) {
		Player player = event.getEnchanter();
		int level = player.getLevel();
		int bookshelves = event.getEnchantmentBonus();
		int bonus = 5 + (bookshelves/2) + bookshelves;
		int[] offeredValues;
		offeredValues = event.getExpLevelCostsOffered();
		offeredValues[0] = Math.min((level/2),  bonus/2);
		offeredValues[1] = Math.min((level/3)*2, (bonus/3)*2);
		offeredValues[2] = Math.min(level, bonus);
	}
	
}

