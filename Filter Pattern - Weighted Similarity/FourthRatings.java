package com.Rec;

import java.util.ArrayList;
import java.util.Collections;

public class FourthRatings {
    public double getAverageByID(String movieID,int minimalRaters){
    	double numID=0.0;
    	double sum=0.0;
    	ArrayList<EfficientRater> myRaters = RaterDatabase.getRaters();
//    	System.out.println("SIZE "+myRaters.size());
    	for(EfficientRater r:myRaters){
//    		System.out.println("ID: "+r.getID());
//    		System.out.println("Number of ratings "+r.numRatings());
    		if(r.hasRating(movieID)){
    				sum=sum+r.getRating(movieID);
        			numID++;
    		}
    	}
    	if(numID>=minimalRaters){
    		return sum/numID;
    	}else{
    		return 0.0;		
    	}	
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
    
    
	private double dotProduct(Rater me, Rater r){
		double ans = 0;
		ArrayList<String> Movielist=me.getItemsRated();
		for(String m:Movielist){
			if(r.hasRating(m)){
				ans=ans + (me.getRating(m)-5)*(r.getRating(m)-5);
			}
		}
		return ans;
	}
	
	
	private ArrayList<Rating> getSimilarities(String id){
		ArrayList<Rating> list= new ArrayList<Rating>();
		Rater me=RaterDatabase.getRater(id);
		ArrayList<EfficientRater> myRaters = RaterDatabase.getRaters();
		for(EfficientRater r:myRaters){
			if(!r.getID().equals(id)){
				list.add(new Rating(r.getID(),dotProduct(me,r)));
			}
		}
		Collections.sort(list, Collections.reverseOrder());
		return list;
	}
	
	public ArrayList<Rating> getSimilarRatings(String id,int numSimilarRaters,int minimalRaters){
    	ArrayList<Rating> res = new ArrayList<Rating>();
    	ArrayList<Rating> list = getSimilarities(id);	
    	ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());	
	    for (String movieID : movies) {
        	double weightedAverage = 0;
        	double sum = 0;
        	int countRaters = 0;
	    	for (int i = 0; i < numSimilarRaters; i++) {
	    		Rating r = list.get(i);
	    		double weight = r.getValue();
	    		String raterID = r.getItem();
	    		Rater myRater = RaterDatabase.getRater(raterID);
	    		if(myRater.hasRating(movieID)) {
	    			countRaters++;
	    			sum += weight * myRater.getRating(movieID);
	    		}
	    		
	    	}
	    	if (countRaters >= minimalRaters) {
	    		weightedAverage = sum / countRaters;
	    		res.add(new Rating(movieID, weightedAverage));
			}			
	    }
		Collections.sort(res, Collections.reverseOrder());
		return res;
	}
	
	
	
	public ArrayList<Rating> getSimilarRatingsByFilter(String id,int numSimilarRaters,int minimalRaters,Filter filterCriteria){
		ArrayList<Rating> res = new ArrayList<Rating>();
    	ArrayList<Rating> list = getSimilarities(id);	
    	ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);
	    for (String movieID : movies) {
        	double weightedAverage = 0;
        	double sum = 0;
        	int countRaters = 0;
	    	for (int i = 0; i < numSimilarRaters; i++) {
	    		Rating r = list.get(i);
	    		double weight = r.getValue();
	    		String raterID = r.getItem();
	    		Rater myRater = RaterDatabase.getRater(raterID);
	    		if(myRater.hasRating(movieID)) {
	    			countRaters++;
	    			sum += weight * myRater.getRating(movieID);
	    		}
	    		
	    	}
	    	if (countRaters >= minimalRaters) {
	    		weightedAverage = sum / countRaters;
	    		res.add(new Rating(movieID, weightedAverage));
			}			
	    }
		Collections.sort(res, Collections.reverseOrder());
		return res;	
    }
	
	
	
}
