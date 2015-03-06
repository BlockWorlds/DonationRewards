package com.shiniofthegami.donationrewards.hats.listeners;

import java.util.Set;

import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.PlayerInventory;

import com.shiniofthegami.donationrewards.DonationRewards;
import com.shiniofthegami.donationrewards.hats.Hat;

public class HatListener implements Listener{
	private static final int HELMET_SLOT = 103;
	private final DonationRewards pl;
	public HatListener(DonationRewards pl){
		this.pl = pl;
	}
	@EventHandler
	public void onInventoryClick(InventoryClickEvent e){
		System.out.println("------------INVENTORY LISTENER (HAT) -------------");
		HumanEntity entity = e.getWhoClicked();
		if(!(entity instanceof Player)){
			return;
		}
		if((e.getClickedInventory() instanceof PlayerInventory)){
			return;
		}
		Player p = (Player) entity;
		Set<Hat> hats = pl.getHatHandler().getHats(p);
		if(hats.isEmpty()){
			return;
		}
		System.out.println("------------INVENTORY LISTENER (HAT) -------------");
		System.out.println(e.getSlot());
		if(e.getSlot()==HELMET_SLOT&&!e.getCurrentItem().getType().equals(Material.AIR)){
			e.setCancelled(true);
			return;
		}
	}
}
