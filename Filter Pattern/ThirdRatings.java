package com.JAVAProject;

import java.util.ArrayList;
import java.util.HashSet;

public class ThirdRatings {

	private ArrayList<EfficientRater> myRaters;
	public ThirdRatings() {
	    this("ratings.csv");
	}
	
    public ThirdRatings(String ratingsfile){
    	FirstRatings FR=new FirstRatings();
    	myRaters=FR.loadRaters(ratingsfile);
    }
    
	public ArrayList<Rating> getAverageRatings(int minimalRaters){
		ArrayList<String> myMovies = MovieDatabase.filterBy(new TrueFilter());
		ArrayList<Rating> list=new ArrayList<Rating>();
		for (String m : myMovies) {
			double avg=getAverageByID(m,minimalRaters);
			if(avg!=0){
				list.add(new Rating (m, avg));
			}

		}
		return list;
	}
	
    public double getAverageByID(String movieID,int minimalRaters){
    	double numID=0;
    	double sum=0.0;
    	for(EfficientRater r:myRaters){
    		if(r.hasRating(movieID)){
    			ArrayList<String> ratingList = r.getItemsRated();
    			for(String s: ratingList) {
    				sum=sum+r.getRating(s);
        			numID++;
    			}
    		}
    	}
    	if(numID>=minimalRaters){
    		return sum/numID;
    	}else{
    		return 0.0;		
    	}	
    }
    
    public int numRaters(){
		HashSet<String> set = new HashSet<String>();
		for(EfficientRater r: myRaters) {
			String s=r.getID();
				if(!set.contains(s)) {
					set.add(s);
				}

			}
		return set.size();
    }
    
    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters,Filter filterCriteria){
		ArrayList<String> myMovies = MovieDatabase.filterBy(filterCriteria);
		ArrayList<Rating> list=new ArrayList<Rating>();
		for (String m : myMovies) {
			double avg=getAverageByID(m,minimalRaters);
			if(avg!=0){
				list.add(new Rating (m, avg));
			}

		}
		return list;
    	
    }

}
