package com.shiniofthegami.effectbox;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import com.shiniofthegami.effectbox.command.EffectboxCommand;
import com.shiniofthegami.effectbox.command.TrailCommand;
import com.shiniofthegami.effectbox.handlers.EffectHandler;
import com.shiniofthegami.effectbox.handlers.TrailHandler;
import com.shiniofthegami.effectbox.listeners.PlayerMoveListener;
import com.shiniofthegami.effectbox.serializable.Trail;

public class EffectBox extends JavaPlugin{
	private TrailHandler trailhandler;
	public void onEnable(){
		this.trailhandler = new TrailHandler(this);
		this.getCommand("trail").setExecutor(new TrailCommand(this));
		this.getCommand("effectbox").setExecutor(new EffectboxCommand(this));
		Bukkit.getPluginManager().registerEvents(new PlayerMoveListener(this), this);
		EffectHandler.init();
		Trail.init();
	}
	
	public void onDisable(){
		
	}
	
	public TrailHandler getTrailHandler(){
		return trailhandler;
	}
}
