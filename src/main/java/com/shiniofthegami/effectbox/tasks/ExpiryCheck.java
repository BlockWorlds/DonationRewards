package com.shiniofthegami.effectbox.tasks;

import com.shiniofthegami.effectbox.EffectBox;

public class ExpiryCheck implements Runnable{
	private final EffectBox pl;
	
	public ExpiryCheck(EffectBox pl){
		this.pl = pl;
	}
	
	public void run() {
		pl.getTrailHandler().checkExpiry();
		
	}

}
