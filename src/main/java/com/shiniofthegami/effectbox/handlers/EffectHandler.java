package com.shiniofthegami.effectbox.handlers;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.shiniofthegami.effectbox.effectlibrary.ParticleEffect;

public class EffectHandler {
	
	private static Map<String, ParticleEffect> effects = new HashMap<String, ParticleEffect>();
	
	public static void init(){
		//init effect filter
		effects.put("explode", ParticleEffect.EXPLOSION_NORMAL);
		effects.put("largeexplode", ParticleEffect.EXPLOSION_LARGE);
		effects.put("hugeexplosion", ParticleEffect.EXPLOSION_HUGE);
		effects.put("fireworksspark", ParticleEffect.FIREWORKS_SPARK);
		effects.put("splash", ParticleEffect.WATER_SPLASH);
		effects.put("wake", ParticleEffect.WATER_WAKE);
		effects.put("depthsuspend", ParticleEffect.SUSPENDED_DEPTH);
		effects.put("crit", ParticleEffect.CRIT);
		effects.put("magiccrit", ParticleEffect.CRIT_MAGIC);
		effects.put("smoke", ParticleEffect.SMOKE_NORMAL);
		effects.put("largesmoke", ParticleEffect.SMOKE_LARGE);
		effects.put("spell", ParticleEffect.SPELL);
		effects.put("instantspell", ParticleEffect.SPELL_INSTANT);
		effects.put("mobspell", ParticleEffect.SPELL_MOB);
		effects.put("mobspellambient", ParticleEffect.SPELL_MOB_AMBIENT);
		effects.put("witchmagic", ParticleEffect.SPELL_WITCH);
		effects.put("dripwater", ParticleEffect.DRIP_WATER);
		effects.put("driplava", ParticleEffect.DRIP_LAVA);
		effects.put("angryvillager", ParticleEffect.VILLAGER_ANGRY);
		effects.put("townaura", ParticleEffect.TOWN_AURA);
		effects.put("note", ParticleEffect.NOTE);
		effects.put("portal", ParticleEffect.PORTAL);
		effects.put("enchantmenttable", ParticleEffect.ENCHANTMENT_TABLE);
		effects.put("flame", ParticleEffect.FLAME);
		effects.put("lava", ParticleEffect.LAVA);
		effects.put("footstep", ParticleEffect.FOOTSTEP);
		effects.put("reddust", ParticleEffect.REDSTONE);
		effects.put("snowballpoof", ParticleEffect.SNOWBALL);
		effects.put("snowshovel", ParticleEffect.SNOW_SHOVEL);
		effects.put("slime", ParticleEffect.SLIME);
		effects.put("heart", ParticleEffect.HEART);
		effects.put("barrier", ParticleEffect.BARRIER);
		effects.put("droplet", ParticleEffect.WATER_DROP);
		effects.put("mobappearance", ParticleEffect.MOB_APPEARANCE);
		//effects.put("testtrail", ParticleEffect.BARRIER);
	}
	
	public static ParticleEffect getEffect(String name){
		name = name.toLowerCase();
		if(effects.containsKey(name)){
			return effects.get(name);
		}
		return null;
	}
	
	public static Set<String> getEffects(){
		return new HashSet<String>(effects.keySet());
	}
	
	

}
