package com.Rec;

import java.util.ArrayList;
import java.util.Collections;

public class MovieRunnerSimilarRatings {
	private void printAverageRatings (){
		RaterDatabase.initialize("ratings_short.csv");
		System.out.println("Read data for " + RaterDatabase.size()+ " raters");
		
		MovieDatabase.initialize("ratedmovies_short.csv");
		System.out.println("Read data for "+MovieDatabase.size()+" movies");
		
		//RaterDatabase.RaterPrint();
		
		ArrayList<Rating> aveRating=new FourthRatings().getAverageRatings(1);
		System.out.println("Average Ratings with minimal rater, "+aveRating.size()+" movies found");
		Collections.sort(aveRating);
		for(Rating r:aveRating){
			System.out.println(r.getValue()+" "+MovieDatabase.getTitle(r.getItem()));
		}
	}
	
	private void printAverageRatingsByYearAfterAndGenre(){
		RaterDatabase.initialize("ratings_short.csv");
		System.out.println("Read data for " + RaterDatabase.size()+ " raters");
		MovieDatabase.initialize("ratedmovies_short.csv");
		System.out.println("Read data for "+MovieDatabase.size()+" movies");
		
		AllFilters AF1=new AllFilters();
		AF1.addFilter(new YearAfterFilter(1980));
		AF1.addFilter(new GenreFilter("Romance"));
		ArrayList<Rating> aveRating6=new FourthRatings().getAverageRatingsByFilter(1,AF1);
		System.out.println("3 Combine, "+aveRating6.size()+" movies matched found");
		Collections.sort(aveRating6);
		for(Rating r:aveRating6){
			System.out.println(r.getValue()+" "+MovieDatabase.getYear(r.getItem())+" "+" \""+MovieDatabase.getTitle(r.getItem())+"\" "+
					MovieDatabase.getGenres(r.getItem()));
		}
	}
	

	private void printSimilarRatings(){
		RaterDatabase.clear();
		RaterDatabase.initialize("ratings.csv");
		System.out.println("Read data for " + RaterDatabase.size()+ " raters");
		MovieDatabase.clear();
		MovieDatabase.initialize("ratedmoviesfull.csv");
		System.out.println("Read data for "+MovieDatabase.size()+" movies");
		
		ArrayList<Rating> SimilarRatings=new FourthRatings().getSimilarRatings("65", 20, 5);
//		for(Rating r:SimilarRatings){
//			System.out.println(r.getValue()+" "+MovieDatabase.getYear(r.getItem())+" "+" \""+MovieDatabase.getTitle(r.getItem())+"\" "+
//					MovieDatabase.getGenres(r.getItem()));
//		}
		Rating first=SimilarRatings.get(0);
		System.out.println("value: "+first.getValue()+" "+MovieDatabase.getTitle(first.getItem()));
	}
	
	private void printSimilarRatingsByGenre(){
		RaterDatabase.clear();
		RaterDatabase.initialize("ratings.csv");
		System.out.println("Read data for " + RaterDatabase.size()+ " raters");
		MovieDatabase.clear();
		MovieDatabase.initialize("ratedmoviesfull.csv");
		
		Filter f=new GenreFilter("Action");
		ArrayList<Rating> SimilarRatings=new FourthRatings().getSimilarRatingsByFilter("65", 20, 5, f);
		Rating first=SimilarRatings.get(0);
		System.out.println("value: "+first.getValue()+" "+MovieDatabase.getTitle(first.getItem()));
	}
	
	private void printSimilarRatingsByDirector(){
		RaterDatabase.clear();
		RaterDatabase.initialize("ratings.csv");
		System.out.println("Read data for " + RaterDatabase.size()+ " raters");
		MovieDatabase.clear();
		MovieDatabase.initialize("ratedmoviesfull.csv");
		
		Filter f=new DirectorsFilter("Clint Eastwood,Sydney Pollack,David Cronenberg,Oliver Stone");
		ArrayList<Rating> SimilarRatings=new FourthRatings().getSimilarRatingsByFilter("1034", 10, 3, f);
		Rating first=SimilarRatings.get(0);
		System.out.println("value: "+first.getValue()+" "+MovieDatabase.getTitle(first.getItem()));
	}
	
	
	private void printSimilarRatingsByGenreAndMinutes(){
		RaterDatabase.clear();
		RaterDatabase.initialize("ratings.csv");
		System.out.println("Read data for " + RaterDatabase.size()+ " raters");
		MovieDatabase.clear();
		MovieDatabase.initialize("ratedmoviesfull.csv");
		
		AllFilters AF=new AllFilters();
		AF.addFilter(new GenreFilter("Adventure"));
		AF.addFilter(new MinutesFilter(100,200));
		ArrayList<Rating> SimilarRatings=new FourthRatings().getSimilarRatingsByFilter("65", 10, 5, AF);
		Rating first=SimilarRatings.get(0);
		System.out.println("value: "+first.getValue()+" "+MovieDatabase.getTitle(first.getItem()));
	}
	
	private void printSimilarRatingsByYearAfterAndMinutes(){
		RaterDatabase.clear();
		RaterDatabase.initialize("ratings.csv");
		System.out.println("Read data for " + RaterDatabase.size()+ " raters");
		MovieDatabase.clear();
		MovieDatabase.initialize("ratedmoviesfull.csv");
		
		AllFilters AF=new AllFilters();
		AF.addFilter(new YearAfterFilter(2000));
		AF.addFilter(new MinutesFilter(80,100));
		ArrayList<Rating> SimilarRatings=new FourthRatings().getSimilarRatingsByFilter("65", 10, 5, AF);
		Rating first=SimilarRatings.get(0);
		System.out.println("value: "+first.getValue()+" "+MovieDatabase.getTitle(first.getItem()));
	}
	
	
	public static void main(String[] args) {	
		new MovieRunnerSimilarRatings().printAverageRatings();
		
		System.out.println();
		new MovieRunnerSimilarRatings().printAverageRatingsByYearAfterAndGenre();
		
		System.out.println();
		new MovieRunnerSimilarRatings().printSimilarRatings();
		
		System.out.println();
		new MovieRunnerSimilarRatings().printSimilarRatingsByGenre();
		
		System.out.println();
		new MovieRunnerSimilarRatings().printSimilarRatingsByDirector();
		
		System.out.println();
		new MovieRunnerSimilarRatings().printSimilarRatingsByGenreAndMinutes();
		
		System.out.println();
		new MovieRunnerSimilarRatings().printSimilarRatingsByYearAfterAndMinutes();
	}

}
