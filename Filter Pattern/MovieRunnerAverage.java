package com.JAVAProject;

public class MovieRunnerAverage {
	public void printAverageRatings(){
    	SecondRatings SR=new SecondRatings("ratedmovies_short.csv","ratings_short.csv");
    	System.out.println("Movie Size: "+SR.getMovieSize());
    	System.out.println("Rater Size: "+SR.getRaterSize());
	}
	
	public void getAverageRatingOneMovie(){
    	//SecondRatings SR=new SecondRatings("ratedmovies_short.csv","ratings_short.csv");
		SecondRatings SR=new SecondRatings();
    	String movieID1=SR.getID("The Maze Runner");
    	System.out.println("The Maze Runner: "+SR.getAverageByID(movieID1, 0));
    	String movieID2=SR.getID("Moneyball");
    	System.out.println("Moneyball: "+SR.getAverageByID(movieID2, 0));
    	String movieID3=SR.getID("Vacation");
    	System.out.println("Vacation: "+SR.getAverageByID(movieID3, 0));

	}
	
	/*
	public static void main(String[] args){
		//new MovieRunnerAverage().printAverageRatings();
		//SecondRatings SR=new SecondRatings("ratedmovies_short.csv","ratings_short.csv");
		SecondRatings SR=new SecondRatings();
		for(Rating r:SR.getAverageRatings(50)){
			System.out.println("Movie Name: "+SR.getTitle(r.getItem())+" ,Average rating: "+r.getValue());
		}
		System.out.println("Number of Movies have 50 or more ratings: "+SR.getAverageRatings(50).size());
		
		double lowestrating1=10.0;
		String lowestratingMovie1="";
		for(Rating r:SR.getAverageRatings(20)){
			if(r.getValue()<lowestrating1){
				lowestrating1=r.getValue();
				lowestratingMovie1=SR.getTitle(r.getItem());
			}
		}
		System.out.println("Movie Name: "+lowestratingMovie1+" ,Average rating: "+lowestrating1);
		
		double lowestrating2=10.0;
		String lowestratingMovie2="";
		for(Rating r:SR.getAverageRatings(12)){
			if(r.getValue()<lowestrating2){
				lowestrating2=r.getValue();
				lowestratingMovie2=SR.getTitle(r.getItem());
			}
		}
		System.out.println("Movie Name: "+lowestratingMovie2+" ,Average rating: "+lowestrating2);
		//new MovieRunnerAverage().getAverageRatingOneMovie();

	}
	*/
}
