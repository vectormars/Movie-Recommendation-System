package com.JAVAProject;

import java.util.ArrayList;
import java.util.Collections;

public class MovieRunnerWithFilters {
	public static void main(String[] args){
		// Part I. Size
		//ThirdRatings TR=new ThirdRatings("ratings_short.csv");
		ThirdRatings TR=new ThirdRatings("ratings.csv");
		System.out.println("Read data for " + TR.numRaters()+ " raters");
		
		//MovieDatabase.initialize("ratedmovies_short.csv");
		MovieDatabase.initialize("ratedmoviesfull.csv");
		System.out.println("Read data for "+MovieDatabase.size()+" movies");
		
		// Part II. Average Ratings with minimal rater (One filter)
		ArrayList<Rating> aveRating1=TR.getAverageRatings(35);
		System.out.println("Print Average Ratings with minimal rater, "+aveRating1.size()+" movies found");
		Collections.sort(aveRating1);
		//for(Rating r:aveRating1){
		//	System.out.println(r.getValue()+" "+MovieDatabase.getTitle(r.getItem()));
		//}
		
		// Part III.A Average Ratings with minimal rater and Year (Combined filter)
		System.out.println();
		ArrayList<Rating> aveRating2=TR.getAverageRatingsByFilter(20,new YearAfterFilter(2000));
		System.out.println("Print Average Ratings with minimal and year after rater, "+aveRating2.size()+" movies found");
		/*
		Collections.sort(aveRating2);
		for(Rating r:aveRating2){
			System.out.println(r.getValue()+" "+MovieDatabase.getYear(r.getItem())+" "+MovieDatabase.getTitle(r.getItem()));
		}
		*/
		
		// Part III.B Average Ratings with minimal rater and Genre (Combined filter)
		System.out.println();
		ArrayList<Rating> aveRating3=TR.getAverageRatingsByFilter(20,new GenreFilter("Comedy"));
		System.out.println("Print Average Ratings with minimal and Genre rater, "+aveRating3.size()+" movies found");
		/*
		Collections.sort(aveRating3);
		for(Rating r:aveRating3){
			System.out.println(r.getValue()+" \""+MovieDatabase.getTitle(r.getItem())+"\" with Genre: "+MovieDatabase.getGenres(r.getItem()));
		}
		*/
		
		// Part III.C Average Ratings with minimal rater and Minutes (Combined filter)
		System.out.println();
		ArrayList<Rating> aveRating4=TR.getAverageRatingsByFilter(5,new MinutesFilter(105,135));
		System.out.println("Print Average Ratings with minimal and Minutes rater, "+aveRating4.size()+" movies found");
		/*
		Collections.sort(aveRating4);
		for(Rating r:aveRating4){
			System.out.println(r.getValue()+" Time: "+MovieDatabase.getMinutes(r.getItem())+" \""+MovieDatabase.getTitle(r.getItem())+"\"");
		}
		*/
		
		// Part III.D Average Ratings with minimal rater and Director (Combined filter)
		System.out.println();
		ArrayList<Rating> aveRating5=TR.getAverageRatingsByFilter(4,new DirectorsFilter("Clint Eastwood,Joel Coen,Martin Scorsese,Roman Polanski,Nora Ephron,Ridley Scott,Sydney Pollack"));
		System.out.println("Print Average Ratings with minimal and Director rater, "+aveRating5.size()+" movies found");
		/*
		Collections.sort(aveRating5);
		for(Rating r:aveRating5){
			System.out.println(r.getValue()+" \""+MovieDatabase.getTitle(r.getItem())+"\" "+MovieDatabase.getDirector(r.getItem()));
		}
		*/
		
		// Part IV.A Minimal rater + Year after + Genre
		System.out.println();
		AllFilters AF1=new AllFilters();
		AF1.addFilter(new YearAfterFilter(1990));
		AF1.addFilter(new GenreFilter("Drama"));
		ArrayList<Rating> aveRating6=TR.getAverageRatingsByFilter(8,AF1);
		System.out.println("3 Combine, "+aveRating6.size()+" movies found");
		/*
		Collections.sort(aveRating6);
		for(Rating r:aveRating6){
			System.out.println(r.getValue()+" "+MovieDatabase.getYear(r.getItem())+" "+" \""+MovieDatabase.getTitle(r.getItem())+"\" "+
					MovieDatabase.getGenres(r.getItem()));
		}
		*/
		
		// Part IV.B Minimal rater of 1 + Minutes + Directors
		System.out.println();
		AllFilters AF2=new AllFilters();
		AF2.addFilter(new MinutesFilter(90,180));
		AF2.addFilter(new DirectorsFilter("Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack"));
		ArrayList<Rating> aveRating7=TR.getAverageRatingsByFilter(3,AF2);
		System.out.println("3 Combine, "+aveRating7.size()+" movies found");
		/*
		Collections.sort(aveRating6);
		for(Rating r:aveRating7){
			System.out.println(r.getValue()+" Time: "+MovieDatabase.getMinutes(r.getItem())+" \""+MovieDatabase.getTitle(r.getItem())+"\" "+MovieDatabase.getDirector(r.getItem()));
		}
		*/
	}
}
