package com.shiniofthegami.donationrewards.trails.listeners;

import java.util.Set;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import com.shiniofthegami.donationrewards.DonationRewards;
import com.shiniofthegami.donationrewards.trails.Trail;

public class PlayerMoveListener implements Listener{

	private final DonationRewards pl;
	
	public PlayerMoveListener(DonationRewards pl){
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
					t.getEffect().display(t.getXOffset(), t.getYOffset(), t.getZOffset(), t.getSpeed(), t.getAmount(), e.getPlayer().getLocation().add(0.0,0.25,0.0), t.getRange());
				}catch(Exception exception){
					exception.printStackTrace();
				}
			}
		}
	}
}
