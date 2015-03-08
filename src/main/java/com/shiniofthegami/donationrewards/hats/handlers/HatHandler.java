package com.shiniofthegami.donationrewards.hats.handlers;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.google.gson.Gson;
import com.shiniofthegami.donationrewards.DonationRewards;
import com.shiniofthegami.donationrewards.hats.Hat;

public class HatHandler {
	private static final File HATS_FILE = new File("plugins/DonationRewards/Hats.json");
	private Set<Hat> hats = new HashSet<Hat>();
	private final DonationRewards pl;
	public HatHandler(DonationRewards pl){
		this.pl = pl;
		this.init();
	}
	
	public void checkExpiry(){
		Set<Hat> newHats = new HashSet<Hat>(hats);
		for(Hat t : hats){
			if(t.isExpired()){
				Player p = Bukkit.getPlayer(t.getPlayerUUID());
				if(p != null){
					p.sendMessage(ChatColor.GOLD + "Your Hat of the type " +  t.getName() + " has expired.");
				}
				newHats.remove(t);
			}
		}
		hats = newHats;
		saveHats(getHatsFile());
	}
	
	private void init(){
		final File effectsFolder = new File("plugins/DonationRewards/");
		if(!effectsFolder.exists()){
			effectsFolder.mkdirs();
		}
		readHats(getHatsFile());
	}
	
	public void addHat(Hat t){
		if(!hats.contains(t)){
			if(getHats(t.getPlayerUUID()).isEmpty()){
				hats.add(t);
			}else{
				removeHat(t);
				hats.add(t);
			}
		}	
		saveHats(getHatsFile());
		Bukkit.getPlayer(t.getPlayerUUID()).getInventory().setHelmet(t.getItemStack());
	}
	
	public Set<Hat> getHats(Player p){
		Set<Hat> playerHats = new HashSet<Hat>();
		for(Hat t : hats){
			if(t.getPlayerUUID().equals(p.getUniqueId())){
				playerHats.add(t);
			}
		}
		return playerHats;
	}
	
	public Set<Hat> getHats(UUID p){
		Set<Hat> playerHats = new HashSet<Hat>();
		for(Hat t : hats){
			if(t.getPlayerUUID().equals(p)){
				playerHats.add(t);
			}
		}
		return playerHats;
	}
	
	public void removeHat(Hat t){
		if(t == null || t.getPlayerUUID() == null)
			return;
		Set<Hat> newHats = new HashSet<Hat>(hats);
		for(Hat Hat : hats){
			if(Hat == null)
				continue;
			if(Hat.getPlayerUUID() == null)
				newHats.remove(Hat);
			if((Hat.getPlayerUUID().equals(t.getPlayerUUID()))){
				newHats.remove(Hat);
			}
		}
		hats = newHats;
		saveHats(getHatsFile());
	}
	
	public void saveHats(final File HatsFile){
		final Gson gson = new Gson();
		if(!HatsFile.exists()){
			try{
				HatsFile.createNewFile();
			}catch(final IOException e){
				e.printStackTrace();
				return;
			}
		}else{
            if (!HatsFile.delete())
            {
                pl.getLogger().severe("Can not save config files");
            }
            try
            {
            	HatsFile.createNewFile();
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
            pw = new PrintWriter(HatsFile);
        }
        catch (final Exception e)
        {
            e.printStackTrace();
            return;
        }
        for (final Hat b : hats)
        {
                pw.println(gson.toJson(b));
        }
        pw.close();
	}
	public void readHats(final File HatsFile){
		final Gson gson = new Gson();
		if(HatsFile.exists()){
			Scanner scan;
			try
            {
                scan = new Scanner(HatsFile);
            }
            catch (final Exception e)
            {
                pl.getLogger().severe("Can not open Hats file");
                e.printStackTrace();
                return;
            }
			try
            {
                while (scan.hasNextLine())
                {
                    final Hat hat = gson.fromJson(scan.nextLine(), Hat.class);
                    addHat(hat);
                }
            }
            catch (final Exception e)
            {
                pl.getLogger().severe("Can not read Hats file");
                e.printStackTrace();
                return;
            }
            finally
            {
                scan.close();
            }
		}
	}
	
	public void purgeHats(){
		hats.clear();
	}
	
	public File getHatsFile(){
		return HATS_FILE;
	}
}
