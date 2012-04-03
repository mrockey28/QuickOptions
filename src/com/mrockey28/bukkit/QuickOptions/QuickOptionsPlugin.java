package com.mrockey28.bukkit.QuickOptions;

import java.util.logging.Logger;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;



/**
 * @author mrockey28
 */
public class QuickOptionsPlugin extends JavaPlugin {
	private final QuickOptionsListener listener = new QuickOptionsListener(this);
	public static final Logger log = Logger.getLogger("Minecraft");
	public static boolean isPermissions = false;
	
	public void onEnable() {
		//  Place any custom enable code here including the registration of any events
		// Register our events
		getServer().getPluginManager().registerEvents(listener, this);

		// EXAMPLE: Custom code, here we just output some info so we can check all is well
		PluginDescriptionFile pdfFile = this.getDescription();
		log.info( pdfFile.getName() + " version " + pdfFile.getVersion() + " is enabled" );
	}
	
	public void onDisable() {
		//  Place any custom disable code here

		// NOTE: All registered events are automatically unregistered when a plugin is disabled

		// EXAMPLE: Custom code, here we just output some info so we can check all is well
		PluginDescriptionFile pdfFile = this.getDescription();
		log.info( pdfFile.getName() + " version " + pdfFile.getVersion() + " is disabled" );
	}

	@Override
	public boolean onCommand(org.bukkit.command.CommandSender sender,
			org.bukkit.command.Command command, String commandLabel, String[] args) {
		Player player = null;

		String[] split = args;
		
		if(sender instanceof Player) {
			player = (Player) sender;
		}
		
		String commandName = command.getName().toLowerCase();

		if (commandName.equals("quickoptions")) {
			if (!isAllowed(player, "access")) {
				return true;
			}
			String argsOneString = "";
			for (String i : args)
			{
				argsOneString += (" " + i);
			}

			log.info("[PLAYER_COMMAND] " + player.getName().toString() + ": /" + commandLabel.toString() + argsOneString);
			
			if (split.length == 0) {
				//Do stuff
			} else if (split.length == 1) {
				try {					
					if (split[0].equalsIgnoreCase("levelup")) {
						player.setLevel(player.getLevel() + 1);
					}else {
						return false;
					}
				} catch (Exception e) {
					return false;
				}
			}else if (split.length == 2) {
				if (split[0].equalsIgnoreCase("levelup")) {
					log.info("tried " + split[1].toString());
					try {
						player.setLevel(Integer.parseInt(split[1].toString()));
					} catch (Exception e) {
						return false;
					}
				}
			}
			return true;
		}
		return false;
	}
	
	public static boolean isAllowed(Player player, String com) {		
		boolean allowed = false;
		if(isPermissions == true) {
			if(player.hasPermission("QuickOptions.access") && player.hasPermission("QuickOptions."+com)) {
				allowed = true;
			} else {
				allowed = false;
			}
		}else if(isPermissions == false && !com.equalsIgnoreCase("none")) {
			allowed = true;
		}
		
		return allowed;
	}
}