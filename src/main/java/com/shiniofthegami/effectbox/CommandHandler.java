package com.shiniofthegami.effectbox;

import org.bukkit.command.CommandExecutor;

public abstract class CommandHandler implements CommandExecutor{
	protected final EffectBox pl;
	public CommandHandler(EffectBox pl){
		this.pl = pl;
	}
}
