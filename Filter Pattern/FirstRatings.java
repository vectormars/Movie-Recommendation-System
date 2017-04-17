package com.JAVAProject;

import edu.duke.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;


import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord; 


public class FirstRatings {
	public ArrayList<Movie> loadMovies(String fileName) {
		ArrayList<Movie> Movies = new ArrayList<Movie>(); 
		FileResource fr = new FileResource(fileName);
		CSVParser parser = fr.getCSVParser();
		for(CSVRecord rec: parser) {
			Movie m = new Movie(rec.get("id"), rec.get("title"), rec.get("year"), rec.get("genre"), rec.get("director"),rec.get("country"), rec.get("poster"), Integer.parseInt(rec.get("minutes")));
			Movies.add(m);
		}
		return Movies;
	}
	
	private boolean checkGenre(Movie m, String genre){
		boolean ans=false;
		if(m.getGenres().contains(genre)){
			ans=true;
		}
		return ans;
	}
	
	private boolean checklength(Movie m, int length){
		boolean ans=false;
		if(m.getMinutes()>length){
			ans=true;
		}
		return ans;
	}
	
	private int checkDirector(ArrayList<Movie> Movies,String director){
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		for (Movie m : Movies) { 
			if(map.containsKey(m.getDirector())==false){
				map.put(m.getDirector(), 1);
			}else{
				map.put(m.getDirector(), map.get(m.getDirector())+1);
			}
		}
		if(map.containsKey(director)){
			return map.get(director);
		}else{
			return 0;
		}
	}
	
	
	private void maxMovie(ArrayList<Movie> Movies){
		HashMap<String, Integer> map = new HashMap<String, Integer>();

		for (Movie m : Movies) { 
			if(map.containsKey(m.getDirector())==false){
				map.put(m.getDirector(), 1);
			}else{
				map.put(m.getDirector(), map.get(m.getDirector())+1);
			}
		}
		int maxValue = Collections.max(map.values());
		for(String d:map.keySet()){
			if(map.get(d)==maxValue){
				System.out.println(d+" "+maxValue);
			}
		}
	}

	public ArrayList<EfficientRater> loadRaters(String fileName) {
		ArrayList<EfficientRater> Raters = new ArrayList<EfficientRater>(); 
		FileResource fr = new FileResource(fileName);
		CSVParser parser = fr.getCSVParser();
		for(CSVRecord rec: parser) {
			EfficientRater r = new EfficientRater(rec.get("rater_id"));
			r.addRating(rec.get("movie_id"), Double.parseDouble(rec.get("rating")));
			Raters.add(r);
		}
		return Raters;
	}
	
	
	private void testLoadRaters(){
		ArrayList<EfficientRater> Raters=loadRaters("ratings.csv");
		System.out.println("Number of raters: "+numRaters(Raters));
		System.out.println("Number of ratings for a particular rater 193: "+numRatingsID(Raters,"193"));
		maxNumRating(Raters);
		System.out.println("Number of ratings a particular movie: "+numRatingsMovie(Raters,"1798709"));
		System.out.println("How many different movies have been rated? "+uniqueMovies(Raters));
	}
	
	private int numRaters(ArrayList<EfficientRater> Raters){
		HashMap<String,Integer> map=new HashMap<String,Integer>();
		for (EfficientRater r : Raters) { 
			if(!map.containsKey(r.getID())){
				map.put(r.getID(), 1);
			}else{
				map.put(r.getID(), map.get(r.getID())+1);
			}
		}
		return map.size();
	}
	
	
	private int numRatingsID(ArrayList<EfficientRater> Raters,String ID){
		HashMap<String,Integer> map=new HashMap<String,Integer>();
		for (EfficientRater r : Raters) { 
			if(!map.containsKey(r.getID())){
				map.put(r.getID(), 1);
			}else{
				map.put(r.getID(), map.get(r.getID())+1);
			}
		}
		if(map.containsKey(ID)){
			return map.get(ID);
		}else{
			return 0;
		}
	}
	

	private int numRatingsMovie(ArrayList<EfficientRater> Raters,String MovieID){
		int num=0;
		for (EfficientRater r : Raters) { 
			if(r.hasRating(MovieID)){
				num++;
			}
		}
		return num;
	}
	
	private int uniqueMovies(ArrayList<EfficientRater> Raters){
		HashSet<String> set = new HashSet<String>();
		for(EfficientRater r: Raters) {
//			System.out.println("Rater ID " + r.getID() + " has " + r.numRatings() + " ratings");
			ArrayList<String> ratingList = r.getItemsRated();
			for(String s: ratingList) {
//				System.out.println("Movie ID " + s + " is rated as " + r.getRating(s));
				if(!set.contains(s)) {
					set.add(s);
				}

			}
		}
		return set.size();
		
	}
	
	private void maxNumRating(ArrayList<EfficientRater> Raters){
		HashMap<String,Integer> map=new HashMap<String,Integer>();
		for (EfficientRater r : Raters) { 
			if(!map.containsKey(r.getID())){
				map.put(r.getID(), 1);
			}else{
				map.put(r.getID(), map.get(r.getID())+1);
			}
		}
		int maxValue = Collections.max(map.values());
		for(String d:map.keySet()){
			if(map.get(d)==maxValue){
				System.out.println("Rater ID: "+d+", max number of ratings "+maxValue);
			}
		}
	}
	

	
	public static void main(String[] args){
		FirstRatings FR=new FirstRatings();
		ArrayList<Movie> Movies =FR.loadMovies("ratedmoviesfull.csv");
		System.out.println("Movie size: "+Movies.size());
		//for (Movie m : Movies) { 
		//	System.out.println(m); 
		//}
		
		int i=0;
		for (Movie m : Movies) { 
			//System.out.println(FR.checkGenre(m,"Comedy"));
			if(FR.checkGenre(m,"Comedy")==true){
				i++;
			}
		}
		System.out.println("Check Genre of Comedy: "+i);

		int ii=0;
		for (Movie m : Movies) { 
			//System.out.println(FR.checkGenre(m,"Comedy"));
			if(FR.checklength(m,150)==true){
				ii++;
			}
		}
		System.out.println("Check Length >150: "+ii);
		

		//System.out.println("Number of movies by director: "+FR.checkDirector(Movies,"Charles Chaplin"));
		FR.maxMovie(Movies);
		
		FR.testLoadRaters();
	}
	

}
