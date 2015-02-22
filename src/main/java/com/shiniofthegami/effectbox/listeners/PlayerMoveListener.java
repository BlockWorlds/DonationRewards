package com.shiniofthegami.effectbox.listeners;

import java.util.Set;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import com.shiniofthegami.effectbox.EffectBox;
import com.shiniofthegami.effectbox.serializable.Trail;

public class PlayerMoveListener implements Listener{

	private final EffectBox pl;
	
	public PlayerMoveListener(EffectBox pl){
		this.pl = pl;
	}
	
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent e){
		Set<Trail> playerTrails = pl.getTrailHandler().getTrails(e.getPlayer());
		if(!playerTrails.isEmpty()){
			for(Trail t : playerTrails){
				if(!t.anyMoveTriggers()){
					if(e.getFrom().distance(e.getTo())<1){
						continue;
					}
				}try{
					t.getEffect().display(t.getXOffset(), t.getYOffset(), t.getZOffset(), t.getSpeed(), t.getAmount(), e.getPlayer().getLocation(), t.getRange());
				}catch(Exception exception){
					exception.printStackTrace();
					System.out.println(t.getEffect());
					System.out.println(t.getName());
				}
			}
		}
	}
}
