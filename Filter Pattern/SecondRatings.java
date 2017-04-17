package com.JAVAProject;

import java.util.*;


public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<EfficientRater> myRaters;
    
    public SecondRatings() {
        // default constructor
    	this("ratedmoviesfull.csv", "ratings.csv");
    }
    
    public SecondRatings(String moviefile,String ratingsfile){
    	FirstRatings FR=new FirstRatings();
    	myMovies=FR.loadMovies(moviefile);
    	myRaters=FR.loadRaters(ratingsfile);
    }
    
    public  int getMovieSize(){
    	return myMovies.size();
    }
    
    public  int getRaterSize(){
		HashSet<String> set = new HashSet<String>();
		for(EfficientRater r: myRaters) {
				if(!set.contains(r.getID())) {
					set.add(r.getID());
				}
			}
		return set.size();
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
    

    
	public ArrayList<Rating> getAverageRatings(int minimalRaters){
		ArrayList<Rating> list=new ArrayList<Rating>();
		for (Movie m : myMovies) {
			double avg=getAverageByID(m.getID(),minimalRaters);
			if(avg!=0){
				list.add(new Rating (m.getID(), avg));
			}

		}
		return list;
	}
    
	public String getTitle(String id){
		String ans="Not found";
		for (Movie m : myMovies) {
			if(m.getID().equals(id)){
				ans=m.getTitle();
			}
		}
		return ans;
	}
    
	public String getID(String title){
		String ans="NO SUCH TITLE.";
		for (Movie m : myMovies) {
			if(m.getTitle().equals(title)){
				ans=m.getID();
			}
		}
		return ans;
	}
    

}
