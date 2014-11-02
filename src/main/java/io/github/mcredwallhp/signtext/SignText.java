/**
 *
 * SignText
 * Java reimplementation of the /signtext command found in NerdCH
 * https://github.com/NerdNu/nerdCH/
 * 
 */

package io.github.mcredwallhp.signtext;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;


public final class SignText extends JavaPlugin implements Listener {
	
	
	@Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		Player player = (Player) sender;
		
		if (!player.hasPermission("signtext.edit")) {
            sender.sendMessage(ChatColor.RED + "You do not have permission to do that.");
            return true;
        }
		
		if (cmd.getName().equalsIgnoreCase("signtext")) {
			
			int line;
			String text;
			Block block;
			Sign sign;
			
			if (args.length < 1) {
				sender.sendMessage(ChatColor.RED + "Usage: /signtext <line> <text>");
				sender.sendMessage(ChatColor.RED + "Supports color symbols. E.g. 'this is &4RED'");
	            return true;
			}
			
			if (args.length == 1) {
				text = "";
			} else {
				text = "";
				for (int i=1; i<args.length; i++) {
				    text += args[i] + " ";
				}
				text = ChatColor.translateAlternateColorCodes('&', text);
			}
			
			line = Integer.parseInt(args[0]);
			block = player.getTargetBlock(null, 5);
			
			if (line < 1 || line > 4) {
				sender.sendMessage(ChatColor.RED + "The line number should be from 1 to 4.");
	            return true;
			}
			
			if (block.getType() != Material.WALL_SIGN && block.getType() != Material.SIGN_POST) {
				sender.sendMessage(ChatColor.RED + "That is not a sign.");
	            return true;
			}
			
			sign = (Sign) block.getState();
			sign.setLine((line-1), text);
			sign.update();
			return true;
			
		}
		
		return false;
		
	}

	
}
