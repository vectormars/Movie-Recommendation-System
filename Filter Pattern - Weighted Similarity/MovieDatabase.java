package com.Rec;

import java.util.*;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import edu.duke.*;


public class MovieDatabase {
    private static HashMap<String, Movie> ourMovies;

    public static void initialize(String moviefile) {
        if (ourMovies == null) {
            ourMovies = new HashMap<String,Movie>();
            loadMovies(moviefile);
        }
    }

    private static void initialize() {
        if (ourMovies == null) {
            ourMovies = new HashMap<String,Movie>();
            loadMovies("ratedmoviesfull.csv");
        }
    }	

	
    private static void loadMovies(String filename) {
		FileResource fr = new FileResource(filename);
		CSVParser parser = fr.getCSVParser();
		for(CSVRecord rec: parser) {
			Movie m = new Movie(rec.get("id"), rec.get("title"), rec.get("year"), rec.get("genre"), rec.get("director"),rec.get("country"), rec.get("poster"), Integer.parseInt(rec.get("minutes")));
			ourMovies.put(m.getID(), m);
		}
    }

    
    public static boolean containsID(String id) {
        initialize();
        return ourMovies.containsKey(id);
    }

    public static int getYear(String id) {
        initialize();
        return ourMovies.get(id).getYear();
    }

    public static String getGenres(String id) {
        initialize();
        return ourMovies.get(id).getGenres();
    }

    public static String getTitle(String id) {
        initialize();
        return ourMovies.get(id).getTitle();
    }

    public static Movie getMovie(String id) {
        initialize();
        return ourMovies.get(id);
    }

    public static String getPoster(String id) {
        initialize();
        return ourMovies.get(id).getPoster();
    }

    public static int getMinutes(String id) {
        initialize();
        return ourMovies.get(id).getMinutes();
    }

    public static String getCountry(String id) {
        initialize();
        return ourMovies.get(id).getCountry();
    }

    public static String getDirector(String id) {
        initialize();
        return ourMovies.get(id).getDirector();
    }

    public static int size() {
        return ourMovies.size();
    }

    public static ArrayList<String> filterBy(Filter f) {
        initialize();
        ArrayList<String> list = new ArrayList<String>();
        for(String id : ourMovies.keySet()) {
            if (f.satisfies(id)) {
                list.add(id);
            }
        }
        
        return list;
    }
    
    public static void clear(){
    	ourMovies= null;
    }

}
