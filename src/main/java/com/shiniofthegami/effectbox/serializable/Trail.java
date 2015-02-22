package com.shiniofthegami.effectbox.serializable;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.bukkit.entity.Player;

import com.shiniofthegami.effectbox.Expiry;
import com.shiniofthegami.effectbox.effectlibrary.ParticleEffect;
import com.shiniofthegami.effectbox.handlers.EffectHandler;

public class Trail implements Serializable{
	private static final float DEFAULT_X_OFFSET = 0.05F;
	private static final float DEFAULT_Y_OFFSET = 0.05F;
	private static final float DEFAULT_Z_OFFSET = 0.05F;
	private static final float DEFAULT_SPEED = 0.05F;
	private static final int DEFAULT_AMOUNT = 5;
	private static final int DEFAULT_RANGE = 15;
	private static Map<String, Float> xOffsets = new HashMap<String, Float>();
	private static Map<String, Float> yOffsets = new HashMap<String, Float>();
	private static Map<String, Float> zOffsets = new HashMap<String, Float>();
	private static Map<String, Float> speeds = new HashMap<String, Float>();
	private static Map<String, Integer> amounts = new HashMap<String, Integer>();
	private static Map<String, Integer> ranges = new HashMap<String, Integer>();
	private static Set<ParticleEffect> anyMoveTriggers = new HashSet<ParticleEffect>();
	
	private static final long serialVersionUID = 3035283670804341786L;
	private final UUID playerUUID;
	private final ParticleEffect effect;
	private final Date expiryTime;
	private final String name;
	
	public static void init(){
		
		//note
		speeds.put("note", 8F);
		amounts.put("note", 1);
		
		//barrier
		ranges.put("barrier",100);
		
		//mobAppearance
		ranges.put("mobappearance", 1);
		amounts.put("mobappearance",10);
	}
	
	public Trail(Player p, String effectName){
		this.playerUUID = p.getUniqueId();
		this.effect = EffectHandler.getEffect(effectName);
		this.expiryTime = null;
		this.name = effectName.toLowerCase();
	}
	
	public Trail(Player p, String effectName, Expiry expiry){
		this.playerUUID = p.getUniqueId();
		this.effect = EffectHandler.getEffect(effectName);
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(expiry.getModifier(), 1);
		this.expiryTime = cal.getTime();
		this.name = effectName.toLowerCase();
	}
	
	public String getName(){
		return name;
	}
	
	public UUID getPlayerUUID(){
		return playerUUID;
	}
	
	public ParticleEffect getEffect(){
		return effect;
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
	
	public float getXOffset(){
		if(xOffsets.containsKey(name)){
			return xOffsets.get(name);
		}else{
			return DEFAULT_X_OFFSET;
		}
	}
	
	public float getYOffset(){
		if(yOffsets.containsKey(name)){
			return yOffsets.get(name);
		}else{
			return DEFAULT_Y_OFFSET;
		}
	}
	
	public float getZOffset(){
		if(zOffsets.containsKey(name)){
			return zOffsets.get(name);
		}else{
			return DEFAULT_Z_OFFSET;
		}
	}
	
	public float getSpeed(){
		if(speeds.containsKey(name)){
			return speeds.get(name);
		}else{
			return DEFAULT_SPEED;
		}
	}
	
	public int getAmount(){
		if(amounts.containsKey(name)){
			return amounts.get(name);
		}else{
			return DEFAULT_AMOUNT;
		}
	}
	
	public int getRange(){
		if(ranges.containsKey(name)){
			return ranges.get(name);
		}else{
			return DEFAULT_RANGE;
		}
	}
	
	public boolean anyMoveTriggers(){
		return anyMoveTriggers.contains(this.getEffect());
	}
}
