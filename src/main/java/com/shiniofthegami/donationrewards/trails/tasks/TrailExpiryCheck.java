package com.shiniofthegami.donationrewards.trails.tasks;

import com.shiniofthegami.donationrewards.DonationRewards;

public class TrailExpiryCheck implements Runnable{
	private final DonationRewards pl;
	
	public TrailExpiryCheck(DonationRewards pl){
		this.pl = pl;
	}
	
	public void run() {
		pl.getTrailHandler().checkExpiry();
		
	}

}
