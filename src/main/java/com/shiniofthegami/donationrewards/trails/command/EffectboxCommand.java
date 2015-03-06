package com.shiniofthegami.donationrewards.trails.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import com.shiniofthegami.donationrewards.DonationRewards;
import com.shiniofthegami.donationrewards.command.CommandHandler;
import com.shiniofthegami.donationrewards.trails.handlers.TrailHandler;

public class EffectboxCommand extends CommandHandler{

	public EffectboxCommand(DonationRewards pl) {
		super(pl);
	}

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(args.length < 1){
			return false;
		}
		TrailHandler handler = pl.getTrailHandler();
		String action = args[0];
		if(action.equalsIgnoreCase("purge")){
			handler.purgeTrails();
			sender.sendMessage(ChatColor.GOLD + "Trails storage has been purged.");
			return true;
		}else if(action.equalsIgnoreCase("save")){
			handler.saveTrails(handler.getTrailsFile());
			sender.sendMessage(ChatColor.GOLD + "Trails have been saved to file.");
			return true;
		}else if(action.equalsIgnoreCase("load")){
			handler.readTrails(handler.getTrailsFile());
			sender.sendMessage(ChatColor.GOLD + "Trails have been loaded from file.");
			return true;
		}
		return false;
	}

}
