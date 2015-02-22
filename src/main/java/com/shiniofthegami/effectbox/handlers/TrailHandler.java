package com.shiniofthegami.effectbox.handlers;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.google.gson.Gson;
import com.shiniofthegami.effectbox.EffectBox;
import com.shiniofthegami.effectbox.serializable.Trail;

public class TrailHandler {
	private static final File TRAILS_FILE = new File("plugins/EffectBox/trails.json");
	private Set<Trail> trails = new HashSet<Trail>();
	private final EffectBox pl;
	public TrailHandler(EffectBox pl){
		this.pl = pl;
		this.init();
	}
	
	public void checkExpiry(){
		Set<Trail> newTrails = new HashSet<Trail>(trails);
		for(Trail t : trails){
			if(t.isExpired()){
				Player p = Bukkit.getPlayer(t.getPlayerUUID());
				if(p != null){
					p.sendMessage(ChatColor.GOLD + "Your trail of the type " + EffectHandler.getEffectName(t.getEffect()) + " has expired.");
				}
				newTrails.remove(t);
			}
		}
		trails = newTrails;
		saveTrails(getTrailsFile());
	}
	
	private void init(){
		final File effectsFolder = new File("plugins/EffectBox/");
		if(!effectsFolder.exists()){
			effectsFolder.mkdirs();
		}
		readTrails(getTrailsFile());
	}
	
	public void addTrail(Trail t){
		if(!trails.contains(t)){
			trails.add(t);
		}
		saveTrails(getTrailsFile());
	}
	
	public Set<Trail> getTrails(Player p){
		Set<Trail> playerTrails = new HashSet<Trail>();
		for(Trail t : trails){
			if(t.getPlayerUUID().equals(p.getUniqueId())){
				playerTrails.add(t);
			}
		}
		return playerTrails;
	}
	
	public void removeTrail(Trail t){
		if(t == null || t.getPlayerUUID() == null || t.getEffect() == null)
			return;
		Set<Trail> newTrails = new HashSet<Trail>(trails);
		for(Trail trail : trails){
			if(trail == null)
				continue;
			if(trail.getPlayerUUID() == null || trail.getEffect() == null)
				newTrails.remove(trail);
			if((trail.getPlayerUUID().equals(t.getPlayerUUID()))&&(trail.getEffect().equals(t.getEffect()))){
				newTrails.remove(trail);
			}
		}
		trails = newTrails;
		saveTrails(getTrailsFile());
	}
	
	public void saveTrails(final File trailsFile){
		final Gson gson = new Gson();
		if(!trailsFile.exists()){
			try{
				trailsFile.createNewFile();
			}catch(final IOException e){
				e.printStackTrace();
				return;
			}
		}else{
            if (!trailsFile.delete())
            {
                pl.getLogger().severe("Can not save config files");
            }
            try
            {
            	trailsFile.createNewFile();
            }
            catch (final IOException e)
            {
                e.printStackTrace();
                return;
            }
        }
		PrintWriter pw;
        try
        {
            pw = new PrintWriter(trailsFile);
        }
        catch (final Exception e)
        {
            e.printStackTrace();
            return;
        }
        for (final Trail b : trails)
        {
                pw.println(gson.toJson(b));
        }
        pw.close();
	}
	public void readTrails(final File trailsFile){
		final Gson gson = new Gson();
		if(trailsFile.exists()){
			Scanner scan;
			try
            {
                scan = new Scanner(trailsFile);
            }
            catch (final Exception e)
            {
                pl.getLogger().severe("Can not open trails file");
                e.printStackTrace();
                return;
            }
			try
            {
                while (scan.hasNextLine())
                {
                    final Trail trail = gson.fromJson(scan.nextLine(), Trail.class);
                    addTrail(trail);
                }
            }
            catch (final Exception e)
            {
                pl.getLogger().severe("Can not read trails file");
                e.printStackTrace();
                return;
            }
            finally
            {
                scan.close();
            }
		}
	}
	
	public void purgeTrails(){
		trails.clear();
	}
	
	public File getTrailsFile(){
		return TRAILS_FILE;
	}
}
