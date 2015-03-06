package com.shiniofthegami.donationrewards.command;

import org.bukkit.command.CommandExecutor;

import com.shiniofthegami.donationrewards.DonationRewards;

public abstract class CommandHandler implements CommandExecutor{
	protected final DonationRewards pl;
	public CommandHandler(DonationRewards pl){
		this.pl = pl;
	}
}
