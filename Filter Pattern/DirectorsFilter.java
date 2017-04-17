package com.JAVAProject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DirectorsFilter implements Filter {
	private List<String> myList;
	
	public DirectorsFilter(String directors){
		myList = new ArrayList<String>(Arrays.asList(directors.split(",")));
	}
	
	public boolean satisfies(String id) {
		for(String s:myList){
			if(MovieDatabase.getDirector(id).contains(s)){
				return true;
			}
		}
		return false;
	}
	
}
