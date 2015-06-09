package com.shiniofthegami.donationrewards;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import com.shiniofthegami.donationrewards.hats.commands.HatCommand;
import com.shiniofthegami.donationrewards.hats.handlers.HatHandler;
import com.shiniofthegami.donationrewards.hats.listeners.HatListener;
import com.shiniofthegami.donationrewards.trails.Trail;
import com.shiniofthegami.donationrewards.trails.command.DonationRewardsCommand;
import com.shiniofthegami.donationrewards.trails.command.TrailCommand;
import com.shiniofthegami.donationrewards.trails.handlers.EffectHandler;
import com.shiniofthegami.donationrewards.trails.handlers.TrailHandler;
import com.shiniofthegami.donationrewards.trails.listeners.PlayerMoveListener;

public class DonationRewards extends JavaPlugin{
	private TrailHandler trailhandler;
	private HatHandler hathandler;
	public void onEnable(){
		this.trailhandler = new TrailHandler(this);
		this.hathandler = new HatHandler(this);
		this.getCommand("trail").setExecutor(new TrailCommand(this));
		this.getCommand("effectbox").setExecutor(new DonationRewardsCommand(this));
		this.getCommand("hat").setExecutor(new HatCommand(this));
		Bukkit.getPluginManager().registerEvents(new PlayerMoveListener(this), this);
		Bukkit.getPluginManager().registerEvents(new HatListener(this), this);
		EffectHandler.init();
		Trail.init();
	}
	
	public void onDisable(){
		
	}
	
	public TrailHandler getTrailHandler(){
		return trailhandler;
	}
	
	public HatHandler getHatHandler(){
		return hathandler;
	}
}
