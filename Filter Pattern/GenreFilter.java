package com.JAVAProject;

public class GenreFilter implements Filter {
	private String mygenre;
	
	public GenreFilter(String genre){
		this.mygenre=genre;
	}
	
	public boolean satisfies(String id) {
		return MovieDatabase.getGenres(id).contains(mygenre);
	}
}
