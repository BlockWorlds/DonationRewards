package com.shiniofthegami.donationrewards;

import java.util.Calendar;

public enum Expiry {
	MONTH("month", Calendar.MONTH), YEAR("year", Calendar.YEAR);
	
	private String name;
	private int calendarmodifier;
	private Expiry(String name, int calendar){
		this.name = name;
		this.calendarmodifier = calendar;
	}
	
	public String getName(){
		return name;
	}
	
	public int getModifier(){
		return calendarmodifier;
	}
	
	public static Expiry get(String name){
		if(name.equalsIgnoreCase("m")||name.equalsIgnoreCase("month")){
			return Expiry.MONTH;
		}else if(name.equalsIgnoreCase("y")||name.equalsIgnoreCase("year")){
			return Expiry.YEAR;
		}
		return null;
	}
}
