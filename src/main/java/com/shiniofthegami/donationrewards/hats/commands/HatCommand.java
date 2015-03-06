package com.shiniofthegami.donationrewards.hats.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.shiniofthegami.donationrewards.DonationRewards;
import com.shiniofthegami.donationrewards.Expiry;
import com.shiniofthegami.donationrewards.command.CommandHandler;
import com.shiniofthegami.donationrewards.hats.Hat;

public class HatCommand extends CommandHandler{

	public HatCommand(DonationRewards pl) {
		super(pl);
	}

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(args.length > 3){
			return false;
		}
		//Material material = null;
		ItemStack itemstack = null;
		Player target = null;
		Expiry exp = null;
		if(args.length == 0){
			if(!sender.hasPermission("donationrewards.hat.self")){
				sender.sendMessage(ChatColor.RED + "You are not allowed to set your own hat!");
				return true;
			}
			if(!(sender instanceof Player)){
				sender.sendMessage(ChatColor.RED + "You can only set a hat for yourself from ingame!");
				return true;
			}
			target = (Player) sender;
			itemstack = target.getItemInHand();
			if(itemstack == null || itemstack.getType().equals(Material.AIR)){
				target.sendMessage(ChatColor.RED + "Not holding an item! Either hold the desired hat or specify it in the command!");
				return true;
			}
		}else if(args.length == 1){
			if(!(sender instanceof Player)){
				sender.sendMessage(ChatColor.RED + "You can only execute the one-argument version of this command from ingame!");
				return true;
			}
			if(args[0].equalsIgnoreCase("usage")){
				sender.sendMessage(command.getUsage());
				return true;
			}
			if(args[0].equalsIgnoreCase("remove")){
				if(!sender.hasPermission("donationrewards.hat.remove")){
					sender.sendMessage(ChatColor.RED + "You are not allowed to remove your own hat!");
					return true;
				}
				target = (Player) sender;
				removeHat(target);
				target.sendMessage(ChatColor.GOLD + "Hat removed!");
				return true;
			}
			Material mat = Material.matchMaterial(args[0]);
			if(mat == null){
				if(!sender.hasPermission("donationrewards.hat.others")){
					sender.sendMessage(ChatColor.RED + "You are not allowed to do that!");
					return true;
				}
				target = Bukkit.getPlayer(args[0]);
				if(target == null){
					sender.sendMessage(ChatColor.RED + "Material or Player not found.");
					return true;
				}else{
					itemstack = ((Player)sender).getItemInHand();
					if(itemstack == null || itemstack.getType().equals(Material.AIR)){
						sender.sendMessage(ChatColor.RED + "Not holding an item! Either hold the desired hat or specify it in the command!");
						return true;
					}
				}
			}else{
				if(!sender.hasPermission("donationrewards.hat.self")){
					sender.sendMessage(ChatColor.RED + "You are not allowed to do that!");
					return true;
				}
				target = (Player) sender;
				itemstack = new ItemStack(mat, 1);
			}
		}else if(args.length == 2){
			
		}else{
			if(!sender.hasPermission("donationrewards.hat.others")){
				sender.sendMessage(ChatColor.RED + "You are not allowed to set other people's hat!");
				return true;
			}
			Material mat = Material.matchMaterial(args[0]);
			if(mat == null){
				sender.sendMessage(ChatColor.RED + "Item could not be found!");
				return true;
			}
			target = Bukkit.getPlayer(args[1]);
			if(target == null){
				sender.sendMessage(ChatColor.RED + "Player could not be found!");
				return true;
			}
			exp = Expiry.get(args[2]);
			if(exp == null){
				sender.sendMessage(ChatColor.RED + "Invalid expiry time!");
				return true;
			}
			itemstack = new ItemStack(mat, 1);
		}
		Hat h;
		if(exp == null){
			h = new Hat(target, itemstack);
		}else{
			h = new Hat(target, itemstack, exp);
		}
		pl.getHatHandler().addHat(h);
		if(sender.equals(target)){
			sender.sendMessage(ChatColor.GOLD + "Your hat has been applied!");
		}else{
			sender.sendMessage(ChatColor.GOLD + "Hat applied to " + target.getName() + "!");
			target.sendMessage(ChatColor.GOLD + "A hat has been applied to you!");
		}
		return true;
	}
	
	public void removeHat(Player p){
		Hat h = new Hat(p, null);
		pl.getHatHandler().removeHat(h);
	}

}
