package com.JAVAProject;

public class MinutesFilter implements Filter {
	private int Min;
	private int Max;
	
	public MinutesFilter(int minminutes,int maxminutes){
		this.Min=minminutes;
		this.Max=maxminutes;
	}
	
	public boolean satisfies(String id) {
		return MovieDatabase.getMinutes(id)>=Min && MovieDatabase.getMinutes(id)<=Max;
	}
}
