package com.shiniofthegami.donationrewards.hats.tasks;

import com.shiniofthegami.donationrewards.DonationRewards;

public class HatExpiryCheck implements Runnable{
	private final DonationRewards pl;
	
	public HatExpiryCheck(DonationRewards pl){
		this.pl = pl;
	}
	
	public void run() {
		pl.getHatHandler().checkExpiry();
		
	}

}
