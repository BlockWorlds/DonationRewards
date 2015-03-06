package com.shiniofthegami.donationrewards.hats;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.shiniofthegami.donationrewards.Expiry;


public class Hat implements Serializable{

	private static final long serialVersionUID = -4405642463272723765L;
	private final UUID playerUUID;
	private final Date expiryTime;
	private final String name;
	private final Material mat;
	private final ItemMeta matData;
	
	public Hat(Player p, ItemStack item){
		this.playerUUID = p.getUniqueId();
		this.expiryTime = null;
		if(item != null){
			this.name = item.getData().getItemType().toString();
			this.mat = item.getType();
			this.matData = item.getItemMeta();
		}else{
			this.name = "";
			this.mat = null;
			this.matData = null;
		}
		
	}
	
	public Hat(Player p, ItemStack item, Expiry expiry){
		this.playerUUID = p.getUniqueId();
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(expiry.getModifier(), 1);
		this.expiryTime = cal.getTime();
		if(item != null){
			this.name = item.getData().getItemType().toString();
			this.mat = item.getType();
			this.matData = item.getItemMeta();
		}else{
			this.name = "";
			this.mat = null;
			this.matData = null;
		}
	}
	
	public String getName(){
		return name;
	}
	
	public UUID getPlayerUUID(){
		return playerUUID;
	}
	
	public ItemStack getItemStack(){
		ItemStack item = new ItemStack(mat,1);
		item.setItemMeta(matData);
		return item;
	}
	
	public Date getExpiry(){
		return expiryTime;
	}
	
	public boolean isExpired(){
		Date now = new Date();
		if(expiryTime == null)
			return false;
		if(now.after(expiryTime)){
			return true;
		}
		return false;
	}
}
